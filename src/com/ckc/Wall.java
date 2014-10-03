package com.ckc;

import java.awt.*;

public class Wall{
	
	int x,y;
	Kuang k;
	public static final int length = 10;
	public static final int width = 200;
	public Wall(int x, int y, Kuang k) {
		this.x = x;
		this.y = y;
		this.k = k;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(x, y, length, width);
		g.setColor(c);
	}
	
	public Rectangle touch(){
		return new Rectangle(x,y,length,width);
		
	}
	
	public boolean hitwall(Cannon c){
		if(this.touch().intersects(c.touch())){
			c.setLive(false);
			return true;
		}
		return false;	
	}
	
	
}
