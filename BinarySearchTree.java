package treestates;

/**
   This class implements a binary search tree.
*/

public class BinarySearchTree
{    
     private class Node
    {
        String  value;          // Value stored in node
        Node left, right;    // Left and right child      
        
        /**
           Constructor for leaf nodes.
           @param val The value to store in the node.
        */
        
        Node(String val)
        {
            value = val;
            left = null;
            right = null;
        }   
        
        /** 
           Constructor for non-leaf nodes.
           @param val The value to initialize the node.
           @param leftChild The link to the left child.
           @param rightChild The link to the right child.
        */

        Node(String val, Node leftChild, Node rightChild)
        {
            value = val;
            left = leftChild;
            right = rightChild;
        }
    }
   
    /**
       This class represents the result of 
       removing a node from a binary tree.
    */
    
    private class RemovalResult
    {
       Node node;    // Removed node
       Node tree;    // Remaining tree
       RemovalResult(Node node, Node tree)
       {
         this.node = node;
         this.tree = tree;
       }
    }

    private Node root = null;   // Root of binary tree    
    
    /**
       The public add method adds a value to the 
       tree by calling a private add method and 
       passing it the root of the tree.
       @param x The value to add to the tree.
       @return true.     
    */
    
    public boolean add(String x)
    {
        root =  add(x, root);
        return true;
    }
   
    /**
       The contains method checks to see if a 
       value is in the binary tree.
       @param x The value to check for.
       @return true if x is in the tree, 
		 false otherwise.
    */
    
    public boolean contains(String x)
    {
        // Call the private recursive method
        return contains(x, root);
    }
    
    /**
       The isEmpty method checks for an empty tree.
       @return true if the tree is empty, 
		 false otherwise.
    */
    
    public boolean isEmpty()
    {
        if (root == null)
            return true;
        else
            return false;
    }
   
    /**
       The add method adds a value to the search tree.
       @x the value to add.
       @param bstree The root of the binary 
		 search tree.
       @return The root of the resulting 
		 binary search tree.
    */  
    
    private Node add(String x, Node bstree)
    {
        if (bstree == null)
            return new Node(x);
        // bstree is not null. 
        if (x.compareTo(bstree.value) < 0)
        {
            // Add x to the left subtree and replace 
            // the current left subtree with the result
            bstree.left = add(x, bstree.left);             
        }
        else
        {   
            // Add x to the right subtree
            bstree.right = add(x, bstree.right);
        }
        return bstree;
    }
    
    /**
       The method contains checks whether an
       item is in a binary search tree.
       @param x The item to check for.
       @param bstree The binary tree to look in.
		 @return true if found, false otherwise.
    */
    
    private boolean contains(String x, Node bstree)
    {
        if (bstree == null)
            return false;
        if (x.compareTo(bstree.value) == 0) 
            return true;
        if (x.compareTo(bstree.value) < 0)
        {
            // Recursively look in left subtree
            return contains(x, bstree.left);
        }
        else
        { 
            // Recursively look in right subtree
            return contains(x, bstree.right);
        }
    }
    
    /** 
       The remove method removes a value from the 
       binary search tree. 
       @returns true if x was removed, 
		 false if x not found.
    */
    
    public boolean remove(String x)
    {
        RemovalResult result = remove(root, x);
        if (result == null)
            return false;
        else 
        {
            root = result.tree;
            return true;
        }        
    }
   
    /**
       This remove method removes a value a 
		 from a binary search tree and returns the 
       removed node and the remaining tree wrapped 
       in a RemovalResult object.
       @param bTree The binary search tree.
       @param x The value to be removed.
       @return null if x is not found in bTree.
    */

    private RemovalResult remove(Node bTree, String x)
    {
       if (bTree == null)
           return null;  
       if (x.compareTo(bTree.value) < 0)
       {
           // Remove x from the left subtree
           RemovalResult result = remove(bTree.left, x);
           if (result == null) 
               return null;
           bTree.left = result.tree;
           result.tree = bTree;
           return result;           
       }
       if (x.compareTo(bTree.value) > 0) 
       {
           // Remove x from the right subtree
           RemovalResult result = remove(bTree.right, x);
           if (result == null) 
               return null;
           bTree.right = result.tree;
           result.tree = bTree;
           return result;            
       }
       // x is in this root node      
       // Is it a leaf? 
       if (bTree.right == null && bTree.left == null) 
           return new RemovalResult(bTree, null);
       
       // Does the node have two children?
       if (bTree.right != null && bTree.left != null)
       {
          // Remove largest node in left subtree and 
          // make it the root of the remaining tree
          RemovalResult remResult
			              = removeLargest(bTree.left);
          Node newRoot = remResult.node;
          newRoot.left = remResult.tree;
          newRoot.right = bTree.right;
          
          // Prepare the result to be returned
          bTree.left = null;
          bTree.right = null;
          return new RemovalResult(bTree, newRoot);              
       }
      // The node has one child
      Node node = bTree;
      Node tree;  
      if (bTree.left != null)
          tree = bTree.left;
      else
          tree = bTree.right;
      node.left = null;
      node.right = null;
      return new RemovalResult(node, tree);                  
    }
    
    /**
       The removeLargest method removes the 
		 largest node from a binary search tree.
       @param bTree: The binary search tree.
       @return  The result of removing the 
		 largest node.
    */
    
    private RemovalResult removeLargest(Node bTree)
    {
        if (bTree == null) 
           return null;        
        if (bTree.right == null)
        {
            // Root is the largest node
            Node tree = bTree.left;
            bTree.left = null;
            return new RemovalResult(bTree, tree);            
        }
        else
        {
            // Remove the largest node from the 
			   // right subtree
            RemovalResult remResult = 
				           removeLargest(bTree.right);
            bTree.right = remResult.tree;
            remResult.tree = bTree;
            return remResult;            
        }
    }
    
    /**
       This private inorder method recursively 
       traverses a binary tree in inorder.
       @param btree The root of the tree to traverse.
    */
    
    private void inorder(Node btree)
    {     
        if (btree != null)
       {
         inorder(btree.left);
         System.out.print(btree.value + " ");
         inorder(btree.right);
       }
    } 
    
    /**
       This inorder method is the public interface to
       the private inorder method. It calls the private 
       inorder method and passes it the root of the tree.
    */
    
    public void inorder()
    {
        inorder(root);
    }
    
}