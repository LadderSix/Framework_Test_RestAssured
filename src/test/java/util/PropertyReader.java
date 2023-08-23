package util;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {
    public static String getFrameworkProperty(String property) {
        try{
            Properties properties = new Properties();
            properties.load(new FileReader(Paths.get(System.getProperty("user.dir"), "src/test/resources/framework.properties").toString()));
            return properties.getProperty(property);
        } catch (Exception e){
            System.out.println("Error durante la obtenci�n de propiedades framework"+e.getMessage());
            return "Error en obtencion";
        }
    }

    //Atributos
    private static Properties props;

    public static String getProperty(String key){
        //Instanciamos un objero properties
        props = new Properties();
        // Definimos String con la ruta del fichero con las properties del proyecto
        String rutaFile = "C:\\IdeaProjects\\Framework_Test_RestAssured\\src\\test\\resources\\framework.properties";

        //Intentamos instancia el fichero como un objeto de tipo file
        try {
            InputStream input = new FileInputStream(rutaFile);
            //Cargamos las properties del fichero
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Retornamos una property en base a su key
        return props.getProperty(key);
    }
}
