package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * JSON : Javascript Object Notation/
 * JSON은 데이터를 주고받기 위한 기본 포맷(문법)이다.
 * 
 * 	- JSON에서 value값으로 가능한 데이터 작업
 * 	
 * 	1. String
 * 	2. number
 * 	3. object( JSON object)
 * 	4. array
 * 	5. boolean
 * 	6. null
 *
 */

public class JsonSimpleWriteTest {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
	
	// JSON 데이터 생성
	JSONObject jsonObj = new JSONObject();
	
	jsonObj.put("name", "홍길동");
	jsonObj.put("job", "학생");
	jsonObj.put("age", 30);
	jsonObj.put("addr", "대전시 중구 대흥동 대덕인재개발원");

	//JSONArray 데이터 생성
	JSONArray singerList = new JSONArray();
	
	JSONObject singer = new JSONObject();
	singer.put("name","아이유");
	singer.put("gender","여자");
	singer.put("age",28);
	singerList.add(singer);
	
	singer = new JSONObject();
	singer.put("name","아이린");
	singer.put("gender","여자");
	singer.put("age",30);
	singerList.add(singer);
	
	singer = new JSONObject();
	singer.put("name","태연");
	singer.put("gender","여자");
	singer.put("age",31);
	singerList.add(singer);
	
	jsonObj.put("singerList", singerList);
	
	FileWriter fw = new FileWriter("E:/myJSONFile.txt");
	fw.write(jsonObj.toString());
	fw.flush();
	fw.close();
	
	System.out.println("JSON객체 내용 출력 : " + jsonObj);
    } 
    
}
