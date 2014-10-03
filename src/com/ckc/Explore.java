package com.ckc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class Explore {
	
	int x, y;
	private boolean live = true;
	private Kuang k;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] p ={
		tk.getImage(Explore.class.getClassLoader().getResource("images/0.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/2.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/3.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/4.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/10.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/9.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/8.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/7.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/6.gif")),
		tk.getImage(Explore.class.getClassLoader().getResource("images/5.gif")),
	};
	int step = 0;
	private static boolean initial = false;
	public Explore(int x, int y, Kuang k) {
			this.x = x;
			this.y = y;
			this.k = k;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	public void draw(Graphics g){
		if(initial == false){
			for (int j = 0; j < p.length; j++) {
				g.drawImage(p[j], -50, -50, null);
			}
			initial = true;
		}
		if(!live){
			k.e.remove(this);
			return;
		}
		if(step == p.length){
			live = false;
			step = 0;
			return;
		}
		g.drawImage(p[step], x, y, null);
		step ++;
	}
}