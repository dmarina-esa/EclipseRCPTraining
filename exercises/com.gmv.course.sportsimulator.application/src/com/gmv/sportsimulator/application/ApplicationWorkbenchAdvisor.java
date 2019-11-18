package com.gmv.sportsimulator.application;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import esa.egos.eud.common.ui.workbench.WorkbenchUtil;
import esa.egos.eud.core.service.ISystemContext;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "com.gmv.sportsimulator.application.perspective"; //$NON-NLS-1$
    private final ISystemContext systemContext;

    /**
     * Creates a new ApplicationWorkbenchAdvisor
     * @param systemContext
     */
    public ApplicationWorkbenchAdvisor(ISystemContext systemContext)
    {
        this.systemContext = systemContext;
    }

    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer, this.systemContext);
    }

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
	
	/** {@inheritDoc} */
	@Override
	public void initialize(IWorkbenchConfigurer configurer)
	{
	    // TODO Auto-generated method stub
	    super.initialize(configurer);
	    
	    // Activate persistence of window size & position
        WorkbenchUtil.addWorkbenchWindowSizePosPersistence(configurer.getWorkbench(), new Point(1000, 800));

        // Inject the SystemContext into the root Eclipse context
        IEclipseContext ec = WorkbenchUtil.getActiveContext();
        ec.set(ISystemContext.class, this.systemContext);
	}
}
