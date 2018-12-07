package world;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import game.Camera;
import render.Shader;

/*
 * In my implementation, world will be responsible for keeping track of the points of interest
 * (one POI per character, and one for the camera), 
 * Each POI will hold references to its adjacent instantiated chunks,
 * World class will track which POI the camera is on, and will render chunks appropriately.
 * Basically, the chunks (and the tiles contained within) surrounding each POI will always be instantiated.
 * However, we only want to render what's on the screen, so from the current point of interest,
 * we'll determine which tiles can be seen (based on player sightlines and lighting),
 * which ones are on the screen, and just render those
 * 
 * Counterpoint - might want to keep visible tiles for each player character rendered, 
 * if we can afford the memory (probably can), 
 * this would make switching faster.
 */

public class World {
	private byte[] tiles;
	private int width;
	private int height;
	
	private Matrix4f world;
	
	public World(){
		width = 16;
		height = 16;
		
		//1D array functions as a 2d via formula: (x + (y * width))
		tiles = new byte[width * height];
		
		world = new Matrix4f().setTranslation(new Vector3f(0));
		world.scale(32); //video says 16 will make tiles 32x32, need to test this. Larger value = larger tiles.
	
	}
	
	public void render(TileRenderer tileRenderer, Shader shader, Camera camera){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				tileRenderer.renderTile(tiles[j + i * width], j, -i, shader, world, camera);
			}
		}
	}
}
