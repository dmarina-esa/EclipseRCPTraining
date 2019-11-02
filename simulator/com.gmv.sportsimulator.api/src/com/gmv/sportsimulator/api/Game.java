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

import java.util.UUID;

/**
 * Class that represents a generic game for any sport for two team players at a
 * certain location
 * 
 * @author David Marina
 *
 */
public abstract class Game
{

    private final UUID id;

    private final Team teamA;

    private final Team teamB;

    private final Location location;

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
    public Game(Team teamA, Team teamB, Location location)
    {
        this(teamA, teamB, location, new Result());
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
    public Game(Team teamA, Team teamB, Location location, Result result)
    {
        this.id = UUID.randomUUID();
        this.teamA = teamA;
        this.teamB = teamB;
        this.location = location;
        this.result = result;
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
     * @return a Team containing the teamA of this Game
     */
    public Team getTeamA()
    {
        return this.teamA;
    }

    /**
     * @return a Team containing the teamB of this Game
     */
    public Team getTeamB()
    {
        return this.teamB;
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
        if (this.teamA.equals(team))
        {
            return 1;
        }
        else if (this.teamB.equals(team))
        {
            return 2;
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
        if (teamPosition == 1)
        {
            return this.teamA;
        }
        else if (teamPosition == 2)
        {
            return this.teamB;
        }
        else
        {
            throw new IllegalArgumentException("Argument team position not valid: " + teamPosition);
        }
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
     * Retrieves the reference to the winner {@link Team}
     * 
     * @return the winner team
     */
    public Team getWinnerTeam()
    {
        return getTeam(getResult().getWinner());
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
        sb.append(this.teamA.toString());
        sb.append(" vs ");
        sb.append(this.teamB.toString());
        return sb.toString();
    }

    /**
     * Scores a point in the {@link Result} of this game for the given team
     * 
     * @param team
     *            the team whose point has to be scored
     */
    public abstract void scorePoint(Team team);

    /**
     * Scores a special point in the {@link Result} of this game for the given
     * team. <br>
     * <br>
     * E.g. 3 points in basketball, 15 points in tennis...
     * 
     * @param team
     *            the ream whose special points have to be scored
     * @param points
     *            the value of the special points to score
     */
    public abstract void scoreSpecialPoint(Team team, int points);

}

// -----------------------------------------------------------------------------
