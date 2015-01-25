package lan;

import game.Paddle;
import gameStates.PongGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import controls.keyboardControls;
import main.Main;

public class ClientToServerConnection extends Thread{
	private DataOutputStream DATA_OUT;
	private DataInputStream DATA_IN;
	
	public ClientToServerConnection(DataOutputStream dataOut, DataInputStream dataIn){
		DATA_OUT = dataOut;
		DATA_IN = dataIn;
	}
	
	public void run(){
		while(true){
			try {
				String message = DATA_IN.readUTF();
				interpretMessage(message);
			} catch (IOException e) {
				System.out.println("ClientToServerConnection: Lost connection to the server.");
				break;
			}
		}
	}
	
	public void interpretMessage(String message) throws UnknownHostException{
		Scanner messageScanner = new Scanner(message);
		String theCommand = messageScanner.next();
		if(theCommand.equals(Server.COM_COORDS)){
			String name = messageScanner.next();
			int xLocation = messageScanner.nextInt();
			int yLocation = messageScanner.nextInt();
			PongGame.board.getElement(name).setLocation(xLocation, yLocation);
		}else if(theCommand.equals(Server.COM_EVENT)){
			int eventID = messageScanner.nextInt();
			int eventLength = messageScanner.nextInt();
			System.out.println("ClientToServerConnection: " + eventID + " " + eventLength);
		}else if(theCommand.equals(Server.COM_START)){
			main.Main.MainGame.paddle1.setName(messageScanner.next());
			main.Main.MainGame.paddle2.setName(messageScanner.next());
			if(main.Main.SERVER != null){
				main.Main.MainGame.playerControls = new keyboardControls(main.Main.MainGame.paddle2);
			}else{
				main.Main.MainGame.playerControls = new keyboardControls(main.Main.MainGame.paddle1);
			}
			Main.Game.enterState(1);
		}
		messageScanner.close();
	}
	
	public void sendMessage(String toSend){
		System.out.println("ClientToServerConnection: Sending " + toSend);
		try {
			DATA_OUT.writeUTF(toSend);
		} catch (IOException e) {
			System.out.println("ClientToServerConnection: Unable to communicate with the server.");
		}
	}
}
