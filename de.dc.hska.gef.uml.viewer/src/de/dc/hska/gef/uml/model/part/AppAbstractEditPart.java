package de.dc.hska.gef.uml.model.part;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.SelectEditPartTracker;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.model.Node;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart
		implements PropertyChangeListener {
	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((Node) getModel()).removePropertyChangeListener(this);
	}

//	@Override
//	public DragTracker getDragTracker(Request req) {
//		return new SelectEditPartTracker(this);
//	}
//
//	@Override
//	public void performRequest(Request req) {
//		if (req.getType().equals(RequestConstants.REQ_OPEN)) {
//			try {
//				IWorkbenchPage page = PlatformUI.getWorkbench()
//						.getActiveWorkbenchWindow().getActivePage();
//				page.showView(IPageLayout.ID_PROP_SHEET);
//			} catch (PartInitException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}