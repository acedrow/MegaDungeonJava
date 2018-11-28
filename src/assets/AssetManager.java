package assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import core.GameConstants;

//loads and manages assets
public class AssetManager {

	public Map<Byte, BufferedImage> tileFloorTextures;

	Map<Byte, String> tileFloorType;

	//on construction, loads all assets into memory.
	public AssetManager(){
		init();
	}

	public void init(){
		initFloorTypes();
	}

	//create hardcoded mapping of tile floor type codes to floor type strings - directly corresponding to file names in /resources/textures/floors.
	private void initFloorTypes(){
		tileFloorType = new HashMap<Byte, String>();
		tileFloorType.put((byte) 0, "metalTile");

		//Remember to update the below variables after adding any floorTile definitions
		int startIndex = 0; //this doesn't change unless we map types to negative values.
		int countTileTypes = 1; //this = total number of types currently hardcoded in

		tileFloorTextures = getTileFloorImages(startIndex, countTileTypes);
	}

	public Map<Byte, BufferedImage> getTileFloorImages(int startIndex, int countTileTypes){
		Map<Byte, BufferedImage> toReturn = new HashMap<Byte, BufferedImage>();
		for(int i = startIndex; Math.abs(i) < countTileTypes; i++){
			
			String imageString = tileFloorType.get((byte) i);
			String path = GameConstants.FLOOR_TEXTURE_PATH + "/" + imageString + GameConstants.TEXTURE_IMAGE_FILE_EXTENSION;
			TextureLoader.loadTexture(path);
		}
		return toReturn;
	}

}
