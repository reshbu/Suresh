package sir.invoice.category;

import java.util.ArrayList;
import java.util.List;
import sir.invoice.R;
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
 
public class CategoryListViewAdapter extends ArrayAdapter<cat_rowtem> {
 
	Context context;
    ViewHolder holder = null;
    List<cat_rowtem> items;
    ArrayList<String> list = new ArrayList<String>();
    int postion1;
    View convertView1; ViewGroup parent1;
  
   double Totalprice = 0;
    int resourceId;
   int i=0;
	 List<cat_rowtem> Cart = new ArrayList<cat_rowtem>();;
	     public CategoryListViewAdapter(Context context,int resourceId,List<cat_rowtem> items) 
    {
	    super(context, resourceId, items);
        this.context = context;
        this.items=items;
        this.resourceId=resourceId;
       }
	   /*private view holder class*/
    private class ViewHolder {

    	Button b1;
    	Button b2;
        }
 
    @SuppressLint("NewApi") public View getView(final int position, View convertView, ViewGroup parent) 
    {
    	convertView1=convertView;
       convertView = convertView1;parent1 =parent;
    	try
    	{
    		final cat_rowtem rowItem = getItem(position);
       LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) 
        {
        
                convertView = mInflater.inflate(R.layout.sir_cat_listview, null);
            holder = new ViewHolder();
            holder.b1 = (Button) convertView.findViewById(R.id.sir_clv_bn_catname);
          
                   convertView.setTag(holder);
                   
                  
          
                   
                   
        
        }
        else
        
        holder = (ViewHolder) convertView.getTag();
     holder.b1.setText(rowItem.getname());
     
     holder.b1.setOnClickListener(new View.OnClickListener(){
	    	//int i=1;
 	  public void onClick(View arg0) {
 	  
 		  
 		 ((sir.invoice.bill.BillPage) context).prodisplay(rowItem.getname());
 	  
 	  }
     });
     
   
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

    

