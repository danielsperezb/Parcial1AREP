/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reflectivechatgpt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//ciclo,quemar,implemntar refelxion
/**
 *
 * @author daniel.perez-b
 */



public class HttpServer {
    

    public static void main(String[] args) throws IOException {
                
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(38000);

            while (true) {
                Socket clientSocket = null;
                try {
                    System.out.println("Listo para recibir ...");
                    clientSocket = serverSocket.accept();
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
                PrintWriter out = new PrintWriter(
                        clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                String inputLine, outputLine;
                while ((inputLine = in.readLine()) != null) {

                    if(inputLine.contains("Class")){
                        //System.out.println("ACA");
                        //String Class = "java.lang.String";
                        String Class = inputLine.replace("GET /consulta?comando=Class(", "");
                        Class = Class.replace("POST /consulta?comando=Class(", "");
                        Class = Class.replace(") HTTP/1.1", "");
                        System.out.println(Class);
                       
 
                    }
                    //System.out.println("Recibí: " + inputLine);
                    
                    if (!in.ready()) {
                        break;
                    }
                }
                
                
               outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "    <head>\n" +
                        "        <title>Form Example</title>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <h1>Form with GET</h1>\n" +
                        "        <form action=\"/consulta\">\n" +
                        "            <label for=\"name\">Comando:</label><br>\n" +
                        "            <input type=\"text\" id=\"comando\" comando=\"comando\" value=\"John\"><br><br>\n" +
                        "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                        "        </form> \n" +
                        "        <div id=\"getrespmsg\"></div>\n" +
                        "\n" +
                        "        <script>\n" +
                        "            function loadGetMsg() {\n" +
                        "                let comandoVar = document.getElementById(\"comando\").value;\n" +
                        "                const xhttp = new XMLHttpRequest();\n" +
                        "                xhttp.onload = function() {\n" +
                        "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                        "                    this.responseText;\n" +
                        "                }\n" +
                        "                xhttp.open(\"GET\", \"/consulta?comando=\"+comandoVar);\n" +
                        "                xhttp.send();\n" +
                        "            }\n" +
                        "        </script>\n" +
                        "\n" +
                        "        <h1>Form with POST</h1>\n" +
                        "        <form action=\"/consultapost\">\n" +
                        "            <label for=\"postcomando\">Comando:</label><br>\n" +
                        "            <input type=\"text\" id=\"postcomando\" comando=\"comando\" value=\"John\"><br><br>\n" +
                        "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postcomando)\">\n" +
                        "        </form>\n" +
                        "        \n" +
                        "        <div id=\"postrespmsg\"></div>\n" +
                        "        \n" +
                        "        <script>\n" +
                        "            function loadPostMsg(comando){\n" +
                        "                let url = \"/consulta?comando=\" + comando.value;\n" +
                        "\n" +
                        "                fetch (url, {method: 'POST'})\n" +
                        "                    .then(x => x.text())\n" +
                        "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n" +
                        "            }\n" +
                        "        </script>\n" +
                        "    </body>\n" +
                        "</html>";
               
               
                out.println(outputLine);
                out.close();
                in.close();
                
              
            
                
                //clientSocket.close();
                //serverSocket.close();
            }

        } catch (IOException e) {
            System.err.println("Could not listen on port: 38000.");
            System.exit(1);
        }
    }

     public String ClassMethod(String ClassName){
        return "Retorna una lista de campos declarados y métodos declarados ";
    }
  
 


    
    
   
    
   
}
