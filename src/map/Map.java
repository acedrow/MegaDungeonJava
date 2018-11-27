package map;

//handles rendering of visible areas, loading and handling of active chunks
public class Map {
	
	private Chunk currChunk;
	
	public Map (Chunk cc){
		currChunk = cc;
	}
	
	//iterates through all tiles ON THE SCREEN (still need to support multiple chunks), and renders them.
	public void renderMap(){
		Tile tileArray[][] = currChunk.getTileArray();
		for (int x = 0; x < tileArray.length; x++){
			for (int y = 0; y < tileArray[x].length; y++){
				
			}
		}
	}
}
