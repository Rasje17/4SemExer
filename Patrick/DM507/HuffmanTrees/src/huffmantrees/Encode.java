package huffmantrees;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Encode {

    public static void main(String[] args) throws IOException {
        // initiating the input stream and the array for counting occurences
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream("C://users//pvies//desktop//huffmantrees_nopackages//AFLEVERING.docx"));
        // we know there are 256 possible bytes, so that is the length of the occurence-array
        int[] occurencesArray = new int[256];

        // place a "bookmark" for the reader, so that we can get all the information again later
        reader.mark(Integer.MAX_VALUE);
        // read the next byte of information.
        int var = reader.read();
        // while there is still more data to be read (i.e. reader is not returning -1), count up the occurence of the next found byte
        while (var != -1) {
            occurencesArray[var]++;
            var = reader.read();
        }

        // generate a huffman tree with the loaded occurences
        HuffmanTree huffTree = new HuffmanTree(occurencesArray);

        // initiating output stream
        OutputStream outStream = new FileOutputStream("C://users//pvies//desktop//huffmantrees_nopackages//test1.txt");
        BitOutputStream output = new BitOutputStream(outStream);

        // first, for every possible value of a byte, output the occurence of each one in the input file.
        for (int i : occurencesArray) {
            // using writeInt because no matter what the amount of occurences was, it has to be given with 32 bits.
            output.writeInt(i);
        }

        // reset the reader to the previously set mark, so that we can read the input again.
        reader.reset();
        // begin reading again
        var = reader.read();
        // while there is more to read, write the numerical value of each individual character in the huffman code for the byte that is encoded
        while (var != -1) {
            // the encode method gets the huffman code for the given byte. This code is then split to an array of chars, to facilitate iterating
            for (char bit : huffTree.encode(var).toCharArray()) {
                output.writeBit(Character.getNumericValue(bit));
            }
            var = reader.read();
        }

        // close resources
        reader.close();
        output.close();
    }

}
