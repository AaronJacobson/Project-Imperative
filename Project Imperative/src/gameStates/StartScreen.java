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
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		textInput = new TextField(container, container.getDefaultFont(), 200, 350, 100, 20);
		background = new Image("slick/testdata/Feild Background.png"); 
		option1 = "StartGame";
		option2 = "Join Game"; 
		option3 = "Create Game";
		option4 = "   Quit";
		
	}
	
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		background.draw();
		textInput.render(container, g);
		
		g.drawString(option1, 205, 260);
		g.drawString(option2, 205, 305);
		g.drawString(option3, 205, 395);
		g.drawString(option4, 208, 540);
	}

	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		
	}

	public int getID() {
		return 0;
	}

}
