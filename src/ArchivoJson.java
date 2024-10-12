//Clase para crear el archivo JSON.
import com.google.gson.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArchivoJson {
    public void generaJson(String primeraMoneda, String segundaMoneda, double cantidad,
                           double dineroConvertido) {

        JsonObject objetoJson = new JsonObject();
        objetoJson.addProperty("De la Primera Moneda", primeraMoneda);
        objetoJson.addProperty("A la Segunda Moneda", segundaMoneda);
        objetoJson.addProperty("Cantidad principal", cantidad);
        objetoJson.addProperty("Cantidad convertida", dineroConvertido);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonArray listaDeLasConversiones = new JsonArray();

        try (FileReader lectorDelArchivo = new FileReader("resultadoDeLaConversion.json")) {
            JsonElement elemento = JsonParser.parseReader(lectorDelArchivo);
            if (elemento.isJsonArray()) {
                listaDeLasConversiones = elemento.getAsJsonArray();
            }
        } catch (IOException e) {
            System.out.println("No se encontró el archivo JSON existente, se creará uno nuevo...");
        }
        listaDeLasConversiones.add(objetoJson);

        try (FileWriter archivo = new FileWriter("resultadoDeLaConversion.json")) {
            archivo.write(gson.toJson(listaDeLasConversiones));
            System.out.println("Se actualizó el archivo JSON con la nueva conversión.");
        } catch (IOException e) {
            System.out.println("Se generó un error al actualizar el archivo JSON: " + e.getMessage());
        }
    }
}
