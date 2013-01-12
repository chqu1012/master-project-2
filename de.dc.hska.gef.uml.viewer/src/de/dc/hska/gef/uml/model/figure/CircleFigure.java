package de.dc.hska.gef.uml.model.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleFigure extends Figure {
	
	public CircleFigure() {
//		ToolbarLayout layout = new ToolbarLayout();
//		setLayoutManager(layout);
//		setBorder(new LineBorder(ColorConstants.black, 1));
//		setOpaque(true);
//		add(new CircleGeometry());
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	@Override
	protected void paintFigure(Graphics g) {
		super.paintFigure(g);
		g.setAntialias(1);
		g.setForegroundColor(ColorConstants.black);
		g.setBackgroundColor(ColorConstants.blue);
		g.fillOval(getBounds());
	}
}