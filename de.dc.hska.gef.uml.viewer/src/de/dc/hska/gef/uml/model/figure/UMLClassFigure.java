package de.dc.hska.gef.uml.model.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import de.dc.hska.gef.uml.model.UML;

public class UMLClassFigure extends Figure {

	public static final int SERVICE_FIGURE_DEFWIDTH = 200;
	public static final int SERVICE_FIGURE_DEFHEIGHT = 200;
	
	public static Color classColor = new Color(null, 255, 255, 206);
	private CompartmentFigure attributeFigure = new CompartmentFigure();
	private CompartmentFigure methodFigure = new CompartmentFigure();
	private Label className = new Label();
	private UML uml;

	public UMLClassFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		setBorder(new LineBorder(ColorConstants.black, 1));
		setBackgroundColor(classColor);
		setOpaque(true);

		add(className);
		add(attributeFigure);
		add(methodFigure);

		methodFigure.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent me) {
				Object o = me.getSource();
				System.out.println("UMLFigure Content: " + o);
			}
			@Override
			public void mouseReleased(MouseEvent me) {}
			@Override
			public void mouseDoubleClicked(MouseEvent me) {}
		});
	}

	public void setLayout(Rectangle rect) {
		setBounds(rect);
	}

	public UML getUml() {
		return uml;
	}

	public void setUml(UML uml) {
		this.uml = uml;
	}

	public void paintUML(UML uml) {
		className.setText(uml.getName());
		Label attribute, method;

		for (String a : uml.getAttributes()) {
				attribute = new Label(a, new Image(null,
						UMLClassFigure.class
								.getResourceAsStream("field_private_obj.gif")));
				attributeFigure.add(attribute);
		}
		
		for (String m : uml.getMethods()) {
			method = new Label(m+"()", new Image(null,
					UMLClassFigure.class.getResourceAsStream("methpub_obj.gif")));
			methodFigure.add(method);
		}
	}
}