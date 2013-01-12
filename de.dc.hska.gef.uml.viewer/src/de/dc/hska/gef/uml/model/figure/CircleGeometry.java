package de.dc.hska.gef.uml.model.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleGeometry extends Figure {
	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setAntialias(1);
		g.setBackgroundColor(ColorConstants.blue);
		g.fillOval(getBounds());
	}
	
	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setAntialias(1);
		g.setBackgroundColor(ColorConstants.blue);
		g.fillOval(getBounds());
	}
}
