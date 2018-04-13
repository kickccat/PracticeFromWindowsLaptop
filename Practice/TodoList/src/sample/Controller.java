package sample;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import sample.datamodel.TodoData;
import sample.datamodel.TodoItem;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Controller {
    
    private List<TodoItem> todoItems;
    @FXML
    private ListView<TodoItem> todoListview;
    @FXML
    private TextArea itemDetailsTextArea;
    @FXML
    private Label deadlineLabel;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private ContextMenu listContextMenu;
    
    public void initialize() {
        listContextMenu = new ContextMenu();
        MenuItem deleteMenuItem = new Menu("Delete");
        deleteMenuItem.setOnAction((event) -> {
            TodoItem item = todoListview.getSelectionModel().getSelectedItem();
            deleteItem(item);
        });
        
        listContextMenu.getItems().addAll(deleteMenuItem);
        todoListview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                TodoItem item = todoListview.getSelectionModel().getSelectedItem();
                itemDetailsTextArea.setText(item.getDetails());
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
                deadlineLabel.setText(timeFormatter.format(item.getDeadline()));
            }
        });
    
        SortedList<TodoItem> itemSortedList = new SortedList<>(TodoData.getInstance().getTodoItems(), Comparator.comparing(TodoItem::getDeadline));
        
//        SortedList<TodoItem> itemSortedList = new SortedList<>(TodoData.getInstance().getTodoItems(), (o1, o2) -> o1.getDeadline().compareTo(o2.getDeadline()));
        
//        todoListview.setItems(TodoData.getInstance().getTodoItems());
        todoListview.setItems(itemSortedList);
        todoListview.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        todoListview.getSelectionModel().selectFirst();
        
        todoListview.setCellFactory(new Callback<>() {
            @Override
            public ListCell<TodoItem> call(ListView<TodoItem> param) {
                ListCell<TodoItem> cell = new ListCell<>() {
                    @Override
                    protected void updateItem(TodoItem item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(item.getShortDescription());
                            if (item.getDeadline().isBefore(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.RED);
                            } else if (item.getDeadline().equals(LocalDate.now().plusDays(1))) {
                                setTextFill(Color.DEEPPINK);
                            }
                        }
                    }
                };
                cell.emptyProperty().addListener(
                        (obs, wasEmpty, isNowEmpty) -> {
                            if (isNowEmpty) {
                                cell.setContextMenu(null);
                            } else {
                                cell.setContextMenu(listContextMenu);
                            }
                        });
                return cell;
            }
        });
    }
    
    private void deleteItem(TodoItem item) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Todo Item");
        alert.setHeaderText("Delete Item: " + item.getShortDescription());
        alert.setContentText("Are you sure? Press OK to confirm, or CANCEL to back out");
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.isPresent() && result.get() == ButtonType.OK) {
            TodoData.getInstance().deleteTodoItem(item);
        }
    }
    
    @FXML
    public void showNewItemDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Todo Item");
        dialog.setHeaderText("Create new item");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("todoItemDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog");
            e.printStackTrace();
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController controller = fxmlLoader.getController();
            TodoItem newItem = controller.processResults();
            todoListview.getSelectionModel().select(newItem);
        }
    }
    
    @FXML
    public void handleClickListview() {
        TodoItem item = todoListview.getSelectionModel().getSelectedItem();
        itemDetailsTextArea.setText(item.getDetails());
        deadlineLabel.setText(item.getDeadline().toString());
    }
    
    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        TodoItem selectedItem = todoListview.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                deleteItem(selectedItem);
            }
        }
    }
}
