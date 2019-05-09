import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Encode {

    public static void main(String[] args) throws IOException {
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(args[0]));
        int[] occurencesArray = new int[256];

        int var = reader.read();
        while (var != -1) {
            occurencesArray[var]++;
            var = reader.read();
        }

        HuffmanTree huffTree = new HuffmanTree(occurencesArray);

        OutputStream outStream = new FileOutputStream(args[1]);
        BitOutputStream output = new BitOutputStream(outStream);

        for (int i : occurencesArray) {
            output.writeInt(i);
        }

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
