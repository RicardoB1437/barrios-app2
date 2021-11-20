package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryManagementPageController {

    @FXML
    private ToggleGroup SortGroup;
    @FXML
    private RadioButton nameSortButton;
    @FXML
    private RadioButton valueSortButton;
    @FXML
    private RadioButton serialSortButton;
    @FXML
    private Label errLabel;
    @FXML
    private ListView<Item> listView;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField serialNumTextField;
    @FXML
    private TextField valueTextField;

    private List<Item> itemList = new ArrayList<>();
    private List<Item> searchedItemList = new ArrayList<>();
    @FXML
    ObservableList<Item> observableItems = FXCollections.observableArrayList(itemList);

    @FXML
    private void addItem(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();

        String name = nameTextField.getText();
        String serialNum = serialNumTextField.getText();
        String value = valueTextField.getText();


        if(helper.nameValid(name) && helper.serialNumValid(itemList, serialNum) && helper.valueValid(value))
        {
            Item newItem = new Item(serialNum, name, Double.parseDouble(value));

            helper.addItemFunction(itemList, serialNum, name, Double.parseDouble(value));
            listView.getItems().add(newItem);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        else
        {
            if(!helper.nameValid(name))
                errLabel.setText("Invalid name");
            if(!helper.serialNumValid(itemList, serialNum))
                errLabel.setText("Invalid serial number");
            if(!helper.valueValid(value))
                errLabel.setText("Invalid value");
        }
    }

    @FXML
    private void deleteItem(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();

        Item removeItem = listView.getItems().get(selectedIdx);
        listView.getItems().remove(removeItem);
        this.itemList = helper.deleteItemFunction(removeItem, this.itemList);
        if(!searchTextField.getText().isEmpty())
            this.searchedItemList = helper.deleteItemFunction(removeItem, this.searchedItemList);
    }

    @FXML
    private void deleteAll(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();

        //search list case
        if(!searchTextField.getText().isEmpty())
        {
            this.itemList = helper.deleteAllSearchItemsFunction(this.searchedItemList, this.itemList);
            this.searchedItemList.removeAll(this.searchedItemList);
        }
        else
        {
            this.itemList = helper.deleteAllItemsFunction(this.itemList);
        }
        listView.getItems().clear();
    }

    @FXML
    private void editName(ActionEvent event)
    {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();
        Item selectedItem = listView.getItems().get(selectedIdx);

        String name = nameTextField.getText();
        //active search case
        if(!searchTextField.getText().isEmpty() && helper.nameValid(name))
        {
            this.searchedItemList = helper.editNameFunction(this.searchedItemList, selectedItem, name);
            this.itemList = helper.editNameFunction(this.itemList, selectedItem, name);

            this.observableItems.clear();
            this.observableItems.addAll(searchedItemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        //no active search
        else if(searchTextField.getText().isEmpty() && helper.nameValid(name))
        {
            this.itemList = helper.editNameFunction(this.itemList, selectedItem, name);

            this.observableItems.clear();
            this.observableItems.addAll(itemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        else
        {
            errLabel.setText("Invalid Name");
        }
    }

    @FXML
    private void editSerialNumber(ActionEvent event)
    {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();
        Item selectedItem = listView.getItems().get(selectedIdx);

        String serialNum = serialNumTextField.getText();
        //active search case
        if(!searchTextField.getText().isEmpty() && helper.serialNumValid(itemList, serialNum))
        {
            this.searchedItemList = helper.editSerialNumberFunction(this.searchedItemList, selectedItem, serialNum);
            this.itemList = helper.editSerialNumberFunction(this.itemList, selectedItem, serialNum);

            this.observableItems.clear();
            this.observableItems.addAll(searchedItemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        //no active search
        else if(searchTextField.getText().isEmpty() && helper.serialNumValid(itemList, serialNum))
        {
            this.itemList = helper.editSerialNumberFunction(this.itemList, selectedItem, serialNum);

            this.observableItems.clear();
            this.observableItems.addAll(itemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        else
        {
            errLabel.setText("Invalid Serial Number");
        }
    }

    @FXML
    private void editValue(ActionEvent event)
    {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();
        Item selectedItem = listView.getItems().get(selectedIdx);

        String value = valueTextField.getText();
        //active search case
        if(!searchTextField.getText().isEmpty() && helper.valueValid(value))
        {
            this.searchedItemList = helper.editValueFunction(this.searchedItemList, selectedItem, Double.parseDouble(value));
            this.itemList = helper.editValueFunction(this.itemList, selectedItem, Double.parseDouble(value));

            this.observableItems.clear();
            this.observableItems.addAll(searchedItemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        //no active search
        else if(searchTextField.getText().isEmpty() && helper.valueValid(value))
        {
            this.itemList = helper.editValueFunction(this.itemList, selectedItem, Double.parseDouble(value));

            this.observableItems.clear();
            this.observableItems.addAll(itemList);
            listView.getItems().clear();
            listView.getItems().addAll(this.observableItems);
            errLabel.setText("");
            nameTextField.clear();
            serialNumTextField.clear();
            valueTextField.clear();
        }
        else
        {
            errLabel.setText("Invalid Value");
        }
    }



    @FXML
    private void loadFile(ActionEvent event) {
        FileParse fp = new FileParse();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files", "*.txt"));
        File f = fc.showOpenDialog(null);

        ArrayList<Item> tempList;
        if(f != null)
        {
            tempList = fp.loadFile(f.getAbsolutePath());
            updateGui(tempList);
            errLabel.setText("");
            System.out.println(f.getAbsolutePath());
        }
        else
        {
            errLabel.setText("Invalid File");
        }
    }

    //load list helper function
    private void updateGui(List<Item> tempItems)
    {
        this.itemList = tempItems;
        this.observableItems.clear();
        this.observableItems.addAll(tempItems);
        listView.getItems().clear();
        listView.getItems().addAll(this.observableItems);
    }

    @FXML
    private void saveFile(ActionEvent event) {
        FileSave fs = new FileSave();
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("text files", "*.txt"));
        File f = fc.showSaveDialog(null);
        try
        {
            fs.saveFile(f.getAbsolutePath(), this.itemList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void search(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();

        String str = searchTextField.getText();
        searchedItemList = helper.search(itemList, str);

        this.observableItems.clear();
        this.observableItems.addAll(searchedItemList);
        listView.getItems().clear();
        listView.getItems().addAll(this.observableItems);
    }

    @FXML
    private void reset(ActionEvent event)
    {
        updateGui(itemList);
        searchTextField.clear();
    }

    @FXML
    private void radioButtonChanged(ActionEvent event)
    {
        ManagementHelper helper = new ManagementHelper();
        List<Item> filterItems;
        //changes the contents of the filter list and pushes the changes to the gui list view
        //handles the sorting with search value
        if(!searchTextField.getText().isEmpty())
        {
            search(event);
            if(SortGroup.getSelectedToggle().equals(this.nameSortButton))
            {
                filterItems = helper.sortByName(searchedItemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
            if(SortGroup.getSelectedToggle().equals(this.valueSortButton))
            {
                filterItems = helper.sortByValue(searchedItemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
            if(SortGroup.getSelectedToggle().equals(this.serialSortButton))
            {
                filterItems = helper.sortBySerial(searchedItemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
        }
        else
        {
            if(SortGroup.getSelectedToggle().equals(this.nameSortButton))
            {
                filterItems = helper.sortByName(itemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
            if(SortGroup.getSelectedToggle().equals(this.valueSortButton))
            {
                filterItems = helper.sortByValue(itemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
            if(SortGroup.getSelectedToggle().equals(this.serialSortButton))
            {
                filterItems = helper.sortBySerial(itemList);
                this.observableItems.clear();
                this.observableItems.addAll(filterItems);
                listView.getItems().clear();
                listView.getItems().addAll(this.observableItems);
            }
        }
    }

    @FXML
    public void initialize()
    {
        listView.getItems().addAll(observableItems);
        errLabel.setText("");

        //handles the initialization of the filters
        SortGroup = new ToggleGroup();
        this.nameSortButton.setToggleGroup(SortGroup);
        this.valueSortButton.setToggleGroup(SortGroup);
        this.serialSortButton.setToggleGroup(SortGroup);
        this.nameSortButton.setSelected(true);
        this.nameSortButton.requestFocus();
    }
}
