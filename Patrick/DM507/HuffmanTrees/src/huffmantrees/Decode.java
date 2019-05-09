package huffmantrees;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decode {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        FileInputStream inFile = new FileInputStream(args[0]);
        BitInputStream reader = new BitInputStream(inFile);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(args[1]));

        int[] occurences = new int[256];

        for (int i = 0; i < occurences.length; i++) {
            occurences[i] = reader.readInt();
        }

        HuffmanTree huffTree = new HuffmanTree(occurences);
        Element root = huffTree.getRoot();
        Node currentNode = (Node) root.getData();

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

    }
}
