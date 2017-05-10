
public class Piece {

	private int x;
	private int y;
	private String color;
	
	Piece(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void movePiece(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		return true;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getColor() {
		return color;
	}
	
}
