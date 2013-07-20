package com.leocarbonate.cu;

import com.leocarbonate.cu.utilities.ActionHandler;
import com.leocarbonate.cu.models.AverageColor;
import com.leocarbonate.cu.models.DigitalEyedropper;
import com.leocarbonate.cu.models.InvertColor;
import com.leocarbonate.cu.models.RandomColor;
import com.leocarbonate.cu.models.ScrollColor;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class ColorUtility{
    //The actual window
    public static JFrame frame;
    public static JTabbedPane pane;
    
    public static JPanel body;
    public static JPanel options;
    
    public static JPanel top; //For future implementations
    
    //Package-wide objects
    public static JColorChooser cc;
    public static JCheckBox cmtoggle;
    
    public static JColorChooser ccc;
    public static JCheckBox ccmtoggle;
    
    public static JPanel evpanel;
    public static JLabel evr = new JLabel("Loading...",JLabel.CENTER);
    public static JLabel evg = new JLabel("Loading...",JLabel.CENTER);
    public static JLabel evb = new JLabel("Loading...",JLabel.CENTER);
    public static JLabel evh = new JLabel("Loading...",JLabel.CENTER);
    public static JLabel evha = new JLabel("Loading...",JLabel.CENTER);
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    public ColorUtility() throws AWTException, InterruptedException{
        //Initialize GridBagConstraints
        GridBagConstraints c = new GridBagConstraints();
        if (RIGHT_TO_LEFT) {
            ColorUtility.evpanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            ColorUtility.pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        if(shouldFill) {
            c.fill = GridBagConstraints.BOTH;
        }
        
        //Initialize the window
        frame = new JFrame("Color Utility");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960,480);
        frame.setResizable(true);
        
        //Initialize the body
        body = new JPanel(new GridBagLayout());
        
        //Initialize the body
        options = new JPanel(new GridBagLayout());
        
        //Initialize the top panel
        top = new JPanel(new BorderLayout());
        
        //Initialize the tabbed pane
        pane = new JTabbedPane();
        
        //Add plafComponents
        
        /* Temporarily disabled
        LookAndFeel plafComponents = new LookAndFeel();
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.2;
        c.weighty = 0.2;
        options.add(plafComponents,c);
        */
        
        //ColorChooser (JColorChooser)
        cmtoggle = new JCheckBox("Color Mixer",true);
        Border cmborder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Mixer");
        final JPanel cm = new JPanel(new BorderLayout());
        cm.setBorder(cmborder);
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.2;
        c.weighty = 0.2;
        c.ipadx = 0;
        c.ipady = 0;
        body.add(cm,c);
        
        //CustomColorChooser (JColorChooser)
        ccmtoggle = new JCheckBox("Color Mixer",true);
        Border ccmborder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Functions");
        final JPanel ccm = new JPanel(new BorderLayout());
        ccm.setBorder(ccmborder);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.2;
        c.weighty = 0.2;
        c.ipadx = 0;
        c.ipady = 0;
        body.add(ccm,c);
        
        //Easyview
        final JCheckBox evtoggle = new JCheckBox("Easy View",true);
        Border evborder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View");
        evpanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.ipadx = 550;
        c.ipady = 225;
        evpanel.setBorder(evborder);
        body.add(evpanel,c);
        
        
        //Controls
        Border controlborder = BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Show or Hide Panels");
        final JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEADING));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.ipadx = 20;
        c.ipady = 20;
        options.add(controls,c);
        
        controls.setBorder(controlborder);
        controls.add(ccmtoggle);
        controls.add(evtoggle);
        controls.add(cmtoggle);
        
        Random random = new Random();
        cc = new JColorChooser(new Color((random.nextFloat()),random.nextFloat(),random.nextFloat()));
        ccc = new JColorChooser(new Color((random.nextFloat()),random.nextFloat(),random.nextFloat()));
        
        AbstractColorChooserPanel[] newPanels = {
            new InvertColor(), new RandomColor(), new ScrollColor(), new DigitalEyedropper(), new AverageColor()
        };
        
        ccc.setChooserPanels(newPanels);
        ccc.setPreviewPanel(new JPanel());
        cm.add(cc,BorderLayout.CENTER);
        ccm.add(ccc,BorderLayout.CENTER);
        
        
        Easyview.updateev(cc.getColor());
        Easyview.initGridBagConstraints();
        
        ActionHandler.createColorMixerChangeListener();
        ActionHandler.createController(ccm,ccmtoggle);
        ActionHandler.createController(evpanel,evtoggle);
        ActionHandler.createController(cm,cmtoggle);
        
        pane.addTab("Body",body);
        pane.addTab("Settings",options);
        pane.addTab("About",new About());
        
        Border current = pane.getBorder();
        Border empty = new EmptyBorder(10,10,10,10);
        if (current == null){
            pane.setBorder(empty);
        } else{
            pane.setBorder(new CompoundBorder(empty, current));
        }
        
        top.add(pane, BorderLayout.CENTER);
        
        frame.add(top);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws AWTException, InterruptedException {
        ColorUtility cu = new ColorUtility();;
    }   
}

