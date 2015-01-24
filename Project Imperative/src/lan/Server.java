package lan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Server {
	public static final int PORT_NUMBER = 41800;
	public static final int NUMBER_OF_PLAYERS = 2;
	public static final String COM_COORDS = "HERE_ARE_SOME_COORDS";
	public static final String EX_COM_COORDS = COM_COORDS + " label yValue xValue";
	public static final String COM_EVENT = "NEW_EVENT";
	public static final String EX_COM_EVENT = COM_EVENT + " " + "eventID" + " " + "eventLength(in seconds)";
	
	private ServerSocket SERVER_SOCKET;
	private Socket SOCKET;
	private DataOutputStream DATA_OUT;
	private DataInputStream DATA_IN;
	private ArrayList<ServerToClientConnection> SERVER_CLIENT_CONNECTIONS;
	private ArrayList<String> IPS;
	
	public Server(){
		SERVER_CLIENT_CONNECTIONS = new ArrayList<>();
		IPS = new ArrayList<>();
	}
	
	public void startServer(){
		try {
			System.out.println("Server: I'm booting up. On the ip address of " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e1) {
			System.out.println("Server: Unable to connect to a network.");
		}
		try {
			SERVER_SOCKET = new ServerSocket(PORT_NUMBER);
			System.out.println("Server: I'm successfully booting up.");
		} catch (IOException e) {
			System.out.println("Server: Something is stoppin me from booting up, you probably lost connection to the network you were on.");
		}
	}
	
	public void waitForPlayers(){
		while(IPS.size() < NUMBER_OF_PLAYERS){
			try {
				SOCKET = SERVER_SOCKET.accept();
				DATA_OUT = new DataOutputStream(SOCKET.getOutputStream());
				DATA_IN = new DataInputStream(SOCKET.getInputStream());
				IPS.add(SOCKET.getInetAddress().toString());
				System.out.println("Server: New connection from " + SOCKET.getInetAddress().toString());
				ServerToClientConnection newConnect = new ServerToClientConnection(this,DATA_OUT,DATA_IN,SOCKET.getInetAddress());
				SERVER_CLIENT_CONNECTIONS.add(newConnect);
				newConnect.start();
			} catch (IOException e) {
				System.out.println("Server: I lost connection to the network.");
			}
		}
	}
	
	public void sendToAll(String toSend){
		for(ServerToClientConnection C : SERVER_CLIENT_CONNECTIONS){
			C.sendCommand(toSend);
		}
	}
}
