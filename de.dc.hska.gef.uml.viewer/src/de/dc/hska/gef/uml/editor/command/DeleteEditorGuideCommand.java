package de.dc.hska.gef.uml.editor.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import de.dc.hska.gef.uml.editor.ruler.ElementBase;
import de.dc.hska.gef.uml.editor.ruler.Guide;
import de.dc.hska.gef.uml.editor.ruler.Ruler;

public class DeleteEditorGuideCommand extends Command {  
  
    /** 
     * ?? 
     */  
    private Ruler parent;  
    /** 
     * ?? 
     */  
    private Guide guide;  
    /** 
     * ??????? 
     */  
    private Map oldParts;  
  
    public DeleteEditorGuideCommand(Guide guide, Ruler parent) {  
        super("Delete guide");  
        this.guide = guide;  
        this.parent = parent;  
    }  
  
    public boolean canUndo() {  
        return true;  
    }  
  
    public void execute() {  
        oldParts = new HashMap(guide.getMap());  
        Iterator iter = oldParts.keySet().iterator();  
        while (iter.hasNext()) {  
            guide.detachElement((ElementBase) iter.next());  
        }  
        parent.removeGuide(guide);  
    }  
  
    public void undo() {  
        parent.addGuide(guide);  
        Iterator iter = oldParts.keySet().iterator();  
        while (iter.hasNext()) {  
            ElementBase element = (ElementBase) iter.next();  
            guide.attachElement(element, ((Integer) oldParts.get(element)).intValue());  
        }  
    }  
}  