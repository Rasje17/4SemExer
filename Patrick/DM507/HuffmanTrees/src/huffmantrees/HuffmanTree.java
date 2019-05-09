package huffmantrees;

/**
 * @author Patrick Christoffersen - pachr16@student.sdu.dk
 * @author Rasmus Jensen - rasje17@student.sdu.dk
 */
public class HuffmanTree {
    
    private Element root;   // the root element used in the tree
    private int leaves; // the number of leves that will be in the tree
    private String[] hCodes;    //the huffmancodes for each of the indexes
    
    //Creating the tree bu reciving an array of occurences of each int index in the sorce material and generating a huffmantree from it and renerating the array of the huffman codes for each index
    public HuffmanTree(int[] occurences) {
        root = generateHuffmanTree(occurences);
        generateCodes();
    }
    
    /**
     * Method for generating the huffmantree, it usees a Minheap of Elements that contains a int key that reffers to the occurence from the arrau with the index saved in its value in a Node object.
     * the 2 lowest element are then extracted from the heap and thir nods are sat as childs on a new node that is wraped in a Element which key is the combined sum of the kys of the children, then the new element is added back into the heap
     * this continues until only one Element remains in the heap, this element is the root of the HuffmanTree.
     * @param occurences the array of acurrences of each index in the surce
     * @return returns the root element of the tree to store the tree and allow traversal of it
     */
    private Element generateHuffmanTree(int[] occurences) {
        leaves = occurences.length;
        PQHeap pq = new PQHeap(leaves); // a heap is used effectivly getting the element with the lowest key
        
        // this creates notes of all elements in the occurence array and ads them to the minheap as the object part of Elements with thir occurenc as keys
        for (int i = 0; i < leaves; i++) {
            Node node = new Node(i);
            Element element = new Element(occurences[i], node);
            pq.insert(element);
        }
        
        // Runs trhogh the heap and extracts the lower 2 elements to combine thir nodes into a new node and returns it as a new element
        for (int i = 0; i < leaves-1; i++) {
            Element ele1 = pq.extractMin();
            Element ele2 = pq.extractMin();
            
            Node newNode = new Node((Node) ele1.getData(), (Node) ele2.getData());
            Element newElement = new Element(ele1.getKey() + ele2.getKey(), newNode);
            
            pq.insert(newElement);
        }
        
        return pq.extractMin();
    }
    
    /**
     * Method that initiates the recursive traversal of the tree to generate the huffmancodes for each index
     */
    private void generateCodes() {
        hCodes = new String[leaves];
        String code = "";
        traversal((Node) root.getData(), code, null);
    }
    
    /**
     * recursive method that builds huffmancodes as it traverses down the tree, when it reaches the end of a branch it writes the  gathered code as a String in an array at the index of the leaves value
     * @param x the curent node
     * @param code the sting af the so far path
     * @param parent the parent node, used when reaching a nonexisting node to define its parent as a leave
     */
    private void traversal(Node x, String code, Node parent) {
        if (x != null) {
            traversal(x.getLeft(), code + '0', x);
            traversal(x.getRight(), code + '1', x);
        }
        else {
            hCodes[parent.getByte()] = code;
        }
    }
    
    public Element getRoot() {
        return this.root;
    }
    
    /**
     * return the Huffman code for a given value
     * @param index the value of a byte
     * @return 
     */
    public String encode(int index) {
        return hCodes[index];
    }
    
}
