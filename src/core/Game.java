package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import assets.TextureLoader;
import gfx.SpriteSheet;
import input.MouseInput;
import map.Chunk;
import map.World;

public class Game implements Runnable{

	Window window;
	
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;
	//private final MouseInput mouseInput;
	
	

	public Game(){
		thread = new Thread(this, "Game Thread");
		window = Window.appWindow;
		//mouseInput = new MouseInput();
	}

	private void init(){

	}

	public void run() {
		try {
			init();
			gameLoop();
		} catch (Exception excp) {
			excp.printStackTrace();
		} finally {
			cleanup();
		}
	}


	private void gameLoop(){
		double timePerFrame = AppConstants.ONE_BILLION / AppConstants.GAME_FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		running = true;
		while (running && !window.windowShouldClose()) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerFrame;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1){
				input();
				update();
				render();
				ticks++;
				delta--;
			}
			
			if (timer >= AppConstants.ONE_BILLION ){
				System.out.println("Ticks: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
	}
	

	//get input from mouse and keyboard managers
	private void input() {


	}
	//update game logic/game state
	private void update(){
		
	}
	
	//render changes to window/GLFW
	private void render() {

	}


	//called from Application to start the game.
	public void start(){
		thread.start();
	}

	private void cleanup(){
		//cleanup resources and such here
	}

}
