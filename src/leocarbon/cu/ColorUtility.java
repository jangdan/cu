package leocarbon.cu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;
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
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import leocarbon.cu.modules.AverageColor;
import leocarbon.cu.modules.DigitalEyedropper;
import leocarbon.cu.modules.InvertColor;
import leocarbon.cu.modules.RandomColor;
import leocarbon.cu.modules.ScrollColor;
import leocarbon.cu.modules.ToneColor;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.simplericity.macify.eawt.Application;
import org.simplericity.macify.eawt.ApplicationEvent;
import org.simplericity.macify.eawt.ApplicationListener;
import org.simplericity.macify.eawt.DefaultApplication;

public class ColorUtility extends JFrame {
    public static ColorUtility CU;
    private static Application MacOSCU;
    
    public static About A;
    public static Options O;
    
    public static ResourceBundle RB = ResourceBundle.getBundle("leocarbon.cu.language.locale", Locale.getDefault());
    
    //<editor-fold defaultstate="collapsed" desc="Menu bar">
    //Menu bar
    public static JMenuBar menubar;
    public static JMenu menu;
    public static String[] menuNames = {
        "File", "Edit", "Modules", "Window", "Help"
    };
    public static JMenuItem menuitem;
    public static String[] FilemenuitemNames = {
        "Options"
    }; int[] FilemenuitemAccelerators = {
        KeyEvent.VK_O
    };
    public static String[] EditmenuitemNames = {
        "Copy Hex Value", "Copy AHex Value", /*"Copy RGB Value", */"Copy RGBA Value", "/", "Paste Hex Value", "Paste AHex Value",/* "Paste RGB Value", */"Paste RGBA Value", "/", "Undo", "Redo", "/",
        "Invert RGB", "Invert Red", "Invert Green", "Invert Blue", "/",
        "Brighten", "Darken"
    }; int[] EditmenuitemAccelerators = {
        KeyEvent.VK_C, -1, -1, -1, KeyEvent.VK_V, -1, -1, -1, KeyEvent.VK_Z, KeyEvent.VK_Y, -1,
        KeyEvent.VK_I, -1, -1, -1, -1,
        KeyEvent.VK_B, KeyEvent.VK_D
    };
    public static String[] ModulesmenuitemNames = {
    }; int[] ModulesmenuitemAccelerators = {
        KeyEvent.VK_A, KeyEvent.VK_E,KeyEvent.VK_R, KeyEvent.VK_S
    };
    public static String[] WindowmenuitemNames = {
        "Color Mixer", "Color Functions", "EasyView", "/", "Reset Windows"
    }; int[] WindowmenuitemAccelerators = {
        KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, -1, -1
    };
    public static String[] HelpmenuitemNames = {
        "About"
    }; int[] HelpmenuitemAccelerators = {
        KeyEvent.VK_0
    };
//</editor-fold>
    public static JRadioButtonMenuItem rbmenuitem;
    public static JCheckBoxMenuItem cbmenuitem;
    
    
    public static Font Monaco18 = new Font("Monaco", Font.PLAIN, 18);
        
    public static JColorChooser cc;
    public JCheckBox cmtoggle;
    
    public static JColorChooser ccc;
    public JCheckBox ccmtoggle;
    
    public static Easyview Ev;
    public JCheckBox evtoggle;
    
    public InvertColor IC = new InvertColor();
    public RandomColor RC = new RandomColor();
    public ScrollColor SC = new ScrollColor();
    public DigitalEyedropper DEyed = new DigitalEyedropper();
    public AverageColor AC = new AverageColor();
    public ToneColor TC = new ToneColor();
        
    public static String OSname;
    public static void getOSname(){
        OSname = System.getProperty("os.name").toLowerCase();
        if(OSname.contains("win")) OSname = "win";
        else if(OSname.contains("mac")) OSname = "mac";
    }
    
    public static void main(String[] args) {
        getOSname();
        if("mac".equals(OSname)){
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Color Utility");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        
        CU = new ColorUtility();
    } public ColorUtility() {
        //Configure the logger (Apache log4j)
        PropertyConfigurator.configure(getClass().getResource("/leocarbon/cu/logging/log4j.properties"));
        Logger.getLogger(ColorUtility.class.getName()).trace("rootlogger configured");
        
        Logger.getLogger(ColorUtility.class.getName()).trace("Operating System: " + System.getProperty("os.name"));
        Logger.getLogger(ColorUtility.class.getName()).trace("ColorUtility.OSname: " + OSname);
        Logger.getLogger(ColorUtility.class.getName()).trace("Java vendor: " + System.getProperty("java.vendor"));
        Logger.getLogger(ColorUtility.class.getName()).trace("Java vendor URL: ["+System.getProperty("java.vendor.url")+"]");
        Logger.getLogger(ColorUtility.class.getName()).trace("Java version: " + System.getProperty("java.version"));
        Logger.getLogger(ColorUtility.class.getName()).trace("Look and Feel: " + UIManager.getSystemLookAndFeelClassName());
        
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
        if("mac".equals(OSname)){
            MacOSCU = new DefaultApplication();
            MacOSCU.addApplicationListener(new MacOSXHandler());
            MacOSCU.addPreferencesMenuItem();
            MacOSCU.setEnabledPreferencesMenu(true);
            Logger.getLogger(ColorUtility.class.getName()).trace("Optimized GUI for Mac OS");
        }
        
        //Initialize GridBagConstraints
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        //Initialize the window
        setTitle(RB.getString("CU.Title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        //Initialize the menubar and its menus
        menubar = new JMenuBar();
        menu = new JMenu(RB.getString("CU.MenuBar.File")); menubar.add(createmenu(RB.getString("CU.MenuBar.File"), FilemenuitemNames, FilemenuitemAccelerators));
        menu = new JMenu(RB.getString("CU.MenuBar.Edit")); menubar.add(createmenu(RB.getString("CU.MenuBar.Edit"), EditmenuitemNames, EditmenuitemAccelerators));
        menu = new JMenu(RB.getString("CU.MenuBar.Modules"));
            AverageColor a = new AverageColor(); DigitalEyedropper d = new DigitalEyedropper(); RandomColor rc = new RandomColor(); ScrollColor s = new ScrollColor();
            ModulesmenuitemNames = new String[]{ a.getDisplayName(), d.getDisplayName(), rc.getDisplayName(), s.getDisplayName() };
            a = null; d = null; rc = null; s = null; System.gc();
            menubar.add(createmenu(RB.getString("CU.MenuBar.Modules"), ModulesmenuitemNames, ModulesmenuitemAccelerators));
        menu = new JMenu(RB.getString("CU.MenuBar.Window")); menubar.add(createmenu(RB.getString("CU.MenuBar.Window"), WindowmenuitemNames, WindowmenuitemAccelerators));
        menu = new JMenu(RB.getString("CU.MenuBar.Help")); menubar.add(createmenu(RB.getString("CU.MenuBar.Help"), HelpmenuitemNames, HelpmenuitemAccelerators));
        
        //Make the Swatches bigger
        int SwatchSize = 15;
        UIManager.put("ColorChooser.swatchesRecentSwatchSize", new Dimension(SwatchSize, SwatchSize));
        UIManager.put("ColorChooser.swatchesSwatchSize", new Dimension(SwatchSize, SwatchSize));
        //ColorChooser (JColorChooser)
        cmtoggle = new JCheckBox(RB.getString("CU.cm"),true);
        Border cmborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), RB.getString("CU.cm"));
        final JPanel cm = new JPanel(new BorderLayout());
        cm.setBorder(cmborder);
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(2,4,2,4);
        add(cm,c);

        //CustomColorChooser (JColorChooser)
        ccmtoggle = new JCheckBox(RB.getString("CU.ccm"),true);
        Border ccmborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), RB.getString("CU.ccm"));
        final JPanel ccm = new JPanel(new BorderLayout());
        ccm.setBorder(ccmborder);
        c.gridy = 2;
        add(ccm,c);
        
        //Easyview
        evtoggle = new JCheckBox(RB.getString("CU.ev"),true);
        //Border evborder = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Easy View");
        Ev = new Easyview();
        c.gridy = 0;
        c.weighty = 1.0;
        c.ipady = 150;
        c.insets = new Insets(-1,-1,2,-1);
        //evpanel.setBorder(evborder);
        add(Ev,c);
        
        int r, g, b;
        Random R = new Random();
        do {
            cc = new JColorChooser(new Color((R.nextFloat()),R.nextFloat(),R.nextFloat()));
            r = cc.getColor().getRed();
            g = cc.getColor().getGreen();
            b = cc.getColor().getBlue();
        } while(r >= 92 && g >= 92 && b >= 92);

        ccc = new JColorChooser(new Color((R.nextFloat()),R.nextFloat(),R.nextFloat()));
        AbstractColorChooserPanel[] newPanels = {
            IC, RC, SC, DEyed, AC, TC
        };
        ccc.setChooserPanels(newPanels);

        Ev.update(cc.getColor());
        
        cc.setPreviewPanel(new JPanel());
        ccc.setPreviewPanel(new JPanel());
        
        cm.add(cc,BorderLayout.CENTER);
        ccm.add(ccc,BorderLayout.CENTER);
        
        ActionHandler.createColorMixerChangeListener();
        ActionHandler.createController(ccm,ccmtoggle);
        ActionHandler.createController(Ev,evtoggle);
        ActionHandler.createController(cm,cmtoggle);

        setJMenuBar(menubar);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setMaximumSize(this.getSize());
        
        Logger.getLogger(ColorUtility.class.getName()).trace("Finished creating GUI");
    }
    
    public static JMenu createmenu(String description, String[] menuitemNames, int[] accelerator) {
        for(int a = 0; a < menuitemNames.length; ++a){
            if("/".equals(menuitemNames[a])) menu.addSeparator();
            else {
                menuitem = new JMenuItem(menuitemNames[a]);
                if(accelerator[a] == -1){
                    menuitem.setAccelerator(null);
                } else {
                    menuitem.setAccelerator(KeyStroke.getKeyStroke(accelerator[a], Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
                }
                menuitem.addActionListener(ActionHandler.ActionListener);
                menuitem.setActionCommand(description+".menu."+menuitemNames[a]);
                menu.add(menuitem);
            }
        }
        menu.getAccessibleContext().setAccessibleDescription(description);
        
        return menu;
    }
    
    
    public class MacOSXHandler implements ApplicationListener {
        @Override
        public void handleAbout(ApplicationEvent AE) {
            A = new About();
        }
        @Override
        public void handlePreferences(ApplicationEvent AE) {
            O = new Options();
        }
        @Override
        public void handleOpenApplication(ApplicationEvent AE) {} @Override
        public void handleOpenFile(ApplicationEvent AE) {} @Override
        public void handlePrintFile(ApplicationEvent AE) {} @Override
        public void handleQuit(ApplicationEvent AE) {} @Override
        public void handleReOpenApplication(ApplicationEvent AE) {}
    }
}