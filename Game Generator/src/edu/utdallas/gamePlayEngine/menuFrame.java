/*
 * This file is used to create a frame with a menu
 * Author: Zac (Qi ZHANG)
 */

package edu.utdallas.gamePlayEngine;

import edu.utdallas.gamePlayEngine.controller.GameController;
import edu.utdallas.gamePlayEngine.model.GameModel;
import edu.utdallas.gamePlayEngine.model.GameModelBoundary;
import edu.utdallas.gamePlayEngine.view.GameView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class menuFrame extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JFrame jFrame;
	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 600;
	JFileChooser myFileChooser = new JFileChooser();
	
	public menuFrame(final GameView gameView)
	{
		jFrame = new JFrame("Game");
		jFrame.setLocationRelativeTo(null);
		jFrame.pack();
		jFrame.setVisible(true);
		jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		jFrame.setLayout(new BorderLayout());

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setLocation(dim.width / 2 - jFrame.getSize().width / 2,
				dim.height / 2 - jFrame.getSize().height / 2);
		
		JMenuBar mainMenu = new JMenuBar();
		mainMenu.setOpaque(true);
	    JMenu games = new JMenu("Games");
	    JMenuItem openGame = new JMenuItem("Open Game");
	    JMenuItem quit = new JMenuItem("Quit");
	    
	    openGame.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	int retval = myFileChooser.showOpenDialog(menuFrame.this);
		        if (retval == JFileChooser.APPROVE_OPTION) {
		            File myFile = myFileChooser.getSelectedFile();
		            System.out.println("Opening File: " + myFile.toString());
		            try
					{
		            	gameView.resetView();
		            	GameController gameController = new GameController(new GameModel(), gameView);
		            	final GameModelBoundary gameModelBoundary = gameController.getModelBoundary();
		            	gameModelBoundary.setView(gameView);
		            	gameModelBoundary.gmbEnd();
		        		gameModelBoundary.startGame(myFile.toString(), jFrame);
					} catch (Exception e)
					{
						System.out.println("Exception in GameViewFrame.java, startGame: " + e.toString());
					}
		        }
	        }
	    });
	    quit.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent arg0) {
	        	System.exit(0);
	        }
	    });
	    
	    games.add(openGame);
	    games.addSeparator();
	    games.add(quit);
	    
	    mainMenu.add(games);
	    jFrame.setJMenuBar(mainMenu);
	    jFrame.setVisible(true);
	    jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
