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
// Sub-System : com.gmv.course.sportsimulator.display
//
// File Name : SimulationSpeedDialog.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 17 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.display.dialogs;

import java.util.Arrays;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.gmv.sportsimulator.api.service.SimulationSpeed;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class SimulationSpeedDialog extends Dialog
{
    private SimulationSpeed speed;


    /**
     * Creates a new SimulationSpeedDialog
     * 
     * @param parentShell
     */
    public SimulationSpeedDialog(Shell parentShell)
    {
        super(parentShell);
    }

    /** {@inheritDoc} */
    @Override
    protected Control createDialogArea(Composite parent)
    {
        Group group = new Group(parent, SWT.NONE);
        GridLayoutFactory.swtDefaults().applyTo(group);
        group.setText("Select simulation speed:");
        GridDataFactory.fillDefaults().applyTo(group);
        Combo speedCombo = new Combo(group, SWT.READ_ONLY);
        GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).applyTo(speedCombo);
        speedCombo.setItems(Arrays.stream(SimulationSpeed.values()).map(Enum::name).toArray(String[]::new));
        speedCombo.setText(SimulationSpeed.NORMAL.toString());
        this.speed = SimulationSpeed.NORMAL;
        speedCombo.addSelectionListener(new SelectionAdapter()
        {

            /** {@inheritDoc} */
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                SimulationSpeedDialog.this.speed = SimulationSpeed.valueOf(speedCombo.getText());
            }
        });
        return super.createDialogArea(parent);
    }

    public SimulationSpeed getSpeed()
    {
        return this.speed;

    }
    
    /** {@inheritDoc} */
    @Override
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Simulation Speed");
    }

}

// -----------------------------------------------------------------------------
