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
// Sub-System : com.gmv.sportsimulator.soccer_handball
//
// File Name : Soccer_Handball_Service.java
//
// Author : David Marina
//
// Creation Date : 4 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.soccer;

import java.util.Map;
import java.util.Random;

import com.gmv.sportsimulator.api.BaseSportService;
import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.ISimulationThread;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.SimulationSpeed;

/**
 * @author David Marina
 *
 */
public class SoccerService extends BaseSportService implements ISportService
{

    private static final String[] SPORTS = new String[] { "Soccer", "Handball" };


    /** {@inheritDoc} */
    @Override
    public String[] getSportTypes()
    {
        return SPORTS;
    }

    /** {@inheritDoc} */
    @Override
    public void placeBet(String bidder, Game game, Result result, int amountToBid)
    {
        throw new RuntimeException("Not implemented yet.");

    }

    /** {@inheritDoc} */
    @Override
    protected Game createGameInstance(String name,
                                      Team teamA,
                                      Team teamB,
                                      Location location,
                                      Map<String, String> metadata)
    {
        if (metadata != null && metadata.containsKey("Sport"))
        {
            String gameType = metadata.get("Sport");
            return new Game(gameType, name, teamA, teamB, location);
        }
        else
        {
            return new Game("Soccer", name, teamA, teamB, location);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected ISimulationThread createAndStartGameSimulation(Game game, SimulationSpeed speed)
    {
        SoccerSimulation gameSimulation = new SoccerSimulation(game.getGameType(), game.getId(), speed);
        gameSimulation.start();
        return gameSimulation;
    }


    private class SoccerSimulation extends Thread implements ISimulationThread
    {
        private String gameId;

        private final SimulationSpeed speed;

        private final int speedFactor;

        private Random random = new Random();

        private boolean resumeSimulation = false;
        
        private long sleepTime;

        private long startTime;

        private long endTime;


        /**
         * Creates a new TennisSimulator.TennisSimulation
         * 
         * @param speed
         */
        public SoccerSimulation(String gameType, String gameId, SimulationSpeed speed)
        {
            super(gameType + " Game: " + gameId);
            this.gameId = gameId;
            this.speed = speed;
            this.sleepTime = 1000;
            switch (speed)
            {
            case NORMAL:
                this.speedFactor = 1;
                this.sleepTime = 10*60000;
                break;
            case FAST:
                this.speedFactor = 100;
                this.sleepTime = 10*6000;
                break;
            case SUPERFAST:
                this.speedFactor = 10*600;
                break;
            case TURBO:
                this.speedFactor = 10000;
                break;
            default:
                this.speedFactor = 10*6000;
            }
        }

        /** {@inheritDoc} */
        @Override
        public void run()
        {
            super.run();
            this.startTime = System.currentTimeMillis();
            this.endTime = this.startTime + (90 * 60000 / this.speedFactor);
            this.resumeSimulation = true;
            Game game = getScheduledGames().get(this.gameId);
            game.resetGame();
            while (!timeOver() && this.resumeSimulation)
            {
                try
                {
                    Thread.sleep(this.sleepTime);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                if (didSomeoneScore())
                {
                    Team scorerTeam = getRandomScorer(game);
                    game.scorePoint(scorerTeam);
                    notifyResultChanged(game);
                }
            }
            game.finalise();
            this.resumeSimulation = false;
            notifyGameEnded(game);
        }

        /**
         * @return
         */
        private boolean didSomeoneScore()
        {
            // The chances for a team to score are 1/2 per loop iteration
            return this.random.nextInt(2) % 2 == 1;
        }

        /**
         * @return
         */
        private boolean timeOver()
        {
            return System.currentTimeMillis() > this.endTime;
        }

        @Override
        public void cancelSimulation()
        {
            this.resumeSimulation = false;
        }

        /** {@inheritDoc} */
        @Override
        public final boolean isRunnableAlive()
        {
            return isAlive();
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

// -----------------------------------------------------------------------------
