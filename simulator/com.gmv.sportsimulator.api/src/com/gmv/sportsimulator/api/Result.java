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
// File Name : Result.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

/**
 * @author David Marina
 *
 */
public class Result
{

    private boolean finalised;

    private int[] teamScores = new int[2];

    private int winner;


    /**
     * Creates a new Result with the initial value "0-0" and not finalised.
     */
    public Result()
    {
        this(0, 0, false);
    }

    public Result(int team1Score, int team2Score, boolean finalised)
    {
        this.teamScores[0] = team1Score;
        this.teamScores[1] = team2Score;
        this.finalised = finalised;
    }
    
    public static Result fromOtherResult(Result otherResult)
    {
        return fromString(otherResult.getScoresString(), otherResult.finalised);
    }

    public static Result fromString(String result, boolean finalised)
    {
        if (result.matches("\\d+-\\d+"))
        {
            String[] scores = result.split(result);
            return new Result(Integer.parseInt(scores[0]), Integer.parseInt(scores[1]), finalised);
        }
        else
        {
            throw new IllegalArgumentException("Argument: "
                                               + result
                                               + " is not a valid argument for Result. It must be provided in the form: 1-0");
        }
    
    }

    public int getTeamScore(int team)
    {
        checkTeam(team);
        return this.teamScores[team];
    }

    public String getScoresString()
    {
        return String.valueOf(this.teamScores[0]) + "-" + String.valueOf(this.teamScores[1]);
    }
    
    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        String result = getScoresString();
        if (this.finalised)
        {
            result = result + " <FINAL>";
        }
        else
        {
            result = result + " <ONGOING";
        }
        return result;
    }

    public void updateScore(int team, int score)
    {
        checkTeam(team);
        this.teamScores[team] = score;
    }
    
    public void scorePoint(int team)
    {
        int newPoints = this.teamScores[team] + 1;
        this.teamScores[team] = newPoints;
    }
    
    public void scoreSpecialPoint(int team, int points)
    {
        int newPoints = this.teamScores[team] + points;
        this.teamScores[team] = newPoints;
    }

    public void finalise(int winner)
    {
        this.finalised = true;
    }

    public boolean isFinal()
    {
        return this.finalised;
    }

    /**
     * returns the winner team number (1 or 2) if the game has ended; returns -1
     * otherwise.
     * 
     * @return the winner team number
     */
    public int getWinner()
    {
        if (this.finalised)
        {
            return this.winner;
        }
        else
        {
            return -1;
        }
    }

    /**
     * @param team
     */
    private static void checkTeam(int team)
    {
        if (team < 1 || team > 2)
        {
            throw new IllegalArgumentException("Team: "
                                               + team
                                               + " is not a valid team number. Only team 1 or 2 are allowed as only two teams compete in one game!!.");
        }

    }
}

// -----------------------------------------------------------------------------
