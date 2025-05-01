
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MockD implements Dictionary{
private final Set<String>words=new HashSet<>();

    public MockD(){

        try {
            File myObj = new File("D:\\ProgramareAvansata\\Lab7\\Resources\\Dictionary");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                words.add(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public boolean isWord(String str) {

        return words.contains(str.toLowerCase());
    }
}
