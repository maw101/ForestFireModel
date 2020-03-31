module java.forestfiremodel {
   requires javafx.controls;
   requires javafx.fxml;

   opens forestfiremodel.gui to javafx.fxml, javafx.base;

   exports forestfiremodel.gui;
}