package core;

import assets.AssetManager;
import map.Chunk;
import map.World;
import map.Tile;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;

//Launcher class
public class GameDriver {
	
	public static void main(String args[]){
		Game game = new Game(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, "MegaDungeon");
		game.start();
	}
	
}
