package sir.invoice.cart;



public class GrandTotal {
	
	
	cart a = new cart() ;
	
	public double getGradeTotal()
	{
		 double GradeTotal=0;
		 
	 for(int i=0;i<=a.GetCart().size()-1;i++)
	 {
		 GradeTotal= a.GetCart().get(i).getTotal_Price()+GradeTotal;
		 	 
	 }
		
	return GradeTotal;
	
	

	
	}
}