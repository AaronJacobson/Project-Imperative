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
	private InterpretServerMessageThread INTERPRET_MESSAGE;
	
	public ClientToServerConnection(DataOutputStream dataOut, DataInputStream dataIn){
		DATA_OUT = dataOut;
		DATA_IN = dataIn;
		INTERPRET_MESSAGE = new InterpretServerMessageThread(this);
	}
	
	public void run(){
		while(true){
			try {
				String message = DATA_IN.readUTF();
				INTERPRET_MESSAGE.setMessage(message);
				INTERPRET_MESSAGE.start();
			} catch (IOException e) {
				System.out.println("ClientToServerConnection: Lost connection to the server, either you lost connection to the network, or they did.");
				break;
			}
		}
	}
	
	public void interpretMessage(String message){
		Scanner messageScanner = new Scanner(message);
		String theCommand = messageScanner.next();
		System.out.println(message);
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
			main.Main.MainGame.playerControls = new keyboardControls(main.Main.MainGame.PADDLES[messageScanner.nextInt()]);
			System.out.println("wubz wubz wubz");
			Main.Game.enterState(1);
		}
		messageScanner.close();
	}
	
	public void sendMessage(String toSend){
		try {
			DATA_OUT.writeUTF(toSend);
		} catch (IOException e) {
			System.out.println("ClientToServerConnection: Unable to communicate with the server.");
		}
	}
}

class InterpretServerMessageThread extends Thread{
	private String MESSAGE;
	private ClientToServerConnection CLIENT_TO_SERVER_CONNECTION;
	
	public InterpretServerMessageThread(ClientToServerConnection serverConnection){
		CLIENT_TO_SERVER_CONNECTION = serverConnection;
	}
	
	public void setMessage(String message){
		MESSAGE = message;
	}
	
	public void run(){
		CLIENT_TO_SERVER_CONNECTION.interpretMessage(MESSAGE);
	}
}
