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
// Sub-System : com.gmv.sportsimulator.gamesloader
//
// File Name : DatabaseCreator.java
//
// Author : David Marina
//
// Creation Date : 2 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.gamesloader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;

/**
 * @author David Marina
 *
 */
@Component
public class DatabaseCreator
{

    private static final String SPORT_NAME = "Tennis";
    
    private ISportService sportService;

    @Reference(name=SPORT_NAME)
    public void populateSportService(ISportService sportService)
    {
        this.sportService = sportService;
        readDatabase();
        
    }

    /**
     * 
     */
    private void readDatabase()
    {
        GameFileReader reader = Activator.fileReader;
        
        readCompetitions(reader);
    }

    private void readCompetitions(GameFileReader reader)
    {
        String sport = "";
        String locationStr = "";
        Map<String, String> metadata = new HashMap<String, String>();
        List<Team> teams = new ArrayList<Team>();
        while (reader.hasMoreLines())
        {
            String line = reader.readTokenLine();
            if (line.startsWith("@"))
            {
                if (line.contains("@SPORT"))
                {
                    sport = reader.readTokenLine();
                }
                else if (line.contains("@LOCATION"))
                {
                    locationStr = reader.readTokenLine();
                }
                else if (line.contains("@METADATA"))
                {
                    metadata.putAll(readMetadata(reader));
                }
                else if (line.contains("@TEAMS"))
                {
                    teams.addAll(readTeams(reader));
                }
            }
            else if (line.contains("_COMPETITION"))
            {
                if (sport.toUpperCase().equals(SPORT_NAME.toUpperCase()))
                {
                    populateDatabaseAndOrganiseGames(locationStr, teams);
                }
                else
                {
                    throw new RuntimeException("The database file does not contain teams for the sport: " + SPORT_NAME);
                }
            }
            
        }
    }

    private void populateDatabaseAndOrganiseGames(String locationStr, List<Team> teams)
    {
        Iterator<Team> iterator = teams.iterator();
        Location location = new Location(locationStr);
        while (iterator.hasNext())
        {
            this.sportService.registerTeam(iterator.next());
        }
        this.sportService.shuffleTeams();
        List<Team> shuffledTeams = this.sportService.getTeams();
        for (int i = 0; i < shuffledTeams.size(); i++)
        {
            if (i + 2 <= shuffledTeams.size())
            {
                this.sportService.registerGame(shuffledTeams.get(i), shuffledTeams.get(i + 1), location);
            }
        }
    }

    /**
     * @param reader
     */
    private Map<String, String> readMetadata(GameFileReader reader)
    {
        Map<String, String> metadata = new HashMap<String, String>();
        while (reader.hasMoreLines())
        {
            String line = reader.readTokenLine();
            if (line.contains("_METADATA"))
            {
                return metadata;
            }
            String[] split = line.split("[ =]");
            if (split.length == 2)
            {
                metadata.put(split[0], split[1]);
            }
            else
            {
                throw new RuntimeException("Error while reading sport file. Wrong player/nationality input");
            }
        }
        throw new RuntimeException("Error while reading sport file. Unexpected end of file");
    }

    /**
     * @param reader
     * @param sport
     */
    private List<Team> readTeams(GameFileReader reader)
    {
        List<Team> teams = new ArrayList<Team>();
        while (reader.hasMoreLines())
        {
            String line = reader.readTokenLine();
            if (line.contains("_TEAMS"))
            {
                return teams;
            }
            String[] split = line.split("[ /]");
            if (split.length == 1)
            {
                teams.add(new Team(split[0]));
            }
            else if (split.length == 2)
            {
                teams.add(new Team(split[0], split[1]));
            }
            else
            {
                throw new RuntimeException("Error while reading sport file. Wrong player/nationality input");
            }
                
        }
        throw new RuntimeException("Error while reading sport file. Unexpected end of file");
    }
}

//-----------------------------------------------------------------------------