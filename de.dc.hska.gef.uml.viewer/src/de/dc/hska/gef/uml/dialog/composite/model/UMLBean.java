package de.dc.hska.gef.uml.dialog.composite.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class UMLBean implements PropertyChangeListener{
	
	private String name;
	private List<String> attributes;
	private List<String> methods;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public UMLBean() {
		attributes=new ArrayList<>();
		methods=new ArrayList<>();
	}
	
 	public String getName(){
 		return name;
 	}
 	
 	public List<String> getAttributes(){
 		return attributes;
 	}
 	
 	public List<String> getMethods(){
 		return methods;
 	}
 	
 	public void setName(String name){
		propertyChangeSupport.firePropertyChange("name", this.name, this.name = name);
 	}
 	
 	public void setAttributes(List<String> attributes){
		propertyChangeSupport.firePropertyChange("attributes", this.attributes, this.attributes = attributes);
 	}
 	
 	public void setMethods(List<String> methods){
		propertyChangeSupport.firePropertyChange("methods", this.methods, this.methods = methods);
 	}
 	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[name: "+name+"] ");
		sb.append("[attributes: "+attributes+"] ");
		sb.append("[methods: "+methods+"] ");
		return sb.toString();
	}
}
