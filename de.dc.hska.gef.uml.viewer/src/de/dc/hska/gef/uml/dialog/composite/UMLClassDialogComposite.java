package de.dc.hska.gef.uml.dialog.composite;

import java.util.List;

import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import de.dc.hska.gef.uml.dialog.composite.model.UMLBean;

public class UMLClassDialogComposite extends XWTComposite<UMLBean> {

	private UMLBean bean;
	
	public UMLClassDialogComposite(Composite parent, UMLBean bean) {
		super(parent, UMLClassDialogComposite.class, bean);
		this.bean=bean;
		initControl();
	}

	public void onAddAttributeButtonSelection(Event event) {
		List<String> attributes = bean.getAttributes();
		InputDialog id = new InputDialog(new Shell(), "Attributename", "Please give an attributename", null, null);
		int open = id.open();
		if(open == 0){
			attributes.add(id.getValue());
			findTableViewer("attributeViewer").refresh();
		}
	}
	public void onAddMethodButtonSelection(Event event) {
		List<String> methods = bean.getMethods();
		InputDialog id = new InputDialog(new Shell(), "Methodname", "Please give an methodname", null, null);
		int open = id.open();
		if(open == 0){
			methods.add(id.getValue());
			findTableViewer("methodViewer").refresh();
		}
	}

	@Override
	protected void initControl() {
		findTableViewer("attributeViewer").setInput(bean.getAttributes());
		findTableViewer("methodViewer").setInput(bean.getMethods());
		findTableViewer("attributeViewer").setLabelProvider(new LabelProvider());
		findTableViewer("methodViewer").setLabelProvider(new LabelProvider());
	}

	@Override
	protected void initDataBinding() {
		// TODO Auto-generated method stub
		
	}
}
