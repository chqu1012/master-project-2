package de.dc.hska.gef.uml.editor.palette;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.CompoundBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

import de.dc.hska.gef.uml.model.UML;

public class EipPaletteTooltipFigure extends Figure {

 	public EipPaletteTooltipFigure( UML uml ) {

 		setLayoutManager( new ToolbarLayout());
		setBackgroundColor( ColorConstants.white );
		setOpaque( true );
		setBorder( new CompoundBorder(
					new LineBorder( ColorConstants.black, 2 ),
					new MarginBorder( 12 )));

 		FontData fontData = new FontData( "Arial", 12, SWT.BOLD );
		Font font = new Font( Display.getDefault(), fontData );

 		Label l = new Label( uml.getName());
		l.setFont( font );
		l.setLabelAlignment( PositionConstants.LEFT );
		l.setBorder( new MarginBorder( 0, 4, 12, 4 ));
		add( l );

 		l = new Label( uml.getName());
		l.setBorder( new MarginBorder( 4, 4, 6, 4 ));
		add( l );
	}
}