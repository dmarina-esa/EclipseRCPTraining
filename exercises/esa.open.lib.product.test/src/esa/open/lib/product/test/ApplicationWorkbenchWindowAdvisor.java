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
// File Name : ApplicationWorkbenchWindowAdvisor.java
//
// Author : Jean Schuetz
//
// Creation Date : 16 Apr 2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
package esa.open.lib.product.test;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import esa.open.lib.common.ui.CommonUiPlugin;
import esa.open.lib.common.ui.workbench.WorkbenchUtil;
import esa.open.lib.core.service.ISystemContext;
import esa.open.lib.core.ui.listeners.StatusLineLogListener;
import esa.open.lib.workspace.WorkspacePlugin;

/**
 * @author Jean Schuetz
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{
    /** The key to search for a custom instance ID in the System.Properties */
    private static final String INSTANCE_ID_KEY = "eud.instance";

    /** a reference to the unique ISystemContext of this example tailoring. */
    private final ISystemContext systemContext;


    /**
     * Creates a new ApplicationWorkbenchWindowAdvisor
     * 
     * @param configurer
     *            the IWorkbenchWindowConfigurer
     * @param systemContext
     *            the unique ISystemContext of this example tailoring
     */
    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer, ISystemContext systemContext)
    {
        super(configurer);
        this.systemContext = systemContext;
    }

    /** {@inheritDoc} */
    @Override
    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer)
    {
        return new ApplicationActionBarAdvisor(configurer, this.systemContext);
    }

    /** {@inheritDoc} */
    @Override
    public void preWindowOpen()
    {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

        // show the cool tool bar
        configurer.setShowCoolBar(true);
        // show progress while loading application
        configurer.setShowProgressIndicator(true);
        // do not show the perspective bar
        configurer.setShowPerspectiveBar(false);
        // enable status line so that the progress indicator can be shown there
        configurer.setShowStatusLine(true);
        // set an initial Size
        WorkbenchUtil.setInitialApplicationWindowSize(configurer, new Point(800, 600));
    }

    /** {@inheritDoc} */
    @Override
    public void postWindowCreate()
    {
        // Update the StatusBar with the last log message
        StatusLineLogListener.getInstance().updateStatusLineManager(getWindowConfigurer().getActionBarConfigurer()
                .getStatusLineManager());

        // maximise the new workbench window by default. This is a matter of
        // taste and may also be disabled.
        getWindowConfigurer().getWindow().getShell().setMaximized(false);

        // Add the optional instance ID to the window title
        String appTitle = getWindowConfigurer().getTitle();
        if (System.getProperty(INSTANCE_ID_KEY) != null)
        {
            appTitle += " <I " + System.getProperty(INSTANCE_ID_KEY) + ">";
            getWindowConfigurer().setData(CommonUiPlugin.ORIGINAL_APPLICATION_TITLE_KEY, appTitle);
            CommonUiPlugin.updateWindowTitle(getWindowConfigurer());
        }

        // Cache the reference to the window configurer in the common.ui plugin
        // to make it available to the EUD application's plugins
        CommonUiPlugin.getDefault().setWorkbenchConfigurerForWorkbenchWindow(getWindowConfigurer(),
                                                                             getWindowConfigurer().getWindow());
        // Startup Persistent Storage Managers
        WorkspacePlugin.getDefault().startWorkspaceStorage(this.systemContext);
    }
}
