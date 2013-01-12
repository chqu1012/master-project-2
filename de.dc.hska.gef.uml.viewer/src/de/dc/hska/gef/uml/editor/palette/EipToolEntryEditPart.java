package de.dc.hska.gef.uml.editor.palette;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.internal.ui.palette.editparts.ToolEntryEditPart;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.PaletteEntry;

import de.dc.hska.gef.uml.model.UML;

public class EipToolEntryEditPart extends ToolEntryEditPart {

 	public EipToolEntryEditPart( PaletteEntry paletteEntry ) {
		super( paletteEntry );
	}

 	@Override
	protected IFigure createToolTip() {
 		IFigure result = null;
		if( getModel() instanceof CombinedTemplateCreationEntry ) {
 			Object tpl = ((CombinedTemplateCreationEntry) getModel()).getTemplate();
			if( tpl instanceof UML )
				result = new EipPaletteTooltipFigure((UML) tpl);
			else
				result = super.createToolTip();
		}
		else
			result = super.createToolTip();

 		return result;
	}
}