import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Board.Cell;
import junit.framework.TestCase;

public class RedPlayerTest extends TestCase{
	private Board board;
	
	@Before
	public void setUp() throws Exception{
		board = new Board();
	}
	
	@Test
	public void testRedplayerTurnMoveVacantCell() {
		board.makeOmove(1, 1);
		board.makeOmove(2, 2);
		Assertions.assertTrue( board.getCell(2, 2) == Board.Cell.O_PLACEMENT);
		assertEquals("", board.changeTurn(), "Blue player");
	}
	
	public void testRedplayerTurnMoveVacantCellS() {
		board.makeSmove(0, 0);
		board.makeSmove(1, 1);
		Assertions.assertTrue( board.getCell(1, 1) == Board.Cell.S_PLACEMENT);
		assertEquals("", board.changeTurn(), "Blue player");
	}

	// acceptance criterion 4.2 illegal move
	@Test
	public void testRedplayerTurnMoveNonVacantCell() {
		board.makeSmove(0, 0);
		assertEquals("", board.getCell(0,0),Board.Cell.S_PLACEMENT);
		assertEquals("", board.changeTurn(), "Red player");
		board.makeSmove(0, 0);
		assertEquals("", board.changeTurn(), "Red player");
	}
	
	//acceptance criterion 4.3 move outside the board, invalid row
	@Test
	public void testRedplayerTurnInvalidRowMove() {
		board.makeSmove(3, 0);
		board.makeSmove(8, 0);
		assertEquals("", board.changeTurn(), "Red player");	
	}
	
	//acceptance criterion 4.3 move outside the board, invalid column
		@Test
		public void testBlueplayerTurnInvalidColumnMove() {
			board.makeSmove(0, 0);
			board.makeSmove(0, 9);
			assertEquals("", board.changeTurn(), "Red player");	
		}


		
		@Test 
		public void testsetRpScore() {
			
			assertEquals("", board.setRpScore(0), 1);
		}
		
		@Test 
		public void testgetBpScore() {
			assertEquals("", board.getRpScore(), 0);
		}
		
		@Test
		public void testgeneralCheckTest() {
			board.generalRedCheck(0, 0, 3);
			board.generalBlueCheck(0, 1, 3);
			board.generalRedCheck(0, 2, 3);
			assertEquals("", board.getRpScore(), 0); 
		}
	
		@Test
		public void testsimpleTestHor() {
		board.makeSmove(0, 0);
		board.makeOmove(0, 1);
		board.makeSmove(0, 2);
		assertEquals("", board.simpleCheck("Red player"), true);
		assertEquals("", board.getGameState(), Board.GameState.BLUE_WON); 
		
		}
		@Test
		public void testsimpleTestVer() {
		board.makeSmove(0, 0);
		board.makeOmove(1,0);
		board.makeSmove(2,0);
		assertEquals("", board.simpleCheck("Red player"), true);
		assertEquals("", board.getGameState(), Board.GameState.BLUE_WON); 
		
		}
		@Test
		public void testsimpleTestDiag() {
		board.makeSmove(0, 0);
		board.makeOmove(1, 1);
		board.makeSmove(2, 2);
		assertEquals("", board.simpleCheck("Red player"), true);
		
		assertEquals("", board.getGameState(), Board.GameState.BLUE_WON); 
		
		}
		@Test
		public void testsimpleTestAntiDiag() {
		board.makeSmove(3, 3);
		board.makeOmove(4, 2);
		board.makeSmove(5, 1);
		assertEquals("", board.simpleCheck("Red player"), true);
		assertEquals("", board.getGameState(), Board.GameState.BLUE_WON); 
		
		}
}
