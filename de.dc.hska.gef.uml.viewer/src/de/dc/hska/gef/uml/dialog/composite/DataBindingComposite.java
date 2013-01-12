package de.dc.hska.gef.uml.dialog.composite;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.jface.databinding.viewers.ViewerSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;

public abstract class DataBindingComposite extends Composite{

	protected DataBindingContext dbc;
	
	public DataBindingComposite(Composite parent) {
		super(parent, 0);
		dbc=new DataBindingContext();
	}

	protected void bindTableViewer(TableViewer viewer, List<?> inputList, Class modelClass, String... titles){
		WritableList input = new WritableList(inputList, modelClass);
		ViewerSupport.bind(viewer, input, BeanProperties.values(titles)); 
	}
	
	protected abstract void initDataBinding();
}
