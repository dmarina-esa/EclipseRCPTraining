package com.gmv.course.sportsimulator.display.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

import com.gmv.course.sportsimulator.display.SportServiceUtils;
import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.SimulationSpeed;

public class StartSimulationHandler extends AbstractHandler
{

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = selectionService.getSelection();
        if (selection instanceof IStructuredSelection && !selection.isEmpty())
        {
            Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (selectedElement instanceof Game)
            {
                SportServiceUtils.getSportServiceReference().simulateGame((Game) selectedElement,
                                                                          SimulationSpeed.TURBO);
            }
        }
        return null;
    }

    @Override
    public boolean isEnabled()
    {
        ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = selectionService.getSelection();
        if (selection instanceof IStructuredSelection && !selection.isEmpty())
        {
            Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (selectedElement instanceof Game)
            {
                
                Game game = (Game) selectedElement;
                Team teamA = game.getTeamA();
                Team teamB = game.getTeamB();
                return !SportServiceUtils.getSportServiceReference().isPlaying(teamA) && !SportServiceUtils.getSportServiceReference().isPlaying(teamB);
            }
        }
        return false;
    }

}

// -----------------------------------------------------------------------------
