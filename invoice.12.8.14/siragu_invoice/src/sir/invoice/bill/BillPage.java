package sir.invoice.bill;

import static com.nativecss.enums.RemoteContentRefreshPeriod.Never;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nativecss.NativeCSS;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import sir.invoice.JSONParser;
import sir.invoice.R;
import sir.invoice.Sir_inv_mp;
//import sir.invoice.Sir_inv_mp.userver;
import sir.invoice.cart.GrandTotal;
import sir.invoice.cart.cart;
import sir.invoice.category.CategoryListViewAdapter;
import sir.invoice.category.cat_rowtem;
import sir.invoice.database.Invoice_database;
import sir.invoice.product.pro_grid_adapter;
import sir.invoice.product.productlistviewadapter;
import sir.invoice.product.prorowteam;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.NavUtils;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint({ "NewApi", "SimpleDateFormat", "ResourceAsColor" }) 


public class BillPage extends Activity {

	Invoice_database db_pro;
	  public Context context;
	  List<String[]> categorylist_array = null ;
      cart a = new cart();
      GrandTotal g = new GrandTotal();
      List<prorowteam> product_list = new ArrayList<prorowteam>();
      List<prorowteam> product_list1 = new ArrayList<prorowteam>();
      JSONParser jsonParser = new JSONParser();
      List<NameValuePair> postvar = new ArrayList<NameValuePair>();
    int whilechecker =0;
      RadioGroup paymenttext;
      EditText searchBox;
      Dialog dialog1;
      int asd=0;
    //  ConnectionDetector cd;
      List<NameValuePair> bill_ = new ArrayList<NameValuePair>();
      BluetoothAdapter mBluetoothAdapter;
  	BluetoothSocket mmSocket;
  	BluetoothDevice mmDevice;
  	int bluetoothtester =0;
  	OutputStream mmOutputStream;
  	InputStream mmInputStream;
  	Thread workerThread;
  	 Boolean isInternetPresent = false;
  	byte[] readBuffer;
  	int readBufferPosition;
  	
  	
  	int counter;
   //   ListView   listView;
  	volatile boolean stopWorker;
  	int checkstatus =0;
  	static int bluetoothchecker =0 ;
    
  	int bluetoothstatus =0;
  	
  	DecimalFormat df = new DecimalFormat("#.00");
      int Billno=1;
	public ProgressDialog pDialog;
      
	    
  @SuppressWarnings("unused")
@TargetApi(Build.VERSION_CODES.GINGERBREAD) @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Full screen setting , no tittle done by wiki // 
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
              	WindowManager.LayoutParams.FLAG_FULLSCREEN);
      
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      
      
      setContentView(R.layout.sir_billingpage);
    
     // it is  used for hidding keyboard //
      this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		  
      //  it is native libary used for css style in layout 
      
      NativeCSS.styleWithCSS("button#sir_bp_bt_submit{top:100px;left:265px;width:30%;text-align:center;color:white;background-color: #6a79b0;border-right:1px solid black;border-bottom:1px solid black;}button#sir_bp_bt_submit:active{background-color: #5968a0;}");

		
	    
      // calling cash radio button and make it checked

        RadioButton a1 = (RadioButton) findViewById(R.id.sir_bp_rb_cash);
        a1.setChecked(true);
         
        
        // it is used for run many thread in class any query check in internet
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
     
       
        
       // checking internet is available or not
        
        ConnectionDetector     cd = new ConnectionDetector(getApplicationContext());
        
   
       
       
       
       
        isInternetPresent = cd.isConnectingToInternet();
      
        if (isInternetPresent) {
        
        	
        	// setting billno
        	
        	if(Billno==1)
            {
      
        		try
        		{
        			
        			//a() is function it is used bring bill number form sever....,.
        			
        		a();
        		}
        		catch(Exception e)
        		{
        			Log.i("error",""+e );
        		}
            
            }
        	
        }else
        {
        	
        	/// if no internet is present dialog box will be open
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder
        	.setTitle("No Internet")
        	.setMessage("No internet Connection Available.Do you want to try again? ")
        	.setIcon(android.R.drawable.ic_dialog_alert)
        	.setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
        	    public void onClick(DialogInterface dialog, int which) {			      	
        	    	//Yes button clicked, do something
        	 
        	    	Intent i = new Intent(getBaseContext(), BillPage.class);
                    startActivity(i);
        	         //         Toast.LENGTH_SHORT).show();
        	    }
        	})
        	
        	
        	
        	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
        	    public void onClick(DialogInterface dialog, int which) {			      	
        	    	//Yes button clicked, do something
        	    	Toast.makeText(BillPage.this, "Data will not be stored in online.......", 
                                  Toast.LENGTH_SHORT).show();
        	    }
        	})						//Do nothing on no
        	.show();
        }
        
        
        
        
        /// it will clear static cart item list
       
        Button clear = (Button)  findViewById(R.id.sir_clear_button);  
        clear.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
            //	cart cr = new cart();
            	a.GetCart().clear();
            	
            	 ListView cart_lv1  =(ListView) findViewById(R.id.sir_cart_lv_bp);;
 				 productlistviewadapter cart_adap = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,a.GetCart(),0);
 			    cart_lv1.setAdapter(cart_adap);
 				billdisplay();
            	
            	
            }
        }); 
        
        
        // search box //
        //it is used for search in item in rowitem list
        
      searchBox=(EditText) findViewById(R.id.sir_serach_item);
   	 
      searchBox.clearFocus();
       
      
     searchBox.addTextChangedListener(new TextWatcher() {
    	 
    	 
     	  
     	  public void onTextChanged(CharSequence s, int start, int before, int count) {
     	    //get the text in the EditText
     	    String searchString=searchBox.getText().toString();
     		 
     	   GridView   pro_lv = (GridView) findViewById(R.id.gridView1);    
     	   
     	   
     	    
     	    int textLength=searchString.length();
     	 
      	   product_list.clear();
     	   
      	   
      	   //clear the initial data set
     	    for(int i=0;i< product_list1.size();i++)
     	    {
     	   String playerName=product_list1.get(i).getname().toString();
       	   if(textLength<=playerName.length()){
     	     if(searchString.equalsIgnoreCase(playerName.substring(0,textLength)))
     	    	product_list.add(product_list1.get(i));
     	   
       	   }
       	   
       	   
     	    }
     	   pro_grid_adapter pro_adap = new pro_grid_adapter( context,R.layout.grid_product_view,product_list,1);
     		
	      pro_lv.setAdapter(pro_adap);
     	 
     	  }
     	  
     	  public void beforeTextChanged(CharSequence s, int start, int count,
     	      int after) {
     	    }
     	  
     	    public void afterTextChanged(Editable s) {
     	  
     	  
     	    }
     	   });
     
   
    
     
     /// used to connect printer
	       
     Button billnumber = (Button) findViewById(R.id.sir_billnumber) ;
	 
     billnumber.setOnClickListener(
	                           new Button.OnClickListener() {
	                               @Override
	                               public void onClick(View v) {
	                            	   
	                            	  
	           						try {
	           								
	           						
	           							findBT();
	           							openBT();	
	           							bluetoothtester =1;
	           					//	 a();
	           						sendData();
	                            	
										
									
	           						}
	           						catch(Exception e)
	           						{
	           							
	           						}
   
	                               }
	                           });
   	 
   	 
   	 
   	 
        try
	       {
        	
        	///this submit button
        	
        	Button bt_billinpage = (Button) findViewById(R.id.sir_bp_bt_submit) ;
        	 
        	 bt_billinpage.setOnClickListener(
        	                           new Button.OnClickListener() {
        	                               @Override
        	                               public void onClick(View v) {
        	                            	   
        	                            	//   Log.i("date", getDateTime().toString());
        	                            	   
        	                     // offline checkbox is clicked then       	   
        	                            	   
        	                            	  CheckBox  offline = (CheckBox )findViewById(R.id.sir_offine) ;
        	                            	  
        	                            	 if(offline.isChecked()){
        	                            		 checkstatus =1;
        	                            	 }
        	                            	 else
        	                            	 {
        	                            		 checkstatus =0;
        	                            	 }
        	                            	  
        	                           //   showcustomdialoog function will help to calculate amount and balance.... 	  
        	                           
        	                            	   showCustomDialog();
        	                            	//   bluetoothstatus=0;
        	                            	   
        	                             	                               }});
        	 

	   
        	try
        	{
        	 
        	 
        	 
        	 context = this;
	        
	  // it is calling local database then bill page opening 
        	 
        	 
        	 db_pro = new Invoice_database(this,1);
	  
        	List<String> cat_Arr = new ArrayList<String>();
	        product_list.clear();
	        product_list1.clear();
	        		
	        		categorylist_array=db_pro.pro_selectAll();
	        		
	        		Log.i("", ""+db_pro.pro_selectAll().toString());
	        		 
	        		// insterting itemes  in product list to display product listview
	        		
	        		for (String[] details :categorylist_array ) {
	        	
	        			cat_Arr.add(details[1].toString());
	        			
	        			prorowteam a = new prorowteam("http://192.168.1.10/siragu_invoice/spr%20logo.png", details[2].toString(), Integer.parseInt(details[3].toString()) , Integer.parseInt(details[3].toString())*1, 1);
	        			product_list.add(a);
	
	        			product_list1.add(a);
	        		 
	        		 }
	        

	        		
	        		
	        		//catagory list is done by wiki.... check out with him
	        		
	        
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
	      
	      	cat_rowtem item = new cat_rowtem(""+cat_name.size(),"ALL");
		      
	        category_list.add(item);
	        LinearLayout ly=(LinearLayout)findViewById(R.id.layout_catagory_btn1);
	        LinearLayout ly2=(LinearLayout)findViewById(R.id.layout_catagory_btn2);

	        
	        
	        for(int i=0;i<category_list.size();i++)
	        {

	        	
	        	
	        Button btnTag = new Button(this);
	 
	    //    btnTag.setTextColor(R.drawable.button_text_color);
	        
	        
	        btnTag.setText(category_list.get(i).getname());
	 
	        btnTag.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	        LayoutParams.WRAP_CONTENT));
	        if( i%2 == 0)
	        {
	        ly.addView(btnTag);
	        }
	        else {
	        ly2.addView(btnTag);
	        }
	        btnTag.setOnClickListener(new OnClickListener() {

	        	
	        public void onClick(View v) {

	        Button b=(Button)v;
	        String btn_text=b.getText().toString();
	        // TODO Auto-generated method stub
	     
	        TextView as = (TextView) findViewById(R.id.sir_gv_cat_header);
	        
	       as.setText(""+btn_text.toString());
	        
	        ((sir.invoice.bill.BillPage) context).prodisplay(btn_text.toString());
	        

	        
	        
	   
	        }
	        });


	        }

	        //used it gridview for display item in gridview 
	        
	        
			 GridView   pro_lv = (GridView) findViewById(R.id.gridView1);
		        
			   pro_grid_adapter pro_adap = new pro_grid_adapter( context,R.layout.grid_product_view,product_list,1);
		     	
		       pro_lv.setAdapter(pro_adap);
		
	       }
	       catch(Exception e)
	       {
	    	   Log.i("cat", e.toString());
	       }
    
	       }
        catch(Exception e)
	       {
	    	   Log.i("cat", e.toString());
	       }
 
    
    }
  
  
  
  protected void buletoothopener() throws IOException {
	// TODO Auto-generated method stub
		findBT();
		openBT();		
			
		
	  
	  
	  
}
  
  public void startbilling()
  {
	  
	  
	  /// it internet is present or not 
       if (isInternetPresent) {
     try
	   {
    	 
    	 
    	 // blutoothtester is used 2 say the system print cart item  1) will print testinging 
    	 
		   bluetoothtester=2;  
			sendData();
			
		//	searchBox.setFocusable(false);
			
  new getdetail().execute();

  
 
	   
	   }
	   catch(Exception e)
	   {
		   Log.i("hello", e.toString());
	   }
	   }
  else
  {
	  
	  
	  //if intrnet is not available we will get 2 print and data will be stored in local database
	  
	   AlertDialog.Builder builder = new AlertDialog.Builder(BillPage.this);
  	builder
  	.setTitle("No Internet")
  	.setMessage("No internet Connection Available.Do you want to try again? ")
  	.setIcon(android.R.drawable.ic_dialog_alert)
  	.setPositiveButton("Only Printout ", new DialogInterface.OnClickListener() {
  	    public void onClick(DialogInterface dialog, int which) {			      	
  	    	//Yes button clicked, do something
  	     bluetoothtester=2;  
			
			try {
				sendData();
				sendData();
				 try
					{
 	         Invoice_database db_pro = new Invoice_database(getBaseContext(),2);  
 	          
 	         
 	        
 	        	 try
 	        	 {
 	        		
 	        		 String a_date = getDateTime().toString();
 	        		 
 	        		 
 	        		for(int i=0 ; i< a.GetCart().size();i++)
 	       		{
 	        			
 	        			
 	        			
    			db_pro.bill_insert(""+db_pro.bill_selectAll().size()+1, ""+a.GetCart().get(i).getname(), ""+a.GetCart().get(i).getprice(), ""+a.GetCart().get(i).getquty(), ""+a.GetCart().get(i).getTotal_Price(),""+a_date );
 	       	
 	        		//	db_pro.bill_insert(bill_id, name, price, qty, total_amount)
 	       		}
 	        		 
 	        		 
 	     //    Toast.makeText(getApplicationContext(),"Please Select the date"+db_pro.bill_selectAll().size(),Toast.LENGTH_LONG).show();	
	
 	        	 }
 	         catch(Exception e1)
 	         {
 	        	  Toast.makeText(getApplicationContext(),""+e1.toString(),Toast.LENGTH_LONG).show();	
					
 	        	  
 	        	  
 	        	  
 	         }
					}
				catch(SQLException e)
				{
					   Toast.makeText(getApplicationContext(),""+e.toString(),Toast.LENGTH_LONG).show();	
							
				}

				
				
				
				
				
				dialog1.cancel();
			//	searchBox.setFocusable(false);
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  	    	
			// cart will be clear cart list will be updated
			
			  a.GetCart().clear();
			  
			  
			  
			  ListView cart_lv1  =(ListView) findViewById(R.id.sir_cart_lv_bp);;
				 productlistviewadapter cart_adap = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,a.GetCart(),0);
			    cart_lv1.setAdapter(cart_adap);
				billdisplay();
			  
  	      	Toast.makeText(BillPage.this, "Data will not be stored in online.......", 
                               Toast.LENGTH_SHORT).show();
  	         //         Toast.LENGTH_SHORT).show();
  	    }
  	})
  	
  	
  	
  	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
  	    public void onClick(DialogInterface dialog, int which) {			      	
  	  
  	 //   	 new getdetail().execute();
  	    
  	    }
  	})						//Do nothing on no
  	.show();
  }
  

  }
  private String getDateTime() {
      SimpleDateFormat dateFormat = new SimpleDateFormat(
              "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
      Date date = new Date();
      return dateFormat.format(date);
}  
 /* public void asd()
	 {
	   AlertDialog.Builder builder = new AlertDialog.Builder(BillPage.this);
	  	builder
	  	.setTitle("Printer Error")
	  	.setMessage("Printer device not Found ")
	  	.setIcon(android.R.drawable.ic_dialog_alert)
	  	.setPositiveButton("Try Cancel", new DialogInterface.OnClickListener() {
	  	    public void onClick(DialogInterface dialog, int which) {	
	  	    	
	  	    	
	  	    	
	  	     }
	  	})
	  	
	  	
	  	
	  	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
	  	    public void onClick(DialogInterface dialog, int which) {			      	
	  	   
	  	    //	 endloop;
	  	    	 whilechecker =1;
	  	    
	  	    }
	  	})						//Do nothing on no
	  	.show();
	  	

	 }
  */

 // a- it used to bring bill number
  public void a()
  {
	try 
	{
  JSONParser json=new JSONParser();

  
	JSONParser jsonParser = new JSONParser();
	
	 
	JSONArray product=null;
	JSONObject jsons;
	 List<NameValuePair> params = new ArrayList<NameValuePair>();
  // GETTING VALUES FROM PHP //
 jsons = jsonParser.makeHttpRequest("http://192.168.1.10/siragu_invoice/billnumber.php","GET", params);
	 

 
 Log.d("show me1:" ,jsons.toString());

	 product = jsons.getJSONArray("products");

	int product_length=product.length();
  
	TextView  bn = (TextView) findViewById(R.id.sir_tv_billnumber);

  for (int i = 0; i <= product_length; i++) 
       {
       	 JSONObject c = product.getJSONObject(i);
       
 			  
       
 		Billno =	(c.getInt("MAX( bill_ID )")+1);
      
 		
 		 bn.setText(""+Billno);
 		
       }
	 
	 
	 } catch (JSONException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
  
  } 
  
  
  
  //this function used for seraching item in list
  
  public void prodisplay(String a)
  {
	  
	  
	  GridView   pro_lv = (GridView) findViewById(R.id.gridView1);        
		//   List<prorowteam> product_list = new ArrayList<prorowteam>();
   		
		 product_list.clear();
		 product_list1.clear();
		 categorylist_array=db_pro.pro_selectAll();
   		 
   		for (String[] details :categorylist_array ) {
   	
   		//	cat_Arr.add(details[1].toString());
   			if(details[1].toString().equals(a)   )
   			{
   			prorowteam dis = new prorowteam("http://192.168.1.10/siragu_invoice/spr%20logo.png", details[2].toString(), Integer.parseInt(details[3].toString()) , Integer.parseInt(details[3].toString())*1, 1);
   			product_list.add(dis);
   			product_list1.add(dis);

   			}else if(a.equals("ALL"))
   			{
   				prorowteam dis = new prorowteam("http://192.168.1.10/siragu_invoice/spr%20logo.png", details[2].toString(), Integer.parseInt(details[3].toString()) , Integer.parseInt(details[3].toString())*1, 1);
   	   			product_list.add(dis);
   	   			
   	   		product_list1.add(dis);
   			}
   		 
   		 }

		 
		 
   	   pro_grid_adapter pro_adap = new pro_grid_adapter( context,R.layout.grid_product_view,product_list,1);
    	
	       pro_lv.setAdapter(pro_adap);
  }
  
  
  
  // this function used for display bill values and grand total////
    
    public void billdisplay()
    {   double b = g.getGradeTotal()*1/100;
   	
   	TextView st = (TextView) findViewById(R.id.sir_bp_tv_st);
   	   
   	TextView vat = (TextView) findViewById(R.id.sir_bp_vat_tv);
   	
   	TextView tax = (TextView) findViewById(R.id.sir_bp_tv_tax);
   	   
   	TextView total = (TextView) findViewById(R.id.Sir_b_tv_tm);
   	
   	st.setText("₹"+g.getGradeTotal());
       vat.setText("₹"+b);
   	
   	tax.setText("₹"+b);
   // NumberFormat formatter = NumberFormat.getCurrencyInstance();
	   String moneyString = df.format((g.getGradeTotal()+b+b));
	
   	
   	total.setText("₹"+moneyString);	   
	   

    }
  
    
    
// for display update of cart values    
    public void displaycart()
   {		       
    	try
    	{
    		
    	//	 Toast.makeText(context, ""+a.GetCart().toString(), Toast.LENGTH_SHORT).show();
    		 
    	 ListView cart_lv1  =(ListView) findViewById(R.id.sir_cart_lv_bp);;	 
        productlistviewadapter cart_adap = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,a.GetCart(),0);
       
		cart_lv1 .setAdapter(cart_adap);
   }
    	catch(Exception e)
    	{
    		Log.i("as", e.toString());
    	}
    	}
    
    /*
     * when internet is present this class will be called for  storting bill number and item  in sever  
     * 
     * 
     */
    
    
    private class getdetail extends AsyncTask<Void, Void,Void>
    {

    @Override
    protected void onPreExecute()
    {
    super.onPreExecute();
   pDialog = new ProgressDialog(BillPage.this);
    pDialog.setMessage("Loading.....");
    pDialog.setCancelable(false);
   pDialog.show();
   // Log.d("fun","Inside");

    
    }
    protected Void doInBackground(Void... arg0) {
    // TODO Auto-generated method stub
 	   
 	 

    	JSONArray name = new JSONArray();
		JSONArray price = new JSONArray();
		JSONArray qty = new JSONArray();
		JSONArray total = new JSONArray();
	JSONArray value1 = new JSONArray();
	

		
		// chage it String pho = ((TextView) findViewById(R.id.textView12)).getText().toString();
	//	 String f= as.GetCart().toString();
		//grandt
	GrandTotal g = new GrandTotal();
		try {
				 for (int i = 0; i < a.GetCart().size() ; i++) {
				  name.put(a.GetCart().get(i).getname()); 
				  price.put(a.GetCart().get(i).getprice());
				  qty.put(a.GetCart().get(i).getquty());
				  total.put(a.GetCart().get(i).getTotal_Price());
				  value1.put(Billno);
				 /* Log.d("name",""+name);
				  Log.d("price",""+price);
				  Log.d("qty",""+qty);
				  Log.d("total",""+total);*/
				 }
				
			 }
			 catch (Exception e) {
				 Log.d("name",""+e.toString());
			}
		//	  Log.d("name",""+name);
			//  Log.d("price",""+price);
			//  Log.d("qty",""+qty);
		//	  Log.d("total",""+total);
			//  Log.d("phone",value1);
			//String  value1="1";
   paymenttext = (RadioGroup)findViewById(R.id.radioSex)	;
	
   int id = paymenttext.getCheckedRadioButtonId();
   
   
   RadioButton  paymenttext1 = (RadioButton) findViewById(id);
   
   double  amount = g.getGradeTotal();
   
   double totalamount = amount + (amount*1/100) + (amount*1/100);
	 bill_.add(new BasicNameValuePair("amount",""+totalamount));
		
	 bill_.add(new BasicNameValuePair("paymenttype",""+paymenttext1.getText().toString()));

	 bill_.add(new BasicNameValuePair("totalitem",""+a.GetCart().size()));

			 postvar.add(new BasicNameValuePair("pro_name",name.toString().trim()));
			 postvar.add(new BasicNameValuePair("price",price.toString().trim()));
			 postvar.add(new BasicNameValuePair("qty",qty.toString().trim()));
			 postvar.add(new BasicNameValuePair("total",total.toString().trim()));
		 postvar.add(new BasicNameValuePair("bill_id",value1.toString().trim()));
          
 	   
         
 	   
   
    return null;

    }

	
    
    
    
    @Override
    protected void onPostExecute(Void result)
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
					TextView  bn = (TextView) findViewById(R.id.sir_tv_billnumber);
                  	
					++Billno;
					bn.setText(""+Billno);
					a.GetCart().clear();
					 ListView cart_lv1  =(ListView) findViewById(R.id.sir_cart_lv_bp);;
					 productlistviewadapter cart_adap = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,a.GetCart(),0);
				       
						cart_lv1.setAdapter(cart_adap);
						billdisplay();
			//		startActivity(new Intent(Bill.this, MainActivity.class));
						
						
						
				
				Toast.makeText(getApplicationContext(), "Thank You For Purchasing From Us", Toast.LENGTH_SHORT).show();
			
				pDialog.cancel();
				
				
				 dialog1.cancel();

				  searchBox.clearFocus();
				
				}
				else
				{
				Toast.makeText(getApplicationContext(), "Connection Failed Try Again", Toast.LENGTH_SHORT).show();
				}
				} catch (JSONException e) {
				// TODO Auto-generated catch block
				Toast.makeText(getApplicationContext(), "Connection Failed Try Again", Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				}

			
			
			
			
		}
		catch(Exception e)
		{
			Log.d("sonss",""+e.toString());	
		}
			
		        // Pulling items from the array
		       
		       // String oneObjectsItem2 = oneObject.getString("timestamp");
    	 Log.d("Jsonlist",""+bill_);
			 Log.d("Jsonlist",""+postvar);
    super.onPostExecute(result);
    

    }
    }
    void findBT() {

		try {
			mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

			if (mBluetoothAdapter == null) {
			//	myLabel.setText("No bluetooth adapter available");
			}

			if (!mBluetoothAdapter.isEnabled()) {
				Intent enableBluetooth = new Intent(
						BluetoothAdapter.ACTION_REQUEST_ENABLE);
				startActivityForResult(enableBluetooth, 0);
			}

			Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
					.getBondedDevices();
			if (pairedDevices.size() > 0) {
				for (BluetoothDevice device : pairedDevices) {
					
					// MP300 is the name of the bluetooth printer device
					if (device.getName().equals("QSprinter")) {
						mmDevice = device;
asd =1;
						break;
					}
				}
			}
		
			if(asd==1)
			{
		//	Toast.makeText(getApplicationContext(),"Bluetooth Device Found",Toast.LENGTH_LONG).show();
			asd=0;
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Please pair Devices",Toast.LENGTH_LONG).show();
			
			}
		
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Tries to open a connection to the bluetooth printer device
	 */
	void openBT() throws IOException {
		try {
			// Standard SerialPortService ID
			UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
			mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
			mmSocket.connect();
			mmOutputStream = mmSocket.getOutputStream();
			mmInputStream = mmSocket.getInputStream();

			beginListenForData();

			
			
			
			 Toast.makeText(getApplicationContext(),"Bluetooth Opened",Toast.LENGTH_LONG).show();
			 bluetoothstatus = 1;
			
		//	myLabel.setText("Bluetooth Opened");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * After opening a connection to bluetooth printer device, 
	 * we have to listen and check if a data were sent to be printed.
	 */
	void beginListenForData() {
		try {
			final Handler handler = new Handler();
			
			// This is the ASCII code for a newline character
			final byte delimiter = 10;

			stopWorker = false;
			readBufferPosition = 0;
			readBuffer = new byte[1024];
			
			workerThread = new Thread(new Runnable() {
				public void run() {
					while (!Thread.currentThread().isInterrupted()
							&& !stopWorker) {
						
						try {
							
							int bytesAvailable = mmInputStream.available();
							if (bytesAvailable > 0) {
								byte[] packetBytes = new byte[bytesAvailable];
								mmInputStream.read(packetBytes);
								for (int i = 0; i < bytesAvailable; i++) {
									byte b = packetBytes[i];
									if (b == delimiter) {
										byte[] encodedBytes = new byte[readBufferPosition];
										System.arraycopy(readBuffer, 0,
												encodedBytes, 0,
												encodedBytes.length);
										final String data = new String(
												encodedBytes, "US-ASCII");
										readBufferPosition = 0;

										handler.post(new Runnable() {
											public void run() {

}
										});
									} else {
										readBuffer[readBufferPosition++] = b;
									}
								}
							}
							
						} catch (IOException ex) {
							stopWorker = true;
						}
						
					}
				}
			});

			workerThread.start();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * This will send data to be printed by the bluetooth printer
	 */
	void sendData() throws IOException {
		try {
		//	Date now = new Date();
		if(bluetoothtester==1)
		{
			String tester ="device is connect \n \n \n";
	
			bluetoothchecker = 1;
			
			try
			{
			mmOutputStream.write(tester.getBytes());
			}
			catch (NullPointerException e) {
				
				Log.i("asdasd", e.toString());
				e.printStackTrace();
			} catch (Exception e) {
				Log.i("er", e.toString());
				
				
				e.printStackTrace();
			}
			
		}
		else if(bluetoothtester==2)
		{
	long now = System.currentTimeMillis();
	String date_time = new SimpleDateFormat("dd:MM:yyyy HH:mm").format(now);
	
	cart c = new cart();
	
	List<prorowteam> CartList = new ArrayList<prorowteam>();
	
	
	CartList = c.GetCart();
	double totalamount = (g.getGradeTotal()*1/100)+(g.getGradeTotal()*1/100)+g.getGradeTotal();
		
	
	String	print = "--------------------------------";
			print += "\n";
			mmOutputStream.write(print.getBytes());
			byte[] arrayOfByte1 = { 27, 33, 0 };
		
	try
	{
			String header = " Student Park Restaurant A/c  ";
	byte[] format = { 27, 33, 0 };

//	format[2] = ((byte)(0x8 | arrayOfByte1[2]));
	format[2] = ((byte)(0x8 | arrayOfByte1[2]));
	
	  format[2] = ((byte)(0x80 | arrayOfByte1[2]));
//	mmOutputStream.write(format);
	mmOutputStream.write(header.getBytes());

	}
	
	catch(Exception  e)

	{
	Log.i("asd", e.toString());
	}
	print = "\n";      
			print += "--------------------------------";
			print += "\n";
			print += "   No.39,Bharathiyar Street";
			print += "\n";
			print +="   Veerapuram,Mahindra City";
			print += "\n";
			print += "   Ch-603002";
			print += "\n";
			print += "Bill no :"+Billno ;
			print += "\n";
			print += "Date&Time : "+date_time;
			print += "\n";
			print += "--------------------------------";
			print += "\n";
			print += "Item name  Quty  Price    Amount";
			print += "\n";
			print += "--------------------------------";
		
			mmOutputStream.write(print.getBytes());
		
			String cart = "";
		
		for(int i=0 ; i< CartList.size();i++)
		{
			cart += ""+CartList.get(i).getname().toString()+"\n";
			cart += "            "+CartList.get(i).getquty()+"     "+CartList.get(i).getprice()+"    "+CartList.get(i).getTotal_Price()+"\n";
		}
		cart += "--------------------------------\n";
		cart += "       Total Item  :"+c.GetCart().size()+"\n";
		cart += "       Tax @ 1     :"+(g.getGradeTotal()*1/100)+"\n";
		cart += "       Vat @ 1     :"+(g.getGradeTotal()*1/100)+"\n";
		cart += "--------------------------------\n";
		cart += "TOTAL               RS."+totalamount+"\n";
		cart += "--------------------------------\n";
		cart += "        THANK YOU    \n";
		cart += "       VISIT AGAIN ";
		cart += "\n";
		cart += "\n";
		cart += "\n";
		cart += "\n";
		mmOutputStream.write(cart.getBytes());
	//	mmOutputStream.write(print.getBytes());
		}
		} catch (NullPointerException e) {
		
			Log.i("asdasd", e.toString());
			e.printStackTrace();
		} catch (Exception e) {
			Log.i("er", e.toString());
			
			
			e.printStackTrace();
		}
	
	}

	/*
	 * Close the connection to bluetooth printer.
 	 */
	void closeBT() throws IOException {
		try {
			stopWorker = true;
			mmOutputStream.close();
			mmInputStream.close();
			mmSocket.close();
		//	myLabel.setText("Bluetooth Closed");
		} catch (NullPointerException e) {
	
			
			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * function will called then user click sumbit button
	 * open customdialog box
	 */
	
	
	 protected void showCustomDialog() {
		      dialog1 = new Dialog(this.context);
	        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog1.setContentView(R.layout.bill_balance);
	       
	    	
	        final EditText  cash = (EditText) dialog1.findViewById(R.id.sir_ed_bill_balance_cash);
        	
	        final TextView  bal = (TextView) dialog1.findViewById(R.id.sir_bill_balance_amount);
        	
	        final double totalamount = (g.getGradeTotal()*1/100)+(g.getGradeTotal()*1/100)+g.getGradeTotal();
	    	
	        cash.addTextChangedListener(new TextWatcher() {
	       	 
	       	 
	       	  
	       	  public void onTextChanged(CharSequence s, int start, int before, int count) {
	       	    //get the text in the EditText
	       		  try
	       		  {
	       	    double balance_amount = (Integer.parseInt(""+cash.getText())-totalamount);
	       		
	       	    if( balance_amount > 0 && balance_amount < 1 )
	       	    {
	       		  bal.setText("₹0"+df.format(balance_amount));
	       	    }
	       	    else
	       	    {
	       	    	bal.setText("₹"+df.format(balance_amount));
	       	    }
	       		  }
	       		  catch(Exception e)
	       		  {
	       			bal.setText("₹0.00");
	       		  }
	       	  }
	       	  
	       	  public void beforeTextChanged(CharSequence s, int start, int count,
	       	      int after) {
	       	    }
	       	  
	       	    public void afterTextChanged(Editable s) {
	       	  
	       	  
	       	    }
	       	   });
			
	       /*
	        * 
	        *try to print bill 
	        * 
	        */
	        
	        
	        
	         
	        Button button = (Button)  dialog1.findViewById(R.id.sir_bill_balancel_bt_ok);  
	        button.setOnClickListener(new View.OnClickListener() {
	            
	            @Override
	            public void onClick(View v) {
	            	
	            	
	            	
	            	 if(checkstatus == 1)
               	  {
	            		 bluetoothtester=2;  
	         			
	         			try {
	         				sendData();
	         				sendData();
	         				 try
	         					{
	          	         Invoice_database db_pro = new Invoice_database(getBaseContext(),2);  
	          	          
	          	         
	          	        
	          	        	 try
	          	        	 {
	          	        		 
	          	        	//	 java.util.Date date= new java.util.Date();
	          	        		 
	          	        		for(int i=0 ; i< a.GetCart().size();i++)
	          	       		{
	          	        			db_pro.bill_insert(""+db_pro.bill_selectAll().size()+1, ""+a.GetCart().get(i).getname(), ""+a.GetCart().get(i).getprice(), ""+a.GetCart().get(i).getquty(), ""+a.GetCart().get(i).getTotal_Price(),getDateTime().toString());
	          	       	
	          	        		//	db_pro.bill_insert(bill_id, name, price, qty, total_amount)
	          	       		}
	          	        		 
	          	        		 
	          	     //    Toast.makeText(getApplicationContext(),"Please Select the date"+db_pro.bill_selectAll().size(),Toast.LENGTH_LONG).show();	
	          	        		
	          	        		
	         	
	          	        	 }
	          	         catch(Exception e1)
	          	         {
	          	        	  Toast.makeText(getApplicationContext(),""+e1.toString(),Toast.LENGTH_LONG).show();	
	         					
	          	        	  
	          	        	  
	          	        	  
	          	         }
	         					}
	         				catch(SQLException e)
	         				{
	         					   Toast.makeText(getApplicationContext(),""+e.toString(),Toast.LENGTH_LONG).show();	
	         							
	         				}

	         				
	         				
	         				
	         				
	         				
	         				dialog1.cancel();
	         			//	searchBox.setFocusable(false);
	         			
	         			} catch (IOException e) {
	         				// TODO Auto-generated catch block
	         				e.printStackTrace();
	         			}
	           	    	
	         			  a.GetCart().clear();
	         			  
	         			  ListView cart_lv1  =(ListView) findViewById(R.id.sir_cart_lv_bp);;
	         				 productlistviewadapter cart_adap = new productlistviewadapter( getBaseContext(),R.layout.sir_pro_listview,a.GetCart(),0);
	         			    cart_lv1.setAdapter(cart_adap);
	         				billdisplay();
	         			  
	           	      	Toast.makeText(BillPage.this, "Data will not be stored in online.......", 
	                                        Toast.LENGTH_SHORT).show();
               	  
  		
               	  }
	            	 else
	            	 {
	            	
				
					if(bluetoothstatus==1)
					{
				    
            	     startbilling();
            	     cash.clearFocus();
            //	 .cancel();
            	   
            	   
					}
					else
					{
						 AlertDialog.Builder builder = new AlertDialog.Builder(BillPage.this);
						  	builder
						  	.setTitle("Printer Error")
						  	.setMessage("Printer device not Found ")
						  	.setIcon(android.R.drawable.ic_dialog_alert)
						  	.setPositiveButton("Try Cancel", new DialogInterface.OnClickListener() {
						  	    public void onClick(DialogInterface dialog, int which) {	
						  	    	

									try {
										buletoothopener();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
						  	    	
						  	     }
						  	})
						  	
						  	
						  	
						  	.setNegativeButton("Cancel",  new DialogInterface.OnClickListener() {
						  	    public void onClick(DialogInterface dialog, int which) {			      	
						  		
						  	 //     startbilling();
				            	//     cash.clearFocus();
				       				  	    	
						  	    	
						  	    	whilechecker =1;
						  	  
						  	    	
						  	    	
						  	    }
						  	})						//Do nothing on no
						  	.show();
						  	
					}
	            	 }
	              }
	            
	        });
	        Button cancel = (Button)dialog1.findViewById(R.id.sir_bill_balnce_bt_cancel);  
	        
	        
	        cancel.setOnClickListener(new View.OnClickListener() {
	            
	            @Override
	            public void onClick(View v) {
	            	dialog1.cancel();
	            	
	                  }
	            
	        });
	        
	        
	        
	                
	        dialog1.show();
			
		}
	 
	 /*
	  * (non-Javadoc)
	 
	  * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	  * 
	  * it is used send user to back page without problem
	  * 
	  */
	 
	 
	 
	 
	 
	 
	 
	 
	 
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
    
    
}
