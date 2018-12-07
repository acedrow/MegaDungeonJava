package io;

import static org.lwjgl.glfw.GLFW.*;

public class Input {
	private long window;
	
	private boolean keys[];
	
	//window.getInput().isMouseButtonDown(0) - 0 is left click, 1 is right.
	
	public Input(long window){
		this.window = window;
		this.keys = new boolean[GLFW_KEY_LAST];
		//start at 32 as keys 0-31 are not valid in GLFW
		for(int i = 32; i < GLFW_KEY_LAST; i++){
			keys[i] = false;
		}
	}
	
	public boolean isKeyDown(int key){
		return glfwGetKey(window, key) == 1;
	}
	
	public boolean isMouseButtonDown(int button){
		return glfwGetMouseButton(window, button) == 1;
	}
	
	public boolean isKeyPressed(int key){
		return (isKeyDown(key) && !keys[key]);
	}
	
	public boolean isKeyReleased(int key){
		return (!isKeyDown(key) && keys[key]);
	}
	
	public void update(){
		for(int i = 32; i < GLFW_KEY_LAST; i++){
			keys[i] = isKeyDown(i);
		}
	}
}
