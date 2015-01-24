package main;

import lan.Client;
import lan.Server;

public class LanTestMain {
	
	public static Server SERVER;
	public static Client CLIENT;
	
	public static void main(String[] args){
		SERVER = new Server();
		CLIENT = new Client();
		SERVER.startServer();
		Thread temp = new Thread(new Runnable(){
			public void run(){
				SERVER.waitForPlayers();
			}
		});
		temp.start();
		CLIENT.connectToServer("127.0.0.1");
		SERVER.sendToAll(Server.COM_EVENT + " " + 4 + " " + 1);
	}
}
