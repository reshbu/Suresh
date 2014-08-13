package sir.invoice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import sir.invoice.bill.sir_report;
import sir.invoice.database.Invoice_database;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Login extends Activity{
	 private static int SPLASH_TIME_OUT = 3000;
	  JSONParser json=new JSONParser();
	  JSONParser jsonParser = new JSONParser();
	  Invoice_database db_pro;
	  public Context context;
	  private ProgressDialog pDialog;

	  protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	      WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      JSONParser json=new JSONParser();
	      setContentView(R.layout.sir_loginpage);
	      Button login_ok = (Button) findViewById(R.id.sir_lp_bt_ok) ;
	      login_ok.setOnClickListener(
	                                  new Button.OnClickListener() {
	                                      @Override
	                                      public void onClick(View v) {
	                          
	                                    	  new   userver().execute();	                                      
	                                      }
	                                      });
	        
	  }
	  private class userver extends AsyncTask<Void, Void,Void>
	    {
	    @Override
	    protected void onPreExecute()
	    {
	    super.onPreExecute();
	    pDialog = new ProgressDialog(Login.this);
		pDialog.setMessage("Please wait...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	   
	    }
	    protected Void doInBackground(Void... arg0) {
	    	try
	    	{
	    		EditText userid = (EditText) findViewById(R.id.sir_lp_ed_loginpage);
	    		EditText password = (EditText) findViewById(R.id.sir_ed_lp_password);
	    		if(userid.getText().toString().equals("")&& password.getText().toString().equals("") )
	    		{
	
	    		}
	    		else
	    		{
	    			JSONParser jsonParser = new JSONParser();
	 	//		JSONArray product=null;
	 	 		 JSONObject jsons = null;
	 	 		 
	 	 		 
	 			 List<NameValuePair> params = new ArrayList<NameValuePair>();
	 	 		params.add(new BasicNameValuePair("phoneno",""+userid.getText().toString().trim()));
	 	 		params.add(new BasicNameValuePair("password",""+password.getText().toString().trim()));
	
	 	 		
	 	 		jsons = jsonParser.makeHttpRequest("http://192.168.1.2/siragu_invoice/user_verification.php","GET", params);
	 		
		 	 	int code = jsons.getInt("getcode");
		 	 	int input_userid = jsons.getInt("userid");
		 	 	
		 	 	
		 	 	Log.i("input_userid", ""+input_userid);
		 	 	
	    		
		 	 	
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
	 	       try
	 	       {
	    	
	 	       }
	 	       catch(Exception e)
	 	       {
	 	    	   Log.i("ghj", e.toString());
	 	       }
	 			super.onPostExecute(result);
			
	    }
	    }
	
	

}
