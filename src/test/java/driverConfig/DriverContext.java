package driverConfig;

import constants.Navegador;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import util.PropertyReader;

public class DriverContext {
    private static DriverManager driverManager = new DriverManager();
    private static Navegador tipoNavegador;
    private static String ambienteURL = "";

    public static String getAmbienteURL() {
        return ambienteURL;
    }


    public static void setAmbienteURL(String ambienteURL) {
        DriverContext.ambienteURL = ambienteURL;
    }

    public static String getTipoNavegador() {
        return tipoNavegador.toString();
    }

    public static void setTipoNavegador(Navegador tipoNavegador) {
        DriverContext.tipoNavegador = tipoNavegador;
    }

    public static void setUp(String nav, String ambURL) {
        try{
            setTipoNavegador(convertTipoNavegador(nav));
            setAmbienteURL(PropertyReader.getFrameworkProperty(ambURL));
            System.out.println("Driver Context " + "Navegador: " + nav + " /Url: " + ambURL);
            driverManager.resolveDriver(tipoNavegador, ambienteURL);
        }catch(Exception e){
            System.out.println("Error durante apertura de driver: "+ e.getMessage());
            Assertions.fail();
        }
    }

    public static WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public static void quitDriver() {
        if (driverManager != null) {
            driverManager.getDriver().quit();
        }

    }

    public static Navegador convertTipoNavegador(String nav){
        Navegador navegador;
        switch (nav.toLowerCase()){
            case "chrome":
            default:
                navegador = Navegador.Chrome;
                break;
            case "firefox":
                navegador = Navegador.Firefox;
                break;
            case "edge":
                navegador = Navegador.Edge;
                break;
        }
        return navegador;
    }
}
