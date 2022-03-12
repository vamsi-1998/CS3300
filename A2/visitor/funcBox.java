
package visitor;
import syntaxtree.*;
import java.util.*;

public class funcBox
{
  // array list has been chosen for parameters
  // as they follow a particular order
  // while being passed to the function
  public ArrayList<String> paraList ;
  public ArrayList<String> paraTypeList;
  public HashMap <String , String> funcVarList ;
  // every function has a retunr type and a class name
  String className ;
  String rtype ;
  String atype ;

  public void typeerror()
  {
    System.out.println("Type error");
    System.exit(0) ;
  }
  public funcBox(String classname , String returnType )
  {

      paraList = new ArrayList<String>() ;
      paraTypeList = new ArrayList<String>() ;
      funcVarList = new HashMap<String, String>();
      this.className = classname ;
      this.rtype = returnType ;
  }

  public void insertAccessType(String a )
  {
    if(!(a.equals("public") || a.equals("private") || a.equals("protected") ))
    {
        typeerror();
    }
    this.atype  = a ;
  }

  public void insertParameter(String id , String type )
  {
      this.paraList.add(id) ;
      this.paraTypeList.add(type) ;
  }

  public void insertfuncVar(String id , String type )
  {
      this.funcVarList.put(id, type);
  }
}
