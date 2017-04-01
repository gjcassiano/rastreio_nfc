package Gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Dao.ObjetoDao;
import Dao.ObjetoDaoImp;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class ObjetoScrean {
	private JTextField textField;
	private JFrame jFrame;
	private JLabel lblName;
	private JTextField textField_1;
	private JTextArea textArea;
	private JLabel lblInfo;
	private JButton btnSave;
	public ObjetoScrean(ObjetoDao objetoDao) {
		jFrame = new JFrame("Read and Write");
		jFrame.setResizable(false);
		jFrame.setTitle("Objeto");
		jFrame.setSize(new Dimension(432, 263));
		jFrame.getContentPane().setLayout(null);	
		
		JLabel lblNuid = new JLabel("NUID:");
		lblNuid.setBounds(10, 11, 46, 14);
		jFrame.getContentPane().add(lblNuid);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(171, 173, 179)));
		textField.setEnabled(false);
		textField.setBounds(10, 30, 209, 20);
		jFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(229, 11, 46, 14);
		jFrame.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new LineBorder(new Color(171, 173, 179)));
		textField_1.setBounds(229, 30, 190, 20);
		jFrame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setColumns(10);
		textArea.setBounds(10, 81, 409, 113);
		jFrame.getContentPane().add(textArea);
		
		lblInfo = new JLabel("Info:");
		lblInfo.setBounds(10, 61, 46, 14);
		jFrame.getContentPane().add(lblInfo);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		btnSave.setBounds(330, 198, 89, 23);
		jFrame.getContentPane().add(btnSave);
		
		load(objetoDao);
	}
	private void load(ObjetoDao objetoDao){
		textField.setText(objetoDao.getNuid());
		textField_1.setText(objetoDao.getName());
		textArea.setText(objetoDao.getInfo());
	}
	private void save(){
		ObjetoDao objetoDao = new ObjetoDao();
		objetoDao.setNuid(textField.getText());
		objetoDao.setName(textField_1.getText());
		objetoDao.setInfo(textArea.getText());
		ObjetoDaoImp.save(objetoDao);
	}
	public void show(){
		jFrame.setVisible(true);
	}
}
