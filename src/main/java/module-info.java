module com.srabby {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires selenium.api;
    requires selenium.remote.driver;
    requires selenium.chrome.driver;

    opens com.srabby to javafx.fxml;
    exports com.srabby;
}
