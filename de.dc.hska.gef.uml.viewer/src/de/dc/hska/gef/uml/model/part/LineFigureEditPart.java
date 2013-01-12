package de.dc.hska.gef.uml.model.part;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;

import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.Node;
import de.dc.hska.gef.uml.model.Rectangle;
import de.dc.hska.gef.uml.model.figure.LineFigure;
import de.dc.hska.gef.uml.model.figure.RectangleFigure;
import de.dc.hska.gef.uml.model.policy.AppDeletePolicy;

public class LineFigureEditPart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		LineFigure figure = new LineFigure();
		Line model = (Line) getModel();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		 installEditPolicy(EditPolicy.COMPONENT_ROLE,new AppDeletePolicy()); 
	}

	@Override
	protected void refreshVisuals() {
		LineFigure figure = (LineFigure) getFigure();
		Line model = (Line) getModel();
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
