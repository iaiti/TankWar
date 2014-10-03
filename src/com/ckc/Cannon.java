package com.ckc;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cannon {	
	int x, y;
	Direction di;
	Kuang k;
	private static final int xlength = 10;
	private static final int ylength = 10;
	public static final int length = 20;
	public static final int width = 20;
	private boolean live = true;
	private boolean good;
	public Cannon(int x, int y, Direction di) {
		this.x = x;
		this.y = y;
		this.di = di;
	}
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Image[] p = null;
	static Map <String,Image> m = new HashMap <String,Image>();
	 static {
		
		Image[] p ={
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileD.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileL.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileLU.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileLD.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileR.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/MissileU.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileRD.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/missileRU.gif")),
		
		
		 };
		 m.put("d", p[0]);
		 m.put("l", p[1]);
		 m.put("lu", p[2]);
		 m.put("ld", p[3]);
		 m.put("r", p[4]);
		 m.put("u", p[5]);
		 m.put("rd", p[6]);
		 m.put("ru", p[7]);
		 
	}
	public Cannon(int x, int y, Direction di,boolean good, Kuang k) {
		this(x, y, di);
		this.k = k;
		this.good = good ;
	}

	public void draw(Graphics g) {
		if(isLive() == false){
			k.cannons.remove(this);
		}
		switch(di){
		case l:
			g.drawImage(m.get("l"), x, y,null);
			break;
		case r:
			g.drawImage(m.get("r"), x, y,null);
			break;
		case u:
			g.drawImage(m.get("u"), x, y,null);
			break;
		case d:
			g.drawImage(m.get("d"), x, y,null);
			break;
		case lu:
			g.drawImage(m.get("lu"), x, y,null);
			break;
		case ld:
			g.drawImage(m.get("ld"), x, y,null);
			break;
		case rd:
			g.drawImage(m.get("rd"), x, y,null);
			break;
		case ru:
			g.drawImage(m.get("ru"), x, y,null);
			break;
		
		}

		move();
	}

	public void move(){
			switch(di){
			case l:
				x -= xlength;
				break;
			case r:
				x += xlength;
				break;
			case u:
				y -= ylength;
				break;
			case d:
				y += ylength;
				break;
			case lu:
				x -= xlength;y -= ylength;
				break;
			case ld:
				x -= xlength;y += ylength;
				break;
			case rd:
				x += xlength;y += ylength;
				break;
			case ru:
				x += xlength;y -= ylength;
				break;
			case stop:
				break;
			}	
			if(x < 0 || y < 0 || x > k.Gamelength || y > k.Gamewidth ){
				
				live = false;
			}
				
	}
	
	public Rectangle touch(){
		return new Rectangle(x,y,length,width);
		
	}
	public boolean whetherhit(Tank tank){
		if(this.live&&this.touch().intersects(tank.touch()) && tank.isLive() && this.good != tank.isGood()){
			if(tank.isGood()) {
				tank.setLife(tank.getLife() - 10);
				if(tank.getLife() <= 0) {
					tank.setLive(false);
				}
			}
			else tank.setLive(false);
			this.setLive(false);
			Explore e = new Explore(x , y ,k);
			k.e.add(e);
			return true;
		}
		return false;
	}
	
	public boolean whetherhit(List <Tank> atitanks ){
		for(int i = 0; i<atitanks.size();i++){
			if(whetherhit(atitanks.get(i)))
				return true;
		}
		return false;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
}







/*my wirting first
import java.awt.*;
import java.awt.event.KeyEvent;

public class Cannon {	
	int x, y;
	private boolean shoot = false;
	Tank.Direction;
	public Cannon(int x, int y)  {
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.white);
		g.fillOval(x+25, y+25, 10, 10);
		g.setColor(c);
		move();
	}
	
	public void keyPressed(KeyEvent k) {
		int key = k.getKeyCode();
		switch(key){
			case KeyEvent.VK_F3:
				shoot = true;
				break;
		}		
	}
	
	public void move(){
		while(shoot){
			x += 5;
		}		
	}
	
	public static void main(String[] args) {

	}

}*/
