package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

public class InventoryManagementPageController {

    @FXML
    private Button addItemButton;
    @FXML
    private Button deleteAllItemButton;
    @FXML
    private Label errLabel;
    @FXML
    private ListView<?> listView;
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

    @FXML
    void addItem(ActionEvent event) {
        //create instance of helper class
        //take the information from the 3 fields and check if inputs are valid
        //if valid then create instance and add it to the list
        //else display error message
        //if(!nameValid) setText to invalid name
        //if(!valueValid) setText to invalid value
        //if(!serialValid) setText to invalid serial num
        //clear all fields
    }

    @FXML
    void deleteItem(ActionEvent event) {
        //get the current selection id
        //delete the current selection from the list and observable list
    }

    @FXML
    void deleteAll(ActionEvent event) {
        //remove all items from list / observable list
    }

    @FXML
    void editItem(ActionEvent event)
    {
        //take the information in the fields and replace the information in the selected item
    }

    @FXML
    void loadFile(ActionEvent event) {
        //create instance of file loader class
        //call load file function from file loader class
        //update gui with new information
    }

    @FXML
    void saveFile(ActionEvent event) {
        //create instance of file saver class
        //call save file function from loader class
    }

    @FXML
    void search(ActionEvent event) {
        //create instance of helper function class
        //take the string from the search text field
        //loop through the list and find the string and display it
    }

    @FXML
    void sortByName(ActionEvent event) {
        //create instance of helper function class
        //sort list alphabetically
    }

    @FXML
    void sortBySerialNum(ActionEvent event) {
        //create instance of helper function class
        //sort ascending by ascii value i assume
    }

    @FXML
    void sortByValue(ActionEvent event) {
        //create instance of helper function class
        //sort ascending by value
    }

}
