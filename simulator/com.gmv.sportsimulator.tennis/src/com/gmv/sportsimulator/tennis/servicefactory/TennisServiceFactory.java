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
// Sub-System : com.gmv.sportsimulator.tennis
//
// File Name : TennisServiceFactory.java
//
// Author : David Marina
//
// Creation Date : 17 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.tennis.servicefactory;

import org.eclipse.core.runtime.Platform;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.servicelocator.ISportServiceFactory;
import com.gmv.sportsimulator.tennis.Activator;

/**
 * @author David Marina
 *
 */
public class TennisServiceFactory implements ISportServiceFactory
{

    private ISportService sportService;

    /**
     * Creates a new TennisServiceFactory
     */
    public TennisServiceFactory()
    {
       System.out.println(); // TODO Auto-generated constructor stub
    }

    /** {@inheritDoc} */
    @Override
    public ISportService getSportService()
    {
        if (this.sportService == null)
        {
            BundleContext bundleContext = Platform.getBundle(Activator.BUNDLE_ID).getBundleContext();
            ServiceReference<ISportService> serviceReference = bundleContext.getServiceReference(ISportService.class);
            this.sportService = bundleContext.getService(serviceReference);
        }
        return this.sportService;
    }

}

// -----------------------------------------------------------------------------
