package ydl.study.javafx.webViewCall;

import java.net.URL;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        
        final URL urlHello = getClass().getResource("hello.html");
        webEngine.load(urlHello.toExternalForm());
        
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<State>(){
                    
                    @Override
                    public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
                        if(newState == State.SUCCEEDED){
                        	
                            JSObject window = (JSObject)webEngine.executeScript("window");
                            window.setMember("app", new JavaApplication());
                            System.out.println("State.SUCCEEDED");
                            
                            
                        }
                    }
                });
        
        
       /* JSObject window = (JSObject)webEngine.executeScript("window");
        window.setMember("app", new JavaApplication());
        */
        
		Scene scene = new Scene(webView, 640, 480);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
    public class JavaApplication {
        public void callFromJavascript(String msg) {
            System.out.println("Click from Javascript: " + msg);
        }
    }
}
