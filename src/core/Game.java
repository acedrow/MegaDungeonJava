package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import assets.TextureLoader;
import gfx.SpriteSheet;
import gfx.Window;

public class Game implements Runnable{
	
	private Window window;
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
	}
	
	private void init(){
		window = new Window(width, height, title); 
		
	}

	public void run() {
		init();

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
	
	//CALLED ONCE PER TICK - BE CAREFUL
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//should only be doing this if we need to?
		g.clearRect(0, 0, width, height);
		//draw here
		
		
		//end drawing
		bs.show();
		g.dispose();
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
