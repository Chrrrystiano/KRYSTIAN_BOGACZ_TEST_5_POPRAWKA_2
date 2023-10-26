package zad02;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import zad02.exceptions.InvalidJsonFormatException;
import zad02.model.Client;
import zad02.service.CurrencyService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CurrencyServiceTest {
    @Mock
    private Client client;

    @InjectMocks
    private CurrencyService currencyService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCorrectConversion() {
        double amount = 100;
        String currencyFrom = "USD";
        String currencyTo = "PLN";
        double expectedValue = 423.0878;

        when(client.getJson(100, currencyFrom, currencyTo)).thenReturn(jsonFromApi());
        double result = currencyService.exchange(amount, currencyFrom, currencyTo);
        assertEquals(expectedValue, result, 0.0001);
    }

    @Test(expected = InvalidJsonFormatException.class)
    public void testInvalidJsonFormat() {
        String invalidJson = "{\"result\": \"invalidValue\"}";
        when(client.getJson(100, "USD", "PLN")).thenReturn(invalidJson);
        currencyService.exchange(100, "USD", "PLN");
    }

    @Test(expected = RuntimeException.class)
    public void testErrorProcessingJson() {
        String jsonWithError = "invalidJson";
        when(client.getJson(100, "USD", "PLN")).thenReturn(jsonWithError);
        currencyService.exchange(100, "USD", "PLN");
    }

    private String jsonFromApi() {
        return "{\n" +
                "    \"success\": true,\n" +
                "    \"query\": {\n" +
                "        \"from\": \"USD\",\n" +
                "        \"to\": \"PLN\",\n" +
                "        \"amount\": 100\n" +
                "    },\n" +
                "    \"info\": {\n" +
                "        \"timestamp\": 1698340863,\n" +
                "        \"rate\": 4.230878\n" +
                "    },\n" +
                "    \"date\": \"2023-10-26\",\n" +
                "    \"result\": 423.0878\n" +
                "}";
    }
}
