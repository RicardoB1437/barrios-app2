package baseline;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class FileSave
{
    public void saveFile(String fileName, List<Item> items) throws IOException
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);

            writer.write("Serial Number    Name    Value\n");

            for(int i=0;i<items.size();i++)
            {
                String name = items.get(i).getName();
                String serialNumber = items.get(i).getSerialNumber();
                String value = Double.toString(items.get(i).getValue());
                writer.write(serialNumber + "    " + name + "    " + value + "\n");
            }
            writer.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("File not created", e);
        }
    }
}