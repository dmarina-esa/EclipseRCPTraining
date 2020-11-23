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
// File Name : SportsTableLabelProvider.java
//
// Author : <ADD YOUR NAME IN TEMPLATE under Window->
//
// Creation Date : 22 Nov 2020
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package course.eud.simulator.view;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import course.eud.simulator.serviceadapter.EudGame;
import esa.open.lib.common.ui.table.IGenericDataTableLabelProvider;

/**
 * @author <TODO: Specify Full name of initial Author in template>
 *
 */
public class SportsTableLabelProvider extends LabelProvider implements IGenericDataTableLabelProvider
{

    /** {@inheritDoc} */
    @Override
    public Image getColumnImage(Object element, int columnIndex)
    {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getColumnText(Object element, int columnIndex)
    {
        EudGame game = (EudGame) element;
        String text;
        if (columnIndex == 0)
        {
            text = game.getTeamA().getTeamName();
        }
        else if (columnIndex == 1)
        {
            text = game.getTeamB().getTeamName();
        }
        else
        {
            text = game.getResult();
        }
        return text;
    }

}

// -----------------------------------------------------------------------------
