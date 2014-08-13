package sir.invoice.database;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
@SuppressLint("Instantiatable")
public class Invoice_database
{
 	private static final  String DATABASE_NAME = "siragu_invoice.db";
    private static final int DATABASE_VERSION = 2;
    static final String TABLE_NAME = "sir_bill";
    
    static final String TABLE_NAME_product = "sir_product";
   
    static final String table_customer_info = "user_info";
    
    private static Context context;
    static SQLiteDatabase db;
  //  private SQLiteStatement insertStmt;
    private SQLiteStatement pro_insertStmt;          //   bill_id TEXT,name TEXT, price TEXT ,qty text ,total_amount text 
    private static final String INSERT = "insert into " + TABLE_NAME + " (bill_id,name,price,qty,total_amount,bill_date) values (?,?,?,?,?,?)";
    private static final String pro_INSERT = "insert into " + TABLE_NAME_product + " (id,catagory_name,name,photo,price,qutytype,stockinhand) values (?,?,?,?,?,?,?)";
    
    public Invoice_database(Context context,int i) {
    	Invoice_database.context = context;
    OpenHelper openHelper = new OpenHelper(Invoice_database.context);
    Invoice_database.db = openHelper.getWritableDatabase();
 //   this.insertStmt = Invoice_database_catagory.db.compileStatement(INSERT);
    try
    {
    if(i==1)
    {
    	
    this.pro_insertStmt = Invoice_database.db.compileStatement(pro_INSERT);
    }
    
    else if(i==2)
    	{
    	  this.pro_insertStmt = Invoice_database.db.compileStatement(INSERT);
    	}
    	}
    catch(Exception e)
    {
    	Log.i("sql", e.toString());
    }
    }
                                          //bill_id TEXT,name TEXT, price TEXT ,qty text ,total_amount text 
    public long bill_insert(String bill_id,String name,String price,String qty,String total_amount,String bill_date) {
        this.pro_insertStmt.bindString(1, bill_id);
        this.pro_insertStmt.bindString(2, name);
        this.pro_insertStmt.bindString(3, price);
        this.pro_insertStmt.bindString(4, qty);
        this.pro_insertStmt.bindString(5, total_amount);
        this.pro_insertStmt.bindString(6, bill_date);
     
        return this.pro_insertStmt.executeInsert();
        }
    
    
    
    
    public long pro_insert(String id,String catagory_name,String name,String photo,String price,String qutytype,String stockinhand) {
        this.pro_insertStmt.bindString(1, id);
        this.pro_insertStmt.bindString(2, catagory_name);
        this.pro_insertStmt.bindString(3, name);
        this.pro_insertStmt.bindString(4, price);
        this.pro_insertStmt.bindString(5, photo);
        this.pro_insertStmt.bindString(6, qutytype);
        this.pro_insertStmt.bindString(7, stockinhand);
     
        return this.pro_insertStmt.executeInsert();
        }
    
    
    
  /*  public long insert(String id,String name,String photo) {
    this.insertStmt.bindString(1, id);
    this.insertStmt.bindString(2, name);
    this.insertStmt.bindString(3, photo);
    return this.insertStmt.executeInsert();
    }*/
    public void deleteAll() {
    db.delete(TABLE_NAME, null, null);
    }
    
    public void pro_deleteAll() {
        db.delete(TABLE_NAME_product, null, null);
        }
    
    public List<String[]> bill_selectAll()
    {
    List<String[]> list_pro = new ArrayList<String[]>();    //bill_id TEXT,name TEXT, price TEXT ,qty text ,total_amount text 
    Cursor cursor = db.query(TABLE_NAME, new String[] {"bill_id","name","price","qty" ,"total_amount","bill_date"}, null, null,null, null, null ); 
    int x=0;
    if (cursor.moveToFirst()) {
       do {
        String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)};
        list_pro.add(b1);
        x=x+1;
       } while (cursor.moveToNext());
    }
    if (cursor != null && !cursor.isClosed()) {
       cursor.close();
    } 
    cursor.close();
    return list_pro;
   }
    
 
    
    
    
    
    
    public List<String[]> pro_selectAll()
    {
    List<String[]> list_pro = new ArrayList<String[]>();  //name,address,gender,dob,phoneno,bloodgroup,tperson,tphoneno
    Cursor cursor = db.query(TABLE_NAME_product, new String[] {"id","catagory_name","name","photo" ,"price","qutytype","stockinhand"}, null, null,null, null, null); 
    int x=0;
    if (cursor.moveToFirst()) {
       do {
        String[] b1=new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)};
        list_pro.add(b1);
        x=x+1;
       } while (cursor.moveToNext());
    }
    if (cursor != null && !cursor.isClosed()) {
       cursor.close();
    } 
    cursor.close();
    return list_pro;
   }
    
    
    public void pro_delete(int rowId) {
        db.delete(TABLE_NAME_product, null, null); 
       }
    
    public void delete(int rowId) {
    db.delete(TABLE_NAME, null, null); 
   }
    private static class OpenHelper extends SQLiteOpenHelper {
    OpenHelper(Context context) {
         super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    	
    //	String Name, int price, double d,int qty
         db.execSQL("CREATE TABLE " + TABLE_NAME + " (bill_id TEXT,name TEXT, price TEXT ,qty text ,total_amount text,bill_date text )");
         
         db.execSQL("CREATE TABLE " + TABLE_NAME_product + " (id TEXT,catagory_name TEXT,name TEXT,photo TEXT,price TEXT,qutytype TEXT,stockinhand TEXT)");
   
    
    
    
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
         
         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_product);
         onCreate(db);
    }
   }
}