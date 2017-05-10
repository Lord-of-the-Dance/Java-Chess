
public class Queen extends Piece {

	Queen(int x, int y, String color) {
		super(x, y, color);
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		if (((deltaY <= 8 || deltaY >= -8) && (deltaX == 0)) ||
			((deltaX <= 8 || deltaX >= -8) && (deltaY == 0)) ||
			 (deltaY == deltaX) ||
			 (deltaY == -deltaX)) {
			return true;
		}
		
		else {
			return false;
		}
			
	}
	
}
