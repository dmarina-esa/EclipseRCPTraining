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
 * Interface to a service for sport simulation
 * 
 * @author David Marina
 *
 */
public interface ISportService
{

    /**
     * Registers a new game in the database
     * 
     * @param teamA
     * @param teamB
     * @param location
     */
    void registerGame(Team teamA, Team teamB, Location location);

    /**
     * Registers a new game in the database
     * 
     * @param teamA
     * @param teamB
     * @param location
     * @param metadata
     */
    void registerGame(Team teamA, Team teamB, Location location, Map<String, String> metadata);

    /**
     * Simulates a game with a certain speed level:
     * {@link SimulationSpeed#NORMAL}, {@link SimulationSpeed#FAST},
     * {@link SimulationSpeed#SUPERFAST} or {@link SimulationSpeed#TURBO}
     * 
     * @param game
     * @param speed
     */
    void simulateGame(Game game, SimulationSpeed speed);

    /**
     * Registers a sport service listener that will be notified with event
     * updates
     * 
     * @param listener
     */
    void registerServiceListener(ISportServiceListener listener);

    /**
     * Requests the simulation of all the games with a certain speed level:
     * {@link SimulationSpeed#NORMAL}, {@link SimulationSpeed#FAST},
     * {@link SimulationSpeed#SUPERFAST} or {@link SimulationSpeed#TURBO}
     * 
     * @param speed
     */
    void simulateAllGames(SimulationSpeed speed);

    /**
     * Requests to stop the simulation of a given game
     * 
     * @param game
     */
    void stopSimulation(Game game);

    /**
     * Requests to stop the simulation of all the games
     */
    void stopAllSimulations();

    /**
     * Requests the reset of all the games, stopping their simulation and
     * restoring the results to the initial values
     */
    void resetAllGames();

    /**
     * Returns the list of the registered games
     * 
     * @return
     */
    List<Game> getRegisteredGames();

    /**
     * Returns the list of the games that have been started by this simulator
     * service
     * 
     * @return
     */
    List<Game> getStartedGames();

    /**
     * Returns <code>true</code> if any simulation is currently ongoing;
     * <code>false</code> otherwise
     * 
     * @return <code>true</code> if any simulation is currently ongoing;
     *         <code>false</code> otherwise
     */
    boolean isOngoingSimulation();
}

// -----------------------------------------------------------------------------
