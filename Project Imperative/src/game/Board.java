package game;

import java.util.ArrayList;

public class Board {
	ArrayList<GameElement> ELEMENTS;
	
	public Board() {
		ELEMENTS = new ArrayList<GameElement>();
	}
	
	public void addElement(GameElement element) {
		ELEMENTS.add(element);
	}
	
	public ArrayList<GameElement> getGameElements() {
		return ELEMENTS;
	}
	
	public ArrayList<GameElement> getElementsAtLocation(int x, int y) {
		ArrayList<GameElement> elements = new ArrayList<GameElement>();
		for(int count = 0; count < ELEMENTS.size(); count++) {
			GameElement current = ELEMENTS.get(count);
			if(current.containsCoordiates(x, y)) {
				elements.add(current);
			}
		}
		return elements;
	}
}
