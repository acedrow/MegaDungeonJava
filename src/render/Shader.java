package render;

import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class Shader {
	private int program;
	private int vertexShader; //processes all vertices given to the shader
	private int fragmentShader; //does color, texture, blur
	
	public Shader(String fileName){
		program = glCreateProgram();
		
		vertexShader = glCreateShader(GL_VERTEX_SHADER); //instantiate shader
		//.vs is the type, can be anything, //TODO should be kept in constants
		glShaderSource(vertexShader, readFile(fileName + ".vs")); //get shader from file
		glCompileShader(vertexShader); //compile shader
		if (glGetShaderi(vertexShader, GL_COMPILE_STATUS) != 1){ //check shader for errors
			System.err.println(glGetShaderInfoLog(vertexShader)); //shader error, print err from gl
			System.exit(1);
		}
		
		fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fragmentShader, readFile(fileName + ".fs")); //TODO should be kept in constants
		glCompileShader(fragmentShader);
		if (glGetShaderi(fragmentShader, GL_COMPILE_STATUS) != 1){
			System.err.println(glGetShaderInfoLog(fragmentShader));
			System.exit(1);
		}
		
		glAttachShader(program, vertexShader); //add shaders to gl instance
		glAttachShader(program, fragmentShader);
		
		glBindAttribLocation(program, 0, "vertices");
		glBindAttribLocation(program, 1, "textures");
		
		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		glValidateProgram(program);
		if (glGetProgrami(program, GL_VALIDATE_STATUS) != 1){
			System.err.println(glGetProgramInfoLog(program));
			System.exit(1);
		}
		
		
	}
	//sets uniform variables within shader files.
	public void setUniform(String name, int value){
		int location = glGetUniformLocation(program, name); //gets the location of the uniform variable from opengl
		if (location != -1){ //if we successfully get the location
			glUniform1i(location, value); //sets the value of the u-var, would use glUniformf for a float
		}
	}
	
	public void setUniform(String name, Matrix4f value){
		int location = glGetUniformLocation(program, name); //gets the location of the uniform variable from opengl
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		if (location != -1){ //if we successfully get the location
			glUniformMatrix4fv(location, false, buffer);
		}
	}
	
	public void bind(){
		glUseProgram(program);
	}
	
	//TODO outside the scope of Shader, should be in assetManager
	private String readFile(String fileName){
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader;
		
		try{
			reader = new BufferedReader(new FileReader(new File("./res/shaders/" + fileName)));
			String line;
			while ((line = reader.readLine()) != null){
				stringBuilder.append(line);
				stringBuilder.append("\n"); //openGL expects a file-like input, which means we need line breaks
			}
			reader.close();
		}catch (IOException e){
			e.printStackTrace();
		}
		return stringBuilder.toString();
	}
}
