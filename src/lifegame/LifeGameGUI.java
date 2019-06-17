package lifegame;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LifeGameGUI
{
	
	private void draw(Graphics g,Cell[][] cell)//���ƽ����е�ϸ��
	{
    	for(int i=0;i<22;i++)//��Ϊ�� 
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
    			if(cell[i][j].getStatus()==1)//ϸ����Ϊ��
    			{
    				g.fillRect(i*25, j*25, 25, 25);
    			}
    			else//ϸ����Ϊ��
    			{
    				g.drawRect(i*25, j*25, 25, 25);
    			}
    		}
    	}
	}
	
	LifeGameGUI()
    {    	
		int count=0;
    	
    	//��ʼ��ϸ����
    	Cell [][]cell=new Cell[22][22];
    	WorldMap.initial(cell);
    	WorldMap.getLiving(cell);
    	
    	//��ʼ��GUI
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

		//����Ĭ�ϼ��200ms
		int unchange;
    	long t1= System.currentTimeMillis();
    	while(true) 
    	{
    		long t2= System.currentTimeMillis();
    		if(t2-t1>200)
    		{
    			t1=t2;
    			
    			//�����ػ�
    			jframe.repaint();
    			unchange=WorldMap.update(cell);//����ϸ����
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