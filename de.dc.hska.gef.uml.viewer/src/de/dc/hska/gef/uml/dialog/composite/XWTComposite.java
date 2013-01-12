package de.dc.hska.gef.uml.dialog.composite;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.e4.xwt.DefaultLoadingContext;
import org.eclipse.e4.xwt.IXWTLoader;
import org.eclipse.e4.xwt.XWT;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public abstract class XWTComposite<T> extends DataBindingComposite{

	protected Control control;

	public XWTComposite(Composite parent, Class clazz, T bean) {
		super(parent);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		String name = clazz.getSimpleName()+".xwt";
		try {
			URL url = clazz.getResource(name);
			Map<String, Object> options = new HashMap<String, Object>();
			options.put(IXWTLoader.CLASS_PROPERTY, this);
			options.put(IXWTLoader.CONTAINER_PROPERTY, this);
			options.put(IXWTLoader.DATACONTEXT_PROPERTY, bean);
			XWT.setLoadingContext(new DefaultLoadingContext(this.getClass()
					.getClassLoader()));
			control = XWT.loadWithOptions(url, options);
		}catch(Throwable e) {
			throw new Error("Unable to load " + name, e);
		}
	}
	
	protected abstract void initControl();
	
	protected Text findText(String name){
		return (Text) XWT.findElementByName(control, name);
	}

	protected Label findLabel(String name){
		return (Label) XWT.findElementByName(control, name);
	}

	protected Combo findCombo(String name){
		return (Combo) XWT.findElementByName(control, name);
	}
	protected TableViewer findTableViewer(String name){
		return (TableViewer) XWT.findElementByName(control, name);
	}
	protected TreeViewer findTreeViewer(String name){
		return (TreeViewer) XWT.findElementByName(control, name);
	}
	protected Composite findComposite(String name){
		return (Composite) XWT.findElementByName(control, name);
	}
	protected Spinner findSpinner(String name){
		return (Spinner) XWT.findElementByName(control, name);
	}
}
