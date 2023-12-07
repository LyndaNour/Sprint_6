import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
	public class SosGUI extends JFrame {
	
		public static final int CELL_SIZE = 80;
		public static final int GRID_WIDTH = 8;
		public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; 

		public static final int CELL_PADDING = CELL_SIZE / 6;
		public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
		public static final int SYMBOL_STROKE_WIDTH = 8; 

		private int CANVAS_WIDTH; 
		private int CANVAS_HEIGHT;

		private GameBoardCanvas gameBoardCanvas; 
		private JLabel gameStatusBar; 
		private JLabel gameStatusBar2; 

		private Board board;
	
		JRadioButton sButton;
		JRadioButton oButton;
		JRadioButton simpleButton;
		JRadioButton generalButton;
		JTextField sizeSelect;
		JLabel blueScore;
		
		JRadioButton sHuman;
		JRadioButton oHuman;
		JRadioButton sComputer;
		JRadioButton oComputer;
		
		JRadioButton sHuman2;
		JRadioButton oHuman2;
		JRadioButton sComputer2;
		JRadioButton oComputer2;
		
		JRadioButton humanPlayer;
		JRadioButton computerPlayer;
		JRadioButton humanPlayer2;
		JRadioButton computerPlayer2;
		JLabel redScore;
		JLabel warningLabel = new JLabel(" ");
		
		JLabel iconBLabel;
		JLabel iconRLabel;
		
		JCheckBox record;
		JCheckBox reply;
		ImageIcon winner;
		ImageIcon smile;
		ImageIcon worst;
		ImageIcon better;
		ImageIcon lazy;
		ImageIcon angry;
		
		

		public SosGUI(Board board) {
			this.board = board;
			setContentPane();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pack(); 
			setTitle("SOS GAME");
			board.startFileWriter();
	
			setVisible(true);  
			
			
		}
		
		
		//update board size
		private void updateBoardSize() {
			
			CANVAS_WIDTH = CELL_SIZE *board.getTotalRows();
			CANVAS_HEIGHT = CELL_SIZE * board.getTotalColumns();
			
			repaint();
		}
		//check for the valid board size
		public boolean setBoardSize(int size) {
			if(size<3 || size>6) {
	
				return false;
			} 
			return true;
		}

		private void setContentPane(){
			gameBoardCanvas = new GameBoardCanvas();  
			CANVAS_WIDTH = CELL_SIZE * board.getTotalRows();  
			CANVAS_HEIGHT = CELL_SIZE * board.getTotalColumns();
			gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
			gameStatusBar = new JLabel(" ");
			gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));;
			gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
			
			gameStatusBar2 = new JLabel(" ");
			gameStatusBar2.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));;
			gameStatusBar2.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));

			Container contentPane = getContentPane();
			contentPane.setLayout(new BorderLayout());
			contentPane.setBackground(Color.WHITE);

			//control for players
			JPanel playerPanel = new JPanel();
			playerPanel.setLayout(new BorderLayout());
			//playerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
			playerPanel.setPreferredSize(new Dimension(100,100));
			playerPanel.setBackground(Color.WHITE);
			
			
			// blue player control panel
			JPanel pl1 = new JPanel();
			pl1.setPreferredSize(new Dimension(200,200));
			pl1.setBackground(new Color(20,124,200));
			
			winner = new ImageIcon("winner.png");
			better = new ImageIcon("happy.png");
			worst = new ImageIcon("worst.gif");
			smile = new ImageIcon("smile.png");
			lazy = new ImageIcon("lazy.gif");
			angry = new ImageIcon("angry.png");
			
			
			
			iconBLabel = new JLabel("happy");
			iconBLabel.setIcon(smile);
			
		//	pl1.add(iconBLabel);

			// create a pl1 panel that holds blue player information 
			JLabel blueLabel = new JLabel("BLUE PLAYER:");

			
			blueScore = new JLabel("Score:");
		
			//RadioButton group S and O for human
			ButtonGroup humanGroup = new ButtonGroup();
			sHuman = new JRadioButton("S");
			oHuman = new JRadioButton("O");
			sHuman.setBackground(null);
			oHuman.setBackground(null);
			humanGroup.add(oHuman);
			humanGroup.add(sHuman);
			
			
			//RadioButton group S and O for computer
			
			ButtonGroup computerHuman = new ButtonGroup();
			
			ButtonGroup computerGroup = new ButtonGroup();
			sComputer = new JRadioButton("S");
			oComputer = new JRadioButton("O");
			computerPlayer = new JRadioButton("Computer");
			humanPlayer = new JRadioButton("Human");
			computerPlayer.setBackground(null);
			humanPlayer.setBackground(null);
			
			computerGroup.add(oComputer);
			computerGroup.add(sComputer);
			
			computerHuman.add(computerPlayer);
			computerHuman.add(humanPlayer);
			
			pl1.add(blueLabel);
			pl1.add(computerPlayer);
			pl1.add(oHuman);
			pl1.add(sHuman);
			pl1.add(humanPlayer);	
			pl1.add(blueScore); 
			pl1.add(iconBLabel);
			
		
			
			// red player control panel
			JPanel pl2 = new JPanel();
			pl2.setPreferredSize(new Dimension(200,200));
			pl2.setBackground(new Color(255,56, 100));
			
			JPanel recordPanel = new JPanel();
			recordPanel.setPreferredSize(new Dimension(50,50));
			recordPanel.setBackground(new Color(255,126, 100));
			
			iconRLabel = new JLabel("happy");
			iconRLabel.setIcon(smile);

			// create a pl2 panel that holds red player information 

			redScore = new JLabel("Score:");
			//RadioButton group S and O for human
			ButtonGroup humanGroup2 = new ButtonGroup();
			sHuman2 = new JRadioButton("S");
			oHuman2 = new JRadioButton("O");
			
			sHuman2.setBackground(null);
			oHuman2.setBackground(null);
			humanGroup2.add(oHuman2);
			humanGroup2.add(sHuman2);
			
			//RadioButton group S and O for computer
			ButtonGroup computerGroup2 = new ButtonGroup();
			sComputer2 = new JRadioButton("S");
			oComputer2 = new JRadioButton("O");
			sComputer2.setBackground(null);
			oHuman2.setBackground(null);
			computerGroup2.add(oComputer2);
			computerGroup2.add(sComputer2);
				
			ButtonGroup computerHuman2 = new ButtonGroup();
			computerPlayer2 = new JRadioButton("Computer");
			humanPlayer2 = new JRadioButton("Human");
			computerPlayer2.setBackground(null);
			humanPlayer2.setBackground(null);
			
			computerHuman2.add(humanPlayer2);
			computerHuman2.add(computerPlayer2);
				
				pl2.add(computerPlayer2);
				pl2.add(oHuman2);
				pl2.add(sHuman2);
				pl2.add(humanPlayer2);
				pl2.add(redScore);
				pl2.add(iconRLabel);
				
	
			
			//add blue player content and red player content to the main player panel
			playerPanel.add(pl1, BorderLayout.NORTH);
			playerPanel.add(pl2, BorderLayout.SOUTH);
			playerPanel.add(recordPanel, BorderLayout.CENTER);
			// create a panel to hold simple and general game 
			JPanel modePanel = new JPanel();
		//	modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.X_AXIS));
			modePanel.setPreferredSize(new Dimension(50,50));
			modePanel.setBackground(Color.cyan);
			simpleButton = new JRadioButton("Simple Game");
			generalButton = new JRadioButton("General Game");
			
			simpleButton.setBackground(null);
			generalButton.setBackground(null);
			simpleButton.setFocusable(false);
			generalButton.setFocusable(false);
			record = new JCheckBox("Record game");
			record.setFocusable(false);
			record.setBackground(null);
			
			reply = new JCheckBox("Reply");
			reply.setFocusable(false);
			reply.setBackground(null);
			
		
			
			ButtonGroup gameMode = new ButtonGroup();
			gameMode.add(simpleButton);
			gameMode.add(generalButton);
			recordPanel.add(record);
			recordPanel.add(reply);
			
			
			modePanel.add(simpleButton);
			modePanel.add(generalButton);
		//	pl1.add(iconLabel);
			
			

			
			// create panel that holds size 
			JLabel sizeLabel = new JLabel("Board size");
			
			 sizeSelect = new JTextField("Enter size here");
			
			modePanel.add(sizeLabel);
			modePanel.add(sizeSelect);
			modePanel.add(warningLabel);
		//	recordPanel.add(iconLabel);
		//	playerPanel.add(record);
			sizeSelect.addActionListener(new ActionListener() {;
				
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer newSize = Integer.parseInt(sizeSelect.getText());
				setBoardSize(newSize);
				if(setBoardSize(newSize) ==false) {
					warningLabel.setText("invalid size");
					
				
				}
				else {
					warningLabel.setText("valid size");
						board.setRows(newSize);
						board.setColumns(newSize);
						updateBoardSize();
						sizeSelect.setEditable(false);
						
				}
				
			}
			});
		
			
		//	recordPanel.add(iconLabel);
			//contentPane.add(iconLabel, BorderLayout.EAST);
			contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
			contentPane.add(gameStatusBar, BorderLayout.PAGE_END);
			contentPane.add(playerPanel, BorderLayout.WEST);
			contentPane.add(modePanel, BorderLayout.BEFORE_FIRST_LINE);
		}
	

		class GameBoardCanvas extends JPanel {

			GameBoardCanvas(){

				
				addMouseListener(new MouseAdapter() {
				
					public void mouseClicked(MouseEvent e) {  
						if (board.getGameState() == Board.GameState.PLAYING) {
							int rowSelected = e.getY() / CELL_SIZE;
							int colSelected = e.getX() / CELL_SIZE;

							//blue player move 
							if(recordGame()) {
								board.appendFileWriter(board.changeTurn()+ " turn");
							}
							
							if(board.changeTurn()=="Blue player") {	
								
							// human player
							if(humanPlayer.isSelected()) {
								if(recordGame()) {
									board.appendFileWriter("blue player selected to be a human");
								}
									

								if(sHuman.isSelected())		{
								board.makeSmove(rowSelected, colSelected);
								warningLabel.setText("blue player places S at (" + rowSelected + ", " +colSelected +")");
								if(recordGame()) {
									board.appendFileWriter("Blue player places S at (" + rowSelected + ", " +colSelected +")");
								}
								

							}  if(oHuman.isSelected()) {
								board.makeOmove(rowSelected, colSelected);
								warningLabel.setText("blue player places O at (" + rowSelected + ", " +colSelected +")");
								if(recordGame()) {
									board.appendFileWriter("blue player places O at (" + rowSelected + ", " +colSelected +")");
								}
	;
							} 
							
							}// end of human player
					
		
							if(generalButton.isSelected()){
								blueScore.setText("Score:" + board.generalBlueCheck(rowSelected, colSelected, board.getTotalRows()));
								if(board.getBpScore()>board.getRpScore()) {
									iconBLabel.setText("Happy!");
									iconBLabel.setIcon(better);
									iconRLabel.setText("Mad!");
									iconRLabel.setIcon(worst);
								}
								else if(board.getBpScore()<board.getRpScore()) {
									iconRLabel.setText("Happy!");
									iconRLabel.setIcon(better);
									iconBLabel.setText("mad!");
									iconBLabel.setIcon(worst);
								}
								else if(board.getBpScore()==board.getRpScore()) {
									
										iconBLabel.setText("Equal!");
										iconBLabel.setIcon(lazy);
										iconRLabel.setText("Equal!");
										iconRLabel.setIcon(lazy);
									
								}
								
							}
						
						} // end of blue player turn
						
							else if(board.changeTurn()=="Red player") {

							// human player for red player
							if(humanPlayer2.isSelected()) {
								warningLabel.setText("Red player selected to be a human");
								if(recordGame()) {
									board.appendFileWriter("Red player selected to be a human");
								}
								
								
							//red player move for human
								if(sHuman2.isSelected()) {
								board.makeSmove(rowSelected, colSelected);
								warningLabel.setText("Red player places S at ( " + rowSelected + ", " +colSelected +")");
								if(recordGame()) {
									board.appendFileWriter(("Red player places S at (" + rowSelected + ", " +colSelected +")"));
								}
								
							
			
							} else if(oHuman2.isSelected()) {
								board.makeOmove(rowSelected, colSelected);
								warningLabel.setText("Red player places O at (" + rowSelected + ", " +colSelected +")");
								if(recordGame()) {
									board.appendFileWriter(("Red player places O at (" + rowSelected + ", " +colSelected +")"));
								}
								
							}

							}//end human
				
							if(generalButton.isSelected()){
								redScore.setText("Score:" + board.generalRedCheck(rowSelected, colSelected, board.getTotalRows()));
								if(board.getBpScore()>board.getRpScore()) {
									iconBLabel.setText("Happy!");
									iconBLabel.setIcon(better);
									iconRLabel.setText("Mad!");
									iconRLabel.setIcon(worst);
								}
								else if(board.getBpScore()<board.getRpScore()) {
									iconRLabel.setText("Happy!");
									iconRLabel.setIcon(better);
									iconBLabel.setText("Mad!");
									iconBLabel.setIcon(worst);
								}
								else if(board.getBpScore()==board.getRpScore()) {
									
										iconBLabel.setText("Equal!");
										iconBLabel.setIcon(lazy);
										iconRLabel.setText("Equal!");
										iconRLabel.setIcon(lazy);
									
								}
								
							}

						}// end of red player 	
					
							
						} // close playing game  
				
						// initialize the game board 

						else {
							if(simpleButton.isSelected()) {
							
							if(board.getGameState()==Board.GameState.BLUE_WON) {
								iconBLabel.setText("I won!");
								iconBLabel.setIcon(winner);
								iconRLabel.setText("Mad!");
								iconRLabel.setIcon(angry);
								if(recordGame()) {
									board.appendFileWriter("Blue won in simple game!!");
									board.closeFileWriter();
								}	
							}
							else if(board.getGameState()==Board.GameState.RED_WON) {
								iconRLabel.setText("I won!");
								iconRLabel.setIcon(winner);
								iconBLabel.setText("Mad!");
								iconBLabel.setIcon(angry);
								if(recordGame()) {
									board.appendFileWriter("Red won in simple game!!");
									board.closeFileWriter();
								}	
							}
							else if(board.getGameState()==Board.GameState.DRAW) {
								iconRLabel.setText("Sleepy!");
								iconRLabel.setIcon(lazy);
								iconBLabel.setText("Sleepy!");
								iconBLabel.setIcon(lazy);
								if(recordGame()) {
									board.appendFileWriter("Draw in simple game!");
									board.closeFileWriter();
								}
							}
							}
							
							else if(generalButton.isSelected()) {
							
							
							
							if(board.isFull() && board.getGameState()==Board.GameState.BLUE_WON) {
								iconBLabel.setText("I won!");
								iconBLabel.setIcon(winner);
								iconRLabel.setText("Mad!");
								iconRLabel.setIcon(angry);
								if(recordGame()) {
									board.appendFileWriter("Blue won in general game with score=" + board.getBpScore());
									board.closeFileWriter();
								}
								
							} else if(board.isFull() && board.getGameState()==Board.GameState.RED_WON) {
								iconRLabel.setText("I won!");
								iconRLabel.setIcon(winner);
								iconBLabel.setText("Mad!");
								iconBLabel.setIcon(angry);
								if(recordGame()) {
									board.appendFileWriter("Red won in general game with score=" + board.getRpScore());
									board.closeFileWriter();
								}
								
							}
							else if(board.isFull() && board.getGameState()==Board.GameState.DRAW) {
								iconRLabel.setText("Sleepy!");
								iconRLabel.setIcon(lazy);
								iconBLabel.setText("Sleepy!");
								iconBLabel.setIcon(lazy);
								if(recordGame()) {
									board.appendFileWriter("Draw in general game!");
									board.closeFileWriter();
								}
							}
							}

						}
						//	repaint();
					// end mouseClicked 
							
		
					}
				
					public void mouseEntered(MouseEvent e) {
						if (board.getGameState() == Board.GameState.PLAYING) {
						Random rand = new Random();
						int r = rand.nextInt(board.getTotalRows());
						int c = rand.nextInt(board.getTotalColumns());
						
						// computer player and blue turn
						 if(computerPlayer.isSelected() && board.changeTurn()=="Blue player") {
			
							warningLabel.setText("Blue player selected to be a computer");	
							if(record.isSelected()) {
								board.appendFileWriter("Blue player selected to be a computer");
							}
							
							
						 
							if(sHuman.isSelected()) {
								if(board.isOccupied(r, c)) {
									warningLabel.setText("The cell at ("+r +", "+ c+") is already occupied!");
								}
								else {
									board.makeSmove(r, c);
									warningLabel.setText("Blue player computer places S at (" + r + ", " +c+")");
								}
								
								
								
								if(record.isSelected()) {
									board.appendFileWriter("Blue player computer places S at (" + r + ", " +c+")");
								}
						
								} else if(oHuman.isSelected()) {
									if(board.isOccupied(r, c)) {
										warningLabel.setText("The cell at ("+r +", "+ c+") is already occupied!");
									}else {
										board.makeOmove(r, c);
										warningLabel.setText("Blue player computer places S at (" + r + ", " +c+")");
									}
							
								if(record.isSelected()) {
									board.appendFileWriter("Blue player computer places S at (" + r + ", " +c+")");
								}
								}
			
							if(generalButton.isSelected()) {

									blueScore.setText("Blue Score:" + board.generalBlueCheck(r, c, board.getTotalRows()));
					
								if(board.getBpScore()>board.getRpScore()) {
									iconBLabel.setText("Happy!");
									iconBLabel.setIcon(better);
									iconRLabel.setText("Mad!");
									iconRLabel.setIcon(worst);
								}
								else if(board.getBpScore()<board.getRpScore()) {
									iconRLabel.setText("Happy!");
									iconRLabel.setIcon(better);
									iconBLabel.setText("Mad!");
									iconBLabel.setIcon(worst);
								}
								else if(board.getBpScore()==board.getRpScore()) {
									
										iconBLabel.setText("Equal!");
										iconBLabel.setIcon(lazy);
										iconRLabel.setText("Equal!");
										iconRLabel.setIcon(lazy);
									
								}
							}
							
							
						
						 }// end of computer player  for blue player 
						 else if (computerPlayer2.isSelected() && board.changeTurn()=="Red player") {
							 if(record.isSelected()) {
								 board.appendFileWriter("Red player selected to be a computer");
							 }
							
								if(sHuman2.isSelected()) {
									if(board.isOccupied(r, c)) {
										warningLabel.setText("The cell at ("+r +", "+ c+") is already occupied!");
									}else {
										board.makeSmove(r, c);
										warningLabel.setText(" Red player computer places S at (" + r + ", " +c+")");
									}
								
									if(record.isSelected()) {
										board.appendFileWriter("Red player computer places S at (" + r + ", " +c+")");
									}
				
									} else if(oHuman2.isSelected()) {
										if(board.isOccupied(r, c)) {
											warningLabel.setText("The cell at ("+r +", "+ c+") is already occupied!");
										}else {
											board.makeOmove(r, c);
											warningLabel.setText("Red player computer places O at (" + r + ", " +c+")");
										}
										
										if(record.isSelected()) {
											board.appendFileWriter("Red player computer places O at (" + r + ", " +c+")");
										}
									} 

								if(generalButton.isSelected()) {
								
										redScore.setText("Red Score:" + board.generalRedCheck(r, c, board.getTotalRows()));
									
									
									if(board.getBpScore()>board.getRpScore()) {
										iconBLabel.setText("Happy!");
										iconBLabel.setIcon(better);
										iconRLabel.setText("Mad!");
										iconRLabel.setIcon(worst);
									}
									else if(board.getBpScore()<board.getRpScore()) {
										iconRLabel.setText("Happy!");
										iconRLabel.setIcon(better);
										iconBLabel.setText("Mad!");
										iconBLabel.setIcon(worst);
									}
									else if(board.getBpScore()==board.getRpScore()) {
										
											iconBLabel.setText("Equal!");
											iconBLabel.setIcon(lazy);
											iconRLabel.setText("Equal!");
											iconRLabel.setIcon(lazy);
										
									}
								}
								
						 }// end red player 
						 
						 if(!simpleButton.isSelected() && !generalButton.isSelected()) {
								warningLabel.setText("Choose a game mode to continue!");
								warningLabel.setForeground(Color.red);
							}// game mode 	
						}
						
						if(reply.isSelected()) {
							board.initBoard(); 
							warningLabel.setText("  ");
							sizeSelect.setText("Enter size here");
							sizeSelect.setEditable(true);
							board.bpScore=0;
							board.rpScore=0;
							blueScore.setText("Score:");
							redScore.setText("Score:");
							iconBLabel.setText("Excited");
							iconBLabel.setIcon(smile);
							iconRLabel.setText("Excited");
							iconRLabel.setIcon(smile);
							repaint();
						}

					}

				});
			}

			@Override
			public void paintComponent(Graphics g) { 
				super.paintComponent(g);   
				setBounds(150, 100,CANVAS_WIDTH, CANVAS_HEIGHT);
				setBackground(new Color(200,3,125));
				drawGridLines(g);
				drawBoard(g);
				printStatusBar();
			}
			
			private void drawGridLines(Graphics g){
				g.setColor(Color.WHITE);
				for (int row = 1; row < board.getTotalRows(); ++row) {
					g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
							CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
				}
				for (int col = 1; col < board.getTotalColumns(); ++col) {
					g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
							GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
				}
			
			}

			private void drawBoard(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				
				for (int row = 0; row < board.getTotalRows(); ++row) {
					for (int col = 0; col < board.getTotalColumns(); ++col) {
						int x1 = col * CELL_SIZE + CELL_PADDING;
						int y1 = row * CELL_SIZE + CELL_PADDING;
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
					
						
						g2d.setFont(new Font(" ",Font.BOLD,30));
						
						g2d.setColor(Color.BLACK);
				
						if (board.getCell(row,col) == Board.Cell.S_PLACEMENT) {
							g2d.drawString("S", (x1+x2)/2, (y1+y2)/2);
							if(simpleButton.isSelected()) {
								
							if(board.simpleCheck(board.changeTurn())) {
								g2d.drawLine(x1,y1, x2, y2);
							}
							}
						
						} else if (board.getCell(row,col) == Board.Cell.O_PLACEMENT) {
							g2d.drawString("O", (x1+x2)/2, (y1+y2)/2);
						
							if(simpleButton.isSelected()) {
								
							if(board.simpleCheck(board.changeTurn())) {
								g2d.drawLine(x1, y1, x2, y2);
							}
							}
						}
						
					}
				}
			}

			private boolean recordGame() {
				if(record.isSelected()) {
					return true;
				}
				return false;
			}
			private void printStatusBar(){
					
					board.compareScore();
					
						
					if (board.getGameState() == Board.GameState.PLAYING) {
						gameStatusBar.setForeground(Color.BLACK);
						if (board.changeTurn() == "Blue player") {
							gameStatusBar.setText("Blue player's Turn");
							
						} else {
							gameStatusBar.setText("Red player's Turn");
						}
						
					} else if (board.getGameState() == Board.GameState.DRAW) {
						gameStatusBar.setForeground(Color.RED);
						gameStatusBar.setText("It's a Draw! Click to play again.");
					} else if (board.getGameState() == Board.GameState.BLUE_WON) {
						gameStatusBar.setForeground(Color.RED);
						gameStatusBar.setText("Blue player' Won! Click to play again.");
					} else if (board.getGameState()== Board.GameState.RED_WON) {
						gameStatusBar.setForeground(Color.RED);
						gameStatusBar.setText("Red player's Won! Click to play again.");
						
					} else if (board.getGameState()== Board.GameState.RED_WON && board.isFull()) {
						gameStatusBar.setText("Red player's Won! Click to play again");
					} else if (board.getGameState()== Board.GameState.BLUE_WON && board.isFull()) {
					gameStatusBar.setText("Blue player's Won! Click to play again");
		
				}
			}
		}

	
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new SosGUI(new Board()); 
				}
	
			});
	
	
		}
		}


