	 package sir.invoice.product;

import java.util.ArrayList;
import java.util.List;
import sir.invoice.R;
import sir.invoice.database.Invoice_database;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Add_product extends Activity {
	
	
 	
	 public Invoice_database db_pro;
	 
	 
	 public Context context;
 	  List<String[]> categorylist = null ;
 	  
 		List<String> category_name = new ArrayList<String>();
 		List<String> quty_type = new ArrayList<String>();
 	
 	
 //  List<RowItem> a = new ArrayList<RowItem>();
	
	  @SuppressLint("ShowToast") protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.sir_product_adding );
	     
	        context = this;
	       
	        	
		  
	     	 
	     	category_name.add("fruits");
	     	category_name.add("vegtable");
	     	category_name.add("others");
	     	
	        
	        final Spinner	proadd_sp_categoryname = (Spinner) findViewById(R.id.sir_proadd_sp_catname);
	         
	      
			  
			   
			  
			   ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, category_name);
			  spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 
			 proadd_sp_categoryname.setAdapter(spinnerAdapter);  
			  
			  
			  Button bt_adding_pro_details = (Button) findViewById(R.id.sir_proadd_bt_ok) ;
		//dd		db_pro = new Invoice_database_product(this);    
			  bt_adding_pro_details.setOnClickListener(
			                     new Button.OnClickListener() {
			                         @Override
			                         public void onClick(View v) {
			 try
			 {
				   EditText pro_et_name = (EditText) findViewById(R.id.sir_proadd_et_name);
			       EditText pro_et_price = (EditText) findViewById(R.id.sir_proadd_et_price);
			       db_pro = new Invoice_database(getBaseContext(),1);  
			 //  	Toast.makeText(context, dh1.pro_selectAll().size()+"nhg"+""+proadd_sp_qutyname.getSelectedItem().toString()+""+pro_et_name.getText().toString()+""+pro_et_price.getText().toString()+""+pro_et_stock_in_hand.getText().toString(),Toast.LENGTH_SHORT).show();
	        // 	dh1.pro_insert(id, catagory_name, name, photo, price, qutytype, stockinhand)
			   	
			       db_pro.pro_insert(""+db_pro.pro_selectAll().size()+1, proadd_sp_categoryname.getSelectedItem().toString(),pro_et_name.getText().toString(),"no photo", pro_et_price.getText().toString(), "unit",""); 
			   	Toast.makeText(context,"Record saved successfully :)",Toast.LENGTH_SHORT).show();
			   	pro_et_name.setText("");
			   	pro_et_price.setText("");
				     			     
			 
			 }
			 catch(SQLException e)
			 {
				 	Toast.makeText(context,"Record did not save.Please contact admination",Toast.LENGTH_SHORT).show();
			 }
			                         	
			                         }
			                         
			                         });
			             
			  
			  
	        }
	  }

			  
			  // Spinner item selection Listener  
		      //  addListenerOnSpinnerItemSelection();
	   
	  
	  


