package de.dc.hska.gef.uml.viewer;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.dc.hska.gef.uml.editor.UMLEditorInput;
import de.dc.hska.gef.uml.editor.UMLGraphicalEditor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "de.dc.hska.gef.uml.viewer.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public void postStartup() {
//		try {
//			IWorkbenchWindow window = PlatformUI.getWorkbench()
//					.getActiveWorkbenchWindow();
//			IWorkbenchPage page = window.getActivePage();
//			page.openEditor(new UMLEditorInput("UML Editor"),
//					UMLGraphicalEditor.ID, false);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
