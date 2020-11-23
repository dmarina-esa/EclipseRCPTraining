//-----------------------------------------------------------------------------
//
// (C) 2020 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : course.eud.simulator.serviceadapter
//
// File Name : EudGame.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 22 Nov 2020
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.serviceadapter;

import java.util.ArrayList;
import java.util.List;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.api.Team;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class EudGame
{
    private final Game game;
    
    private List<EudTeam> eudTeams = new ArrayList<>();

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getGameType()
     */
    public String getGameType()
    {
        return this.game.getGameType();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getId()
     */
    public String getId()
    {
        return this.game.getId();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getName()
     */
    public String getName()
    {
        return this.game.getName();
    }

    /**
     * @param name
     * @see com.gmv.sportsimulator.api.Game#setName(java.lang.String)
     */
    public void setName(String name)
    {
        this.game.setName(name);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getTeamA()
     */
    public EudTeam getTeamA()
    {
        return this.eudTeams.get(0);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getTeamB()
     */
    public EudTeam getTeamB()
    {
        return this.eudTeams.get(1);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getLocation()
     */
    public String getLocation()
    {
        return this.game.getLocation().getLocationName();
    }

    /**
     * @param team
     * @return
     * @see com.gmv.sportsimulator.api.Game#getTeamPosition(com.gmv.sportsimulator.api.Team)
     */
    public int getTeamPosition(Team team)
    {
        return this.game.getTeamPosition(team);
    }

    /**
     * @param teamPosition
     * @return
     * @see com.gmv.sportsimulator.api.Game#getTeam(int)
     */
    public EudTeam getTeam(int teamPosition)
    {
        return this.eudTeams.get(teamPosition);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getTeams()
     */
    public List<EudTeam> getTeams()
    {
        List<EudTeam> eudTeams = new ArrayList<>();
        for (Team t : this.game.getTeams())
        {
            eudTeams.add(new EudTeam(t));
        }
        return eudTeams;
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#getResult()
     */
    public String getResult()
    {
        return this.game.getResult().getScoresString();
    }

    /**
     * @param newResult
     * @see com.gmv.sportsimulator.api.Game#updateResult(com.gmv.sportsimulator.api.Result)
     */
    public void updateResult(Result newResult)
    {
        this.game.updateResult(newResult);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#isGameFinished()
     */
    public boolean isGameFinished()
    {
        return this.game.isGameFinished();
    }

    /**
     * 
     * @see com.gmv.sportsimulator.api.Game#resetGame()
     */
    public void resetGame()
    {
        this.game.resetGame();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#toString()
     */
    public String toString()
    {
        return this.game.toString();
    }
//
//    /**
//     * @param team
//     * @return
//     * @see com.gmv.sportsimulator.api.Game#isTeamRegistered(com.gmv.sportsimulator.api.Team)
//     */
//    public boolean isTeamRegistered(Team team)
//    {
//        return this.game.isTeamRegistered(team);
//    }

//    /**
//     * @param team
//     * @param points
//     * @see com.gmv.sportsimulator.api.Game#scoreSpecialPoint(com.gmv.sportsimulator.api.Team, int)
//     */
//    public void scoreSpecialPoint(Team team, int points)
//    {
//        this.game.scoreSpecialPoint(team, points);
//    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#isFinalised()
     */
    public boolean isFinalised()
    {
        return this.game.isFinalised();
    }

    /**
     * 
     * @see com.gmv.sportsimulator.api.Game#finalise()
     */
    public void finalise()
    {
        this.game.finalise();
    }

//    /**
//     * @return
//     * @see com.gmv.sportsimulator.api.Game#getWinnerTeam()
//     */
//    public Team getWinnerTeam()
//    {
//        return this.game.getWinnerTeam();
//    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Game#hashCode()
     */
    public int hashCode()
    {
        return this.game.hashCode();
    }

    /**
     * @param obj
     * @return
     * @see com.gmv.sportsimulator.api.Game#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        return this.game.equals(obj);
    }

    /**
     * Creates a new EudGame
     */
    public EudGame(Game game)
    {
        this.game = game;
        for (Team t : this.game.getTeams())
        {
            this.eudTeams.add(new EudTeam(t));
        }
        // TODO Auto-generated constructor stub
    }

}

//-----------------------------------------------------------------------------