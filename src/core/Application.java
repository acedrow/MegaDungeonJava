package core;

import org.lwjgl.glfw.GLFWKeyCallback;

public class Application {
	private static Window window;
	
	public static void init(){
		initWindow();
		startGame();
	}
	
	private static void initWindow(){
		window = new Window(AppConstants.WINDOW_WIDTH, AppConstants.WINDOW_WIDTH, "Window Title Here");
		
		window.createWindow();
		/*
		window.setKetInput(new GLFWKeyCallback());
		window.setMouseInput();
		window.setMousePos();
		*/
	}
	
	private static void startGame(){
		Game game = new Game();
	}
}
