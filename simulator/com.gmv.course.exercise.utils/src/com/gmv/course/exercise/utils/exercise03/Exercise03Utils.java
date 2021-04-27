package com.gmv.course.exercise.utils.exercise03;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.ServiceReference;

import com.gmv.course.exercise.utils.Activator;
import com.gmv.sportsimulator.api.service.ISportService;

/**
 * Utility class for Exercise 03.
 */
public class Exercise03Utils
{

    private static ISportService serviceReference;


    /**
     * Private constructor.
     */
    private Exercise03Utils()
    {
        // Do nothing
    }

    /**
     * Creates the tree viewer for exercise 03
     * 
     * @param parent
     *            The parent composite provided by Eclipse to the
     *            createPartControl() method.
     * @return The parent composite.
     */
    public static TreeViewer createViewContents(Composite parent)
    {
        TreeViewer viewer = new TreeViewer(parent);
        viewer.setContentProvider(new Exercise03ContentProvider());
        viewer.setLabelProvider(new Exercise03LabelProvider());
        viewer.setInput(getSportService());

        return viewer;
    }

    public static ISportService getSportService()
    {
        if (serviceReference == null)
        {
            ServiceReference<ISportService> sr = Activator.getContext().getServiceReference(ISportService.class);
            ISportService service = Activator.getContext().getService(sr);
            serviceReference = service;
        }
        return serviceReference;
    }
}
