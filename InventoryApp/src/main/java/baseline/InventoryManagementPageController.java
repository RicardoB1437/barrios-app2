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
    private Button addItemButton;
    @FXML
    private Button deleteAllItemButton;
    @FXML
    private Label errLabel;
    @FXML
    private ListView<Item> listView;
    @FXML
    private Button loadFileButton;
    @FXML
    private TextField nameTextField;
    @FXML
    private Button saveFileButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField serialNumTextField;
    @FXML
    private SplitMenuButton sortMenu;
    @FXML
    private MenuItem sortNameButton;
    @FXML
    private MenuItem sortSerialButton;
    @FXML
    private MenuItem sortValueButton;
    @FXML
    private TextField valueTextField;

    private List<Item> itemList = new ArrayList<>();
    private List<Item> searchedItemList = new ArrayList<>();
    @FXML
    ObservableList<Item> observableItems = FXCollections.observableArrayList(itemList);

    @FXML
    void addItem(ActionEvent event) {
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
    void deleteItem(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();

        Item removeItem = listView.getItems().get(selectedIdx);
        listView.getItems().remove(removeItem);
        this.itemList = helper.deleteItemFunction(removeItem, this.itemList);
        if(!searchTextField.getText().isEmpty())
            this.searchedItemList = helper.deleteItemFunction(removeItem, this.searchedItemList);
    }

    @FXML
    void deleteAll(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();
        //remove all items from list / observable list
        //search list case
        if(!searchTextField.getText().isEmpty())
        {
            this.searchedItemList = helper.deleteAllSearchItemsFunction(searchTextField.getText(), this.searchedItemList);
            listView.getItems().clear();
        }
        else
        {
            this.itemList = helper.deleteAllItemsFunction(this.itemList);
            listView.getItems().clear();
        }
        this.itemList = helper.deleteAllItemsFunction(this.itemList);
        listView.getItems().clear();
    }

    @FXML
    private void editItem(ActionEvent event)
    {
        ManagementHelper helper = new ManagementHelper();
        int selectedIdx = listView.getSelectionModel().getSelectedIndex();

        String name = nameTextField.getText();
        String serialNum = serialNumTextField.getText();
        String value = valueTextField.getText();
        Item newItem = new Item(serialNum, name, Double.parseDouble(value));

        if(helper.nameValid(name) && helper.serialNumValid(itemList, serialNum) && helper.valueValid(value))
        {
            itemList = helper.editItemFunction(selectedIdx, itemList, serialNum, name, Double.parseDouble(value));
            listView.getItems().set(selectedIdx, newItem);
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
    void loadFile(ActionEvent event) {
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
    void saveFile(ActionEvent event) {
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
    void search(ActionEvent event) {
        ManagementHelper helper = new ManagementHelper();

        String str = searchTextField.getText();
        searchedItemList = helper.search(itemList, str);

        this.observableItems.clear();
        this.observableItems.addAll(searchedItemList);
        listView.getItems().clear();
        listView.getItems().addAll(this.observableItems);
    }

    @FXML
    void reset(ActionEvent event)
    {
        updateGui(itemList);
        searchTextField.clear();
    }

    @FXML
    public void radioButtonChanged(ActionEvent event)
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

    public void sortHelper(List<Item> tempItems)
    {

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
