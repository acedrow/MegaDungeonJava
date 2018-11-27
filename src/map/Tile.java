package map;

public class Tile {
	
	//the tile's coordinates relative to it's parent chunk, from 0 to 99.
	private byte chunkx;
	private byte chunky;
	//tile's pixel coordinates on the game window;
	private int screenx;
	private int screeny;
	
	private byte floorType;
	
	public Tile (byte x, byte y, byte ft){
		chunkx = x;
		chunky = y;
		floorType = ft;
	}

}
