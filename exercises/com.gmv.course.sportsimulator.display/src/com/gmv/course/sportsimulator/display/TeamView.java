package com.gmv.course.sportsimulator.display;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import com.gmv.sportsimulator.api.Game;
import com.gmv.sportsimulator.api.Team;
import com.gmv.sportsimulator.api.service.ISportService;
import com.gmv.sportsimulator.servicelocator.ISportServiceFactory;

public class TeamView extends ViewPart implements ISelectionListener
{

    private Text teamNameText;

    private Text countryText;

    private Text genderText;

    private Text victoriesText;

    private ISportService sportService;


    public TeamView()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void createPartControl(Composite parent)
    {
        this.sportService = SportServiceUtils.getSportServiceReference();

        GridLayoutFactory.fillDefaults().applyTo(parent);
        
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayoutFactory.fillDefaults().numColumns(3).equalWidth(true).applyTo(composite);
        GridDataFactory.fillDefaults().applyTo(composite);

        this.teamNameText = createLabelAndText("Name", composite);
        this.countryText = createLabelAndText("Country", composite);
        this.genderText = createLabelAndText("Gender", composite);
        this.victoriesText = createLabelAndText("Victories", composite);

        getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
    }

    private static Text createLabelAndText(String labelText, Composite parent)
    {
        Label newLabel = new Label(parent, SWT.NONE);
        newLabel.setText(labelText + ": ");
        Text newTextWidget = new Text(parent, SWT.BORDER);
        newTextWidget.setEditable(false);
        GridData genderGd = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
        genderGd.horizontalSpan = 2;
        newTextWidget.setLayoutData(genderGd);
        return newTextWidget;
    }

    @Override
    public void setFocus()
    {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection)
    {
        if (selection instanceof IStructuredSelection && !selection.isEmpty())
        {
            Object selectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (selectedElement instanceof Game)
            {
                System.out.println("game");
            }
            else if (selectedElement instanceof Team)
            {
                Team team = (Team) selectedElement;
                this.teamNameText.setText(team.getTeamName());
                this.countryText.setText(team.getCountry());
                this.genderText.setText(team.getGender().toString());
                this.victoriesText.setText(String.valueOf(team.getVictories()));
            }
        }
        
    }

}

// -----------------------------------------------------------------------------
