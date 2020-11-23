//-----------------------------------------------------------------------------
//
// (C) 2015 European Space Agency
// European Space Operations Centre
// Darmstadt, Germany
//
//-----------------------------------------------------------------------------
//
// System : EGOS USER DESKTOP
//
// Sub-System : eud.product.test
//
// File Name : OpenMimicDisplayHandler.java
//
// Author : David Marina
//
// Creation Date : 15 Dec 2015
//
//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------

package esa.open.lib.product.test.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import esa.open.lib.product.test.ProductTestPlugin;

/**
 * @author David Marina
 *
 */
public class OpenEudLogHandler extends AbstractHandler
{

    /** The LOG_VIEW_ID of this OpenEudLogActionDelegate: {@value} */
    private static final String LOG_VIEW_ID = "org.eclipse.pde.runtime.LogView";


    /** {@inheritDoc} */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException
    {
        IWorkbenchPart part = HandlerUtil.getActivePart(event);
        try
        {
            part.getSite().getWorkbenchWindow().getActivePage().showView(LOG_VIEW_ID);
        }
        catch (PartInitException e)
        {
            String msg = "Error Log view could not be opened. Check View-ID in OpenEudLogActionDelegate";
            ProductTestPlugin.getPlugin().logError(msg, e);
        }
        catch (Exception e)
        {
            // Ignore Exception due to shutdown
        }
        return null;
    }

}

// -----------------------------------------------------------------------------
