package sir.invoice.product;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import sir.invoice.R;
import sir.invoice.bill.BillPage;
import sir.invoice.cart.cart;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;


public class product_list extends ArrayAdapter<prorowteam> {
 
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
	     public product_list(Context context,int resourceId,List<prorowteam> items, int j) 
    {
	    super(context, resourceId, items);
        this.context = context;
        this.items=items;
        this.resourceId=resourceId;
        this.j=j;
       
       }
	   /*private view holder class*/
    private class ViewHolder {

    	 ImageView imageView;
	        TextView txtTitle;
	        TextView txtPrice,pro2;
	        TextView  txtQty,txttotalprice;
	        Button buttonPlus,buttonadd,buttonMinus;
			public ProgressBar pbar;
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
        
                convertView = mInflater.inflate(R.layout.sir_pro_listview, null);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.item_display_pdname);
			 holder.txtQty = (TextView) convertView.findViewById(R.id.item_display_ed_qty);
			 holder.txtPrice = (TextView) convertView.findViewById(R.id.sir_pro_priceamount);
			 holder.txttotalprice = (TextView) convertView.findViewById(R.id.sir_pro_totalamount);
			    holder.pro2 = (TextView) convertView.findViewById(R.id.sir_pr_lv_item_pro2);
					
		//	 holder.qty = (TextView) convertView.findViewById(R.id.item_display_qty);
				
			 
			// holder.imageView = (ImageView) convertView.findViewById(R.id.item_display_imageview);
			 holder.buttonMinus= (Button) convertView.findViewById(R.id.item_display_minus);
			 holder.buttonPlus= (Button) convertView.findViewById(R.id.item_display_plus);
			 holder.buttonadd = (Button) convertView.findViewById(R.id.item_display_add);
		//	 holder.pbar = (ProgressBar) convertView.findViewById(R.id.progressBar1);
          
                   convertView.setTag(holder);
              
        }
        else
        
        holder = (ViewHolder) convertView.getTag();
        
       
        holder.txtQty.setText(""+rowItem.getquty());
        holder.txttotalprice.setText("₹"+rowItem.getTotal_Price());
        
        
        if(j==0)
        {
        	holder.pro2.setVisibility(View.GONE);
        	
        	holder.txtTitle.setText(""+rowItem.getname());
             holder.txtPrice.setText("₹"+rowItem.getprice());
        	
        	 holder.buttonadd.setText("Delete Item");
        }
        else
        {
        	
        	
        	 holder.txtTitle.setVisibility(View.GONE);
            
        //	 holder.qty.setVisibility(View.GONE);
        	holder.buttonMinus.setVisibility(View.GONE);
        	holder.buttonPlus.setVisibility(View.GONE);
        	  holder.txttotalprice.setVisibility(View.GONE);
        	  holder.txtQty.setVisibility(View.GONE);
        	  holder.txtPrice.setText(" ₹"+rowItem.getprice());
          	
        		holder.pro2.setText(""+rowItem.getname());
                	
        	 holder.buttonadd.setText("Add item");
        }
       
        holder.buttonadd.setOnClickListener(new View.OnClickListener(){
	    	//int i=1;
    	  public void onClick(View arg0) {
    		  
    		  if(j==1)
    		  {
    		  
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
    		  else if(j==0)
        	  {
    			 
    			  try
    	    		 {
    				  items.remove(items.get(position)); 
    		 notifyDataSetChanged(); 
    		 ((sir.invoice.bill.BillPage) parent1.getContext() ).billdisplay();
    		 
    		 
    		
    		 
                     	   
                     
    	    		 }
    			  
    			  catch(Exception e)
    	    		 {
    	    			 Log.i("baby", e.toString())
    	    			 ;
    	    			 Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
    	    		//	 ((sir.invoice.bill.BillPage) convertView1.getContext() ).billdisplay();
    	    	    			 
    	    		 }
    			  
    			  
    			  }
    	  }
    	 
    	    });
        
        
        
        holder.buttonMinus.setOnClickListener(new View.OnClickListener(){
	    	//int i=1;
    	  public void onClick(View arg0) {
    		  int tempquty = rowItem.getquty();
    		 if(tempquty>1)
    		 {
    			 rowItem.setquty(tempquty-1);
    			 
    			 
    			 rowItem.setTotal_Price(rowItem.getquty()*rowItem.getprice());
    			 
    			 ((sir.invoice.bill.BillPage) parent1.getContext() ).billdisplay();
    		 }
    		 else
    		 {
    			 Toast.makeText(context, "Atleast min quty must be 1", Toast.LENGTH_SHORT).show();
    		 }
    		 
    		  
    		//  holder.txtQty.setText(""+rowItem.getquty());
    		  notifyDataSetChanged();
    		 // 

    	  }
    	    });

        
        
        
        
       
        holder.buttonPlus.setOnClickListener(new View.OnClickListener(){
	    	//int i=1;
    	  public void onClick(View arg0) {
    		  int tempquty = rowItem.getquty();
    		  rowItem.setquty(tempquty+1);
    		  
    	
    		  rowItem.setTotal_Price(rowItem.getquty()*rowItem.getprice());
    		  ((sir.invoice.bill.BillPage) parent1.getContext() ).billdisplay();
 			 notifyDataSetChanged();
    		

    	  }
    	    });
        
        
        
 //    holder.b1.setText(rowItem.getname());
   
    //	 holder.b2.setText(rowItem.getname2());
    	 
    
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

    

