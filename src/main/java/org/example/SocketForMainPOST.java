package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketForMainPOST {

    private final static String http = "POST /main HTTP/1.1\n"
            + "Content-Type: application/json\n"
            + "Content-Length: 52\n"
            + "Host: localhost\n"
            + "\n"
            + "{\n" +
            "  \"firstName\": \"Stepan\",\n" +
            "  \"lastName\": \"Bandera\"\n" +
            "}\n";

    private final static String host = "localhost";
    private final static int port = 8080;

    public static void main(String[] args) {
        System.out.println(doPost());
    }

    @SneakyThrows
    private static String doPost() {
        try (Socket socket = new Socket(host, port)) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(http);
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line).append("\n");
            }
            return response.toString();
        }
    }

}
