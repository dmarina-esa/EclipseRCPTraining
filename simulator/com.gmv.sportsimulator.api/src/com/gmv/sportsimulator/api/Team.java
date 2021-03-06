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
// File Name : Team.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

import java.util.concurrent.atomic.AtomicInteger;

import com.gmv.sportsimulator.api.Team.TeamGender;

/**
 * Class representing a team. A team is typically composed by a name and a
 * country
 * 
 * @author David Marina
 *
 */
public class Team
{

    /**
     * Defines the team gender. One of MALE, FEMALE or MIXED
     * 
     * @author David Marina
     *
     */
    public enum TeamGender
    {
        MALE, FEMALE, MIXED, UNKNOWN
    }


    private static final String UNKNOWN_COUNTRY = "<Unknown>";

    private final String teamName;

    private final String country;

    private AtomicInteger victories;

    private final TeamGender gender;


    /**
     * Creates a new Team
     */
    public Team(String teamName)
    {
        this(teamName, UNKNOWN_COUNTRY, TeamGender.UNKNOWN);
    }

    /**
     * Creates a new Team
     * @param teamName
     * @param gender
     */
    public Team(String teamName, TeamGender gender)
    {
        this(teamName, UNKNOWN_COUNTRY, gender);
    }

    /**
     * Creates a new Team
     * @param teamName
     * @param country
     */
    public Team(String teamName, String country)
    {
        this(teamName, country, TeamGender.UNKNOWN);
    }

    /**
     * Creates a new Team
     * @param teamName
     * @param country
     * @param gender
     */
    public Team(String teamName, String country, TeamGender gender)
    {
        this.teamName = teamName;
        this.country = country;
        this.gender = gender;
        this.victories = new AtomicInteger();
    }

    /**
     * @return a String containing the teamName of this Team
     */
    public String getTeamName()
    {
        return this.teamName;
    }

    /**
     * @return a String containing the country of this Team
     */
    public String getCountry()
    {
        return this.country;
    }

    /**
     * @return a int containing the victories of this Team
     */
    public int getVictories()
    {
        return this.victories.get();
    }

    /**
     * Adds a victory and returns the total number of victories
     * 
     * @return the total number of victories
     */
    public int addVictory()
    {
        return this.victories.addAndGet(1);
    }

    /**
     * Returns the {@link TeamGender} of this team. One of : MALE, FEMALE or
     * MIXED
     * 
     * @return
     */
    public TeamGender getGender()
    {
        return this.gender;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getTeamName().toString());
        sb.append(" (" + getCountry() + ")");
        return sb.toString();
    }
}

// -----------------------------------------------------------------------------
