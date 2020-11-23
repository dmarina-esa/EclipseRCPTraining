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

package esa.open.lib.product.test;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;

import esa.open.lib.common.AbstractEudPlugin;
import esa.open.lib.common.ui.EudAbstractUIPlugin;
import esa.open.lib.core.ui.CoreUiPlugin;
import esa.open.lib.core.ui.preferences.PreferenceConstants;

/**
 * @author Jean Schuetz
 * 
 */
public class ProductTestPlugin extends EudAbstractUIPlugin
{
    /** The shared instance */
    private static ProductTestPlugin plugin;

    /** The inner plugin instance */
    private static EUDPlugin eudPlugin;


    /**
     * The constructor
     */
    public ProductTestPlugin()
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

    /**
     * Initializes the product test plugin
     */
    public void initProductTestPlugin()
    {
        // disable the simple login dialog for the demo product.
        CoreUiPlugin.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_EUD_LOGIN_SIMPLE, false);

        // enable the login for the demo product.
        CoreUiPlugin.getDefault().getPreferenceStore().setValue(PreferenceConstants.P_EUD_LOGIN_PRESENT, true);

        /*
         * set the operation mode for the parameter browser:
         * P_EUD_PARAM_BROWSER_SHOW_PARAM_TABLE = false -> Tree viewer only
         * P_EUD_PARAM_BROWSER_SHOW_PARAM_TABLE = true -> Table viewer +
         * hierarchy tree
         */
        CoreUiPlugin.getDefault().getPreferenceStore()
                .setValue(PreferenceConstants.P_EUD_PARAM_BROWSER_SHOW_PARAM_TABLE, true);

        /*
         * set the parameter browser to automatically select the first match
         * after a filter update.
         */
        CoreUiPlugin.getDefault().getPreferenceStore()
                .setValue(PreferenceConstants.P_EUD_PARAM_BROWSER_AUTO_SELECT_POST_FILTER, true);

        /*
         * set the parameter browser's filter max level retrieval until only
         * cache is used.
         */
        CoreUiPlugin.getDefault().getPreferenceStore()
                .setValue(PreferenceConstants.P_EUD_PARAM_BROWSER_MAX_FILTER_LEVEL, 100);
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
    public static ProductTestPlugin getDefault()
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
