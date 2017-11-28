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
            return "/fxml/Main.fxml";
        }
    }, SECOND {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("second.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/second.fxml";
        }
    }, THIRD {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("third.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/third.fxml";
        }
    };
    
    abstract String getTitle();
    abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
