
package visitor;
import syntaxtree.*;
import java.util.*;

public class funcBox
{
    int maxArg;
    public LinkedHashMap<Integer,Integer>leftTempMap ;
    public LinkedHashMap<Integer,Integer>rightTempMap ;
  //  public LinkedHashMap<Integer,Integer>argTempMap ;
    boolean tFlag ;
    boolean sFlag ;
    int flag ;
    int stkCnt ;
    int itr ;
    int argcount ;
    String funcname ;
    public HashMap<Integer,String> regMap ;
    String []regSet ;
    String []regS ;
    String []regT ;
    String []argRegSet;
    boolean freeReg[] ;
     ArrayList<Integer> activeRegisters;
    public HashMap<Integer,String> usedRegMap ;
    funcBox( String fname , int argc )
    {

       maxArg =0 ;
       stkCnt = 0 ;
       tFlag = false ;
       sFlag = false ;
       this.funcname = fname ;
       this.argcount = argc ;
       leftTempMap = new LinkedHashMap<Integer,Integer>() ;
       rightTempMap = new LinkedHashMap<Integer,Integer>() ;
       regSet = new String[]{"s0","s1","s2","s3","s4","s5","s6","s7","t0","t1","t2","t3","t4","t5","t6","t7","t8","t9"};
       regS  = new String[]{"s0","s1","s2","s3","s4","s5","s6","s7"};
       regT = new String[]{"t0","t1","t2","t3","t4","t5","t6","t7","t8","t9"};
       argRegSet = new String[]{"a0","a1","a2","a3"};
       activeRegisters = new ArrayList<Integer>() ;
       usedRegMap = new HashMap<Integer,String>() ;
       //argTempMap = new LinkedHashMap<String,String>() ;
       regMap = new HashMap<Integer,String>() ;
       freeReg = new boolean[18] ;
       Arrays.fill(freeReg,true) ;

    }

    void LinearScan(LinkedHashMap<Integer,Integer>left , LinkedHashMap<Integer,Integer>right)
    {
        flag =1 ;
        Set<Integer> keys = left.keySet();
       for(int k:keys)
       {
           if(flag != 0 )
           {
           for(int i = 0 ; i < activeRegisters.size() ; i++ )
           {
                int tmp = activeRegisters.get(i) ;
                if( right.containsKey(tmp) )
                {
                    // if endpoint of tmp is less than start point of key k
                    if( right.get(tmp) <= left.get(k) )
                    {
                      // remove tmp activeRegister
                      String str = regMap.get(tmp);
                      if(str != null )
                      {
                        usedRegMap.put(activeRegisters.get(i),str);
                        activeRegisters.remove(i) ;
                        activeRegisters.add(k) ;
                        regMap.remove(tmp) ;
                        regMap.put(k,str) ;
                        break ;
                      }
                      // removed the register map
                      // regMap.remove(tmp) ;
                      // regMap.put(k,str) ;
                    }
                }
           }
           if(!activeRegisters.contains(k))
           {
             for( itr = 0 ; itr < freeReg.length ; itr++)
             {

                  if(freeReg[itr]==true )
                  {
                    activeRegisters.add(k);
                    String str = regSet[itr] ;
                      regMap.put(k,str);
                      if(str.startsWith("s"))
                      {
                        sFlag = true ;
                      }
                      if(str.startsWith("t"))
                      {
                        tFlag = true ;
                      }
                      freeReg[itr] = false ;
                      break ;
                  }
             }
             //System.out.println(" i is " + itr + " freeRegLength is : " + freeReg.length);
             if( itr == freeReg.length)
             {
                System.out.println("Register cant be allocated");
             }
           }
         }
         flag = 1 ;
       }
    }

}
