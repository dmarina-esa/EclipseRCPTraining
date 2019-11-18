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
// File Name : EUDBetService.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 18 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.sportsimulator.servicelocator.impl;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.servicelocator.IEudBetService;
import com.gmv.sportsimulator.servicelocator.ISportServiceFactory;

import esa.egos.eud.common.compatibility.SingletonFactory;
import esa.egos.eud.common.exceptions.ConnectionException;
import esa.egos.eud.core.service.connection.IConnectableService;
import esa.egos.eud.core.service.impl.Preference;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class EUDBetService implements IEudBetService, IConnectableService
{
    
    private ISportService sportService;

    /**
     * Creates a new EUDBetService
     */
    public EUDBetService()
    {
        if (!SingletonFactory.INSTANCE.exists(ISportServiceFactory.class))
        {
            try
            {
                IConfigurationElement[] configElements = Platform.getExtensionRegistry()
                        .getConfigurationElementsFor("com.gmv.sportsimulator.servicelocator.sportsimulator");
                if (configElements.length > 0)
                {
                    ISportServiceFactory serviceFactory = (ISportServiceFactory) configElements[0].createExecutableExtension("class");
                    SingletonFactory.INSTANCE.setInstance(ISportServiceFactory.class, serviceFactory);
                }
                else
                {
                    throw new RuntimeException("No sport service found in the extension registry");
                }
            }
            catch (CoreException e)
            {
                e.printStackTrace();
            }
        }
        ISportServiceFactory factory = SingletonFactory.INSTANCE.getInstance(ISportServiceFactory.class);
        if (factory != null)
        {
            this.sportService = factory.getSportService();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void placeBet(String userName, int euros, String gameName, String result)
    {
    
        this.sportService.placeBet(userName, null, null, euros);
    }

    /** {@inheritDoc} */
    @Override
    public String[] getBetWinners(String gameName)
    {
        // TODO Auto-generated method stub
        return new String[0];
    }

    /** {@inheritDoc} */
    @Override
    public int getAmountWon(String userName)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    /** {@inheritDoc} */
    @Override
    public String getName()
    {
        return "Bet_Service";
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Preference> getPreferences()
    {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setPreferences(Map<String, Preference> preferences)
    {
        // TODO Auto-generated method stub
        
    }

    /** {@inheritDoc} */
    @Override
    public void connect() throws ConnectionException
    {
        // TODO Auto-generated method stub
        
    }

    /** {@inheritDoc} */
    @Override
    public void disconnect()
    {
        // TODO Auto-generated method stub
        
    }

    /** {@inheritDoc} */
    @Override
    public boolean checkConnection()
    {
        // TODO Auto-generated method stub
        return this.sportService != null;
    }

}

//-----------------------------------------------------------------------------