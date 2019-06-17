package lifegame;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LifeGameGUI
{
	
	private void draw(Graphics g,Cell[][] cell)//绘制界面中的细胞
	{
    	for(int i=0;i<22;i++)//底为白 
    	{
    		for(int j=0;j<22;j++) 
    		{
    			g.drawRect(i*25, j*25, 25, 25);
    		}
    	}
    	for(int i=0;i<22;i++)
    	{
    		for(int j=0;j<22;j++)
    		{
    			if(cell[i][j].getStatus()==1)//细胞生为黑
    			{
    				g.fillRect(i*25, j*25, 25, 25);
    			}
    			else//细胞亡为白
    			{
    				g.drawRect(i*25, j*25, 25, 25);
    			}
    		}
    	}
	}
	
	LifeGameGUI()
    {    	
		int count=0;
    	
    	//初始化细胞们
    	Cell [][]cell=new Cell[22][22];
    	WorldMap.initial(cell);
    	WorldMap.getLiving(cell);
    	
    	//初始化GUI
    	JFrame jframe=new JFrame();
    	jframe.setSize(460,460);
    	jframe.setTitle("ROUND"+count);
    	jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	JPanel jpanel=new JPanel();
    	jpanel.setBounds(0,0,400,400);
    	jframe.add(jpanel);
    	jframe.setVisible(true);
    	
    	Graphics g=jpanel.getGraphics();
    	draw(g,cell);

		//动画默认间隔200ms
		int unchange;
    	long t1= System.currentTimeMillis();
    	while(true) 
    	{
    		long t2= System.currentTimeMillis();
    		if(t2-t1>200)
    		{
    			t1=t2;
    			
    			//界面重绘
    			jframe.repaint();
    			unchange=WorldMap.update(cell);//更新细胞们
    			WorldMap.getLiving(cell);
    			draw(g,cell);
    		    count++;
    		    jframe.setTitle("ROUND"+count);
    			
    			if(unchange==22*22)
    				break;
    			if(count>1000) 
    				break;
    		}	    	
    	}
   	}
}