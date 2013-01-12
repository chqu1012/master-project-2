package de.dc.hska.gef.uml.model.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.Rectangle;
import de.dc.hska.gef.uml.model.UML;
import de.dc.hska.gef.uml.model.UMLContainer;

public class AppTreeEditPartFactory implements EditPartFactory {
	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;
		if (model instanceof UML)
			part = new UMLTreeEditPart();
		else if(model instanceof UMLContainer)
			part = new UMLContainerTreeEditPart();
		else if(model instanceof UML)
			part = new UMLTreeEditPart();
		else if(model instanceof Circle)
			part = new CircleTreeEditPart();
		else if(model instanceof Rectangle)
			part = new RectangleTreeEditPart();
		else if(model instanceof Line)
			part = new LineTreeEditPart();
		
		if (part != null)
			part.setModel(model);
		return part;
	}
}