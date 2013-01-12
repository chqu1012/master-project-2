package de.dc.hska.gef.uml.editor.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import de.dc.hska.gef.uml.model.UML;
import de.dc.hska.gef.uml.model.UMLContainer;

public class UMLCreateCommand extends Command {
	private UML uml;
	private UMLContainer container;

	public UMLCreateCommand() {
		super();
		container=null;
		uml = null;
	}

	public void setUML(Object s) {
		if (s instanceof UML)
			this.uml = (UML) s;
	}
	public void setUMLContainer(Object s) {
		if (s instanceof UMLContainer)
			this.container= (UMLContainer) s;
		
	}


	public void setLayout(Rectangle r) {
		if (uml == null)
			return;
		uml.setLayout(r);
	}

	@Override
	public boolean canExecute() {
		if (uml == null || container==null)
			return false;
		return true;
	}

	@Override
	public void execute() {
//		container.addChild(uml);
	}

	@Override
	public boolean canUndo() {
		if (container == null || uml == null)
			return false;
		return container.contains(uml);
	}

	@Override
	public void undo() {
		container.removeChild(uml);
	}
}