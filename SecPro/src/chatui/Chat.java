package chatui;

import Client.Client;
import Server.Server;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chat {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Chat");
		JButton b1 = new JButton("Create");
		JButton b2 = new JButton("Join");
		frame.setLayout(null);
		
		frame.setSize(300,500);
		
	
		b1.setSize(80,20);
		b2.setSize(60,20);
		
		b1.setLocation(60, 250);
		b2.setLocation(160, 250);
		
		b1.addActionListener(new EventServer());
		b2.addActionListener(new EventClient());
		
		frame.addWindowListener(new EventHandler());
		frame.add(b1);
		frame.add(b2);
		
		
		frame.setVisible(true);
		
		
	}

}

class EventHandler implements WindowListener{
	public void windowOpened(WindowEvent e){

	}
	public void windowClosing(WindowEvent e){
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);
	}
	public void windowClosed(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeactivated(WindowEvent e){}
}

class EventClient implements ActionListener{
	public void actionPerformed(ActionEvent e){
		new Client();
	}
}

class EventServer implements ActionListener{
	public void actionPerformed(ActionEvent e){
		new Server();
	}
}


