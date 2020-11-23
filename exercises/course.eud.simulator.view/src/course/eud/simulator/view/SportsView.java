package course.eud.simulator.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import course.eud.simulator.serviceadapter.IEudSportService;
import esa.open.lib.common.exceptions.ServiceUnavailableException2;
import esa.open.lib.common.ui.table.GenericDataTable;
import esa.open.lib.common.ui.table.TableConfiguration;
import esa.open.lib.core.CorePlugin;
import esa.open.lib.core.service.IEgosService;
import esa.open.lib.core.service.ISystemContext;
import esa.open.lib.core.ui.views.AbstractEudView;

public class SportsView extends AbstractEudView
{

    private IEudSportService sportSimulator;
    private List<IEgosService> services;

    public SportsView()
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<IEgosService> getServices()
    {
        
        return this.services;
    }

    @Override
    public AbstractUIPlugin getPlugin()
    {
        return SimulatorViewPlugin.getDefault();
    }

    @Override
    public String getDisplayType()
    {
        return "Sports";
    }

    @Override
    public void doCreatePartControl(Composite parent, ISystemContext systemContext)
    {
        Composite composite = new Composite(parent, SWT.NONE);
        GridLayoutFactory.fillDefaults().applyTo(composite);
        GridDataFactory.fillDefaults().applyTo(composite);
        Label title = new Label(composite, SWT.NONE);
        
        title.setText("This is the content of the first EUD view");
        
        registerServices();

        createWidgets(composite);
    }

    /**
     * @param composite 
     * 
     */
    private void createWidgets(Composite composite)
    {
        TableConfiguration tc = new TableConfiguration(new String[] {"Team1", "Team2", "Result"});
        GenericDataTable table = new GenericDataTable(composite, SWT.NONE, tc);
        table.getTableViewer().setLabelProvider(new SportsTableLabelProvider());
        table.setInput(new SportTableInput(this.sportSimulator));
        
        
    }

    /**
     * 
     */
    private void registerServices()
    {
        this.services = new ArrayList<>();
        try
        {
            this.sportSimulator = (IEudSportService) CorePlugin.getDefault().getServiceManager().getServiceAdapterFor(getSystemContext(), "sports_simulator");
            CorePlugin.getDefault().getServiceManager().getMonitorFor(this.sportSimulator);
            this.services.add(this.sportSimulator);
        }
        catch (ServiceUnavailableException2 e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    protected void focus()
    {
        // TODO Auto-generated method stub

    }

}

//-----------------------------------------------------------------------------