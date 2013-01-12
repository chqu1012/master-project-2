package de.dc.hska.gef.uml.model.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Node;
import de.dc.hska.gef.uml.model.Rectangle;
import de.dc.hska.gef.uml.model.UML;
import de.dc.hska.gef.uml.model.policy.AppDeletePolicy;

public class RectangleTreeEditPart extends AppAbstractTreeEditPart {
	protected List<Node> getModelChildren() {
		return ((Rectangle) getModel()).getChildrenArray();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}
	public void refreshVisuals() {
		Rectangle model = (Rectangle) getModel();
		setWidgetText("Rectangle: "+model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_OBJ_ELEMENT));
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
	}
}