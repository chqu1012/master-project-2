package de.dc.hska.gef.uml.model.part;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import de.dc.hska.gef.uml.model.Node;
import de.dc.hska.gef.uml.model.UML;
import de.dc.hska.gef.uml.model.figure.UMLClassFigure;
import de.dc.hska.gef.uml.model.policy.AppDeletePolicy;

public class UMLClassFigurePart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		UMLClassFigure figure = new UMLClassFigure();
		UML model = (UML) getModel();
		figure.paintUML(model);
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		 installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy()); 
	}

	@Override
	protected void refreshVisuals() {
		UMLClassFigure figure = (UMLClassFigure) getFigure();
		UML model = (UML) getModel();
		figure.setUml(model);
		figure.setLayout(model.getLayout()); 
		super.refreshVisuals();
	}
	
	public List<Node> getModelChildren() {
		return new ArrayList<Node>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		 if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT)) refreshVisuals(); 		
		 if (evt.getPropertyName().equals(Node.PROPERTY_ADD)) refreshChildren();             
		 if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE)) refreshChildren();          
	}

}
