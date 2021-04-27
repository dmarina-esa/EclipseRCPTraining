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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.gmv.course.sportsimulator.api.service.ISportServiceFactory;
import com.gmv.sportsimulator.api.service.ISportService;

/**
 * @author David Marina
 *
 */
public class SportServiceUtils
{

    private static ISportServiceFactory serviceFactory;


    public static ISportService getSportServiceReference()
    {
        if (serviceFactory == null)
        {
            try
            {
                IConfigurationElement[] configElements = Platform.getExtensionRegistry()
                        .getConfigurationElementsFor("com.gmv.course.sportsimulator.api.service.sportsimulator");
                if (configElements.length > 0)
                {
                    serviceFactory = (ISportServiceFactory) configElements[0].createExecutableExtension("class");
                }
                else
                {
                    return null;
                }
            }
            catch (CoreException e)
            {
                e.printStackTrace();
            }
        }
        return serviceFactory.getSportService();
    }
}

// -----------------------------------------------------------------------------
