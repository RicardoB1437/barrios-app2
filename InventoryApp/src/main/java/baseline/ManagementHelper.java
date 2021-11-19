package baseline;

import java.util.List;

public class ManagementHelper
{
    public boolean nameValid(String str)
    {
        //make sure the string is between 2 and 256 chars
    }

    public boolean valueValid(String str)
    {
        //make sure the string can be parsed in as double
        //make sure parsed number is greater than 0
        //if so then return true
    }

    public boolean serialNumValid(List<Item> items, String str)
    {
        //make sure the string follows the format A-XXX-XXX-XXX
        //basically make sure theres no number for A
        //make sure X isNumber or isLetter
        //loop through the list and see if it contains str
        //if passes all then return true
    }

    public List<Item> search(List<Item> items, String searchString)
    {
        //take the search string and loop through the list of items and locate the string
        //if found add the item to a new list
        //return the list
    }

    public List<Item> sortByName(List<Item> items)
    {

    }

    public List<Item> sortByValue(List<Item> items)
    {

    }

    public List<Item> sortBySerial(List<Item> items)
    {

    }
}
