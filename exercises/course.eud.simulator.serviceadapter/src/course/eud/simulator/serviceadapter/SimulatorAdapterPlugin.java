package course.eud.simulator.serviceadapter;
import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import esa.open.lib.common.AbstractEudPlugin;
import esa.open.lib.common.EudPlugin;

public class SimulatorAdapterPlugin extends EudPlugin implements BundleActivator
{

    /** The shared instance */
    private static SimulatorAdapterPlugin plugin;

    /** The inner plugin instance */
    private static EUDPlugin eudPlugin;


    /**
     * The constructor
     */
    public SimulatorAdapterPlugin()
    {
        plugin = this;
        eudPlugin = new EUDPlugin();
    }

    /** {@inheritDoc} */
    @Override
    public void start(BundleContext context) throws Exception
    {
        super.start(context);
    }

    /** {@inheritDoc} */
    @Override
    public void stop(BundleContext context) throws Exception
    {
        plugin = null;
        super.stop(context);
    }

    /**
     * Returns the shared instance
     * 
     * @return the shared instance
     */
    public static SimulatorAdapterPlugin getDefault()
    {
        return plugin;
    }

    /**
     * returns the EUD plugin
     * 
     * @return the EUD plugin
     */
    public static EUDPlugin getPlugin()
    {
        return eudPlugin;
    }


    /**
     * Inner plugin class providing added functionality
     */
    public class EUDPlugin extends AbstractEudPlugin
    {
        /** {@inheritDoc} */
        @Override
        public Plugin getPlugin()
        {
            return plugin;
        }
    }
}

//-----------------------------------------------------------------------------