package game;

import lan.Server;

import org.newdawn.slick.Color;

public class Paddle extends GameElement {
	
	public Paddle(Board board, int locationX, int locationY) {
		super(board, 100, 30, locationX, locationY);
		COLOR = Color.white;
		NAME = "Paddle";
	}
	
	public void moveLeft(int delta){
		LOCATION_X -= 1 * delta;
		main.Main.CLIENT.sendMessage(Server.COM_COORDS + " " + this.NAME + " " + this.LOCATION_X + " " + this.LOCATION_Y);
	}
	
	public void moveRight(int delta){
		LOCATION_X += 1 * delta;
		main.Main.CLIENT.sendMessage(Server.COM_COORDS + " " + this.NAME + " " + this.LOCATION_X + " " + this.LOCATION_Y);
	}
}
