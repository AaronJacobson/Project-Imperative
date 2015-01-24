package main.test;
import java.util.ArrayList;

import game.Ball;
import game.Board;
import game.GameElement;

public class GameElementTest {
	public static void main(String[] args) {
		Board board = new Board();
//		
//		GameElement element = new GameElement(board, 100);
//		System.out.println(element);
		
		Ball ball = new Ball(board, 100);
		GameElement element = new GameElement(board,50,255,250);
		System.out.println(ball + "\n" + element);
		ball.collisionTest();
	}
	
	public static void testAtLocation(Board board, int x, int y) {
		ArrayList<GameElement> elements = board.getElementsAtLocation(x, y);
		
		System.out.println("(" + x + "," + y + "):");
		for(int count = 0; count < elements.size(); count++) {
			System.out.println("  " + elements.get(count).getName());
		}
		System.out.println();
	}
}
