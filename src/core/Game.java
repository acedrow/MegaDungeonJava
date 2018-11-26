package core;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import gfx.SpriteSheet;
import gfx.TextureLoader;

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
		
		//game loop - update variables, render them to screen, 
		while (running){
			tick();
			render();
		}
	}
	
	//CALLED ONCE PER TICK - BE CAREFUL
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if (bs == null){
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//draw
		
		
		//end draw
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
