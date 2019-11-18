package com.gmv.sportsimulator.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import esa.egos.eud.workspace.views.WorkspaceView;

public class Perspective implements IPerspectiveFactory {

    public static final String GENERAL_VIEW_ID = "com.gmv.course.sportsimulator.display.generalview";
    
    public static final String TEAM_VIEW_ID = "com.gmv.course.sportsimulator.display.teamview";
    
	@Override
    public void createInitialLayout(IPageLayout layout) {
	    layout.setEditorAreaVisible(false);
	    IFolderLayout leftFolder = layout.createFolder("LEFT", IPageLayout.LEFT, 0.3f, layout.getEditorArea());
	    leftFolder.addView(WorkspaceView.getViewId());
	    IFolderLayout rightFolder = layout.createFolder("RIGHT", IPageLayout.RIGHT, 0.7f, layout.getEditorArea());
	    rightFolder.addView(GENERAL_VIEW_ID);
	    rightFolder.addView(TEAM_VIEW_ID);
	}
}
