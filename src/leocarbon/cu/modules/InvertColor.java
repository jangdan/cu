package leocarbon.cu.modules;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import static leocarbon.cu.ColorUtility.CU;
import leocarbon.cu.GUI;
import org.apache.log4j.Logger;

public class InvertColor extends AbstractColorChooserPanel implements ActionListener {
    JButton invert;
    JButton rinvert, ginvert, binvert;
    JPanel invertPanel;
    
    int r, g, b;
    
    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = GUI.initGridBagConstraints();
        
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
    public void actionPerformed(ActionEvent AE) {
        r = CU.cc.getColor().getRed();
        g = CU.cc.getColor().getGreen();
        b = CU.cc.getColor().getBlue();
        if("invert".equals(AE.getActionCommand())){
            Logger.getLogger(InvertColor.class.getName()).trace("Requested invertion of color: ");
            
            CU.cc.setColor(new Color(255 - r, 255 - g, 255 - b));
            
            Logger.getLogger(InvertColor.class.getName()).trace("Done");
        } else if("rinvert".equals(AE.getActionCommand())){
            Logger.getLogger(InvertColor.class.getName()).trace("Requested invertion of red: ");
            
            CU.cc.setColor(new Color(255 - r, g, b));
            
            Logger.getLogger(InvertColor.class.getName()).trace("Done");
        } else if("ginvert".equals(AE.getActionCommand())){
            Logger.getLogger(InvertColor.class.getName()).trace("Requested invertion of green: ");
            
            CU.cc.setColor(new Color(r, 255 - g, b));
            
            Logger.getLogger(InvertColor.class.getName()).trace("Done");
        } else if("binvert".equals(AE.getActionCommand())){
            Logger.getLogger(InvertColor.class.getName()).trace("Requested invertion of blue: ");
            
            CU.cc.setColor(new Color(r, g, 255 - b));
            
            Logger.getLogger(InvertColor.class.getName()).trace("Done");
        }
    }
    
}
