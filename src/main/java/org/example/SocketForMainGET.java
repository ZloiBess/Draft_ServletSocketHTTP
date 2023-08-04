package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.net.Socket;

public class SocketForMainGET {

    private final static String http = "GET /main HTTP/1.1\n" + "Host: localhost\n\n";
    private final static String host = "localhost";
    private final static int port = 8080;

    public static void main(String[] args) throws IOException {
        System.out.println(doGet());
    }

    @SneakyThrows
    private static String doGet() {
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
