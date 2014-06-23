package edu.utdallas.gamewizard;

 import java.awt.Graphics;
 import java.awt.Image;
 import java.awt.Toolkit;
 import java.io.File;
 import java.io.IOException;

 import javax.imageio.ImageIO;
 import javax.swing.JPanel;

 import edu.utdallas.sharedfiles.Shared.ImageHelper;

 public class WizardPanel extends JPanel
 {
     Image bg;
     int posX;
     int posY;
     public WizardPanel(String imageName, int x, int y)
     {
         super();
         posX = x;
         posY = y;
         
         try{
             bg = ImageHelper.getScaledImage(ImageIO.read(new File("Office, Classroom\\" + imageName)), 1.5);
         }
         catch(IOException e)
         {
             System.out.println("File is missing from repository, cannot load");
         }
     }
     protected void paintComponent(Graphics g)
     {
         super.paintComponent(g);
         g.drawImage(bg,posX,posY, null);
     }
     
     public void changeCoord(int x, int y){
         posX = x;
         posY = y;
     }
     
     public void changeFileName(String imageName){
         try{
             bg = ImageHelper.getScaledImage(ImageIO.read(new File("Office, Classroom\\" + imageName)), 1.5);
         }
         catch(IOException e)
         {
             System.out.println("File is missing from repository, cannot load");
         }
     }
 }

