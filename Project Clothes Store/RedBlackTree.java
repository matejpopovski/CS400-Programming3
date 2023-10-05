// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import java.util.LinkedList;
import java.util.Iterator;

/**
 * Red-Black Tree implementation with a Node inner class for representing
 * the nodes of the tree. Currently, this implements a Binary Search Tree that
 * we will turn into a red black tree by modifying the insert functionality.
 * In this activity, we will start with implementing rotations for the binary
 * search tree insert algorithm. You can use this class' insert method to build
 * a regular binary search tree, and its toString method to display a level-order
 * traversal of the tree.
 */
public class RedBlackTree <T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent;
        public Node<T> leftChild;
        public Node<T> rightChild;

        public int blackHeight = 0;

        public Node(T data) { this.data = data; }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }
    }
    protected Node<T> root; 
    protected int size = 0; 

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    @Override
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) {
          root = newNode;
          size++;
          this.root.blackHeight = 1;
          return true;
          } 
        else{
            boolean returnValue = insertHelper(newNode,root); 
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            this.root.blackHeight = 1;
            return returnValue;
        }
    }
protected void enforceRBTreePropertiesAfterInsert(Node<T> node){

        if(node == this.root){
            return;

        }
        Node<T> parent = node.parent;
        if(parent.blackHeight > 0){
            return;
        }
        if(parent == this.root) {
            return;
        }

        Node<T> grandParent = node.parent.parent;
        Node<T> parentSibling;

        if(parent.isLeftChild()){
            parentSibling = grandParent.rightChild;
        }
        else {
            parentSibling = grandParent.leftChild;
        }

        if(parentSibling != null && parentSibling.blackHeight == 0) {
            parent.blackHeight = 1;
            parentSibling.blackHeight = 1;
            grandParent.blackHeight = 0;
            enforceRBTreePropertiesAfterInsert(grandParent);
            return;
        }
        if(node.isLeftChild() != parent.isLeftChild()){
            this.rotate(node, parent);
            enforceRBTreePropertiesAfterInsert(parent);
            return;
        }
        rotate(parent, grandParent);
        int tmp = grandParent.blackHeight;
        grandParent.blackHeight = parent.blackHeight;
        parent.blackHeight = tmp;
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        if(compare == 0) return false;
        else if(compare < 0) {
            if(subtree.leftChild == null) { 
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
            } else return insertHelper(newNode, subtree.leftChild);
        }

        else {
            if(subtree.rightChild == null) { 
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
      boolean leftChild = false;
      boolean rightChild = false;

      try {
        if (parent.leftChild.equals(child)) {
          leftChild = true;
        }
      }
      catch(NullPointerException e) {
      }

      try {
        if (parent.rightChild.equals(child)) {
          rightChild = true;
        }
      } catch(NullPointerException e) {

      }

      if(leftChild || rightChild) {

      }
      else {
        throw new IllegalArgumentException();
      }


      if(child.isLeftChild()) {
        parent.leftChild = child.rightChild;
        if(child.rightChild != null) {
          parent.rightChild.parent = parent;
        }
        if (parent.parent == null) {
          this.root = child;
        }
        else if (!parent.isLeftChild()){
          parent.parent.rightChild = child;
          if(parent.parent.rightChild != null) {
            parent.parent.rightChild.parent = parent.parent;
          }
        }
        else {
          parent.parent.leftChild = child;
          if(parent.parent.leftChild != null) {
            parent.parent.leftChild.parent = parent.parent;
          }
        }
        child.rightChild = parent;
        child.rightChild.parent = child;
      }
      else {
        parent.rightChild = child.leftChild;
        if(child.leftChild != null) {
          parent.rightChild.parent = parent;
        }

        if (parent.parent == null) {
          this.root = child;
        }
        else if(parent.isLeftChild()) {
          parent.parent.leftChild = child;
          if(parent.parent.leftChild != null) {
            parent.parent.leftChild.parent = parent.parent;
          }
        }
        else {
          parent.parent.rightChild = child;
          if(parent.parent.rightChild != null) {
            parent.parent.rightChild.parent = parent.parent;
          }
        }
        child.leftChild = parent;
        child.leftChild.parent = child;
      }

    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    @Override
    public boolean contains(T data) {
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                return containsHelper(data, subtree.rightChild);
            } else {
                return true;
            }
        }
    }
    /**
     * Removes the node with the given data from the tree (using compareTo, not ==)
     * @param dataToRemove data that a node must match to be removed
     */
    @Override
    public T remove(T dataToRemove) {
        Node<T> toRemove = searchNode(dataToRemove, root);
        if(toRemove == null) return null;
        return removeHelper(toRemove);
    }

    /**
     * @param dataMatch of the node to remove
     * @param nodeMatch currently being checked
     * @return the node that matched the given data with compareTo
     */
    protected Node<T> searchNode(T dataMatch, Node<T> nodeMatch){
        if(nodeMatch == null) return null;
        if(nodeMatch.data.compareTo(dataMatch) > 0) {
            if(nodeMatch.leftChild == null) return null;
            return searchNode(dataMatch, nodeMatch.leftChild);
        }
        if(nodeMatch.data.compareTo(dataMatch) < 0){
            if(nodeMatch.rightChild == null) return null;
            return searchNode(dataMatch, nodeMatch.rightChild);
        }
        return nodeMatch;
    }

    /**
     * Prepares the tree for removal of a black leaf node by resolving the double black node (sometime recursively)
     * @param db reference to the double black node
     */
    protected void resolveDoubleBlack(Node<T> db){
        Node<T> parent = db.parent;
        Node<T> sibling;
        if(db.isLeftChild()) sibling = parent.rightChild;
        else sibling = parent.leftChild;
        if(sibling.blackHeight == 0){
            rotate(sibling, parent);
            int tmp = parent.blackHeight;
            parent.blackHeight = sibling.blackHeight;
            sibling.blackHeight = tmp;
            resolveDoubleBlack(db);
            return;
        }
        if((sibling.leftChild == null || sibling.leftChild.blackHeight == 1)
            && (sibling.rightChild == null || sibling.rightChild.blackHeight == 1)){
                db.blackHeight--;
                sibling.blackHeight--;
                parent.blackHeight++;
                if(parent.blackHeight == 1) return;
                if(parent == root) {
                    root.blackHeight = 1;
                    return;
                }
                resolveDoubleBlack(parent);
                return;
        }
        if(sibling.isLeftChild() && (sibling.leftChild == null || sibling.leftChild.blackHeight == 1)){
            int tmp = sibling.blackHeight;
            sibling = sibling.parent;
            sibling.blackHeight = sibling.leftChild.blackHeight;
            sibling.leftChild.blackHeight = tmp;
            rotate(sibling.rightChild, sibling);
        } else if(!sibling.isLeftChild() && (sibling.rightChild == null || sibling.rightChild.blackHeight == 1)){
            rotate(sibling.leftChild, sibling);
            sibling = sibling.parent;
            int tmp = sibling.blackHeight;
            sibling.blackHeight = sibling.rightChild.blackHeight;
            sibling.rightChild.blackHeight = tmp;
        }

        db.blackHeight--;
        if(sibling.isLeftChild()) sibling.leftChild.blackHeight++;
        else sibling.rightChild.blackHeight++;

        rotate(sibling, parent);
        int tmp = sibling.blackHeight;
        sibling.blackHeight = parent.blackHeight;
        parent.blackHeight = tmp;
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }
    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * Main method to run tests. Comment out the lines for each test
     * to run them.
     * @param args
     */
    public static void main(String[] args) {
    }

	@Override
	public Iterator<T> levelOrderIterator() {
		LinkedList<Node<T>> q = new LinkedList<>();
      if(root != null) q.add(root);
      LinkedList<T> levelOrderList = new LinkedList<T>();
      while(!q.isEmpty()){
          Node<T> next = q.removeFirst();
          if(next.leftChild != null) q.add(next.leftChild);
          if(next.rightChild != null) q.add(next.rightChild);
          levelOrderList.add(next.data);
      }
      return levelOrderList.iterator();
	}

    protected void deleteNode(Node<T> nodeToRemove){
        if(nodeToRemove.isLeftChild()){
            nodeToRemove.parent.leftChild = null;
            return;
        }
        nodeToRemove.parent.rightChild = null;
    }
    
    /**
    * Helper for the remove method that removes the Node passed in
    * @param nodeToRemove to remove
    * @return the data of the removed node
    */
   protected T removeHelper(Node<T> nodeToRemove){

       if(nodeToRemove.leftChild == null && nodeToRemove.rightChild == null){
           if(nodeToRemove == root) {
               root = null;
               this.size--;
               return nodeToRemove.data;
           }
           if(nodeToRemove.blackHeight == 0){
               deleteNode(nodeToRemove);
               this.size--;
               return nodeToRemove.data;
           }
           nodeToRemove.blackHeight = 2;
           resolveDoubleBlack(nodeToRemove);
           deleteNode(nodeToRemove);
           this.size--;
           return nodeToRemove.data;
       }
       T data = nodeToRemove.data;
       if(nodeToRemove.rightChild == null){
           nodeToRemove.data = nodeToRemove.leftChild.data;
           deleteNode(nodeToRemove.leftChild);
           this.size--;
           return data;
       }
       if(nodeToRemove.leftChild == null){
           nodeToRemove.data = nodeToRemove.rightChild.data;
           deleteNode(nodeToRemove.rightChild);
           this.size--;
           return data;
       }
       Node<T> successor = nodeToRemove.rightChild;
       while(successor.leftChild != null){
           successor = successor.leftChild;
       }
       nodeToRemove.data = successor.data;
       removeHelper(successor);
       return data; // ovaa data kako/dali da ja preimenuvam?
   }
}