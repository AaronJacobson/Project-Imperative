package lan;

import gameStates.PongGame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerToClientConnection extends Thread{
	private Server SERVER;
	private DataOutputStream DATA_OUT;
	private DataInputStream DATA_IN;
	private InterpretClientMessageThread INTERPRET_THREAD;
	private InetAddress IP;

	public ServerToClientConnection(Server server,DataOutputStream dataOut, DataInputStream dataIn,InetAddress ip){
		SERVER = server;
		DATA_OUT = dataOut;
		DATA_IN = dataIn;
		IP = ip;
		INTERPRET_THREAD = new InterpretClientMessageThread(this);
	}
	public void run(){
		while(true){
			try {
				String message = DATA_IN.readUTF();
				INTERPRET_THREAD.setMessage(message);
				INTERPRET_THREAD.start();
			} catch (IOException e) {
				System.out.println("ServerToClientConnection: I have lost connection to the client, either you have lost connection to the network or they did.");
				break;
			}
		}
	}

	public void interpretMessage(String message){
		Scanner messageScanner = new Scanner(message);
		String theCommand = messageScanner.next();
		if(theCommand.equals(Server.COM_COORDS)){
			String name = messageScanner.next();
			int xLocation = messageScanner.nextInt();
			int yLocation = messageScanner.nextInt();
			PongGame.board.getElement(name).setLocation(xLocation, yLocation);
		}
		messageScanner.close();
	}
	
	public void sendToEveryoneElse(String toSend){
		for(ServerToClientConnection C : SERVER.getConnections()){
			if(C != this){
				sendCommand(toSend);
			}
		}
	}
	
	public void sendStart(String toSend){
		System.out.println("ServerToClientConnction: sending " + toSend + " " + SERVER.IPS.indexOf(IP.toString()));
		sendCommand(toSend + " " + SERVER.IPS.indexOf(IP.toString()));
	}

	public void sendCommand(String toSend){
		try {
			DATA_OUT.writeUTF(toSend);
		} catch (IOException e) {
			System.out.println("ServerToClientConnection: I lost connection to the network or my client lost connection");
		}
	}
}
class InterpretClientMessageThread extends Thread{
	private String MESSAGE;
	private ServerToClientConnection SERVER_TO_CLIENT_CONNECTION;
	
	public InterpretClientMessageThread(ServerToClientConnection clientConnection){
		SERVER_TO_CLIENT_CONNECTION = clientConnection;
	}
	
	public void setMessage(String message){
		MESSAGE = message;
	}
	
	public void run(){
		SERVER_TO_CLIENT_CONNECTION.sendToEveryoneElse(MESSAGE);
		SERVER_TO_CLIENT_CONNECTION.interpretMessage(MESSAGE);
	}
}
