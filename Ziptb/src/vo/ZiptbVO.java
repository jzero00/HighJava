package vo;

public class ZiptbVO {

    private String zipCode;
    private String sido;
    private String gugun;
    private String dong;
    private String ri;
    private String bldg;
    private String bunji;

    public ZiptbVO(String zipCode, String sido, String gugun, String dong, String ri, String bldg, String bunji) {
	super();
	this.zipCode = zipCode;
	this.sido = sido;
	this.gugun = gugun;
	this.dong = dong;
	this.ri = ri;
	this.bldg = bldg;
	this.bunji = bunji;
    }

    public String getZipCode() {
	return zipCode;
    }

    public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
    }

    public String getSido() {
	return sido;
    }

    public void setSido(String sido) {
	this.sido = sido;
    }

    public String getGugun() {
	return gugun;
    }

    public void setGugun(String gugun) {
	this.gugun = gugun;
    }

    public String getDong() {
	return dong;
    }

    public void setDong(String dong) {
	this.dong = dong;
    }

    public String getRi() {
	return ri;
    }

    public void setRi(String ri) {
	this.ri = ri;
    }

    public String getBldg() {
	return bldg;
    }

    public void setBldg(String bldg) {
	this.bldg = bldg;
    }

    public String getBunji() {
	return bunji;
    }

    public void setBunji(String bunji) {
	this.bunji = bunji;
    }

}
