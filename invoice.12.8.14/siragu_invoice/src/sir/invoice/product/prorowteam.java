package sir.invoice.product;

public class prorowteam {
    
	private String imageId;
    private String name;
    private int price;
    private double Total_price;
        private int quty;
 
    public prorowteam(String imageId, String name, int price,double d,int quty) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
        this.Total_price = d;
        this.quty = quty;
    }


    public int getquty()
    {
    	return quty;
    }
    
    public void setquty(int quty)
    {
        this.quty = quty;
        	
    }
    
    public double getTotal_Price() {
        return Total_price;
    }
    public void setTotal_Price(double totalprice) {
        this.Total_price = totalprice;
    }
    public String getImageId() {
        return imageId;
    }
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
    public int getprice() {
        return price;
    }
    public void setprice(int price) {
        this.price = price;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    @Override
   
    public String toString() {
        return  "{"+name+","+price+","+Total_price+"}";
    }
    }
