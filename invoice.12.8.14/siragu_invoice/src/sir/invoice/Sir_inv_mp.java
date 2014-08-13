package sir.invoice;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



import sir.invoice.JSONParser;


import sir.invoice.bill.BillPage;
import sir.invoice.bill.sir_report;
import sir.invoice.cart.cart;
import sir.invoice.category.categoryinfo;
import sir.invoice.database.Invoice_database;
import sir.invoice.product.Add_product;
import sir.invoice.product.productdisplay;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;



public class Sir_inv_mp extends Activity {

	  private static int SPLASH_TIME_OUT = 3000;
	  JSONParser json=new JSONParser();
	  JSONParser jsonParser = new JSONParser();
	  Invoice_database db_pro;
	  public Context context;
	  private ProgressDialog pDialog;
		
		
		String input_userid;
		String input_password;
		protected int code =0 ;
  	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              	WindowManager.LayoutParams.FLAG_FULLSCREEN);
      
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      
        
        setContentView(R.layout.sir_mainpage);
        
     	  
        try
        {
                
 Button bt_catdisplay = (Button) findViewById(R.id.sir_mp_bt_report) ;
        
 bt_catdisplay.setOnClickListener(
                           new Button.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                               	
                            //	   showCustomDialog();
                            	//   pDialog.cancel();
                            	   Intent i = new Intent(getBaseContext(), sir_report.class);
                                   startActivity(i);  
                            		
                             
                               	
                               }

                               
                               
                               
                               
							
                               
                               });
 
 
Button bt_downloand = (Button) findViewById(R.id.sir_mp_update) ;
 
bt_downloand.setOnClickListener(
                           new Button.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                              
                            	//   new GetCatogeries().execute();
                          //  		try {
                            		//	
                            	   
                            	   
                            	   new GetCatogeries().execute();
                            	
                            		
                            	   
                               }

							
                               
                               });
 
        
 Button bt_billinpage = (Button) findViewById(R.id.sir_mp_billpage) ;
 
 bt_billinpage.setOnClickListener(
                           new Button.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                            //   	Log.i("kjh", "baby");
                               	Intent i = new Intent(getBaseContext(), BillPage.class);
                                   startActivity(i);
                               	
                               }

							
                               
                               });
 
 
 
 Button bt_prodisplay = (Button) findViewById(R.id.sir_mp_about) ;
 
 bt_prodisplay.setOnClickListener(
                           new Button.OnClickListener() {
                               @Override
                               public void onClick(View v) {
                               	
                               	Intent i = new Intent(getBaseContext(), productdisplay.class);
                                   startActivity(i);
                               	
                               }

							
                               
                               });
 
        }
        catch (Exception e)
        {
        	Log.i("main", ""+e.toString());
        }
        
        
    }
    
    
   


    protected void showCustomDialog() {
	    final Dialog dialog = new Dialog(Sir_inv_mp.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sir_loginpage);
        Button button = (Button)dialog.findViewById(R.id.sir_lp_bt_ok);  
        
    	
        
        
        button.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	
            	input_userid="";
            	input_password ="";
            	
            	EditText  userid = (EditText) dialog.findViewById(R.id.sir_lp_ed_loginpage);
            	
                  EditText  password = (EditText) dialog.findViewById(R.id.sir_ed_lp_password);
              	
                
            	
            	input_userid = userid.getText().toString().trim();
            	input_password =  password.getText().toString().trim();
            	
            	
    
            	new   userver().execute();
            	
            	
            	
            	if(code == 1)
    	 	 	{
    	        	Intent i = new Intent(dialog.getContext(), sir_report.class);
                    startActivity(i);
                    userid.setText("");
                	
                	password.setText("");
    	 	 	}
    	 	 	else
    	 	 	{
    	 	      	Toast.makeText(dialog.getContext(),"Please check your user name and password",Toast.LENGTH_SHORT).show();
    	 	
    	 	      	userid.setText("");
                	
                	password.setText("");
    	 	      	
    	 	 	}
        	   
            	
            	
            	
            	
            }
            
        });
        Button cancel = (Button)dialog.findViewById(R.id.sir_lp_bt_cancle);  
        
        
        cancel.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            	dialog.cancel();
            	
                  }
            
        });
        
        
        
                
        dialog.show();
		
	}





	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sir_inv_mp, menu);
        return true;
    }
    
    
    
    private class GetCatogeries extends AsyncTask<Void, Void,Void>
    {

    @Override
    protected void onPreExecute()
    {
    super.onPreExecute();
    pDialog = new ProgressDialog(Sir_inv_mp.this);
	pDialog.setMessage("Loading product details. Please wait...");
	pDialog.setIndeterminate(false);
	pDialog.setCancelable(true);
	pDialog.show();
   
    
    }
    protected Void doInBackground(Void... arg0) {
    // TODO Auto-generated method stub
 	   
 	 

    		JSONParser jsonParser = new JSONParser();
    		
    		 
    		JSONArray product=null;
    		JSONArray deal=null;
    		
    		JSONArray child=null;
    		 try {

    			 List<NameValuePair> params = new ArrayList<NameValuePair>();
    	         // GETTING VALUES FROM PHP //
    			 JSONObject jsons = jsonParser.makeHttpRequest("http://www.buyfresh.in/android/invoice/display_product.php","GET", params);
    			 Log.d("show me1:" ,jsons.toString());

    			product = jsons.getJSONArray("products");
    		
    			int product_length=product.length();
    	         
    	         String check=String.valueOf(product_length);
    	        
    	         
    	         db_pro = new Invoice_database(getBaseContext(),1);  
     	  		
    	        
    	         db_pro.pro_deleteAll();
    	         
    	         for (int i = 0; i < product_length; i++) 
    	  	        {
    	  	        	 JSONObject c = product.getJSONObject(i);
    	  	        
    	  	   	 //  	Toast.makeText(context, dh1.pro_selectAll().size()+"nhg"+""+proadd_sp_qutyname.getSelectedItem().toString()+""+pro_et_name.getText().toString()+""+pro_et_price.getText().toString()+""+pro_et_stock_in_hand.getText().toString(),Toast.LENGTH_SHORT).show();
    	  	        // 	dh1.pro_insert(id, catagory_name, name, photo, price, qutytype, stockinhand)
    	  			   	
    	  			       db_pro.pro_insert(""+db_pro.pro_selectAll().size()+1, c.getString("category_name").toString(),c.getString("pro_Name").toString(),"no photo",c.getString("Price").toString(), "unit",""); 
    	  			   
    	  	        
    	  	        }
    	       
    	  	        
    	       	Toast.makeText(context,"Record saved successfully :)",Toast.LENGTH_SHORT).show();
	  			 
    	  	        
    	  	        
    				        } catch (Exception e) {
    				            e.printStackTrace();
    				        }
    	           
        
    	
 		

         
          
 	   
         
 	   
   
    return null;

    }

    
    
    
    
    @Override
    protected void onPostExecute(Void result)
    {
 	       
    	pDialog.cancel();
    super.onPostExecute(result);
    

    }
    }
    private class userver extends AsyncTask<Void, Void,Void>
    {

    @Override
    protected void onPreExecute()
    {
    super.onPreExecute();
    pDialog = new ProgressDialog(Sir_inv_mp.this);
	pDialog.setMessage("Please wait...");
	pDialog.setIndeterminate(false);
	pDialog.setCancelable(true);
	pDialog.show();
   
    }
    protected Void doInBackground(Void... arg0) {
 
    	
    	
  		
    	try
    	{
    	
    		
    		if( input_userid.equals("")&& input_password.equals("") )
    		{
    			
    		}
    		else
    		{
    			JSONParser jsonParser = new JSONParser();
 	//		JSONArray product=null;
 	 		 JSONObject jsons = null;
 	 		 
 	 		 
 			 List<NameValuePair> params = new ArrayList<NameValuePair>();
 	 		params.add(new BasicNameValuePair("phoneno",""+input_userid.toString().trim()));
 	 		params.add(new BasicNameValuePair("password",""+input_password.toString().trim()));
	 	 	jsons = jsonParser.makeHttpRequest("http://192.168.1.8/siragu_invoice/user_verification.php","GET", params);
 					    	
	 	 code = jsons.getInt("getcode");
	 	 	
	 	 	
	 	 	int input_userid = jsons.getInt("userid");
	 	 	
	 	 	
	 	 	Log.i("input_userid", ""+input_userid);
	 	 	
	 	 	
	 	 	userid us = new userid();
	 	 	
	 	 	us.setuserid(input_userid);
    		
	 	 	
	 	 	Log.i("code", ""+code);
	 	 	
	 	 	
	 	 	
	 	 	
	 	 	
	 	 
	 	 	
	 	 	
	 	 	
	 	 	
	 	 	
	 	 	
    		}	  
 		    	
 		}
 		catch(Exception e)
 		{
 			Log.i("error", e.toString() );
 		}
 		
    	
    	
    	
    	
    	
    return null;

    }

	
    
    
    
    @Override
    protected void onPostExecute(Void result)
    {
    	
    	pDialog.cancel();	 
 	       
    	
 			super.onPostExecute(result);
		
    }
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
	     if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.ECLAIR
	         && (keyCode == KeyEvent.KEYCODE_BACK    || keyCode == KeyEvent.KEYCODE_HOME)
	     && event.getRepeatCount() == 0) 
	     {
	         onBackPressed();
	     }
	     return super.onKeyDown(keyCode, event);
	 }

	 @Override
	 public void onBackPressed() {
	     // Do nothing
	
			
	    	Intent startMain = new Intent(Intent.ACTION_MAIN);
	        startMain.addCategory(Intent.CATEGORY_HOME);
	        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        startActivity(startMain);
	    	    		        System.exit(0);
	     return;
	 }
}
