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
// File Name : EudTeam.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 22 Nov 2020
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.serviceadapter;

import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.Team.TeamGender;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class EudTeam
{

    private final Team team;

    /**
     * @param o
     * @return
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o)
    {
        return this.team.equals(o);
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#getTeamName()
     */
    public String getTeamName()
    {
        return this.team.getTeamName();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#getCountry()
     */
    public String getCountry()
    {
        return this.team.getCountry();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#getVictories()
     */
    public int getVictories()
    {
        return this.team.getVictories();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#addVictory()
     */
    public int addVictory()
    {
        return this.team.addVictory();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#getGender()
     */
    public String getGender()
    {
        return this.team.getGender().toString();
    }

    /**
     * @return
     * @see com.gmv.sportsimulator.api.Team#toString()
     */
    public String toString()
    {
        return this.team.toString();
    }

    /**
     * @return
     * @see java.lang.Object#hashCode()
     */
    public int hashCode()
    {
        return this.team.hashCode();
    }

    /**
     * Creates a new EudTeam
     */
    public EudTeam(Team team)
    {
        this.team = team;
    }
}

//-----------------------------------------------------------------------------