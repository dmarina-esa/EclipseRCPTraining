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
 * @author David Marina
 *
 */
public interface ISportServiceListener
{
    
    void gameAdded(Game game);

    void updateResult(Game game, String result);
    
    void gameFinalised(Game game, Team winner, Result result);
    
    void gameStarted(Game game);

    /**
     * @param game
     * @param result
     */
    void gameReseted(Game game, Result result);
}

//-----------------------------------------------------------------------------