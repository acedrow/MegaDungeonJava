package map;

import core.GameConstants;

//game world is made of chunks, each holds x by x (defined in constants) tiles in an array, origin at the top left corner.
public class Chunk {

	//the chunk's coordinate within the world map, origin is at the top left corner.
	private int worldx;
	private int worldy;

	private Tile[][] tile;	

	public Chunk(int wx, int wy, Tile[][] ta){
		int sideLength = GameConstants.CHUNK_SIDE_LENGTH;
		tile = new Tile[sideLength][sideLength];
		worldx = wx;
		worldy = wy;
		tile = ta;
	}

	public int getWorldx() {
		return worldx;
	}

	public void setWorldx(int worldx) {
		this.worldx = worldx;
	}

	public int getWorldy() {
		return worldy;
	}

	public void setWorldy(int worldy) {
		this.worldy = worldy;
	}

	public Tile[][] getTileArray() {
		return tile;
	}
	
	public Tile getTile(int x, int y){
		return tile[x][y];
	}

}
