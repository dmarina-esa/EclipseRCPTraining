package com.gmv.sportsimulator.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(false);
        configurer.setTitle("Sports Simulator"); //$NON-NLS-1$
    }
    
    /** {@inheritDoc} */
    @Override
    public void postWindowOpen()
    {
        try
        {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Perspective.GENERAL_VIEW_ID);
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Perspective.TEAM_VIEW_ID);
        }
        catch (PartInitException e)
        {
            e.printStackTrace();
        }
    }
}
