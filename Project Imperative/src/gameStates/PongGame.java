package gameStates;

import game.Ball;
import game.Board;
import game.GameElement;
import main.test.GameElementTest;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class PongGame extends BasicGameState{
	Board board = new Board();
	private Ball ball = new Ball(board, 10, 300, 340,1,1,5);
//	private Ball ball2 = new Ball(board, 10, 40, 340,1,1,5);
//	private Ball ball3 = new Ball(board, 10, 100, 340,2,1,5);
//	private Ball ball4 = new Ball(board, 10, 150, 340,1,1,5);
//	private Ball ball5 = new Ball(board, 10, 300, 340,4,1,5);
	private GameElement leftWall = new GameElement(board, 15, 700, 0, 0);
	private GameElement rightWall = new GameElement(board, 15, 700, 485, 0);
	private GameElement bottomWall = new GameElement(board, 500, 15, 0, 685);
	private GameElement topWall = new GameElement(board, 500, 15, 0, 0);
	
	public void init(GameContainer container, StateBasedGame arg1) throws SlickException {
		
	}

	public void render(GameContainer container, StateBasedGame arg1, Graphics g) throws SlickException {
		ball.draw(g);
		ball.setColor(Color.blue);
//		ball2.draw(g);
//		ball2.setColor(Color.gray);
//		ball3.draw(g);
//		ball3.setColor(Color.magenta);
//		ball4.draw(g);
//		ball3.setColor(Color.red);
//		ball5.draw(g);
		
		ball.updateLocation();
//		ball2.moveSelf();
//		ball3.moveSelf();
//		ball4.moveSelf();
//		ball5.moveSelf();
		
		leftWall.draw(g);
		rightWall.draw(g);
		bottomWall.draw(g);
		topWall.draw(g);
	}

	public void update(GameContainer container, StateBasedGame arg1, int arg2) throws SlickException {	

	}

	public int getID() {
		return 1;
	}

}
