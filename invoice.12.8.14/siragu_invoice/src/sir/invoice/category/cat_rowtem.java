package sir.invoice.category;

public class cat_rowtem {
    
	private String id;
    private String name;
   
    public cat_rowtem(String id, String name) {
        this.id = id;
        this.name = name;
         
        
        
        
        
    }
	
	public String getid() {
        return id;
    }
    public void setcat(String id) {
        this.id = id;
    }
    
    
    
     public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    
    
    @Override
   
    public String toString() {
        return  "{"+name+","+id+"}";
    }
    }