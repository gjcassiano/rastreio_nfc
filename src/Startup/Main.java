package Startup;

import Communication.Serial;
import Communication.SerialInterface;

public class Main  implements SerialInterface{

	public static void main(String[] args) {
		Serial serial = new Serial();
	}

	@Override
	public void ReceivedMsg(String msg) {
		System.out.println("lol: "+ msg );
		
	}
}
