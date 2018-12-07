package world;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.HashMap;

import org.joml.Matrix3dc;
import org.joml.Matrix3fc;
import org.joml.Matrix3x2fc;
import org.joml.Matrix4dc;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Matrix4x3fc;
import org.joml.Quaternionf;
import org.joml.Quaternionfc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import game.AppConstants;
import game.Camera;
import render.Model;
import render.Shader;
import render.Texture;

public class TileRenderer {
	private HashMap<String, Texture> tileTextures; //TODO - this should be handled in assetManager
	private Model model;

	public TileRenderer(){
		tileTextures = new HashMap<String, Texture>();

		float[] verticies = new float[]{
				-0.5f, 0.5f, 0, //top left
				0.5f, 0.5f, 0,// top right
				0.5f, -0.5f, 0,// bottom right
				-0.5f, -0.5f, 0,//bottom left

		};

		float[] textureCoords = new float[]{
				0,0, //top left
				1,0, //top right
				1,1, //bottom right
				0,1, //bottom left
		};

		int[] indicies = new int[]{
				0, 1, 2, //first triangle
				2, 3, 0, //second triangle
		};

		model = new Model (verticies, textureCoords, indicies);

		for (int i = 0; i < Tile.tile.length; i++){
			if (Tile.tile[i] != null){
				if(!tileTextures.containsKey(Tile.tile[i].getTexture())){
					String tex = Tile.tile[i].getTexture();
					tileTextures.put(tex, new Texture("./res/"+tex + AppConstants.PNG_EXTENSION));
				}
			}
		}
	}
	public void renderTile(byte id, int x, int y, 
			Shader shader, Matrix4f world, Camera camera){

		shader.bind();
		if(tileTextures.containsKey(Tile.tile[id].getTexture())){ //checks if the texture exists in the hashmap
			tileTextures.get(Tile.tile[id].getTexture()).bind(0); //binds the texture to sampler 0.
		}
		Matrix4f tilePos = new Matrix4f().translate(new Vector3f(x, y, 0));
		Matrix4f target = new Matrix4f();

		camera.getProjection().mul(world, target); //target = camera * world
		target.mul(tilePos);

		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);

		model.render();

	}
}
