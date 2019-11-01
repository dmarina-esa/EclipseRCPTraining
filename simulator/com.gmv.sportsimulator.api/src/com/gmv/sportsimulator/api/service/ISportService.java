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

import com.gmv.sportsimulator.api.Game;

/**
 * @author David Marina
 *
 */
public interface ISportService
{
    
    void addGame(Game game);
    
    void simulateGame(Game game, SimulationSpeed speed);
    
    void registerServiceListener(ISportServiceListener listener);
   
}

//-----------------------------------------------------------------------------