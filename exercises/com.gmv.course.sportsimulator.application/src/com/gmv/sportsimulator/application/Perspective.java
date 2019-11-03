package com.gmv.sportsimulator.application;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

    private static final String GENERAL_VIEW_ID = "com.gmv.course.sportsimulator.display.generalview";
    
	@Override
    public void createInitialLayout(IPageLayout layout) {
	    layout.setEditorAreaVisible(false);
	    IFolderLayout leftFolder = layout.createFolder("LEFT", IPageLayout.LEFT, 0.3f, layout.getEditorArea());
	    leftFolder.addView(GENERAL_VIEW_ID);
	    IFolderLayout rightFolder = layout.createFolder("RIGHT", IPageLayout.RIGHT, 0.7f, "LEFT");
	}
}
