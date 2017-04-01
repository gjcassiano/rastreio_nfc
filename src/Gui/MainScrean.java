package Gui;

import javax.swing.JFrame;
import javax.swing.JComboBox;


import javax.swing.JLabel;

import Communication.Serial;
import Startup.Main;

import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JToggleButton;

public class MainScrean {
	JTextArea textArea = new JTextArea();
	public JToggleButton tglbtnReadAndWriteButton;
	public MainScrean() {
		JFrame jFrame = new JFrame("Read and Write");
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				Main.serial.disconnect();
			}
		});
		jFrame.setSize(new Dimension(600, 400));
		jFrame.getContentPane().setLayout(null);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		textArea.setBounds(10, 39, 564, 312);
		jFrame.getContentPane().add(textArea);
		
		JButton btnConfig = new JButton("Config");
		btnConfig.setSize(new Dimension(70, 20));
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConfigSerial configSerial = new ConfigSerial();
				configSerial.show();
			}
		});
		btnConfig.setBounds(10, 5, 80, 30);
		jFrame.getContentPane().add(btnConfig);
		
		tglbtnReadAndWriteButton = new JToggleButton("Read and write");
		tglbtnReadAndWriteButton.setSelected(true);
		tglbtnReadAndWriteButton.setBounds(100, 4, 129, 32);
		jFrame.getContentPane().add(tglbtnReadAndWriteButton);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
	}
	
	public JTextArea getTextArea(){
		return this.textArea;
	}
}
