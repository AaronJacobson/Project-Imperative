package lan;

import gameStates.PongGame;

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
	public static final String COM_START = "START_GAME";
	public static final String EX_COM_START = COM_START + " " + "paddle1Name" + " " + "paddle2Name" + " " + "playerNumber";
	
	private ServerSocket SERVER_SOCKET;
	private Socket SOCKET;
	private DataOutputStream DATA_OUT;
	private DataInputStream DATA_IN;
	private ArrayList<ServerToClientConnection> SERVER_CLIENT_CONNECTIONS;
	public ArrayList<String> IPS;
	
	public Server(){
		SERVER_CLIENT_CONNECTIONS = new ArrayList<>();
		IPS = new ArrayList<>();
	}
	
	public void startServer(){
		try {
			System.out.println("Server: I'm booting up. On the ip address of " + InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
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
				if(IPS.size() == 1){
					PongGame.paddle1.setName(SOCKET.getInetAddress().toString());
				}else if(IPS.size() == 2){
					PongGame.paddle2.setName(SOCKET.getInetAddress().toString());
				}
			} catch (IOException e) {
				System.out.println("Server: I lost connection to the network.");
			}
		}
		try {
			PongGame.paddle1.setName(InetAddress.getLocalHost().toString());
		} catch (UnknownHostException e) {
			System.out.println("Server: Lost connection to the network.");
		}
		System.out.println("Server: Starting game.");
		sendStart();
	}
	
	public void sendStart(){
		for(ServerToClientConnection C : SERVER_CLIENT_CONNECTIONS){
			System.out.println("Server: sending stuff");
			String toSend = COM_START;
			for(String I : IPS){
				toSend += " " + I;
			}
			C.sendStart(toSend);
		}
	}
	
	public void sendToAll(String toSend){
		for(ServerToClientConnection C : SERVER_CLIENT_CONNECTIONS){
			C.sendCommand(toSend);
		}
	}
	
	public ArrayList<ServerToClientConnection> getConnections(){
		return SERVER_CLIENT_CONNECTIONS;
	}
}
