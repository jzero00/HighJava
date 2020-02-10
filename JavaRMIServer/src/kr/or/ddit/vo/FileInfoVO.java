package kr.or.ddit.vo;

import java.io.Serializable;

/**
 * 파일 전송용 VO 클래스
 */
public class FileInfoVO implements Serializable{
    
    private String filename;
    private byte[] filedata;
    
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public byte[] getFiledata() {
        return filedata;
    }
    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }
 
}

