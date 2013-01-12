package de.dc.hska.gef.uml.model.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;

public class LineFigure extends Figure {
	
	public LineFigure() {
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setAntialias(1);
		g.setForegroundColor(ColorConstants.yellow);
		g.setBackgroundColor(ColorConstants.yellow);
		g.setLineWidth(5);
		g.drawLine(getBounds().x, getBounds().y, getBounds().x+200, getBounds().y+200);
	}
}