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
// Sub-System : com.gmv.sportsimulator.servicelocator
//
// File Name : IEudBetService.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 18 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.servicelocator;

import esa.egos.eud.core.service.IEgosService;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public interface IEudBetService extends IEgosService
{
    void placeBet(String userName, int euros, String gameName, String result);
    String[] getBetWinners(String gameName);
    int getAmountWon(String userName);
}

//-----------------------------------------------------------------------------