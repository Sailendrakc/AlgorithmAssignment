package Assignment;

public class RandomizedBSTNode {
	int key;//data at the node
    RandomizedBSTNode left;//left child
    RandomizedBSTNode right;//right child
    RandomizedBSTNode up = null; //its parent
    
    public int Size = 1;

    public RandomizedBSTNode(int key, RandomizedBSTNode parent) {
        this.key = key;
        left = null;
        right = null;
        up = parent;
    }
////////////////////////////Standard RandomizedBST///////////////////////////////////////

    //Implementation of standard RandomizedBST search at a node 
    //returns node that contains the key
    RandomizedBSTNode nodeSearch(int key) {
        RandomizedBSTNode node = this;

        while (node != null) {
            if (key == node.key) {
                return node;
            } else if (key < node.key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    //Implementation of standard RandomizedBST insert at a node 
    boolean nodeInsert(int key) {
        if (key == this.key) {
            System.err.println("The tree already contains a node with the key: " + key);
            return false;
        }
        if (key < this.key) {
            if (left == null) {
                left = new RandomizedBSTNode(key, this);
                Size += 1;
                return true;
            } else {
            	boolean b = left.nodeInsert(key);
            	if(b) {
            		Size += 1;
            	}
                return b;
            }
        } else {
            if (right == null) {
                right = new RandomizedBSTNode(key,this);
                Size += 1;
                return true;
            } else {
            	boolean b = right.nodeInsert(key);
            	if(b) {
            		Size += 1;
            	}
                return b;
            }
        }
    }

    //Private method required by nodeDelete:
    //if the key is not found - returns null 
    //if the key is at the root - returns the root
    //otherwise, returns the parent of the node that contains the key
    private RandomizedBSTNode parentSearch(int key) {
        RandomizedBSTNode parent = this;
        RandomizedBSTNode node = this;

        while (node != null) {
            if (key == node.key) {
                return parent;
            } else if (key < node.key) {
                parent = node;
                node = node.left;
            } else {
                parent = node;
                node = node.right;
            }
        }
        return null;
    }

    //Implementation of standard RandomizedBST delete:
    //if this.key == key the method does not delete the node and returns false
    //if the key is not found the method returns false
    //otherwise, it deletes the node containing the key and returns true
    boolean nodeDelete(int key) {
        if (this.key == key) {
            return false;
        }
        RandomizedBSTNode parent = parentSearch(key);//parent of the node to be deleted
        if (parent == null) {
            return false; //nothing to delete
        }
        boolean isLeftChild = true;
        if (parent.left != null) {
            isLeftChild = parent.left.key == key;
        }

        RandomizedBSTNode node = isLeftChild ? parent.left : parent.right;

        if (node.left == null && node.right == null) {// deletes a leaf
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return true;
        }
        if (node.left != null && node.right == null) {//deletes a node with only a left child
            parent.left = node.left;
            return true;
        }
        if (node.left == null && node.right != null) {//deletes a node with only a right child
            parent.right = node.right;
            return true;
        }

        RandomizedBSTNode base = node.right;//defines the base point - the right child of the node to be deleted

        if (base.left == null) {//the base point does not have a left child
            if (isLeftChild) {
                parent.left = base;
            } else {
                parent.right = base;
            }
            base.left = node.left;
            return true;
        }
        RandomizedBSTNode first = base;
        RandomizedBSTNode second = base.left;
        while (second.left != null) {
            first = second;
            second = second.left;
        }

        first.left = second.right;
        second.right = base;
        second.left = node.left;
        if (isLeftChild) {
            parent.left = second;
        } else {
            parent.right = second;
        }
        return true;
    }

    static int getHeight(RandomizedBSTNode n) {
        if (n == null) {
            return 0;
        }
        int h1 = getHeight(n.left);
        int h2 = getHeight(n.right);
        return h1 > h2 ? h1 + 1 : h2 + 1;
    }

    void printPreorderNode() {
    	/*int upkey = 0;
    	if(this.up != null) {
    		upkey = up.key;
    	}*/
        System.out.print(key + ":"+ Size + " , ");
    	//System.out.print(key +  " , ");
        if (left != null) {
            left.printPreorderNode();
        }
        if (right != null) {
            right.printPreorderNode();
        }
    }

    void printInorderNode() {
        if (left != null) {
            left.printInorderNode();
        }
        System.out.print(key + " ");
        if (right != null) {
            right.printInorderNode();
        }
    }

    void printPostorderNode() {
        if (left != null) {
            left.printPostorderNode();
        }
        if (right != null) {
            right.printPostorderNode();
        }
        System.out.print(key + " ");
    }

/////////////////////////////Rotations, root-insert, root-search//////////////////////////
    //performs a right rotation at the node and returns the new root
    public  RandomizedBSTNode rightRotation(RandomizedBSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        
        //take the note of size variable 
        int nodesize = node.Size;
        int leftnodesize = node.left.Size;
        int otherSize = 0;
        if(node.left.right != null) {
        	otherSize = node.left.right.Size;
        }
        
        //performing rotation
        RandomizedBSTNode temp = node;
        node = node.left;
        temp.left = node.right;
        node.right = temp;
        
        
        //updating size variable
        node.Size = nodesize;
        temp.Size = nodesize-leftnodesize+otherSize;
        
        //maintaining value of 'up' variable that holds the info of parent of a node
        node.up = temp.up;
        if(node.up != null) {
       	 if(node.up.key > temp.key) {
       		 //we are left child
       		 node.up.left = node;
       	 }
       	 else {
       		 node.up.right = node;
       	 }
        }
        else {
        	Driver.tree.root = node;
        }
        
        if(temp.left != null) {
            temp.left.up = temp;
        }
        
        temp.up = node;
        
        return node;
    }
    //performs a left rotation at the node and returns the new root
    public RandomizedBSTNode leftRotation(RandomizedBSTNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        
        //take the note of size variable 
        int nodesize = node.Size;
        int rightnodesize = node.right.Size;
        int otherSize = 0;
        if(node.right.left != null) {
        	otherSize = node.right.left.Size;
        }
        
        //performing rotation
        RandomizedBSTNode temp = node;
        node = node.right;
        temp.right = node.left;
        node.left = temp;
        
        //updating size variable
        node.Size = nodesize;
        temp.Size = nodesize-rightnodesize+otherSize;
         
        //maintaining value of 'up' variable that holds the info of parent of a node
         node.up = temp.up;
         if(node.up != null) {
        	 if(node.up.key > temp.key) {
        		 //we are left child
        		 node.up.left = node;
        	 }
        	 else {
        		 node.up.right = node;
        	 }
         }
         else {
         	Driver.tree.root = node;
         }
         
         if(temp.right != null) {
             temp.right.up = temp;
         }
         
         temp.up = node;
         
        return node;
    }

    //Root-insert at the node
    public RandomizedBSTNode rootInsert(RandomizedBSTNode node, int key) {
        if (node.nodeSearch(key) != null) {//key is already in the tree
            return node;
        }
        return rootInsertHelper(node, key);
    }
    
    //Private recursive method, called by rootInsert
    //It does all the job
    private  RandomizedBSTNode rootInsertHelper(RandomizedBSTNode node, int Key) {
        if (Key < this.key) {
        	if(this.left == null) {
        		this.left = new RandomizedBSTNode(Key, this);
                this.Size += 1;
                RandomizedBSTNode n = this.rightRotation(this);
        		return n;
        	}
        	else {
                this.left.rootInsertHelper(this.left, Key);
                this.Size += 1;
                RandomizedBSTNode n=rightRotation(this);
                return n;
        	}      
        } else {//(key > node.key)
        	if(this.right == null) {
        		this.right = new RandomizedBSTNode(Key, this);
                this.Size += 1;
                RandomizedBSTNode n = this.leftRotation(this);
                if(this.up == null) {
                	Driver.tree.ChangeRoot(n);
                }
        		return n;
        	}
        	else {
                this.right.rootInsertHelper(this.right, Key);
                this.Size += 1;
                RandomizedBSTNode n=leftRotation(this);
                if(this.up == null) {
                	Driver.tree.ChangeRoot(n);
                }
                return n;
        	}   
        }
    }
    
  	//this method is responsible for inserting new node at this node with probabiliy of root insertion
  	//of 1/N+1 and probability of child insertion of N/N+1. This is implementaition of 
    // algorithm described in lecture 6.1 for randomised BST Insert.
    //the boolean 'checked', indicates that if we the program has checked the key is already in tree or not.
    public boolean RandomizedBstInsert(int keey, boolean checked) {
        //node we are inserting is already exist in the tree so return
        if (!checked) {
        	checked = true;
            if (Driver.tree.root.nodeSearch(keey) != null) {//key is already in the tree
                System.err.println("The tree already contains a node with the key: " + key);
                return false;
            }
        }
     
      //calculate the random numbers from 1 to N, perform root insertion at this node
      //if we get 1 else perform this method again in this's child. (child insertion).
      int max = (this.Size)+1; //size+1 == n+1
      int min = 1;
      
      //calculation of random 
      int x = (int)(Math.random()*((max-min)+1))+min;
      
      if(x==1){
        //perform root insert at this node
       // System.out.println("Inserting "+ keey+" in root due to probability 1 at node: "+ this.key);
    	  
        boolean result = Driver.tree.root.InsertAsNodeRoot(keey, this, false);
        
        return result;
      }
      else{
        //System.out.println("Inserting "+ keey+" in leaf due to probability other than 1 ");
        if(keey < this.key){
          //insert at left
          
          if(this.left == null){
            
            //create new and insert            
            this.left = new RandomizedBSTNode(keey, this);
            this.Size += 1;
            return true;
          }
          else{
            boolean inserted = left.RandomizedBstInsert(keey, checked);
            if(inserted) {
                this.Size += 1;
            }
          return inserted;          
          }
        }
        else{
          //insert at right
            if(this.right == null){
            
            //create new and insert
            right = new RandomizedBSTNode(keey, this);
            this.Size += 1;
            return true;
          }
          else{
            boolean inserted = right.RandomizedBstInsert(keey, checked);
            if(inserted) {
                this.Size += 1;
            }
          return inserted;          
          }
        }
      }
    }
    
    //This is helper class for random Inert at Node function. This keeps track of 
    // rotations and size while performing root insertion on randomizedBSTInsertion
    //key is the value, node is the node where root insertion hasto be done, startinserting
    // is the boolean for the indication of if we are above the node to be inserted or down.
    //so that we can perform rotation if we are below, and only increase size if we are above.
  	public boolean InsertAsNodeRoot(int Key, RandomizedBSTNode node, boolean startInserting){
  		if(this == node) {
  			//from this point our program should handle rotation as we are going to perform
  			//root insertion in this node. This is the indicator.
  			startInserting = true;
  		}
  		
        if (Key < this.key) {
        	if(this.left == null) {
        		this.left = new RandomizedBSTNode(Key, this);
                this.Size += 1;
                if(startInserting) {
                	RandomizedBSTNode n = this.rightRotation(this);
                }
        		return startInserting;

        	}
        	else {
                this.left.InsertAsNodeRoot(Key, node, startInserting);
                this.Size += 1;
                if(startInserting) {
                	RandomizedBSTNode n=rightRotation(this);
                }
        		return startInserting;
        	}      
        } else {//(key > node.key)
        	if(this.right == null) {
        		this.right = new RandomizedBSTNode(Key, this);
                this.Size += 1;
                if(startInserting) {
                	RandomizedBSTNode  n = this.leftRotation(this);
                }

        		return startInserting;
        	}
        	else {
                this.right.InsertAsNodeRoot(Key, node , startInserting);
                this.Size += 1;
                if(startInserting) {
                	RandomizedBSTNode n = this.leftRotation(this);
                }
                return startInserting;
        	}   
        }
    }
    
    //wrapper funciton and helper that helps on deletion of a node. This is implimented 
    //for keeping number of counts of child nodes.
  	//****Dont call this function to delete a node.
     public boolean nodedelete(int keey)
     {
       if(this.key == keey){
         return this.nodeDeleteHelper();
       }
       
       if(keey < this.key) {
         //on left
         if(this.left == null){
           //no such node exists to delete.
           return false;
         }
          boolean res = this.left.nodedelete(keey);
         if(res){
           Size -= 1;
         }
         return res;
         
          }
       else{
         //on right
         if(this.right == null){
           //no such node exist to delete
           return false;
         }
         boolean res = this.right.nodedelete(keey);
         if(res){
           Size -= 1;
         }
         return res;
          }
       }
      
    	
    	//Standard implementation of deletion of a node
    	//this.nodeDelete() will delete this node
     //This is the standard deletion process
     //i followed this https://www.youtube.com/watch?v=gcULXE7ViZw&t=660s&ab_channel=mycodeschool
     //tutorial.
    	public boolean nodeDeleteHelper(){
    		RandomizedBSTNode todelete = this;
        
        //if this node does not have any child  ---//root with no child will be deleted from tree class delete method
          if(todelete.left == null&& todelete.right == null){
            
            //simply remove the pointer
            RandomizedBSTNode upnode = todelete.up;
            
            //remove the reference of this node from its parent
            if(upnode.key > todelete.key){
              //we are left
              upnode.left = null;
              
            }
            else{
              upnode.right = null;
            }
            return true;
          }
  		
          //else if it only has only left child
           if(todelete.right == null){
              
             //if this element was root element
              if(todelete.up == null){
                //it was root element, simply exchange the root nodes

                Driver.tree.ChangeRoot(todelete.left);
                return true;
              }
              
              //it does not has right child, simply push the first left child up.
              if(todelete.up.key > todelete.key){
                //todelete is left child
                todelete.up.left = todelete.left;
                todelete.left.up = todelete.up;
                
              }
              else{
                //todelete is right child
                todelete.up.right = todelete.left;
                todelete.left.up = todelete.up;
              }
              return true;
            }
          
          //else if it only has only right child
          if(todelete.left == null){
              if(todelete.up == null){
                //it was root element, simply exchange the root nodes
                
                Driver.tree.ChangeRoot(todelete.right);
                return true;
              }
              
              //it does not has left child, simply push the first right child up.
              if(todelete.up.key > todelete.key){
                //todelete is left child
                todelete.up.left = todelete.right;
                todelete.right.up = todelete.up;
                
              }
              else{
                //todelete is right child
                todelete.up.right = todelete.right;
                todelete.right.up = todelete.up;
              }
              return true;
            }
  		
        
          //it has both childs
          // find a minimum value in right and transport it at the position of deleting node.
          RandomizedBSTNode minNodeinRight = todelete.right.minimumNodeFinder(true);
          
          //now minNodeinRight will not have left node and may have right node
          
          todelete.key = minNodeinRight.key;
          todelete.Size -= 1;
        
          //System.out.println("Deleting the original transfered node");
        	 minNodeinRight.nodeDeleteHelper();
            //original minNodeinRight will be collected by GC
          return true;
      }
    	
    	//finds minumun node to perform node deletion, it is also a kind of helper funciton to nodeDelete funciton
    	RandomizedBSTNode minimumNodeFinder(boolean forDelete){
        if(this.left != null){
          RandomizedBSTNode minNode = this.left.minimumNodeFinder(forDelete);
          if(forDelete){
            this.Size -= 1;
            minNode.Size += 1;
          }
          return minNode;
        }
        else{
          return this;
        }
      }
    	
    //Root-search at the node
    public RandomizedBSTNode rootSearch(RandomizedBSTNode node, int key) {
        if (node == null) {
            return null;
        }
          if (key == node.key) {
            return node;
        }
        if (key < node.key) {
            RandomizedBSTNode temp = rootSearch(node.left, key);
            if (temp != null) {
                node.left = temp;
                node = rightRotation(node);
                return node;
            }
            return null;
        }
        if (key > node.key) {
            RandomizedBSTNode temp = rootSearch(node.right, key);
            if (temp != null) {
                node.right = temp;
                node = leftRotation(node);
                return node;
            }
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "data: " + key + "Node size: "+ Size;
        return s;
    }
}

