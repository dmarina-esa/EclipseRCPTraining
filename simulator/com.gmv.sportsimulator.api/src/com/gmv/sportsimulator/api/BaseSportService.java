//-----------------------------------------------------------------------------
//
// (C) 2019 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : com.gmv.sportsimulator.tennis
//
// File Name : TennisSimulator.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.ISportServiceListener;
import com.gmv.sportsimulator.api.service.SimulationSpeed;

/**
 * @author David Marina
 *
 */
public abstract class BaseSportService implements ISportService
{
    private final Map<String, Game> scheduledGames = new HashMap<String, Game>();

    private Map<Game, IGameSimulator> startedGames = new HashMap<Game, IGameSimulator>();

    private List<ISportServiceListener> listenersList = new ArrayList<ISportServiceListener>();

    private boolean simulationOngoing;


    /**
     * Creates a new TennisSimulator
     */
    public BaseSportService()
    {}

    /** {@inheritDoc} */
    @Override
    public void registerGame(Team teamA, Team teamB, Location location)
    {
        Game game = createGameInstance(teamA, teamB, location, null);
        addGame(game);
    }

    /**
     * @param teamA
     * @param teamB
     * @param location
     * @return
     */

    protected abstract Game createGameInstance(Team teamA, Team teamB, Location location, Map<String, String> metadata);
    
    /** {@inheritDoc} */
    @Override
    public void registerGame(Team teamA, Team teamB, Location location, Map<String, String> metadata)
    {
        Game game = createGameInstance(teamA, teamB, location, metadata);
        addGame(game);
    }

    /** {@inheritDoc} */
    @Override
    public void registerServiceListener(ISportServiceListener listener)
    {
        this.listenersList.add(listener);
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void simulateGame(Game game, SimulationSpeed speed)
    {
        executeInSeparateThread(() -> doSimulateGame(game, speed));
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void simulateAllGames(SimulationSpeed speed)
    {
        executeInSeparateThread(() -> {
            doResetAllGames();
            for (Game game : this.scheduledGames.values())
            {
                doSimulateGame(game, speed);
            }
        });

    }

    /** {@inheritDoc} */
    @Override
    public void stopAllSimulations()
    {
        executeInSeparateThread(() -> {
            for (Game game : this.scheduledGames.values())
            {
                doStopSimulation(game);
            }
        });

    }

    /** {@inheritDoc} */
    @Override
    public void stopSimulation(Game game)
    {
        executeInSeparateThread(() -> doStopSimulation(game));

    }

    /** {@inheritDoc} */
    @Override
    public List<Game> getStartedGames()
    {
        List<Game> gamesStarted = new ArrayList<Game>();
        gamesStarted.addAll(this.startedGames.keySet());
        return gamesStarted;
    }

    /** {@inheritDoc} */
    @Override
    public List<Game> getRegisteredGames()
    {
        List<Game> registeredGames = new ArrayList<Game>();
        registeredGames.addAll(this.scheduledGames.values());
        return registeredGames;
    }

    /** {@inheritDoc} */
    @Override
    public void resetAllGames()
    {
        executeInSeparateThread(() -> {
            doResetAllGames();
        });
    }
    
    /** {@inheritDoc} */
    @Override
    public boolean isOngoingSimulation()
    {
        return this.simulationOngoing;
    }

    /**
     * @return a Map<String,Game> containing the scheduledGames of this BaseSportService
     */
    protected Map<String, Game> getScheduledGames()
    {
        return this.scheduledGames;
    }

    private void doSimulateGame(Game game, SimulationSpeed speed)
    {
        doStopSimulation(game);
        IGameSimulator gameSimulation = createAndStartGameSimulation(game, speed);
        this.startedGames.put(game, gameSimulation);
        this.simulationOngoing = true;
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.gameStarted(game);
        }
    }

    /**
     * @param game
     * @param speed
     * @return
     */
    protected abstract IGameSimulator createAndStartGameSimulation(Game game, SimulationSpeed speed);

    private void doStopSimulation(Game game)
    {
        if (!this.scheduledGames.containsKey(game.getId()))
        {
            throw new IllegalArgumentException("Game: " + game.toString()
                                               + " is not a game registered in this Simulator service");
        }
        IGameSimulator oldSimulation = this.startedGames.get(game);
        if (oldSimulation != null)
        {
            oldSimulation.cancelSimulation();
        }
    }

    private void doResetAllGames()
    {
        for (Game game : this.scheduledGames.values())
        {
            resetGame(game);
            for (ISportServiceListener listener : this.listenersList)
            {
                listener.gameReseted(game, game.getResult());
            }
        }
    }

    private static void executeInSeparateThread(Runnable runnable)
    {
        new Thread(runnable).start();
    }

    public void resetGame(Game game)
    {
        cancelSimulation(game);
        game.resetGame();
    }

    /**
     * @param game
     */
    private void cancelSimulation(Game game)
    {
        if (this.startedGames.keySet().contains(game))
        {
            IGameSimulator gameSimulation = this.startedGames.get(game);
            gameSimulation.cancelSimulation();
            while (gameSimulation.isRunnableAlive())
            {
                try
                {
                    Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                    new RuntimeException("Error while waiting for simulation to be cancelled");
                }
            }
        }
    }

    protected void addGame(Game game)
    {
        this.scheduledGames.put(game.getId(), game);
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.gameAdded(game);
        }
    }

    protected void notifyResultChanged(Game game)
    {
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.updateResult(game, game.getResult().getScoresString());
        }
    }

    protected void notifyGameEnded(Game game)
    {
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.gameFinalised(game, game.getWinnerTeam(), game.getResult());
        }
        checkSimulationStatus();
    }

    /**
     * 
     */
    private void checkSimulationStatus()
    {
        boolean ended = true;
        for (Game game : this.scheduledGames.values())
        {
            if (!game.isGameFinished())
            {
                ended = false;
            }
        }
        if (ended)
        {
            this.simulationOngoing = false;
        }

    }


    

}

// -----------------------------------------------------------------------------
