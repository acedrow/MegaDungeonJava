package io;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

import game.AppConstants;

public class Window {

	private long window;
	private int width, height;
	private boolean fullscreen;
	private Input input;
	
	public static void setCallBacks(){
		glfwSetErrorCallback(GLFWErrorCallback.createPrint(System.err));
	}
	
	public Window(){
		setSize(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_HEIGHT);
	}

	public void createWindow(String title){
		window = glfwCreateWindow(
				width, 
				height, 
				title, 
				fullscreen ?  glfwGetPrimaryMonitor() : 0,
						0);

		if (window == 0){
			throw new IllegalStateException("Failed to create game window");
		}

		if(!fullscreen){
			GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			//centers the window on the screen.
			glfwSetWindowPos(window, 
					(vidMode.width() - width) / 2, 
					(vidMode.height() - height) / 2);
		}
		
		glfwShowWindow(window);
		glfwMakeContextCurrent(window); //allows LWJGL to create capabilities - generally doesn't like to work w/o a context
		input = new Input(window);
		
	}
	
	public void update(){
		input.update();
		glfwPollEvents();
	}

	public boolean shouldClose(){
		return glfwWindowShouldClose(window);
	}
	
	public void swapBuffers(){
		glfwSwapBuffers(window);
	}
	
	/*
	 * GETTERS AND SETTERS
	 */

	public int getWidth() {return width;}
	public int getHeight() {return height;}
	public long getWindowID(){return window;}
	public Input getInput(){return input;}

	public void setSize(Integer width, int height) {
		this.width = width;
		this.height = height;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}
	

	

}
