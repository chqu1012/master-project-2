package de.dc.hska.gef.uml.editor.factory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.dialog.UMLClassDialog;
import de.dc.hska.gef.uml.dialog.composite.model.UMLBean;
import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;
import de.dc.hska.gef.uml.model.ModelProvider;
import de.dc.hska.gef.uml.model.UML;

public class UMLObjectFactory implements CreationFactory {

	@Override public Object getNewObject() {
		UML uml = null;
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
			UMLClassDialog dialog = new UMLClassDialog(new Shell());
			int open = dialog.open();
			if (open == 0) {
				UMLBean bean = dialog.getBean();
				uml = new UML();
				uml.setAttributes(bean.getAttributes());
				uml.setMethods(bean.getMethods());
				uml.setName(bean.getName());
				uml.setParent(ModelProvider.INSTANCE.getModel());
				ModelProvider.INSTANCE.getModel().addChild(uml);
				UMLGraphicalEditor editor = (UMLGraphicalEditor) page.getActiveEditor();
				editor.refresh();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uml;
	}

	@Override public Object getObjectType() {
		return UML.class;
	}

}
