/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

//Importamos la clase para usar las clses de fecha
import java.time.LocalDate;
import java.time.Month;

//Importamos el paquete para usar las clases con expresiones regulares
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Paquete con la Clase TOKENIZER par probar como funciona, cuenta palabras entre espacios en blanco(No se usa en la tarea)
import java.util.StringTokenizer;

/**
 * Clase para validar los datos que se introducen desde la clse Principal
 * @author oscar
 */
public class ValidarDatosVehiculo {
   
    /**
     * Metodo estatico para validar que los Km no son negativos ni cero.
     * @param km Dato de tipo int que seran los km a validar
     * @return Devuelve true si el dato es mayor o igual a 1, y false en caso contrario.
     */
    public static boolean validarKm(int km){
        boolean km0;        
        km0=(km>=1) ? true : false;
        return km0;
    }
    
    
    
    /**
     * Metodo estatico para validar que el precio no es negativo.
     * @param precio Dato de tipo int que sera el precio a validar
     * @return Devuelve true si el dato es mayor o igual a 1, y false en caso contrario.
     */
    public static boolean validarPrecio(int precio){
        boolean precio0;        
        precio0=(precio>=0) ? true : false;
        return precio0;
    }
    
    
    
    
    /**
     * Metodo para validar que una fecha del tipo LocalDate es anterior a otra
     * @param fechaActual Parametro con la fecha actual, tipo LocalDate
     * @param fechaMatriculacion Parametro con la fecha que se quiere comparar con la actual, tipo LocalDate
     * @return Devuelve true si la fechaMatriculacion es anterior a fechaActual, y false en caso contrario.
     */
    public static boolean validaFechaMatriculacion(LocalDate fechaActual, LocalDate fechaMatriculacion){
        boolean fecha;
        fecha= fechaMatriculacion.isBefore(fechaActual) ? true : false; 
        return fecha;
    }
    
    
    
    
    /**
     * Metodo para validar sin un dato tipo String requerido por teclado esta vacio o no
     * @param dato Parametro String que contiene el dato a validar
     * @return Devuelve true si tiene algun caracter y false si esta vacio.
     */
    public static boolean validarDatoTexto(String dato){
        boolean vacio=false;
        vacio=(dato.length()>=1) ? true:false;
        return vacio;
        
    }
    
    
    
    
    /**
     * Metodo para validar si el String que nos llega por parametro cumple con el formato
     * requerido.
     * El formato que usamos como patron es NNNNLLL
     * Donde NNNN es un numero entre 0000 y 9999. LLL son letras mayusculas del abecedario
     * ej:1234ABC
     * 
     * @param matricula Parametro tipo String a validar
     * @return Devuelve true si cumple con el patron, false si no lo cumple
     */
    public static boolean validarMatricula(String matricula){
        boolean valida=false;        
        String patron="[0-9]{4}[A-Z]{3}";
        Pattern p=Pattern.compile(patron);
        Matcher m=p.matcher(matricula);
        if (m.matches()) valida=true;        
        return valida;
    }
    
    
    
    /**
     * Metodo para validar el Dni,comprueba que el parametro que recibe tiene el formato
     * 'NNNNNNNNL' como '12345678A'
     * No comprueba que la letra del dni sea valida, solo que el formato tenga 8 numeros y 1 letra.
     * Devuelve true si cumple el formato y false si no lo cumple
     * @param dni Parametro String que es el dni a validar
     * @return 
     */    
    public static boolean validarDniExpRegular(String dni){
        boolean valido=false;
        String patron="[0-9]{8}[A-Za-z]{1}";
        
        Pattern p=Pattern.compile(patron);
        Matcher m=p.matcher(dni);
        if (m.matches()) valido=true;
        return valido;
    
    }
        
    
    /**
     * Metodo para contar cuantas palabras tiene un string.
     * Cuenta las palabras que estan separadas entre espacios en blanco, se considera cualquier
     * palabra lo que este separado entre un hueco vacio, ya sea letra o palabra.
     * ej:"hola soy oscar", contara que hay 2 huecos en blanco, o sea 3 palabras
     * ej"hola soy oscar             ",contara que hay 2 huecos en blanco, o sea que son 3 palabras
     * ej:"   hola     soy      oscar", contara que hay 2 huecos en blanco, o sea que son 3 palabras
     * ej:"a 2 v",contara que hay 2 huecos, o sea 3 palabras
     * 
     * Se eliminan los espacios blancos que puede tener el String al principio y al final
     * Un espacio en blanco como "    "  cuenta como un espacio en blanco como " ", o sea un hueco.
     * @param nombrePropietario Parametro tipp String que contiene la cadena a validar
     * @param longitud Parametro tipo int con la longitud que no puede superar la cadena.
     * @return  Devuelve true si la cadena no supera los 40 caracteres y tiene 3 palabras(2huecos)
     */
    public static boolean validarNombreY2Apellidos(String nombrePropietario, int longitud){
        boolean valido=false;
        char caracter;
        int palabras=0;
        
        nombrePropietario=nombrePropietario.trim();
        for(int i=0;i<nombrePropietario.length();i++){
            caracter=nombrePropietario.charAt(i);
            if(caracter ==' ' && nombrePropietario.charAt(i+1) != ' '){
                palabras++;
            }
        }
        if (palabras>=2 && nombrePropietario.length()<longitud) { valido=true; }
        return valido;
    }
    
    
    
     /**
     *    NO USO ESTE METODO EN EL EJERCECIO, SE USA EL QUE COMPRUEBA CON EXP REGULARES
     * Metodo para comprobar si el String nombre del Propietario, contiene un nombre y dos apellidos
     * Tambien comprueba que la longitud del parametro no exceda de 40 caracteres
     * @param nombre Parametro tipo String que contiene el dato a validar
     * @param longitud parametro tipo int que contiene la longitud maxima de la cadena a validar
     * @return Devuelve true si es valido, false si no es valido.
     */
    public static boolean validarNombreApelldos(String nombre, int longitud){
        boolean valido=false;
        int cantidadPalabras=0;

        StringTokenizer st = new StringTokenizer(nombre);
        System.out.println("Número de palabras: " + st.countTokens());  
        cantidadPalabras=st.countTokens();
        if(cantidadPalabras >=3 && nombre.length()<=longitud ){ valido=true; }
        return valido;
    }
}
