/**
 *
 * @author Patrick Christoffersen - pachr16@student.sdu.dk
 * @author Rasmus Jensen - rasje17@student.sdu.dk
 */
public class Node {
    
    private int value;
    private Node leftChild = null, rightChild = null;
    
    public Node(int value) {
        this.value = value;
    }
    
    public Node(Node leftChild, Node rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    public Node getLeft(){
        return this.leftChild;
    }

    public Node getRight(){
        return this.rightChild;
    }  
    
    public int getByte(){
        return this.value;
    }
    
    public void setLeft(Node newLeft){
        this.leftChild = newLeft;
    }
    
    public void setRight(Node newRight){
        this.rightChild = newRight;
    }
    
}
