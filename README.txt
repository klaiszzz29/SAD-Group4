compile jdbc in terminal:
javac -d bin -cp ".;lib/mysql-connector-j-8.4.0.jar" *.java    //compile
java -cp "bin;lib/mysql-connector-j-8.4.0.jar" DBTest     //test if it works



-----------FOR CLINIC MANAGEMENT ONLY! -------------
download java fx sdk
open terminal(cmd)
cd ClinicManagement
javac --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "lib/mysql-connector-j-8.4.0.jar" -d bin src/main/java/*.java