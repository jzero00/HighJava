package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class T09_MultiChatClient2 {
    
    public static void main(String[] args) {
	new T09_MultiChatClient2().clientStart();
    }

    private String ninkName;	//대화명
    
    //비즈니스 로직 시작
    public void clientStart() {
	Scanner scan = new Scanner(System.in);	//FX쓰면 스캐너 지우기 
	System.out.println("대화명 : ");
	ninkName = scan.nextLine();
	
	scan.close();
	
	Socket socket = null;
	
	try {
	    String serverIp = "192.168.205.10";
	    socket = new Socket(serverIp, 7777);
	    
	    System.out.println("서버에 연결되었습니다.");
	    
	    //송신용 쓰레드 생성
	    ClientSender clientSender = new ClientSender(socket, ninkName);
	    
	    //수신용 쓰레드 생성
	    ClientReciever clientReciever = new ClientReciever(socket);
	    
	    //실행
	    clientSender.start();
	    clientReciever.start();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    //메시지를 전송하는 Thread
    class ClientSender extends Thread{
	Socket socket;
	DataOutputStream dos;
	String name;
	Scanner scan = new Scanner(System.in);
	
	public ClientSender(Socket socket, String name) {
	    this.socket = socket;
	    this.name = name;
	    
	    try {
		dos = new DataOutputStream(socket.getOutputStream());
	    } catch (IOException e) {

	    }
	}
	@Override
	public void run() {
	    try {
		//시작하자마자 자신의 대화명을 서버로 전송
		if(dos != null) {
		    dos.writeUTF(name);
		}
		while (dos != null) {
		    //키보드로부터 입력받은 메시지를 서버로 전송
		    dos.writeUTF("[" + name + "]" + scan.nextLine());
		}
	    } catch (IOException e) {}
	}
    }//송신용 쓰레드 끝...
    
    class ClientReciever extends Thread{
	Socket socket;
	DataInputStream dis;
	
	//생성자
	public ClientReciever(Socket socket) {
	    this.socket = socket;
	    try {
		dis = new DataInputStream(socket.getInputStream());
	    } catch (IOException e) {}
	}
	@Override
	public void run() {
	    while (dis != null) {
		try {
		    //서버로 부터 수신한 메시지 출력하기
		    System.out.println(dis.readUTF());
		} catch (IOException e) {e.printStackTrace();}
	    }
	}
    }
}
