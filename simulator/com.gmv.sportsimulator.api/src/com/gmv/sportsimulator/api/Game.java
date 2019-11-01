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
     * Creates a new Game
     */
    public Game(Team teamA, Team teamB, Location location)
    {
        this(teamA, teamB, location, new Result());
    }
    
    /**
     * Creates a new Game
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
    
    public void updateResult(Result newResult)
    {
        this.result = newResult;
    }
    
    public boolean isGameFinished()
    {
        return getResult().isFinal();
    }
    
    public Team getWinnerTeam()
    {
        return getTeam(getResult().getWinner());
    }
    
    public abstract void scorePoint(Team team);
    
    public abstract void scoreSpecialPoint(Team team, int points);
    
}

//-----------------------------------------------------------------------------