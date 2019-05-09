package huffmantrees;

public class HuffmanTree {
    
    private Element root;
    private int leaves;
    private String[] hCodes;
    
    public HuffmanTree(int[] occurences) {
        root = generateHuffmanTree(occurences);
        generateCodes();
    }
    
    private Element generateHuffmanTree(int[] occurences) {
        leaves = occurences.length;
        PQHeap pq = new PQHeap(leaves);
        
        for (int i = 0; i < leaves; i++) {
            Node node = new Node(i);
            Element element = new Element(occurences[i], node);
            pq.insert(element);
        }
        
        for (int i = 0; i < leaves-1; i++) {
            Element ele1 = pq.extractMin();
            Element ele2 = pq.extractMin();
            
            Node newNode = new Node((Node) ele1.getData(), (Node) ele2.getData());
            Element newElement = new Element(ele1.getKey() + ele2.getKey(), newNode);
            
            pq.insert(newElement);
        }
        
        return pq.extractMin();
    }
    
    private void generateCodes() {
        hCodes = new String[leaves];
        String code = "";
        traversal((Node) root.getData(), code, null);
    }
    
    private void traversal(Node x, String code, Node parent) {
        if (x != null) {
            System.out.println(code);
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
    
    public String encode(int index) {
        System.out.println("We're encoding! This is the result: " + hCodes[index]);
        return hCodes[index];
    }
    
}
