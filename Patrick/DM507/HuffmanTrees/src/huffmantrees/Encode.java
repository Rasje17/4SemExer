package huffmantrees;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Encode {

    public static void main(String[] args) throws IOException {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("C://users//pvies//desktop//huffmantrees_nopackages//dialloovershaq.jpg"));
        int[] occurencesArray = new int[256];

        reader.mark(Integer.MAX_VALUE);
        int var = reader.read();
        while (var != -1) {
            occurencesArray[var]++;
            var = reader.read();
        }

        HuffmanTree huffTree = new HuffmanTree(occurencesArray);

        OutputStream outStream = new FileOutputStream("C://users//pvies//desktop//huffmantrees_nopackages//test1.txt");
        BitOutputStream output = new BitOutputStream(outStream);

        for (int i : occurencesArray) {
            output.writeInt(i);
        }

        reader.reset();
        var = reader.read();
        while (var != -1) {
            for (char bit : huffTree.encode(var).toCharArray()) {
                output.writeBit(Character.getNumericValue(bit));
            }
            var = reader.read();
        }

        reader.close();
        output.close();
    }

}
