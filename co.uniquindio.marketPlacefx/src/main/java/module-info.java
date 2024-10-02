module co.uniquindio.marketplacefx.marketplaceapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.uniquindio.marketplacefx.marketplaceapp to javafx.fxml;
    exports co.uniquindio.marketplacefx.marketplaceapp;
}