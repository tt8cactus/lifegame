package lifegame;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldMapTest {
	private static Cell [][]cell=new Cell[22][22];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		WorldMap.initial(cell);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testUpdate() {
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				cell[i][j].setStatus(1);
			}
		}
		WorldMap.getLiving(cell);
		assertEquals(4,WorldMap.update(cell));
		
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				cell[i][j].setStatus(0);
			}
		}
		WorldMap.getLiving(cell);
		assertEquals(484,WorldMap.update(cell));
	}

	@Test
	public void testGetLiving() {
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				cell[i][j].setStatus(1);
			}
		}
		WorldMap.getLiving(cell);
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				if(i>0&&i<21&&j>0&&j<21)
					assertEquals(8,cell[i][j].getLiving());
				else if(((i==0||i==21)&&(j>0&&j<21))||((j==0||j==21)&&(i>0&&i<21)) )
					assertEquals(5,cell[i][j].getLiving());
				else
					assertEquals(3,cell[i][j].getLiving());
			}
		}
		
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				cell[i][j].setStatus(0);
			}
		}
		WorldMap.getLiving(cell);
		for(int i=0;i<22;i++) {
			for(int j=0;j<22;j++) {
				assertEquals(0,cell[i][j].getLiving());
			}
		}
	}

}
