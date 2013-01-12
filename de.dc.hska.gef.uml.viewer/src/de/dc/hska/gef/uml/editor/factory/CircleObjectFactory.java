package de.dc.hska.gef.uml.editor.factory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;
import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.ModelProvider;

public class CircleObjectFactory implements CreationFactory {

	@Override public Object getNewObject() {
		Circle circle = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			circle = new Circle();
			
			InputDialog id = new InputDialog(new Shell(), "Circle Name", "Please give a name for the circle!", "", null);
			int open = id.open();
			if(open==0){
				if(!id.getValue().equals("")){
					circle.setName(id.getValue());
				}
			}
			
			ModelProvider.INSTANCE.getModel().addChild(circle);
			UMLGraphicalEditor editor = (UMLGraphicalEditor) page.getActiveEditor();
			editor.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return circle;
	}

	@Override public Object getObjectType() {
		return Circle.class;
	}

}
