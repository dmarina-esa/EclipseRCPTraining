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
// File Name : IGameSimulator.java
//
// Author : David Marina
//
// Creation Date : 2 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

/**
 * @author David Marina
 *
 */
public interface IGameSimulator extends Runnable
{

    abstract void cancelSimulation();

    /**
     * @return
     */
    abstract boolean isRunnableAlive();
    
}

//-----------------------------------------------------------------------------