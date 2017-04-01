package Startup;

import javax.swing.JFrame;
import javax.swing.UIManager;

import Communication.Serial;
import Dao.ObjetoDao;
import Dao.ObjetoDaoImp;
import Gui.MainScrean;
import Gui.ObjetoScrean;

public class Main {
    public static Serial serial = new Serial();
    static MainScrean mainScrean ;
    
    public static void connectSerialport(){
    	
    	new Thread(){
    		   @Override
    	       public void run() {
    			   receivedMsg();
    		   }
    	}.start();
    	serial.connect();
    }
    public static void disconnectSerialport(){
    	serial.disconnect();
    }
    
    public static void receivedMsg(){
    	String lastString = "";
    	while (serial != null){
    		if(!serial.MessagesReceiveds.isEmpty()){
    			  lastString = serial.MessagesReceiveds.pollFirst();
    			  //Read and write ?
    			  if(mainScrean.tglbtnReadAndWriteButton.isSelected()){
					  if(lastString.startsWith("NUID:")){
						  ObjetoDao objetoDao =new ObjetoDao();
						  objetoDao.setNuid(lastString.replace("NUID:", ""));
						  objetoDao = ObjetoDaoImp.load(lastString.replace("NUID:", ""));
						  
						  ObjetoScrean objetoScrean = new ObjetoScrean(objetoDao);
						  objetoScrean.show();
					  }
    			  }
    			  mainScrean.getTextArea().append(lastString + "\n");
    		} 
    		try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
    	}
    }
    
    public Main() {
    	  try {
    	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    	    } catch (Exception unused) {
    	      
    	    }
    	
    	mainScrean = new MainScrean();
    	connectSerialport();
    }

	public static void main(String[] args) {
		new Main();	
	}
}
