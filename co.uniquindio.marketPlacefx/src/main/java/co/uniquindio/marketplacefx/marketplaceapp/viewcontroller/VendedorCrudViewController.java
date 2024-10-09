package co.uniquindio.marketplacefx.marketplaceapp.viewcontroller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import co.uniquindio.marketplacefx.marketplaceapp.controller.VendedorCrudContoller;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import static co.uniquindio.marketplacefx.marketplaceapp.utils.PrestamoConstantes.*;

public class VendedorCrudViewController {
    VendedorCrudContoller vendedorCrudContoller;
    List<Usuario> listaUsuarios=new ArrayList<>();
    ObservableList<VendedorDto>listaVendedoresDto = FXCollections.observableArrayList();
    VendedorDto vendedorSeleccionado;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnLimpiar;

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
    private TableColumn<VendedorDto, String> tcUsuario;

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
        limpiarCampos();
        listenerSelection();
    }

    private void obtenerVendedores() {
        listaVendedoresDto.addAll(vendedorCrudContoller.obtenerVendedores());
        listaUsuarios.addAll(vendedorCrudContoller.obtenerUsuarios());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().nombre()));
        tcApellido.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apellido()));
        tcCedula.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().cedula()));
        tcDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().direccion()));
        tcUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario().getNickUsuario()));
        tcContrasena.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().usuario().getContrasena()));

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
            txtUsuario.setText(vendedorSeleccionado.usuario().getNickUsuario());
            txtContrasena.setText(vendedorSeleccionado.usuario().getContrasena());
        }
    }

    @FXML
    void onAgregarCliente(ActionEvent event) {
        agregarCliente();
    }

    private void agregarCliente() {
        VendedorDto vendedorDto = crearVendedorDto();
        if(datosValidosDto(vendedorDto)){

            if(vendedorCrudContoller.agregarVendedor(vendedorDto)){
                listaVendedoresDto.add(vendedorDto);
                listaUsuarios.add(vendedorDto.usuario());
                limpiarCampos();
                mostrarMensaje(TITULO_VENDEDOR_AGREGADO, HEADER, BODY_VENDEDOR_AGREGADO,Alert.AlertType.INFORMATION);
            }else{
                mostrarMensaje(TITULO_VENDEDOR_NO_AGREGADO, HEADER, BODY_VENDEDOR_NO_AGREGADO,Alert.AlertType.ERROR);
            }
        }else{
            mostrarMensaje(TITULO_INCOMPLETO, HEADER, BODY_INCOMPLETO,Alert.AlertType.WARNING);

        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtUsuario.setText("");
        txtContrasena.setText("");
        vendedorSeleccionado=crearVendedorDto();
    }

    private boolean datosValidosDto(VendedorDto vendedorDto) {
        if(vendedorDto.nombre().isEmpty() ||
                vendedorDto.apellido().isEmpty() ||
                vendedorDto.cedula().isEmpty() ||
                vendedorDto.direccion().isEmpty() ||
                vendedorDto.usuario().getNickUsuario().isEmpty() ||
                vendedorDto.usuario().getContrasena().isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    private VendedorDto crearVendedorDto() {
        Usuario usuario=new Usuario(txtUsuario.getText(), txtContrasena.getText(),txtCedula.getText());

        return new VendedorDto(txtNombre.getText(),
                txtApellido.getText(),
                txtCedula.getText(),
                txtDireccion.getText(),
                usuario);
    }

    @FXML
    void onActualizarVendedor(ActionEvent event) {
        actualizarVendedor();

    }

    private void actualizarVendedor() {

        VendedorDto vendedorOld = datosViejos(vendedorSeleccionado);
        VendedorDto vendedorActualizado = crearVendedorDto();
        if (datosValidosDto(vendedorActualizado)){
            if(vendedorCrudContoller.actualizarVendedor(vendedorOld,vendedorActualizado)){
                for (int i=0; i<listaVendedoresDto.size(); i++){
                    VendedorDto vendedorDto = listaVendedoresDto.get(i);
                    if(vendedorDto.cedula().equalsIgnoreCase(vendedorOld.cedula())){
                        listaVendedoresDto.set(i,vendedorActualizado);
                        listaUsuarios.set(i,vendedorActualizado.usuario());
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

    private VendedorDto datosViejos(VendedorDto vendedorSeleccionado) {

        return new VendedorDto(vendedorSeleccionado.nombre(),
                vendedorSeleccionado.apellido(),
                vendedorSeleccionado.cedula(),
                vendedorSeleccionado.direccion(),
                vendedorSeleccionado.usuario());
    }

    @FXML
    void onEliminarVendedor(ActionEvent event) {
        eliminarVendedor();

    }

    private void eliminarVendedor() {
        VendedorDto vendedorDto = vendedorSeleccionado;
        if(vendedorDto != null){
            if(vendedorCrudContoller.eliminarVendedor(vendedorDto)){
                listaVendedoresDto.remove(vendedorDto);
                listaUsuarios.remove(vendedorDto.usuario());
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
    void onBurcarVendedor(ActionEvent event) {
        buscarVendedor();

    }

    private void buscarVendedor() {
        VendedorDtoId vendedorDtoId =new  VendedorDtoId  (txtCedula.getText());
        if (!vendedorDtoId.cedula().isEmpty()){
            if(vendedorCrudContoller.buscarVendedor(vendedorDtoId)){
                for(VendedorDto vendedorDto : listaVendedoresDto){
                    if(vendedorDto.cedula().equalsIgnoreCase(vendedorDtoId.cedula())){
                        txtNombre.setText(vendedorDto.nombre());
                        txtApellido.setText(vendedorDto.apellido());
                        txtCedula.setText(vendedorDto.cedula());
                        txtDireccion.setText(vendedorDto.direccion());
                        txtUsuario.setText(vendedorDto.usuario().getNickUsuario());
                        txtContrasena.setText(vendedorDto.usuario().getContrasena());
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
    void onLimpiar(ActionEvent event) {
        limpiarCampos();
    }



}
