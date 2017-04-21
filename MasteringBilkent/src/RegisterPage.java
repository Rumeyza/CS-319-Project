import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.collections.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
 

public class RegisterPage {

    static Scene scene;

    public static void start() {
    	
    	BorderPane border = new BorderPane();
    	VBox centerMenu = new VBox();
        centerMenu.setPadding(new Insets(10));
        centerMenu.setSpacing(8);
        centerMenu.setAlignment(Pos.TOP_CENTER);

    	 String fontFamily = "Helvetica";
         double titleFontSize = 48;

         Text scenetitle1 = new Text("MASTERING");
         scenetitle1.setFill(Color.BLUE);
         scenetitle1.setFont(Font.font(fontFamily, FontWeight.EXTRA_BOLD, titleFontSize));

         Text scenetitle2 = new Text(" BILKENT");
         scenetitle2.setFill(Color.RED);
         scenetitle2.setFont(Font.font(fontFamily, FontWeight.EXTRA_BOLD, titleFontSize));

         HBox logo = new HBox();
         logo.setPadding(new Insets(10));
         logo.setSpacing(8);
         logo.setAlignment(Pos.TOP_CENTER);
         logo.getChildren().addAll(scenetitle1, scenetitle2);

      //   Hyperlink buttonA = new Hyperlink("Register Another Course");

         centerMenu.getChildren().addAll(logo);
    	border.setTop(centerMenu);
        //Grid
    	 GridPane grid = new GridPane();
         grid.setAlignment(Pos.TOP_CENTER);
         grid.setHgap(30);
         grid.setVgap(30);
         grid.setPadding(new Insets(25, 25, 25, 25));
         grid.setStyle("-fx-background: #FFFFFF;");

        //TITLE
         //String fontFamily = "Helvetica";
         //double titleFontSize = 48;
         /*
         TextFlow textFlow = new TextFlow();
         
         Text scenetitle1 = new Text("MASTERING");
         scenetitle1.setFill(Color.BLUE);
         scenetitle1.setFont(Font.font(fontFamily, FontWeight.EXTRA_BOLD, titleFontSize));
       
         Text scenetitle2 = new Text(" BILKENT");
         scenetitle2.setFill(Color.RED);
         scenetitle2.setFont(Font.font(fontFamily, FontWeight.EXTRA_BOLD, titleFontSize));
        
         textFlow.getChildren().addAll(scenetitle1, scenetitle2);     
         grid.add(textFlow, 2, 0);*/
        //user name
         Label userName = new Label("Enter Your Name:");
         grid.add(userName, 0, 1);
         TextField userNameTextField = new TextField();
         userNameTextField.setPrefWidth(10); 
         grid.add(userNameTextField, 1, 1);
       

        //user surname
         Label userSurname = new Label("Enter Your Surname:");
         grid.add(userSurname, 0, 2);
         TextField userSurnameTextField = new TextField();
         grid.add(userSurnameTextField, 1, 2);
         
        //user email
         Label userEmail = new Label("Enter Your E-mail:");
         grid.add(userEmail, 0, 3);
         TextField userEmailTextField = new TextField();
         grid.add(userEmailTextField, 1,3);
         

        //user choose password
         
         Label pw = new Label("Enter Your Password:");
         grid.add(pw, 0,4);
         PasswordField pwBox = new PasswordField();
         grid.add(pwBox, 1,4);

        //user re enter password
         Label repw = new Label("Re-Enter Your Password:");
         grid.add(repw, 0,5);
         PasswordField repwBox = new PasswordField();
         grid.add(repwBox, 1,5);

        //user department
         Label userDepartment = new Label("Institution:");
         grid.add(userDepartment, 0,6);
         TextField userDepartmentTextField = new TextField();
         grid.add(userDepartmentTextField, 1,6);
         

         Button registerButton = new Button("Complete Registration");         
         grid.add( registerButton, 1,7);
         
         // User Roles:
         ObservableList<String> userRoles = 
         	    FXCollections.observableArrayList(
         	        "a Student",
         	        "an Instructor"
         	    );
         	final ComboBox<String> userRolesBox = new ComboBox<String>(userRoles);
         	userRolesBox.setPromptText("I am");
         	grid.add( userRolesBox, 0, 0);
         	
         	userRolesBox.setOnAction((event)->{
         		String role = (String) userRolesBox.getSelectionModel().getSelectedItem();
         		if( role == "a Student"){    	        	  
         	        //User Status for Student:
         	        ObservableList<String> studentStatus = 
         	        	    FXCollections.observableArrayList( "Undergraduate", "Graduate", "None"   );
         	        final ComboBox<String> studentStatusBox = new ComboBox<String>(studentStatus);
         	        studentStatusBox.setPromptText( "My Degree is");
         	        grid.add( studentStatusBox, 1,0);
         		}
         		else{
         	        ObservableList<String> instructorStatus = 
         	        	    FXCollections.observableArrayList( "Prof.","Assoc. Prof", "Asst. Prof", "Instructor");
         	        final ComboBox<String> instructorStatusBox = new ComboBox<String>(instructorStatus);
         	        instructorStatusBox.setPromptText( "My Degree is");
         	        grid.add( instructorStatusBox, 1,0);

         		}
         	});
        
        border.setCenter(grid);
        scene = new Scene(border);
    }

    public static Scene startScene() {
        start();
        return scene;
    }
}
    