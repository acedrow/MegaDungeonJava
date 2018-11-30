package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import assets.TextureLoader;
import gfx.SpriteSheet;
import gfx.Window;
import map.Chunk;
import map.World;

public class Game implements Runnable{
	
	private Window window;
	private World map;
	
	public int width, height;
	public String title;

	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g; 

	public Game(int w, int h, String t){
		width = w;
		height = h;
		title = t;
		
		window = new Window(this, GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT, "MegaDungeon");
		window.run();

	}

	public void run() {

		int fps = GameConstants.GAME_FPS;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		
		//game loop - update variables, render them to screen, 
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;			
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
				//System.out.println("Ticks");
			}
			
			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
		
	private void render() {

	}

	private void tick() {

		
	}

	public synchronized void start(){
		if (running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); // will call run, above, game code (loop) goes there.
	}

	public synchronized void stop(){
		if (!running){
			return;
		}
		running = false;
		try {
			thread.join();
			System.out.println("Ending game thread");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}
