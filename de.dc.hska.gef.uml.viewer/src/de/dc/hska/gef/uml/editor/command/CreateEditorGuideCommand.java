package de.dc.hska.gef.uml.editor.command;

import org.eclipse.gef.commands.Command;

import de.dc.hska.gef.uml.editor.ruler.Guide;
import de.dc.hska.gef.uml.editor.ruler.Ruler;

public class CreateEditorGuideCommand extends Command {

	private Guide guide;
	private Ruler parent;
	private int position;

	public CreateEditorGuideCommand(Ruler parent, int position) {
		super("Create guide");
		this.parent = parent;
		this.position = position;
	}

	public boolean canUndo() {
		return true;
	}

	public void execute() {
		if (guide == null)
			guide = new Guide(!parent.isHorizontal());
		guide.setPosition(position);
		parent.addGuide(guide);
	}

	public void undo() {
		parent.removeGuide(guide);
	}
}