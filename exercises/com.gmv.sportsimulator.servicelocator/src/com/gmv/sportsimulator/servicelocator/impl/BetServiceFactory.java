package com.gmv.sportsimulator.servicelocator.impl;

import com.gmv.sportsimulator.servicelocator.IEudBetService;

import esa.egos.eud.common.compatibility.SingletonFactory;
import esa.egos.eud.common.exceptions.ServiceUnavailableException2;
import esa.egos.eud.core.service.IEgosService;
import esa.egos.eud.core.service.IEudAdapterFactory;
import esa.egos.eud.core.service.IServiceManager;
import esa.egos.eud.core.service.ISystemContext;
import esa.egos.eud.core.types.exceptions.NoDedicatedServiceInstanceException;

public class BetServiceFactory implements IEudAdapterFactory
{

    @Override
    public IEgosService getService(ISystemContext systemContext, IServiceManager serviceManager) throws ServiceUnavailableException2
    {
        if (!SingletonFactory.INSTANCE.exists(IEudBetService.class))
        {
            EUDBetService eudBetService = new EUDBetService();
            SingletonFactory.INSTANCE.setInstance(IEudBetService.class, eudBetService);
        }
        return SingletonFactory.INSTANCE.getInstance(IEudBetService.class);
    }

    @Override
    public IEgosService createDedicatedServiceInstance(ISystemContext systemContext, IServiceManager serviceManager) throws ServiceUnavailableException2,
                                                                                                                    NoDedicatedServiceInstanceException
    {
        return new EUDBetService();
    }

}

//-----------------------------------------------------------------------------