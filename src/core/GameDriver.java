package core;

import java.awt.Graphics;

import javax.swing.JFrame;

import assets.AssetManager;
import map.Chunk;
import map.Map;
import map.Tile;

//Launcher class
public class GameDriver {
	
	public static void main(String args[]){
		//instantiate an assetmanager, will automatically load assets.
		AssetManager assetManager = new AssetManager();
		
		//TEST CODE map, camera instantiation etc should happen in a separate handler. This class is for high-level menu flow and such.
		Tile tile[][] = new Tile[10][10];
		Chunk testChunk = new Chunk(0,0, tile);
		Map map = new Map(testChunk);
		map.renderMap();
		
		Game game = new Game(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, "Title");
		game.start();
	}
}
