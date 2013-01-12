package de.dc.hska.gef.uml.editor.factory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;
import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.ModelProvider;
import de.dc.hska.gef.uml.model.Rectangle;

public class RectangleObjectFactory implements CreationFactory {

	@Override public Object getNewObject() {
		Rectangle rect = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			rect = new Rectangle();
			ModelProvider.INSTANCE.getModel().addChild(rect);
			
			InputDialog id = new InputDialog(new Shell(), "Rectangle Name", "Please give a name for the rectangle!", "", null);
			int open = id.open();
			if(open==0){
				if(!id.getValue().equals("")){
					rect.setName(id.getValue());
				}
			}
			
			UMLGraphicalEditor editor = (UMLGraphicalEditor) page.getActiveEditor();
			editor.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rect;
	}

	@Override public Object getObjectType() {
		return Circle.class;
	}

}
