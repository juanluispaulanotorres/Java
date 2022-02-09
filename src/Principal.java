import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Principal {
    public static void main(String[] arg) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        List<Persona> lista = new ArrayList<>();

        String linea;

        String nombre;
        String poblacion;
        int edad;

        int i = 0;

        try {
            archivo = new File("personas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero

            while ((linea = br.readLine()) != null) {
                try {
                    nombre = linea.split(":")[0];
                    poblacion = linea.split(":")[1];

                    Optional <String> oPoblacion = Optional.ofNullable(poblacion);

                    if (oPoblacion.get() == "")
                        poblacion = "Desconocida";

                    edad = Integer.parseInt(linea.split(":")[2]);

                } catch (ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                // Almacenar la clase "Persona" en la lista

                Persona persona = new Persona(nombre, poblacion, edad);
                lista.add(persona);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Imprimir la lista usando STREAMS
        lista.stream().filter(persona -> persona.getEdad() < 25).forEach(System.out::println);

        try {
            fr.close();

        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}