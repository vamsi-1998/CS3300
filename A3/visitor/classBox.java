
package visitor;
import syntaxtree.*;
import java.util.*;




public class classBox
{

  public HashMap <String,String> varList  ;
  public HashMap <String , funcBox> funcList ;
  public String className ;
  public  String parentName ;
  public  HashMap <String,Integer>clsVarOrd ;
  public  HashMap <Integer,String>funcOrdList ;
  public HashMap<String , Integer>funcRevList ;
  //HashMap <String , FunctionBox> fundec ;
    // classBox(String id )
    // {
    //    this.className = id ;
    //    varList = new HashMap<>();
    //    funcList = new HashMap<>();
    //    this.parentName = null ;
    // }
    classBox(String child , String parent )
    {
      this.className = child ;
      varList = new HashMap<>();
      funcList = new HashMap<>();
      clsVarOrd = new HashMap<>();
      funcOrdList = new HashMap<>() ;
      funcRevList = new HashMap<>() ;
      this.parentName = parent ;
    }
    public  void insertOrdclassVar(String id , int num )
    {
        this.clsVarOrd.put(id , num ) ;
    }
    public void insertclassVar(String id , String type )
    {
        // if( !class.containsKey(id)  )
         this.varList.put(id , type ) ;
    }
   public void insertFunc(String fname , String rtype )
   {
      funcBox fB = new funcBox(fname , rtype ) ;
      this.funcList.put(fname,fB) ;
   }
    /*
      public void printEle()
      {
        for (Map.Entry mapElement : vardec.entrySet())
        {
           String key = (String)mapElement.getKey();
           String  value = (String)mapElement.getValue();
           System.out.println("variable name is "+ key + "Type is " + value );
           System.out.print("size of Table till now : " + vardec.size() + '\n');

         }
      }*/
}
