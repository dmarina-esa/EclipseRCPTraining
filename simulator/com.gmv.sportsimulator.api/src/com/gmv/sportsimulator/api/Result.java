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
 * Class representing the result of a game. This class may be sub-classed to
 * implement other score types. The basic implementation provides a single
 * one-point score system
 * 
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

    /**
     * Creates a new Result with the given scores and finalisation status
     * 
     * @param team1Score
     * @param team2Score
     * @param finalised
     */
    public Result(int team1Score, int team2Score, boolean finalised)
    {
        this.teamScores[0] = team1Score;
        this.teamScores[1] = team2Score;
        this.finalised = finalised;
    }

    /**
     * Creates a new {@link Result} from the reference to another {@link Result}
     * 
     * @param otherResult
     * @return
     */
    public static Result fromOtherResult(Result otherResult)
    {
        return fromString(otherResult.getScoresString(), otherResult.finalised);
    }

    /**
     * Creates a new {@link Result} from a String result in format
     * scoreTeam1-scoreTeam2 (e.g. 1-0) and a boolean indicating if the result
     * is final or not
     * 
     * @param result
     * @param finalised
     * @return
     */
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

    /**
     * Returns the points scored by a team
     * 
     * @param team
     *            the team (1 or 2)
     * @return
     */
    public int getTeamScore(int team)
    {
        int arrayPosition = checkTeam(team);
        return this.teamScores[arrayPosition];
    }

    /**
     * Returns a String with the current result values separated by "-"
     * 
     * @return
     */
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
            result = result + " <ONGOING>";
        }
        return result;
    }

    /**
     * Updates (replaces) the score for one team with the new value
     * 
     * @param team
     * @param score
     */
    public void updateScore(int team, int score)
    {
        int arrayPosition = checkTeam(team);
        this.teamScores[arrayPosition] = score;
    }

    /**
     * Scores one point to the given team. Subclasses implement another pointing
     * system can use this method to provide the numeric equivalent to a normal
     * score for a team
     * 
     * @param team
     */
    public void scorePoint(int team)
    {
        int arrayPosition = checkTeam(team);
        int newPoints = this.teamScores[arrayPosition] + 1;
        this.teamScores[arrayPosition] = newPoints;
    }

    /**
     * Scores a special point to the given team. 
     * @param team
     * @param points
     */
    public void scoreSpecialPoint(int team, int points)
    {
        int arrayPosition = checkTeam(team);
        int newPoints = this.teamScores[arrayPosition] + points;
        this.teamScores[arrayPosition] = newPoints;
    }

    /**
     * Sets the result as final and stores the reference to the winner player
     * @param winner
     */
    public void finalise(int winner)
    {
        this.winner = winner;
        this.finalised = true;
    }

    /**
     * Returns <code>true</code> if the result is final; <code>false</code> if the game is still being played
     * @return
     */
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
     * Resets the {@link Result} to its initial status
     */
    public void reset()
    {
        this.teamScores = new int[2];
        this.winner = -1;
        this.finalised = false;
    }

    /**
     * @param team
     */
    private static int checkTeam(int team)
    {
        if (team < 1 || team > 2)
        {
            throw new IllegalArgumentException("Team: "
                                               + team
                                               + " is not a valid team number. Only team 1 or 2 are allowed as only two teams compete in one game!!.");
        }
        return team - 1;
    }
}

// -----------------------------------------------------------------------------
