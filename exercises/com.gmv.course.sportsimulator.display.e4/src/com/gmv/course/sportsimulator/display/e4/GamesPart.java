//-----------------------------------------------------------------------------
//
// (C) 2019 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : com.gmv.course.sportsimulator.display.e4
//
// File Name : GamesPart.java
//
// Author : David Marina
//
// Creation Date : 5 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.display.e4;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.gmv.sportsimulator.api.service.ISportService;

/**
 * @author David Marina
 *
 */
public class GamesPart
{
    
    @Inject
    private ISportService sportService;
    
    @Inject
    public void createPartcontents(Composite composite, IEclipseContext ec)
    {
        Label label = new Label(composite, SWT.BORDER);
        label.setText("Games");
        System.out.println(sportService.getRegisteredGames());
    }

}

//-----------------------------------------------------------------------------