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
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
    private List<Team> teams = Collections.synchronizedList(new ArrayList<Team>());

    private final Map<String, Game> scheduledGames = new LinkedHashMap<String, Game>();

    private Map<Game, ISimulationThread> startedGames = new HashMap<Game, ISimulationThread>();

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
        registerGame(null, teamA, teamB, location);
    }

    /** {@inheritDoc} */
    @Override
    public void registerGame(String name, Team teamA, Team teamB, Location location)
    {
        Game game = createGameInstance(name, teamA, teamB, location, null);
        addGame(game);
    }

    /** {@inheritDoc} */
    @Override
    public void registerGame(Team teamA, Team teamB, Location location, Map<String, String> metadata)
    {
        registerGame(null, teamA, teamB, location, metadata);
    }

    /** {@inheritDoc} */
    @Override
    public void registerGame(String name, Team teamA, Team teamB, Location location, Map<String, String> metadata)
    {
        Game game = createGameInstance(name, teamA, teamB, location, metadata);
        addGame(game);
    }

    /**
     * Creates a new game instance of the specific type
     * 
     * @param name
     * @param teamA
     * @param teamB
     * @param location
     * @return
     */

    protected abstract Game createGameInstance(String name,
                                               Team teamA,
                                               Team teamB,
                                               Location location,
                                               Map<String, String> metadata);

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

    public void resetGame(Game game)
    {
        cancelSimulation(game);
        game.resetGame();
    }

    /** {@inheritDoc} */
    @Override
    public void registerTeam(Team team)
    {
        if (!this.teams.contains(team))
        {
            this.teams.add(team);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<Team> getTeams()
    {
        return this.teams;
    }

    /** {@inheritDoc} */
    @Override
    public void removeTeam(Team team) throws IllegalStateException
    {
        if (!isPlaying(team))
        {
            this.teams.remove(team);
            removeGamesFromSchedule(team);
        }
        else
        {
            throw new IllegalStateException("Impossible to unregister teamm: " + team.getTeamName()
                                            + ". The selected team is currently playing one or more games.");
        }
    }

    /** {@inheritDoc} */
    @Override
    public String renameGame(Game game, String newName)
    {
        String oldName = "";
        for (Game scheduledGame : this.scheduledGames.values())
        {
            if (scheduledGame.equals(game))
            {
                oldName = scheduledGame.getName();
                scheduledGame.setName(newName);
            }
        }
        return oldName;
    }

    /** {@inheritDoc} */
    @Override
    public void shuffleTeams()
    {
        Collections.shuffle(this.teams);
    }

    /**
     * @param team
     */
    private void removeGamesFromSchedule(Team team)
    {
        List<String> gamesToRemove = new ArrayList<String>();
        for (String key : this.scheduledGames.keySet())
        {
            if (this.scheduledGames.get(key).isTeamRegistered(team))
            {
                gamesToRemove.add(key);
            }
        }
        for (String deletedKey : gamesToRemove)
        {
            this.scheduledGames.remove(deletedKey);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isPlaying(Team team)
    {
        for (Game game : this.startedGames.keySet())
        {
            if (game.isTeamRegistered(team))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a Map<String,Game> containing the scheduledGames of this
     *         BaseSportService
     */
    protected Map<String, Game> getScheduledGames()
    {
        return this.scheduledGames;
    }

    private void doSimulateGame(Game game, SimulationSpeed speed)
    {
        doStopSimulation(game);
        ISimulationThread gameSimulation = createAndStartGameSimulation(game, speed);
        checkSimulationStatus();
        if (this.simulationOngoing == false)
        {
            for (ISportServiceListener listener : this.listenersList)
            {
                listener.simulationStarted();
            }
        }
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
    protected abstract ISimulationThread createAndStartGameSimulation(Game game, SimulationSpeed speed);

    private void doStopSimulation(Game game)
    {
        if (!this.scheduledGames.containsKey(game.getId()))
        {
            throw new IllegalArgumentException("Game: " + game.toString()
                                               + " is not a game registered in this Simulator service");
        }
        ISimulationThread oldSimulation = this.startedGames.get(game);
        if (oldSimulation != null)
        {
            oldSimulation.cancelSimulation();
        }
        this.startedGames.remove(game);
        checkSimulationStatus();
        if (this.simulationOngoing == false)
        {
            for (ISportServiceListener listener : this.listenersList)
            {
                listener.simulationEnded();
            }
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

    /**
     * @param game
     */
    private void cancelSimulation(Game game)
    {
        if (this.startedGames.keySet().contains(game))
        {
            ISimulationThread gameSimulation = this.startedGames.get(game);
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
        this.startedGames.remove(game);
        checkSimulationStatus();
        if (this.simulationOngoing == false)
        {
            for (ISportServiceListener listener : this.listenersList)
            {
                listener.simulationEnded();
            }
        }
    }

    /**
     * 
     */
    private void checkSimulationStatus()
    {
        this.simulationOngoing = this.startedGames.size() > 0;
    }

}

// -----------------------------------------------------------------------------
