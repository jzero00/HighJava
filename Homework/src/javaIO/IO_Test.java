package javaIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO_Test {

<<<<<<< HEAD
	public static void main(String[] args) {

		// 원본 파일 경로
		String oriFilePath = "D:\\D_Other\\Tulips.jpg";
		//복사될 파일 경로
		String copyFilePath = "D:\\D_Other\\복사본_Tulips.jpg";

		//파일객체생성
		File oriFile = new File(oriFilePath);
		//복사파일객체생성
		File copyFile = new File(copyFilePath);

		try {

			FileInputStream fis = new FileInputStream(oriFile); //읽을파일
			FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일

			int fileByte = 0; 
			// fis.read()가 -1 이면 파일을 다 읽은것
			while((fileByte = fis.read()) != -1) {
				fos.write(fileByte);
			}
			//자원사용종료
			fis.close();
			fos.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

=======
    public static void main(String[] args) {
	
	//원본 파일경로
        String oriFilePath = "d:/D_Other/Tulips.jpg";
        //복사될 파일경로
        String copyFilePath = "d:/D_Other/복사본_Tulips.jpg";
        
        //파일객체생성
        File oriFile = new File(oriFilePath);
        //복사파일객체생성
        File copyFile = new File(copyFilePath);
        
        try {
            
            FileInputStream fis = new FileInputStream(oriFile); //읽을파일
            FileOutputStream fos = new FileOutputStream(copyFile); //복사할파일
            
            int fileByte = 0; 
            // fis.read()가 -1 이면 파일을 다 읽은것
            while((fileByte = fis.read()) != -1) {
                fos.write(fileByte);
            }
            //자원사용종료
            fis.close();
            fos.close();
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
>>>>>>> master
}
