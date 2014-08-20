package leocarbon.cu;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static leocarbon.cu.ColorUtility.A;
import static leocarbon.cu.ColorUtility.CU;
import static leocarbon.cu.ColorUtility.Ev;
import static leocarbon.cu.ColorUtility.cc;
import org.apache.log4j.Logger;

public class ActionHandler implements ActionListener {
    public static ActionHandler ActionListener = new ActionHandler();
    
    public static void createController(final JPanel inpanel, final JCheckBox input){
        input.addActionListener((ActionEvent AE) -> {
            if(input.isSelected()){
                inpanel.setVisible(true);
                
                CU.ccmtoggle.setEnabled(true);
                CU.cmtoggle.setEnabled(true);
                
                CU.pack();
            }
            else {
                inpanel.setVisible(false);
                if(CU.cmtoggle.equals(AE.getSource())){
                    CU.ccmtoggle.setEnabled(false);
                } else if(CU.ccmtoggle.equals(AE.getSource())){
                    CU.cmtoggle.setEnabled(false);
                }
                CU.pack();
            }
        });
    }
    
    public static void createColorMixerChangeListener(){
        final ColorSelectionModel model = cc.getSelectionModel();
        ChangeListener changeListener = (ChangeEvent CE) -> {
            if(Easyview.Opaque && model.getSelectedColor().getAlpha() != 255) Ev.update(new Color(model.getSelectedColor().getRed(),model.getSelectedColor().getGreen(),model.getSelectedColor().getBlue(),255));
            else Ev.update(new Color(model.getSelectedColor().getRed(),model.getSelectedColor().getGreen(),model.getSelectedColor().getBlue(),model.getSelectedColor().getAlpha()));
        };
        model.addChangeListener(changeListener);
    }
    
    @Override
    public void actionPerformed(ActionEvent AE) {
        String ActionCommand = AE.getActionCommand();
        if(ActionCommand.contains(".menu.")){
            String[] description = ActionCommand.split("\\.");
            if("File".equals(description[0])){
                if(description[2].equals("Options")){
                    ColorUtility.O = new Options();
                }         
            } else if("Edit".equals(description[0])){
                switch(description[2]){
                    case "Copy Hex Value":
                        copytoClipboard(Easyview.hex);
                        Logger.getLogger(ActionHandler.class).info("Copied Hex Color "+Easyview.hex);
                        break;
                    case "Copy AHex Value":
                        copytoClipboard("0x"+Easyview.ahex);
                        Logger.getLogger(ActionHandler.class).info("Copied AHex Color "+"0x"+Easyview.ahex);
                        break;
                    case "Copy RGB Value":
                        copytoClipboard(Easyview.rgb);
                        Logger.getLogger(ActionHandler.class).info("Copied RGB Color "+Easyview.rgb);
                        break;
                    case "Copy RGBA Value":
                        copytoClipboard(Easyview.rgba);
                        Logger.getLogger(ActionHandler.class).info("Copied RGBA Color "+Easyview.rgba);
                        break;
                    case "Paste Hex Value":
                        String phex = pastefromClipboard();
                        if(!phex.startsWith("#")) phex = "#".concat(phex);
                        cc.setColor(Color.decode(phex));
                        Logger.getLogger(ActionHandler.class).info("Pasted Hex Color "+phex);
                        break;
                    case "Paste AHex Value":
                        String pahex = pastefromClipboard();
                        if(pahex.startsWith("0x")) pahex = pahex.substring(2);
                        Color C = new Color(Integer.parseInt(pahex,16));
                        cc.setColor(C);
                        Logger.getLogger(ActionHandler.class).info("Pasted AHex Color "+pahex);
                        break;
                    case "Paste RGB Value":
                        Logger.getLogger(ActionHandler.class).info("Pasted RGB Color ");
                        break;
                    case "Paste RGBA Value":
                        String prgba = pastefromClipboard();
                        cc.setColor(new Color(Integer.parseInt(prgba)));
                        Logger.getLogger(ActionHandler.class).info("Pasted RGBA Color "+prgba);
                        break;
                    case "Undo":
                        break;
                    case "Redo":
                        break;
                    case "Invert RGB":
                        CU.IC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "invert"));
                        break;
                    case "Invert Red":
                        CU.IC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "rinvert"));
                        break;
                    case "Invert Green":
                        CU.IC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "ginvert"));
                        break;
                    case "Invert Blue":
                        CU.IC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "binvert"));
                        break;
                    case "Brighten":
                        CU.TC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "lighten"));
                        break;
                    case "Darken":
                        CU.TC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "darken"));
                        break;
                }
            } else if("Modules".equals(description[0])){
                switch(description[2]){
                    case "Average":
                        CU.AC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "A"));
                        break;
                    case "Eyedropper":
                        if(!CU.DEyed.deyedstart.isSelected()){
                            CU.DEyed.deyedstart.setSelected(true);
                            CU.DEyed.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "DEyed"));
                        } break;
                    case "Random":
                        CU.RC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "random"));
                        break;
                    case "Color Fading":
                        if(!CU.SC.scroll.isSelected()){
                            CU.SC.scroll.setSelected(true);
                            CU.SC.actionPerformed(new ActionEvent((Object)this, ActionEvent.ACTION_PERFORMED, "hscroll"));
                        } break;
                }
            } else if("Window".equals(description[0])){
                
            } else if("Help".equals(description[0])){
                if(description[2].equals("About")){
                    A = new About();
                }
            }
        }
    }
    public static void copytoClipboard(String iString) {
        StringSelection SS = new StringSelection(iString);
        Clipboard C = Toolkit.getDefaultToolkit().getSystemClipboard();
        C.setContents(SS, SS);
    } public static String pastefromClipboard() {
        Clipboard C = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable T = C.getContents(null);
        try {
            return (String)T.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException E) {
            Logger.getLogger(ActionHandler.class.getName()).error(E);
        }
        return null;
    }
}