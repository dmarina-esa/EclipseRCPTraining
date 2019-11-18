package com.gmv.sportsimulator.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import esa.egos.eud.core.service.ISystemContext;
import esa.egos.eud.core.ui.listeners.StatusLineLogListener;
import esa.egos.eud.workspace.WorkspacePlugin;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    private ISystemContext systemContext;

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer, ISystemContext systemContext) {
        super(configurer);
        this.systemContext = systemContext;
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer, this.systemContext);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        // show progress while loading application
        configurer.setShowProgressIndicator(true);
        configurer.setTitle("Sports Simulator"); //$NON-NLS-1$
    }
    
    /** {@inheritDoc} */
    @Override
    public void postWindowCreate()
    {
        // Update the StatusBar with the last log message
        StatusLineLogListener.getInstance().updateStatusLineManager(getWindowConfigurer().getActionBarConfigurer()
                .getStatusLineManager());
//        try
//        {
//            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Perspective.GENERAL_VIEW_ID);
//            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(Perspective.TEAM_VIEW_ID);
//        }
//        catch (PartInitException e)
//        {
//            e.printStackTrace();
//        }
        
        initStorageAreas();
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

    private void initStorageAreas()
    {
        WorkspacePlugin.getDefault().startWorkspaceStorage(this.systemContext);
    }
    
    
}
