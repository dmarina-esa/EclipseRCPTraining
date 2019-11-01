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

package com.gmv.sportsimulator.tennis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.osgi.service.component.annotations.Component;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.ISportServiceListener;
import com.gmv.sportsimulator.api.service.SimulationSpeed;

/**
 * @author David Marina
 *
 */
@Component(service = ISportService.class, name="TennisSimulator")
public class TennisSimulator implements ISportService
{
    private final Map<String, Game> scheduledGames = new HashMap<String, Game>();
    private List<Game> startedGames = new ArrayList<Game>();
    private List<ISportServiceListener> listenersList = new ArrayList<ISportServiceListener>();
    
    /**
     * Creates a new TennisSimulator
     */
    public TennisSimulator()
    {
    }

    /** {@inheritDoc} */
    @Override
    public void addGame(Game game)
    {
        this.scheduledGames.put(game.getId(), game);
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.gameAdded(game);
        }
    }
    
    @Override
    public void registerServiceListener(ISportServiceListener listener)
    {
        this.listenersList .add(listener);
    }
    
    /** {@inheritDoc} */
    @Override
    public void simulateGame(Game game, SimulationSpeed speed)
    {
        startGame(game.getId());
        TennisSimulation gameSimulation = new TennisSimulation(game.getId(), speed);
        gameSimulation.start();
    }
    
    public void startGame(String gameId)
    {
        Game game = this.scheduledGames.get(gameId);
        this.startedGames .add(game);
        for (ISportServiceListener listener : this.listenersList)
        {
            listener.gameStarted(game);
        }
    }

    private void notifyResultChanged(Game game)
    {
        for (ISportServiceListener listener : listenersList)
        {
            listener.updateResult(game, game.getResult().getScoresString());
        }
    }
    
    private void notifyGameEnded(Game game)
    {
        for (ISportServiceListener listener : listenersList)
        {
            listener.gameFinalised(game, game.getWinnerTeam(), game.getResult());
        }
    }

    private class TennisSimulation extends Thread
    {
        private String gameId;
        private final SimulationSpeed speed;
        private final long sleepPeriod;
        
        private Random random = new Random();

        /**
         * Creates a new TennisSimulator.TennisSimulation
         * @param speed 
         */
        public TennisSimulation(String gameId, SimulationSpeed speed)
        {
            super("Tennis Game: " + gameId);
            this.gameId = gameId;
            this.speed = speed;
            switch(speed)
            {
            case NORMAL:
                this.sleepPeriod = 1000L;
                break;
            case FAST:
                this.sleepPeriod = 300L;
                break;
            case SUPERFAST:
                this.sleepPeriod = 10;
                break;
                default:
                    this.sleepPeriod = 1000L;
            }
        }
        
        /** {@inheritDoc} */
        @Override
        public void run()
        {
            super.run();
            Game game = TennisSimulator.this.scheduledGames.get(this.gameId);
            while (!game.isGameFinished())
            {
                if (this.sleepPeriod > 0)
                {
                    try
                    {
                        sleep(this.sleepPeriod);
                    }
                    catch (InterruptedException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                Team scorerTean = getRandomScorer(game);
                game.scorePoint(scorerTean);
                notifyResultChanged(game);
            }
            notifyGameEnded(game);
            
            
        }

        /**
         * 
         */
        private Team getRandomScorer(Game game)
        {
            int teamNumber = (this.random.nextInt(1000) % 2) + 1;
            return game.getTeam(teamNumber);
        }
    }
    
}

//-----------------------------------------------------------------------------