package game;

import java.util.ArrayList;

public class Ball extends GameElement {
	private int SLOPE_X = 1;
	private int SLOPE_Y = 1;
	private int VELOCITY = 5;

	public Ball(Board board, int size) {
		super(board, size);
		NAME = "Quincy";
	}

	public Ball(Board board, int size, int locationX, int locationY) {
		super(board,size,size,locationX,locationY);
		NAME = "Quincy";
	}
	
	public Ball(Board board, int size, int slopeX, int slopeY, int velocity) {
		this(board, size);
		setSlope(slopeX, slopeY);
		setVelocity(velocity);
		NAME = "Quincy";
	}

	public Ball(Board board, int size, int locationX, int locationY, int slopeX, int slopeY, int velocity) {
		this(board,size,size,locationX,locationY);
		setSlope(slopeX, slopeY);
		setVelocity(velocity);
		NAME = "Quincy";
	}

	public void moveSelf() {
		int newX = LOCATION_X + (SLOPE_X * VELOCITY);
		int newY = LOCATION_Y + (SLOPE_Y * VELOCITY);
		super.moveSelf(newX, newY);
		collisionTest();
	}
	
	public void collisionTest() {
		boolean firstEncounter = true;
		int lastX = 0;
		int lastY = 0;
		GhostElement ghost = new GhostElement();
		for(int countX = LEFT_LINE; countX <= RIGHT_LINE; countX++) {
			for(int countY = TOP_LINE; countY <= BOTTOM_LINE; countY++) {
				ArrayList<GameElement> currentElements = BOARD.getElementsAtLocation(countX, countY);
				for(int count = 0; count < currentElements.size(); count++) {
					GameElement element = currentElements.get(count);
					if(element != this && firstEncounter) {
						ghost.CREATED = true;
						ghost.TOP_LINE = countY;
						ghost.LEFT_LINE = countX;
						firstEncounter = false;
						lastX = countX;
						lastY = countY;
					} else if(element != this) {
						lastX = countX;
						lastY = countY;
					}
					
				}
			}
		}
		
		if(ghost.CREATED) {
			ghost.RIGHT_LINE = lastX;
			ghost.BOTTOM_LINE = lastY;
			System.out.println(ghost + "\n");
			
			int ghostXDiff = Math.abs(ghost.LEFT_LINE - ghost.RIGHT_LINE);
			int ghostYDiff = Math.abs(ghost.TOP_LINE - ghost.BOTTOM_LINE);
			
			System.out.println("X Diff: " + ghostXDiff);
			System.out.println("Y Diff: " + ghostYDiff);
			
			if(ghostXDiff - 2 > ghostYDiff + 2) {
//				System.out.println("X");
				SLOPE_X /= -1;
				
				if(ghost.RIGHT_LINE > LOCATION_X) {
					int move = ghost.LEFT_LINE - (SIZE_X/2);
					moveSelf(move - 2, LOCATION_Y);
				} else {
					int move = ghost.RIGHT_LINE + (SIZE_X/2);
					moveSelf(move + 2, LOCATION_Y);
				}
			} else if(ghostXDiff + 2 < ghostYDiff - 2) {
//				System.out.println("Y");
				SLOPE_Y /= -1;

				if(ghost.TOP_LINE > LOCATION_Y) {
					int move = ghost.BOTTOM_LINE + (SIZE_X/2);
					moveSelf(LOCATION_X, move + 2);
				} else {
					int move = ghost.TOP_LINE - (SIZE_X/2);
					moveSelf(LOCATION_X, move - 2);
				}
			} else {
//				System.out.println("BOTH");
				SLOPE_X /= -1;
				SLOPE_Y /= -1;
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
	
	private class GhostElement {
		public boolean CREATED = false;
		public int TOP_LINE;
		public int RIGHT_LINE;
		public int BOTTOM_LINE;
		public int LEFT_LINE;
		
		public String toString() {
			String output = CREATED + ":\n";
			output += "Top Left:     (" + LEFT_LINE + "," + TOP_LINE + ")\n";
			output += "Bottom Right: (" + RIGHT_LINE + "," + BOTTOM_LINE + ")\n";
			return output;
		}
	}
}
