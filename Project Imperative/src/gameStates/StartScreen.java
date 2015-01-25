package gameStates;

import game.Paddle;
import lan.Client;
import lan.Server;
import main.Main;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartScreen extends BasicGameState {
	private TextField textInput;
	private Image background;
	private String option2;
	private String option3;
	private String option4;
	private int optionNumb = 2;
	
		

	public void handleInput(Input i, int delta){
		if(i.isKeyPressed(Input.KEY_DOWN)){
			if(optionNumb < 4){
				optionNumb++;
			}
		}else if(i.isKeyPressed(Input.KEY_UP)){
			if(optionNumb > 2){
			optionNumb--;
			}
		}else if(i.isKeyPressed(Input.KEY_ENTER)){
			if(optionNumb == 2){//Join Server
				Main.CLIENT = new Client();
				Main.CLIENT.connectToServer(textInput.getText());
			}else if(optionNumb == 3){//Create Server
				Main.SERVER = new Server();
				Main.CLIENT = new Client();
				Main.CLIENT.connectToServer("127.0.0.1");
				Main.SERVER.startServer();
				Main.SERVER.waitForPlayers();
			}else {
				System.exit(0);
			}
		}
	}
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		textInput = new TextField(container, container.getDefaultFont(), 200, 350, 100, 20);
		background = new Image("slick/testdata/Feild Background.png"); 
		option2 = "Join Server"; 
		option3 = "Create Server";
		option4 = "   Quit";
		
	}
	
	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		g.setColor(Color.blue);
		background.draw();
		textInput.render(container, g);
		if(optionNumb == 2){
			g.setColor(Color.magenta);
			g.drawString(option2, 205, 305);
			g.setColor(Color.blue);
		}else{
			g.drawString(option2, 205, 305);
		}
		
		if(optionNumb == 3){
			g.setColor(Color.magenta);
			g.drawString(option3, 205, 395);
			g.setColor(Color.blue);
		}else{
			g.drawString(option3, 205, 395);
		}
		if(optionNumb == 4){
			g.setColor(Color.magenta);
			g.drawString(option4, 208, 540);
			g.setColor(Color.blue);
		}else{
			g.drawString(option4, 208, 540);
		}
	}

	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {
		handleInput(container.getInput(), delta);
	}

	public int getID() {
		return 0;
	}

}
