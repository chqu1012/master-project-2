package de.dc.hska.gef.uml.editor.command;

import java.util.Iterator;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import de.dc.hska.gef.uml.editor.ruler.ElementBase;
import de.dc.hska.gef.uml.editor.ruler.Guide;

public class MoveEditorGuideCommand extends Command {  
  
    /**????*/  
    private int pDelta;  
    /**??*/  
    private Guide guide;  
  
    public MoveEditorGuideCommand(Guide guide, int positionDelta) {  
        super("Move guide");  
        this.guide = guide;  
        pDelta = positionDelta;  
    }  
  
    public void execute() {  
        guide.setPosition(guide.getPosition() + pDelta);  
        Iterator iter = guide.getParts().iterator();  
        while (iter.hasNext()) {  
            ElementBase element = (ElementBase) iter.next();  
            Rectangle layout = element.getLayout().getCopy();  
            if (guide.isHorizontal()) {  
                layout.y += pDelta;  
            } else {  
                layout.x += pDelta;  
            }  
            element.setLayout(layout);  
        }  
    }  
  
    public void undo() {  
        guide.setPosition(guide.getPosition() - pDelta);  
        Iterator iter = guide.getParts().iterator();  
        while (iter.hasNext()) {  
            ElementBase element = (ElementBase) iter.next();  
            Rectangle layout = element.getLayout().getCopy();  
            if (guide.isHorizontal()) {  
                layout.y -= pDelta;  
            } else {  
                layout.x -= pDelta;  
            }  
            element.setLayout(layout);  
        }  
    }  
  
}  