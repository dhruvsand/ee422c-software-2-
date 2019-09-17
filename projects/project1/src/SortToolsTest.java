//package assignment1;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class SortToolsTest {
	
	@Test(timeout = 2000)
	public void testFindFoundFull(){
		int[] x = new int[]{-2, -1, 0, 1, 2, 3};
		assertEquals(3, SortTools.find(x, 6, 1));
	}
	
	@Test(timeout = 2000)
	public void testInsertGeneralPartialEnd(){
		int[] x = new int[]{10, 20, 30, 40, 50};
		int[] expected = new int[]{10, 20, 30, 35};
		assertArrayEquals(expected, SortTools.insertGeneral(x, 3, 35));
		//assertArrayEquals(expected, x);
	}

	@Test
	public void testinsertInPlace() {
        int[] x =new int[5];
        x[0]=10;
        x[1]=20;
        x[2]=30;
        x[3]=40;
        assertEquals(4, SortTools.insertInPlace(x, 4, 30));

	}

    @Test
    public void testinsertSort() {

        int[] x = new int[]{50, 30, 20, 40, 10};
        SortTools.insertSort(x,5);
        int[] expected = new int[]{10, 20, 30, 40, 50};
        assertArrayEquals(expected, x);



    }
}
