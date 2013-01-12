package de.dc.hska.gef.uml.model.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleFigure extends Figure {
	
	public RectangleFigure() {
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setAntialias(1);
		g.setForegroundColor(ColorConstants.black);
		g.setBackgroundColor(ColorConstants.red);
		g.fillRectangle(getBounds());
	}
}