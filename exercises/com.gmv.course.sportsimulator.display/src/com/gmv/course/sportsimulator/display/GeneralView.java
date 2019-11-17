package com.gmv.course.sportsimulator.display;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.part.ViewPart;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Result;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.api.service.ISportServiceListener;

public class GeneralView extends ViewPart implements ISportServiceListener
{

    private ISportService sportService;

    private Label simulationLabel;


    public GeneralView()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayoutFactory.fillDefaults().applyTo(composite);
        GridDataFactory.fillDefaults().applyTo(composite);
        TreeViewer viewer = new TreeViewer(composite);
        GridDataFactory.fillDefaults().grab(true, true).applyTo(viewer.getTree());
        viewer.setContentProvider(new SportsContentProvider());
        viewer.setLabelProvider(new SportsLabelProvider());
        try
        {
            this.sportService = SportServiceUtils.getSportServiceReference();// Exercise03Utils.getSportService();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        viewer.setInput(this.sportService);

        getSite().setSelectionProvider(viewer);

        MenuManager mm = new MenuManager("com.gmv.sportsimulator.generalviewmenu");
        ((IMenuService) getSite().getService(IMenuService.class))
                .populateContributionManager(mm, "popup:com.gmv.sportsimulator.generalviewmenu");
        Menu menu = mm.createContextMenu(viewer.getTree());
        viewer.getTree().setMenu(menu);
        this.simulationLabel = new Label(composite, SWT.BORDER);
        this.simulationLabel.setText("simulation");
        GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).applyTo(this.simulationLabel);

        this.sportService.registerServiceListener(this);
    }

    @Override
    public void setFocus()
    {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void gameAdded(Game game)
    {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void teamAdded(Team team)
    {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void updateResult(Game game, String result)
    {
//        System.out.println("update Result: " + game.getName() + " result: " + result);

    }

    /** {@inheritDoc} */
    @Override
    public void gameFinalised(Game game, Team winner, Result result)
    {
//        System.out.println("update Result: " + game.getName() + " - winner " + winner
//                           + " result: " + result);
    }

    /** {@inheritDoc} */
    @Override
    public void gameStarted(Game game)
    {
//        System.out.println("Game started: " + game.getName());

    }

    /** {@inheritDoc} */
    @Override
    public void gameReseted(Game game, Result result)
    {
//        System.out.println("Game reseted: " + game.getName());

    }

    /** {@inheritDoc} */
    @Override
    public void simulationStarted()
    {
        Display display = this.simulationLabel.getDisplay();
        display.asyncExec(() -> {
            this.simulationLabel.setText("Simulation Running");
            this.simulationLabel.setBackground(display.getSystemColor(SWT.COLOR_GREEN));
        });

    }

    /** {@inheritDoc} */
    @Override
    public void simulationEnded()
    {
        Display display = this.simulationLabel.getDisplay();
        display.asyncExec(() -> {
            this.simulationLabel.setText("Simulation Stopped");
            this.simulationLabel.setBackground(display.getSystemColor(SWT.COLOR_RED));
        });

    }

    /** {@inheritDoc} */
    @Override
    public void playerWinsBet(String winner, Game game, Result result, int amountWon)
    {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void playerLosesBet(String loser, Game game)
    {
        // TODO Auto-generated method stub

    }

}

// -----------------------------------------------------------------------------
