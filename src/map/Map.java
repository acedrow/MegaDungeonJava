package map;

import java.awt.image.BufferedImage;

//handles rendering of visible areas, loading and handling of active chunks
public class Map {
	
	private Chunk currChunk;
	
	public Map (Chunk cc, Window window){
		currChunk = cc;
	}
	
	//TEST METHOD
	public void testRender(BufferedImage image){
		
	}
	
	//renders all tiles, objects, actors currently on screen. Called when characters/camera move, or when we switch characters.
	public void renderMap(){
		//general outline for render strategy:
		//setup for loop based on bounds of camera frame,
		//loop through those tiles (may be from multiple chunks, will have to handle that)
		//render them, this way, rendering nothing not within the camera frame.
		//will have to figure out how to handle keeping multiple areas (groups of chunks) stored in memory at a time
		//e.g. if multiple characters are in different locations, don't want to have to re-load chunks from disc when switching characters
		//thinking may have to track "points of interest", one for each character, each keep the current chunk and adjacent chunk loaded.
		
		Tile tileArray[][] = currChunk.getTileArray();
		for (int x = 0; x < tileArray.length; x++){
			for (int y = 0; y < tileArray[x].length; y++){
				
			}
		}
	}
}
