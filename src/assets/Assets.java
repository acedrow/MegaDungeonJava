package assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

//loads and manages assets
public class Assets {

	Map<Byte, String> tileFloorType;
	
	public void init(){
		initFloorTypes();
	}
	
	//create hardcoded mapping of tile floor type codes to floor type strings - directly corresponding to file names in /resources/textures/floors.
	private void initFloorTypes(){
		tileFloorType = new HashMap<Byte, String>();
		tileFloorType.put((byte) 1, "metalTile");
		
	}
	
	public Map<Byte, BufferedImage> getTileFloorImages(){
		Map<Byte, BufferedImage> toReturn = new HashMap<Byte, BufferedImage>();
		for(int i = -128; Math.abs(i) < 128; i++){
			String imageString = null;
			try{
				imageString = tileFloorType.get((byte) i);
			} catch (Exception e){
				
			}
			if (imageString != null){
				TextureLoader.loadTexture("/resources/textures")
			}
		}
		return toReturn;
	}

}
