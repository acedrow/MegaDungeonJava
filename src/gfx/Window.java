package gfx;

import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;


public class Window {
	
	private JFrame frame;
	private Canvas canvas;
	
	public Window(int width, int height, String title){
		
		frame = new JFrame(title);
		canvas = new Canvas();
		
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public Canvas getCanvas(){
		return canvas;
	}


}