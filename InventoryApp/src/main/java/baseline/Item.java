package baseline;

import java.time.LocalDate;

public class Item
{
    public String name;
    public String serialNumber;
    public double value;

    public Item(String serialNumber, String name, double value)
    {
        this.name = name;
        this.serialNumber = serialNumber;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public double getValue() {
        return value;
    }

    public String toString()
    {
        return String.format("Name: %s\nSerial Number: %s\nValue: %s", getName(), getSerialNumber(), getValue());
    }
}
