package de.dc.hska.gef.uml.viewer;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		 String editorArea = layout.getEditorArea(); 
         layout.setEditorAreaVisible(true); 
         layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.25f, editorArea);
         layout.addView(IPageLayout.ID_PROP_SHEET, IPageLayout.BOTTOM, 0.75f, editorArea);
         
//         IFolderLayout tabs = layout.createFolder(
//        		 "folder", IPageLayout.LEFT, 0.3f, editorArea);
//        		 tabs.addView(IPageLayout.ID_OUTLINE);
//        		 tabs.addPlaceholder(IPageLayout.ID_PROP_SHEET);
//        		 }
	}
}
