package com.gmv.sportsimulator.application;

import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
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
    public void postWindowCreate()
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
//        
//        EPartService partService = (EPartService) PlatformUI.getWorkbench().getService(EPartService.class);
//        
//        
//        MPart gamesPart  = partService.findPart("com.gmv.course.sportsimulator.display.e4.gamespart");
//        MPart otherPart = partService.findPart(Perspective.TEAM_VIEW_ID);
//        
//        MPlaceholder otherPartPlaceHolder = otherPart.getCurSharedRef();
//        EModelService ms = (EModelService) PlatformUI.getWorkbench().getService(EModelService.class);
//
//        ms.move(gamesPart, otherPartPlaceHolder.getParent(), false);
        
    }
    
    
}
