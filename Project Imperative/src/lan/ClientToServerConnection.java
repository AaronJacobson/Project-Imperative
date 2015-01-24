package lan;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class ClientToServerConnection extends Thread{
	private DataOutputStream DATA_OUT;
	private DataInputStream DATA_IN;
	
	public ClientToServerConnection(DataOutputStream dataOut, DataInputStream dataIn){
		DATA_OUT = dataOut;
		DATA_IN = dataIn;
	}
	
	
}
