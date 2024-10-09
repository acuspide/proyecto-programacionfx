package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import co.uniquindio.marketplacefx.marketplaceapp.controller.ProductoCrudController;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static co.uniquindio.marketplacefx.marketplaceapp.utils.PrestamoConstantes.*;
import static co.uniquindio.marketplacefx.marketplaceapp.utils.PrestamoConstantes.BODY_INCOMPLETO;

public class ProductoCrudViewController {
    ProductoCrudController productoCrudController;
    //List<Usuario> listaUsuarios=new ArrayList<>();
    ObservableList<ProductoDto> listaProductosDto = FXCollections.observableArrayList();
    ProductoDto productoSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarProducto;

    @FXML
    private Button btnAgregarProducto;

    @FXML
    private Button btnBurcarProducto;

    @FXML
    private Button btnEliminarProducto;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<ProductoDto> tableProducto;

    @FXML
    private TableColumn<ProductoDto, String> tcCategoria;

    @FXML
    private TableColumn<ProductoDto, String> tcContrasena;

    @FXML
    private TableColumn<ProductoDto, String> tcId;

    @FXML
    private TableColumn<ProductoDto, String> tcImagen;

    @FXML
    private TableColumn<ProductoDto, String> tcNombre;

    @FXML
    private TableColumn<ProductoDto, Double> tcPrecio;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtImagen;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtCagetoria;


    @FXML
    void initialize() {
        productoCrudController = new ProductoCrudController();
        initView();
    }

    private void initView() {
        initDataBinding();
        obtenerProductos();
        tableProducto.getItems().clear();
        tableProducto.setItems(listaProductosDto);
        limpiarCampos();
        listenerSelection();
    }

    private void obtenerProductos() {
        listaProductosDto.addAll(productoCrudController.obtenerProductos());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().id()));
        //tc precio
        tcCategoria.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().categoria()));
        tcPrecio.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().precio()));        tcImagen.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().imagen()));

    }
    private void listenerSelection() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tableProducto.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            productoSeleccionado = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarInformacionCliente(productoSeleccionado);
        });
    }
    private void mostrarInformacionCliente(ProductoDto productoSeleccionado) {
        //Verificar que el cliente exista
        if(productoSeleccionado !=null){
            //Mostrar en los texFiel los datos que se selecciono
            txtNombre.setText(productoSeleccionado.nombre());
            txtId.setText(productoSeleccionado.id());
            txtCagetoria.setText(productoSeleccionado.categoria());
            //txt precio
            txtPrecio.setText(String.valueOf(productoSeleccionado.precio()));
            txtImagen.setText(productoSeleccionado.imagen());

        }
    }
    private void limpiarCampos() {
        txtNombre.setText("");
        txtId.setText("");
        txtCagetoria.setText("");
        txtPrecio.setText("0.0");
        txtImagen.setText("");
        productoSeleccionado=crearProducto();
    }
    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert aler = new Alert(alertType);
        aler.setTitle(titulo);
        aler.setHeaderText(header);
        aler.setContentText(contenido);
        aler.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    @FXML
    void onAgregarProducto(ActionEvent event) {
        agregarProducto();
    }
    private void agregarProducto() {
        ProductoDto productoDto = crearProducto();
        if(datosValidosDto(productoDto)){

            if(productoCrudController.agregarVendedor(productoDto)){
                listaProductosDto.add(productoDto);
                limpiarCampos();
                mostrarMensaje(TITULO_VENDEDOR_AGREGADO, HEADER, BODY_VENDEDOR_AGREGADO, Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_VENDEDOR_NO_AGREGADO, HEADER, BODY_VENDEDOR_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);

        }
    }
    private ProductoDto crearProducto() {
        return new ProductoDto(txtNombre.getText(),
                txtId.getText(),
                txtCagetoria.getText(),Double.parseDouble(txtPrecio.getText()),
                txtImagen.getText());
    }
    private boolean datosValidosDto(ProductoDto productoDto) {
        if(productoDto.nombre().isEmpty() ||
                productoDto.id().isEmpty() ||
                productoDto.precio() <= 0 ||
                productoDto.imagen().isEmpty()){
            return false;
        }else {
            return true;
        }
    }
    @FXML
    void onActualizarProducto(ActionEvent event) {
        actualizarProducto();
    }
    private void actualizarProducto() {

        ProductoDto productoDtoOld = datosViejos(productoSeleccionado);
        ProductoDto productoActualizado = crearProducto();
        if (datosValidosDto(productoActualizado)){
            if(productoCrudController.actualizarProducto(productoDtoOld,productoActualizado)){
                for (int i=0; i<listaProductosDto.size(); i++){
                    ProductoDto productoDto = listaProductosDto.get(i);
                    if(productoDto.id().equalsIgnoreCase(productoDtoOld.id())){
                        listaProductosDto.set(i,productoActualizado);
                    }
                }

                limpiarCampos();
                mostrarMensaje(TITULO_VENDEDOR_ACTUALIZADO, HEADER, BODY_VENDEDOR_ACTUALIZADO,Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_VENDEDOR_NO_ACTUALIZADO, HEADER, BODY_VENDEDOR_NO_ACTUALIZADO,Alert.AlertType.ERROR);
            }

        }else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);

        }
    }
    private ProductoDto datosViejos(ProductoDto productoSeleccionado) {

        return new ProductoDto(productoSeleccionado.nombre(),
                productoSeleccionado.id(),
                productoSeleccionado.categoria(),
                productoSeleccionado.precio(),
                productoSeleccionado.imagen());
    }

    @FXML
    void onEliminarProducto(ActionEvent event) {
        eliminarProducto();
    }
    private void eliminarProducto() {
        ProductoDto productoDto = productoSeleccionado;
        if(productoDto != null){
            if(productoCrudController.eliminarProducto(productoDto)){
                listaProductosDto.remove(productoDto);
                limpiarCampos();
                mostrarMensaje(TITULO_VENDEDOR_ELIMINADO, HEADER, BODY_VENDEDOR_ELIMINADO,Alert.AlertType.INFORMATION);
            }else {
                mostrarMensaje(TITULO_VENDEDOR_NO_ELIMINADO, HEADER, BODY_VENDEDOR_NO_ELIMINADO,Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }

    }

    @FXML
    void onBurcarProdcuto(ActionEvent event) {
        buscarProducto();
    }

    private void buscarProducto() {
        ProductoDtoId productoDtoId =new ProductoDtoId(txtId.getText());
        if (!productoDtoId.id().isEmpty()){
            if(productoCrudController.buscarProducto(productoDtoId)){
                for(ProductoDto productoDto : listaProductosDto){
                    if(productoDto.id().equalsIgnoreCase(productoDtoId.id())){
                        txtNombre.setText(productoDto.nombre());
                        txtId.setText(productoDto.id());
                        txtCagetoria.setText(productoDto.categoria());
                        txtPrecio.setText(String.valueOf(productoDto.precio()));
                        txtImagen.setText(productoDto.imagen());
                        mostrarMensaje(TITULO_VENDEDOR_ENCONTRADO, HEADER, BODY_VENDEDOR_ENCONTRADO,Alert.AlertType.INFORMATION);
                        break;
                    }
                }
            }else {
                mostrarMensaje(TITULO_VENDEDOR_NO_ENCONTRADO, HEADER, BODY_VENDEDOR_NO_ENCONTRADO,Alert.AlertType.ERROR);
            }
        }else {
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);
        }
    }

    @FXML
    void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }

}

