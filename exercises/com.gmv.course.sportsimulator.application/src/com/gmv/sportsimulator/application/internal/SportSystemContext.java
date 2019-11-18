//-----------------------------------------------------------------------------
//
// (C) 2012 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : eud.product.test
//
// File Name : TestSystemContext.java
//
// Author : Jean Schuetz
//
// Creation Date : 9 Feb 2012
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.application.internal;

import esa.egos.eud.core.service.impl.BaseSystemContext;

/**
 * The System Context Implementation for the EUD Test Product. Every Tailoring
 * of the EUD to a (real) system shall provide their own distinct implementation
 * of BaseSystemContext with the appropriate System-ID.
 * 
 * This TEST_SYSTEM Tailoring is used for offline usage of the EUD in
 * conjunction with the EUD Backend Simulator.
 * 
 * @author Jean Schuetz
 * 
 */
public class SportSystemContext extends BaseSystemContext
{
    /** The SYSTEM_ID of this TestSystemContext: {@value} */
    private static final String SYSTEM_ID = "TEST_SYSTEM";


    /**
     * Creates a new TestSystemContext with the System-ID
     * {@value SportSystemContext#SYSTEM_ID}
     */
    public SportSystemContext()
    {
        super(SYSTEM_ID);
    }

}

// -----------------------------------------------------------------------------
