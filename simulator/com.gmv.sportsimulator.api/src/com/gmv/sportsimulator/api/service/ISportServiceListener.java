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
// File Name : ISportServiceListener.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api.service;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.api.Team;

/**
 * Interface for listeners that will be notified about event updates in the
 * sport service
 * 
 * @author David Marina
 *
 */
public interface ISportServiceListener
{
    /**
     * Notifies that one game has been added to the database
     * 
     * @param game
     */
    void gameAdded(Game game);

    /**
     * Notifies that one team has been added to the database
     * 
     * @param team
     */
    void teamAdded(Team team);

    /**
     * Notifies the new result for one of the games
     * 
     * @param game
     * @param result
     */
    void updateResult(Game game, String result);

    /**
     * Notifies that one game has finalised and indicates the winner team and
     * the final result
     * 
     * @param game
     * @param winner
     * @param result
     */
    void gameFinalised(Game game, Team winner, Result result);

    /**
     * Notifies that one game has been started
     * 
     * @param game
     */
    void gameStarted(Game game);

    /**
     * Notifies that one game has been reseted and returns the initial result
     * (typically 0-0)
     * 
     * @param game
     * @param result
     */
    void gameReseted(Game game, Result result);

    /**
     * Notifies that at least one game simulation has started
     */
    void simulationStarted();

    /**
     * Notifies that all the simulations have finalised
     */
    void simulationEnded();
}

// -----------------------------------------------------------------------------
