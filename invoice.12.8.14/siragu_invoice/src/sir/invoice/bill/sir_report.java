package sir.invoice.bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONException;
import org.json.JSONObject;

//import com.Team4.BuyFresh.MainActivity;
//import org.json.JSONStringer;
//import com.Team4.BuyFresh.ConnectionDetector;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import sir.invoice.JSONParser;
import sir.invoice.R;
import sir.invoice.Sir_inv_mp;
import sir.invoice.cart.GrandTotal;
import sir.invoice.cart.cart;
import sir.invoice.database.Invoice_database;
//import sir.invoice.product.productlistviewadapter;
//import sir.invoice.product.prorowteam;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;

 @SuppressLint("SimpleDateFormat") @TargetApi(Build.VERSION_CODES.GINGERBREAD) public class sir_report  extends FragmentActivity    {
int xyz= 0;
	
	EditText mEdit;
	 JSONParser json=new JSONParser();
	 ListView resullist;
	 ArrayAdapter<String> listAdapter1 ;  
	
	 Date today;
	 List<NameValuePair> bill_ = new ArrayList<NameValuePair>();
     
	    List<NameValuePair> postvar = new ArrayList<NameValuePair>();
	    
	 ArrayList<String>  result = new ArrayList<String>();
	 ListView mainListView ;
	 
	 ArrayList<String> date = new ArrayList<String>();
	 
	//  private ArrayAdapter<String> listAdapter ;
	int Billno;
	 String totalamount;
	 
	 
	// int 

	public ProgressDialog pDialog;

	
	 @TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        
	        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	              	WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      
	    
	        
	        setContentView(R.layout.sir_report);
	        
	        
	        
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	        try {
				today = dateFormat.parse(dateFormat.format(new Date()));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        
	     
	        
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	        StrictMode.setThreadPolicy(policy);
	        
	        
	        ConnectionDetector   cd = new ConnectionDetector(getApplicationContext());
	 	   
	 	   // get Internet status
	        boolean isInternetPresent = cd.isConnectingToInternet();
	        if (isInternetPresent) {
	        }
	        else
	        {
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	        	builder
	        	.setTitle("No Internet")
	        	.setMessage("No internet Connection Available.Do you want to try again? ")
	        	.setIcon(android.R.drawable.ic_dialog_alert)
	        	.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
	        	    public void onClick(DialogInterface dialog, int which) {			      	
	        	    	//Yes button clicked, do something
	        	 
	        	    	Intent i = new Intent(getBaseContext(), sir_report.class);
	                    startActivity(i);
	        	         //         Toast.LENGTH_SHORT).show();
	        	    }
	        	})
	        	
	        	
	        	
	        	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
	        	    public void onClick(DialogInterface dialog, int which) {			      	
	        	    
	        	    	Intent i = new Intent(getBaseContext(), Sir_inv_mp.class);
	                    startActivity(i);
	        	    }
	        	})						//Do nothing on no
	        	.show();
	        }
	   //     StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	     //   StrictMode.setThreadPolicy(policy);
	        
	        mEdit = (EditText)findViewById(R.id.sir_rp_date);
	
	        
	        mEdit.setText("Please Select date");
	        
	        mEdit.setEnabled(false);
	        
	        
	        Button ok = (Button) findViewById(R.id.sir_rp_ok);
	        
	        
	        ok.setOnClickListener(new OnClickListener() {
	        	public void onClick(View arg0) {
	        
	        if(mEdit.getText().toString().trim().equals("Please Select date"))
	        {
	        	AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(sir_report.this);

				 dlgAlert.setMessage("Please Select date");
				 dlgAlert.setTitle("Message");
			//	 dlgAlert.setPositiveButton("OK", null);
				
				 dlgAlert.setPositiveButton("Ok",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
						          //dismiss the dialog  
						    
						        	 DialogFragment newFragment = new SelectDateFragment();
						             newFragment.show(getSupportFragmentManager(), "DatePicker");
						        }
						   
				 });
				
			       dlgAlert.setCancelable(true);
				 dlgAlert.create().show(); 
	        }
	        else
	        {
	        		
	        		xyz =2;
              	   
          		   new getdetail().execute();
          		   
	        }
	        }
	        }
	        
	        		
	        		);
	        
	        
	        
	        
	        Button totat = (Button) findViewById(R.id.sir_rp_total);
	        
	        
	        totat.setOnClickListener(new OnClickListener() {
	        				
	        				@Override
	        				public void onClick(View arg0) {
	        					
	        					   try
                            	   {
	        						   xyz =1;
                            	   
                            		   new getdetail().execute();
                            		   
                            		   
                            		 
                            		  
                            	  }
                            	   catch(Exception e)
                            	   {
                            		   Log.i("hello", e.toString());
                            	   }
	        				
	        				
	        				
	        				}
	        			});
	        	   
	        
	        	   
	       
	        
	        Button a = (Button) findViewById(R.id.sir_rp_dp);
	        
	        
a.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
			 DialogFragment newFragment = new SelectDateFragment();
             newFragment.show(getSupportFragmentManager(), "DatePicker");
				}
			});
	   
	Button a1 = (Button) findViewById(R.id.sir_clear_button);
		a1.setOnClickListener(new OnClickListener() {
				
			
				@Override
				public void onClick(View arg0) {
			         
	    	        
	    	        	 try
	    	        	 {
	    	        		 new getdetail1().execute();	           	 
						}
	    	         catch(Exception e1)
	    	         {
	    	        	  Toast.makeText(getApplicationContext(),""+e1.toString(),Toast.LENGTH_LONG).show();	
							
	    	         }
				     
	     	  		
	    	        
				
				
				
				}
			});
	 }
	 
	 
	 public void populateSetDate(int year, int month, int day) {
    	 mEdit = (EditText)findViewById(R.id.sir_rp_date);
    	 mEdit.setText(year+"-"+month+"-"+day);
    	// mEdit.setText(month+"-"+day+"-"+year);

    	
		   
    	 
    	 
	}
	
	 
    @SuppressLint("ValidFragment")
	public class SelectDateFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    	@Override
    	public Dialog onCreateDialog(Bundle savedInstanceState) {
			final Calendar calendar = Calendar.getInstance();
			int yy = calendar.get(Calendar.YEAR);
			int mm = calendar.get(Calendar.MONTH);
			int dd = calendar.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    	}
    	
    	public void onDateSet(DatePicker view, int yy, int mm, int dd) {
    		populateSetDate(yy, mm+1, dd);
    	}
	
    }
    
    private class getdetail extends AsyncTask<Void, Void,Void>
    {

    @Override
    protected void onPreExecute()
    {
    super.onPreExecute();
    pDialog = new ProgressDialog(sir_report.this);
    pDialog.setMessage("Fetching food categories..");
    pDialog.setCancelable(false);
   pDialog.show();
   // Log.d("fun","Inside");
    
    }
    protected Void doInBackground(Void... arg0) {
    // TODO Auto-generated method stub
 	      
    	result.clear();
    
    	totalamount="";
    	
    	
    	
			JSONParser jsonParser = new JSONParser();
 		
 		 
 		
 		
 		try {
 			JSONArray product=null;
 	 		 JSONObject jsons = null;
 			
 	 		 
 	 		
 	 	   List<NameValuePair> input = new ArrayList<NameValuePair>();
 	 		 
 	 		 List<NameValuePair> params = new ArrayList<NameValuePair>();
 	         // GETTING VALUES FROM PHP //
 			 
 			 if(xyz==1)
 			 {
 			 
 			 if(( jsons = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/report.php","GET", params))!=null)
 			 {	 product = jsons.getJSONArray("products");
 	 		
 		 	 totalamount = jsons.getString("total_amount").toString();
 		 			
 		 		result.clear();
 		 		
 		 		
 		 			int product_length=product.length();
 		 	      
 		 			for (int i = 0; i < product_length; i++) 
 		 	  	        {
 		 	  	        	 JSONObject c = product.getJSONObject(i);
 		 	  	        
 		 	  	        	 
 		 	
 		 	  	        	 
 		 	  	        	 result.add(""+c.getString("pro_Name")+"  - "+c.getString("Total_Quantity")+" -  "+(Integer.parseInt(c.getString("total_price"))/Integer.parseInt(c.getString("Total_Quantity")))+" -  "+(Integer.parseInt(c.getString("total_price"))));
 		 	  	        
 		 	  	        
 		 	  	        }
 		   			 Log.d("result:" ,result.toString()+jsons.getString("total_amount"));
 		   			 

 		 	
 		 	listAdapter1 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1 ,result );
 		 	
 			 
 			 
 			 }
 			  
 			 }
 	if(xyz==2)
 	{
 		 mEdit = (EditText)findViewById(R.id.sir_rp_date);
 	
 		 //if(mEdit.getText().equals(o))
 		input.add(new BasicNameValuePair("date",""+mEdit.getText().toString()));
 		
 		product = null;
 		 if(( jsons = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/report.php","POST", input ))==null)
 			 {
 			Log.i("asd","no data found");
 			 }
 		 else
 		 {
 			// result.clear();
 			
 			product = jsons.getJSONArray("products");
 	 	
 			
 			
 		 	 totalamount = jsons.getString("total_amount").toString();
 		 			
 		 		result.clear();
 		 		
 		 		
 		 			int product_length=product.length();
 		 	      
 		 			for (int i = 0; i < product_length; i++) 
 		 	  	        {
 		 	  	        	 JSONObject c = product.getJSONObject(i);
 		 	  	       	result.add(""+c.getString("pro_Name")+"\t- "+c.getString("Total_Quantity")+"\t- "+(Integer.parseInt(c.getString("total_price"))/Integer.parseInt(c.getString("Total_Quantity")))+"\t-"+(Integer.parseInt(c.getString("total_price"))));
 		 	  	        }
 		   			 Log.d("result:" ,result.toString()+jsons.getString("total_amount"));
 		   			 listAdapter1 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1 ,result );
 		 }
 	}		 
 		//	 Log.d("show me1:" ,jsons.toString());

 			
 		} catch (Exception e) {
 				            e.printStackTrace();
 				            
 				       	pDialog.cancel();
 				           Log.i("report_error",e.toString());   
 				            
 				            
 				        }
    	
 	 	pDialog.cancel();
    	
    	
    	
    	
    	
    return null;

    }

	
    
    
    
    
    @Override
    protected void onPostExecute(Void result)
    {
 	       try
 	       {
    	
        	 TextView a = (TextView) findViewById( R.id.sir_rp_totalamount);
        	 a.setText("₹"+totalamount);
        	 mainListView = (ListView) findViewById( R.id.listView1 );
	         mainListView.setAdapter( listAdapter1 ); 
  		 	 if(totalamount.equals("0"))
  		 	 {
  		 		 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(sir_report.this);

				 dlgAlert.setMessage("No sales record found on this date");
				 dlgAlert.setTitle("Message");
			//	 dlgAlert.setPositiveButton("OK", null);
				
				 dlgAlert.setPositiveButton("Ok",
						    new DialogInterface.OnClickListener() {
						        public void onClick(DialogInterface dialog, int which) {
						          //dismiss the dialog  
						    
						        
						        }
						   
				 });
				
			       dlgAlert.setCancelable(true);
				 dlgAlert.create().show();  
  		 	 }
	         
	         
	         
	         
	         xyz=0;
 	       }
 	       catch(Exception e)
 	       {
 	    	   Log.i("ghj", e.toString());
 	       }
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
	
		 NavUtils.navigateUpFromSameTask(this);
	     return;
	 }
	 
	 
	   private class getdetail1 extends AsyncTask<Void, Void,Void>
	    {

	    @Override
	    protected void onPreExecute()
	    {
	    super.onPreExecute();
	   pDialog = new ProgressDialog(sir_report.this);
	    pDialog.setMessage("Loading.....");
	    pDialog.setCancelable(false);
	   pDialog.show();
	    }
	    protected Void doInBackground(Void... arg0) {
	    // TODO Auto-generated method stub
	    	
		    	JSONArray name = new JSONArray();
			JSONArray price = new JSONArray();
			JSONArray qty = new JSONArray();
			JSONArray total = new JSONArray();
		JSONArray value1 = new JSONArray();
		
		JSONArray bill_date = new JSONArray();
			GrandTotal g = new GrandTotal();
		cart a = new cart();
		try
		{
		
		  Invoice_database db_pro = new Invoice_database(getBaseContext(),2);  
	       
		  List<String[]> bill_array = null ;
		    
		  bill_array = db_pro.bill_selectAll();
		
		Log.i("", ""+db_pro.bill_selectAll().size());
		date.clear();
 		
		a();
		
		
		
		for (String[] details :bill_array ) {
			//bill_id","name","price","qty" ,"total_amount
	
			a.a("", ""+details[1].toString(),Integer.parseInt(details[2].toString()) , Double.parseDouble(details[4].toString()),Integer.parseInt(details[3].toString()) );
		 
			Log.i("name",""+details[2].toString()) ;
			Log.i("quty",""+details[3].toString()) ;
			
			Log.i("total",""+details[4].toString() );
		
			Log.i("DATE",""+details[5].toString() );
			
			
			
			date.add(details[5].toString());
			
		 }
		
		
		
	
		
					 for (int i = 0; i < a.GetCart().size() ; i++) {
					  name.put(a.GetCart().get(i).getname()); 
					  price.put(a.GetCart().get(i).getprice());
					  qty.put(a.GetCart().get(i).getquty());
					  total.put(a.GetCart().get(i).getTotal_Price());
					  bill_date.put(date.get(i));
					  value1.put(Billno);
					 }
					
				 }
				 catch (Exception e) {
					 Log.d("name",e.toString());
				}
			//	  Log.d("name",""+name);
		
		
				//  Log.d("price",""+price);
				//  Log.d("qty",""+qty);
			//	  Log.d("total",""+total);
				//  Log.d("phone",value1);
				//String  value1="1";
	   
	   double  amount = g.getGradeTotal();
	   
	   double totalamount = amount + (amount*1/100) + (amount*1/100);
		 bill_.add(new BasicNameValuePair("amount",""+totalamount));
			
		 bill_.add(new BasicNameValuePair("paymenttype","Cash"));

		 bill_.add(new BasicNameValuePair("totalitem",""+a.GetCart().size()));

				 postvar.add(new BasicNameValuePair("pro_name",name.toString().trim()));
				 postvar.add(new BasicNameValuePair("price",price.toString().trim()));
				 postvar.add(new BasicNameValuePair("qty",qty.toString().trim()));
				 postvar.add(new BasicNameValuePair("total",total.toString().trim()));
			 postvar.add(new BasicNameValuePair("bill_id",value1.toString().trim()));
	    Log.i("bill_date", bill_date.toString().trim());      
			 postvar.add(new BasicNameValuePair("bill_date",bill_date.toString().trim()));
			       
		
			 
			 
			 
			 
	 	   
			 
			 
			 
	   
	    return null;

	    }

		
	    
	    
	    
	    @Override
	    protected void onPostExecute(Void result)
	    {
	 	       cart a = new cart();
	 	       
	 	       if(a.GetCart().size()==0)
	 	       {
	 	    	   
	 	    		
					pDialog.cancel();
					
				//	Toast.makeText(getApplicationContext(), "No Record found in localdatabase", Toast.LENGTH_SHORT).show();
				
					 AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(sir_report.this);

					 dlgAlert.setMessage("No Record found in localdatabase");
					 dlgAlert.setTitle("No Data");
				//	 dlgAlert.setPositiveButton("OK", null);
					
					 dlgAlert.setPositiveButton("Ok",
							    new DialogInterface.OnClickListener() {
							        public void onClick(DialogInterface dialog, int which) {
							          //dismiss the dialog  
							    
							        
							        }
							   
					 });
					
				       dlgAlert.setCancelable(true);
					 dlgAlert.create().show(); 
					
					
	 	    	   
	 	       }
	 	       else
	 	       {
	 	       
	    	try
			{
				JSONParser jsonParser = new JSONParser();
			
				 JSONObject sonss1  = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/bildetail.php", "POST", bill_);
					
				
			 JSONObject sonss  = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/purchase_detail.php", "POST", postvar);
			 
				
				try {
					if (sonss.getString("value").equals("1") && sonss1.getString("value").equals("1"))
					{
						//cart a = new cart();
				
						  Invoice_database db_pro = new Invoice_database(getBaseContext(),2);  
						  
						
						  a.GetCart().clear();
						  
						  
						  
						  db_pro.deleteAll();
					Toast.makeText(getApplicationContext(), "Values are stored in online ", Toast.LENGTH_SHORT).show();
				
					pDialog.cancel();
					
										}
					else
					{
					Toast.makeText(getApplicationContext(), "Connection Failed Try Again", Toast.LENGTH_SHORT).show();
					
					pDialog.cancel();
					}
					} catch (JSONException e) {
					// TODO Auto-generated catch block
						pDialog.cancel();
					Toast.makeText(getApplicationContext(), "Connection Failed Try Again", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					}

				//pDialog.cancel();
				
			
				
			}
			catch(Exception e)
			{
				Log.d("sonss",""+e.toString());	
			}
				
	 	       }        
	    super.onPostExecute(result);
	    

	    }
	    }

	 
	 
	 
	   public void a()
	   {
	 	try 
	 	{
	//   JSONParser json=new JSONParser();

	   
	 	JSONParser jsonParser = new JSONParser();
	 	
	 	 
	 	JSONArray product=null;
	 	JSONObject jsons;
	 	 List<NameValuePair> params = new ArrayList<NameValuePair>();
	   // GETTING VALUES FROM PHP //
	  jsons = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/billnumber.php","GET", params);
	 	 

	  
	  Log.d("show me1:" ,jsons.toString());

	 	 product = jsons.getJSONArray("products");

	 	int product_length=product.length();
	   
	 	
	   for (int i = 0; i <= product_length; i++) 
	        {
	        	 JSONObject c = product.getJSONObject(i);
	        
	  			  
	        
	  		Billno =	(c.getInt("MAX( bill_ID )")+1);
	       
	  		
	  		
	        }
	 	 
	 	 
	 	 } catch (JSONException e) {
	 	// TODO Auto-generated catch block
	 	e.printStackTrace();
	 }
	 	
	   
	   } 
	 
	 
	 
	 
	 
	 
    
    }

