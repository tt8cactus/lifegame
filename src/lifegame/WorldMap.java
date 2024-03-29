package lifegame;

import java.util.Random;

public class WorldMap {
    private final static int x=22;
    private final static int y=22;
    
    public static Cell [][]initial(Cell [][]cell) {
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			cell[i][j]=new Cell();
    			Random random=new Random();
    			cell[i][j].setStatus(random.nextInt(2));
    		}
    	}
    	return cell;
    }

    public static int update(Cell[][] cell){
    	int count=0;
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			int status=cell[i][j].getStatus();
    			cell[i][j].UpdateStatus();
    			if(status==cell[i][j].getStatus())
    				count++;
    		}
    	}
    	return count;
    }
    
    public static void getLiving(Cell [][]cell){
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			int living=0;
    			
    			if(i>0&&j>0) living+=cell[i-1][j-1].getStatus();
    			if(i>0) living+=cell[i-1][j].getStatus();
    			if(j>0) living+=cell[i][j-1].getStatus();
    			
    			if(i<x-1&&j<x-1) living+=cell[i+1][j+1].getStatus();
    			if(i<x-1) living+=cell[i+1][j].getStatus();
    			if(j<x-1) living+=cell[i][j+1].getStatus();
    			
    			if(i<x-1&&j>0) living+=cell[i+1][j-1].getStatus();
    			if(i>0&&j<x-1) living+=cell[i-1][j+1].getStatus();
    			
    			cell[i][j].setLiving(living);
    		}
    	}
    }
      
    public static void showMap(Cell[][] cell) {
    	for(int i=0;i<x;i++) {
    		for(int j=0;j<y;j++) {
    			System.out.print(cell[i][j].getStatus()+" ");
    		}
    	}
    }
}
