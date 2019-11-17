package com.gmv.course.sportsimulator.display.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

import com.gmv.course.sportsimulator.display.SportServiceUtils;
import com.gmv.course.sportsimulator.display.dialogs.SimulationSpeedDialog;

public class StartAllSimulationsHandler extends AbstractHandler
{

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        SimulationSpeedDialog ssd = new SimulationSpeedDialog(PlatformUI.getWorkbench().getDisplay().getActiveShell());
        if (ssd.open() == Window.OK)
        {
            SportServiceUtils.getSportServiceReference().simulateAllGames(ssd.getSpeed());
        }
        return null;
    }

    @Override
    public boolean isEnabled()
    {
        return !SportServiceUtils.getSportServiceReference().isOngoingSimulation();
    }

}

// -----------------------------------------------------------------------------
