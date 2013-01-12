package de.dc.hska.gef.uml.editor.ruler;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
    public class ElementBase implements IAdaptable {  
      
        /** 
         *  
         */  
        private static final long serialVersionUID = 1L;  
      
        /** 
         * ?? 
         */  
        private Rectangle layout;  
      
        /** 
         * ???? 
         *  
         * @return 
         */  
        public Rectangle getLayout() {  
            return layout;  
        }  
      
        public void setLayout(Rectangle layout) {  
            this.layout = layout;  
        }  
      
        /** 
         * ????????? 
         */  
        private Guide verticalGuide, horizontalGuide;  
      
        public Guide getVerticalGuide() {  
      
            return verticalGuide;  
      
        }  
      
        /** 
         * ?????? 
         *  
         * @param verticalGuide 
         */  
        public void setVerticalGuide(Guide verticalGuide) {  
      
            this.verticalGuide = verticalGuide;  
      
        }  
      
        public Guide getHorizontalGuide() {  
      
            return horizontalGuide;  
      
        }  
      
        public void setHorizontalGuide(Guide horizontalGuide) {  
      
            this.horizontalGuide = horizontalGuide;  
      
        }  
      
        public Object getAdapter(Class adapter) {  
            return null;  
        }  
}