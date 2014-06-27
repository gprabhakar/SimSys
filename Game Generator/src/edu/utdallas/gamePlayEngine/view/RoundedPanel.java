package edu.utdallas.gamePlayEngine.view;

import edu.utdallas.gamePlayEngine.model.ReadBehavior;
import edu.utdallas.gamePlayEngine.model.Location;
import edu.utdallas.gamePlayEngine.model.Prop;
import edu.utdallas.gamePlayEngine.model.Time;
import edu.utdallas.gamePlayEngine.model.Size;
import edu.utdallas.gamePlayEngine.model.Reward;
import edu.utdallas.gamePlayEngine.model.GameModel;
import edu.utdallas.gamePlayEngine.model.Util;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.Animator.Direction;
import org.jdesktop.animation.timing.Animator.RepeatBehavior;

public class RoundedPanel extends JPanel implements TimingTarget{
	 /** Stroke size. it is recommended to set it to 1 for better view */
    protected int strokeSize = 1;
    /** Color of shadow */
    protected Color shadowColor = Color.black;
    /** Sets if it drops shadow */
    protected boolean shady = true;
    /** Sets if it has an High Quality view */
    protected boolean highQuality = true;
    /** Double values for Horizontal and Vertical radius of corner arcs */
    protected Dimension arcs = new Dimension(20, 20);
    /** Distance between shadow border and opaque panel border */
    protected int shadowGap = 5;
    /** The offset of shadow.  */
    protected int shadowOffset = 4;
    /** The transparency value of shadow. ( 0 - 255) */
    protected int shadowAlpha = 150;
    
    private String stringTextToDisplay;
    private Prop prop = null;
    
    float alpha = 1.0f;                 // current opacity of button
    Animator animator;                  // for later start/stop actions
    BufferedImage buttonImage = null;
    String toDisplay = "";
    
    public RoundedPanel(Prop prop) {
        super();             
        
        /*try{
        	this.prop.setText(prop.getText());
        }catch(Exception e){        	        	
        	this.prop.setText("");        	
        }
        System.out.println("Prop set properly!!!1111");
        try{        	
        	this.prop.setColor(prop.getColor());
        }catch(Exception e){
        	this.prop.setColor(gamePlayEngine.model.gameElement.graphic.Color.YELLOW);
        }        
        System.out.println("Prop set properly!!!999");
        try{
        	
        	this.prop.setLocation(prop.getLocation());
        }catch(Exception e){
        	this.prop.setLocation(Location.C);
        }
        System.out.println("Prop set properly!!!888");
        try{
        	this.prop.setSize(prop.getSize());
        }catch(Exception e){
        	this.prop.setSize(Size.MEDIUM);
        }
        System.out.println("Prop set properly!!!567");
        try{
        	this.prop.setTextSize(prop.getTextSize());
        }catch(Exception e){
        	this.prop.setTextSize("12");
        }       */      
        this.prop = prop;        
        setOpaque(false);		
				 		
		//this.stringTextToDisplay = this.prop.getText();
		System.out.println("************************************************************");				
		//System.out.println(this.prop.getLocation());
		//System.out.println(this.prop.getColor());
        
        Time time = prop.getType().getEvents().get(0).getTime();
    	List<edu.utdallas.gamePlayEngine.model.Timer> timers = GameModel.getGameModelObject().getGameConstants().getTimers();
		int timerValue = 0;
		for(edu.utdallas.gamePlayEngine.model.Timer timer:timers)
		{
			System.out.println("Timer name is..." + timer.getName());
			
			if(timer.getName() != null && time != null){
				if(timer.getName().trim().equals(time.name()))
				{										
						timerValue = Integer.parseInt(timer.getValue());
						System.out.println("Timer value is" + timerValue);					
				}
			}
		}
		System.out.println("Timer Value is : "+timerValue + " *******$$$$$#############33");
        animator = new Animator(timerValue,1, 
                RepeatBehavior.REVERSE, this);
        animator.setStartFraction(1.0f);
        animator.setStartDirection(Direction.BACKWARD);
        animator.start();     
    }
    
    
    protected void paintComponent(Graphics g) {
        
        int width = 200;
        int height = 100;
        int shadowGap = this.shadowGap;        
        Color shadowColorA = new Color(shadowColor.getRed(), 
        shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha);
                  
        Graphics2D graphics = (Graphics2D) g;
        
        //Sets antialiasing if HQ.
        if (highQuality) {
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
			RenderingHints.VALUE_ANTIALIAS_ON);
        }

        //Draws shadow borders if any.
        if (!shady) {
        	shadowGap = 1;
        }
        
        AlphaComposite newComposite = 
      	    AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
      	graphics.setComposite(newComposite);
        graphics.setStroke(new BasicStroke(10));
        graphics.setColor(Color.BLACK);
        //System.out.println("Getting size!!!");
        
        Dimension dim = null;
        try{
        	dim = Util.panelDimension(this.prop.getSize());
        }catch(Exception e){
        	dim = Util.panelDimension(Size.MEDIUM);
        }

        graphics.drawRoundRect(3, 3, (int)dim.getWidth()-5,
		(int)dim.getHeight() -5, arcs.width, arcs.height);
        
        //Sets strokes to default, is better.
        graphics.setStroke(new BasicStroke()); 
        try{
        	
        	graphics.setColor(Util.StringToColor(this.prop.getColor().toString().trim()));
        	//}
        	System.out.println("the color of the information box is: " + this.prop.getColor().toString().trim());
        	
        }catch(Exception e){
        	graphics.setColor(Util.StringToColor("YELLOW"));
        }
        graphics.fillRoundRect(3, 3, (int)dim.getWidth()-5, 
        		(int)dim.getHeight() -5, arcs.width, arcs.height);     
        try{
        	// Zac ZHANG: Modify to get font from XML files
        	graphics.setFont(new Font(prop.getFont().toString(), Font.PLAIN, Integer.parseInt(this.prop.getTextSize())));
        }catch(Exception e){
        	graphics.setFont(new Font("Serif",Font.PLAIN,Integer.parseInt("10")));
        }
        graphics.setColor(Color.BLACK);
                
        
        try{
        	toDisplay = this.prop.getText();
        	try{
            	ReadBehavior readBehavior = new ReadBehavior(prop.getBehavior());
            	if (readBehavior.getModel().trim().equals("Reward")) {
            		
        			//if (readBehavior.getAction().trim().equals("GetPoints")) {
        				Reward reward = GameModel.getGameModelObject().getCharacter()
        						.getReward();
        				//System.out.println("Points are" + reward.getPoints());
        				toDisplay = toDisplay+ " " + reward.getPoints() + ". " ;

        				if(reward.getTitle() != null)
        					// Zac ZHANG: Modify this part to make this part same with document
        					toDisplay = "Your title is " + reward.getTitle() + " and you have " + reward.getPoints() + " points!";
        			//}
        		}        	
            }catch(Exception e){
            	
            }	
        	drawStringRect(graphics,3,3,(int)dim.getWidth()-5,(int)dim.getHeight() -5,1.5f,toDisplay);
        }catch(Exception e){
        	drawStringRect(graphics,3,3,(int)dim.getWidth()-5,(int)dim.getHeight() -5,1.5f," ");
        }
    }
    
    private void drawStringRect(Graphics2D graphics, int x1, int y1, int x2, int y2, 
            float interline, String txt) {
            AttributedString as = new AttributedString(txt);
            as.addAttribute(TextAttribute.FOREGROUND, graphics.getPaint());
            as.addAttribute(TextAttribute.FONT, graphics.getFont());
           // System.out.println("Font name is " + graphics.getFont().getFontName());
            AttributedCharacterIterator aci = as.getIterator();
            FontRenderContext frc = new FontRenderContext(null, true, false);
            LineBreakMeasurer lbm = new LineBreakMeasurer(aci, frc);
            float width = x2 - x1;

            while (lbm.getPosition() < txt.length()) {
                TextLayout tl = lbm.nextLayout(width);
                y1 += tl.getAscent();
                tl.draw(graphics, x1, y1);
                y1 += tl.getDescent() + tl.getLeading() + (interline - 1.0f) * tl.getAscent();
                if (y1 > y2) {
                    break;
                }
            }
        }

    
   /* public void paint(Graphics g) {
   	 // Create an image for the button graphics if necessary
   	System.out.println("In the paint method !!!");
   	//setOpaque(false);
       if (buttonImage == null || buttonImage.getWidth() != getWidth() ||
               buttonImage.getHeight() != getHeight()) {
       	System.out.println("Yes.. Here it is");
           buttonImage = getGraphicsConfiguration().
                   createCompatibleImage(getWidth(), getHeight());
           System.out.println("Yes.. Here it was");           
           //setOpaque(false);
           }else{
       	//setOpaque(false);
       }
       Graphics gButton = buttonImage.getGraphics();       
       gButton.setClip(g.getClip());
       //gButton.setColor(Color.GREEN);
       //  Have the superclass render the button for us
       super.paint(gButton);
       super.setOpaque(false);
       //setBackground(Color.YELLOW);
       // Make the graphics object sent to this paint() method translucent
	  Graphics2D g2d  = (Graphics2D)g;
	  AlphaComposite newComposite = 
	      AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
	  g2d.setComposite(newComposite);
       
       // Copy the button's image to the destination graphics, translucently
       g2d.drawImage(buttonImage, 0, 0, null);
       //setOpaque(false);
   }*/


	//@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}



//	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}



	//@Override
	public void repeat() {
		// TODO Auto-generated method stub
		
	}



	//@Override
	public void timingEvent(float fraction) {
		// TODO Auto-generated method stub
		alpha = fraction;        
        if(alpha > 0){        	
        	repaint();
        }else{
        	animator.stop();
        }  
	}
}
