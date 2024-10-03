package co.uniquindio.marketplacefx.marketplaceapp.utils;

import co.uniquindio.marketplacefx.marketplaceapp.factory.ModelFactory;
import co.uniquindio.marketplacefx.marketplaceapp.model.MarketPlace;
import co.uniquindio.marketplacefx.marketplaceapp.model.PrestamoObjeto;
import co.uniquindio.marketplacefx.marketplaceapp.model.Vendedor;

public class DataUtil {
    public static PrestamoObjeto inicializarDatos() {
        PrestamoObjeto prestamoObjeto = new PrestamoObjeto();
        Vendedor vendedor = Vendedor.builder()
                .nombre("Jesus")
                .apellido("Luligo")
                .cedula("1010")
                .direccion("Km 5")
                .usuario("Yisus")
                .contrasena("3767")
                .build();

        Vendedor vendedor2 = Vendedor.builder()
                .nombre("Luis")
                .apellido("Ruiz")
                .cedula("4040")
                .direccion("Km 10")
                .usuario("Luixd")
                .contrasena("3030")
                .build();

        prestamoObjeto.getListaVendedores().add(vendedor);
        prestamoObjeto.getListaVendedores().add(vendedor2);
        return prestamoObjeto;
    }
}
