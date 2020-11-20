package Assignment;

/**
*
* @author Eldar Hajilarov
*/
public class Driver {

   /**
    * @param args the command line arguments
    */
    static RandomizedBSTNode n = new RandomizedBSTNode(10, null);
    static RandomizedBST tree = new RandomizedBST(n);

   public static void main(String[] args) {

	      System.out.println("Print pre order will print 'Value : Size' style");
       /*tree.nodeInsert(5);
       tree.nodeInsert(2);
       tree.nodeInsert(6);
       tree.nodeInsert(15);
       tree.nodeInsert(13);
       tree.nodeInsert(14);
       tree.nodeInsert(20);
       tree.nodeInsert(18);
       tree.nodeInsert(19);
       tree.nodeInsert(21);*/
       

	   //algorithm needed to implement for assignment
       tree.root.RandomizedBstInsert(5, false);

       tree.root.RandomizedBstInsert(15, false);

       tree.root.RandomizedBstInsert(2, false);
   
       tree.root.RandomizedBstInsert(6, false);
 
       tree.root.RandomizedBstInsert(13, false);

       tree.root.RandomizedBstInsert(20, false);

       tree.root.RandomizedBstInsert(12, false);

       tree.root.RandomizedBstInsert(14, false);

       tree.root.RandomizedBstInsert(18, false);

       tree.root.RandomizedBstInsert(21, false);
       
       tree.printPreorder();
       System.out.println("-----------");
       
       tree.BSTNodeDelete(10);
       tree.printPreorder();
       
       
   }
}
