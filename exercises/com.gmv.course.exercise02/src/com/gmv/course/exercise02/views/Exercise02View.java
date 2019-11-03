package com.gmv.course.exercise02.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.gmv.course.exercise.utils.exercise02.Exercise02Utils;

public class Exercise02View extends ViewPart
{

    public Exercise02View()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent)
    {
        Exercise02Utils.createViewContents(parent);

    }

    @Override
    public void setFocus()
    {
        // TODO Auto-generated method stub

    }

}

//-----------------------------------------------------------------------------