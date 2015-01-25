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
	private InetAddress IP;
	
	public ServerToClientConnection(Server server,DataOutputStream dataOut, DataInputStream dataIn,InetAddress ip){
		SERVER = server;
		DATA_OUT = dataOut;
		DATA_IN = dataIn;
		IP = ip;
	}
	public void run(){
		while(true){
			try {
				String message = DATA_IN.readUTF();
				SERVER.sendToAll(message);
				interpretMessage(message);
			} catch (IOException e) {
				System.out.println("ServerToClientConnection: I have lost connection to the network.");
			}
		}
	}
	
	public void interpretMessage(String message){
		Thread temp = new Thread(new Runnable(){
			public void run(){
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
		});
		temp.start();
	}
	
	public void sendCommand(String toSend){
		try {
			DATA_OUT.writeUTF(toSend);
		} catch (IOException e) {
			System.out.println("ServerToClientConnection: I lost connection to the network or my client lost connection");
		}
	}
}
