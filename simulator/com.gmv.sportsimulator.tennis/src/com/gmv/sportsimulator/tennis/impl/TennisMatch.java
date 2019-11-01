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
// File Name : TennisMatch.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.tennis.impl;

import java.util.HashMap;
import java.util.Map;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Team;

/**
 * @author David Marina
 *
 */
public class TennisMatch extends Game
{

    public enum MatchType
    {
        NORMAL,
        GRAND_SLAM
    }
    
    
    public TennisMatch(Team player1, Team player2, Location location)
    {
        this(player1, player2, location, MatchType.NORMAL);
    }
    
    /**
     * Creates a new TennisMatch
     * @param player1
     * @param player2
     * @param location
     * @param matchType
     */
    public TennisMatch(Team player1, Team player2, Location location, MatchType matchType)
    {
        super(player1, player2, location, new TennisResult(matchType));
    }
    
    /** {@inheritDoc} */
    @Override
    public void scorePoint(Team team)
    {
        getResult().scorePoint(getTeamPosition(team));
        
    }

    /** {@inheritDoc} */
    @Override
    public void scoreSpecialPoint(Team team, int points)
    {
        getResult().scoreSpecialPoint(getTeamPosition(team), points);
        
    }
    
    public boolean isFinalised()
    {
        return getResult().isFinal();
    }
    
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

//-----------------------------------------------------------------------------