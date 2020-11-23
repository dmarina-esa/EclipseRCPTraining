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
// File Name : EudTestApplication.java
//
// Author : Jean Schuetz
//
// Creation Date : 16 Apr 2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
package esa.open.lib.product.test;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import esa.open.lib.core.CorePlugin;
import esa.open.lib.core.service.ISystemContext;
import esa.open.lib.core.ui.CoreUiPlugin;

/**
 * This class represents the entry point of the RCP application.
 */
public class EudTestApplication implements IApplication
{
    /** {@inheritDoc} */
    @Override
    public Object start(IApplicationContext context)
    {
        ISystemContext systemContext = new TestSystemContext();

        // explicitly set the default systemContext
        CorePlugin.getDefault().setDefaultSystemContext(systemContext);

        Display display = PlatformUI.createDisplay();
        initPlugins();
        try
        {
            ApplicationWorkbenchAdvisor awa = new ApplicationWorkbenchAdvisor(systemContext);

            int rtc = PlatformUI.createAndRunWorkbench(display, awa);
            if (rtc == PlatformUI.RETURN_RESTART)
            {
                return IApplication.EXIT_RESTART;
            }
            return IApplication.EXIT_OK;
        }
        finally
        {
            display.dispose();
        }
    }

    /**
     * Initializes the necessary plugins.
     */
    private void initPlugins()
    {
        ProductTestPlugin.getDefault().initProductTestPlugin();
        CoreUiPlugin.getDefault().initCorePlugin();
    }

    /** {@inheritDoc} */
    @Override
    public void stop()
    {
        final IWorkbench workbench = PlatformUI.getWorkbench();
        if (workbench == null)
        {
            return;
        }

        final Display display = workbench.getDisplay();

        display.syncExec(new Runnable()
        {
            /** {@inheritDoc} */
            @Override
            public void run()
            {
                if (!display.isDisposed())
                {
                    workbench.close();
                }
            }
        });
    }
}
