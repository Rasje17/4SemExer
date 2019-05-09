package huffmantrees;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        // initiating input and output streams
        FileInputStream inFile = new FileInputStream("C://users//pvies//desktop//huffmantrees_nopackages//test1.txt");
        BitInputStream reader = new BitInputStream(inFile);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("C://users//pvies//desktop//huffmantrees_nopackages//iwannasee.jpg"));

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
        for (int i = 0; i < root.getKey(); i++) {
            while (var != -1) {
                if (currentNode.getLeft() != null || currentNode.getRight() != null) {
                    if (var == 0) {
                        currentNode = currentNode.getLeft();
                        var = reader.readBit();
                    } else if (var == 1) {
                        currentNode = currentNode.getRight();
                        var = reader.readBit();
                    }
                } else {
                    output.write(currentNode.getByte());
                    currentNode = (Node) root.getData();
                    var = reader.readBit();
                    break;
                }
            }
        }
        output.flush();

        output.close();
        reader.close();
    }
}
