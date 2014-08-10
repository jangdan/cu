/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package leocarbon.cu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static leocarbon.cu.ColorUtility.CU;
import leocarbon.cu.handlers.ActionHandler;
import org.apache.log4j.Logger;

/**
 *
 * @author Leocarbon
 */
public class Options extends JFrame {
    public static JPanel newBrightnessController() {
        GridBagConstraints c = GUI.initGridBagConstraints();
        
        JPanel brightnessPanel = new JPanel(new GridBagLayout());
        
        final JLabel Displaybrightness = new JLabel("Current brightness: 232");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.09;
        c.weighty = 0.09;
        brightnessPanel.add(Displaybrightness,c);
        
        final JSlider brightness = new JSlider(91,255,232);
        //brightness.setMajorTickSpacing(24);
        brightness.setMinorTickSpacing(41);
        brightness.setPaintTicks(true);
        brightness.setPaintLabels(true);
        brightness.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent CE) {
                System.out.println("Changed background color to: " + brightness.getValue());
                Displaybrightness.setText("Current brightness: " + brightness.getValue());
                CU.setBackground(new Color(brightness.getValue(),brightness.getValue(),brightness.getValue()));
            }
        });
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1;
        c.weighty = 1;
        brightnessPanel.add(brightness,c);
        
        JButton brightnessReset = new JButton("Reset background color");
        brightnessReset.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent AE){
                brightness.setValue(232);
            }
        });
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.09;
        c.weighty = 0.09;
        brightnessPanel.add(brightnessReset,c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        brightnessPanel.add(new JLabel("<html><font color = 'red'><b>Note: </b></font><i>This function might not work for some Look and Feels.</i></html>"),c);
        Border brightnessBorder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Background");
        brightnessPanel.setBorder(brightnessBorder);
        
        return brightnessPanel; 
    }
    
    public static JPanel visibility(){
        Border controlborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Component Visibility");
        final JPanel controls = new JPanel(new GridLayout(0,1));
        controls.setBorder(controlborder);
        
        JPanel ccmpanel = new JPanel();
        ccmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Functions"));
        ccmpanel.add(CU.ccmtoggle);
        controls.add(ccmpanel);
        
        JPanel evpanel = new JPanel();
        evpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View"));
        
        evpanel.add(CU.evtoggle);
        
        JCheckBox texttoggle = new JCheckBox("Show Color Values", true);
        ActionHandler.createEasyViewTextController(texttoggle);
        evpanel.add(texttoggle);
        
        controls.add(evpanel);
        
        JPanel cmpanel = new JPanel();
        cmpanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Picker"));
        cmpanel.add(CU.cmtoggle);
        controls.add(cmpanel);
        
        return controls;
    }
    
    public static JPanel LookAndFeel(){
        JPanel LAF = new JPanel();
        LAF.setLayout(new GridLayout(2,0));
        
        Border plafBorder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Look and Feel");
        LAF.setBorder(plafBorder);
        
        final UIManager.LookAndFeelInfo[] ILAFs = UIManager.getInstalledLookAndFeels();
        String[] ILAFNames = new String[ILAFs.length];
        
        for(int a = 0; a < ILAFs.length; ++a) ILAFNames[a] = ILAFs[a].getName();
        final JComboBox LAFJC = new JComboBox(ILAFNames);
        
        LAF.add(LAFJC);
        
        final JCheckBox pack = new JCheckBox("JFrame.pack() on change", true);
        LAF.add(pack);
        
        LAFJC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent AE) {
                int index = LAFJC.getSelectedIndex();
                try {
                    UIManager.setLookAndFeel(ILAFs[index].getClassName());
                    SwingUtilities.updateComponentTreeUI(CU);
                    
                    CU.setVisible(false);
                    CU.dispose();
                    CU = null;
                    System.gc();
                    
                    ColorUtility.CU = new ColorUtility();
                    if(pack.isSelected()) CU.pack();
                    
                    if(UIManager.getLookAndFeel().getClass().getName().equals(ILAFs[index].getClassName())) Logger.getLogger(ColorUtility.class.getName()).info("Changed Look and Feel to: " + UIManager.getLookAndFeel());
                } catch (ClassNotFoundException CNFE){
                    Logger.getLogger(ColorUtility.class.getName()).error(CNFE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (InstantiationException  IE){
                    Logger.getLogger(ColorUtility.class.getName()).error(IE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (IllegalAccessException IAE){
                    Logger.getLogger(ColorUtility.class.getName()).error(IAE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                } catch (UnsupportedLookAndFeelException ULAFE){
                    Logger.getLogger(ColorUtility.class.getName()).error(ULAFE);
                    Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+ILAFs[index].getClassName());
                }
            }
        });
        return LAF;
    }
    
    public static JPanel logging() {
        JPanel L = new JPanel();
        
        return L;
    }
    
    public Options(){
        setTitle("Color Utility Options");
        setLayout(new GridBagLayout());
        //Initialize GridBagConstraints
        GridBagConstraints c = GUI.initGridBagConstraints();

        //Add plafComponents
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.2;
        c.weighty = 0.2;
        add(LookAndFeel(),c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipadx = 20;
        c.ipady = 20;
        add(visibility(),c);
        
        setAlwaysOnTop(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
