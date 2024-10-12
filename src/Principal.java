//Clase principal que contiene el menú para que el usuario pueda interactuar
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConvertidorDeMonedas convertir = new ConvertidorDeMonedas();
        ArchivoJson archivoJson = new ArchivoJson();

        int opcion = 0;
        while (opcion != 7) {
            System.out.println("//////////////////////////////////////////////////////////////");
            System.out.println("Bienvenido al Convertidor de Monedas!!!");
            System.out.println("1) Dólar => Peso argentino");
            System.out.println("2) Peso argentino => Dólar");
            System.out.println("3) Dólar => Real brasileño");
            System.out.println("4) Real brasileño => Dólar");
            System.out.println("5) Dólar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dólar");
            System.out.println("7) Salir");
            System.out.print("Elija una de las opciones del menú que desee realizar: ");
            opcion = teclado.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingrese la cantidad de dinero que desee convertir: ");
                double cantidad = teclado.nextDouble();

                String primeraMoneda = "", segundaMoneda = "";
                switch (opcion) {
                    case 1: primeraMoneda = "USD"; segundaMoneda = "ARS"; break;
                    case 2: primeraMoneda = "ARS"; segundaMoneda = "USD"; break;
                    case 3: primeraMoneda = "USD"; segundaMoneda = "BRL"; break;
                    case 4: primeraMoneda = "BRL"; segundaMoneda = "USD"; break;
                    case 5: primeraMoneda = "USD"; segundaMoneda = "COP"; break;
                    case 6: primeraMoneda = "COP"; segundaMoneda = "USD"; break;
                }

                try {
                    double dineroConvertido = convertir.convertidorDeMoneda(primeraMoneda, segundaMoneda, cantidad);
                    System.out.println("El dinero $" + cantidad + " "+ primeraMoneda + " convertido a "
                            + segundaMoneda + " es: $" + dineroConvertido);
                    archivoJson.generaJson(primeraMoneda, segundaMoneda, cantidad, dineroConvertido);
                } catch (Exception e) {
                    System.out.println("Ocurrio un error en la conversion: " + e.getMessage());
                }
            } else if (opcion == 7) {
                System.out.println("Programa Finalizado!!!");
            } else {
                System.out.println("Opción ingresada no valida, ingrese una opción correcta....");
            }
        }
        teclado.close();
    }
}
