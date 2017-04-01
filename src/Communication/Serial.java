package Communication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Dao.ConfigDaoImp;
import Gui.ConfigSerial;

import com.fazecast.jSerialComm.SerialPort;

public class Serial {

	public LinkedList<String> MessagesReceiveds = new LinkedList<String>();
	public static String PORT_ARDUINO = "";
	private static int DATA_RATE = 9600;
	private SerialPort serialPort;

	public static List<String> getPorts() {
		SerialPort ports[] = SerialPort.getCommPorts();
		List<String> lista = new ArrayList<String>();
		for (SerialPort serialPort : ports) {
			lista.add(serialPort.getSystemPortName());
		}
		return lista;
	}
	public Serial() {
		String text = "Ports avaliables: ";
		for (String port : getPorts()) {
			text += port + " ";
		}
		ReceivedMsg(text);
	}
	public void connect() {
		if (ConfigDaoImp.load() == null)
			return;
		PORT_ARDUINO = ConfigDaoImp.load().getPort();
		DATA_RATE = ConfigDaoImp.load().getData_rate();
		this.serialPort = SerialPort.getCommPort(PORT_ARDUINO);
		connect(PORT_ARDUINO);
	}

	public void connect(String serialportname) {
		if (this.serialPort == null)
			this.serialPort = SerialPort.getCommPort(PORT_ARDUINO);
		ReceivedMsg("Try connect to serial port: " + serialportname + " and DataRate: " + DATA_RATE);
		
		if (this.serialPort != null) {
			Serial.DATA_RATE = this.serialPort.getBaudRate();
			try {
				this.serialPort.openPort();
				ReceivedMsg("Connection is opend");
			} catch (Exception ex) {
				ReceivedMsg("Chack connection ther is a problem");
			}
			this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING,
					0, 0);

			Thread t = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						ReceivedMsg("Waing message of serial port");
						BufferedReader in = new BufferedReader(
								new InputStreamReader(
										serialPort.getInputStream()));

						while (serialPort.isOpen()) {
							if (serialPort.bytesAvailable() > 0) {
								byte[] b = new byte[7 * 2 * 6 + 10]; // 00:00:00:00:00:00:00
																		// 04:E5:A1:5A:BB:2B:80
								serialPort.getInputStream().read(b);
								ReceivedMsg(new String(b).trim());
							}
							Thread.sleep(100);

						}
						ReceivedMsg("The SerialPort were closed");
					} catch (Exception e) {
						ReceivedMsg("Maybe the connection have a problem");
					}

					serialPort.closePort();
				}
			});
			t.start();

		}

	}

	public boolean isOpen() {
		if (this.serialPort == null)
			return false;
		return this.serialPort.isOpen();
	}

	private void ReceivedMsg(String msg) {
		MessagesReceiveds.add(msg);
		System.err.println(msg);
	}

	public void disconnect() {
		if (this.serialPort == null)
			return;
		this.serialPort.closePort();
					
	}

}