package core;

import java.awt.Graphics;

import javax.swing.JFrame;

//Launcher class
public class GameDriver {
	
	public static void main(String args[]){
		Game game = new Game(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, "Title");
		game.start();
	}
}
