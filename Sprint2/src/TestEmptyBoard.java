import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
public class TestEmptyBoard extends TestCase {
	private Board board = new Board(); // context test, create object board

	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
	
	}
	
	@Test // AC test empty board

	public void testNewBoard() {
		for(int row=0;row< board.getTotalRows();++row) {
			for(int column=0;column<board.getTotalColumns();++column) {
				assertEquals("",board.getCell(row,column),Board.Cell.EMPTY);
			}
		}

	}
	//AC 3.1 < invalid row index>
	@Test
	public void testInvalidRow() {
		assertEquals("", board.getCell(9, 0), null);	
	}
	
	//AC 3.2 <invalid column index>
	@Test
	public void testInvalidColumn() {
		assertEquals("", board.getCell(0, 9), null);
	}
	

}
