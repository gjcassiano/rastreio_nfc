package Communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class Serial {

	private BufferedReader input;
	private OutputStream output;

	private static final String PORT_ARDUINO = "COM9";
	private static final int TIME_OUT = 2000;
	private static int DATA_RATE = 9600;
	private SerialPort serialPort;
	
	public List<String> getPorts(){
		SerialPort ports[] = SerialPort.getCommPorts();
        List<String> lista = new ArrayList<String>();
		for (SerialPort serialPort : ports) {
			lista.add(serialPort.getSystemPortName());
		}
		return lista;
	}
	public void connect(String serialportname){
		for (String portName : getPorts()) {
			if (serialPort.getSystemPortName().equals(PORT_ARDUINO)) {
				this.serialPort = serialPort;
			}
		}
		
		
		if (this.serialPort != null) {
			Serial.DATA_RATE = this.serialPort.getBaudRate();
		   try
		    {
			   this.serialPort.openPort();
		        System.out.println("Connection is opend");
		    }
		    catch (Exception ex)
		    {
		        System.out.println("Chack connection ther is a problem");
		    }
			this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING , 0, 0);

			try
			{
				BufferedReader in = new BufferedReader(new InputStreamReader(this.serialPort.getInputStream()));
				while(this.serialPort.isOpen()){
					if(this.serialPort.bytesAvailable() >0){
						byte[] b = new byte[7*2*6+1]; //00:00:00:00:00:00:00 04:E5:A1:5A:BB:2B:80
						this.serialPort.getInputStream().read(b);
						ReceivedMsg(new String(b).trim());
					}
					Thread.sleep(100);
					
				}
			} catch (Exception e) { e.printStackTrace(); }
			
			this.serialPort.closePort();
		}
		
	}
	
	public Serial() {


		
	}
	SerialInterface serialInterface;
	public void setListener(SerialInterface serialInterface){
		this.serialInterface = serialInterface;
	}
	private void ReceivedMsg(String msg){
		System.err.println(msg);
		//serialInterface.ReceivedMsg(msg);
	}
	public static void main(String[] args) throws Exception {
		Serial main = new Serial();

	}
}