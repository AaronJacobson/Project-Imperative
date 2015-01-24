package lan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

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
			}
		}
	}
	
	public void interpretMessage(String message){
		Scanner messageScanner = new Scanner(message);
		String theCommand = messageScanner.next();
		if(theCommand.equals(Server.COM_COORDS)){
			String label = messageScanner.next();
			int xLocation = messageScanner.nextInt();
			int yLocation = messageScanner.nextInt();
		}
	}
}
