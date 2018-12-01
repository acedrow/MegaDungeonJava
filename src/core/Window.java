package core;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;



public class Window {
	
	public static Window appWindow;
	private long windowID;
	private int windowWidth;
	private int windowHeight;
	private String windowTitle;
	
	
	public Window(int windowWidth, int windowHeight, String windowTitle){
		if (appWindow == null){
			appWindow = this;
		}
		
		GLFWErrorCallback.createPrint(System.err).set();
		if ( !glfwInit() )
			throw new IllegalStateException("Unable to initialize GLFW");
		
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.windowTitle = windowTitle;
	}
	
	public void createWindow(){
		//GLFW.glfwWindowHint(131075, 0);
		this.windowID = GLFW.glfwCreateWindow(windowWidth, windowHeight, windowTitle, 0L, 0L);
		
		if (this.windowID == 0L){
			throw new RuntimeException("Failed to create the GLFW window");
		}
		
		//this try block sets the window's position at the center of the screen.
		try ( MemoryStack stack = stackPush() ) {
			IntBuffer pWidth = stack.mallocInt(1); // int*
			IntBuffer pHeight = stack.mallocInt(1); // int*

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(this.windowID, pWidth, pHeight);

			// Get the resolution of the primary monitor
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				this.windowID,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		} 
		
		GLFW.glfwMakeContextCurrent(this.windowID);
		glfwShowWindow(this.windowID);

	}

	/* From Turn-Based Strategy Game, unsure if needed
	public void setKetInput(GLFWKeyCallback inputCallback) {
		GLFW.glfwSetKeyCallback(this.windowID, inputCallback);
		
	}

	public void setMouseInput(GLFWMouseButtonCallback mouseInputCallback) {
		GLFW.glfwSetMouseButtonCallback(this.windowID, mouseInputCallback);
		
	}

	public void setMousePos(GLFWCursorPosCallback mousePosCallback) {
		GLFW.glfwSetCursorPosCallback(this.windowID, mousePosCallback);
		
	}*/

	public void close(){
		GLFW.glfwSetWindowShouldClose(this.windowID, true);
		GLFW.glfwDestroyWindow(this.windowID);
	}
	
}
