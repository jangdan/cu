package leocarbon.cu;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import static leocarbon.cu.Easyview.createEasyview;
import static leocarbon.cu.Easyview.updateEv;
import static leocarbon.cu.GUI.constructFonts;
import static leocarbon.cu.GUI.createmenu;
import static leocarbon.cu.GUI.initGridBagConstraints;
import leocarbon.cu.handlers.ActionHandler;
import leocarbon.cu.modules.AverageColor;
import leocarbon.cu.modules.DigitalEyedropper;
import leocarbon.cu.modules.InvertColor;
import leocarbon.cu.modules.RandomColor;
import leocarbon.cu.modules.ScrollColor;
import leocarbon.cu.modules.ToneColor;
import leocarbon.cu.system.GetOSName;
import leocarbon.cu.system.MacOSXHandler;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simplericity.macify.eawt.Application;
import org.simplericity.macify.eawt.DefaultApplication;

public class ColorUtility extends JFrame {
    public static ColorUtility CU;
    private static Application MacOSCU;
    
    public static About A;
    public static Options O;
    //Menu bar
    public static JMenuBar menubar;
    public static JMenu menu;
    public static String[] menuNames = {
        "File", "Edit", "Modules", "Window", "Help"
    };
    public static JMenuItem menuitem;
        public static String[] FilemenuitemNames = {
            "Options"
        }; public static String[] EditmenuitemNames = {
            "Copy Hex Value", "Copy AHex Value", /*"Copy RGB Value", */"Copy RGBA Value", "/", "Paste Hex Value", /*"Paste AHex Value", "Paste RGB Value", */"Paste RGBA Value", "/", "Undo", "Redo", "/", 
            "Invert RGB", "Invert Red", "Invert Green", "Invert Blue", "/", 
            "Brighten", "Darken"
        }; public static String[] ModulesmenuitemNames = {
        }; public static String[] WindowmenuitemNames = {
            "Color Mixer", "Color Functions", "EasyView", "/", "Reset Windows"
        }; public static String[] HelpmenuitemNames = {
            "About"
        };
    public static JRadioButtonMenuItem rbmenuitem;
    public static JCheckBoxMenuItem cbmenuitem;
    
    //Global objects
    public static JColorChooser cc;
    public JCheckBox cmtoggle;
    
    public static JColorChooser ccc;
    public JCheckBox ccmtoggle;
    
    public static JPanel evpanel;
    public static Easyview Ev;
    public JCheckBox evtoggle;
    
    public InvertColor IC = new InvertColor();
    public RandomColor RC = new RandomColor();
    public ScrollColor SC = new ScrollColor();
    public DigitalEyedropper DEyed = new DigitalEyedropper();
    public AverageColor AC = new AverageColor();
    public ToneColor TC = new ToneColor();
        
    public static void main(String[] args) throws AWTException, InterruptedException {
        if("mac".equals(GetOSName.formatted())){
            Application macinstance = new DefaultApplication();
            MacOSCU = macinstance;
            
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Color Utility");
        }
        CU = new ColorUtility();
    } public ColorUtility() {
        //Configure the logger (Apache log4j)
        PropertyConfigurator.configure(getClass().getResource("/leocarbon/cu/config/log4j.properties"));
        Logger.getLogger(ColorUtility.class.getName()).trace("rootlogger configured");
        
        Logger.getLogger(ColorUtility.class.getName()).info("Operating System: " + System.getProperty("os.name"));
        Logger.getLogger(ColorUtility.class.getName()).info("Java vendor: " + System.getProperty("java.vendor"));
        Logger.getLogger(ColorUtility.class.getName()).info("Java vendor URL: ["+System.getProperty("java.vendor.url")+"]");
        Logger.getLogger(ColorUtility.class.getName()).info("Java version: " + System.getProperty("java.version"));
        Logger.getLogger(ColorUtility.class.getName()).info("Look and Feel: " + UIManager.getSystemLookAndFeelClassName());
        
        //Set Look and Feel to the operating system's native Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if(UIManager.getLookAndFeel().getClass().getName().equals(UIManager.getSystemLookAndFeelClassName())) Logger.getLogger(ColorUtility.class.getName()).trace("Set Look and Feel to "+UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException CNFE){
            Logger.getLogger(ColorUtility.class.getName()).error(CNFE);
            Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException  IE){
            Logger.getLogger(ColorUtility.class.getName()).error(IE);
            Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException IAE){
            Logger.getLogger(ColorUtility.class.getName()).error(IAE);
            Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException ULAFE){
            Logger.getLogger(ColorUtility.class.getName()).error(ULAFE);
            Logger.getLogger(ColorUtility.class.getName()).trace("Could not set Look and Feel to "+UIManager.getSystemLookAndFeelClassName());
        }
        
        //Extra implementations for Mac OS
        if("mac".equals(GetOSName.formatted())){
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Color Utility");
     
            MacOSCU.addApplicationListener(new MacOSXHandler());
            MacOSCU.addPreferencesMenuItem();
            MacOSCU.setEnabledPreferencesMenu(true);
            Logger.getLogger(ColorUtility.class.getName()).trace("Optimized GUI for Mac OS");
        }
        
        constructFonts();
        
        //Initialize GridBagConstraints
        GridBagConstraints c = initGridBagConstraints();
        
        //Initialize the window
        setTitle("Color Utility");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        //Initialize the menubar and its menus
        menubar = new JMenuBar();
        
        menu = new JMenu("File"); {
            int[] FilemenuitemAccelerators = {
                KeyEvent.VK_O
            };
            menubar.add(createmenu("File", FilemenuitemNames, FilemenuitemAccelerators));
        } menu = new JMenu("Edit"); {
            int[] EditmenuitemAccelerators = {
                KeyEvent.VK_C, -1, -1, -1, KeyEvent.VK_V, -1, -1, KeyEvent.VK_Z, KeyEvent.VK_Y, -1,
                KeyEvent.VK_I, -1, -1, -1, -1,
                KeyEvent.VK_B, KeyEvent.VK_D
            };
            menubar.add(createmenu("Edit", EditmenuitemNames, EditmenuitemAccelerators));
        } menu = new JMenu("Modules"); {
            AverageColor a = new AverageColor(); DigitalEyedropper d = new DigitalEyedropper(); RandomColor r = new RandomColor(); ScrollColor s = new ScrollColor();
            ModulesmenuitemNames = new String[]{
                a.getDisplayName(), d.getDisplayName(), r.getDisplayName(), s.getDisplayName()
            };
            a = null; d = null; r = null; s = null;
            System.gc();

            int[] ModulesmenuitemAccelerators = {
                KeyEvent.VK_A, KeyEvent.VK_E,KeyEvent.VK_R, KeyEvent.VK_S
            };
            menubar.add(createmenu("Modules", ModulesmenuitemNames, ModulesmenuitemAccelerators));
        } menu = new JMenu("Window"); {
            int[] WindowmenuitemAccelerators = {
                KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, -1, -1
            };
            menubar.add(createmenu("Window", WindowmenuitemNames, WindowmenuitemAccelerators));
        } menu = new JMenu("Help"); {
            int[] HelpmenuitemAccelerators = {
                KeyEvent.VK_0
            };
            menubar.add(createmenu("Help", HelpmenuitemNames, HelpmenuitemAccelerators));
        }
        
        int SwatchSize = 15;
        UIManager.put("ColorChooser.swatchesRecentSwatchSize", new Dimension(SwatchSize, SwatchSize));
        UIManager.put("ColorChooser.swatchesSwatchSize", new Dimension(SwatchSize, SwatchSize));
        
        //ColorChooser (JColorChooser)
        cmtoggle = new JCheckBox("Color Mixer",true);
        Border cmborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Picker");
        final JPanel cm = new JPanel(new BorderLayout());
        cm.setBorder(cmborder);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(2,4,2,4);
        add(cm,c);

        //CustomColorChooser (JColorChooser)
        ccmtoggle = new JCheckBox("Color Functions",true);
        Border ccmborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Color Functions");
        final JPanel ccm = new JPanel(new BorderLayout());
        ccm.setBorder(ccmborder);
        c.gridy = 2;
        add(ccm,c);

        //Easyview
        evtoggle = new JCheckBox("Easy View",true);
        //Border evborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View");
        evpanel = new JPanel(new GridBagLayout());
        c.gridy = 0;
        c.weighty = 1.0;
        c.ipady = 150;
        c.insets = new Insets(-1,-1,2,-1);
        //evpanel.setBorder(evborder);
        add(evpanel,c);

        int r, g, b;
        Random R = new Random();
        do {
            cc = new JColorChooser(new Color((R.nextFloat()),R.nextFloat(),R.nextFloat()));
            r = cc.getColor().getRed();
            g = cc.getColor().getGreen();
            b = cc.getColor().getBlue();
        } while(r >= 92 && g >= 92 && b >= 92);
        cc.setPreviewPanel(evpanel);

        ccc = new JColorChooser(new Color((R.nextFloat()),R.nextFloat(),R.nextFloat()));

        AbstractColorChooserPanel[] newPanels = {
            IC, RC, SC, DEyed, AC, TC
        };
        ccc.setChooserPanels(newPanels);

        ccc.setPreviewPanel(new JPanel());
        cm.add(cc,BorderLayout.CENTER);
        ccm.add(ccc,BorderLayout.CENTER);

        createEasyview();
        updateEv(cc.getColor());
        
        ActionHandler.createColorMixerChangeListener();
        ActionHandler.createController(ccm,ccmtoggle);
        ActionHandler.createController(evpanel,evtoggle);
        ActionHandler.createController(cm,cmtoggle);

        setJMenuBar(menubar);

        pack();
        //setMinimumSize(this.getSize());
        setLocationRelativeTo(null);
        setVisible(true);
        //setResizable(false);
        Logger.getLogger(ColorUtility.class.getName()).trace("Finished creating GUI");
    } 
}