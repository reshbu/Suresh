package sir.invoice.category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import sir.invoice.R;
import sir.invoice.database.Invoice_database;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class categoryinfo extends Activity{
	 Invoice_database db_pro;
	  public Context context;
	  List<String[]> categorylist_array = null ;
	 @SuppressWarnings("null")
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sir_categorydisplay);
	       try
	       {
	        context = this;
	        
	        db_pro = new Invoice_database(this,1);
	        
	    //    List<cat_rowtem> categorylist = null ;
	     //   String[] cat_Arr = null;
	        List<String> cat_Arr = new ArrayList<String>();
	       
	        		
	        		categorylist_array=db_pro.pro_selectAll();
	        		 
	        		for (String[] details :categorylist_array ) {
	        	
	        			cat_Arr.add(details[1].toString());
	        		 
	        		 }
	        
	        
	        
	     	        List<String> cat_tmpList =cat_Arr ;
	        TreeSet<String> cat_name = new TreeSet<String>(cat_tmpList);
	 	
	        Object[] cat_name_list = cat_name.toArray() ;
	        
	       // String[] cat_name_list = (String[]) cat_name.toArray();
	        
	        List<cat_rowtem> category_list = new ArrayList<cat_rowtem>();
	        
	        
	        for(int i=0;i<cat_name.size();i++)
	        {
	        	cat_rowtem item = new cat_rowtem(""+i,""+cat_name_list[i].toString());
	        	category_list.add(item);
	        }
	      
	        ListView   listView = (ListView) findViewById(R.id.sir_catdis_listview);
	        
	        CategoryListViewAdapter adapter = new CategoryListViewAdapter(getBaseContext(),R.layout.sir_cat_listview,category_list);
	     	     
	   
			listView.setAdapter(adapter);
	        
	        
	          //               listView.setAdapter(adapter);
	                     
	        
	       }
	       catch(Exception e)
	       {
	    	   Log.i("cat", e.toString());
	       }
	        
	 }

}
