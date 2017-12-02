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
            return "/fxml/login.fxml";
        }
    }, SALESPIPELINE {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("pipeline.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/pipelineview.fxml";
        }
    }, ORDERNOTES {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("ordernotes.title");
        }

        @Override
        String getFxmlFile() {
            return "/fxml/ordernotes.fxml";
        }
    };
    
    abstract String getTitle();
    abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
