package core;

import java.util.HashMap;
import java.util.Map;

public final class GameConstants {
	/*
	 * GAME SYSTEM VARIABLES 
	 */
	
	//game FPS
	public static int GAME_FPS = 30;
	//game window size
	public static int WINDOW_WIDTH = 640;
	public static int WINDOW_HEIGHT = 480;
	//tile side length in pixels
	public static int TILE_SIDE_LENGTH = 32;
	//width and height for total size of sprite sheet image;
	public static final int SPRITE_SHEET_WIDTH = 100;
	public static final int SPIRTE_SHEET_HEIGHT = 100;
	//width and height for individual sprite images
	public static final int SPRITE_WIDTH = 32;
	public static final int SPRITE_HEIGHT = 32;
	//chunk width and height in tiles
	public static final int CHUNK_SIDE_LENGTH = 100;
	
	/*
	 * PATHS
	 */
	public static final String RESOURCE_PATH = "/resources";
	public static final String TEXTURE_PATH = RESOURCE_PATH + "/textures";
	public static final String FLOOR_PATH = TEXTURE_PATH + "/floors";
	
}
