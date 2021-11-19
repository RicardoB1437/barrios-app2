package baseline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManagementHelper
{
    public boolean nameValid(String str)
    {
        //make sure the string is between 2 and 256 chars
        return str.length() >= 2 && str.length() <= 256;
    }

    public boolean valueValid(String str)
    {
        try
        {
            double num = Double.parseDouble(str);
            return num >= 0;
        }
        catch (NumberFormatException ex)
        {
            return false;
        }
    }

    public boolean serialNumValid(List<Item> items, String str)
    {
        int flag = 0;

        if(str.length() != 13)
        {
            return false;
        }
        char[] charArray = str.toCharArray();
        //make sure the string follows the format A-XXX-XXX-XXX
        //checking manually since i dont understand regex (im sorry)
        if(Character.isLetter(charArray[0]))
            flag++;
        if(charArray[1] == '-')
            flag++;
        if(Character.isLetter(charArray[2]) || Character.isDigit(charArray[2]))
            flag++;
        if(Character.isLetter(charArray[3]) || Character.isDigit(charArray[3]))
            flag++;
        if(Character.isLetter(charArray[4]) || Character.isDigit(charArray[4]))
            flag++;
        if(charArray[5] == '-')
            flag++;
        if(Character.isLetter(charArray[6]) || Character.isDigit(charArray[6]))
            flag++;
        if(Character.isLetter(charArray[7]) || Character.isDigit(charArray[7]))
            flag++;
        if(Character.isLetter(charArray[8]) || Character.isDigit(charArray[8]))
            flag++;
        if(charArray[9] == '-')
            flag++;
        if(Character.isLetter(charArray[10]) || Character.isDigit(charArray[10]))
            flag++;
        if(Character.isLetter(charArray[11]) || Character.isDigit(charArray[11]))
            flag++;
        if(Character.isLetter(charArray[12]) || Character.isDigit(charArray[12]))
            flag++;

        //no check and make sure its a unique serial number
        int uniqueFlag = 0;
        if(!items.isEmpty())
        {
            for(int i=0;i<items.size();i++)
            {
                if(items.get(i).getSerialNumber().equals(str))
                    uniqueFlag++;
            }
        }

        //if passes all then return true
        if(flag == str.length() && uniqueFlag == 0)
            return true;
        else
            return false;
    }

    public List<Item> search(List<Item> items, String searchString)
    {
        List<Item> tempList = new ArrayList<>();
        //take the search string and loop through the list of items and locate the string
        //if found add the item to a new list
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getName().equals(searchString) || items.get(i).getSerialNumber().equals(searchString))
                tempList.add(items.get(i));
        }
        //return the list
        return tempList;
    }

    public List<Item> sortByName(List<Item> items)
    {
        items.sort(Comparator.comparing(Item::getName));
        return items;
    }

    public List<Item> sortByValue(List<Item> items)
    {
        items.sort(Comparator.comparing(Item::getValue));
        return items;
    }

    public List<Item> sortBySerial(List<Item> items)
    {
        items.sort(Comparator.comparing(Item::getSerialNumber));
        return items;
    }

    public List<Item> deleteAllItemsFunction(List<Item> items)
    {
        //simply remove items from list and then remove items from list view
        items.removeAll(items);
        return items;
    }
    public List<Item> deleteAllSearchItemsFunction(String searchString, List<Item> items)
    {
        //simply remove items from list and then remove items from list view
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getName().equals(searchString) || items.get(i).getSerialNumber().equals(searchString))
                items.remove(i);
        }
        return items;
    }

    public List<Item> deleteItemFunction(Item removeItem, List<Item> items)
    {
        items.remove(removeItem);
        return items;
    }

    public List<Item> addItemFunction(List<Item> items, String serialNum, String name, double value)
    {
        Item newItem = new Item(serialNum, name, value);
        items.add(newItem);
        return items;
    }

    public List<Item> editItemFunction(int index, List<Item> items, String serialNum, String name, double value)
    {
        Item replaceItem = new Item(serialNum, name, value);
        items.set(index, replaceItem);
        return items;
    }
}
