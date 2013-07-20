package com.leocarbonate.cu.utilities;

import com.leocarbonate.cu.ColorUtility;
import com.leocarbonate.cu.Easyview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ActionHandler {
    public static void createController(final JPanel inpanel, final JCheckBox input){
        input.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(input.isSelected()){
                    inpanel.setVisible(true);
                    
                    ColorUtility.ccmtoggle.setEnabled(true);
                    ColorUtility.cmtoggle.setEnabled(true);
                    
                    ColorUtility.frame.pack();
                }
                else{
                    inpanel.setVisible(false);
                    
                    if(ColorUtility.cmtoggle.equals(e.getSource())){
                        ColorUtility.ccmtoggle.setEnabled(false);
                        System.out.println("ccmtogggle Disabled");
                    } else if(ColorUtility.ccmtoggle.equals(e.getSource())){
                        ColorUtility.cmtoggle.setEnabled(false);
                        System.out.println("cmtogggle Disabled");
                    }
                    
                    ColorUtility.frame.pack();
                }
            }
        });
    }
    
    public static void createColorMixerChangeListener(){
        ColorSelectionModel model = ColorUtility.cc.getSelectionModel();
        ChangeListener changeListener = new ChangeListener() {
            public void stateChanged(ChangeEvent changeEvent) {
                Easyview.updateev(ColorUtility.cc.getColor());
            }
        };
        model.addChangeListener(changeListener);
    }
}