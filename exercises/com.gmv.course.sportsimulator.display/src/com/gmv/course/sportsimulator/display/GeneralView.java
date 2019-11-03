package com.gmv.course.sportsimulator.display;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.gmv.course.exercise.utils.exercise03.Exercise03ContentProvider;
import com.gmv.course.exercise.utils.exercise03.Exercise03LabelProvider;
import com.gmv.course.exercise.utils.exercise03.Exercise03Utils;

public class GeneralView extends ViewPart
{

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
        viewer.setInput(Exercise03Utils.getSportService());
    }

    @Override
    public void setFocus()
    {
        // TODO Auto-generated method stub

    }

}

//-----------------------------------------------------------------------------