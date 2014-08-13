package sir.invoice.product;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import sir.invoice.R;
import sir.invoice.category.CategoryListViewAdapter;
import sir.invoice.category.cat_rowtem;
import sir.invoice.database.Invoice_database;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

public class productdisplay extends Activity {
//	 Invoice_database db_pro;
	//  public Context context;
	//  List<String[]> product_array = null ;
	 @SuppressWarnings("null")
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	              	WindowManager.LayoutParams.FLAG_FULLSCREEN);

	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        
	        setContentView(R.layout.about_us);
	     
	      
	        
	        /*context = this;
	        
	        db_pro = new Invoice_database(this);
	        
	        product_array =db_pro.pro_selectAll();
	        List<prorowteam> product_list = new ArrayList<prorowteam>();
	 
	        		 
	        		for (String[] details : product_array ) {
	        		
	        			Toast.makeText(context,""+details[3],Toast.LENGTH_SHORT).show();
		        		
	        			
	        			prorowteam a = new prorowteam("http://www.buyfresh.in/android/fruits_images/apple.png", details[2].toString(), Integer.parseInt(details[3].toString()) , Integer.parseInt(details[3].toString())*1, 1);
	        			product_list.add(a);
	        			
	        		}
	        
	        		Toast.makeText(context,""+product_list.get(0),Toast.LENGTH_SHORT).show();
		        	
	        
	       
	     
	        		
	       try
	       {
	    	   
	        ListView   listView = (ListView) findViewById(R.id.sir_prodis_lv_display);
	        
	       productlistviewadapter adapter = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,product_list,1);
	
	       
	       
	       listView.setAdapter(adapter);
	     
	       
	       }
	       catch(Exception e)
	       {
	    	   Log.i("as", e.toString());
	       }
	       
	   
			*/
	        
	      
	        
	 }


}
