package com.leocarbonate.cu.utilities;

import com.leocarbonate.cu.ColorUtility;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;

public class Functions {
    //Contstants
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public GridBagConstraints initGridBagContstraints(){
        GridBagConstraints c = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            ColorUtility.evpanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            ColorUtility.pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }
        return c;
    }
    
}
