//-----------------------------------------------------------------------------
//
// (C) 2010 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : eud.product.test
//
// File Name : ProductTestPlugin.java
//
// Author : Jean Schuetz
//
// Creation Date : 16 Apr 2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.view;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import esa.open.lib.common.AbstractEudPlugin;
import esa.open.lib.common.ui.EudAbstractUIPlugin;

/**
 * @author Jean Schuetz
 * 
 */
public class SimulatorViewPlugin extends EudAbstractUIPlugin
{
    /** The shared instance */
    private static SimulatorViewPlugin plugin;

    /** The inner plugin instance */
    private static EUDPlugin eudPlugin;


    /**
     * The constructor
     */
    public SimulatorViewPlugin()
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
    public static SimulatorViewPlugin getDefault()
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
     * Returns an image descriptor for the image file at the given plug-in
     * relative path
     * 
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path)
    {
        return imageDescriptorFromPlugin(getPlugin().getID(), path);
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

// -----------------------------------------------------------------------------
