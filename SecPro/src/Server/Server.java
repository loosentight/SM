package Server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Server extends JFrame{
	JTextField text;
	static JTextArea area = new JTextArea();;
	ServerSocket serverSocket = null;
	Socket socket = null;
	Receiver receiver = null;
	DataOutputStream out;
	DataInputStream in;
	String name, msg;
	//Scanner scanner;
	public Server(){
		super("Server");
		text = new JTextField();
		
		setSize(300,500);
		add(text, BorderLayout.SOUTH);
		add(area, BorderLayout.CENTER);
		setVisible(true);
		text.requestFocus();
		//scanner = new Scanner(System.in);
		
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
		
		area.setEditable(false);
	
		// connec
		try{
			
			serverSocket = new ServerSocket(7777);
			area.append("Server is Ready...\n");
			socket = serverSocket.accept();
			
			out = new DataOutputStream(socket.getOutputStream());
			name = "[" + "Server" + "] ";
			
			
			receiver = new Receiver(socket);
			
			receiver.start();
		
		}catch(IOException e){
			e.printStackTrace();
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
