package de.dc.hska.gef.uml.editor.palette;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.CreationFactory;

import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.UML;

public class NodeCreationFactory implements CreationFactory {
	private Class<?> template;

	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;
		else if (template == UML.class) {
			List<String> meth=new ArrayList<>();
			List<String> attr=new ArrayList<>();
			meth.add("getTest");
			attr.add("test");
			UML uml = createClass(new Point(40,40),"NewClass", attr, meth);
			return uml;
		}else if (template == Circle.class) {
			Circle cirle = new Circle();
			cirle.setLayout(new Rectangle(40, 40, 200, 200));
			return cirle;
		}else if (template == de.dc.hska.gef.uml.model.Rectangle.class) {
			de.dc.hska.gef.uml.model.Rectangle rect = new de.dc.hska.gef.uml.model.Rectangle();
			rect.setLayout(new Rectangle(40, 40, 200, 200));
			return rect;
		}else if (template == Line.class) {
			Line line = new Line();
			line.setLayout(new Rectangle(40, 40, 200, 200));
			return line;
		}
		return null;
	}

	private UML createClass(Point pos, String className,
			List<String> attributes, List<String> methods) {
		UML uml = new UML();
		uml.setLayout(new Rectangle(pos.x, pos.y, 200, 200));
		uml.setName(className);
		if(attributes!=null)
		uml.setAttributes(attributes);
		if(methods!=null)
		uml.setMethods(methods);
		return uml;
	}
	
	@Override
	public Object getObjectType() {
		return template;
	}
}