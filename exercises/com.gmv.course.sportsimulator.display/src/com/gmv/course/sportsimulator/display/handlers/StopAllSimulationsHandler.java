package com.gmv.course.sportsimulator.display.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.gmv.course.sportsimulator.display.SportServiceUtils;

public class StopAllSimulationsHandler extends AbstractHandler
{

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        SportServiceUtils.getSportServiceReference().stopAllSimulations();
        return null;
    }

    @Override
    public boolean isEnabled()
    {
        return SportServiceUtils.getSportServiceReference().isOngoingSimulation();
    }

}

// -----------------------------------------------------------------------------
