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
// Sub-System : com.gmv.course.sportsimulator.display
//
// File Name : ISportServiceFactory.java
//
// Author : David Marina
//
// Creation Date : 4 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.api.service;

import com.gmv.sportsimulator.api.service.ISportService;

/**
 * @author David Marina
 *
 */
public interface ISportServiceFactory
{

    ISportService getSportService();
}

//-----------------------------------------------------------------------------