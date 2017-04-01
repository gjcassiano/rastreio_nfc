package Gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Communication.Serial;
import Dao.ConfigDao;
import Dao.ConfigDaoImp;
import Startup.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

public class ConfigSerial {
	public final static String SERIAL_PORT="SERIAL_PORT";
	public final static String SERIAL_DATA_RATE="SERIAL_DATA_RATE";
	
	private JTextField textField;
	private JTextField textField_1;
	private JFrame jFrame;
	public ConfigSerial() {
		jFrame = new JFrame("Read and Write");
		jFrame.setAlwaysOnTop(true);
		jFrame.setType(Type.UTILITY);
		jFrame.setResizable(false);
		jFrame.setTitle("Config");
		jFrame.setSize(new Dimension(225, 129));
		jFrame.getContentPane().setLayout(null);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveSetting();
			}
		});
		btnSave.setBounds(110, 57, 89, 23);
		jFrame.getContentPane().add(btnSave);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(10, 11, 69, 14);
		jFrame.getContentPane().add(lblPort);
		
		JLabel lblDataRate = new JLabel("Data rate");
		lblDataRate.setBounds(112, 11, 69, 14);
		jFrame.getContentPane().add(lblDataRate);
		
		textField = new JTextField();
		textField.setBounds(10, 26, 86, 20);
		jFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(112, 26, 86, 20);
		jFrame.getContentPane().add(textField_1);
		//jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadSetting();
	}
	
	private void loadSetting(){
		ConfigDao configDao = ConfigDaoImp.load();
		if(configDao == null)
			return;
		textField.setText(configDao.getPort());
		textField_1.setText(String.valueOf(configDao.getData_rate()));
	}
	private void saveSetting(){
		ConfigDao configDao = new ConfigDao();
		configDao.setPort(textField.getText());
		configDao.setData_rate(Integer.parseInt(textField_1.getText()));
		ConfigDaoImp.save(configDao);
		Main.serial = new Serial();
		Main.connectSerialport();
		
	}
	public void show(){
		jFrame.setVisible(true);
	}

	
}
