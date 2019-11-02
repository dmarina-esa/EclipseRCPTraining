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
// Sub-System : com.gmv.sportsimulator.api.service
//
// File Name : ISportService.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api.service;

import java.util.List;
import java.util.Map;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Location;
import com.gmv.sportsimulator.api.Team;

/**
 * @author David Marina
 *
 */
public interface ISportService
{
    
    void registerGame(Team teamA, Team teamB, Location location);
    
    void registerGame(Team teamA, Team teamB, Location location, Map<String, String> metadata);
    
    void simulateGame(Game game, SimulationSpeed speed);
    
    void registerServiceListener(ISportServiceListener listener);

    /**
     * @param normal
     */
    void simulateAllGames(SimulationSpeed normal);
    
    void stopSimulation(Game game);
   
    void stopAllSimulations();
    
    void resetAllGames();
    
    List<Game> getRegisteredGames();
    
    List<Game> getStartedGames();
    
    boolean isOngoingSimulation();
}

//-----------------------------------------------------------------------------