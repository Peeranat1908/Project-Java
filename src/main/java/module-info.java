module ku.cs {
    requires javafx.controls;
    requires javafx.fxml;


    opens ku.cs.cs211671project to javafx.fxml;
    exports ku.cs.cs211671project;
    exports ku.cs.controllers;
    opens ku.cs.controllers to javafx.fxml;
    exports ku.cs.models;
    opens ku.cs.models to javafx.base;
    exports ku.cs.controllers.components;
    opens ku.cs.controllers.components to javafx.fxml;
}