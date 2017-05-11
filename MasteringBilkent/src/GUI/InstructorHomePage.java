package GUI;

import ApplicationLogic.Course;
import ApplicationLogic.Instructor;
import ApplicationLogic.User;
import Storage.DatabaseManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.shape.*;

import java.util.ArrayList;


public class InstructorHomePage{
	
    static Scene scene;

    public static void start(Instructor instructor){

    	 DatabaseManager dbms = new DatabaseManager();
        //  BORDER PANE COMPONENTS
        //  CENTER
         ScrollPane sp = new ScrollPane();

         VBox centerMenu = new VBox();
         centerMenu.setPadding(new Insets(10));
         centerMenu.setSpacing(8);
         centerMenu.setAlignment(Pos.TOP_CENTER);

        //Mastering Bilkent Title
         String fontFamily = "Helvetica";
         double titleFontSize = 36;
        
         Text scenetitle1 = new Text("Mastering Bilkent Instructor Home Page");
         scenetitle1.setFill(Color.rgb(153, 0, 76));
         scenetitle1.setFont(Font.font(fontFamily, FontWeight.EXTRA_BOLD, titleFontSize));

         HBox logo = new HBox();
         logo.setPadding(new Insets(10));
         logo.setSpacing(8);
         logo.setAlignment(Pos.TOP_CENTER);
         logo.getChildren().addAll(scenetitle1);

        //Line
         Line line = new Line(0, 20, 750, 20);

         centerMenu.getChildren().addAll(logo, line);

        //Mastering Bilkent Title End
         ArrayList<Course> list = instructor.getCourseList();

         for(int i = 0 ; i < list.size();i++){
             Course course = list.get(i);

             VBox courseBox = new VBox();
             courseBox.setPadding(new Insets(15));
             courseBox.setSpacing(10);
             courseBox.setAlignment(Pos.TOP_CENTER);
             courseBox.setStyle("-fx-background-color:  #003366");

             Hyperlink courseLink = new Hyperlink(course.getContentName());
             int id = list.get(i).getContentId();
             courseLink.setOnAction(e -> LoginApp.myStage.setScene(CoursePage.startScene(course, instructor)));
             courseLink.setStyle("-fx-text-fill: white");
             courseLink.setFont(Font.font("Helvetica", 24));
             courseLink.setBorder(Border.EMPTY);
             Instructor courseCreator = new Instructor();
             try {
            	 courseCreator = dbms.getInstructor(course.getInstructorId());
             } catch (Exception e1) {
     			// TODO Auto-generated catch block
     			e1.printStackTrace();
     		}
             Text ins = new Text(courseCreator.getUserName()+" / Spring 2017");
             ins.setFill(Color.WHITE);
             ins.setFont(Font.font("Helvetica",18));
             courseBox.getChildren().addAll(courseLink, ins);

             //courseList.getChildren().addAll(courseBox);
             centerMenu.getChildren().addAll(courseBox);

         }

         sp.setFitToHeight(true);
         sp.setVmax(1000);
         sp.setPrefSize(115, 150);
         sp.setContent(centerMenu);

         //Instructor Course List End


         //Course Creation Link
         Hyperlink newCourseLink = new Hyperlink("Create Another Course");
         newCourseLink.setOnAction(e-> NewCourseBox.display(instructor));
         //Course Creation Link End
         centerMenu.getChildren().addAll( sp , newCourseLink);
         //  CENTER END

         //  LEFT
         VBox leftMenu = new VBox();
         leftMenu.setStyle("-fx-background-color: #990000;");
         leftMenu.setPadding(new Insets(10));
         leftMenu.setSpacing(8);

         //profile image
         GridPane imagegrid = new GridPane();
         Image avatar = new Image("file:avatar.png");
         ImageView iv1 = new ImageView();
         iv1.setFitWidth(240);
         iv1.setFitHeight(240);
         iv1.setImage(avatar);
         imagegrid.add(iv1,0,0);
         imagegrid.setAlignment(Pos.CENTER);

         //user information grid
         GridPane infogrid = new GridPane();
         infogrid.setPadding(new Insets(10,10,10,10));
         infogrid.setVgap(8);
         infogrid.setHgap(10);

         String fontFamily1 = "Helvetica";
         double titleFontSize1 = 16;

         Text userName = new Text(10, 20, instructor.getUserName() + " " + instructor.getUserSurname());
         userName.setFont(Font.font(fontFamily1, titleFontSize1));
         userName.setFill(Color.WHITE);

         Text userInst = new Text(10, 20, instructor.getUserInstitution());
         userInst.setFont(Font.font(fontFamily1, titleFontSize1));
         userInst.setFill(Color.WHITE);
         GridPane.setConstraints(userInst, 0,1,2,1);

         Text userDep = new Text(10, 20,  instructor.getUserDepartment()+ " / " + instructor.getUserTitle());
         userDep.setFont(Font.font(fontFamily1, titleFontSize1));
         userDep.setFill(Color.WHITE);
         GridPane.setConstraints(userDep, 0,2);

         Text userEmail = new Text(10, 20, instructor.getUserEmail());
         userEmail.setFont(Font.font(fontFamily1, titleFontSize1));
         userEmail.setFill(Color.WHITE);
         GridPane.setConstraints(userEmail, 0,3,3,3);
        
         Line line2 = new Line(0,0, 200, 0);
         line2.setStyle("-fx-stroke: #FFFFFF;");
         GridPane.setConstraints(line2, 0,6);
        
         infogrid.getChildren().addAll(userName, userInst, userDep, userEmail,line2);
       

        //navigator list

         Hyperlink link1 = new Hyperlink("All Courses");
         link1.setStyle("-fx-text-fill: white");
         link1.setOnAction(e -> LoginApp.myStage.setScene(Offerings.startScene(instructor)));


         Hyperlink link3 = new Hyperlink("Profile");
         link3.setStyle("-fx-text-fill: white");
         link3.setOnAction(e -> {
             LoginApp.myStage.setScene(ProfilePage.startScene(instructor));
             LoginApp.myStage.setTitle("Your Profile");
         });

         Hyperlink link4 = new Hyperlink("Send Feedback");
         link4.setStyle("-fx-text-fill: white");
         link4.setOnAction(e -> AlertBox.display("contact","mastering@bilkent.edu.tr"));

         Hyperlink link5 = new Hyperlink("Settings");
         link5.setStyle("-fx-text-fill: white");
         //link5.setOnAction(e -> );

         Hyperlink logoutlink = new Hyperlink("Logout");
         logoutlink.setStyle("-fx-text-fill: white");
         logoutlink.setOnAction(e -> {
             LoginApp.myStage.setScene(LoginApp.scene);
             LoginApp.myStage.setTitle("Mastering Bilkent");
         });

         //search bar
         final TextField searchfield = new TextField();
         searchfield.setPromptText("Find Course");
         searchfield.setPrefColumnCount(10);
         searchfield.getText();

         leftMenu.getChildren().addAll(imagegrid, infogrid, link1, link3, link4, link5,logoutlink, searchfield);
         leftMenu.setAlignment(Pos.TOP_LEFT);

         //  LEFT END

         //  BORDER PANE
         BorderPane layout1 = new BorderPane();
         layout1.setCenter(sp);
         layout1.setLeft(leftMenu);
         layout1.setStyle("-fx-background: #FFFFFF;");

         scene = new Scene(layout1);

     }

     public static Scene startScene(User user) {
         start((Instructor) user);
         return scene;
     }
}
