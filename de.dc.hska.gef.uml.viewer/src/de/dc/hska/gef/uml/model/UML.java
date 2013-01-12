package de.dc.hska.gef.uml.model;

import java.util.ArrayList;
import java.util.List;

public class UML extends Node {

	public static final String NAME = "name";
	public static final String ATTRIBUTES = "ATTRIBUTES";
	public static final String METHODS = "METHODS";
	private String name;
	private List<String> attributes, methods;
	
	public UML() {
		attributes=new ArrayList<>();
		methods=new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
}
