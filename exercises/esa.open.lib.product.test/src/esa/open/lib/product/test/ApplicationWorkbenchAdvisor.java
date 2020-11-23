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
// File Name : ApplicationWorkbenchAdvisor.java
//
// Author : Jean Schuetz
//
// Creation Date : 16 Apr 2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
package esa.open.lib.product.test;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import esa.open.lib.common.ui.workbench.WorkbenchUtil;
import esa.open.lib.core.service.ISystemContext;

/**
 * This workbench advisor creates the window advisor, and specifies the
 * perspective id for the initial window.
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor
{
    /** The preference key to the default perspective-ID: {@value} */
    private static final String DEFAULT_PERSPECTIVE_ID = "DEFAULT_PERSPECTIVE_ID";

    /** Constant for the namespace product test: {@value} */
    private static final String NAMESPACE_PRODUCT_TEST = "esa.open.lib.product.test";

    /** The perspective to be open at application start */
    private String perspectiveId;

    /** The systemContext of this ApplicationWorkbenchAdvisor */
    private final ISystemContext systemContext;


    /**
     * Creates a new ApplicationWorkbenchAdvisor
     * 
     * @param systemContext
     */
    public ApplicationWorkbenchAdvisor(ISystemContext systemContext)
    {
        this.systemContext = systemContext;
        IConfigurationElement[] elements = Platform.getExtensionRegistry()
                .getConfigurationElementsFor("org.eclipse.ui.perspectives");
        for (IConfigurationElement elem : elements)
        {
            if (elem.getAttribute("id") != null && elem.getNamespaceIdentifier().equals(NAMESPACE_PRODUCT_TEST))
            {
                this.perspectiveId = elem.getAttribute("id");
            }
        }
        if (this.perspectiveId == null)
        {
            this.perspectiveId = ProductTestPlugin.getPlugin().getStringResource(DEFAULT_PERSPECTIVE_ID);
        }
    }

    /** {@inheritDoc} */
    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer)
    {
        return new ApplicationWorkbenchWindowAdvisor(configurer, this.systemContext);
    }

    /** {@inheritDoc} */
    @Override
    public void initialize(IWorkbenchConfigurer configurer)
    {
        super.initialize(configurer);

        /*
         * DOES NOT WORK anymore with Eclipse 4. So much for the
         * "compatibility layer". In e4 one has to use the
         * "-clearPersistedState" command line switch to achieve the same
         * behaviour. For EUD save and restore from the framework must be
         * deactivated.
         */
        configurer.setSaveAndRestore(false);

        // Activate persistence of window size & position
        WorkbenchUtil.addWorkbenchWindowSizePosPersistence(configurer.getWorkbench(), new Point(800, 600));

        // Inject the SystemContext into the root Eclipse context
        IEclipseContext ec = WorkbenchUtil.getActiveContext();
        ec.set(ISystemContext.class, this.systemContext);
    }

    /** {@inheritDoc} */
    @Override
    public String getInitialWindowPerspectiveId()
    {
        return this.perspectiveId;
    }

    /** {@inheritDoc} */
    @Override
    public boolean preShutdown()
    {
        boolean delete = true;
        /*
         * Enable the following if EUD exit confirmation is desired.
         */
        // delete = GeneralDialogMessage.showQuestion("Application Exit",
        // "Do you really want to close the application?");
        return delete;
    }

    /** {@inheritDoc} */
    @Override
    public IStatus restoreState(IMemento memento)
    {
        return super.restoreState(memento);
    }

    /**
     * Retrieves the reference to the system context
     */
    protected ISystemContext getSystemContext()
    {
        return this.systemContext;
    }
}
