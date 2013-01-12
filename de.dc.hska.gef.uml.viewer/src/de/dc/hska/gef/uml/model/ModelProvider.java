package de.dc.hska.gef.uml.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;


public enum ModelProvider {
	INSTANCE;
	
	private UMLContainer model;
	
	private ModelProvider() {
		setModel(createDummyUML());
	}

	public UMLContainer getModel() {
		return model;
	}

	public void setModel(UMLContainer model) {
		this.model = model;
	}
	
	public UMLContainer createDummyUML() {
		UMLContainer container = new UMLContainer();
	
		List<String> methods = new ArrayList<>();
		methods.add("setName");
		methods.add("getName");
		methods.add("setAge");
		methods.add("getAge");
		List<String> attributes = new ArrayList<String>();
		attributes.add("name");
		attributes.add("age");
		String className = "Person";
		Point pos = new Point(10, 10);
		container.addChild(createClass(pos, className, attributes, methods));
	
		List<String> m1 = new ArrayList<>();
		m1.add("setName");
		m1.add("getName");
		List<String> a1 = new ArrayList<String>();
		a1.add("name");
		String className1 = "Group";
		Point pos1 = new Point(300, 300);
		container.addChild(createClass(pos1, className1, a1, m1));

		Random rand = new Random();
		
		Circle circle = new Circle();
		circle.setName("TestCircle");
		circle.setLayout(new Rectangle(rand.nextInt(500), rand.nextInt(500), 200, 200));
		circle.setColor("blue");
		container.addChild(circle);
		
		de.dc.hska.gef.uml.model.Rectangle rect = new de.dc.hska.gef.uml.model.Rectangle();
		rect.setName("TestRectangle");
		rect.setLayout(new Rectangle(rand.nextInt(500), rand.nextInt(500), 200, 200));
		container.addChild(rect);
		
		Line line = new Line();
		line.setName("TestLine");
		line.setLayout(new Rectangle(rand.nextInt(500), rand.nextInt(500), 200, 200));
		container.addChild(line);
		
		return container;
	}
	
	public void createClass(){
		Random rand = new Random();
		Point pos = new Point(rand.nextInt(500), rand.nextInt(500));
		
		List<String> methods = new ArrayList<>();
		methods.add("setName");
		methods.add("getName");
		List<String> attributes = new ArrayList<String>();
		attributes.add("name");
		
		model.addChild(createClass(pos, "Test", attributes, methods));
	}
	
	private UML createClass(Point pos, String className,
			List<String> attributes, List<String> methods) {
		UML uml = new UML();
		uml.setLayout(new Rectangle(pos.x, pos.y, 200, 200));
		uml.setName(className);
		uml.setAttributes(attributes);
		uml.setMethods(methods);
		uml.setParent(model);
		return uml;
	}
}
