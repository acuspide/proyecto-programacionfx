package co.uniquindio.marketplacefx.marketplaceapp.utils;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.model.MarketPlace;
import co.uniquindio.marketplacefx.marketplaceapp.model.PrestamoObjeto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Usuario;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;

public class DataUtil {
    public static PrestamoObjeto inicializarDatos() {
        PrestamoObjeto prestamoObjeto = new PrestamoObjeto();
        Vendedor vendedor = Vendedor.builder()
                .nombre("Jesus")
                .apellido("Luligo")
                .cedula("1010")
                .direccion("Km 5")
                .usuario(new Usuario("yisus","1234","1010"))
                .build();

        Vendedor vendedor2 = Vendedor.builder()
                .nombre("Luis")
                .apellido("Ruiz")
                .cedula("4040")
                .direccion("Km 10")
                .usuario(new Usuario("lucho","4321","4040"))
                .build();

        prestamoObjeto.getListaVendedores().add(vendedor);
        prestamoObjeto.getListaVendedores().add(vendedor2);
        prestamoObjeto.getListaUsuarios().add(vendedor.usuario);
        prestamoObjeto.getListaUsuarios().add(vendedor2.usuario);
        return prestamoObjeto;
    }
}
