package com.ckc;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class Kuang extends Frame {
	public static final int Gamelength = 800;
	public static final int Gamewidth = 600;
	Image backScreen = null;
	int j =1; int guanshu = 1;
	Wall wall = new Wall(600,300,this);
	Tank tank = new Tank(400,300,true,Direction.d.stop,this);
	Blood b = new Blood();
	//Tank atitank = new Tank(200,300,false,this);
	List <Cannon> cannons = new ArrayList<Cannon>();
	List <Explore> e = new ArrayList<Explore>();
	List <Tank> atitanks = new ArrayList<Tank>();
	public boolean kuanglive = false;
	//static{System.out.println("q");}    能够不用方法就能直接打印的代码
	
	public boolean isKuanglive() {
		return kuanglive;
	}

	public void setKuanglive(boolean kuanglive) {
		this.kuanglive = kuanglive;
	}

	public static void main (String[] args) {	
		Kuang k = new Kuang();
		//if(k.isKuanglive())
		k.Struct();	
		//System.out.println("erro");
	}
	
	public void Struct() {
		/*for(int x = 50; x<600;x =x+50){
			Tank a = new Tank(x,100,false,Tank.Direction.d,this);*/ //之前自己写的
		int TNumber =Integer.parseInt(PropertiesClient.getProperty("intialTNumber")) ;
		for(int i=0; i< TNumber; i++) {
		atitanks.add(new Tank(50 + 60*(i+1), 80, false, Direction.d, this));
		}
		//atitanks.add(a);
		
		setBounds(300,100,Gamelength,Gamewidth);
		setVisible(true);
		setBackground(Color.black);
		setTitle("TankWar");
		//setEnabled(false);
		this.setResizable(false);
		this.addWindowListener (
			    new WindowAdapter() {
			      public void windowClosing(WindowEvent e) {
			        setVisible(false);
			        System.exit(-1);
			      }
			    });	
		addKeyListener(new KeyMonitor());
		//while(true){
			//repaint();}结论   速度之快  无法肉眼！
		new Thread(new Paintagain()).start();
		
		
	}
	
	public void paint(final Graphics g) {
		tank.draw(g);
	   	tank.eatblood(b);
	    wall.draw(g);
	    b.draw(g);
		/*Timer timer = new Timer();
	    timer.schedule(new TimerTask() {
	       public void run() {
	    	
	    }
	 },  10*100);*/  //定时器，。由于repaint 存在，所以不能用
		
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.drawString("Cannon Number :"+cannons.size(), 20, 50);
		g.drawString("Explore Number :" + e.size(), 20, 63);
		g.drawString("atiTanks Number :" + atitanks.size(), 20, 76);
		g.drawString("Mylife :" + tank.getLife(), 20, 89);
		g.drawString("The  " + guanshu + "  guan ", 20, 102);
		g.setColor(c);
		if (j == 3&&atitanks.size()==0){//关数完结   打印胜利   自己添加
			g.setColor(Color.yellow);
			g.drawString("vicroy ", 400, 300);
			g.setColor(c);
		}
		if(j < 3){
			
			if(atitanks.size()==0){
				for(int i=0; i<5 +j+3; i++) {
					atitanks.add(new Tank(50 + 60*(i+1), 80, false,Direction.d,this));
					}
					j++;guanshu ++;
					
				
			}	
		}
		
		if(!tank.isLive()){
			
		}
		for(int i = 0; i < e.size(); i++){
			Explore explores = e.get(i);
			explores.draw(g);
		}
		
		for(int i = 0; i < atitanks.size(); i ++){
			Tank atitank = atitanks.get(i);
			atitank.tankhitwall(wall);
			atitank.tanktank(atitanks);
			atitank.draw(g);
		}
		
		for(int i = 0; i < cannons.size(); i ++){
			Cannon cannon = cannons.get(i);
			cannon.whetherhit(atitanks);
			cannon.whetherhit(tank);
			wall.hitwall(cannon);
			cannon.draw(g);
		}
		
	}
	
	public void update(Graphics g) {
		if(backScreen == null) {
			backScreen = this.createImage(Gamelength,Gamewidth);
		}
		
		Graphics getbackScreen = backScreen.getGraphics();
		Color c = getbackScreen.getColor();
		getbackScreen.setColor(Color.black);
		getbackScreen.fillRect(0, 0, Gamelength, Gamewidth);
		paint(getbackScreen);
		g.drawImage(backScreen,0,0,null);
	}
	
	private class Paintagain implements Runnable{

		public void run() {
				while(true){
					repaint();
					try {
						Thread.sleep(45);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
		}
	}
	
	
	class KeyMonitor extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			tank.keyReleased(e);
		}
		
		public void keyPressed(KeyEvent k) {
			tank.keyPressed(k);
		}
	}
	
}
