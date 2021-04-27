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
// File Name : TennisSet.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.tennis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.gmv.sportsimulator.api.Result;

/**
 * @author David Marina
 *
 */
public class TennisSet extends Result
{

    private List<TennisSetGame> setGames = new ArrayList<TennisSetGame>();

    private AtomicInteger currentSetGame;

    private boolean tiebreak = false;

    private int[] tieBreakPoints = new int[2];


    /**
     * Creates a new TennisSet
     */
    public TennisSet()
    {
        TennisSetGame tennisSetGame = new TennisSetGame();
        this.setGames.add(tennisSetGame);
        this.currentSetGame = new AtomicInteger(1);
    }

    /** {@inheritDoc} */
    @Override
    public void scorePoint(int team)
    {
        int arrayPosition = this.currentSetGame.get() - 1;
        TennisSetGame setGame = this.setGames.get(arrayPosition);
        setGame.scorePoint(team);
        if (setGame.isFinal())
        {
            super.scorePoint(team);
            if (checkFinished())
            {
                if (getTeamScore(team) > 7)
                {
                    int otherTeam = (team % 2) + 1;
                    this.tiebreak = true;
                    this.tieBreakPoints[team - 1] = getTeamScore(team);
                    this.tieBreakPoints[otherTeam - 1] = getTeamScore(otherTeam);
                    updateScore(team, 7);
                    updateScore(otherTeam, 6);
                }

                finalise(team);
                return;
            }
            else
            {
                this.setGames.add(new TennisSetGame());
                this.currentSetGame.incrementAndGet();
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void reset()
    {
        super.reset();
        this.setGames.clear();
        this.setGames.add(new TennisSetGame());
        this.currentSetGame.set(1);
    }

    /** {@inheritDoc} */
    @Override
    public String getScoresString()
    {
        String scoresString = super.getScoresString();
        if (this.tiebreak)
        {
            scoresString = scoresString + "(" + this.tieBreakPoints[0] + "-" + this.tieBreakPoints[1] + ")";
        }
        return scoresString;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return getScoresString();
    }

    private boolean checkFinished()
    {
        int score1 = getTeamScore(1);
        int score2 = getTeamScore(2);

        if (Math.abs(score1 - score2) > 1)
        {
            if (score1 > 5 || score2 > 5)
            {
                return true;
            }
        }
        return false;
    }

}

// -----------------------------------------------------------------------------
