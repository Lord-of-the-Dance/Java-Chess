
public class Rook extends Piece {

	Rook(int x, int y, String color) {
		super(x, y, color);
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		if (((deltaX <= 8 && deltaX >= -8) && deltaY == 0) ||
			((deltaY <= 8 && deltaY >= -8) && deltaX == 0)) {
			return true;
		}
		
		else {
			return false;
		}
	}
}
