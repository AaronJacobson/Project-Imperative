package lan;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;

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
		
	}
}
