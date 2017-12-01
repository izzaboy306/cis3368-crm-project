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
    }, SALESPIPELINE {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("salespipeline.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/salespipelineview.fxml";
        }
    }, MANAGERPIPELINE {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("managerpipeline.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/managerpipelineview.fxml";
        }
    }, LOGIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/login.fxml";
        }
    };
    
    abstract String getTitle();
    abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
