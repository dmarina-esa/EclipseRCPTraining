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
 * Class representing a game location
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

    /** {@inheritDoc} */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.locationName == null) ? 0 : this.locationName.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        if (this.locationName == null)
        {
            if (other.locationName != null) return false;
        }
        else if (!this.locationName.equals(other.locationName)) return false;
        return true;
    }
    
    

}

//-----------------------------------------------------------------------------