package com.leocarbonate.cu.models;

import com.leocarbonate.cu.ColorUtility;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class InvertColor extends AbstractColorChooserPanel implements ActionListener{
    JButton invert;
    JButton rinvert, ginvert, binvert;
    JPanel invertPanel;
    
    int r, g, b;
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            invertPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }
        
        invertPanel = new JPanel(new GridBagLayout());
        //invertPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Invert"));
        
        invert = new JButton("Invert Color");
        invert.setActionCommand("invert");
        invert.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        invertPanel.add(invert,c);
        
        rinvert = new JButton("Invert Red");
        rinvert.setActionCommand("rinvert");
        rinvert.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        invertPanel.add(rinvert,c);
        
        ginvert = new JButton("Invert Green");
        ginvert.setActionCommand("ginvert");
        ginvert.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        invertPanel.add(ginvert,c);
        
        binvert = new JButton("Invert Blue");
        binvert.setActionCommand("binvert");
        binvert.addActionListener(this);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        invertPanel.add(binvert,c);
        
        add(invertPanel);
    }

    @Override
    public String getDisplayName() {
        return "Invert Color";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        r = ColorUtility.cc.getColor().getRed();
        g = ColorUtility.cc.getColor().getGreen();
        b = ColorUtility.cc.getColor().getBlue();
        if("invert".equals(e.getActionCommand())){
            System.out.print("Requested invertion of color: ");
            
            ColorUtility.cc.setColor(new Color(255 - r, 255 - g, 255 - b));
            
            System.out.println("Done");
        } else if("rinvert".equals(e.getActionCommand())){
            System.out.print("Requested invertion of red: ");
            
            ColorUtility.cc.setColor(new Color(255 - r, g, b));
            
            System.out.println("Done");
        } else if("ginvert".equals(e.getActionCommand())){
            System.out.print("Requested invertion of green: ");
            
            ColorUtility.cc.setColor(new Color(r, 255 - g, b));
            
            System.out.println("Done");
        } else if("binvert".equals(e.getActionCommand())){
            System.out.print("Requested invertion of blue: ");
            
            ColorUtility.cc.setColor(new Color(r, g, 255 - b));
            
            System.out.println("Done");
        }
    }
    
}
