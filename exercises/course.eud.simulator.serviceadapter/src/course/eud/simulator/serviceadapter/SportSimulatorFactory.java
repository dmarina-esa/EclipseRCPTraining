package course.eud.simulator.serviceadapter;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.gmv.sportsimulator.api.service.ISportService;

import esa.open.lib.common.compatibility.SingletonFactory;
import esa.open.lib.common.exceptions.ServiceUnavailableException2;
import esa.open.lib.core.service.IEgosService;
import esa.open.lib.core.service.IEudAdapterFactory;
import esa.open.lib.core.service.IServiceManager;
import esa.open.lib.core.service.ISystemContext;
import esa.open.lib.core.types.exceptions.NoDedicatedServiceInstanceException;

public class SportSimulatorFactory implements IEudAdapterFactory
{

    @Override
    public IEgosService getService(ISystemContext systemContext,
                                   IServiceManager serviceManager) throws ServiceUnavailableException2
    {
        if (!SingletonFactory.INSTANCE.exists(IEudSportService.class))
        {
            SingletonFactory.INSTANCE.setInstance(IEudSportService.class, createServiceInstance());
        }
        
        return SingletonFactory.INSTANCE.getInstance(IEudSportService.class);
    }

    @Override
    public IEgosService createDedicatedServiceInstance(ISystemContext systemContext,
                                                       IServiceManager serviceManager) throws ServiceUnavailableException2,
                                                                                       NoDedicatedServiceInstanceException
    {
        return createServiceInstance();
    }

    private IEudSportService createServiceInstance()
    {
        BundleContext bundleContext = SimulatorAdapterPlugin.getDefault().getBundle().getBundleContext();
        ServiceReference<?> sr = bundleContext.getServiceReference(ISportService.class);
        ISportService sportBackend = (ISportService) bundleContext.getService(sr);
        return new EudSportService(sportBackend);
    }

}

//-----------------------------------------------------------------------------