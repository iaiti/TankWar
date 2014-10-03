package com.ckc;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Blood {
	int x,y,l,w;
	Kuang k;
	int step = 0;
	private boolean live = true;
	private  int location[][] = {{100,200},{110,200},{120,200},{1300,210},{1400,220},{1500,230},{160,200},{170,200},{1900,200},{200,200},{220,200},{230,200},{250,200}};
	
	
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live) {
		this.live = live;
	}
	
	public  Blood(){
		x = location[0][0];
		y = location[0][1];
		w = l = 15;
	}
	
	public void draw(Graphics g){
		if(!isLive()) return; 
		Color c = g.getColor();
		g.setColor(Color.orange);
		g.fillRect(x, y, l, w);
		g.setColor(c);
		//new Thread(new zhanting()).start();
		  move();
	}
	
	public void move(){
		step++;
		if(step == location.length){
			step = 0;
		}
		x = location[step][0];
		y = location[step][1];
		
	}
	
	public Rectangle touch(){
		return new Rectangle(x,y,l,w);
	}
	
	/*private class zhanting implements Runnable{

		public void run() {
					try {
						
						Thread.sleep(500);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
		}
	}*/
}
