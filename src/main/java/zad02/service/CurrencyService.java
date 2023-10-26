package zad02.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.DoubleNode;
import lombok.RequiredArgsConstructor;
import zad02.exceptions.InvalidJsonFormatException;
import zad02.model.Client;

import java.io.IOException;

@RequiredArgsConstructor
public class CurrencyService {
    private final Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public double exchange(double amount, String from, String to) {
        String json = client.getJson(amount, from, to);
        return parseJson(json);
    }

    private double parseJson(String json) {
        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            JsonNode resultNode = jsonNode.path("result");

            if (resultNode instanceof DoubleNode) {
                return resultNode.asDouble();
            } else {
                throw new InvalidJsonFormatException("Invalid JSON format: 'result' is not a double.");
            }
        } catch (IOException e) {
            throw new InvalidJsonFormatException("Error processing JSON", e);
        }
    }
}
