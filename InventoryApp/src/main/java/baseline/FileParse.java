package baseline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParse
{
    public ArrayList<Item> loadFile(String fileName) throws RuntimeException
    {
        ArrayList<String> fileLines = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = "";
            while((line=reader.readLine())!=null)
            {
                fileLines.add(line);
            }
            reader.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("File not found", e);
        }
        return convertFileToList(fileLines);
    }

    private ArrayList<Item> convertFileToList(List<String> fileLines)
    {
        ArrayList<Item> itemList = new ArrayList<>();
        String s;
        for (int i=1;i<fileLines.size();i++)
        {
            s = fileLines.get(i);
            String[] arr = s.split("\t");
            String serialNumber = arr[0];

            String name = arr[1];

            String value = arr[2];
            double valueNum = Double.parseDouble(value);

            Item newItem = new Item(serialNumber, name, valueNum);
            itemList.add(newItem);
        }
        return itemList;
    }
}