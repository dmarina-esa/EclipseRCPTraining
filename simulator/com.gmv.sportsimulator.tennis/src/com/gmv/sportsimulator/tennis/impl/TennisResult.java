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
// File Name : TennisResult.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.tennis.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.tennis.impl.TennisMatch.MatchType;

/**
 * @author David Marina
 *
 */
public class TennisResult extends Result
{
    
    private static final String RESULT_SEPARATOR = " ";

    private final MatchType matchType;
    
    private List<TennisSet> tennisSets;

    private AtomicInteger currentSet;

    /**
     * Creates a new TennisResult
     */
    public TennisResult(MatchType matchType)
    {
        this.matchType = matchType;
        TennisSet tennisSet = new TennisSet();
        this.tennisSets.add(tennisSet);
        this.currentSet.set(1);
    }

    /** {@inheritDoc} */
    @Override
    public void scorePoint(int team)
    {
        TennisSet set = this.tennisSets.get(this.currentSet.get());
        set.scorePoint(team);
        if (set.isFinal())
        {
            scorePoint(team);
            if (checkFinished())
            {
                finalise(team);
                return;
            }
            else
            {
                this.tennisSets.add(new TennisSet());
                this.currentSet.incrementAndGet();
            }
        }
    }
    
    public String getDetailedResult()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tennisSets.size(); i++)
        {
            if (i > 0)
            {
                sb.append(RESULT_SEPARATOR);
            }
            sb.append(this.tennisSets.get(i).toString());
        }
        return sb.toString();
    }

    private boolean checkFinished()
    {
        int score1 = getTeamScore(1);
        int score2 = getTeamScore(2);

        if (this.matchType == MatchType.NORMAL)
        {
            if (score1 == 2 || score2 == 2)
            {
                return true;
            }
        }
        else //GRAND_SLAM
        {
            if (score1 == 3 || score2 == 3)
            {
                return true;
            }
        }
        return false;
    }
}

//-----------------------------------------------------------------------------