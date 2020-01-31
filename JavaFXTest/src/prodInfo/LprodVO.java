package prodInfo;

public class LprodVO {

    private String id;
    private String name;
    private String lgu;
    private int cost;
    private int price;
    private int sale;
    private String outline;
    private String detail;

    public LprodVO(String id, String name, String lgu, int cost, int price, int sale, String outline, String detail) {
	super();
	this.id = id;
	this.name = name;
	this.lgu = lgu;
	this.cost = cost;
	this.price = price;
	this.sale = sale;
	this.outline = outline;
	this.detail = detail;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLgu() {
	return lgu;
    }

    public void setLgu(String lgu) {
	this.lgu = lgu;
    }

    public int getCost() {
	return cost;
    }

    public void setCost(int cost) {
	this.cost = cost;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public int getSale() {
	return sale;
    }

    public void setSale(int sale) {
	this.sale = sale;
    }

    public String getOutline() {
	return outline;
    }

    public void setOutline(String outline) {
	this.outline = outline;
    }

    public String getDetail() {
	return detail;
    }

    public void setDetail(String detail) {
	this.detail = detail;
    }

}
