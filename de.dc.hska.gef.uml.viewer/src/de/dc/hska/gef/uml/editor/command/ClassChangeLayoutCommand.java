package de.dc.hska.gef.uml.editor.command;

import org.eclipse.draw2d.geometry.Rectangle;

import de.dc.hska.gef.uml.model.UML;

public class ClassChangeLayoutCommand extends AbstractLayoutCommand {
	private UML model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {                   
		this.model = (UML)model;                   
		this.oldLayout = ((UML)model).getLayout();           
	}                      
	public void undo() {                   
		this.model.setLayout(this.oldLayout);           
	}
}