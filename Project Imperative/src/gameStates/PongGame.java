package gameStates;

import game.Ball;
import game.Board;
import game.GameElement;
import game.Paddle;
import main.test.GameElementTest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controls.keyboardControls;

public class PongGame extends BasicGameState{
	public static Board board = new Board();
	private Ball ball = new Ball(board, 20, 140, 250);
	private Ball ball2 = new Ball(board, 20, 40, 200);
	private Ball ball3 = new Ball(board, 20, 100, 100);
	private GameElement leftWall = new GameElement(board, 15, 700, 0, 0);
	private GameElement rightWall = new GameElement(board, 15, 700, 485, 0);
	private keyboardControls playerControls;
	private Paddle paddle1;
	private Paddle paddle2;
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		paddle1 = new Paddle(board, 50, 680);	
		paddle2 = new Paddle(board, 50, 0);
		playerControls = new keyboardControls(paddle1);
		paddle1.setName(main.Main.SERVER.IPS.get(0));
		paddle2.setName(main.Main.SERVER.IPS.get(1));
	}

	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		leftWall.setName("Left Wall");
		rightWall.setName("Right Wall");
		ball.drawBall(g);
		ball.setColor(Color.blue);
		ball.updateLocation();
		
		ball2.drawBall(g);
		ball2.setColor(Color.green);
		ball2.updateLocation();
		
		ball3.drawBall(g);
		ball3.setColor(Color.pink);
		ball3.updateLocation();
		
		leftWall.draw(g);
		rightWall.draw(g);
		paddle1.draw(g);
	}

	public void update(GameContainer container, StateBasedGame arg1, int delta) throws SlickException {	
		playerControls.handleInput(container.getInput(), delta);
	}

	public int getID() {
		return 1;
	}

}
