package main.view;

import java.util.ResourceBundle;

public enum FxmlView {

    MAIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("main.app.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/splashscreen.fxml";
        }
    }, SALESMAIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("salesmain.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/salesmain.fxml";
        }
    }, MANAGERMAIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("managermain.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/managermain.fxml";
        }
    };
    
    abstract String getTitle();
    abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
