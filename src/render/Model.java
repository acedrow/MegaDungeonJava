package render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Model {
	private int drawCount;
	private int vertexID;
	private int textureID;
	
	private int indexID;
	
	public Model(float[] verticies, float[] texCoords, int[] indicies){
		//for only 2 axis, do / 2
		drawCount = indicies.length;
		
		vertexID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(verticies), GL_STATIC_DRAW);
		
		textureID = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, textureID);
		glBufferData(GL_ARRAY_BUFFER, createBuffer(texCoords), GL_STATIC_DRAW);
		
		indexID = glGenBuffers();
		IntBuffer intBuffer = BufferUtils.createIntBuffer(indicies.length);
		intBuffer.put(indicies);
		intBuffer.flip();
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexID);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL_STATIC_DRAW);
		
		//unbind element array
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		//unbind vertexID
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void render(){
		glEnableVertexAttribArray(0);
		glEnableVertexAttribArray(1);
		
		glBindBuffer(GL_ARRAY_BUFFER, vertexID);
		//second arg should be 2 for 2 axes - x and y, 3 for 3d.
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ARRAY_BUFFER, textureID);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexID);
		
		glDrawElements(GL_TRIANGLES, drawCount, GL_UNSIGNED_INT, 0);
		
		//unbind element buffer
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
		
		//unbind buffer
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		glDisableVertexAttribArray(0);
		glDisableVertexAttribArray(1);
		
	}
	
	private FloatBuffer createBuffer(float[] data){
	FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	
	return buffer;
	}
}
