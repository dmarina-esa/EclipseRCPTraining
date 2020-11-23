//-----------------------------------------------------------------------------
//
// (C) 2020 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : course.eud.simulator.view
//
// File Name : SportTableInput.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 22 Nov 2020
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.view;

import course.eud.simulator.serviceadapter.EudGame;
import course.eud.simulator.serviceadapter.IEudSportService;
import esa.open.lib.common.ui.table.ITableData;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class SportTableInput implements ITableData
{
    private IEudSportService service;

    /**
     * Creates a new SportTableInput
     */
    public SportTableInput(IEudSportService service)
    {
        this.service = service;
    }

    /** {@inheritDoc} */
    @Override
    public Object[] getTableData()
    {
        return this.service.getRegisteredGames().toArray(new EudGame[0]);
    }

}

//-----------------------------------------------------------------------------