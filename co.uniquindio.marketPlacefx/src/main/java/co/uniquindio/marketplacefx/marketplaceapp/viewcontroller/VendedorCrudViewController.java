package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.marketplacefx.marketplaceapp.controller.VendedorCrudContoller;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class VendedorCrudViewController {
    VendedorCrudContoller vendedorCrudContoller;
    ObservableList<VendedorDto>listaVendedoresDto = FXCollections.observableArrayList();
    VendedorDto vendedorSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnActualizarVnededor;

    @FXML
    private Button btnAgregarVendedor;

    @FXML
    private Button btnBurcarVendedor;

    @FXML
    private Button btnEliminarVendedor;

    @FXML
    private TableView<VendedorDto> tableVendedor;

    @FXML
    private TableColumn<VendedorDto, String> tcApellido;

    @FXML
    private TableColumn<VendedorDto, String> tcCedula;

    @FXML
    private TableColumn<VendedorDto, String> tcContrasena;

    @FXML
    private TableColumn<VendedorDto, String> tcDireccion;

    @FXML
    private TableColumn<VendedorDto, String> tcNombre;

    @FXML
    private TableColumn<VendedorDto,String> tcUsuario;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtContrasena;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsuario;

    @FXML
    void initialize() {
        vendedorCrudContoller = new VendedorCrudContoller();
        initView();


    }

    private void initView() {
        initDataBinding();
        obtenerVendedores();
        tableVendedor.getItems().clear();
        tableVendedor.setItems(listaVendedoresDto);
        listenerSelection();
    }

    private void obtenerVendedores() {
        listaVendedoresDto.addAll(vendedorCrudContoller.obtenerVendedores());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario()));
        tcContrasena.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().contrasena()));
    }
    private void listenerSelection() {
        //Sirve la dar la seleccion de la tabla cada que se seleccione se va guardar en una variable cliente seleccionado
        tableVendedor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            vendedorSeleccionado = newSelection;
            //Cada que se seleccione de hace una nueva seleccion y se va mostrar el seleccionado
            mostrarInformacionCliente(vendedorSeleccionado);
        });
    }
    private void mostrarInformacionCliente(VendedorDto vendedorSeleccionado) {
        //Verificar que el cliente exista
        if(vendedorSeleccionado !=null){
            //Mostrar en los texFiel los datos que se selecciono
            txtNombre.setText(vendedorSeleccionado.nombre());
            txtApellido.setText(vendedorSeleccionado.apellido());
            txtCedula.setText(vendedorSeleccionado.cedula());
            txtDireccion.setText(vendedorSeleccionado.direccion());
            txtUsuario.setText(vendedorSeleccionado.usuario());
            txtContrasena.setText(vendedorSeleccionado.contrasena());
        }
    }

    @FXML
    void onAgregarCliente(ActionEvent event) {
        agregarCliente();
    }

    private void agregarCliente() {
        VendedorDto vendedorDto = crearVendedorDto();
        if(datosValidosDto(vendedorDto)){
            
        }else{

        }
    }

    private boolean datosValidosDto(VendedorDto vendedorDto) {
        if(vendedorDto.nombre().isEmpty() ||
                vendedorDto.apellido().isEmpty() ||
                vendedorDto.cedula().isEmpty() ||
                vendedorDto.direccion().isEmpty() ||
                vendedorDto.usuario().isEmpty() ||
                vendedorDto.contrasena().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    private VendedorDto crearVendedorDto() {
        return new VendedorDto(txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                txtDireccion.getText(),
                txtUsuario.getText(),
                txtContrasena.getText());
    }

    @FXML
    void onActualizarVendedor(ActionEvent event) {

    }

    @FXML
    void onEliminarVendedor(ActionEvent event) {

    }

    @FXML
    void onBurcarVendedor(ActionEvent event) {

    }
    private void mostrarInformacionVendedor(VendedorDto vendedorSeleccionado) {
        if(vendedorSeleccionado != null){
            txtNombre.setText(vendedorSeleccionado.nombre());
            txtApellido.setText(vendedorSeleccionado.apellido());
            txtCedula.setText(vendedorSeleccionado.cedula());
            txtDireccion.setText(vendedorSeleccionado.direccion());
            txtUsuario.setText(vendedorSeleccionado.usuario());
            txtContrasena.setText(vendedorSeleccionado.contrasena());
        }
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



}
