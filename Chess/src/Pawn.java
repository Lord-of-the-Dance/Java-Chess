
public class Pawn extends Piece {

	Pawn(int x, int y, String color) {
		super(x, y, color);
	}
	
	public boolean isLegalMove(int deltaX, int deltaY) {
		//File Movement
		if (getColor().equals("White")) {					
			if (deltaY < 0 && deltaY >= -2 && deltaX <= 1 && deltaX >= -1) {
				if (deltaY < 0 && deltaY >= -1) {
					return true;
				}				
				else if (getY() == 6 && deltaX == 0) {
					return true;
				}				
				else {
					return false;
				}
			}			
			else {
				return false;
			}
		}
		
		else {
			if (deltaY > 0 && deltaY <= 2 && deltaX <= 1 && deltaX >= -1) {
				if (deltaY > 0 && deltaY <= 1) {
					return true;
				}				
				else if (getY() == 1) {
					return true;
				}				
				else {
					return false;
				}
			}			
			else {
				return false;
			}
		}
		
	}
	
}
