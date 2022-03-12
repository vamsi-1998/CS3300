
package visitor;
import syntaxtree.*;
import java.util.*;

public class SymbolTable
{

   public   LinkedHashMap<String , funcBox> funcList ;
   SymbolTable()
   {
       funcList = new LinkedHashMap<String, funcBox>() ;
   }

   void printTable()
   {
     // for (Map.Entry <String , funcBox> mapElement : funcList.entrySet())
     //       {
     //           String key = (String)mapElement.getKey();
     //           funcBox fb = mapElement.getValue() ;
     //           System.out.print('\n');


               Set<String> keys = funcList.keySet();

        // printing the elements of LinkedHashMap
        for (String key : keys) {

            // key contains the string
            funcBox fb = funcList.get(key);
            if( fb != null )
            {
              System.out.print('\n');
              Set<Integer> keys1 = fb.leftTempMap.keySet();
             for(int k:keys1)
             {
                 System.out.print("TEMP"+k+"[" + fb.leftTempMap.get(k) + ":") ;
                 if(fb.rightTempMap.containsKey(k) )
                 {
                     System.out.print( fb.rightTempMap.get(k) + "]");
                 }
                 else
                 {
                   System.out.print( "-1"+ "]");
                 }
                 System.out.print('\n');
             }
            }
        }

               //
               //
               //
               // if( fb != null )
               // {
               //   Set<Integer> keys = fb.leftTempMap.keySet();
               //  for(int k:keys)
               //  {
               //      System.out.print("TEMP"+k+"[" + fb.leftTempMap.get(k) + ":") ;
               //      if(fb.rightTempMap.containsKey(k) )
               //      {
               //          System.out.print( fb.rightTempMap.get(k) + "]");
               //      }
               //      else
               //      {
               //        System.out.print( "-1"+ "]");
               //      }
               //      System.out.print('\n');
               //  }
               // }
           }
   }
