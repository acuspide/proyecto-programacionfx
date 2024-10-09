package co.uniquindio.marketplacefx.marketplaceapp.model;

import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDto;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.ProductoDtoId;
import co.uniquindio.marketplacefx.marketplaceapp.mapping.dto.VendedorDtoId;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class PrestamoObjeto {
    private List<Vendedor>listaVendedores = new ArrayList<>();
    private List<Usuario>listaUsuarios = new ArrayList<>();
    private List<Producto>listaProductos = new ArrayList<>();

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public List<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public boolean agregarVendedor(Vendedor vendedor) {
        Vendedor vendedorEncontrado = obtenerVendedor(vendedor.getCedula());
        if(vendedorEncontrado == null){
            listaVendedores.add(vendedor);
            listaUsuarios.add(vendedor.getUsuario());
            return true;
        }else{
            return false;
        }

    }

    private Vendedor obtenerVendedor(String cedula) {
        Vendedor vendedor = null;
        for(Vendedor vendedo1 : listaVendedores){
            if(vendedo1.getCedula().equalsIgnoreCase(cedula)){
                vendedor = vendedo1;
                break;
            }
        }
        return vendedor;
    }

    public boolean actualizarVendedor(Vendedor vendedorOld,Vendedor vendedorActualizado) {
        Vendedor vendedor = obtenerVendedor(vendedorOld.getCedula());
        Vendedor vendedorMod= obtenerVendedor(vendedorActualizado.getCedula());
        if(vendedor!=null){
            if(vendedor.getCedula().equals(vendedorActualizado.getCedula()) || vendedorMod==null){
                int index=getIndex(vendedorOld.getCedula());
                listaVendedores.set(index,vendedorActualizado);
                listaUsuarios.set(index,vendedorActualizado.getUsuario());
                return true;
            }else{return false;}
        }else{return false;}
    }
    public int getIndex(String cedula) {
        int index=0;
        for(Vendedor v:listaVendedores) {
            if (v.equals(cedula)) {
                index = listaVendedores.indexOf(v);
                break;
            }
        }

        return index;
    }


    public boolean eliminarVendedor(Vendedor vendedor) {
        Vendedor vendedorExisten = obtenerVendedor(vendedor.getCedula());
        if (vendedorExisten != null){
            for (Vendedor vendedor1: listaVendedores){
                if(vendedor1.getCedula().equalsIgnoreCase(vendedor.getCedula())){
                    listaVendedores.remove(vendedor1);
                    listaUsuarios.remove(vendedor1.getUsuario());
                    return true;

                }

            }
        }

        return false;
    }

    public boolean bucarVendedor(VendedorDtoId vendedorDtoId) {
        for (Vendedor vendedor: listaVendedores){
            if (vendedor.getCedula().equalsIgnoreCase(vendedorDtoId.cedula())){
                return true;
            }
        }
        return false;
    }

    public boolean agregarProducto(Producto producto) {
        Producto productoExistente = obtenerProducto(producto.getId());
        if(productoExistente == null){
            listaProductos.add(productoExistente);
            return true;
        }
        return false;
    }
    private Producto obtenerProducto(String id) {
        Producto producto = null;
        for(Producto producto1 : listaProductos){
            if(producto1.getId().equalsIgnoreCase(id)){
                producto = producto1;
                break;
            }
        }
        return producto;
    }

    public boolean actualizarProducto(Producto productoOld , Producto productoActualizado) {
        Producto producto = obtenerProducto(productoOld.getId());
        Producto productoMod = obtenerProducto(productoActualizado.getId());
        if(producto!=null){
            if(producto.getId().equals(productoActualizado.getId()) || productoMod==null){
                int index=getIndexProducto(productoOld.getId());
                listaProductos.set(index,productoActualizado);
                return true;
            }else{return false;}
        }else{return false;}
    }
    public int getIndexProducto(String id) {
        int index=0;
        for(Producto p:listaProductos) {
            if (p.equals(id)) {
                index = listaProductos.indexOf(p);
                break;
            }
        }

        return index;
    }

    public boolean eliminarProducto(Producto producto) {
        Producto productoExistente = obtenerProducto(producto.getId());
        if (productoExistente != null){
            for (Producto producto1: listaProductos){
                if(producto1.getId().equalsIgnoreCase(producto.getId())){
                    listaProductos.remove(producto1);
                    return true;

                }

            }
        }

        return false;
    }

    public boolean buscarProducto(ProductoDtoId productoDtoId) {
        for (Producto producto: listaProductos){
            if (producto.getId().equalsIgnoreCase(productoDtoId.id())){
                return true;
            }
        }
        return false;
    }
}
