
package visitor;
import syntaxtree.*;
import java.util.*;

public class SymbolTable
{
           // new symbol table created
           public HashMap<String, classBox> classList;
           SymbolTable()
           {
              classList = new HashMap<String , classBox>();
           }
           public boolean findClass(String str )
           {
              boolean present = classList.containsKey(str) ;
              return present ;
           }
           public void typeerror()
           {
             System.out.println("Type error");
             System.exit(0) ;
           }
           public void insertClass(String str , classBox c  )
           {
               if(!classList.containsKey(str) ) classList.put(str , c ) ;
                else typeerror() ;
           }
           // public void insertExtendClass(String ch , classBox c   )
           // {
           //     if(!classList.containsKey(str))classList.put(ch ,c ) ;
           //     else typeerror();
           // }
           public boolean isFuncPresent(String c, String f )
           {
                  return this.classList.get(c).funcList.containsKey(f) ;
           }

           public boolean isSubClass(String sclass , String pclass)
           {
             //
                 String inter = getParent(sclass) ;
                 while(!inter.equals(""))
                 {
                      if( inter.equals(pclass))
                      {
                          return true ;
                      }
                      inter = getParent(inter);
                 }
                 return false ;
           }

           public String getParent(String cls)
           {
                return classList.get(cls).parentName;
           }
           public boolean isAllowed(String currCls , String cls , String fn )
           {

             // if both the classes are valid
              if(findClass(currCls) && findClass(cls))
              {
                // if current class and the class are not equal
                if(!currCls.equals(cls))
                {
                      // if they are not equal
                      // check whether the function is calle fr
                      if(isFuncPresent(cls,fn))
                      {
                          // functions is present in the class

                          if( (classList.get(cls).funcList.get(fn).atype).equals("private") )
                          {
                             return false ;
                          }
                          else if ( (classList.get(cls).funcList.get(fn).atype).equals("public"))
                          {
                              return true ;
                          }
                          else
                          {
                                // function is protected
                                if(isSubClass(currCls,cls))
                                {
                                    return true ;
                                }
                                else return false ;
                          }
                      }
                      else
                      {
                        // function not in the present class
                        // check whether the function is present in the parent class
                        String p = getParent(cls);
                        while(p != null )
                        {
                           if(isFuncPresent(p,fn))
                           {
                                // check the access Type of the function

                                if( (classList.get(cls).funcList.get(fn).atype).equals("private") )
                                {
                                   return false ;
                                }
                                else if ( (classList.get(cls).funcList.get(fn).atype).equals("public"))
                                {
                                    return true ;
                                }
                                else if((classList.get(cls).funcList.get(fn).atype).equals("protected"))
                                {
                                    return true ;
                                }
                           }
                           p = getParent(p) ;
                        }

                        return false ;
                      }
                }
                else return true ;
              }
              else return false ;
           }
           public String getType(String id , String cname , String fname )
           {

             classBox currbox = this.classList.get(cname) ;
             funcBox f = currbox.funcList.get(fname);
             boolean present = false   ;
            // System.out.print(id);
            // check for function variables
             for(Map.Entry <String , String> fvi : f.funcVarList.entrySet())
             {
                    // variable found in function variables list
                    if ( fvi.getKey() == id )
                    {
                      present = true ;
                      return fvi.getValue() ;
                    }

             }
             if(!present)
             {
               for(int i = 0 ; i < f.paraList.size() ; i++)
               {
                       if ( f.paraList.get(i) == id )
                       {
                           present = true ;
                           return f.paraTypeList.get(i);
                        }
               }
             }
             //  present is still false
             // then check for the class variables of the function
               if(!present)
               {

                   //public HashMap <String,String> varList  ;
                   for(Map.Entry <String , String> fvi : currbox.varList.entrySet())
                   {
                           if ( fvi.getKey() == id )
                           {
                             present = true ;
                             return fvi.getValue()  ;
                           }

                   }
               }
               if(!present)
               {
                  // check for the parent class of the function where the variables are present or not
                  String pclass = getParent(cname) ;
                  while(pclass != null )
                  {
                        // check if id is present in the class variables of parent
                        // System.out.println("Wow");
                        // System.out.println(pclass + " " + cname);
                        // System.out.println("Wow");
                        currbox = this.classList.get(pclass) ;
                        for(Map.Entry <String , String> fvi : currbox.varList.entrySet())
                        {
                                if ( fvi.getKey() == id )
                                {
                                  present = true ;
                                  return fvi.getValue()  ;
                                }

                        }
                        pclass = getParent(pclass) ;
                  }
               }
               typeerror() ;
               return null ;
           }
           /*public String getType (String id ,  String  cname , String  fname )
           {
             funcBox f = currbox.funcList.get(fname);
             String type = "";
             // public HashMap <String , String> funcVarList ;
             boolean present = false ;
             //System.out.print(id);
             if(isVarPresent(id , cname , fname , currbox))
             {
               for(Map.Entry <String , String> fvi : f.funcVarList.entrySet())
               {
                      // variable found in function variables list
                      if ( fvi.getKey() == id )
                      {
                        present = true ;
                        type = fvi.getValue() ;
                        return type ;
                      }

               }
               if(!present)
               {
                 for(int i = 0 ; i < f.paraList.size() ; i++)
                 {
                         if ( f.paraList.get(i) == id )
                         {
                             present = true ;
                             type = f.paraTypeList.get(i) ;
                            return type ;
                          }
                 }
               }
               //  present is still false
               // then check for the class variables of the function
                 if(!present)
                 {
                   for(Map.Entry <String , String> fvi : currbox.varList.entrySet())
                   {
                           if ( fvi.getKey() == id )
                           {
                             present = true ;
                             type = fvi.getValue() ;
                             return type  ;
                           }

                   }
                 }
                 if(!present)
                 {
                   // check whether the class has parent class
                    System.out.print("Type check error");
                    System.exit(0) ;

                   // if it has no parent then

                 }
             }
             else
             {
               System.out.print("Type check error");
               System.exit(0) ;
             }
             return type ;

           }*/
           /*public void printTable()
           {
             for (Map.Entry <String , classBox> mapElement : classList.entrySet())
             {
                 String key = (String)mapElement.getKey();
                 classBox c = mapElement.getValue() ;
                 System.out.print("I am in class : " + key + '\n');
                 System.out.print("class Variables in " + key + " are "+ '\n');
                 if( c != null )
                 {
                   for(Map.Entry <String , String> cvi : c.varList.entrySet())
                   {
                      System.out.print( (String)cvi.getKey() + " type is " + (String) cvi.getValue()+'\n' );
                   }
                   for(Map.Entry <String , funcBox> fi : c.funcList.entrySet())
                   {
                     funcBox fb = fi.getValue();
                     System.out.print("Function name is " + (String) fi.getKey()+" whose retun type is " + fb.rtype + " whose access type is "+ fb.atype+ '\n');
                     System.out.print("Parameters for the function are : " +'\n');
                     for ( int i = 0 ; i < fb.paraList.size() ; i++ )
                     {
                       System.out.print(fb.paraList.get(i) + " whose type is " + fb.paraTypeList.get(i) ) ;
                      }
                      System.out.print('\n');
                        System.out.print("Variables for the function are : " +'\n');
                       for(Map.Entry <String , String> fvi : fb.funcVarList.entrySet())
                       {

                              System.out.print(fvi.getKey() + " type is " + fvi.getValue()+'\n');

                       }
                   }
                   System.out.print('\n');
                 }

             }

          }*/
}
