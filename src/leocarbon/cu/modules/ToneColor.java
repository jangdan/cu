package leocarbon.cu.modules;

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

public class ToneColor extends AbstractColorChooserPanel implements ActionListener{
    JButton Darken;
    JButton Lighten;
    JPanel TonePanel;
    
    @Override
    public void updateChooser() {
        
    }

    @Override
    protected void buildChooser() {
        GridBagConstraints c = GUI.initGridBagConstraints();
        
        TonePanel = new JPanel(new GridBagLayout());
        //TonePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Tone Color"));
        
        Lighten = new JButton("Brighten color");
        Lighten.setActionCommand("lighten");
        Lighten.addActionListener(this);
        c.gridy = 1;
        TonePanel.add(Lighten,c);
        
        Darken = new JButton("Darken color");
        Darken.setActionCommand("darken");
        Darken.addActionListener(this);
        c.gridy = 0;
        TonePanel.add(Darken,c);
        
        add(TonePanel);
    }

    @Override
    public String getDisplayName() {
        return "Color Toner";
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
         if("darken".equals(AE.getActionCommand())){
            Logger.getLogger(ToneColor.class.getName()).trace("Requested darkening of color: ");
         
            CU.cc.setColor(CU.cc.getColor().darker());
            
            Logger.getLogger(ToneColor.class.getName()).trace("Done");
        }  else if("lighten".equals(AE.getActionCommand())){
            Logger.getLogger(ToneColor.class.getName()).trace("Requested brightening of color: ");
         
            CU.cc.setColor(CU.cc.getColor().brighter());
            
            Logger.getLogger(ToneColor.class.getName()).trace("Done");
        }
    }
}
