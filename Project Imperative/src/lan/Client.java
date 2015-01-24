package lan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	private Socket SOCKET;
	private DataInputStream DATA_IN;
	private DataOutputStream DATA_OUT;
	private ClientToServerConnection CLIENT_SERVER_CONNECTION;
	
	public Client(){
		
	}
	
	public void connectToServer(String ip){
		try {
			SOCKET = new Socket(ip,Server.PORT_NUMBER);
			DATA_IN = new DataInputStream(SOCKET.getInputStream());
			DATA_OUT = new DataOutputStream(SOCKET.getOutputStream());
			System.out.println("Client: Successfully connected to the server.");
		} catch (UnknownHostException e) {
			System.out.println("Client: Unable to connect over the network.");
		} catch (IOException e) {
			System.out.println("Client: Someone is already using the port number.");
		}
	}
}
