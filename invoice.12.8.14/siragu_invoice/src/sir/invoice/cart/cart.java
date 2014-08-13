package sir.invoice.cart;

import java.util.ArrayList;
import java.util.List;

import sir.invoice.product.prorowteam;



public class cart {
	static List<prorowteam> CartList = new ArrayList<prorowteam>();
	  
	public void a(String url,String Name, int price, double d,int qty) {
		// TODO Auto-generated method stub
					
		prorowteam item = new prorowteam(url,Name,price,d,qty);
		  CartList.add(item);
		
		  
    //     Toast.makeText(this, " "+Name+price+  this.CartList.size(),Toast.LENGTH_SHORT).show();
         
         

		
	}
	public List<prorowteam> GetCart()
	{
		return CartList;
	}

	
	
	
	
	

}
