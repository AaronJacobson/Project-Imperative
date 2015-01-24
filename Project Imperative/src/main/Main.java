package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
	
	public static Main Game = new Main("Game");
	
	public Main(String name) {
		super(name);
	}

	public static void main(String[] args) throws SlickException{
		AppGameContainer gameFrame = new AppGameContainer(Game);
		gameFrame.setTargetFrameRate(60);
		gameFrame.setDisplayMode(1000, 500, false);		
		gameFrame.start();		
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		
	}
}
