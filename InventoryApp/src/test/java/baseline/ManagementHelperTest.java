package baseline;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagementHelperTest {

    @Test
    void nameValid() {
        ManagementHelper helper = new ManagementHelper();

        String testString1 = "a";
        String testString2 = "apple";

        boolean expected1 = false;
        boolean expected2 = true;
        boolean actual1 = helper.nameValid(testString1);
        boolean actual2 = helper.nameValid(testString2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void valueValid() {
        ManagementHelper helper = new ManagementHelper();

        String testString1 = "a";
        String testString2 = "10";

        boolean expected1 = false;
        boolean expected2 = true;
        boolean actual1 = helper.valueValid(testString1);
        boolean actual2 = helper.valueValid(testString2);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    void serialNumValid() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();

        testItems.add(new Item("A-123-456-789", "name", 20));
        testItems.add(new Item("Z-123-456-789", "name", 50));

        String testString1 = "aaaaaaaaaaaaa";
        String testString2 = "a-ff3-34g-5fs";
        String testString3 = "A-123-456-789";

        boolean expected1 = false;
        boolean expected2 = true;
        boolean expected3 = false;
        boolean actual1 = helper.serialNumValid(testItems, testString1);
        boolean actual2 = helper.serialNumValid(testItems, testString2);
        boolean actual3 = helper.serialNumValid(testItems, testString3);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void search() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> searchItems1 = new ArrayList<>();
        List<Item> searchItems2 = new ArrayList<>();
        List<Item> searchItems3 = new ArrayList<>();

        testItems.add(new Item("A-123-456-789", "name", 20));
        testItems.add(new Item("Z-123-456-789", "name", 50));
        testItems.add(new Item("F-123-456-789", "gabe", 100));
        testItems.add(new Item("D-123-456-789", "john", 200));

        String testString1 = "john";
        String testString2 = "name";
        String testString3 = "abby";

        int expected1 = 1;
        int expected2 = 2;
        int expected3 = 0;

        searchItems1 = helper.search(testItems, testString1);
        searchItems2 = helper.search(testItems, testString2);
        searchItems3 = helper.search(testItems, testString3);

        int actual1 = searchItems1.size();
        int actual2 = searchItems2.size();
        int actual3 = searchItems3.size();

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void sortByName() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> sortedItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));
        testItems.add(new Item("A-123-456-789", "apple", 20));
        testItems.add(new Item("Z-234-456-789", "cat", 50));
        testItems.add(new Item("Z-123-456-789", "banana", 50));

        String testString1 = "apple";
        String testString2 = "banana";
        String testString3 = "cat";
        String testString4 = "dog";

        sortedItems = helper.sortByName(testItems);

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean expected4 = true;
        boolean actual1 = sortedItems.get(0).getName().equals(testString1);
        boolean actual2 = sortedItems.get(1).getName().equals(testString2);
        boolean actual3 = sortedItems.get(2).getName().equals(testString3);
        boolean actual4 = sortedItems.get(3).getName().equals(testString4);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);
    }

    @Test
    void sortByValue() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> sortedItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));
        testItems.add(new Item("A-123-456-789", "apple", 20));
        testItems.add(new Item("Z-234-456-789", "cat", 100));
        testItems.add(new Item("Z-123-456-789", "banana", 10000));

        double testNum1 = 20;
        double testNum2 = 50;
        double testNum3 = 100;
        double testNum4 = 10000;

        sortedItems = helper.sortByValue(testItems);

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean expected4 = true;
        boolean actual1 = sortedItems.get(0).getValue() == testNum1;
        boolean actual2 = sortedItems.get(1).getValue() == testNum2;
        boolean actual3 = sortedItems.get(2).getValue() == testNum3;
        boolean actual4 = sortedItems.get(3).getValue() == testNum4;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);
    }

    @Test
    void sortBySerial() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> sortedItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));
        testItems.add(new Item("A-123-456-789", "apple", 20));
        testItems.add(new Item("Z-234-456-789", "cat", 50));
        testItems.add(new Item("Z-123-456-789", "banana", 50));

        String testString1 = "A-123-456-789";
        String testString2 = "B-123-456-789";
        String testString3 = "Z-123-456-789";
        String testString4 = "Z-234-456-789";

        sortedItems = helper.sortBySerial(testItems);

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean expected4 = true;
        boolean actual1 = sortedItems.get(0).getSerialNumber().equals(testString1);
        boolean actual2 = sortedItems.get(1).getSerialNumber().equals(testString2);
        boolean actual3 = sortedItems.get(2).getSerialNumber().equals(testString3);
        boolean actual4 = sortedItems.get(3).getSerialNumber().equals(testString4);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
        assertEquals(expected4, actual4);
    }

    @Test
    void deleteAllItemsFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> newItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));
        testItems.add(new Item("A-123-456-789", "apple", 20));
        testItems.add(new Item("Z-234-456-789", "cat", 50));
        testItems.add(new Item("Z-123-456-789", "banana", 50));

        newItems = helper.deleteAllItemsFunction(testItems);

        boolean expected1 = true;
        boolean actual1 = newItems.isEmpty();

        assertEquals(expected1, actual1);
    }

    @Test
    void deleteItemFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();
        List<Item> newItems;

        testItems.add(new Item("B-123-456-789", "dog", 50));
        testItems.add(new Item("A-123-456-789", "apple", 20));
        testItems.add(new Item("Z-234-456-789", "cat", 50));
        testItems.add(new Item("Z-123-456-789", "banana", 50));

        String testString1 = "dog";
        String testString2 = "apple";
        String testString3 = "banana";

        newItems = helper.deleteItemFunction(testItems.get(2), testItems);

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean actual1 = newItems.get(0).getName().equals(testString1);
        boolean actual2 = newItems.get(1).getName().equals(testString2);
        boolean actual3 = newItems.get(2).getName().equals(testString3);

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void addItemFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();

        String name = "dog";
        String serial = "a-123-456-789";
        double value = 30;

        testItems = helper.addItemFunction(testItems, serial, name, value);

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean actual1 = testItems.get(0).getName().equals(name);
        boolean actual2 = testItems.get(0).getSerialNumber().equals(serial);
        boolean actual3 = testItems.get(0).getValue() == value;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void editNameFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));

        String newName = "apple";
        testItems = helper.editNameFunction(testItems, testItems.get(0), newName);

        String testSerial = "B-123-456-789";
        double testValue = 50;

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean actual1 = testItems.get(0).getName().equals(newName);
        boolean actual2 = testItems.get(0).getSerialNumber().equals(testSerial);
        boolean actual3 = testItems.get(0).getValue() == testValue;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void editSerialNumberFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));

        String newSerial = "A-123-456-789";
        testItems = helper.editSerialNumberFunction(testItems, testItems.get(0), newSerial);

        String testName = "dog";
        double testValue = 50;

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean actual1 = testItems.get(0).getName().equals(testName);
        boolean actual2 = testItems.get(0).getSerialNumber().equals(newSerial);
        boolean actual3 = testItems.get(0).getValue() == testValue;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }

    @Test
    void editValueFunction() {
        ManagementHelper helper = new ManagementHelper();
        List<Item> testItems = new ArrayList<>();

        testItems.add(new Item("B-123-456-789", "dog", 50));

        double newValue = 10;
        testItems = helper.editValueFunction(testItems, testItems.get(0), newValue);

        String testSerial = "B-123-456-789";
        String testName = "dog";

        boolean expected1 = true;
        boolean expected2 = true;
        boolean expected3 = true;
        boolean actual1 = testItems.get(0).getName().equals(testName);
        boolean actual2 = testItems.get(0).getSerialNumber().equals(testSerial);
        boolean actual3 = testItems.get(0).getValue() == newValue;

        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertEquals(expected3, actual3);
    }
}