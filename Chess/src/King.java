
public class King extends Piece {

	King(int x, int y, String color) {
		super(x, y, color);
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		if (deltaX <= 1 && deltaX >= -1 && deltaY <= 1 && deltaY >= -1) {
			return true;
		}
		
		else if ((deltaX == 2 && deltaY == 0) || (deltaX == -2 && deltaY == 0)) {
			return true;
		}
		
		else {
			return false;
		}
	}	
}
