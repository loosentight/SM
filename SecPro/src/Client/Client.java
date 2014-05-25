package Client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Client extends JFrame{
	JTextField text;
	static JTextArea area = new JTextArea();
	Socket socket = null;
	Receiver receiver = null;
	DataOutputStream out;
	DataInputStream in;
	String name, msg;
	
	public Client(){
		super("Client");
		text = new JTextField();
		area.setEditable(false);		
		
		setSize(300,500);
		add(text, BorderLayout.SOUTH);
		add(area, BorderLayout.CENTER);
		setVisible(true);
		text.requestFocus();
		
		text.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				msg = text.getText();
				area.append( name + msg + "\n");
				text.setText("");
				text.requestFocus();
				
				try{
					out.writeUTF(name + msg + "\n");
				}catch(IOException ee){
					ee.printStackTrace();
				}
			}
		});
		
		
		
		// conn
		try{
			
			//socket = new Socket("127.0.0.1", 7777);
			System.out.println("what?");
			socket = new Socket("192.168.123.165", 7777);
			area.append("Connected....\n");
			
			out = new DataOutputStream(socket.getOutputStream());
			name = "[" + "Client" + "] ";

			receiver = new Receiver(socket);
			

			receiver.start();
			
			
		}catch(IOException e){
		}
		
	}
	
	class Receiver extends Thread{
		Socket rsocket;
		public Receiver(Socket socket){
			rsocket = socket;
			try{
				in = new DataInputStream(rsocket.getInputStream());
			}catch(IOException e){}
		
		}
		
		public void run(){
			while(in != null){
				try{
					area.append(in.readUTF());
			
				}catch(IOException e){}
			}
			
		}
	}
	
	

}
