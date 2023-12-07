import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Board.Cell;
import Board.GameState;
import junit.framework.TestCase;

public class TestBlueplayerMoves extends TestCase {
	
	private Board board;
	

	
	@Before
	public void setUp() throws Exception{
		board = new Board();
	}

	/*@After
	public void tearDows() throws Exception{
		
	}*/
	// acceptance criterion 4.1


	@Test
	public void testBlueplayerTurnMoveVacantCell() {
		board.makeOmove(0, 0);
		Assertions.assertTrue( board.getCell(0, 0) == Board.Cell.O_PLACEMENT);
		assertEquals("", board.changeTurn(), "Red player");
	}
	
	public void testBlueplayerTurnMoveVacantCellS() {
		board.makeSmove(0, 0);
		Assertions.assertTrue( board.getCell(0, 0) == Board.Cell.S_PLACEMENT);
		assertEquals("", board.changeTurn(), "Red player");
	}

	// acceptance criterion 4.2 illegal move
	@Test
	public void testBlueplayerTurnMoveNonVacantCell() {
		board.makeSmove(0, 0);
		board.makeSmove(1, 0);
		assertEquals("", board.getCell(1,0),Board.Cell.S_PLACEMENT);
		assertEquals("", board.changeTurn(), "Blue player");
		board.makeSmove(0, 0);
		assertEquals("", board.changeTurn(), "Blue player");
	}
	
	//acceptance criterion 4.3 move outside the board, invalid row
	@Test
	public void testBlueplayerTurnInvalidRowMove() {
		board.makeSmove(9, 0);
		assertEquals("", board.changeTurn(), "Blue player");	
	}
	
	//acceptance criterion 4.3 move outside the board, invalid column
		@Test
		public void testBlueplayerTurnInvalidColumnMove() {
			board.makeSmove(0, 9);
			assertEquals("", board.changeTurn(), "Blue player");	
		}
		
		@Test
		public void testBlueplayerTurnisFull() {
			
			board.makeOmove(0, 0);
			assertEquals("", board.isFull(), false);
			
			for (int row = 0; row < board.getTotalRows(); ++row) {
				for (int col = 0; col < board.getTotalColumns(); ++col) {
					board.grid[row][col]=(Board.Cell.S_PLACEMENT);
				}
			}
			assertEquals("", board.isFull(), true);
		}

		
		@Test 
		public void testsetBpScore() {
			
			assertEquals("", board.setBpScore(0), 1);
		}
		
		@Test 
		public void testgetBpScore() {
			assertEquals("", board.getBpScore(), 0);
		}
		
		@Test
		public void testIsDraw() {
			
			board.makeOmove(0, 0);
			assertEquals("", board.isDraw(), false);
			for (int row = 0; row < board.getTotalRows(); ++row) {
				for (int col = 0; col < board.getTotalColumns(); ++col) {
					board.grid[row][col] = Board.Cell.S_PLACEMENT;
				}
			}
			assertEquals("", board.isDraw(), true);
		}
		
		@Test
		public void testgeneralCheckTest() {
			board.generalBlueCheck(0, 0, 3);
			board.generalRedCheck(0, 1, 3);
			board.generalBlueCheck(0, 2, 3);
			assertEquals("", board.getBpScore(), 0);
		}
	
		@Test
		public void testsimpleTestHor() {
		board.makeSmove(0, 0);
		board.makeOmove(0, 1);
		board.makeSmove(0, 2);
		assertEquals("", board.simpleCheck("Blue player"), true);
		assertEquals("", board.getGameState(), Board.GameState.RED_WON); 
		
		}
		@Test
		public void testsimpleTestVer() {
		board.makeSmove(0, 0);
		board.makeOmove(1,0);
		board.makeSmove(2,0);
		assertEquals("", board.simpleCheck("Blue player"), true);
		assertEquals("", board.getGameState(), Board.GameState.RED_WON); 
		
		}
		@Test
		public void testsimpleTestDiag() {
		board.makeSmove(0, 0);
		board.makeOmove(1, 1);
		board.makeSmove(2, 2);
		assertEquals("", board.simpleCheck("Blue player"), true);
		assertEquals("", board.getGameState(), Board.GameState.RED_WON);
		}
		@Test
		public void testsimpleTestAntiDiag() {
		board.makeSmove(3, 3);
		board.makeOmove(4, 2);
		board.makeSmove(5, 1);
		assertEquals("", board.simpleCheck("Blue player"), true);
		assertEquals("", board.getGameState(), Board.GameState.RED_WON);
		}
}
