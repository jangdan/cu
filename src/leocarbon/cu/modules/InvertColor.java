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
import static leocarbon.cu.ColorUtility.RB;
import static leocarbon.cu.ColorUtility.cc;
import static leocarbon.cu.ColorUtility.dologging;
import org.apache.log4j.Logger;

public class InvertColor extends AbstractColorChooserPanel implements ActionListener {
    JButton invert;
    JButton rinvert, ginvert, binvert;
    JPanel invertPanel;
    
    int r, g, b;
    
    @Override
    public void updateChooser() {}

    @Override
    protected void buildChooser() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        invertPanel = new JPanel(new GridBagLayout());
        //invertPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Invert"));
        
        invert = new JButton(RB.getString("IC.invert"));
        invert.setActionCommand("invert");
        invert.addActionListener(this);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 3;
        invertPanel.add(invert,c);
        
        rinvert = new JButton(RB.getString("IC.rinvert"));
        rinvert.setActionCommand("rinvert");
        rinvert.addActionListener(this);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        invertPanel.add(rinvert,c);
        
        ginvert = new JButton(RB.getString("IC.ginvert"));
        ginvert.setActionCommand("ginvert");
        ginvert.addActionListener(this);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        invertPanel.add(ginvert,c);
        
        binvert = new JButton(RB.getString("IC.binvert"));
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
        return RB.getString("IC");
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
        r = cc.getColor().getRed();
        g = cc.getColor().getGreen();
        b = cc.getColor().getBlue();
        switch(AE.getActionCommand()){
            case "invert":
                cc.setColor(new Color(255 - r, 255 - g, 255 - b));
                if(dologging) Logger.getLogger(InvertColor.class.getName()).trace("Inverted Color");
                break;
            case "rinvert":
                cc.setColor(new Color(255 - r, g, b));
                if(dologging) Logger.getLogger(InvertColor.class.getName()).trace("Inverted red channel");
                break;
            case "ginvert":
                cc.setColor(new Color(r, 255 - g, b));
                if(dologging) Logger.getLogger(InvertColor.class.getName()).trace("Inverted green channel");
                break;
            case "binvert":
                cc.setColor(new Color(r, g, 255 - b));
                if(dologging) Logger.getLogger(InvertColor.class.getName()).trace("Inverted blue channel");
                break;
        }
    }
    
}
