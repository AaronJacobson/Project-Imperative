package main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import gameStates.PongGame;
import gameStates.StartScreen;

public class Main extends StateBasedGame{
	public static Main Game = new Main("Game");
	public StartScreen OptionMenu = new StartScreen();
	public PongGame MainGame = new PongGame();
	
	public Main(String name) {
		super(name);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer gameFrame = new AppGameContainer(Game);
		gameFrame.setTargetFrameRate(60);
		gameFrame.setDisplayMode(500, 700, false);		
		gameFrame.start();		
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		addState(OptionMenu);
		addState(MainGame);
	}
}
