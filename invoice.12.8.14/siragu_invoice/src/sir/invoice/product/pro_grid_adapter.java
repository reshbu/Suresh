package sir.invoice.product;

import static com.nativecss.enums.RemoteContentRefreshPeriod.Never;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.nativecss.NativeCSS;

import sir.invoice.R;
import sir.invoice.bill.BillPage;
import sir.invoice.cart.cart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

public class pro_grid_adapter extends ArrayAdapter<prorowteam> {
 
	Context context;
    ViewHolder holder = null;
    List<prorowteam> items;
    ArrayList<String> list = new ArrayList<String>();
    int postion1;
    static BillPage b;
    
   
	  public static cart a = new cart();
    View convertView1; ViewGroup parent1;
    int j ;
  
   double Totalprice = 0;
    int resourceId;
   int i=0;
	 List<prorowteam> Cart = new ArrayList<prorowteam>();;
	     public pro_grid_adapter(Context context,int resourceId,List<prorowteam> items, int j) 
    {
	    super(context, resourceId, items);
        this.context = context;
        this.items=items;
        this.resourceId=resourceId;
        this.j=j;
       
       }
	   /*private view holder class*/
    private class ViewHolder {

    	
	     //   TextView txtTitle;
	        TextView txtPrice;
	      //  TextView  txtQty,txttotalprice,qty;
	        Button buttonadd ;
			
        }
 
    @SuppressLint("NewApi") public View getView(final int position, View convertView, ViewGroup parent) 
    {
    	convertView1=convertView;
       convertView = convertView1;parent1 =parent;
    	try
    	{
    		
    		
    		
    		
    		final prorowteam rowItem = getItem(position);
       LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) 
        {
        

    		
                convertView = mInflater.inflate(R.layout.grid_product_view, null);
            holder = new ViewHolder();
  		    holder.txtPrice = (TextView) convertView.findViewById(R.id.pro_grid_view);
			 holder.buttonadd = (Button) convertView.findViewById(R.id.gv_button);
	               convertView.setTag(holder);
              
        }
        else
       	
        holder = (ViewHolder) convertView.getTag();
        
        	  holder.txtPrice.setText("₹"+rowItem.getprice()+"  ");
          	
        	 holder.buttonadd.setText(""+rowItem.getname());
       
        holder.buttonadd.setOnClickListener(new View.OnClickListener(){
	    	//int i=1;
    	  public void onClick(View arg0) {
    		  
    		  
    		 a.a(rowItem.getImageId(), rowItem.getname(), rowItem.getprice(), rowItem.getTotal_Price(), rowItem.getquty()); 
    		  
    		 try
    		 {
    		 
    		 ((sir.invoice.bill.BillPage) context).displaycart();
    		 
    		 ((sir.invoice.bill.BillPage) getContext()).billdisplay();
    		 
    		 }
    		 catch(Exception e)
    		 {
    			 Log.i("baby", e.toString())
    			 ;
    			 Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
        		 
    		 }
    	  }
    		  
    			
    	 
    	    });
    	}
    	catch(Exception e)
    	{
    	 Log.i("ERrror",e.toString() );
    	}
              
      
       
        return convertView;
    }

	protected Context getBaseContext() {
		// TODO Auto-generated method stub
		return this.context;
	}	
    
}

    

