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
// File Name : ISimulationThread.java
//
// Author : David Marina
//
// Creation Date : 2 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

/**
 * General interface for a runnable implementing a single game simulation
 * 
 * @author David Marina
 *
 */
public interface ISimulationThread extends Runnable
{

    /**
     * Request the simulation to be canceled as soon as possible
     */
    abstract void cancelSimulation();

    /**
     * Returns <code>true</code> if the simulation is still running;
     * <code>false</code> otherwise
     * 
     * @return <code>true</code> if the simulation is still running;
     *         <code>false</code> otherwise
     */
    abstract boolean isRunnableAlive();

}

// -----------------------------------------------------------------------------
