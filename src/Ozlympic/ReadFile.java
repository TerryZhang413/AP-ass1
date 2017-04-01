package Ozlympic;

//-Yipeng
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
    /**
     * @date 19.3.2017
     * @author Yipeng.Zhang
     * @version 1.1
     * @Description read file from address and return data as
     *              ArrayList<String>
     */
    public ArrayList<String> readText(String address) throws IOException {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            // read file into buffer
            BufferedReader buffReader = new BufferedReader(
                    new FileReader(address));
            // read line from buffer one by one
            String data = buffReader.readLine();
            while (data != null) {
                // split data with " "
                String[] dataArray = data.split(" ");
                for (String element : dataArray) {
                    arrayList.add(element);
                }
                data = buffReader.readLine();
            }
            buffReader.close();
            return arrayList;
        } catch (Exception e) {
            return null;
        }
    }

}
