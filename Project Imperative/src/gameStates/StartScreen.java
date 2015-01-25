package gameStates;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreen extends BasicGameState {
	private TextField textInput;
	private Image background;

	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		textInput = new TextField(container, null, 200, 50, 100, 20);
		background = new Image("slick/testdata/Feild Background.png"); 
		
	}

	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		background.draw();
		// textInput.render(container, g);
	}

	public void update(GameContainer container, StateBasedGame arg1, int arg2) throws SlickException {
		
	}

	public int getID() {
		return 0;
	}

}
