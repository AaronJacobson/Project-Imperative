package game;

import org.newdawn.slick.Color;

public class Paddle extends GameElement {
	private int POINTS;
	
	public Paddle(Board board, int locationX, int locationY) {
		super(board, 100, 30, locationX, locationY);
		COLOR = Color.white;
		NAME = "Paddle";
	}
	
	public void moveLeft(int delta){
		LOCATION_X -= 1 * delta;
	}
	
	public void moveRight(int delta){
		LOCATION_X += 1 * delta;
	}
}
