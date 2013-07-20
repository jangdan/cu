package com.leocarbonate.cu.models;

import com.leocarbonate.cu.ColorUtility;
import components.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;

public class AverageColor extends AbstractColorChooserPanel{
    BufferedImage img;
    long redBucket = 0;
    long greenBucket = 0;
    long blueBucket = 0;
    long pixelCount = 0;
    public static AverageColor ac;
            
    public AverageColor(){
        final JButton averageStart = new JButton("Choose Image");
        averageStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                img = getImage();
                Color out = getMedian();
                //Easyview.updateev(out);
                ColorUtility.cc.getSelectionModel().setSelectedColor(out);
            }
        });
        add(averageStart,BorderLayout.CENTER);
    }
    
    public Color getMedian(){
        for (int y = 0; y < img.getHeight(); y++){
            for (int x = 0; x < img.getWidth(); x++){
                int color = img.getRGB(x, y);
                int b = (color)&0xFF;
                int g = (color>>8)&0xFF;
                int r = (color>>16)&0xFF;
                Color concolor = new Color(r,g,b);
                pixelCount++;
                redBucket += concolor.getRed();
                greenBucket += concolor.getGreen();
                blueBucket += concolor.getBlue();
            }
        }
        
        System.out.println(pixelCount + "pixels...");
        System.out.println(redBucket + "red     " + greenBucket + "green     " + blueBucket + "blue");
        int r = (int)(redBucket/pixelCount);
        int g = (int)(greenBucket/pixelCount);
        int b = (int)(blueBucket/pixelCount);
        pixelCount = 0;
        redBucket = 0;
        greenBucket = 0;
        blueBucket = 0;
        return new Color(r ,g ,b );
    }
    public Color getMostFrequent(){
        return null;
    }
    
    protected BufferedImage getImage(){
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("Get Median Color");
        chooser.setFileFilter(new components.ImageFilter());
        chooser.setFileView(new ImageFileView());
        chooser.setAccessory(new ImagePreview(chooser));
        int returnName = chooser.showOpenDialog(null);
        String path = null;
        if (returnName == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            if (f != null) { // Make sure the user didn't choose a directory.
 
                path = f.getAbsolutePath();//get the absolute path to selected file
                //below line to test the file chooser
                System.out.println(path);
            }
        }
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(AverageColor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }
    
    @Override
    public void updateChooser() {

    }

    @Override
    protected void buildChooser() {
        ac = new AverageColor();
    }

    @Override
    public String getDisplayName() {
        return "Average";
    }

    @Override
    public Icon getSmallDisplayIcon() {
        return null;
    }

    @Override
    public Icon getLargeDisplayIcon() {
        return null;
    }
}
