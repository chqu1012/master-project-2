package de.dc.hska.gef.uml.palette;

import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

import de.dc.hska.gef.uml.editor.factory.CircleObjectFactory;
import de.dc.hska.gef.uml.editor.factory.LineObjectFactory;
import de.dc.hska.gef.uml.editor.factory.RectangleObjectFactory;
import de.dc.hska.gef.uml.editor.factory.UMLObjectFactory;

public class UMLGraphicalEditorPalette extends PaletteRoot {

	PaletteGroup group, geometrieGroup;
	
	public UMLGraphicalEditorPalette() {
		addGroup();
		addSelectionTool();
		addObjectTool();
	}
	
	private void addSelectionTool() {
		SelectionToolEntry entry = new SelectionToolEntry();
		group.add(entry);
		group.add(new MarqueeToolEntry());
		setDefaultEntry(entry);
	}
	
	private void addGroup() {
		group = new PaletteGroup("UML Controls");
		add(group);
		geometrieGroup = new PaletteGroup("Geometry");
		add(geometrieGroup);
	}
	
	private void addObjectTool() {
		CreationToolEntry entry = new CreationToolEntry("UML-Class", "Create a new Class", new UMLObjectFactory(), null, null);
		entry.setToolClass(CreationAndDirectEditTool.class);		
		group.add(entry);
		
		CreationToolEntry circleEntry = new CreationToolEntry("Cirle", "Create a new Circle", new CircleObjectFactory(), null, null);
		circleEntry.setToolClass(CreationAndDirectEditTool.class);		
		
		CreationToolEntry rectangleEntry = new CreationToolEntry("Rectangle", "Create a new Rectangle", new RectangleObjectFactory(), null, null);
		rectangleEntry.setToolClass(CreationAndDirectEditTool.class);		

		CreationToolEntry lineEntry = new CreationToolEntry("Line", "Create a new Line", new LineObjectFactory(), null, null);
		lineEntry.setToolClass(CreationAndDirectEditTool.class);		
		
		geometrieGroup.add(circleEntry);
		geometrieGroup.add(rectangleEntry);
		geometrieGroup.add(lineEntry);
	}
	
}
