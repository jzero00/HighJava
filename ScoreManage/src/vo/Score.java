package vo;

public class Score {

    private String name;
    private int kor;
    private int eng;
    private int mat;


    public Score(String name, int kor, int eng, int mat) {
	super();
	this.name = name;
	this.kor = kor;
	this.eng = eng;
	this.mat = mat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKor() {
        return kor;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public int getEng() {
        return eng;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public int getMat() {
        return mat;
    }

    public void setMat(int mat) {
        this.mat = mat;
    }
    
    
}
