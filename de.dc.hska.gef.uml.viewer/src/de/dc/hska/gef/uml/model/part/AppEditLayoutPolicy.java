package de.dc.hska.gef.uml.model.part;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.dc.hska.gef.uml.editor.command.AbstractLayoutCommand;
import de.dc.hska.gef.uml.editor.command.CircleChangeLayoutCommand;
import de.dc.hska.gef.uml.editor.command.ClassChangeLayoutCommand;
import de.dc.hska.gef.uml.editor.command.LineChangeLayoutCommand;
import de.dc.hska.gef.uml.editor.command.RectangleChangeLayoutCommand;
import de.dc.hska.gef.uml.editor.command.UMLCreateCommand;
import de.dc.hska.gef.uml.model.figure.UMLClassFigure;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		AbstractLayoutCommand command = null;
		if (child instanceof UMLClassFigurePart) 
			command = new ClassChangeLayoutCommand();
		else if (child instanceof CircleFigureEditPart) 
			command = new CircleChangeLayoutCommand();
		else if (child instanceof RectangleFigureEditPart) 
			command = new RectangleChangeLayoutCommand();
		else if (child instanceof LineFigureEditPart) 
			command = new LineChangeLayoutCommand();
		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE && getHost() instanceof UMLContainerFigurePart) {
			UMLCreateCommand cmd = new UMLCreateCommand();
			cmd.setUMLContainer(getHost().getModel());
			cmd.setUML(request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? UMLClassFigure.SERVICE_FIGURE_DEFWIDTH
					: constraint.width;
			constraint.height = (constraint.height <= 0) ? UMLClassFigure.SERVICE_FIGURE_DEFHEIGHT
					: constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		}
		return null;
	}
}
