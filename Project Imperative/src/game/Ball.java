package game;

import java.awt.Rectangle;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;


public class Ball extends GameElement {
	private int SLOPE_X = 1;
	private int SLOPE_Y = 1;
	private int VELOCITY = 5;

	public Ball(Board board, int size) {
		super(board, size);
		NAME = "Ball";
		COLOR = Color.pink;
	}

	public Ball(Board board, int size, int locationX, int locationY) {
		super(board,size,size,locationX,locationY);
		NAME = "Ball";
		COLOR = Color.pink;
	}
	
	public Ball(Board board, int size, int slopeX, int slopeY, int velocity) {
		this(board, size);
		setSlope(slopeX, slopeY);
		setVelocity(velocity);
		NAME = "Ball";
		COLOR = Color.pink;
	}

	public Ball(Board board, int size, int locationX, int locationY, int slopeX, int slopeY, int velocity) {
		this(board,size,size,locationX,locationY);
		setSlope(slopeX, slopeY);
		setVelocity(velocity);
		NAME = "Ball";
		COLOR = Color.pink;
	}

	public void updateLocation() {
		int newX = LOCATION_X + (SLOPE_X * VELOCITY);
		int newY = LOCATION_Y + (SLOPE_Y * VELOCITY);
		super.moveSelf(newX, newY);
		collisionTest();
		checkOutOfBounds();
		super.moveSelf(LOCATION_X,LOCATION_Y);
	}
	
	public void checkOutOfBounds(){
		if(LOCATION_X > 500 || LOCATION_X < 0){
			System.out.println(NAME + " has went out of bounds.");
			System.out.println(NAME + "'s x is " + LOCATION_X);
			LOCATION_X = 250;
		}
		if(LOCATION_Y > 700 || LOCATION_Y < 0){
			System.out.println(NAME + " has went out of bounds.");
			System.out.println(NAME + "'s y is " + LOCATION_Y);
			LOCATION_Y = 350;
		}
	}
	
	public void collisionTest() {
		for(GameElement E : BOARD.getGameElements()) {
			if(E != this){
				Rectangle myBounds = new Rectangle(LOCATION_X,LOCATION_Y,SIZE_X,SIZE_Y);
				Rectangle rekt = new Rectangle(E.getLocationX(),E.getLocationY(),E.getSizeX(),E.getSizeY());
				if(myBounds.intersects(rekt)){
					if(LEFT_LINE < E.getRightLine()+5&& LOCATION_X > E.getRightLine()+5){
						System.out.println("Hit left");
						SLOPE_X *= -1;
					}else if(RIGHT_LINE > E.getLeftLine() - 5&& LOCATION_X < E.getLeftLine()-5){
						System.out.println("Hit right");
						SLOPE_X *= -1;
					}
					if(TOP_LINE < E.getBottomLine() +5&& LOCATION_Y > E.getBottomLine()+5){
						System.out.println("Hit top");
						SLOPE_Y *= -1;
					}else if(BOTTOM_LINE > E.getTopLine() - 5&& LOCATION_Y < E.getTopLine()-5){
						System.out.println("Hit bottom");
						SLOPE_Y *= -1;
					}
				}
			}
		}
	}

//-----------------------------------------------------------------------------------------------	
	
	public int getVelocity() {
		return VELOCITY;
	}
	
	public void setVelocity(int velocity) {
		VELOCITY = velocity;
	}
	
//-----------------------------------------------------------------------------------------------	
	
	public int getSlopeX() {
		return SLOPE_X;
	}
	
	public int getSlopeY() {
		return SLOPE_Y;
	}
	
	public void setSlopeX(int slopeX) {
		SLOPE_X = slopeX;
	}
	
	public void setSlopeY(int slopeY) {
		SLOPE_Y = slopeY;
	}
	
	public void setSlope(int slopeX, int slopeY) {
		SLOPE_X = slopeX;
		SLOPE_Y = slopeY;
	}
	
	public void drawBall(Graphics g){
		g.setColor(COLOR);
		g.fillOval(LOCATION_X, LOCATION_Y, SIZE_X, SIZE_Y);
	}
	
//	System.out.println("Initiate: Collision");
//	boolean firstEncounter = true;
//	int lastX = 0;
//	int lastY = 0;
//	GhostElement ghost = new GhostElement();
//	for(int countX = LEFT_LINE; countX <= RIGHT_LINE; countX++) {
//		for(int countY = TOP_LINE; countY <= BOTTOM_LINE; countY++) {
//			System.out.println(countX + "," + countY);
////			System.out.println();
//			ArrayList<GameElement> currentElements = BOARD.getElementsAtLocation(countX, countY);
//			for(int count = 0; count < currentElements.size(); count++) {
//				GameElement element = currentElements.get(count);
////				System.out.println(element.getName());
//				if(element != this && firstEncounter) {
//					System.out.println(element);
//					ghost.CREATED = true;
//					ghost.TOP_LINE = countY;
//					ghost.LEFT_LINE = countX;
//					firstEncounter = false;
//					lastX = countX;
//					lastY = countY;
//				} else if(element != this) {
//					lastX = countX;
//					lastY = countY;
//				}
//			}
//		}
//	}
//	
//	if(ghost.CREATED) {
//		ghost.RIGHT_LINE = lastX;
//		ghost.BOTTOM_LINE = lastY;
//		System.out.println(ghost + "\n");
//		
//		int ghostXDiff = Math.abs(ghost.LEFT_LINE - ghost.RIGHT_LINE);
//		int ghostYDiff = Math.abs(ghost.TOP_LINE - ghost.BOTTOM_LINE);
//		
//		System.out.println("X Diff: " + ghostXDiff);
//		System.out.println("Y Diff: " + ghostYDiff);
//		
//		if(ghostXDiff - 2 > ghostYDiff + 2) {
//			System.out.println("X");
//			SLOPE_X /= -1;
//			
//			if(ghost.RIGHT_LINE > LOCATION_X) {
//				int move = ghost.LEFT_LINE - (SIZE_X/2);
//				moveSelf(move - 2, LOCATION_Y);
//			} else {
//				int move = ghost.RIGHT_LINE + (SIZE_X/2);
//				moveSelf(move + 2, LOCATION_Y);
//			}
//		} else if(ghostXDiff + 2 < ghostYDiff - 2) {
//			System.out.println("Y");
//			SLOPE_Y /= -1;
//
//			if(ghost.TOP_LINE > LOCATION_Y) {
//				int move = ghost.BOTTOM_LINE + (SIZE_X/2);
//				moveSelf(LOCATION_X, move + 2);
//			} else {
//				int move = ghost.TOP_LINE - (SIZE_X/2);
//				moveSelf(LOCATION_X, move - 2);
//			}
//		} else {
//			System.out.println("BOTH");
//			SLOPE_X /= -1;
//			SLOPE_Y /= -1;
//		}
//		
//		System.out.println(ghost + "\n");
//	}
//	private class GhostElement {
//		public boolean CREATED = false;
//		public int TOP_LINE;
//		public int RIGHT_LINE;
//		public int BOTTOM_LINE;
//		public int LEFT_LINE;
//		
//		public String toString() {
//			String output = CREATED + ":\n";
//			output += "Top Left:     (" + LEFT_LINE + "," + TOP_LINE + ")\n";
//			output += "Bottom Right: (" + RIGHT_LINE + "," + BOTTOM_LINE + ")\n";
//			return output;
//		}
//	}
}
