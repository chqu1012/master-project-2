package de.dc.hska.gef.uml.editor;

import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.PaletteEditPartFactory;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import de.dc.hska.gef.uml.editor.palette.EipToolEntryEditPart;
import de.dc.hska.gef.uml.editor.ruler.EditorRulerProvider;
import de.dc.hska.gef.uml.editor.ruler.Ruler;
import de.dc.hska.gef.uml.model.ModelProvider;
import de.dc.hska.gef.uml.model.UMLContainer;
import de.dc.hska.gef.uml.model.part.AppEditPartFactory;
import de.dc.hska.gef.uml.model.part.AppTreeEditPartFactory;
import de.dc.hska.gef.uml.palette.UMLGraphicalEditorPalette;

public class UMLGraphicalEditor extends GraphicalEditorWithPalette {
	public static final String ID = "de.dc.hska.gef.projektarbeit.uml.editor";
	private RulerComposite rulerComp;

	private boolean enableRuler = true;
	
	public UMLGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}
	
	@Override
	protected void createGraphicalViewer(Composite parent) {
		if(enableRuler){
			rulerComp = new RulerComposite(parent, SWT.NONE);
			super.createGraphicalViewer(rulerComp);
			rulerComp
					.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
			configureRuler();
		}else
			super.createGraphicalViewer(parent);
	}

	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());

		double[] zoomLevels;
		ArrayList<String> zoomContributions;
		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0,
				4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);

		initKeyBinding(viewer);
	}

	public Object getAdapter(Class type) {
		if (type == ZoomManager.class)
			return ((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		}
		return super.getAdapter(type);
	}

	private void initKeyBinding(GraphicalViewer viewer) {
		keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);
		viewer.setKeyHandler(keyHandler);

	}

	protected void initializeGraphicalViewer() {
		GraphicalViewer viewer = getGraphicalViewer();
		// model = createUML();
		model = ModelProvider.INSTANCE.getModel();
		viewer.setContents(ModelProvider.INSTANCE.getModel());

		((FigureCanvas)viewer.getControl()).setBackground(ColorConstants.tooltipBackground);
		
		ScalableRootEditPart editPart = (ScalableRootEditPart)viewer.getRootEditPart();
		final Viewport viewPort = (Viewport)editPart.getFigure();
		viewPort.getUpdateManager().performUpdate();
		
		viewer.reveal(viewer.getContents());
		
		getEditorSite().setSelectionProvider(getGraphicalViewer());
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().addSelectionListener(this);
	
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));
		getGraphicalViewer().addDropTargetListener(new TemplateTransferDropTargetListener(getGraphicalViewer()));
		
		getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED,true);
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_ENABLED,true);
		getGraphicalViewer().setProperty(SnapToGrid.PROPERTY_GRID_VISIBLE,true);
	}

	public void refresh() {
		model = ModelProvider.INSTANCE.getModel();
		getGraphicalViewer().setContents(ModelProvider.INSTANCE.getModel());
	}

	private void configureRuler() {
		GraphicalViewer viewer = getGraphicalViewer();

		if(enableRuler){
		viewer.setProperty(RulerProvider.PROPERTY_VERTICAL_RULER,
				new EditorRulerProvider(new Ruler(false)));
		viewer.setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER,
				new EditorRulerProvider(new Ruler(true)));
		viewer.setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, true);

		IAction action = new ToggleRulerVisibilityAction(getGraphicalViewer());
		getActionRegistry().registerAction(action);
		}
	}

	// public UMLContainer createUML() {
	// UMLContainer container = new UMLContainer();
	//
	// List<String> methods = new ArrayList<>();
	// methods.add("setName");
	// methods.add("getName");
	// methods.add("setAge");
	// methods.add("getAge");
	// List<String> attributes = new ArrayList<String>();
	// attributes.add("name");
	// attributes.add("age");
	// String className = "Person";
	// Point pos = new Point(10, 10);
	// container.addChild(createClass(pos, className, attributes, methods));
	//
	// List<String> m1 = new ArrayList<>();
	// m1.add("setName");
	// m1.add("getName");
	// List<String> a1 = new ArrayList<String>();
	// a1.add("name");
	// String className1 = "Group";
	// Point pos1 = new Point(300, 300);
	// container.addChild(createClass(pos1, className1, a1, m1));
	//
	// return container;
	// }

	// private UML createClass(Point pos, String className,
	// List<String> attributes, List<String> methods) {
	// UML uml = new UML();
	// uml.setLayout(new Rectangle(pos.x, pos.y, 200, 200));
	// uml.setName(className);
	// uml.setAttributes(attributes);
	// uml.setMethods(methods);
	// return uml;
	// }

	private UMLContainer model;
	private KeyHandler keyHandler;

	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;

		public OutlinePage() {
			super(new TreeViewer());
		}

		public void init(IPageSite pageSite) {
			super.init(pageSite);
			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();
			getViewer().setKeyHandler(keyHandler);
		}

		public Control getControl() {
			return sash;
		}

		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			model = ModelProvider.INSTANCE.getModel();
			getViewer().setContents(model);
			getSelectionSynchronizer().addViewer(getViewer());

			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed())
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);
			super.dispose();
		}
	}

	@Override
	protected PaletteRoot getPaletteRoot() {
		return new UMLGraphicalEditorPalette();
	}

	// @Override
	// protected PaletteRoot getPaletteRoot() {
	// PaletteRoot root = new PaletteRoot();
	//
	// PaletteGroup manipGroup = new PaletteGroup("UML Werzeug");
	// root.add(manipGroup);
	// SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
	// manipGroup.add(selectionToolEntry);
	// manipGroup.add(new MarqueeToolEntry());
	// root.setDefaultEntry(selectionToolEntry);
	//
	// PaletteSeparator sep2 = new PaletteSeparator();
	// root.add(sep2);
	// PaletteGroup instGroup = new PaletteGroup("Klassen erstellen");
	//
	// root.add(instGroup);
	// instGroup.add(new CreationToolEntry("Klasse",
	// "Klasse erstellen", new NodeCreationFactory(
	// UML.class), null, null));
	// root.setDefaultEntry(selectionToolEntry);
	// return root;
	// }

}
