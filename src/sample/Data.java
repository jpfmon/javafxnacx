package sample;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.*;
import java.util.*;

public class Data {

    ObservableList<Order> data;

    FileInputStream fileInputStream;
    HSSFWorkbook wb = null;
    ArrayList<String> headersOrdersFile = new ArrayList<>();

    public Data() {
        this.data = FXCollections.observableArrayList();
    }

    public ObservableList<Order> getData() {
        return data;
    }

    public void setData(ObservableList<Order> data) {
        this.data = data;
    }


    public ObservableList<Order> getFromFile(String excelFile) {

        data = FXCollections.observableArrayList();
        try {
            fileInputStream = new FileInputStream(excelFile);
            wb = new HSSFWorkbook(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        int rowIndex = 0;

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            int columnIndex = 0;
            String tempReferencia = "";
            String tempTelefono = "";
            String tempImporte = "";
            String tempDestinatario = "";
            String tempTipoReem = "";
            String prodList1 = "";
            String prodList2 = "";
            String prodList3 = "";
            String prodList4 = "";
            String prodList5 = "";

            String tempDireccion = "";
            String tempCp = "";
            String tempPoblacion = "";
            String tempProvincia = "";
            String tempObservaciones1 = "";
            String tempObservaciones2 = "";
            String tempObservaciones3 = "";
            String tempObservaciones4 = "";

            while (cellIterator.hasNext()) {
                /**
                 * TO DO
                 * Here I'm going to extract the headers of the file, so I can have them easily
                 * when needed to create/download the new excel file after processing everything
                 */
                if (rowIndex == 0) {
                    headersOrdersFile.add(cellIterator.next().getStringCellValue());
                } else {
                    switch (columnIndex) {
                        case 5:
                            tempReferencia = cellIterator.next().getStringCellValue();
//                        System.out.println("\n*******" + tempReferencia + "*******\n");
                            break;
                        case 9:
                            tempDestinatario = cellIterator.next().getStringCellValue();
                            break;
                        case 12:
                            tempDireccion = cellIterator.next().getStringCellValue();
                            break;
                        case 14:
                            tempCp = cellIterator.next().getStringCellValue();
                            break;
                        case 15:
                            tempPoblacion = cellIterator.next().getStringCellValue();
                            break;
                        case 16:
                            tempProvincia = cellIterator.next().getStringCellValue();
                            break;
                        case 17:
                            tempTelefono = cellIterator.next().getStringCellValue();
//                        System.out.println("\n*******" + tempTelefono + "*******\n");
                            break;
                        case 18:
                            tempImporte = cellIterator.next().getStringCellValue();
//                        System.out.println("\n*******" + tempImporte + "*******\n");
                            break;
                        case 19:
                            tempTipoReem = cellIterator.next().getStringCellValue();
                            break;
                        case 20:
                            tempObservaciones1 = cellIterator.next().getStringCellValue();
                            break;
                        case 21:
                            tempObservaciones2 = cellIterator.next().getStringCellValue();
                            break;
                        case 22:
                            tempObservaciones3 = cellIterator.next().getStringCellValue();
                            break;
                        case 23:
                            tempObservaciones4 = cellIterator.next().getStringCellValue();
                            break;
                        case 42:
                            prodList1 = cellIterator.next().getStringCellValue();
                            break;
                        case 43:
                            prodList2 = cellIterator.next().getStringCellValue();
                            break;
                        case 44:
                            prodList3 = cellIterator.next().getStringCellValue();
                            break;
                        case 45:
                            prodList4 = cellIterator.next().getStringCellValue();
                            break;
                        case 46:
                            prodList5 = cellIterator.next().getStringCellValue();
                            break;
                        default:
                            String text = cellIterator.next().getStringCellValue();
                    }
                }
                columnIndex++;
            }
            if (rowIndex > 0) {
                data.add(new Order(tempReferencia, tempTelefono, tempImporte, tempDestinatario, tempDireccion, tempCp, tempPoblacion, tempProvincia, tempTipoReem,
                        tempObservaciones1, tempObservaciones2, tempObservaciones3, tempObservaciones4, prodList1, prodList2, prodList3, prodList4, prodList5));
            }
            rowIndex++;
        }
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(headersOrdersFile.toString());
        return data;
    }

    public ObservableList<Order> addGiftedProduct(String path, ObservableList<Order> data, String giftedProduct) throws IOException {

        String resultFileWithPath = path + "\\resultForMessento.csv";
        System.out.println(resultFileWithPath);
        FileWriter fileWriter = new FileWriter(resultFileWithPath);
        fileWriter.write("Phone\n");

        for (Order order : data) {
            String tempProductList = "";
            if (order.getTipoReem().equals("O")) {
                int sizeProductList1 = order.getProdList1().length();
                int sizeProductList2 = order.getProdList2().length();
                int sizeProductList3 = order.getProdList3().length();
                int sizeProductList4 = order.getProdList4().length();
                int sizeProductList5 = order.getProdList5().length();


                if (sizeProductList1 < 35 && (sizeProductList1 + 6 < 36)) {
                    tempProductList = order.getProdList1() + giftedProduct + "-";
                    order.setProdList1(tempProductList);
                    fileWriter.write("34" + order.getTelefono().trim() + "\n");
//                    System.out.println("Hemos pasado por 1");
                } else if (sizeProductList2 < 35 && (sizeProductList2 + 6 < 36)) {
                    tempProductList = order.getProdList2() + giftedProduct + "-";
                    order.setProdList2(tempProductList);
                    fileWriter.write("34" + order.getTelefono().trim() + "\n");
//                    System.out.println("Hemos pasado por 2");
                } else if (sizeProductList3 < 35 && (sizeProductList3 + 6 < 36)) {
                    tempProductList = order.getProdList3() + giftedProduct + "-";
                    order.setProdList3(tempProductList);
                    fileWriter.write("34" + order.getTelefono().trim() + "\n");
//                    System.out.println("Hemos pasado por 3");
                } else if (sizeProductList4 < 35 && (sizeProductList4 + 6 < 36)) {
                    tempProductList = order.getProdList4() + giftedProduct + "-";
                    order.setProdList4(tempProductList);
                    fileWriter.write("34" + order.getTelefono().trim() + "\n");
//                    System.out.println("Hemos pasado por 4");
                } else if (sizeProductList5 < 35 && (sizeProductList5 + 6 < 36)) {
                    tempProductList = order.getProdList5() + giftedProduct + "-";
                    order.setProdList5(tempProductList);
                    fileWriter.write("34" + order.getTelefono().trim() + "\n");
//                    System.out.println("Hemos pasado por 5");
                } else {
                    return null;
                }

            }
        }
        fileWriter.close();
//        System.out.println(data.toString());
        return data;
    }

    public ObservableList checkDoubleOrders(ObservableList<Order> data, Label doubledConfirmed, String path) throws IOException {
        Map<String, Integer> phones = new HashMap<String, Integer>();

        if (data.size() == 0) {
            doubledConfirmed.setText("NO DATA!!");
            return data;
        }
        for (Order order : data) {
            if (!phones.containsKey(order.getTelefono())) {
                phones.put(order.getTelefono(), 1);
            } else {
                int value = phones.get(order.getTelefono()) + 1;
                phones.replace(order.getTelefono(), value);
            }
        }

        ArrayList<String> doubled = new ArrayList();

        String resultFileWithPath = path + "\\doubledOrders.csv";
//        System.out.println(resultFileWithPath);


        for (Map.Entry entry : phones.entrySet()) {
            if ((int) entry.getValue() >= 2) {
                for (Order order : data) {
                    if (order.getTelefono().equals(entry.getKey())) {
                        doubled.add(order.getReferencia());
//                        System.out.println("Doubled!, Reference added: " + order.getReferencia());
                    }
                }
            }
        }
        System.out.println(doubled.toString());

        if (doubled.size() > 0) {
            FileWriter fileWriter = new FileWriter(resultFileWithPath);
            doubledConfirmed.setText("There are doubles. Check file doubledOrders.csv");
            int index = 0;
            for (String s : doubled) {
                String line = "";
                if (index == 0) {
                    line = s;
                } else {
                    line = " , " + s;
                }
                fileWriter.write(line);
                index++;
            }
            fileWriter.close();
        } else {
            doubledConfirmed.setText("No doubles detected");
        }


        ListIterator iterator = data.listIterator();
        ObservableList<Order> newData = FXCollections.observableArrayList();

        while (iterator.hasNext()) {

            Order tempOrder = (Order) iterator.next();
            if (!doubled.contains(tempOrder.getReferencia())) {
                newData.add(tempOrder);
//                System.out.println(tempOrder.getReferencia() + " added to oservable list of not repeated");
            }
        }
        data = newData; //data has now orders WITHOUT the repeated ones, TABLEVIEW is not updated in this method
        return data;
    }

    public void checkDoubleProducts(ObservableList<Order> data, String path,Label doubleConfirmed) throws IOException {
        HashSet<Order> ordersMaybeDoubledProducts = new HashSet();

        for (Order order : data) {
            String prodList1 = order.getProdList1();
            String prodList2 = order.getProdList2();
            String prodList3 = order.getProdList3();
            String prodList4 = order.getProdList4();
            String prodList5 = order.getProdList5();

            ArrayList<String> productsList = new ArrayList();
            for (String p : prodList1.split("-")) {
                if (p.equals("")) {
                    continue;
                }
                productsList.add(p);
            }
            for (String p : prodList2.split("-")) {
                if (p.equals("")) {
                    continue;
                }
                productsList.add(p);
            }
            for (String p : prodList3.split("-")) {
                if (p.equals("")) {
                    continue;
                }
                productsList.add(p);
            }
            for (String p : prodList4.split("-")) {
                if (p.equals("")) {
                    continue;
                }
                productsList.add(p);
            }
            for (String p : prodList5.split("-")) {
                if (p.equals("")) {
                    continue;
                }
                productsList.add(p);
            }

            HashMap map = new HashMap();

            for (String pl : productsList) {
                if (map.containsKey(pl)) {
                    int value = (int) map.get(pl);
                    int newValue = value + 1;
                    map.put(pl, newValue);
                    ordersMaybeDoubledProducts.add(order);
                } else {
                    map.put(pl, 1);
                }
            }

            System.out.println(map);

            String resultFileWithPath = path + "\\possibleOrdersWithDoubleProducts.csv";

            if (!ordersMaybeDoubledProducts.isEmpty()) {
                FileWriter fileWriter = new FileWriter(resultFileWithPath);
                for (Order order1 : ordersMaybeDoubledProducts) {
                    fileWriter.write(order1.getReferencia() + "\n");
                }
                doubleConfirmed.setText("Orders with possible double products, check file");
                fileWriter.close();
            }
        }
    }


    public void downloadFile(ObservableList<Order> data, String path) throws IOException {
        System.out.println("Download File to " + path);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet 1");
        Row firstRow = sheet.createRow(0);

        int columnIndexFirstRow = 0;

        for (String s : headersOrdersFile) {
            Cell cell = firstRow.createCell(columnIndexFirstRow);
            cell.setCellValue(s);
            columnIndexFirstRow++;
        }
        int rowIndexOrders = 1;
        for (Order order : data) {
            Row row = sheet.createRow(rowIndexOrders);
            Cell nAbonado = row.createCell(0);
            nAbonado.setCellValue("3995");
            Cell departamento = row.createCell(1);
            departamento.setCellValue("HAR.LIFE");
            Cell servicio = row.createCell(2);
            servicio.setCellValue("27");
            Cell tipoCobro = row.createCell(3);
            tipoCobro.setCellValue("O");
            Cell excesos = row.createCell(4);
            excesos.setCellValue("");
            Cell referencia = row.createCell(5);
            referencia.setCellValue(order.getReferencia());
            Cell bagPaq = row.createCell(6);
            bagPaq.setCellValue("2");
            Cell bultos = row.createCell(7);
            bultos.setCellValue("1");
            Cell kilos = row.createCell(8);
            kilos.setCellValue("1");
            Cell destinatario = row.createCell(9);
            destinatario.setCellValue(order.getDestinatario());
            Cell departamentoEnt = row.createCell(10);
            departamentoEnt.setCellValue("");
            Cell personaEnt = row.createCell(11);
            personaEnt.setCellValue("");
            Cell direccion = row.createCell(12);
            direccion.setCellValue(order.getDireccion());
            Cell paisEnt = row.createCell(13);
            paisEnt.setCellValue("ES");
            Cell cp = row.createCell(14);
            cp.setCellValue(order.getCp());
            Cell poblacion = row.createCell(15);
            poblacion.setCellValue(order.getPoblacion());
            Cell provincia = row.createCell(16);
            provincia.setCellValue(order.getProvincia());
            Cell telefono = row.createCell(17);
            telefono.setCellValue(order.getTelefono());
            Cell impReem = row.createCell(18);
            impReem.setCellValue(order.getImporte());
            Cell tipoReem = row.createCell(19);
            tipoReem.setCellValue(order.getTipoReem());
            Cell observaciones1 = row.createCell(20);
            observaciones1.setCellValue(order.getObservaciones1());
            Cell observaciones2 = row.createCell(21);
            observaciones2.setCellValue(order.getObservaciones2());
            Cell observaciones3 = row.createCell(22);
            observaciones3.setCellValue(order.getObservaciones3());
            Cell observaciones4 = row.createCell(23);
            observaciones4.setCellValue(order.getObservaciones4());
            Cell retorno = row.createCell(24);
            retorno.setCellValue("N");
            Cell o830 = row.createCell(25);
            o830.setCellValue("N");
            Cell sabado = row.createCell(26);
            sabado.setCellValue("N");
            Cell gestion = row.createCell(27);
            gestion.setCellValue("N");
            Cell ok15 = row.createCell(28);
            ok15.setCellValue("N");
            Cell prepagado = row.createCell(29);
            prepagado.setCellValue("N");
            Cell tipoSegu = row.createCell(30);
            tipoSegu.setCellValue("N");
            Cell impSegui = row.createCell(31);
            impSegui.setCellValue("0");
            Cell tipoEAl = row.createCell(32);
            tipoEAl.setCellValue("");
            Cell eAl = row.createCell(33);
            eAl.setCellValue("");
            Cell alto = row.createCell(34);
            alto.setCellValue("");
            Cell ancho = row.createCell(35);
            ancho.setCellValue("");
            Cell largo = row.createCell(36);
            largo.setCellValue("");
            Cell contenido = row.createCell(37);
            contenido.setCellValue("");
            Cell valorDecl = row.createCell(38);
            valorDecl.setCellValue("");
            Cell digita = row.createCell(39);
            digita.setCellValue("N");
            Cell albCli = row.createCell(40);
            albCli.setCellValue("0");
            Cell insAdi = row.createCell(41);
            insAdi.setCellValue("S");
            Cell prodList1 = row.createCell(42);
            prodList1.setCellValue(order.getProdList1());
            Cell prodList2 = row.createCell(43);
            prodList2.setCellValue(order.getProdList2());
            Cell prodList3 = row.createCell(44);
            prodList3.setCellValue(order.getProdList3());
            Cell prodList4 = row.createCell(45);
            prodList4.setCellValue(order.getProdList4());
            Cell prodList5 = row.createCell(46);
            prodList5.setCellValue(order.getProdList5());
            Cell prodList6 = row.createCell(47);
            prodList6.setCellValue("");
            Cell prodList7 = row.createCell(48);
            prodList7.setCellValue("");
            Cell prodList8 = row.createCell(49);
            prodList8.setCellValue("");
            Cell prodList9 = row.createCell(50);
            prodList9.setCellValue("");
            Cell prodList10 = row.createCell(51);
            prodList10.setCellValue("");
            Cell prodList11 = row.createCell(52);
            prodList11.setCellValue("");
            Cell prodList12 = row.createCell(53);
            prodList12.setCellValue("");
            Cell prodList13 = row.createCell(54);
            prodList13.setCellValue("");
            Cell prodList14 = row.createCell(55);
            prodList14.setCellValue("");
            Cell prodList15 = row.createCell(56);
            prodList15.setCellValue("");
            Cell tipoPrealBF = row.createCell(57);
            tipoPrealBF.setCellValue("S");
            Cell modoAlBG = row.createCell(58);
            modoAlBG.setCellValue("S");
            Cell preAlBH = row.createCell(59);
            preAlBH.setCellValue(order.getTelefono());
            Cell preAlMenBI = row.createCell(60);
            preAlMenBI.setCellValue("");
            Cell tipoPrealBJ = row.createCell(61);
            tipoPrealBJ.setCellValue("");
            Cell modoAlBK = row.createCell(62);
            modoAlBK.setCellValue("");
            Cell preAlBL = row.createCell(63);
            preAlBL.setCellValue("");
            Cell preAlMenBM = row.createCell(64);
            preAlMenBM.setCellValue("");
            Cell tipoPrealBN = row.createCell(65);
            tipoPrealBN.setCellValue("");
            Cell modoAlBO = row.createCell(66);
            modoAlBO.setCellValue("");
            Cell BP = row.createCell(67);
            BP.setCellValue("");
            Cell BQ = row.createCell(68);
            BQ.setCellValue("");
            Cell BR = row.createCell(69);
            BR.setCellValue("");
            Cell BS = row.createCell(70);
            BS.setCellValue("");
            Cell BT = row.createCell(71);
            BT.setCellValue("");
            Cell BU = row.createCell(72);
            BU.setCellValue("");
            Cell BV = row.createCell(73);
            BV.setCellValue("");
            Cell BW = row.createCell(74);
            BW.setCellValue("");
            Cell BX = row.createCell(75);
            BX.setCellValue("");
            Cell BY = row.createCell(76);
            BY.setCellValue("");
            Cell BZ = row.createCell(77);
            BZ.setCellValue("");
            Cell CA = row.createCell(78);
            CA.setCellValue("");
            Cell CB = row.createCell(79);
            CB.setCellValue("");
            Cell referenciaCC = row.createCell(80);
            referenciaCC.setCellValue(order.getReferencia());
            Cell CD = row.createCell(81);
            CD.setCellValue("");
            Cell CE = row.createCell(82);
            CE.setCellValue("");
            rowIndexOrders++;
        }


//        File currDir = new File(".");
//        String path = currDir.getAbsolutePath();
//        String fileLocation = path.substring(0, path.length() - 1) + "downloadedFile.xls";

        System.out.println(path);
        FileOutputStream outputStream = new FileOutputStream(path + "\\pimpirimpimpim.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }
}
