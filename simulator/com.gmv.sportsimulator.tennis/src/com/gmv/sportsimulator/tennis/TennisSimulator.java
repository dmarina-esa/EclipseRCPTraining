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

import java.util.Map;
import java.util.Random;

import org.osgi.service.component.annotations.Component;

import com.gmv.sportsimulator.api.BaseSportService;
import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.IGameSimulator;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.SimulationSpeed;
import com.gmv.sportsimulator.tennis.impl.TennisMatch;
import com.gmv.sportsimulator.tennis.impl.TennisMatch.MatchType;

/**
 * @author David Marina
 *
 */
@Component(service = ISportService.class, name = "Tennis")
public class TennisSimulator extends BaseSportService
{

    /** {@inheritDoc} */
    @Override
    protected Game createGameInstance(String gameName, Team teamA, Team teamB, Location location, Map<String, String> metadata)
    {
        MatchType matchType = MatchType.NORMAL;
        String matchTypeStr = metadata == null ? null : metadata.get("Match_Type");
        if (matchTypeStr != null)
        {
            matchType = MatchType.valueOf(matchTypeStr);
        }
        Game newGame = new TennisMatch(gameName, teamA, teamB, location, matchType);
        return newGame;
    }

    /** {@inheritDoc} */
    @Override
    protected IGameSimulator createAndStartGameSimulation(Game game, SimulationSpeed speed)
    {
        TennisSimulation gameSimulation = new TennisSimulation(game.getId(), speed);
        gameSimulation.start();
        return gameSimulation;
    }


    private class TennisSimulation extends Thread implements IGameSimulator
    {
        private String gameId;

        private final SimulationSpeed speed;

        private final long sleepPeriod;

        private Random random = new Random();

        private boolean resumeSimulation = false;


        /**
         * Creates a new TennisSimulator.TennisSimulation
         * 
         * @param speed
         */
        public TennisSimulation(String gameId, SimulationSpeed speed)
        {
            super("Tennis Game: " + gameId);
            this.gameId = gameId;
            this.speed = speed;
            switch (speed)
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
            case TURBO:
                this.sleepPeriod = 0;
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
            this.resumeSimulation = true;
            Game game = getScheduledGames().get(this.gameId);
            while (!game.isGameFinished() && this.resumeSimulation)
            {
                Team scorerTeam = getRandomScorer(game);
                game.scorePoint(scorerTeam);
                notifyResultChanged(game);
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
            }
            this.resumeSimulation = false;
            notifyGameEnded(game);
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
