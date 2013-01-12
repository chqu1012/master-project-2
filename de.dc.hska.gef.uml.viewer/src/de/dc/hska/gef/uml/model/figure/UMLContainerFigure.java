package de.dc.hska.gef.uml.model.figure;


import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class UMLContainerFigure extends Figure {

	public UMLContainerFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
	}
	
	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}
}