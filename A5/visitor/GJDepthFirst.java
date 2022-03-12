//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJDepthFirst<R,A> implements GJVisitor<R,A> {
   //
   // Auto class visitors--probably don't need to be overridden.

   //
   int stkCnt;
   boolean mt4 ;
   int spillMin = 10000;
   boolean isLable ;
   String str , op , binOpReg ;
   public R visit(NodeList n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n, A argu) { return (R) n.tokenImage; }

   //
   //https://stackoverflow.com/questions/3844595/how-can-i-make-java-print-quotes-like-hello
   // User-generated visitor methods below
   //

   /**
    * f0 -> "MAIN"
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> "["
    * f5 -> IntegerLiteral()
    * f6 -> "]"
    * f7 -> "["
    * f8 -> IntegerLiteral()
    * f9 -> "]"
    * f10 -> StmtList()
    * f11 -> "END"
    * f12 -> ( SpillInfo() )?
    * f13 -> ( Procedure() )*
    * f14 -> <EOF>
    */
   public R visit(Goal n, A argu) {
      R _ret=null;
      System.out.println("\t .text ");
      System.out.println("\t .globl \t main ");
      n.f0.accept(this, argu);
      System.out.println("main:");
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      String stackCnt = (String) n.f5.accept(this, argu);
      int stkCnt = Integer.parseInt(stackCnt) ;
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      System.out.println("\t move $fp, $sp");
      System.out.println("\t subu $sp, $sp, " + (stkCnt+1)*4 );
      System.out.println("\t sw $ra, -4($fp)");
      n.f10.accept(this, argu);
      System.out.println("\t lw $ra , -4($fp)");
      System.out.println("\t addu $sp , $sp , " + (stkCnt+1)*4 );
      System.out.println("\t jal _exitret");
      // lw $ra, -4($fp)
      //    addu $sp, $sp, 4
      //    j $ra
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      System.out.println();
      n.f13.accept(this, argu);
      /*
          .text
    .globl _halloc
      _halloc:
         li $v0, 9
         syscall
         j $ra

         .text
         .globl _print
_print:
         li $v0, 1
         syscall
         la $a0, newl
         li $v0, 4
         syscall
         j $ra

         .data
         .align   0
newl:    .asciiz "\n"
         .data
         .align   0
str_er:  .asciiz " ERROR: abnormal termination\n"

      */
      System.out.println("\t .text");
      System.out.println("\t .globl _halloc");
      System.out.println("_halloc:");
      System.out.println("\t li $v0 9 ");
      System.out.println("\t syscall");
      System.out.println("\t jr $ra");

      System.out.println();

      System.out.println("\t .text");
      System.out.println("\t .globl _print");
      System.out.println("_print:");
      System.out.println("\t li $v0 1");
      System.out.println("\t syscall");
      System.out.println("\t la $a0, newl");
      System.out.println("\t li $v0, 4");
      System.out.println("\t syscall");
      System.out.println("\t jr $ra");

      System.out.println();
//
//
//       .text
// .globl _exitret
// _exitret:
// li $v0, 10
// syscall

      System.out.println("\t .text");
      System.out.println("\t .globl _exitret");
      System.out.println(" _exitret:");
      System.out.println("\t li $v0, 10");
      System.out.println("\t syscall");
      System.out.println();
      //'\u0022'
      System.out.println("\t .data");
      System.out.println("\t .align  0 ");
      System.out.println("newl: \t .asciiz " +   '\u0022' + "\\n" +   '\u0022' );
      System.out.println("\t .data");
      System.out.println("\t .align  0 ");
      System.out.println("str_er:  .asciiz " +   '\u0022' + "ERROR: abnormal termination \\n" +   '\u0022' );



      n.f14.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ( ( Label() )? Stmt() )*
    */
   public R visit(StmtList n, A argu) {
      R _ret=null;
   n.f0.accept(this, argu );


      return _ret;
   }

   /**
    * f0 -> Label()
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> "["
    * f5 -> IntegerLiteral()
    * f6 -> "]"
    * f7 -> "["
    * f8 -> IntegerLiteral()
    * f9 -> "]"
    * f10 -> StmtList()
    * f11 -> "END"
    * f12 -> ( SpillI  String all = (String) nfo() )?
    */
   public R visit(Procedure n, A argu) {
      R _ret=null;
      spillMin = 10000;
      //System.out.println("From proc " + spillMin );
      String l = (String) n.f0.accept(this, (A) "proc");
      isLable = false ;
      System.out.println("\t .text " );
      System.out.println("\t .globl\t" + l );
      System.out.println(l+":");
      n.f1.accept(this, argu);
      String argnt = (String) n.f2.accept(this, argu);
      int argCount = Integer.parseInt(argnt) ;
      if(argCount > 4 ) mt4 = true ;
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      String stackCnt  = (String) n.f5.accept(this, argu);
      stkCnt = Integer.parseInt(stackCnt) ;
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      // handling fp and sp and allocationg space

      System.out.println("\t sw $fp, -8($sp)");
      System.out.println("\t move $fp, $sp");
      System.out.println("\t subu $sp , $sp " + (stkCnt+2)*4 );
      System.out.println("\t sw $ra , -4($fp)");
    //  System.out.println("spillMin is " + spillMin);
      n.f10.accept(this, argu);
      System.out.println("\t  lw $ra , -4($fp) ");
      System.out.println("\t lw $fp , " + stkCnt*4 + "($sp)");
      System.out.println("\t addu $sp, $sp ," + (stkCnt+2)*4 );
      System.out.println("\t jr $ra ");
      n.f11.accept(this, argu);
      spillMin = 10000 ;
      n.f12.accept(this, argu);
      System.out.println();
      return _ret;
   }

   /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    *       | ALoadStmt()
    *       | AStoreStmt()
    *       | PassArgStmt()
    *       | CallStmt()
    */
   public R visit(Stmt n, A argu) {
      R _ret=null;
     n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "NOOP"
    */
   public R visit(NoOpStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      System.out.println("\t nop");
      return _ret;
   }

   /**
    * f0 -> "ERROR"
    */
   public R visit(ErrorStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "CJUMP"
    * f1 -> Reg()
    * f2 -> Label()
    */
   public R visit(CJumpStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String reg = (String)n.f1.accept(this, argu);
      String lb = (String) n.f2.accept(this, (A) "cjump");
      System.out.println("\t beqz "+reg+" "+lb);
      isLable = false ;
      return _ret;

   }

   /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
   public R visit(JumpStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String lb = (String) n.f1.accept(this, (A) "jump");
      System.out.println("\t b "+" "+ lb);
      isLable = false ;
      return _ret;
   }

   /**
    * f0 -> "HSTORE"
    * f1 -> Reg()
    * f2 -> IntegerLiteral()
    * f3 -> Reg()
    */
   public R visit(HStoreStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String s1 = (String) n.f1.accept(this, argu);
      String off = (String) n.f2.accept(this, argu);
      String s2 = (String) n.f3.accept(this, argu);
      System.out.println("\t sw " + s2 + "," + off+"("+s1+")");
      return _ret;
   }

   /**
    * f0 -> "HLOAD"
    * f1 -> Reg()
    * f2 -> Reg()
    * f3 -> IntegerLiteral()
    */
   public R visit(HLoadStmt n, A argu) {
      R _ret=null;
     n.f0.accept(this, argu);
        String s1 = (String)n.f1.accept(this, argu);
        String s2 = (String)n.f2.accept(this, argu);
        String off = (String)n.f3.accept(this, argu);
        System.out.println("\t lw "+ s1 + " " + off + "(" + s2 +")");
      return _ret;
   }

   /**
    * f0 -> "MOVE"
    * f1 -> Reg()
    * f2 -> Exp()
    */
   public R visit(MoveStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String reg = (String) n.f1.accept(this, argu);
      String exp = (String) n.f2.accept(this, argu);

      try{
        int num =  Integer.parseInt(exp) ;
        System.out.println("\t li "+reg+" "+num);
      }
      catch (Exception e )
      {
        if(exp == "halloc")
          System.out.println("\t move "+reg + " $v0");
        else
        {
          if(isLable)
          {
              //System.out.println("isLable is " + isLable);
              System.out.println("\t la "+reg +" "+exp );
              isLable =false ;
          }
          else if ( exp == "binOp")
          {
              System.out.println("\t " + str + " " + reg + " " + binOpReg + " " + op );
          }
          else
            System.out.println("\t move "+reg+" "+exp);

        }

      }


      return _ret;
   }

   /**
    * f0 -> "PRINT"
    * f1 -> SimpleExp()
    */
   public R visit(PrintStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String reg = (String) n.f1.accept(this, argu);
      System.out.println("\t move $a0 "+ reg );
      System.out.println("\t jal _print");
      return _ret;
   }

   /**
    * f0 -> "ALOAD"
    * f1 -> Reg()
    * f2 -> SpilledArg()
    */
   public R visit(ALoadStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String reg = (String) n.f1.accept(this, argu);
      String val = (String) n.f2.accept(this, argu);
      int value = Integer.parseInt(val) ;
      //System.out.println("spillMin is " + spillMin + " value is " + value );
      if( value < spillMin )
      {
          System.out.println("\t lw " + reg + ", "+value*4 +"($fp)" );
      }
      else
      System.out.println("\t lw " + reg + ", "+value*4 +"($sp)" );
      return _ret;
   }

   /**
    * f0 -> "ASTORE"
    * f1 -> SpilledArg()
    * f2 -> Reg()
    */
   public R visit(AStoreStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String val = (String) n.f1.accept(this, argu);
      int value = Integer.parseInt(val) ;
      String reg = (String) n.f2.accept(this, argu);
      if(value < spillMin ) spillMin = value ;
      //System.out.println("spillMin value is " + spillMin);
      // ASTORE SPILLEDARG s0
      System.out.println("\t sw " + reg + ", "+value*4 +"($sp)" );
      return _ret;
   }

   /**
    * f0 -> "PASSARG"
    * f1 -> IntegerLiteral()
    * f2 -> Reg()
    */
   public R visit(PassArgStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String val = (String) n.f1.accept(this, argu);
      int value = Integer.parseInt(val) ;
      String reg = (String) n.f2.accept(this, argu);
      System.out.println("\t sw " + reg + " , " + (value-1)*4+"($sp)" );
      return _ret;
   }

   /**
    * f0 -> "CALL"
    * f1 -> SimpleExp()
    */
   public R visit(CallStmt n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String reg = (String) n.f1.accept(this, argu);
      if(isLable)
      {
        System.out.println("\t jal " + reg );
        isLable = !isLable ;
      }
      else
      System.out.println("\t jalr " + reg );

      return _ret;
   }

   /**
    * f0 -> HAllocate()
    *       | BinOp()
    *       | SimpleExp()
    */
   public R visit(Exp n, A argu) {
      R _ret=null;
      String exp = (String) n.f0.accept(this, argu);

        _ret = (R) exp ;

      return _ret;
   }

/*
System.out.println("\taddu $sp, $sp, " + (4 * (stackSpace - inArgs + outArgs) + 4));
      System.out.println("\tlw $ra, -4($fp)");
      System.out.println("\tj $ra");
      System.out.println();
For other functions
System.out.println("\taddu $sp, $sp, " + (4 * (stackSpace - inArgs + outArgs) + 8));
      System.out.println("\tlw $ra, -4($fp)");
      System.out.println("\tlw $fp, -8($sp)");
      System.out.println("\tj $ra");
*/


   /**
    * f0 -> "HALLOCATE"
    * f1 -> SimpleExp()
    */
   public R visit(HAllocate n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String simpExp = (String) n.f1.accept(this, argu);
      try{
        // if simpExp is an integer
        int num = Integer.parseInt(simpExp) ;
        System.out.println("\t li $a0 "+num);
        System.out.println("\t jal _halloc");
        _ret = (R) "halloc";
      }
      catch (Exception e )
      {
        System.out.println("\t move $a0 "+ simpExp);
        System.out.println("\t jal _halloc");
        _ret = (R) "halloc";
      }
      return _ret;
   }

   /**
    * f0 -> Operator()
    * f1 -> Reg()
    * f2 -> SimpleExp()
    */
   public R visit(BinOp n, A argu) {
      R _ret=null;
        //str = null;
        str  = (String) n.f0.accept(this, argu);
      switch(str)
      {
        case "LE": str = "sle" ; break ;
        case "NE": str = "sne" ; break ;
        case "PLUS": str = "add" ; break ;
        case "MINUS" : str = "sub" ; break ;
        case "TIMES" : str = "mul" ; break ;
        case "DIV" : str = "div" ; break ;
      }
       binOpReg = (String) n.f1.accept(this, argu);
      op = (String) n.f2.accept(this, argu);

      _ret = (R) "binOp";
      return _ret;
   }

   /**
    * f0 -> "LE"
    *       | "NE"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"CALL
    *       | "DIV"
    */
   public R visit(Operator n, A argu) {
      R _ret=null;
      String op = (String) n.f0.accept(this, argu);
      _ret = (R) op ;
      return _ret;
   }

   /**
    * f0 -> "SPILLEDARG"
    * f1 -> IntegerLiteral()
    */
   public R visit(SpilledArg n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String offset = (String) n.f1.accept(this, argu);
      int off = Integer.parseInt(offset) ;
      _ret = (R) offset ;
      return _ret;
   }

   /**
    * f0 -> Reg()
    *       | IntegerLiteral()
    *       | Label()
    */
   public R visit(SimpleExp n, A argu) {
      R _ret=null;
      //String simpExp = (String) n.f0.accept(this, argu);
      if(n.f0.which == 0 )
      {
            String simpExp = (String) n.f0.accept(this, argu);
            _ret = (R) simpExp;
      }
      if(n.f0.which == 1 )
      {
            String simpExp = (String) n.f0.accept(this, argu);
            _ret = (R) simpExp;
      }
      if(n.f0.which == 2 )
      {
            String simpExp = (String) n.f0.accept(this, (A) "smpExp");
            _ret = (R) simpExp;
      }

      return _ret;
   }

   /**
    * f0 -> "a0"
    *       | "a1"
    *       | "a2"
    *       | "a3"
    *       | "t0"
    *       | "t1"
    *       | "t2"
    *       | "t3"
    *       | "t4"
    *       | "t5"
    *       | "t6"
    *       | "t7"
    *       | "s0"
    *       | "s1"
    *       | "s2"
    *       | "s3"
    *       | "s4"
    *       | "s5"
    *       | "s6"
    *       | "s7"
    *       | "t8"
    *       | "t9"
    *       | "v0"
    *       | "v1"
    */
   public R visit(Reg n, A argu) {
      R _ret=null;
      String reg = (String) n.f0.accept(this, argu);
      reg = "$"+reg ;
      _ret = (R) reg ;
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n, A argu) {
      R _ret=null;
      String val = (String) n.f0.accept(this, argu);
      _ret = (R) val ;
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Label n, A argu) {
      R _ret=null;

      String lbl = (String) n.f0.accept(this, argu);
      if( ((String)argu) == "proc"  || ((String)argu) == "cjump" || ((String)argu) == "jump" ||  ((String)argu) == "smpExp" )
      {

      //System.out.print(" "+lbl);
      isLable = true ;
      _ret = (R) lbl ;
      }
      else
      {
          isLable = false ;
          System.out.println(lbl+":");
      }

      return _ret;
   }

   /**
    * f0 -> "//"
    * f1 -> SpillStatus()
    */
   public R visit(SpillInfo n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> <SPILLED>
    *       | <NOTSPILLED>
    */
   public R visit(SpillStatus n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

}
