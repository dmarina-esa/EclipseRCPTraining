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
// File Name : WorkspaceViewContributor.java
//
// Author : Noe Casas
//
// Creation Date : 06.07.2010
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package esa.open.lib.product.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchWindow;

import esa.open.lib.core.service.ISystemContext;
import esa.open.lib.workspace.contributions.IWorkspaceViewContributor;

/**
 * Class that contributes custom actions to the workspace view. This mechanism
 * is supposed to be used for actions in the Workspace View that can not be
 * provided through the plugin.xml (using ViewActions Extension Point).
 */
public class WorkspaceViewContributor implements IWorkspaceViewContributor
{
    /** {@inheritDoc} */
    @Override
    public Collection<IAction> getGeneralActions(ISystemContext context, IWorkbenchWindow window)
    {
        Collection<IAction> actions = new ArrayList<IAction>();
        return actions;
    }

    /** {@inheritDoc} */
    @Override
    public Collection<IAction> getNewDisplayActions(ISystemContext context, IWorkbenchWindow window)
    {
        return Collections.emptyList();
    }
}
