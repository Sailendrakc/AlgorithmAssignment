package Assignment;

public class RandomizedBST {
	 public  RandomizedBSTNode root;

	    public RandomizedBST() {
	        root = null;
	    }

	    public RandomizedBST(RandomizedBSTNode node) {
	        root = node;
	    }
	    
	    //this function changes the calue of root variable of this class
	    public void ChangeRoot(RandomizedBSTNode newroot) {
	    	this.root = newroot;
	    }
	    
	    
	    public RandomizedBSTNode nodeSearch(int key){
	        return root.nodeSearch(key);
	    }
	    
	    //call this function to delete a node, this function
	    //handles the subtraction of size and manages rotations needed to perform deletion.
	    public void BSTNodeDelete(int keey) {
	    	root.nodedelete(keey);
	    }
	    
	    public int getHeight() {
	        return RandomizedBSTNode.getHeight(root);
	    }

	    public void printPreorder() {
	        root.printPreorderNode();
	    }

	    public void printInorder() {
	        root.printInorderNode();
	    }

	    public void printPostorder() {
	        root.printPostorderNode();
	    }
	    
	    public boolean nodeInsert(int key){
	        return root.nodeInsert(key);
	    }
	    
	    public boolean nodeDelete(int key){
	        return root.nodeDelete(key);
	    }
	    
	    public void rootInsert(int key){
	        root = Driver.tree.root.rootInsert(root, key);
	    }
	    
	    public boolean rootSearch(int key){
	        if(key != root.key){
	            root = Driver.tree.root.rootSearch(root, key);
	            return key == root.key;
	        }
	        return false;
	    }

	   /* //this overload method is used to insert a node in a non root node but as a root.
	    //this is used to insert to a node when we get 1 from random numbers of 1 to N.
	    //as required when implementing the randomized insert algorithm
		public void RandomizedrootInsert(int key, RandomizedBSTNode randomizedBSTNode) {
			root.InsertAsNodeRoot(key, randomizedBSTNode, false);
		}*/
}

