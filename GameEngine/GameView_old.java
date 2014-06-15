/**
 * Dummy View class implementation - Will be replaced by actual view classes 
 */
package gamePlayEngine.view;

import gamePlayEngine.controller.GameController;
import gamePlayEngine.controller.Message;
import gamePlayEngine.controller.MessageType;
import gamePlayEngine.model.gameElement.graphic.Backdrop;
import gamePlayEngine.model.gameElement.graphic.Prop;
import gamePlayEngine.model.gameElement.player.reward.Reward;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;

public class GameView_old  extends javax.swing.JFrame {

	private GameController controller;
	private String displayInfo;
    public JButton UserButton;
    public JButton game3UserButton;
    public JLabel jLabel1;
    public JLabel jLabel2;
    public RoundedButton jLabel3;
    public JButton Exit;
    public int Qucki = 100;
    public int moderate = 1000;
    public int longer = 10000;
    public int iniatialPoints;
    public String behave;
    public int fPoints;

   public GameView_old(){
	   System.out.println("view has been initialized");
   }
	public GameController getController() {
		return controller;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

    public void init()
    {
        UserButton = new javax.swing.JButton();
        Exit = new javax.swing.JButton();

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new RoundedButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-293)/2, (screenSize.height-160)/2, 293, 160);
    }

	
	// Handle Backdrop start, play and end messages to add it to display or remove from the display
    public  void HandleBackdrop(Backdrop backdrop, Message message) {
        System.out.println(message + " " + backdrop.getClass());
        if(message == Message.Start)
            init();
        if(message == Message.Play)
            backGround();   // starting backdrop


    }

	// Handle Prop start, play and end messages to add it to display or remove from the display
	public void HandleProp(Prop prop, Message message) throws InterruptedException {
			System.out.println(">>>>>>>> type name : "+prop.getType().getTypeName());
			System.out.println(">>>>>>>> behaviour : "+prop.getBehavior());
			System.out.println(">>>>>>>> next : "+prop.getNext());
		if (prop.getType().getTypeName().equals("Button1")) {
			HandleButton(prop, message);
		} else if (prop.getType().getTypeName().equals("InformationBox1")) {
			HandleLabel(prop, message);
		}
		
		
		  /*if(prop.getTime().equals("QUICK")){
			  System.out.println("?????????");
			  HandleTime(prop, message);
		  }*/
	}

	// Handle Label start, play and end messages to add it to display or remove from the display
	
	public void HandleTime(Prop prop, Message message) {
		System.out.println(message + " chandru" + prop + " " + displayInfo);
	}
	
	public void HandleLabel(Prop prop, Message message) {
		System.out.println(message + " " + prop.getText() + ">>>> " + displayInfo);
        addLabel(prop.getBehavior());  // starting label
    }

	// Handle Button start, play and end messages to add it to display or remove from the display
    public void HandleButton(Prop prop, Message message) throws InterruptedException {
        System.out.println(message + " " + prop);

        if(message == Message.Play) { // wait for user input
            // Can be a mouse click for GUI but on console it is simulated using keyboard input
            System.out.println("View: Starting button "+prop.getText());
            System.out.println("starting button " + prop.getText() + " " + message);
            getContentPane().removeAll();
            getContentPane().repaint();
            System.out.println(prop.getText());
            addButton(prop.getText(),prop.getBehavior()); //button adding for both Exit and Play To Win !
            setVisible(true);
        }

    }



    public void showStartView() throws IOException, InterruptedException {
		System.out.println("Please enter a key to start the game:");
		System.in.read();
		controller.gameControllerPlay(MessageType.External, "start");
		
	}

    public void addLabel(String Str)
    {
        Str = Str + Integer.toString(fPoints);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().removeAll();
        getContentPane().repaint();

        jLabel3.setBackground(new java.awt.Color(255, 255, 0));

        jLabel3.setLabel(Str);
        jLabel3.setBounds(15, 10, 250, 30);


        Exit.setText("EXIT");
        Exit.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        Exit.setBounds(160, 70, 100, 30);
        Exit.setBackground(new java.awt.Color(255, 255, 0));
        getContentPane().add(Exit);
        getContentPane().add(jLabel3);

        backGround();
    }

    public int getPoints(String str) {
        System.out.println("  "+str);
        String[] st = str.split("\\.");
        System.out.println(st[2]);
        return Integer.parseInt(st[2]);
    }



    public void ExitActionPerformed(java.awt.event.ActionEvent evt) {

        System.exit(1);
    }

    public void backGround()
    {
        getContentPane().setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\background1.png")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -40, 280, 200);

    }

    public void addButton(String str, String behavior)

    {
        if(str.compareToIgnoreCase("play to win!") == 0)
        {
            init();
            UserButton.setText(str);
            behave = behavior;
            fPoints = getPoints(behavior);
            fPoints = iniatialPoints + fPoints ;
            UserButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    UserButtonActionPerformed(evt);
                }
            });
            getContentPane().add(UserButton);

            UserButton.setBounds(70, 50, 150, 23);
            UserButton.setBorder(BorderFactory.createLineBorder(Color.black,3));
            UserButton.setBackground(new java.awt.Color(255, 255, 0));
            setVisible(true);
        }
        getContentPane().add(UserButton);
        backGround();

        if(str.compareToIgnoreCase("END!") == 0)
        {
          init(); // just intialized. will triger after the button is clicked
        }

    }

    public void UserButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            RewardPay();
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public void RewardPay() throws InterruptedException {
        Prop p = new Prop();
        p.setBehavior("You Won ! Your Points are ");
        HandleLabel(p, Message.Play);
    }

    public void sendPonits(int points) {
        iniatialPoints = points;

    }
}

// points to begin with 5000 and on click we get extra 5000.so result should be 10,000
// result should be 0
// no hard coding anywhere..
//result should be 1000
