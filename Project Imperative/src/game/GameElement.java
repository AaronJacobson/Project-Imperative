package game;

import lan.Server;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class GameElement {
	protected Board BOARD;
	protected String NAME = "Larry";
	protected Color COLOR;
	
	protected int LOCATION_X = 240;
	protected int LOCATION_Y = 340;
	
	protected int TOP_LINE;
	protected int RIGHT_LINE;
	protected int BOTTOM_LINE;
	protected int LEFT_LINE;
	
	protected int SIZE_X;
	protected int SIZE_Y;
	
	public GameElement(Board board, int size) {
		SIZE_X = size;
		SIZE_Y = size;
		updateBounds();
		BOARD = board;
		board.addElement(this);
		COLOR = Color.darkGray;
	}
	
	public GameElement(Board board, int sizeX, int sizeY) {
		SIZE_X = sizeX;
		SIZE_Y = sizeY;
		updateBounds();
		BOARD = board;
		board.addElement(this);
		COLOR = Color.darkGray;
	}
	
	public GameElement(Board board, int sizeX, int sizeY, int locationX, int locationY) {
		SIZE_X = sizeX;
		SIZE_Y = sizeY;
		LOCATION_X = locationX;
		LOCATION_Y = locationY;
		updateBounds();
		BOARD = board;
		board.addElement(this);
		COLOR = Color.darkGray;
	}

	public GameElement(Board board, int size, int locationX, int locationY) {
		SIZE_X = size;
		SIZE_Y = size;
		LOCATION_X = locationX;
		LOCATION_Y = locationY;
		updateBounds();
		BOARD = board;
		board.addElement(this);
		COLOR = Color.darkGray;
	}
	
	private void updateBounds() {
		TOP_LINE = LOCATION_Y - (SIZE_Y/2);
		BOTTOM_LINE = LOCATION_Y + (SIZE_Y/2);
		LEFT_LINE = LOCATION_X - (SIZE_X/2);
		RIGHT_LINE = LOCATION_X + (SIZE_X/2);
	}
	
	public void moveSelf(int x, int y) {
		LOCATION_X = x;
		LOCATION_Y = y;
		main.Main.CLIENT.sendMessage(Server.COM_COORDS + " " + NAME + " " + LOCATION_X + " " + LOCATION_Y);
		updateBounds();
	}
	
	public boolean containsCoordiates(int x, int y) {
		boolean output = false;
		if(x <= RIGHT_LINE && x >= LEFT_LINE) {
			if(y <= BOTTOM_LINE && y >= TOP_LINE) {
				output = true;
			}
		}
		return output;
	}
	
	public void draw(Graphics g) {
		g.setColor(COLOR);
		g.fillRect(getLocationX(), getLocationY(), getSizeX(), getSizeY());
	}
	
	public void setColor(Color color) {
		COLOR = color;
	}
	
//-----------------------------------------------------------------------------------------------
	
	public int getLocationX() {
		return LOCATION_X;
	}
	
	public int getLocationY() {
		return LOCATION_Y;
	}
	
	public void setLocation(int newX,int newY){
		LOCATION_X = newX;
		LOCATION_Y = newY;
	}
	
//-----------------------------------------------------------------------------------------------	
	
	public String getName() {
		return NAME;
	}
	
	public void setName(String name) {
		NAME = name;
	}
	
//-----------------------------------------------------------------------------------------------	
	
	public int getSizeX() {
		return SIZE_X;
	}
		
	public int getSizeY() {
		return SIZE_Y;
	}
		
//-----------------------------------------------------------------------------------------------	
	
	public int getTopLine() {
		return TOP_LINE;
	}
		
	public int getRightLine() {
		return RIGHT_LINE;
	}
	
	public int getBottomLine() {
		return BOTTOM_LINE;
	}
		
	public int getLeftLine() {
		return LEFT_LINE;
	}
		
//-----------------------------------------------------------------------------------------------	
//	public String toString() {
//		String output = "";
//		output += "Center:       (" + LOCATION_X + "," + LOCATION_Y + ")\n";
//		output += "Top Left:     (" + LEFT_LINE + "," + TOP_LINE + ")\n";
//		output += "Bottom Right: (" + RIGHT_LINE + "," + BOTTOM_LINE + ")\n";
//		return output;
//	}
}
