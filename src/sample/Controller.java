package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class Controller {

    @FXML
    Button testConnection= new Button();
    //@FXML
    //TextArea ipTestConnection = new TextArea();
    @FXML
    AnchorPane anchor = new AnchorPane();
    @FXML
    Label pingAnswer = new Label();
    @FXML
    Label connectAnswer = new Label();
    @FXML
    TextArea textAreaMessage = new TextArea();
    @FXML
    TextArea history = new TextArea();
    @FXML
            Button sendMessageButton = new Button();

    Message message;
    String[] hteeh;
@FXML
TextArea ipadress = new TextArea();

public void conrollerStart(){

}

    public void t() throws IOException {
       // String ip = ipTestConnection.getText();
       // System.out.println(ip);
        String ip =ipadress.getText();
        message=new Message();
        //ping
        //List<String> out = message.PingIpAddr(ip);

        //String answer = message.pingAnswer(out);
        //pingAnswer.setText(answer);
        //System.out.println(answer);
        //connect
        message.connect(ip,8086);
        connectAnswer.setText(message.isConnectedSocket());
        if (message.isConnectedSocket()=="ok")
        {sendMessageButton.setDisable(false);
        textAreaMessage.setDisable(false);}

    }
    public TextArea getHA(){
        return history;
    }
    public void sendMessage_Button()throws IOException {
        //send
        String s=textAreaMessage.getText()+"\n";
        textAreaMessage.setText("");
        message.sendMessage(s);
        System.out.println(s);
        addHistory("Ÿ: "+s);
    }
    public void addHistory(String s)
    {
        history.setText(history.getText()+s);
    }

   public void readMessage_Button() throws IOException, ClassNotFoundException {
       message=new Message();
       ipadress.setDisable(false);
       testConnection.setDisable(false);
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   message.readMessage(8086,history);
               } catch (IOException e) {
                   e.printStackTrace();
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               }
           }
       }).start();

   }
}
