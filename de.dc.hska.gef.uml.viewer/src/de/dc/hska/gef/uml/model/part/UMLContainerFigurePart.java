package de.dc.hska.gef.uml.model.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import de.dc.hska.gef.uml.model.Node;
import de.dc.hska.gef.uml.model.UMLContainer;
import de.dc.hska.gef.uml.model.figure.UMLContainerFigure;
import de.dc.hska.gef.uml.model.policy.AppDeletePolicy;

public class UMLContainerFigurePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new UMLContainerFigure();
		return figure;
	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		UMLContainerFigure figure = (UMLContainerFigure) getFigure();
	}
	
	public List<Node> getModelChildren() {
		return  ((UMLContainer)getModel()).getChildrenArray(); 
	}

    protected void createEditPolicies() {                 
    	installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());   
//    	installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy()); 
    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
//		 if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();             
//		 if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();         
	} 
}
