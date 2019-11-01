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
// File Name : Location.java
//
// Author : David Marina
//
// Creation Date : 1 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.api;

/**
 * @author David Marina
 *
 */
public class Location
{
    
    private final String locationName;

    /**
     * Creates a new Location
     */
    public Location(String locationName)
    {
        this.locationName = locationName;
    }
    
    /**
     * @return a String containing the locationName of this Location
     */
    public String getLocationName()
    {
        return this.locationName;
    }

}

//-----------------------------------------------------------------------------