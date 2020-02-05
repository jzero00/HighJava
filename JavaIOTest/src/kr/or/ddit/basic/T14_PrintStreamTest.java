package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 프린트기능 제공 보조 스트림 
 */
public class T14_PrintStreamTest {

    public static void main(String[] args) throws FileNotFoundException, IOException {
	
	FileOutputStream fout = new FileOutputStream("E:/D_Other/print.txt");
	FileOutputStream fout2 = new FileOutputStream("E:/D_Other/print2.txt");
	
	PrintStream out = new PrintStream(fout);
	out.print("안녕하셔요. PrintStream입니다. \r\n");
	out.print("안녕하셔요. PrintStream입니다.2 \n");
	out.print("안녕하셔요. PrintStream입니다.3 \n");
	
	out.close();
	
	PrintWriter writer = new PrintWriter(new OutputStreamWriter(fout2, "UTF-8"));
	writer.print("안녕하셔요. PrintWriter입니다.\r\n");
	writer.print("안녕하셔요. PrintWriter입니다.2\r\n");
	writer.print("안녕하셔요. PrintWriter입니다.3\r\n");
	
	writer.close();
	
    }
    
}
