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
// File Name : SportServiceUtils.java
//
// Author : David Marina
//
// Creation Date : 4 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.display;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.gmv.sportsimulator.api.service.ISportService;

import esa.open.lib.common.compatibility.SingletonFactory;

/**
 * @author David Marina
 *
 */
public class SportServiceUtils
{

    public static ISportService getSportServiceReference()
    {
        if (!SingletonFactory.INSTANCE.exists(ISportService.class))
        {
            /*
             * We know that the Sport Simulator has been registered as a DS
             * Component
             */
            BundleContext bundleContext = Activator.getDefault().getBundle().getBundleContext();
            ServiceReference<?> sr = bundleContext.getServiceReference(ISportService.class);
            ISportService sportBackend = (ISportService) bundleContext.getService(sr);

            /*
             * This is not really necessary but we want to demonstrate the
             * singleton factory :)
             */
            SingletonFactory.INSTANCE.setInstance(ISportService.class, sportBackend);
        }
        return SingletonFactory.INSTANCE.getInstance(ISportService.class);
    }

}

// -----------------------------------------------------------------------------
