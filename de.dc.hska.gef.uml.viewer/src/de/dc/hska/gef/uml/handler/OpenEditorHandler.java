package de.dc.hska.gef.uml.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.editor.UMLEditorInput;
import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;

public class OpenEditorHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new UMLEditorInput("UM Editor"), UMLGraphicalEditor.ID,
					false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
