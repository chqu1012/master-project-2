package de.dc.hska.gef.uml.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.dc.hska.gef.uml.dialog.UMLClassDialog;
import de.dc.hska.gef.uml.dialog.composite.model.UMLBean;
import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;
import de.dc.hska.gef.uml.model.ModelProvider;
import de.dc.hska.gef.uml.model.UML;

public class AddDummyClassHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage();
//			
//			UMLGraphicalEditor editor = (UMLGraphicalEditor) page.getActiveEditor();
//			ModelProvider.INSTANCE.createClass();
//			editor.setModel();
//			System.out.println("Children size of UMLContainer: "+ModelProvider.INSTANCE.getModel().getChildrenArray().size());
		
			UMLClassDialog dialog = new UMLClassDialog(new Shell());
			int open = dialog.open();
			if (open == 0) {
				UMLBean bean = dialog.getBean();
				UML uml = new UML();
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
		return null;
	}

}
