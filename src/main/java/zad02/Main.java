package zad02;

import zad02.model.Client;

public class Main {
    public static void main(String[] args) {

        Client client = new Client();
        System.out.println(client.getJson(100, "USD", "PLN"));
    }
}
