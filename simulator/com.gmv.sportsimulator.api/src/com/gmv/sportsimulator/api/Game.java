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
// Sub-System : com.gmv.sportsimulator.api
//
// File Name : Game.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Class that represents a generic game for any sport for two team players at a
 * certain location. The results are based on one point scores like in football.
 * Exceptionally, a multiple point can be added to the result. This class may be
 * extended to create more complex games. E.g. with more complex score results
 * like tennis
 * 
 * @author David Marina
 *
 */
public class Game
{

    private final String gameType;

    private final UUID id;

    private final List<Team> teams = new ArrayList<Team>();

    private final Location location;

    private String name;

    private Result result;


    /**
     * Creates a new Game with a simple result object that scores point to point
     * 
     * @param teamA
     *            the A team
     * @param teamB
     *            the B team
     * @param location
     *            the game location
     */
    public Game(String gameType, Team teamA, Team teamB, Location location)
    {
        this(gameType, teamA, teamB, location, new Result());
    }

    /**
     * Creates a new Game with a simple result object that scores point to point
     * 
     * @param gameName
     *            the human readable name for this game. If not provided, the
     *            game ID will be used instead
     * @param teamA
     * @param teamB
     * @param location
     */
    public Game(String gameType, String gameName, Team teamA, Team teamB, Location location)
    {
        this(gameType, gameName, teamA, teamB, location, new Result());
    }

    /**
     * Creates a new Game
     * 
     * @param teamA
     *            the team A
     * @param teamB
     *            the team B
     * @param location
     *            the game location
     * @param result
     *            the new result board
     */
    public Game(String gameType, Team teamA, Team teamB, Location location, Result result)
    {
        this(gameType, "", teamA, teamB, location, result);
    }

    /**
     * Creates a new Game
     * 
     * @param gameName
     *            the human readabale name for this game. If not provided, the
     *            game ID will be used instead
     * @param teamA
     * @param teamB
     * @param location
     * @param result
     */
    public Game(String gameType, String gameName, Team teamA, Team teamB, Location location, Result result)
    {
        this.id = UUID.randomUUID();
        this.gameType = gameType;
        this.name = (gameName != null && gameName.trim().length() > 0) ? gameName : this.id.toString();
        this.teams.add(teamA);
        this.teams.add(teamB);
        this.location = location;
        this.result = result;
    }

    /**
     * @return a String containing the gameType of this Game
     */
    public String getGameType()
    {
        return this.gameType;
    }

    /**
     * Returns a unique id representing this game
     * 
     * @return a UUID containing the id of this Game
     */
    public String getId()
    {
        return this.id.toString();
    }

    /**
     * Returns the human readable name for this game. If it was not provided,
     * the game id will be used as game name
     * 
     * @return
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Replaces the game name with the new one
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return a Team containing the teamA of this Game
     */
    public Team getTeamA()
    {
        return this.teams.get(0);
    }

    /**
     * @return a Team containing the teamB of this Game
     */
    public Team getTeamB()
    {
        return this.teams.get(1);
    }

    /**
     * @return a Location containing the location of this Game
     */
    public Location getLocation()
    {
        return this.location;
    }

    /**
     * For a certain {@link Team}, it retrieves the numeric position in this
     * game: Team 1 or Team 2.
     * 
     * @param team
     *            the team to search
     * @return the team position for the given {@link Team} in this {@link Game}
     */
    public int getTeamPosition(Team team)
    {
        if (this.teams.contains(team))
        {
            // Normalise team position to 1 or 2
            return this.teams.indexOf(team) + 1;
        }
        else
        {
            throw new IllegalArgumentException("Argument Team not valid: " + team);
        }
    }

    /**
     * For a certain team position: Team 1 or Team 2, it retrieves the reference
     * to the {@link Team} associated to it.
     * 
     * @param teamPosition
     *            the team position
     * @return the {@link Team} associated to the team position
     */
    public Team getTeam(int teamPosition)
    {
        if (teamPosition <= this.teams.size() && teamPosition > 0)
        {
            return this.teams.get(teamPosition - 1);
        }
        else
        {
            throw new IllegalArgumentException("Argument team position not valid: " + teamPosition
                                               + ". Valid positions are 1 and 2");
        }
    }

    /**
     * Returns the teams that are part of this game
     * 
     * @return
     */
    public List<Team> getTeams()
    {
        return this.teams;
    }

    /**
     * @return a Result containing the result of this Game
     */
    public Result getResult()
    {
        return this.result;
    }

    /**
     * Updates the result of this {@link Game}
     * 
     * @param newResult
     */
    public void updateResult(Result newResult)
    {
        this.result = newResult;
    }

    /**
     * Returns <code>true</code> if the game is finished; <code>false</code>
     * otherwise
     * 
     * @return <code>true</code> if the game is finished; <code>false</code>
     *         otherwise
     */
    public boolean isGameFinished()
    {
        return getResult().isFinal();
    }

    /**
     * Resets the game to its initial status
     */
    public void resetGame()
    {
        this.result.reset();
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getLocation().getLocationName());
        sb.append(": ");
        sb.append(this.teams.get(0).toString());
        sb.append(" vs ");
        sb.append(this.teams.get(1).toString());
        return sb.toString();
    }

    /**
     * Returns <code>true</code> if the given team is part of this game;
     * <code>false</code> otherwise
     * 
     * @param team
     * @return <code>true</code> if the given team is part of this game;
     *         <code>false</code> otherwise
     */
    public boolean isTeamRegistered(Team team)
    {
        return this.teams.contains(team);
    }

    /**
     * Scores a point in the {@link Result} of this game for the given team.
     * This method may be overridden to perform some special action on scoring
     * 
     * @param team
     *            the team whose point has to be scored
     */
    public void scorePoint(Team team)
    {
        getResult().scorePoint(getTeamPosition(team));
        if (isFinalised())
        {
            team.addVictory();
        }

    }

    /**
     * Scores a special point in the {@link Result} of this game for the given
     * team. This method may be overridden to perform some special action on
     * scoring<br>
     * <br>
     * E.g. 3 points in basketball, 15 points in tennis...
     * 
     * @param team
     *            the ream whose special points have to be scored
     * @param points
     *            the value of the special points to score
     */
    public void scoreSpecialPoint(Team team, int points)
    {
        getResult().scoreSpecialPoint(getTeamPosition(team), points);
        if (isFinalised())
        {
            team.addVictory();
        }
    }

    /**
     * Returns <code>true</code> if the game has finalised; <code>false</code>
     * otherwise
     * 
     * @return
     */
    public boolean isFinalised()
    {
        return getResult().isFinal();
    }
    
    public void finalise()
    {
        int teamScore1 = getResult().getTeamScore(1);
        int teamScore2 = getResult().getTeamScore(2);
        if (teamScore1 > teamScore2)
        {
            getResult().finalise(teamScore1);
            getTeam(teamScore1).addVictory();
        }
        else if (teamScore2 > teamScore1)
        {
            getResult().finalise(teamScore2);
            getTeam(teamScore2).addVictory();
        }
        else
        {
            getResult().finalise(-1);
        }
    }

    /**
     * Returns the winner team or null if the game has not finalised
     * 
     * @return
     */
    public Team getWinnerTeam()
    {
        if (isFinalised())
        {
            return getTeam(getResult().getWinner());
        }
        else
        {
            return null;
        }
    }

}

// -----------------------------------------------------------------------------
