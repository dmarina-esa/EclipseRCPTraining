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
// File Name : TennisSetGame.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.tennis.impl;

import com.gmv.sportsimulator.api.Result;

/**
 * @author David Marina
 *
 */
public class TennisSetGame extends Result
{
    private int playerAdvantage = -1;
    
    /** {@inheritDoc} */
    @Override
    public void scorePoint(int team)
    {
        int actualScore = getTeamScore(team);
        switch(actualScore)
        {
        case 0:
        case 15:
            scoreSpecialPoint(team, 15);
            break;
        case 30:
            scoreSpecialPoint(team, 10);
            break;
        case 40:
            handleFinalPoint(team);
            break;
            default:
                throw new RuntimeException("Unexpected state");
                // This should never happen!
        }
        
    }
    
    /**
     * @param team
     */
    private void handleFinalPoint(int scorerPlayer)
    {
        int otherPlayer = (scorerPlayer % 2) + 1;
        int otherPlayerScore = getTeamScore(otherPlayer);
        if (otherPlayerScore < 40)
        {
            scoreSpecialPoint(scorerPlayer, 20); //Increase it up to 60, in case user wants to print it!
            finalise(scorerPlayer);
        }
        else
        {
            if (this.playerAdvantage == otherPlayer)
            {
                this.playerAdvantage = -1;
            }
            else if (this.playerAdvantage == -1)
            {
                this.playerAdvantage = scorerPlayer;
            }
            else if (this.playerAdvantage == scorerPlayer)
            {
                scoreSpecialPoint(scorerPlayer, 20); //Increase it up to 60, in case user wants to print it!
                finalise(scorerPlayer);
            }
            else
            {
                throw new RuntimeException("Unexpected state");
                // This should never happen!
            }
        }
        
    }

}

//-----------------------------------------------------------------------------