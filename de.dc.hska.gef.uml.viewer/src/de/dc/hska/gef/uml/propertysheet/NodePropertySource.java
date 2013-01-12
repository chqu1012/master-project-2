package de.dc.hska.gef.uml.propertysheet;

import java.util.ArrayList;

import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import de.dc.hska.gef.uml.model.Circle;
import de.dc.hska.gef.uml.model.Line;
import de.dc.hska.gef.uml.model.Node;
import de.dc.hska.gef.uml.model.Rectangle;
import de.dc.hska.gef.uml.model.UML;

public class NodePropertySource implements IPropertySource {

	private Node node;
	
	public NodePropertySource(Node node) {
		this.node = node;
	}
	
	@Override
	public Object getEditableValue() {
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		if (node instanceof UML){
			properties.add(new PropertyDescriptor(Node.PROPERTY_NAME, "Name"));
			properties.add(new PropertyDescriptor(UML.ATTRIBUTES, "Attributes"));
			properties.add(new PropertyDescriptor(UML.METHODS, "Methods"));
		}
//		else{
//			properties.add(new PropertyDescriptor(Node.PROPERTY_NAME, "Name"));
//			properties.add(new PropertyDescriptor(Node.PROPERTY_NODE_COLOR, "Color"));
//		}
		else if (node instanceof Circle) {
			properties.add(new PropertyDescriptor(Node.PROPERTY_NAME, "Name"));
			properties.add(new PropertyDescriptor(Node.PROPERTY_NODE_COLOR, "Color"));
		}
		else if (node instanceof Rectangle) {
			properties.add(new PropertyDescriptor(Node.PROPERTY_NODE_COLOR, "Color"));
			properties.add(new PropertyDescriptor(Node.PROPERTY_NAME, "Name"));
		}    
		else if (node instanceof Line) {
			properties.add(new PropertyDescriptor(Node.PROPERTY_NODE_COLOR, "Color"));
			properties.add(new PropertyDescriptor(Node.PROPERTY_NAME, "Name"));
		}
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(Node.PROPERTY_NAME))
				return node.getName();
		else if (id.equals(Node.PROPERTY_NODE_COLOR))
			return node.getColor();
		else if (id.equals(UML.ATTRIBUTES))
			return ((UML)node).getAttributes().toString();
		else if (id.equals(UML.METHODS))
			return ((UML)node).getMethods().toString();
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return false;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		// TODO Auto-generated method stub

	}

}
