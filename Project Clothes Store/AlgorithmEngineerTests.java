// --== CS400 Project One File Header ==--
// Name: Matej Popovski
// CSL Username: matej
// Email: popovski@wisc.edu
// Lecture #: 002 - 2:30

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlgorithmEngineerTests {
    protected RedBlackTree<Integer> redBlack_Tree = null;
    @BeforeEach
    public void createInstance() {
        redBlack_Tree = new RedBlackTree<Integer>();
    }


    /**
     * Tests the non edge cases cases for the remove method of the RedBlackTree class that do not involve a double black (Whitebox)
     */
    @Test
    public void test1(){
        redBlack_Tree.insert(7);
        redBlack_Tree.insert(8);
        redBlack_Tree.insert(6);
        redBlack_Tree.insert(9);
        assertEquals(9, redBlack_Tree.remove(9));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(7, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(8, redBlack_Tree.root.rightChild.data);
        assertEquals(7, redBlack_Tree.root.rightChild.parent.data);
        assertEquals(1, redBlack_Tree.root.rightChild.blackHeight);
        assertEquals(6, redBlack_Tree.root.leftChild.data);
        assertEquals(7, redBlack_Tree.root.leftChild.parent.data);
        assertEquals(1, redBlack_Tree.root.leftChild.blackHeight);

        //Case 1: removing node with one child (meaning node is black and child is red)

        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(6);
        redBlack_Tree.insert(5);
        redBlack_Tree.insert(7);
        redBlack_Tree.insert(8);
        assertEquals(7, redBlack_Tree.remove(7));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(6, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(5, redBlack_Tree.root.leftChild.data);
        assertEquals(6, redBlack_Tree.root.leftChild.parent.data);
        assertEquals(1, redBlack_Tree.root.leftChild.blackHeight);
        assertEquals(8, redBlack_Tree.root.rightChild.data);
        assertEquals(6, redBlack_Tree.root.rightChild.parent.data);
        assertEquals(1, redBlack_Tree.root.rightChild.blackHeight);

        //Case 2: removing node with two children (Whitebox, should replace node's data with succerssor's data,

        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(6);
        redBlack_Tree.insert(8);
        redBlack_Tree.insert(5);
        redBlack_Tree.insert(7);
        redBlack_Tree.insert(9);

        assertEquals(8, redBlack_Tree.remove(8));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(6, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(5, redBlack_Tree.root.leftChild.data);
        assertEquals(6, redBlack_Tree.root.leftChild.parent.data);
        assertEquals(1, redBlack_Tree.root.leftChild.blackHeight);
        assertEquals(9, redBlack_Tree.root.rightChild.data);
        assertEquals(6, redBlack_Tree.root.rightChild.parent.data);
        assertEquals(1, redBlack_Tree.root.rightChild.blackHeight);
        assertEquals(7, redBlack_Tree.root.rightChild.leftChild.data);
        assertEquals(9, redBlack_Tree.root.rightChild.leftChild.parent.data);
        assertEquals(0, redBlack_Tree.root.rightChild.leftChild.blackHeight);

        //Case 3: Node is not in non-empty redBlack_Tree, should return null
        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(10);
        redBlack_Tree.insert(11);
        redBlack_Tree.insert(12);
        assertEquals(null, redBlack_Tree.remove(1));
    }

    /**
     * Tests edge cases of the remove method of the RedBlackTree class that don't involve double blacks
     */
    @Test
    public void test2(){
        //Case 0: node is root with no children, should just set the root to null
        redBlack_Tree.insert(15);
        assertEquals(15, redBlack_Tree.remove(15));
        assertEquals(null, redBlack_Tree.root);

        //Case 1: node is root with one child, should set root to the child
        ////////////////////////////////////////////////////////////////////////////////////
        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(75);
        redBlack_Tree.insert(76);
        assertEquals(76, redBlack_Tree.remove(76));
        assertEquals(75, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);

        //Case 2: node is root with 2 children, should replace root's data with the successor, then recursivly remove the successor
        ////////////////////////////////////////////////////////////////////////////////////
        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(85);
        redBlack_Tree.insert(86);
        redBlack_Tree.insert(84);
        assertEquals(85, redBlack_Tree.remove(85));
        assertEquals(86, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(84, redBlack_Tree.root.leftChild.data);
        assertEquals(86, redBlack_Tree.root.leftChild.parent.data);
        assertEquals(0, redBlack_Tree.root.leftChild.blackHeight);

        //Case 3: redBlack_Tree has no elements, should return null
        redBlack_Tree = new RedBlackTree<Integer>();
        assertEquals(null, redBlack_Tree.remove(895));

        //Case 4: null passed into remove, should return null
        assertEquals(null, redBlack_Tree.remove(null));
    }

    /**
     * Tests non-recursive cases for resolving a double black node on the remove method of the RedBlackTree class
     */
    @Test
    public void test3(){
    	
        //redBlack_Trees in this method are not valid RedBlackTrees, but do correctly test functionality
        //Case 0: double black's sibling is black with one or more red children
        //Case 0.1: sibling's only red child is a child of the same side as sibling

        RedBlackTree.Node<Integer> parent = redBlack_Tree.root = new RedBlackTree.Node<Integer>(80);
        parent.blackHeight = 1;
        RedBlackTree.Node<Integer> toRemove = parent.leftChild = new RedBlackTree.Node<Integer>(70);
        toRemove.blackHeight = 1;
        toRemove.parent = parent;
        RedBlackTree.Node<Integer> sibling = parent.rightChild = new RedBlackTree.Node<Integer>(100);
        sibling.blackHeight = 1;
        sibling.parent = parent;
        RedBlackTree.Node<Integer> nephew = sibling.rightChild = new RedBlackTree.Node<Integer>(110);
        nephew.parent = sibling;

        assertEquals(70, redBlack_Tree.remove(70));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(false, redBlack_Tree.contains(70));
        assertEquals(sibling, redBlack_Tree.root);
        assertEquals(parent, sibling.leftChild);
        assertEquals(sibling, parent.parent);
        assertEquals(nephew, sibling.rightChild);
        assertEquals(sibling, nephew.parent);
        assertEquals(1, sibling.blackHeight);
        assertEquals(1, parent.blackHeight);
        assertEquals(1, nephew.blackHeight);

        //Case 0.2: sibling's only red child is a child of the opposite side as sibling

        parent = redBlack_Tree.root = new RedBlackTree.Node<Integer>(80);
        parent.blackHeight = 1;
        toRemove = parent.leftChild = new RedBlackTree.Node<Integer>(70);
        toRemove.parent = parent;
        toRemove.blackHeight = 1;
        sibling = parent.rightChild = new RedBlackTree.Node<Integer>(100);
        sibling.blackHeight = 1;
        sibling.parent = parent;
        nephew = sibling.leftChild = new RedBlackTree.Node<Integer>(90);
        nephew.parent = sibling;

        assertEquals(70, redBlack_Tree.remove(70));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(false, redBlack_Tree.contains(70));
        assertEquals(nephew, redBlack_Tree.root);
        assertEquals(parent, nephew.leftChild);
        assertEquals(nephew, parent.parent);
        assertEquals(sibling, nephew.rightChild);
        assertEquals(nephew, sibling.parent);
        assertEquals(1, sibling.blackHeight);
        assertEquals(1, parent.blackHeight);
        assertEquals(1, nephew.blackHeight);

        //Case 0.3: sibling has 2 red children

        parent = redBlack_Tree.root = new RedBlackTree.Node<Integer>(81);
        parent.blackHeight = 1;
        toRemove = parent.leftChild = new RedBlackTree.Node<Integer>(71);
        toRemove.blackHeight = 1;
        toRemove.parent = parent;
        sibling = parent.rightChild = new RedBlackTree.Node<Integer>(101);
        sibling.blackHeight = 1;
        sibling.parent = parent;
        RedBlackTree.Node<Integer> innerNephew = sibling.leftChild = new RedBlackTree.Node<Integer>(91);
        nephew.parent = sibling;
        RedBlackTree.Node<Integer> outerNephew = sibling.rightChild = new RedBlackTree.Node<Integer>(111);
        outerNephew.parent = sibling;

        assertEquals(71, redBlack_Tree.remove(71));
         //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(false, redBlack_Tree.contains(71));
        assertEquals(sibling, redBlack_Tree.root);
        assertEquals(parent, sibling.leftChild);
        assertEquals(sibling, parent.parent);
        assertEquals(outerNephew, sibling.rightChild);
        assertEquals(sibling, outerNephew.parent);
        assertEquals(innerNephew, parent.rightChild);
        assertEquals(parent, innerNephew.parent);
        assertEquals(1, sibling.blackHeight);
        assertEquals(1, parent.blackHeight);
        assertEquals(0, innerNephew.blackHeight);
        assertEquals(1, outerNephew.blackHeight);

        //Case 1: double black's sibling is black with no red children
        //Case 1.1: parent is root, should just change parent's black height from 2 to 1

        parent = redBlack_Tree.root = new RedBlackTree.Node<Integer>(81);
        parent.blackHeight = 1;
        toRemove = parent.leftChild = new RedBlackTree.Node<Integer>(71);
        toRemove.blackHeight = 1;
        toRemove.parent = parent;
        sibling = parent.rightChild = new RedBlackTree.Node<Integer>(101);
        sibling.blackHeight = 1;
        sibling.parent = parent;
        innerNephew = sibling.leftChild = new RedBlackTree.Node<Integer>(91);
        innerNephew.parent = sibling;
        innerNephew.blackHeight = 1;
        outerNephew = sibling.rightChild = new RedBlackTree.Node<Integer>(111);
        outerNephew.parent = sibling;
        outerNephew.blackHeight = 1;

        assertEquals(71, redBlack_Tree.remove(71));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(false, redBlack_Tree.contains(71));
        assertEquals(parent, redBlack_Tree.root);
        assertEquals(sibling, parent.rightChild);
        assertEquals(parent, sibling.parent);
        assertEquals(innerNephew, sibling.leftChild);
        assertEquals(sibling, innerNephew.parent);
        assertEquals(outerNephew, sibling.rightChild);
        assertEquals(sibling, outerNephew.parent);
        assertEquals(1, parent.blackHeight);
        assertEquals(0, sibling.blackHeight);
        assertEquals(1, sibling.leftChild.blackHeight);
        assertEquals(1, sibling.rightChild.blackHeight);

        //Case 1.2: parent is red, meaning parent's black height just ends as 1 and there is no new double black to resolve

        redBlack_Tree.root = new RedBlackTree.Node<Integer>(61);
        redBlack_Tree.root.blackHeight = 1;
        parent = redBlack_Tree.root.rightChild = new RedBlackTree.Node<Integer>(81);
        toRemove = parent.leftChild = new RedBlackTree.Node<Integer>(71);
        toRemove.blackHeight = 1;
        toRemove.parent = parent;
        sibling = parent.rightChild = new RedBlackTree.Node<Integer>(101);
        sibling.blackHeight = 1;
        sibling.parent = parent;
        innerNephew = sibling.leftChild = new RedBlackTree.Node<Integer>(91);
        innerNephew.parent = sibling;
        innerNephew.blackHeight = 1;
        outerNephew = sibling.rightChild = new RedBlackTree.Node<Integer>(111);
        outerNephew.parent = sibling;
        outerNephew.blackHeight = 1;

        assertEquals(71, redBlack_Tree.remove(71));
        //verify the redBlack_Tree matches the above, including parent and child references, as well as black heights
        assertEquals(false, redBlack_Tree.contains(71));
        assertEquals(parent, redBlack_Tree.root.rightChild);
        assertEquals(sibling, parent.rightChild);
        assertEquals(parent, sibling.parent);
        assertEquals(innerNephew, sibling.leftChild);
        assertEquals(sibling, innerNephew.parent);
        assertEquals(outerNephew, sibling.rightChild);
        assertEquals(sibling, outerNephew.parent);
        assertEquals(1, parent.blackHeight);
        assertEquals(0, sibling.blackHeight);
        assertEquals(1, sibling.leftChild.blackHeight);
        assertEquals(1, sibling.rightChild.blackHeight);

    }

    /**
     * Tests recursive double black situations for the remove method of the RedBlackTree class
     */
    @Test
    public void test4(){

        redBlack_Tree.insert(53);
        redBlack_Tree.insert(43);
        redBlack_Tree.insert(73);
        redBlack_Tree.insert(33);
        redBlack_Tree.insert(63);
        redBlack_Tree.insert(93);
        redBlack_Tree.insert(83);
        redBlack_Tree.insert(103);
        redBlack_Tree.root.rightChild.blackHeight = 1;
        redBlack_Tree.root.rightChild.rightChild.leftChild.blackHeight = 1;
        redBlack_Tree.root.rightChild.rightChild.rightChild.blackHeight = 1;


        assertEquals(63, redBlack_Tree.remove(63));
        //only going to check a few spots since this is a very large redBlack_Tree
        assertEquals(false, redBlack_Tree.contains(63));
        assertEquals(43, redBlack_Tree.root.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(33, redBlack_Tree.root.leftChild.data);
        assertEquals(1, redBlack_Tree.root.leftChild.blackHeight);
        assertEquals(93, redBlack_Tree.root.rightChild.rightChild.rightChild.data);
        assertEquals(0, redBlack_Tree.root.rightChild.rightChild.rightChild.blackHeight);

        //Case 1: double black's sibling is red
        //Case I will use to test this will end up in test4's Case 1.2 after recursing, as shown below

        redBlack_Tree = new RedBlackTree<Integer>();
        redBlack_Tree.insert(53);
        redBlack_Tree.insert(43);
        redBlack_Tree.insert(73);
        redBlack_Tree.insert(63);
        redBlack_Tree.insert(83);
        redBlack_Tree.root.rightChild.blackHeight = 0;
        redBlack_Tree.root.rightChild.leftChild.blackHeight = 1;
        redBlack_Tree.root.rightChild.rightChild.blackHeight = 1;

        assertEquals(43, redBlack_Tree.remove(43));
        //check to make sure child references, parent references, and black heights match the above
        assertEquals(73, redBlack_Tree.root.data);
        assertEquals(53, redBlack_Tree.root.leftChild.data);
        assertEquals(73, redBlack_Tree.root.leftChild.parent.data);
        assertEquals(63, redBlack_Tree.root.leftChild.rightChild.data);
        assertEquals(53, redBlack_Tree.root.leftChild.rightChild.parent.data);
        assertEquals(83, redBlack_Tree.root.rightChild.data);
        assertEquals(73, redBlack_Tree.root.rightChild.parent.data);
        assertEquals(1, redBlack_Tree.root.blackHeight);
        assertEquals(1, redBlack_Tree.root.leftChild.blackHeight);
        assertEquals(1, redBlack_Tree.root.rightChild.blackHeight);
        assertEquals(0, redBlack_Tree.root.leftChild.rightChild.blackHeight);
    }



    // Tests from last week

    @Test
    public void testRightRedSibling() {
      RedBlackTree<Integer> redBlack_Tree = new RedBlackTree<Integer>();

      redBlack_Tree.insert(720);
      redBlack_Tree.insert(718);
      redBlack_Tree.insert(726);
      redBlack_Tree.root.leftChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 1;
      redBlack_Tree.insert(722);
      redBlack_Tree.root.rightChild.leftChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 0;
      redBlack_Tree.insert(730);

      assertEquals(redBlack_Tree.toLevelOrderString(), "[ 726, 720, 730, 718, 722 ]");
      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.leftChild.leftChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.rightChild.blackHeight, 1);

      redBlack_Tree = new RedBlackTree<Integer>();

      redBlack_Tree.insert(773);
      redBlack_Tree.insert(723);
      redBlack_Tree.insert(797);
      redBlack_Tree.root.leftChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 1;
      redBlack_Tree.insert(805);
      redBlack_Tree.root.rightChild.rightChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 0;
      redBlack_Tree.insert(786);

      assertEquals(redBlack_Tree.toLevelOrderString(), "[ 786, 773, 797, 723, 805 ]");
      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.leftChild.leftChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.rightChild.rightChild.blackHeight, 1);

    }

    @Test
    public void testLeftRedSibling() {
      redBlack_Tree.insert(11);
      redBlack_Tree.insert(6);
      redBlack_Tree.insert(16);
      redBlack_Tree.root.leftChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 1;
      redBlack_Tree.insert(9);
      redBlack_Tree.root.leftChild.rightChild.blackHeight = 1;
      redBlack_Tree.root.leftChild.blackHeight = 0;
      redBlack_Tree.insert(4);


      assertEquals(redBlack_Tree.toLevelOrderString(), "[ 6, 4, 11, 9, 16 ]");
      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.leftChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.rightChild.rightChild.blackHeight, 1);

      redBlack_Tree = new RedBlackTree<Integer>();

      redBlack_Tree.insert(103);
      redBlack_Tree.insert(39);
      redBlack_Tree.insert(129);
      redBlack_Tree.root.leftChild.blackHeight = 1;
      redBlack_Tree.root.rightChild.blackHeight = 1;
      redBlack_Tree.insert(16);
      redBlack_Tree.root.leftChild.leftChild.blackHeight = 1;
      redBlack_Tree.root.leftChild.blackHeight = 0;
      redBlack_Tree.insert(85);

      assertEquals(redBlack_Tree.toLevelOrderString(), "[ 85, 39, 103, 16, 129 ]");
      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.leftChild.leftChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.rightChild.rightChild.blackHeight, 1);
    }

        @Test
    public void testRecolouring() {
      redBlack_Tree.insert(20);
      redBlack_Tree.insert(8);
      redBlack_Tree.insert(35);
      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 0);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 0);

      redBlack_Tree.insert(4);
      redBlack_Tree.insert(55);
      redBlack_Tree.insert(3);
      redBlack_Tree.insert(51);


      assertEquals(redBlack_Tree.root.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.rightChild.blackHeight, 1);
      assertEquals(redBlack_Tree.root.leftChild.leftChild.blackHeight, 0);
    }



// OTHER TESTS












}