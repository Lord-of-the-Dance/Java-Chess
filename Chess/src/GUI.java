import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel gameBoard = new JPanel();
	private JPanel options = new JPanel();	
	private JButton newGame = new JButton("New Game");	
	private JButton[][] squares = new JButton[8][8];
	private ArrayList<String> game = new ArrayList<String>();
	private JList<String> notation = new JList<String>();
	private Piece[][] pieces = new Piece[8][8];
	private Color darkColor = new Color(125,84,13);
	private Color lightColor = new Color(236,212,171);
	
	private boolean squareSelected = false;
	private boolean whiteTurn = true;
	private boolean whiteKingMoved = false;
	private boolean blackKingMoved = false;
	private int startX;
	private int startY;
	private int endX;
	private int endY;
	private int deltaX;
	private int deltaY;
	private String movedPiece = "";
	private String endSquare = "";
	
	private File whitePawn = new File("WhitePawn.png");
	private File blackPawn = new File("BlackPawn.png");
	private File whiteKnight = new File("WhiteKnight.png");
	private File blackKnight = new File("BlackKnight.png");
	private File whiteBishop = new File("WhiteBishop.png");
	private File blackBishop = new File("BlackBishop.png");
	private File whiteQueen = new File("WhiteQueen.png");
	private File blackQueen = new File("BlackQueen.png");
	private File whiteRook = new File("WhiteRook.png");
	private File blackRook = new File("BlackRook.png");
	private File whiteKing = new File("WhiteKing.png");
	private File blackKing = new File("BlackKing.png");
	
	private Image whiteP;
	private Image blackP;
	private Image whiteN;
	private Image blackN;
	private Image whiteB;
	private Image blackB;
	private Image whiteQ;
	private Image blackQ;
	private Image whiteR;
	private Image blackR;
	private Image whiteK;
	private Image blackK;
	
	private ImageIcon wP;
	private ImageIcon bP;
	private ImageIcon wN;
	private ImageIcon bN;
	private ImageIcon wB;
	private ImageIcon bB;
	private ImageIcon wQ;
	private ImageIcon bQ;
	private ImageIcon wR;
	private ImageIcon bR;
	private ImageIcon wK;
	private ImageIcon bK;
	
	GUI() {
		
		setTitle("Chess");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650,550);
		setResizable(false);
		setIcons();
		
		setLayout(new BorderLayout());
		gameBoard.setLayout(new GridLayout(8,8));
		
		for (int i = 0; i < squares.length; i++) {
			for (int k = 0; k < squares[i].length; k++) {
				JButton b = new JButton();
				if ((i % 2 == 0 && k % 2 == 0) || (k % 2 != 0 && i % 2 != 0)) {
					b.setBackground(lightColor);
				} else {
					b.setBackground(darkColor);
				}
				b.addActionListener(new SquareMovingListener());
				b.setBorderPainted(false);
				gameBoard.add(b);
				squares[i][k] = b;
			}
		}
		options.add(newGame);
		options.add(notation);
		newGame.addActionListener(new NewGameListener());
		setIcons();
		
		add(gameBoard, BorderLayout.CENTER);
		add(options, BorderLayout.EAST);
		setVisible(true);
		
	}
	
	public void setIcons() {
		try {
			whiteP = ImageIO.read(whitePawn);
			blackP = ImageIO.read(blackPawn);
			whiteN = ImageIO.read(whiteKnight);
			blackN = ImageIO.read(blackKnight);
			whiteB = ImageIO.read(whiteBishop);
			blackB = ImageIO.read(blackBishop);
			whiteQ = ImageIO.read(whiteQueen);
			blackQ = ImageIO.read(blackQueen);
			whiteR = ImageIO.read(whiteRook);
			blackR = ImageIO.read(blackRook);
			whiteK = ImageIO.read(whiteKing);
			blackK = ImageIO.read(blackKing);			
		
			wP = new ImageIcon(whiteP);
			bP = new ImageIcon(blackP);
			wN = new ImageIcon(whiteN);
			bN = new ImageIcon(blackN);
			wB = new ImageIcon(whiteB);
			bB = new ImageIcon(blackB);
			wQ = new ImageIcon(whiteQ);
			bQ = new ImageIcon(blackQ);
			wR = new ImageIcon(whiteR);
			bR = new ImageIcon(blackR);
			wK = new ImageIcon(whiteK);
			bK = new ImageIcon(blackK);
			
			for (int i = 0; i < squares.length; i++) {
				for (int k = 0; k < squares[i].length; k++) {
					if ((i == 0 && k == 0) || (i == 0 && k == 7)) {
						squares[i][k].setIcon(bR);
						pieces[i][k] = new Rook(k,i,"Black");
					}
					else if ((i == 0 && k == 1) || (i == 0 && k == 6)) {
						squares[i][k].setIcon(bN);
						pieces[i][k] = new Knight(k,i,"Black");
					}
					else if ((i == 0 && k == 2) || (i == 0 && k == 5)) {
						squares[i][k].setIcon(bB);
						pieces[i][k] = new Bishop(k,i,"Black");
					}
					else if ((i == 0 && k == 3)) {
						squares[i][k].setIcon(bQ);
						pieces[i][k] = new Queen(k,i,"Black");
					}
					else if ((i == 0 && k == 4)) {
						squares[i][k].setIcon(bK);
						pieces[i][k] = new King(k,i,"Black");
					}
					else if (i == 1) {
						squares[i][k].setIcon(bP);
						pieces[i][k] = new Pawn(k,i,"Black");
					}
					else if (i == 6) {
						squares[i][k].setIcon(wP);
						pieces[i][k] = new Pawn(k,i,"White");
					}
					else if ((i == 7 && k == 0) || (i == 7 && k == 7)) {
						squares[i][k].setIcon(wR);
						pieces[i][k] = new Rook(k,i,"White");
					}
					else if ((i == 7 && k == 1) || (i == 7 && k == 6)) {
						squares[i][k].setIcon(wN);
						pieces[i][k] = new Knight(k,i,"White");
					}
					else if ((i == 7 && k == 2) || (i == 7 && k == 5)) {
						squares[i][k].setIcon(wB);
						pieces[i][k] = new Bishop(k,i,"White");
					}
					else if ((i == 7 && k == 3)) {
						squares[i][k].setIcon(wQ);
						pieces[i][k] = new Queen(k,i,"White");
					}
					else if ((i == 7 && k == 4)) {
						squares[i][k].setIcon(wK);
						pieces[i][k] = new King(k,i,"White");
					}
					else {
						squares[i][k].setIcon(null);
						pieces[i][k] = null;
					}
					
				}
			}
		} catch (Exception e) {}
	}
	
	private class SquareMovingListener implements ActionListener {
		
		public boolean checkChecker(String color) {
			int kingX = 0;
			int kingY = 0;
			String kingColor = "";
			for (int i = 0; i < pieces.length; i++) {
				for (int j = 0; j < pieces[i].length; j++) {
					if (pieces[i][j].getClass() == new King(0, 0, "").getClass()) {
						if (color.equalsIgnoreCase("white") && pieces[i][j].getColor().equalsIgnoreCase("black")) {
							kingX = j;
							kingY = i;
							break;
						}
						
						else if (color.equalsIgnoreCase("black") && pieces[i][j].getColor().equalsIgnoreCase("white")) {
							kingX = j;
							kingY = i;
							break;
						}
					}
				}
			}
			
			int increment = 1;
			for (int i = kingX; i < 8; i += increment) {
				if (pieces[kingY][i].getColor().equalsIgnoreCase("black")) {
					if ()
				}
			}
		}
		
		public boolean pathChecker(int startY, int startX, int endY, int endX) {
			boolean pathClear = false;
			deltaX = endX - startX;
			deltaY = endY - startY;
			if (pieces[startY][startX].getClass() != new Knight(startX,startY,pieces[startY][startX].getColor()).getClass()) {
				if (deltaY < -1 && deltaX < -1) {
					for (int j = -1; j > deltaY; j--) {
						if (pieces[startY + j][startX + j] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}										
				}
				else if (deltaY < -1 && deltaX == 0) {
					for (int j = -1; j > deltaY; j--) {
						if (pieces[startY + j][endX] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY == 0 && deltaX < -1) {
					for (int m = -1; m > deltaX; m--) {
						if (pieces[endY][startX + m] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY > 1 && deltaX > 1) {
					for (int j = 1; j < deltaY; j++) {
						if (pieces[startY + j][startX + j] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY > 1 && deltaX == 0) {
					for (int j = 1; j < deltaY; j++) {
						if (pieces[startY + j][endX] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY < -1 && deltaX > 1) {
					for (int j = -1; j > deltaY; j--) {
						if (pieces[startY + j][startX - j] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY > 1 && deltaX < -1) {
					for (int j = -1; j > deltaX; j--) {
						if (pieces[startY - j][startX + j] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else if (deltaY == 0 && deltaX > 1) {
					for (int m = 1; m < deltaX; m++) {
						if (pieces[endY][startX + m] == null) {
							pathClear = true;
						}
						else {
							pathClear = false;
							break;
						}
					}
				}
				else {
					pathClear = true;
				}
				
				if (pathClear) {
					if (pieces[endY][endX] != null && pieces[endY][endX].getColor().equals(pieces[startY][startX].getColor()) == false) {
						pathClear = true;
					}
					else if (pieces[endY][endX] == null) {
						pathClear = true;
					}
					else {
						pathClear = false;
					}
				}
			}								
			
			else {
				if (pieces[endY][endX] != null) {
					if (pieces[endY][endX].getColor() == pieces[startY][startX].getColor()) {
						pathClear = false;
					}
					
					else {
						pathClear = true;
					}
				}
				
				else {
					pathClear = true;
				}
			}
			
			if (pieces[startY][startX].getClass() == new King(startX,startY,pieces[startY][startX].getColor()).getClass()) {
				if (deltaY == 0 && (deltaX == 2 || deltaX == -2)) {
					if (pathClear) {
						if (pieces[startY][startX].getColor().equals("White")) {
							if (whiteKingMoved == false) {
								whiteKingMoved = true;
								if (deltaX == 2) {
									pieces[7][7].movePiece(5, 7);
									pieces[7][5] = pieces[7][7];
									pieces[7][7] = null;
									squares[7][5].setIcon(squares[7][7].getIcon());
									squares[7][7].setIcon(null);
								}
								else {
									pieces[7][0].movePiece(3, 7);
									pieces[7][3] = pieces[7][0];
									pieces[7][0] = null;
									squares[7][3].setIcon(squares[7][0].getIcon());
									squares[7][0].setIcon(null);
								}
							}
							else {
								pathClear = false;
							}
						} 
						
						else {
							if (blackKingMoved == false) {
								blackKingMoved = true;
								if (deltaX == 2) {
									pieces[0][7].movePiece(5, 0);
									pieces[0][5] = pieces[0][7];
									pieces[0][7] = null;
									squares[0][5].setIcon(squares[0][7].getIcon());
									squares[0][7].setIcon(null);
								}
								else {
									pieces[0][0].movePiece(3, 0);
									pieces[0][3] = pieces[0][0];
									pieces[0][0] = null;
									squares[0][3].setIcon(squares[0][0].getIcon());
									squares[0][0].setIcon(null);
								}
							}
							else {
								pathClear = false;
							}
						}
					}
				}
				
				else if (pieces[startY][startX].isLegalMove(deltaX, deltaY)) {
					pathClear = true;
				}
			}
			
			
			
			if (pieces[startY][startX].getClass() ==  new Pawn(startX,startY,pieces[startY][startX].getColor()).getClass()) {
				if ((deltaY == -1 || deltaY == -2) && (deltaX == -1 || deltaX == 1)) {
					if (pieces[startY][startX].getColor().equals("White") && pieces[startY + deltaY][startX + deltaX] != null && pieces[startY + deltaY][startX + deltaX].getColor().equals("Black")) {
						pathClear = true;
					}
					
					else {
						pathClear = false;
					}
				}
				
				else if ((deltaY == 1 || deltaY == 2) && (deltaX == -1 || deltaX == 1)) {
					if (pieces[startY][startX].getColor().equals("Black") && pieces[startY + deltaY][startX + deltaX] != null && pieces[startY + deltaY][startX + deltaX].getColor().equals("White")) {
						pathClear = true;
					}
					
					else {
						pathClear = false;
					}
				}
			}
			
			return pathClear;
		}
		
		public void notationChecker(int endY, int endX, Piece piece) {
			endSquare = "";
			for (int i = 0; i < 7; i++) {
				for (int k = 0; k < 7; k++) {
					if (pieces[i][k] != null) {
						if (pieces[i][k].getClass() == piece.getClass() && pieces[i][k].getColor().equals(piece.getColor()) && pieces[startY][startX] != pieces[i][k]) {
							if (pieces[i][k].isLegalMove(endX - k, endY - i) && pathChecker(i, k, endY, endX)) {
								if (k == startX) {
									for (int s = 0; s < 7; s++) {
										if (startX == s) {
											endSquare = "" + (8 - startY);
										}
									}
								}
								
								else {
									if (startX == 0) {
										endSquare = "a";
									}
									else if (startX == 1) {
										endSquare = "b";
									}			
									else if (startX == 2) {
										endSquare = "c";
									}
									else if (startX == 3) {
										endSquare = "d";
									}
									else if (startX == 4) {
										endSquare = "e";
									}
									else if (startX == 5) {
										endSquare = "f";
									}
									else if (startX == 6) {
										endSquare = "g";
									}
									else {
										endSquare = "h";
									}
								}
							}
						}
					}
				}
			}	
			
			if (pieces[endY][endX] != null) {
				if (pieces[endY][endX].getColor() != piece.getColor()) {
					endSquare = endSquare + "x";
					if (pieces[startY][startX].getClass() == new Pawn(startX, startY, pieces[startY][startX].getColor()).getClass()) {
						if (startX == 0) {
							movedPiece = "a";
						}
						else if (startX == 1) {
							movedPiece = "b";
						}			
						else if (startX == 2) {
							movedPiece = "c";
						}
						else if (startX == 3) {
							movedPiece = "d";
						}
						else if (startX == 4) {
							movedPiece = "e";
						}
						else if (startX == 5) {
							movedPiece = "f";
						}
						else if (startX == 6) {
							movedPiece = "g";
						}
						else {
							movedPiece = "h";
						}
					}
				}
			}

			if (endX == 0) {
				endSquare = endSquare + "a";
			}			
			else if (endX == 1) {
				endSquare = endSquare + "b";
			}			
			else if (endX == 2) {
				endSquare = endSquare + "c";
			}
			else if (endX == 3) {
				endSquare = endSquare + "d";
			}
			else if (endX == 4) {
				endSquare = endSquare + "e";
			}
			else if (endX == 5) {
				endSquare = endSquare + "f";
			}
			else if (endX == 6) {
				endSquare = endSquare + "g";
			}
			else {
				endSquare = endSquare + "h";
			}		

			for (int i = 0; i < 7; i++) {
				if (endY == i) {
					endSquare = endSquare + (8 - i);
				}
			}
			
			if (pieces[startY][startX].getClass() == new King(startX, startY, pieces[startY][startX].getColor()).getClass()) {
				if (deltaX == 2 || deltaX == -2) {
					endSquare = "";
				}
			}
			
		}
		
		public void actionPerformed(ActionEvent e) {
			
			if (squareSelected == false) {
				for (int i = 0; i < squares.length; i++) {
					for (int k = 0; k < squares[i].length; k++) {
						if (squares[i][k] == e.getSource()) {
							if (pieces[i][k] == null) {
								break;
							}
							
							else {
								startY = i;
								startX = k;
								squareSelected = true;
							}
						}
					}
				}
			}
			else {
				for (int i = 0; i < squares.length; i++) {
					for (int k = 0; k < squares[i].length; k++) {
						if (squares[i][k] == e.getSource()) {
							endY = i;
							endX = k;
							deltaX = endX - startX;
							deltaY = endY - startY;
							if ((pieces[startY][startX].getColor().equals("White") && whiteTurn) || (pieces[startY][startX].getColor().equals("White") == false && whiteTurn == false)) {
								if (pieces[startY][startX].isLegalMove(deltaX, deltaY)) {									
									if (pathChecker(startY, startX, endY, endX)) {
										if (pieces[startY][startX].getClass() == new Bishop(startX,startY,pieces[startY][startX].getColor()).getClass()) {
											movedPiece = "B";
										}
										
										else if (pieces[startY][startX].getClass() == new Knight(startX,startY,pieces[startY][startX].getColor()).getClass()) {
											movedPiece = "N";
										}
										
										else if (pieces[startY][startX].getClass() == new Rook(startX,startY,pieces[startY][startX].getColor()).getClass()) {
											movedPiece = "R";
										}
										
										else if (pieces[startY][startX].getClass() == new Queen(startX,startY,pieces[startY][startX].getColor()).getClass()) {
											movedPiece = "Q";
										}
										
										else if (pieces[startY][startX].getClass() == new King(startX,startY,pieces[startY][startX].getColor()).getClass()) {
											movedPiece = "K";
											if (deltaX == 2) {
												movedPiece = "O-O";
												endSquare = "";
											}
											
											else if (deltaX == -2) {
												movedPiece = "O-O-O";
												endSquare = "";
											}
										}
										
										else {
											movedPiece = "";
										}
										
										notationChecker(endY, endX, pieces[startY][startX]);
										game.add(movedPiece + endSquare);
										System.out.println(movedPiece + endSquare);
										pieces[startY][startX].movePiece(endX, endY);
										pieces[endY][endX] = pieces[startY][startX];
										pieces[startY][startX] = null;
										squares[endY][endX].setIcon(squares[startY][startX].getIcon());
										squares[startY][startX].setIcon(null);
										if (whiteTurn) {
											whiteTurn = false;
										}
										else {
											whiteTurn = true;
										}
									}
								}
							}
							
							game.add(movedPiece + endSquare);
							squareSelected = false;
							break;
						}
					}
				}				
			}			
		}
	}

	private class NewGameListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			setIcons();
			whiteTurn = true;
		}
		
	}
}
