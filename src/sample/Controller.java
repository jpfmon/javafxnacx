package sample;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Controller implements Initializable {

    Data dataObject = new Data();

    //    ObservableList<Order> data = FXCollections.observableArrayList();
    ObservableList<Order> data = dataObject.getData();


    @FXML
    TableView tableView;
    @FXML
    TableColumn referencia, telefono, importe, destinatario, tipoReem, prodList1, prodList2, prodList3, prodList4, prodList5;

    @FXML
    Button exec, exec1, exec2, downFile;
    @FXML
    MenuItem selectFile, close;
    @FXML
    Label fileSelected, doubledConfirmed;
    @FXML
    TextField productCode;

    String path = "";
    String excelFile = "download_orders.xls";

//    Following 2 variables not needed here when using Data Class as Model
//    FileInputStream fileInputStream = null;
//    HSSFWorkbook wb = null;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tableView.setItems(data);
        tableView.setEditable(true);
        referencia.setCellValueFactory(new PropertyValueFactory<Order, String>("referencia"));
        referencia.setCellFactory(TextFieldTableCell.forTableColumn());
        referencia.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {

                ((Order) event.getTableView().getItems().get(event.getTablePosition().getRow())).setReferencia((String) event.getNewValue());
                for (Order s : data) {
                    System.out.println(s.getReferencia());
                }
            }
        });
        telefono.setCellValueFactory(new PropertyValueFactory<Order, String>("telefono"));
        importe.setCellValueFactory(new PropertyValueFactory<Order, String>("importe"));
        destinatario.setCellValueFactory(new PropertyValueFactory<Order, String>("destinatario"));
        tipoReem.setCellValueFactory(new PropertyValueFactory<Order, String>("tipoReem"));
        prodList1.setCellValueFactory(new PropertyValueFactory<Order, String>("prodList1"));
        prodList2.setCellValueFactory(new PropertyValueFactory<Order, String>("prodList2"));
        prodList3.setCellValueFactory(new PropertyValueFactory<Order, String>("prodList3"));
        prodList4.setCellValueFactory(new PropertyValueFactory<Order, String>("prodList4"));
        prodList5.setCellValueFactory(new PropertyValueFactory<Order, String>("prodList5"));

    }

    public void loadData() {
        doubledConfirmed.setText("");
        tableView.getItems().clear();
        if (excelFile.equals("download_orders.xls")) {
            fileSelected.setText("no file selected");
            doubledConfirmed.setText("NO FILE SELECTED!!!!");
        }
        data = dataObject.getFromFile(excelFile);
        setTableView(data);
    }

    public void cleanData() {
        doubledConfirmed.setText("");
        tableView.getItems().clear();
        productCode.setText("");
    }

    public void selectFile() {
        Stage stage = new Stage();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files", "*.xls");
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(filter);
        File file = chooser.showOpenDialog(stage);
        path = file.getParent();
        String fileFullPath = file.toString();
        System.out.println(fileFullPath);
        excelFile = fileFullPath;
        String fileName = file.getName();
        fileSelected.setText(fileName);
    }

    public void checkDoubleProducts() throws IOException {
        dataObject.checkDoubleProducts(data,path,doubledConfirmed);
    }

    public void checkDoubledOrders() throws IOException {
        data = dataObject.checkDoubleOrders(data, doubledConfirmed, path);
        setTableView(data);
    }

    private void setTableView(ObservableList<Order> data) {
        tableView.setItems(data);
        tableView.setEditable(true);
        referencia.setCellValueFactory(new PropertyValueFactory<Order, String>("referencia"));
        referencia.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public void addGiftedProduct() throws IOException {
        if (data.size() == 0) {
            doubledConfirmed.setText("LOAD DATA FIRST!!");
        } else {
            String giftedProduct = productCode.getText().trim();
            if (giftedProduct.equals("")) {
                doubledConfirmed.setText("PLEASE ADD PRODUCT CODE!!");
                productCode.setText(giftedProduct);
            } else if (giftedProduct.length() != 5) {
                doubledConfirmed.setText("Product code must have 5 characters!!");
                productCode.setText(giftedProduct);
            } else {
                productCode.setText(giftedProduct);
                data = dataObject.addGiftedProduct(path, data, giftedProduct);
                if (data == null) {
                    doubledConfirmed.setText("ERROR adding gifted product. Do manually");
                } else {
                    doubledConfirmed.setText("Product added succesfully, see file: resultForMessento.csv ");
                    setTableView(data);
                }
            }
        }
    }

    public void close() {
        Platform.exit();
        System.exit(0);
    }

    public void info() {
        Stage stage = new Stage();
        Stage primaryStage = (Stage) tableView.getScene().getWindow();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(primaryStage);
        VBox vBox = new VBox(20);
        vBox.getChildren().add(new Text("\n\n       Application created by \n    Juan Fernandez Montojo \n    jpfmontojo@gmail.com"));
        Scene scene = new Scene(vBox, 200, 200);
        stage.setScene(scene);
        stage.show();
    }

    public void downloadFileCont() throws IOException {
            dataObject.downloadFile(data,path);
    }
}


