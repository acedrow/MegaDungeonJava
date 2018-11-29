package assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import core.GameConstants;

//loads and manages assets
public class AssetManager {

	static Map<Byte, BufferedImage> tileFloorTextures;
	static Map<Byte, String> tileFloorType;
	private static boolean loaded = false;
	

	//on construction, loads all assets into memory.
	public AssetManager(){
		loadAssets();
	}

	private void loadAssets(){
		//load here
		initFloorTypes();
		
		//done loading
		loaded = true;
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
			BufferedImage toAdd = TextureLoader.loadTexture(path);
			toReturn.put((byte)i, toAdd);
		}
		return toReturn;
	}
	
	public static Map<Byte, BufferedImage> getFloorTextures(){
		if (!loaded){
			System.out.println("load not complete");
			return null;
		}
		return tileFloorTextures;
	}

}
