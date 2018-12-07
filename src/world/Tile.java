package world;

public class Tile {
	
	public static Tile tile[] = new Tile[16];
	
	public static final Tile testTile = new Tile((byte)0, "stoneBrick");

	private byte id;
	private String texture;
	
	public Tile(byte id, String texture){
		this.id = id;
		this.texture = texture;
		if (tile[id] != null){
			throw new IllegalStateException("Tile at: ["+id+"] + is already instantiated");
		}
		tile[id] = this;
	}
	
	public byte getId(){return id;}
	public String getTexture(){return texture;}
	
}
