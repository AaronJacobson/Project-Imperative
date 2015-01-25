package controls;

import game.Paddle;

import org.newdawn.slick.Input;

public class keyboardControls {
	private Paddle controledPaddle;
	
	public keyboardControls(Paddle newPaddle){
		controledPaddle = newPaddle;
	}

	public void handleInput(Input i, int delta){
		if(i.isKeyDown(Input.KEY_D)){
			controledPaddle.moveRight(delta);
		}else if(i.isKeyDown(Input.KEY_A)){
			controledPaddle.moveLeft(delta);
		}
	}
}
