
public class Bishop extends Piece {

	Bishop(int x, int y, String color) {
		super(x, y, color);
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		if ((deltaY == deltaX) || (deltaY == -deltaX)) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
