package main.test;
import game.GameElement;

public class GameElementTest {
	public static void main(String[] args) {
		GameElement element = new GameElement(100);
		System.out.println(element);
		
		element.moveSelf(100, 100);
		System.out.println(element);
	}
}
