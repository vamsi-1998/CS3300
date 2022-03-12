import syntaxtree.*;
import visitor.*;

public class A4{
   public static void main(String [] args) {
      try {
         Node root = new microIRParser(System.in).Goal();
         //System.out.println("Program parsed successfully");
         root.accept(new GJDepthFirst() , null ); // Your assignment part is invoked here.
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
}

/*
microIRParser mip = new microIRParser(System.in);
        Node root = microIRParser.Goal();
        root.accept(new RegisterAllocator<String, String>(), "");
*/
