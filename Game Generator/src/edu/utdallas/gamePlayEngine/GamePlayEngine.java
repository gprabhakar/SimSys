/*The Game Play Engine has an active MVC (model, view, controller) architecture.
*The purpose of this file is to create the model, view and controller components and their interfaces.
*/

package edu.utdallas.gamePlayEngine;

import edu.utdallas.gamePlayEngine.controller.GameController;
import edu.utdallas.gamePlayEngine.model.GameModel;
import edu.utdallas.gamePlayEngine.model.GameModelBoundary;
import edu.utdallas.gamePlayEngine.view.GameView;
import edu.utdallas.gamePlayEngine.view.GameViewFrame;

/**
 * edit game system
 * Core GamePlayEngine class - Creates Model, View and Controller and sets up 
 * interaction amongst them.
 */
public class GamePlayEngine {

        /** Main entry point of the GamePlayEngine - Sets up the game and starts it up.
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception  {
                // Zac ZHANG: use the new class to show the program with a menu
        		GameView gameView = new GameView();
        		menuFrame myMenuFrame = new menuFrame(gameView);
        		
        }
}
