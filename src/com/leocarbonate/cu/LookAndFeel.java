package com.leocarbonate.cu;

import com.leocarbonate.cu.utilities.OSValidator;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LookAndFeel extends JPanel{
    public LookAndFeel(){
        /* Under construction
        this.setLayout(new GridLayout(2,0));
        
        Border plafBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Look and Feel");
        this.setBorder(plafBorder);
        final UIManager.LookAndFeelInfo[] plafInfos = UIManager.getInstalledLookAndFeels();
        String[] plafNames = new String[plafInfos.length];
        for (int ii=0; ii<plafInfos.length; ii++){
            plafNames[ii] = plafInfos[ii].getName();
        }
        final JComboBox plafChooser = new JComboBox(plafNames);
        this.add(plafChooser);
        
        final JCheckBox pack = new JCheckBox("JFrame.pack() on change", true);
        this.add(pack);
        
        final JSlider brightness = new JSlider(0,255);
        this.add(brightness);
        final JLabel brightnessLabel = new JLabel("Window Background Color: ",JLabel.CENTER);
        
        
        plafChooser.addActionListener( new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = plafChooser.getSelectedIndex();
                try{
                    UIManager.setLookAndFeel(plafInfos[index].getClassName());
                    SwingUtilities.updateComponentTreeUI(ColorUtility.pane);
                    if(pack.isSelected()){
                        ColorUtility.frame.pack();
                    }
                } catch(Exception e) {
                }
            }
        });
        
        brightness.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
               System.out.println(brightness.getValue());
               ColorUtility.frame.setBackground(new Color(brightness.getValue(),brightness.getValue(),brightness.getValue()));
            }
        });
        * */
    }
}
