package de.dc.hska.gef.uml.model.part;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.Rectangle;
import de.dc.hska.gef.uml.model.UML;
import de.dc.hska.gef.uml.model.UMLContainer;

public class AppEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		AbstractGraphicalEditPart part = null;
		if (model instanceof UMLContainer)
			part = new UMLContainerFigurePart();
		else if (model instanceof UML)
			part = new UMLClassFigurePart();
		else if (model instanceof Circle)
			part = new CircleFigureEditPart();
		else if (model instanceof Rectangle)
			part = new RectangleFigureEditPart();
		else if (model instanceof Line)
			part = new LineFigureEditPart();
		part.setModel(model);
		
		return part;
	}

}
