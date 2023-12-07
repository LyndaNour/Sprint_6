import java.awt.BorderLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import Board.Cell;

public class Board {
	Scanner scan = new Scanner(System.in);
	
	public enum Cell{
		EMPTY, S_PLACEMENT ,O_PLACEMENT
		}
	
	public enum GameState {
		PLAYING, DRAW, BLUE_WON, RED_WON
	}
	
	public enum ScoreState{
		DRAW_SCORE, BLUE_SCORE, RED_SCORE
	}
	
	private ScoreState currentScoreState;
	
	private Board.GameState currentGameState;
	
	protected int TOTALROWS = 6; // total rows 
	protected int TOTALCOLUMNS = 6; // total columns
	protected Cell[][] grid;

	private String turn = "Blue player";
	int bpScore = 0;
	int rpScore = 0;
	
	FileWriter writer;
	Random rand;
	public Board() { //constructor	
		grid = new Cell [TOTALROWS][TOTALCOLUMNS]; //initialization
		initBoard();
	}
	
	//initialize the game
	public void initBoard() {
		for(int row =0;row<TOTALROWS;row++) {
			for(int column=0;column<TOTALCOLUMNS;column++) {
				grid[row][column] = Cell.EMPTY;
			}
		}
		currentGameState = GameState.PLAYING;
		currentScoreState = ScoreState.DRAW_SCORE;
		turn = "Blue player";
	}
	
	public void setRows( int NEWTOTALROWS) {
		TOTALROWS = NEWTOTALROWS;
	}
	public void setColumns(int NEWTOTALCOLUMNS) {
		TOTALCOLUMNS = NEWTOTALCOLUMNS;
	}
	
	public int getTotalRows() {
		return TOTALROWS;
	}
	
	public int getTotalColumns() {
		return TOTALCOLUMNS;
	}
	
	// return the content of that cell
	public Cell getCell(int row, int column) {
		if(row>=0 && row<TOTALROWS && column>=0 && column<TOTALCOLUMNS) {
			return grid[row][column];		
		}else {
			return null;
		}
	}


	public String changeTurn() {
		return turn;
	}
	
	public static int save() {
		
		return 1;
		}


	//make s move 
	public void makeSmove(int row, int column) {
		if(row>=0 && row<TOTALROWS && column>=0 && column< TOTALCOLUMNS && grid[row][column]== Cell.EMPTY) {
			grid[row][column] = Cell.S_PLACEMENT;
			turn = (turn =="Blue player")? "Red player" : "Blue player";
		}

	}
	//make o move 
	public void makeOmove(int row, int column) {
		if(row>=0 && row<TOTALROWS && column>=0 && column< TOTALCOLUMNS && grid[row][column]== Cell.EMPTY) {
			grid[row][column] = Cell.O_PLACEMENT;
			turn = (turn =="Blue player")? "Red player" : "Blue player";	
		}
	}
	
	public boolean isOccupied(int row, int column) {
		if(grid[row][column]!= Cell.EMPTY) {
			System.out.println("occupied!!");
			
			
			return true;
		}
		return false;
	}
	public int setBpScore(int points) {
		 bpScore = points+1;
		
		 return bpScore;
	}
	public int getBpScore() {
		return bpScore;
	}

	public int setRpScore(int points) {
		rpScore = points+1;
		return rpScore;
	}
	public int getRpScore() {
		return rpScore;
	}
	
	// board is full
	
	public boolean isFull() {
		for(int i=0;i<TOTALROWS;i++) {
			for(int j=0;j<TOTALCOLUMNS;j++) {
				if(grid[i][j] == Cell.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}
	
	// no winner 
	public boolean isDraw() {
		for (int row = 0; row < TOTALROWS; ++row) {
			for (int col = 0; col < TOTALCOLUMNS; ++col) {
				if (grid[row][col] == Cell.EMPTY) {
					return false; // an empty cell found, not draw
				}
			}
		}
		currentGameState = GameState.DRAW;
		return true;
	}

	
	// check horizontalCheck
	/*
	 * S O S
	 */

	  public boolean simpleCheck(String turn) {
		  
		  String winner = null;
	       for (int i = 0; i < TOTALROWS ; i++) {
	            for (int j = 0; j < TOTALCOLUMNS-2 ; j++) {
	                if (grid[i][j].equals(Cell.S_PLACEMENT) && grid[i][j + 1].equals(Cell.O_PLACEMENT) && grid[i][j + 2].equals(Cell.S_PLACEMENT)) {
	                	//winner =turn;
	                	
	                	if(turn =="Blue player") {
	                		winner ="Red player";
	                		currentGameState = GameState.RED_WON;
	                	}
	                		
	                		
	                	else {
	                		winner ="Blue player";
	                		currentGameState = GameState.BLUE_WON;
	                	}
	                	
	                	return true;
	                }
	            }     
	        }
	           // check vertical 
	        /*
	         * S
	         * O
	         * S
	         */
            for (int i = 0; i < TOTALROWS-2; i++) {
	            for (int j = 0; j < TOTALCOLUMNS; j++) {
	                if (grid[i][j].equals(Cell.S_PLACEMENT) && grid[i + 1][j].equals(Cell.O_PLACEMENT) && grid[i + 2][j].equals(Cell.S_PLACEMENT)) {
	                    winner = turn;
	                    
	                    if(turn =="Red player") {
	                    	winner = "Blue player";
	    	                currentGameState = GameState.BLUE_WON;
	                    }
	                    
	                    else {
	                    	currentGameState = GameState.RED_WON;
	                    }
	                    return true;
	                }
	            }
	        }
            // check diagonals 
            /* S
             * 	  O
             * 		S
             */
                for (int i = 0; i < TOTALROWS- 2; i++) {
                    for (int j = 0; j < TOTALCOLUMNS- 2; j++) {
                        if (grid[i][j].equals(Cell.S_PLACEMENT) && grid[i + 1][j + 1].equals(Cell.O_PLACEMENT) && grid[i + 2][j + 2].equals(Cell.S_PLACEMENT)) {
                            winner = turn; 
                            
                            if(turn =="Red player") {
    	                    	winner = "Blue player";
    	    	                currentGameState = GameState.BLUE_WON;
    	                    }
    	                    
    	                    else {
    	                    	currentGameState = GameState.RED_WON;
    	                    }
                        
    	                    return true;
                        }
                    }
                }
                
                // check anti diagonal 
                /*
                 * 			S
                 * 		O
                 * 	S
                 */
                
                for (int i = 0; i < TOTALROWS - 2; i++) {
                    for (int j = 2; j < TOTALCOLUMNS; j++) {
                        if (grid[i][j].equals(Cell.S_PLACEMENT) && grid[i + 1][j - 1].equals(Cell.O_PLACEMENT) && grid[i + 2][j - 2].equals(Cell.S_PLACEMENT)) {
                        	winner = turn;
                        	
                        	 if(turn =="Red player") {
     	                    	winner = "Blue player";
     	    	                currentGameState = GameState.BLUE_WON;
     	                    }
     	                    
     	                    else {
     	                    	currentGameState = GameState.RED_WON;
     	                    }
       	                    return true;
                        }
                    }
                }
                // check the first above diagonal 
                /*
                 *  -	S	-	-
                 *  -	-	O	-
                 *  -	-	-	S
                 *  -	-	-	-
                 */ 
                for(int i =0; i<TOTALROWS-3; i++) {
                	  if (grid[i][i+1].equals(Cell.S_PLACEMENT) && grid[i+1][i+2].equals(Cell.O_PLACEMENT) && grid[i+2][i+3].equals(Cell.S_PLACEMENT)) {
                		  winner = turn;
                		  
                		  if(turn =="Red player") {
  	                    	winner = "Blue player";
  	    	                currentGameState = GameState.BLUE_WON;
  	                    }
  	                    
  	                    else {
  	                    	currentGameState = GameState.RED_WON;
  	                    }
     	                    return true;
                	  }
                }
                
                // check the first below diagonal 
                /*
                 *  	-	-	-	-
                 *  	S	-	-	-
                 *  	-	O	-	-
                 *  	-	-	S	-
                 */
            	   for(int i =0; i<TOTALROWS-3; i++) {
                  	  if (grid[i+1][i].equals(Cell.S_PLACEMENT) && grid[i+2][i+1].equals(Cell.O_PLACEMENT) && grid[i+3][i+2].equals(Cell.S_PLACEMENT)) {
                  		  winner = turn;
                  		  
                  		 if(turn =="Red player") {
 	                    	winner = "Blue player";
 	    	                currentGameState = GameState.BLUE_WON;
 	                    }
 	                    
 	                    else {
 	                    	currentGameState = GameState.RED_WON;
 	                    }
       	                    return true;
                  	  }
            	   }
            	   
         	      // check the second above diagonal 
                /*
                 *  	-	-	S	-	-
                 *  	-	-	-	O	-
                 *  	-	-	-	-	S
                 *  	-	-	-	-	-
                 *  	-	-	-	-	-
                 */
            	   for(int i =0; i<TOTALROWS-4; i++) {
                  	  if (grid[i][i+2].equals(Cell.S_PLACEMENT) && grid[i+1][i+3].equals(Cell.O_PLACEMENT) && grid[i+2][i+4].equals(Cell.S_PLACEMENT)) {
                  		  winner = turn;
                  		 if(turn =="Red player") {
 	                    	winner = "Blue player";
 	    	                currentGameState = GameState.BLUE_WON;
 	                    }
 	                    
 	                    else {
 	                    	currentGameState = GameState.RED_WON;
 	                    }
       	                    return true;
                  	  }
            	   }
        
               	   
         	      // check the second below diagonal 
                /*
                 *  	-	-	-	-	-
                 *  	-	-	-	-	-
                 *  	S	-	-	-	-
                 *  	-	O	-	-	-
                 *  	-	-	S	-	-
                 */
            	   for(int i =0; i<TOTALROWS-4; i++) {
                  	  if (grid[i+2][i].equals(Cell.S_PLACEMENT) && grid[i+3][i+1].equals(Cell.O_PLACEMENT) && grid[i+4][i+2].equals(Cell.S_PLACEMENT)) {
                  		  winner = turn;
                  		  
                  		 if(turn =="Red player") {
 	                    	winner = "Blue player";
 	    	                currentGameState = GameState.BLUE_WON;
 	                    }
 	                    
 	                    else {
 	                    	currentGameState = GameState.RED_WON;
 	                    }
       	                    return true;
                  	  }
            	   }
            	      // check the third above diagonal 
                   /*
                    *  	-	-	-	S	-	-
                    *  	-	-	-	-	O	-
                    *  	-	-	-	-	-	S
                    *  	-	-	-	-	-	-
                    *  	-	-	-	-	-	-
                    *  	-	-	-	-	-	-
                    */
               	   for(int i =0; i<TOTALROWS-5; i++) {
                     	  if (grid[i][i+3].equals(Cell.S_PLACEMENT) && grid[i+1][i+4].equals(Cell.O_PLACEMENT) && grid[i+2][i+5].equals(Cell.S_PLACEMENT)) {
                     		  winner = turn;
                     		  
                     		 if(turn =="Red player") {
     	                    	winner = "Blue player";
     	    	                currentGameState = GameState.BLUE_WON;
     	                    }
     	                    
     	                    else {
     	                    	currentGameState = GameState.RED_WON;
     	                    }
          	                    return true;
                     	  }
               	   }
                   // check the third below diagonal 
                   /*
                    *  	-	-	-	-	-	-
                    *  	-	-	-	-	-	-
                    *  	-	-	-	-	-	-
                    *  	-	-	-	-	-	-
                    *  	S	-	-	-	-	-
                    *  	- 	O	-	-	-	-
                    *  	-	-	S	-	-	-
                    */
               	   for(int i =0; i<TOTALROWS-5; i++) {
                     	  if (grid[i+3][i].equals(Cell.S_PLACEMENT) && grid[i+4][i+1].equals(Cell.O_PLACEMENT) && grid[i+5][i+2].equals(Cell.S_PLACEMENT)) {
                     		  winner = turn;
                     		 if(turn =="Red player") {
     	                    	winner = "Blue player";
     	    	                currentGameState = GameState.BLUE_WON;
     	                    }
     	                    
     	                    else {
     	                    	currentGameState = GameState.RED_WON;
     	                    }
          	                    return true;
                     	  }
               	   }
                  
	        return false; 
	    }

	 
	  public int generalRedCheck(int row, int col, int size) {
		  
			 /* S O S
				 */
		  
		  if(col <= size-3) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row][col+1].equals(Cell.O_PLACEMENT) && grid[row][col +2].equals(Cell.S_PLACEMENT) ) {
				  setRpScore(rpScore);
			  }
		  }
		  if(col >= 2 )
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row][col-1].equals(Cell.O_PLACEMENT) && grid[row][col -2].equals(Cell.S_PLACEMENT)) {
				  setRpScore(rpScore);
				
			  }
		  if(col >=1 && col <= size-2) {
			  if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row][col-1].equals(Cell.S_PLACEMENT) && grid[row][col + 1].equals(Cell.S_PLACEMENT)) {
				  setRpScore(rpScore);
			  }
		  }
		  
		  // check columns 
		  /*
		   * S
		   * O
		   * S
		   */
		  
		  if(row <= size-3) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row+1][col].equals(Cell.O_PLACEMENT) && grid[row +2][col].equals(Cell.S_PLACEMENT) ) {
				  setRpScore(rpScore);
			  }
		  }
		  if(row >= 2 ) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col].equals(Cell.O_PLACEMENT) && grid[row -2][col].equals(Cell.S_PLACEMENT)) {
				  rpScore ++;
			  }
		  }
		  if(row >=1 && row <= size-2) {
			  if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col].equals(Cell.S_PLACEMENT) && grid[row +1][col].equals(Cell.S_PLACEMENT)) {
				  rpScore ++;
			  }
		  }
		  
		  // check diagonal
		  /*
		   * S		
		   * 	O
		   * 		S
		   */
		  
	  if(row <= size -3 && col <= size -3) {
		  	if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row +1 ][col +1].equals(Cell.O_PLACEMENT) && grid[row +2][col+2].equals(Cell.S_PLACEMENT)) {
			  rpScore ++;
		  	}
	  }
	  
	  else if(row >= 2 && col >= 2) {
		  	if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col -1].equals(Cell.O_PLACEMENT) && grid[row -2][col-2].equals(Cell.S_PLACEMENT)) {
			  rpScore ++;
		  	}
	  }
	  if(row >=1 && row <= size-2 && col >=1 && col<=size-2) {
			if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col -1].equals(Cell.S_PLACEMENT) && grid[row +1][col+1].equals(Cell.S_PLACEMENT)) {
				  rpScore ++;
			}
	  }
	  
	  // check anti diagonal
	  if(row >= size-3 && col <= size-3) {
		  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col +1].equals(Cell.O_PLACEMENT) && grid[row -2][col+2].equals(Cell.S_PLACEMENT)) {
			  rpScore ++;
		  	}
	  }
	  
	  if( col <=size-2 && col >=1) {
		  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row +1 ][col -1].equals(Cell.O_PLACEMENT) && grid[row +2][col-2].equals(Cell.S_PLACEMENT)) {
			  rpScore ++;
		  	}
	  }
	  
	  if(row >=1 && row <= size-2 && col >=1 && col<=size-2) {
			if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col +1].equals(Cell.S_PLACEMENT) && grid[row +1][col-1].equals(Cell.S_PLACEMENT)) {
				  rpScore ++;
			}
	  }
	  
	  return getRpScore();
	  }
	  public int generalBlueCheck(int row, int col, int size) {

		  
			 /* S O S
				 */
		  
		  if(col <= size-3) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row][col+1].equals(Cell.O_PLACEMENT) && grid[row][col +2].equals(Cell.S_PLACEMENT) ) {
				  setBpScore(bpScore);
			  }
		  }
		  if(col >= 2 )
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row][col-1].equals(Cell.O_PLACEMENT) && grid[row][col -2].equals(Cell.S_PLACEMENT)) {
				  setBpScore(bpScore);
				
			  }
		  if(col >=1 && col <= size-2) {
			  if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row][col-1].equals(Cell.S_PLACEMENT) && grid[row][col + 1].equals(Cell.S_PLACEMENT)) {
				  setBpScore(bpScore);
			  }
		  }
		  
		  // check columns 
		  /*
		   * S
		   * O
		   * S
		   */
		  
		  if(row <= size-3) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row+1][col].equals(Cell.O_PLACEMENT) && grid[row +2][col].equals(Cell.S_PLACEMENT) ) {
				  setBpScore(bpScore);
			  }
		  }
		  if(row >= 2 ) {
			  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col].equals(Cell.O_PLACEMENT) && grid[row -2][col].equals(Cell.S_PLACEMENT)) {
				  bpScore ++;
			  }
		  }
		  if(row >=1 && row <= size-2) {
			  if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col].equals(Cell.S_PLACEMENT) && grid[row +1][col].equals(Cell.S_PLACEMENT)) {
				  bpScore ++;
			  }
		  }
		  
		  // check diagonal
		  /*
		   * S		
		   * 	O
		   * 		S
		   */
		  
	  if(row <= size -3 && col <= size -3) {
		  	if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row +1 ][col +1].equals(Cell.O_PLACEMENT) && grid[row +2][col+2].equals(Cell.S_PLACEMENT)) {
			  bpScore ++;
		  	}
	  }
	  
	   if(row >= 2 && col >= 2) {
		  	if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col -1].equals(Cell.O_PLACEMENT) && grid[row -2][col-2].equals(Cell.S_PLACEMENT)) {
			  bpScore ++;
		  	}
	  }
	  if(row >=1 && row <= size-2 && col >=1 && col<=size-2) {
			if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col -1].equals(Cell.S_PLACEMENT) && grid[row +1][col+1].equals(Cell.S_PLACEMENT)) {
				  bpScore ++;
			}
	  }
	  
	  // check anti diagonal
	  if(row >= size-3 && col <= size-3) {
		  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row -1 ][col +1].equals(Cell.O_PLACEMENT) && grid[row -2][col+2].equals(Cell.S_PLACEMENT)) {
			  bpScore ++;
		  	}
	  }
	  
	  if( col <=size-2 && col >=1 && row >=1 && row <= size-2) {
		  if(grid[row][col].equals(Cell.S_PLACEMENT) && grid[row +1 ][col -1].equals(Cell.O_PLACEMENT) && grid[row +2][col-2].equals(Cell.S_PLACEMENT)) {
			  bpScore ++;
		  	}
	  }
	  
	  if(row >=1 && row <= size-2 && col >=1 && col<=size-2) {
			if(grid[row][col].equals(Cell.O_PLACEMENT) && grid[row -1 ][col +1].equals(Cell.S_PLACEMENT) && grid[row +1][col-1].equals(Cell.S_PLACEMENT)) {
				  bpScore ++;
			}
	  }
	  
	  return getBpScore();
	  }

	  public void compareScore() {
		  
		 if(getRpScore() > getBpScore() && isFull()) {
			 
			 currentGameState = GameState.RED_WON;
		
		 } else if(getRpScore() <  getBpScore() && isFull()) {
			 currentGameState = GameState.BLUE_WON;
			 
		 } else if(getRpScore() == getBpScore() && isFull()) {
			 currentGameState = GameState.DRAW;
			
		 }
		
	  }
	  
	
	  
	  public void startFileWriter() {
		 
			  try {
				writer = new FileWriter("gameRecord.txt");
				System.out.println("start recording");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  
	  public void appendFileWriter(String text) {
		  try {
			writer.write(text + "\n");
			System.out.println("text added");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public void closeFileWriter() {
		  try {
			writer.close();
			System.out.println("file closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  

	public GameState getGameState() {
		return currentGameState;
	}
	
	public ScoreState getScoreState() {
		return currentScoreState;
	}

}


