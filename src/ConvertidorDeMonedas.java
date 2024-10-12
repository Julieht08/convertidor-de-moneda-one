//Clase del cnvertidor de moneda, donde se hace uso de la API y los HTTP
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConvertidorDeMonedas {

    public double convertidorDeMoneda(String primeraMoneda, String segundaMoneda, double cantidad)
            throws Exception {

        String direccion = "https://v6.exchangerate-api.com/v6/5c2abceedf3e5825ca824c38/latest/"
                + primeraMoneda;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(new URI(direccion))
                .GET()
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        JsonObject objetoJson = JsonParser.parseString(respuesta.body()).getAsJsonObject();

        JsonObject tasaDeConversion = objetoJson.getAsJsonObject("conversion_rates");
        double tipoDeCambio = tasaDeConversion.get(segundaMoneda).getAsDouble();

        return cantidad * tipoDeCambio;
    }
}
