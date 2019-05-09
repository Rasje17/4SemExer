import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Patrick Christoffersen - pachr16@student.sdu.dk
 * @author Rasmus Jensen - rasje17@student.sdu.dk
 */
public class Decode {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // initiating input and output streams
        FileInputStream inFile = new FileInputStream(args[0]);
        BitInputStream reader = new BitInputStream(inFile);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[1]));

        // initiating the array for counting occurences of each byte - we know that there is 256 possibilities, so this is the length of the array
        int[] occurences = new int[256];

        // loading the occurences of each byte from the input stream into the previously instantiated array. The index is the value of the byte, and the value of the element is the amount of occurences
        for (int i = 0; i < occurences.length; i++) {
            occurences[i] = reader.readInt();
        }

        // generating a new huffman tree with the loaded occurences
        HuffmanTree huffTree = new HuffmanTree(occurences);
        
        // defining the root node of the huffman tree
        Element root = huffTree.getRoot();
        Node currentNode = (Node) root.getData();
        
        // reads the first bit of the encoded part of the input stream
        int var = reader.readBit();
        
        // the key of the root is the total amount of occurences of all the possible bytes. Therefore this loop iterates that many times, since that is the amount of bytes that needs to be printed
        for (int i = 0; i < root.getKey(); i++) {
            // if becomes -1, that means the end of the input file has been reached
            while (var != -1) {
                // checks if the current node has any children. If so, it is necessary to traverse further through the tree before writing the next byte, since the huffman code is incomplete
                if (currentNode.getLeft() != null || currentNode.getRight() != null) {
                    // if the next bit is a 0, go down the left leg of the tree.
                    if (var == 0) {
                        currentNode = currentNode.getLeft();
                        var = reader.readBit();
                    // likewise, if the next bit is a 1, continue to the right child.
                    } else if (var == 1) {
                        currentNode = currentNode.getRight();
                        var = reader.readBit();
                    }
                // if the current node is an end node (has no children), the huffman code for this byte is complete, and the value of the current node should be printed.
                } else {
                    output.write(currentNode.getByte());
                    // then reset the current node to the root for the next iteration
                    currentNode = (Node) root.getData();
                    var = reader.readBit();
                    // break from the current loop, as an end node has been reached
                    break;
                }
            }
        }
        // flush the output stream to write the buffered data
        output.flush();

        // close resources when done
        output.close();
        reader.close();
    }
}
