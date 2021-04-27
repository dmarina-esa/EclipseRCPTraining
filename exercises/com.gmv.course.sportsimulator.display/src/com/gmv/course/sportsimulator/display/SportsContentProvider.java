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
// File Name : SportsContentProvider.java
//
// Author : David Marina
//
// Creation Date : 17 Nov 2019
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package com.gmv.course.sportsimulator.display;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.service.ISportService;

/**
 * @author David Marina
 *
 */
public class SportsContentProvider implements ITreeContentProvider
{

    @Override
    public Object[] getElements(Object inputElement)
    {
        // The input element is a ISportService (see ViewPart1). This
        // method is called to build the top level nodes of the tree; those
        // elements are the games returned by the service:
        if (inputElement instanceof ISportService)
        {
            ISportService service = (ISportService) inputElement;
            return service.getRegisteredGames().toArray();
        }

        // If the input is not a ISportService we don't know how to handle
        // it and therefore there will be no items in the tree. Never null!
        return new Object[0];
    }

    @Override
    public Object[] getChildren(Object parentElement)
    {
        // IProject elements have tasks as children.
        if (parentElement instanceof Game)
        {
            Game game = (Game) parentElement;
            return game.getTeams().toArray();
        }
        // If it is not an IProject, no children are returned. Never null!
        return new Object[0];
    }

    @Override
    public Object getParent(Object element)
    {
        // ITask elements have parent but it cannot be easily computed (not
        // needed for this example)
        return null;
    }

    @Override
    public boolean hasChildren(Object element)
    {
        // IProject elements may have children (tasks)
        return (element instanceof Game);
    }

    @Override
    public void dispose()
    {
        // TODO Auto-generated method stub
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
    {
        // TODO Auto-generated method stub

    }

}

// -----------------------------------------------------------------------------
