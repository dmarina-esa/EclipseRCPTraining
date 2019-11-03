package com.gmv.course.sportsimulator.display;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.gmv.course.exercise.utils.exercise03.Exercise03ContentProvider;
import com.gmv.course.exercise.utils.exercise03.Exercise03Utils;
import com.gmv.sportsimulator.api.service.ISportService;

public class GeneralView extends ViewPart
{

    private ISportService sportService;

    public GeneralView()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent)
    {
        TreeViewer viewer = new TreeViewer(parent);
        viewer.setContentProvider(new Exercise03ContentProvider());
        viewer.setLabelProvider(new SportsLabelProvider());
        try
        {
            this.sportService = Exercise03Utils.getSportService();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        viewer.setInput(this.sportService);
    }

    @Override
    public void setFocus()
    {
        // TODO Auto-generated method stub

    }

}

//-----------------------------------------------------------------------------