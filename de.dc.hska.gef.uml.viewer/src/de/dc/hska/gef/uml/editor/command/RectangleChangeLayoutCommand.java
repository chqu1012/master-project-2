package de.dc.hska.gef.uml.editor.command;

import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleChangeLayoutCommand extends AbstractLayoutCommand {
	private de.dc.hska.gef.uml.model.Rectangle model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {                   
		this.model = (de.dc.hska.gef.uml.model.Rectangle)model;                   
		this.oldLayout = ((de.dc.hska.gef.uml.model.Rectangle)model).getLayout();           
	}                      
	public void undo() {                   
		this.model.setLayout(this.oldLayout);           
	}
}