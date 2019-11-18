//-----------------------------------------------------------------------------
//
// (C) 2010 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : eud.product.test
//
// File Name : ApplicationActionBarAdvisor.java
//
// Author : Jean Schuetz
//
// Creation Date : 16 Apr 2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
package com.gmv.sportsimulator.application;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import esa.egos.eud.common.ui.compatibility.CompatibilityUtilities;
import esa.egos.eud.core.service.ISystemContext;
import esa.egos.eud.core.ui.actions.ApplicationInformationAction;
import esa.egos.eud.core.ui.actions.ViewNavigationHistoryAction;
import esa.egos.eud.workspace.actions.OpenWorkspaceViewAction;

/**
 * Class responsible for the actions that are added to the workbench window.
 * Note that the fillxxx methods are called for each RCP Window that is created.
 * 
 * @author Jean Schuetz
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{
    /**
     * Note that the creation of the action objects and the filling into the
     * workspace are being performed in different methods. This ensures that the
     * action objects are not replicated for each workbench window.
     */
    private IWorkbenchAction exitAction;

    /** The About Action in the Help menu */
    private IWorkbenchAction aboutAction;

    /** The new Window Action in the Window menu */
    private IWorkbenchAction newWindowAction;

    /** The Preferences Action in the Window menu */
    private IWorkbenchAction preferencesAction;

    /** The Lock ToolBar Action */
    private IWorkbenchAction lockToolBarAction;

    /** Show EUD Workspaces View Action */
    private IAction showWorkspaceViewAction;

    /** The Show View Menu in the Window menu */
    private MenuManager showViewMenuMgr;

    /** ISystemContext object */
    private final ISystemContext systemContext;

    /** Open windows */
    private IContributionItem openWindows;

    /** The go backward action (used to navigate between views) */
    private ViewNavigationHistoryAction viewNavigationHistoryBackward;

    /** The go forward action (used to navigate between views) */
    private ViewNavigationHistoryAction viewNavigationHistoryForward;


    /**
     * Creates a new ApplicationActionBarAdvisor
     * 
     * @param configurer
     */
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer, ISystemContext systemContext)
    {
        super(configurer);
        this.systemContext = systemContext;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation creates the actions and registers them. Registering is
     * needed to ensure that key bindings work. The corresponding commands
     * keybindings are defined in the plugin.xml file. Registering also provides
     * automatic disposal of the actions when the window is closed.
     */
    @Override
    protected void makeActions(final IWorkbenchWindow window)
    {
        this.exitAction = ActionFactory.QUIT.create(window);
        register(this.exitAction);

        // Action performed behind a facade for RCP/RAP compatibility
        this.aboutAction = CompatibilityUtilities.ABOUT.create(window);
        register(this.aboutAction);

        this.newWindowAction = ActionFactory.OPEN_NEW_WINDOW.create(window);
        this.newWindowAction.setText("&New Window");
        register(this.newWindowAction);

        this.preferencesAction = ActionFactory.PREFERENCES.create(window);
        register(this.preferencesAction);

        this.showViewMenuMgr = new MenuManager("Show View", "showView");
        this.showViewMenuMgr.add(ContributionItemFactory.VIEWS_SHORTLIST.create(window));

        this.lockToolBarAction = ActionFactory.LOCK_TOOL_BAR.create(window);
        register(this.lockToolBarAction);

        this.showWorkspaceViewAction = new OpenWorkspaceViewAction(this.systemContext);

        this.openWindows = ContributionItemFactory.OPEN_WINDOWS.create(window);

        this.viewNavigationHistoryBackward = new ViewNavigationHistoryAction("Previous", false, window);
        register(this.viewNavigationHistoryBackward);

        this.viewNavigationHistoryForward = new ViewNavigationHistoryAction("Next", true, window);
        register(this.viewNavigationHistoryForward);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation fills the toolbar with the 'Show Workspace' Action. Other
     * toolbar actions are populated by the various EUD plugins through the
     * plugin.xml extension mechanisms.
     */
    @Override
    protected void fillCoolBar(ICoolBarManager coolBar)
    {
        /*
         * Create named placeholder groups for the main application toolbar. Those group
         * can then be populated via the plugin.xml org.eclipse.ui.actionSets EP, by
         * specifying the desired group in the toolbarPath field, e.g.
         * toolbarPath="alpha/additions" or "delta/additions". Alternatively the
         * locationURI scheme can be used to add action contributions in the following
         * way: toolbar:org.eclipse.ui.main.toolbar?after=beta
         */
        int style = SWT.NONE;
        if (CompatibilityUtilities.INSTANCE.isRAPApplication())
        {
            // Change style to flat to avoid ugly tool bar effect in RAP E4
            style = SWT.FLAT;
        }
        IToolBarManager tba = new ToolBarManager(style);
        tba.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        tba.add(this.showWorkspaceViewAction);
        coolBar.add(new ToolBarContributionItem(tba, "alpha"));

        IToolBarManager tbb = new ToolBarManager(style);
        tbb.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        coolBar.add(new ToolBarContributionItem(tbb, "beta"));

        IToolBarManager tbg = new ToolBarManager(style);
        tbg.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        coolBar.add(new ToolBarContributionItem(tbg, "gamma"));

        IToolBarManager tbd = new ToolBarManager(style);
        tbd.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        coolBar.add(new ToolBarContributionItem(tbd, "delta"));

        IToolBarManager tbe = new ToolBarManager(style);
        tbe.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        coolBar.add(new ToolBarContributionItem(tbe, "epsilon"));

        IToolBarManager tbz = new ToolBarManager(style);
        tbz.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        tbz.add(this.viewNavigationHistoryBackward);
        tbz.add(this.viewNavigationHistoryForward);
        coolBar.add(new ToolBarContributionItem(tbz, "zeta"));

        super.fillCoolBar(coolBar);
    }

    /** {@inheritDoc} */
    @Override
    protected void fillMenuBar(IMenuManager menuBar)
    {
        MenuManager fileMenu = new MenuManager("&File", IWorkbenchActionConstants.M_FILE);
        MenuManager workspaceMenu = new MenuManager("W&orkspace", "workspace");
        MenuManager helpMenu = new MenuManager("&Help", IWorkbenchActionConstants.M_HELP);
        MenuManager windowMenu = new MenuManager("&Window", IWorkbenchActionConstants.M_WINDOW);

        menuBar.add(fileMenu);
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(workspaceMenu);
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(windowMenu);
        menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        menuBar.add(helpMenu);

        // File Menu
        // Creation of some groups for setting commands and the Exit command at
        // the end of the menu
        fileMenu.add(new GroupMarker("additions.1"));
        fileMenu.add(new Separator());
        fileMenu.add(new GroupMarker("additions.2"));
        fileMenu.add(new Separator());
        fileMenu.add(new GroupMarker("additions.3"));
        fileMenu.add(new Separator());
        fileMenu.add(new GroupMarker("additions"));
        fileMenu.add(new Separator());
        fileMenu.add(this.exitAction);

        // Workspace menu
        workspaceMenu.add(this.showWorkspaceViewAction);
        workspaceMenu.add(new Separator());

        // Window Menu
        windowMenu.add(this.lockToolBarAction);
        windowMenu.add(new Separator());
        windowMenu.add(this.newWindowAction);
        windowMenu.add(new Separator());

        /*
         * Deactivate the following line if the 'Show View' menu shall not be shown in
         * your product.
         */
        windowMenu.add(this.showViewMenuMgr);
        windowMenu.add(this.preferencesAction);

        windowMenu.add(this.openWindows);

        helpMenu.add(new Separator());
        helpMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
        helpMenu.add(new Separator());
        helpMenu.add(this.aboutAction);
    }

}
