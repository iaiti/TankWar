package com.ckc;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class Tank {
	public static final int xlength = 5;
	public static final int ylength = 5;
	
	private boolean live = true;
	private boolean good ;
	private int life = 100;
	
	public BloodStick bs = new BloodStick(); 
	private static Random r = new Random();
	private int tankstep =  r.nextInt(15) + 5;
	int x, y;
	Kuang k;
	private boolean xl= false,xr = false,xu = false,xd = false;
	private Direction di = Direction.stop;
	private Direction breeching = Direction.u;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Image[] p = null;
	public static final int length = 30;
	public static final int width = 30;
	static Map <String,Image> m = new HashMap <String,Image>();
	 static {
		
		 Image[] p ={
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
		 };
		 m.put("d", p[0]);
		 m.put("l", p[1]);
		 m.put("ld", p[2]);
		 m.put("lu", p[3]);
		 m.put("r", p[4]);
		 m.put("u", p[5]);
		 m.put("rd", p[6]);
		 m.put("ru", p[7]);
		 
	}
	 
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}

	int oldx,oldy;
	public boolean isGood() {
		return good;
		
	}
	public  Tank(int x, int y ,boolean good) {
		this.x = x;
		this.y = y;
		this.oldx = x;
		this.oldy = y;
		this.good = good;
	}
	
	public Tank(int x, int y, boolean good,Direction di, Kuang k){
		this(x, y,good);
		this.di = di;
		this.k = k;
	}

	public void draw(Graphics g) {
		if( !live ){
			if(!good){
				k.atitanks.remove(this);
			}
			return;
		}
		if(good){
			bs.draw(g);
		}
		
		
		switch(breeching){
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
		
		}move();
	}
	

	public void move() {
		this.oldx = x;
		this.oldy = y;
		switch(di){
		case l:
			x -= xlength;
//System.out.println("erro");
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
			x -= xlength; y -= ylength;
			break;
		case ld:
			x -= xlength; y += ylength;
			break;
		case rd:
			x += xlength; y += ylength;
			break;
		case ru:
			x += xlength; y -= ylength;
			break;
		case stop:
			break;
		}
		
		if(x < 0) x = 0;
		if(y < 36) y = 36;
		if(x + Tank.length >  k.Gamelength)  x = k.Gamelength - Tank.length;
		if(y + Tank.width > k.Gamewidth)    y = k.Gamewidth - Tank.width;
		if(this.di != Direction.stop){
			this.breeching = this.di;
		}
		if(!good){
			if(tankstep == 0){
				tankstep = r.nextInt(20) +1;
				Direction[] direction = di.values();
				int r2 = r.nextInt(direction.length );
				di = direction[r2];
				
			}
			if(r.nextInt(50) > 35){			
				fire();
			}
			tankstep --;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key){
				case KeyEvent.VK_LEFT:
					xl = true;
					break;
				case KeyEvent.VK_RIGHT:
					xr = true;
					break;
				case KeyEvent.VK_UP:
					xu = true;
					break;
				case KeyEvent.VK_DOWN:
					xd = true;
					break;
					
			}
			changeDirection();
		}
	public void keyReleased(KeyEvent e) {
		  int key = e.getKeyCode();
			switch(key){
			case KeyEvent.VK_CONTROL:
				fire();
				break;
				case KeyEvent.VK_LEFT:
					xl = false;
					break;
				case KeyEvent.VK_RIGHT:
					xr = false;
					break;
				case KeyEvent.VK_UP:
					xu = false;
					break;
				case KeyEvent.VK_DOWN:
					xd = false;
					break;
				case KeyEvent.VK_Z:
					aroundfires();
					break;	
				case KeyEvent.VK_C:
					if(!this.live){
						this.setLive(true);
						this.x = 400;
						this.y = 300;
						this.setLife(100);
						break;
					}
				case KeyEvent.VK_K:
					k.setKuanglive(true);
					break;	
			}
		changeDirection();
	}
	void changeDirection(){
		if(xl && !xr && !xu && !xd) di = Direction.l;
		if(!xl && xr && !xu && !xd) di = Direction.r;
		if(!xl && !xr && xu && !xd) di = Direction.u;
		if(!xl && !xr && !xu && xd) di = Direction.d;
		if(xl && !xr && xu && !xd) di = Direction.lu;
		if(xl && !xr && !xu && xd) di = Direction.ld;
		if(!xl && xr && xu && !xd) di = Direction.ru;
		if(!xl && xr && !xu && xd) di = Direction.rd;
		if(!xl && !xr && !xu && !xd) di = Direction.stop;
		
	}
	public Cannon fire(){
		if(!live)
			return null;
		int x = this.x + Tank.length/2 - Cannon.length/2;
		int y = this.y + Tank.width/2 -Cannon.length/2;
		Cannon c = new Cannon(x , y , breeching, good , k);
		k.cannons.add(c);
		
		k.cannons.remove(this);
		return c;
		
	}
	
	public Cannon aroundfire(Direction di){
		if(!live)
			return null;
		int x = this.x + Tank.length/2 - Cannon.length/2;
		int y = this.y + Tank.width/2 -Cannon.length/2;
		Cannon c = new Cannon(x , y , di, good , k);
		k.cannons.add(c);
		k.cannons.remove(this);
		return c;

	}
	
	public void aroundfires(){
		Direction di[] = Direction.values();
		for (int i = 0; i < 8 ;i ++ ){
			 aroundfire(di[i]);
		}
	}
	
	public void fantan(){
		x = this.oldx ;
		y = this.oldy;
	}
	
	public Rectangle touch(){
		return new Rectangle(x,y,length,width);
		
	}
	
	public boolean tankhitwall(Wall w){
		if(this.live &&this.touch().intersects(w.touch())){
			this.fantan();
			return true;
		}
		return false;
	}
	
	
	public boolean eatblood(Blood b){
		if(this.live &&this.touch().intersects(b.touch())){
			this.setLife(100);
			b.setLive(false);
			return true;
		}
		return false;
	}
	
	public boolean tanktank(java.util.List <Tank> tanks ){
		for(int i = 0; i<tanks.size();i++){
			Tank t = tanks.get(i);
			if(this != t){
				if(this.touch().intersects(t.touch()) && t.isLive() && this.live){
				//this.fantan();
				t.fantan();
				return true;
				}
			}	
		}
		return false;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	public class BloodStick{
		public void draw(Graphics g){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(x, y - 30, length, width -20);
			int bloodlength = length * getLife()/100;
			g.fillRect(x, y - 30, bloodlength, width -20);
			g.setColor(c);
		}
		
	}
}
