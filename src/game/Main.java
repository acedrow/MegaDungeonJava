package game;

import org.lwjgl.opengl.GL;

import io.Window;
import render.Model;
import render.Shader;
import render.Texture;
import world.TileRenderer;
import world.World;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

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


//TODO game loop should be threaded, a la my old code on personal laptop.
public class Main {
	//TODO these should all be in constants

	public static double FPS = 60.0;
	//TODO need 32x32 test texture to test world scale with - recall, he had the *2's in tile renderer, and scale set to 16
	static int windowScale = 32;

	public static void main(String args[]){
		Window.setCallBacks(); //more detailed error reporting

		if (!glfwInit()){
			System.err.println("GLFW failed to initialize");
			System.exit(1);
		}

		Window window = new Window();
		window.setFullscreen(false);
		window.createWindow("MegaDungeon");

		GL.createCapabilities();

		Camera camera = new Camera(window.getWidth(), window.getHeight());
		glEnable(GL_TEXTURE_2D);

		TileRenderer tileRenderer = new TileRenderer();

		Shader shader = new Shader("testShader");
		Texture texture = new Texture("./res/stoneBrick.png");

		World world = new World();

		camera.setPosition(new Vector3f(0, 0, 0));

		//seconds per frames
		double frameCap = 1.0/FPS;
		double frameTime = 0;
		int frames = 0;
		double time = Timer.getTime();
		double unprocessed = 0;

		while(!window.shouldClose()){
			boolean shouldRender = false;
			double time2 = Timer.getTime();
			double passed = time2 - time;
			unprocessed += passed;
			frameTime += passed;
			time = time2;

			while(unprocessed >= frameCap){
				unprocessed -= frameCap;
				shouldRender = true;

				if (window.getInput().isKeyPressed(GLFW_KEY_ESCAPE)){
					glfwSetWindowShouldClose(window.getWindowID(), true);
				}
				
				if(window.getInput().isKeyDown(GLFW_KEY_UP)){
					camera.getPosition().sub(new Vector3f(0, 1, 0));
				}
				
				if(window.getInput().isKeyDown(GLFW_KEY_DOWN)){
					camera.getPosition().sub(new Vector3f(0, -1, 0));
				}
				
				if(window.getInput().isKeyDown(GLFW_KEY_LEFT)){
					camera.getPosition().sub(new Vector3f(-1, 0, 0));
				}
				if(window.getInput().isKeyDown(GLFW_KEY_RIGHT)){
					camera.getPosition().sub(new Vector3f(1, 0, 0));
				}
				
				//updates key presses and calls glfwPollEvents()
				window.update(); 
				if (frameTime >= 1.0){
					frameTime = 0;
					System.out.println("FPS: " + frames);
					frames = 0;
				}
			}

			if (shouldRender){
				glClear(GL_COLOR_BUFFER_BIT);

				/*	testShader.bind();
				testShader.setUniform("sampler", 0);
				testShader.setUniform("projection", camera.getProjection().mul(target));
				model.render();
				texture.bind(0);*/
				
				world.render(tileRenderer, shader, camera);

				window.swapBuffers();
				frames++;
			}
		}
		glfwTerminate();
	}
}
