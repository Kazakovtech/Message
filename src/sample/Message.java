package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import java.awt.*;
import java.awt.peer.CanvasPeer;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Message {

    Socket socket;
    //Пинг ip
    public static List<String> PingIpAddr(String ip) throws IOException
    {
        ProcessBuilder pb = new ProcessBuilder("ping", ip);
            //ProcessBuilder pb = new ProcessBuilder("ping", "-c 5", ip);
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(pb.start().getInputStream(),"Cp866"));
        while (!stdInput.ready())
        {
            // custom timeout handling
        }

        String line;
        ArrayList<String> output = new ArrayList<>();
        while ((line = stdInput.readLine()) != null)
        {
            output.add(line);
            System.out.println(line);
        }
        return output;
    }

    //Обработка ответа на пинг
    public static String pingAnswer(List<String> pingOutPut){
        String answer="";
        String er="Пакетов: отправлено = 4, получено = 4, потеряно = 0";
        for(String line :pingOutPut){
            if(line.contains(er)){
                answer="Пинг успешно прошел";
                System.out.println(answer);
                break;
            }else {
                answer="УПС";
            }
        }
        return answer;
    }

    //Соединение с сервером
    public Socket connect(String ip,int port)throws UnknownHostException, IOException{

       //socket = new Socket(ip,port);

        try {


            socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 1000);
        }
        catch (Exception e){}
        return socket;
    }

    //Есть ли соединение?
    public String isConnectedSocket( ){

        String connectionMess;
        if(socket.isConnected()){
            connectionMess="ok";
            System.out.println(connectionMess);
        }else {
            connectionMess="err";
            System.out.println(connectionMess);
        }
        return connectionMess;
    }

    //Отправка сообщения в существующее соединение
    public void sendMessage(String message) throws UnknownHostException, IOException {


        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        out.write(message);
        out.flush();

       // out.close();
    }

    BufferedReader in;
    ServerSocket server;
Controller r = new Controller();

    public void readMessage(int port, TextArea hist) throws UnknownHostException, IOException, ClassNotFoundException {

        server = new ServerSocket(port);
        socket = server.accept();
        Scanner sc=new Scanner(socket.getInputStream());
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        //Parent parent = loader.load();
        //r = loader.getController();


        while (true) {
            try {
                String red = sc.nextLine();
                System.out.println(red);
                //r.getHA().setText(r.getHA().getText()+"Собеседник: "+red);
                Thread.sleep(100);
                hist.setText(hist.getText()+"Собеседник: "+red+"\n");
                Main.getStage().toFront();
                Main.getStage().requestFocus();


                //.getHA().setText(r.getHA().getText()+"Собеседник: "+red);
            }
            catch (Exception e){}

        }



        //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));


       // String line;
      //  while (true) {
       //     line = in.readLine();
       //     System.out.println(line);

       // }
    }

        public void showMessage()
        {

        }


}
