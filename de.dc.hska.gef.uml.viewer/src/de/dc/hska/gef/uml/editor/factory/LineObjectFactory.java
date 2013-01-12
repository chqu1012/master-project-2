package de.dc.hska.gef.uml.editor.factory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;
import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.ModelProvider;

public class LineObjectFactory implements CreationFactory {

	@Override public Object getNewObject() {
		Line line = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			line = new Line();
		
			InputDialog id = new InputDialog(new Shell(), "Line Name", "Please give a name for the line!", "", null);
			int open = id.open();
			if(open==0){
				if(!id.getValue().equals("")){
					line.setName(id.getValue());
				}
			}
			
			ModelProvider.INSTANCE.getModel().addChild(line);
			UMLGraphicalEditor editor = (UMLGraphicalEditor) page.getActiveEditor();
			editor.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}

	@Override public Object getObjectType() {
		return Circle.class;
	}

}
