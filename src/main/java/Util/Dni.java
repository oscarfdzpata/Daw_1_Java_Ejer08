/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 * Clase Dni basada en los ejemplos de la unidad 5.
 * Hago unas modificaciones para adapatarla al ejercicio planteado.
 * 
 * @author oscar fernandez pata
 */
public class Dni {
    private int numDNI; //Almacenara el numero del dni, la letra no la almacena porque es calculada en la ejecucion del metodo.
    
    //Con esta cadena disponible, es muy sencillo implementar el algoritmo del módulo 23:
    private static final String LETRAS_DNI= "TRWAGMYFPDXBNJZSQVHLCKE";
    
    
    /**
     * Constructores de la clase sobrecargados. No hago uso de ellos.
     * No valida el dni, se tendra que validar antes con su metodo estatico para evitar errores.
     * @param dni 
     */
    public Dni(String dni){
        this.setDni(dni);
    }
    
    public Dni(int dni){
        this.setDni(dni);
    }
    
    /**
     * Este método estático ha sido definido como privado, aunque también podría haber sido definido como público
     * para que otros objetos pudieran hacer uso de él (típico ejemplo de uso de un método estático).
     * @param dni
     * @return 
     */
    
    private static char calcularLetraNIF (int dni) {

      char letra;

      // Cálculo de la letra NIF

      letra= LETRAS_DNI.charAt(dni % 23);       

       // Devolución de la letra NIF

       return letra;

    }
    
    /**
     * Para poder manipular adecuadamente la cadena NIF,
     * podemos crear un par de métodos para extraer el número de DNI o la letra a partir de una cadena NIF. 
     * Ambos métodos pueden declararse estáticos y privados (aunque no es la única posibilidad):
     * @param nif
     * @return 
     */
    
    private static char extraerLetraNIF (String nif) {

        char letra=   nif.charAt(nif.length()-1);
        return letra;
    }    

    private static int extraerNumeroNIF (String nif) {

        int numero= Integer.parseInt(nif.substring(0, nif.length()-1));
        return numero;

    }
    
    /**
     * Metodo para validar Dni
     * Es un metodo publico y estatico. Por lo que se puede usar fuera de esta clase y sin intanciar un objeto Dni.
     * Para llamarlo desde fuera de la clase sin intanciar objeto Dni, seria asi, Dni.validaNif(String)
     * Este metodo no lanza excepciones, a continuacion esta el mismo que sin lanza excepciones.
     * 
     * El proceso de validacion es:
     *   Extracción del número.
     *   Extracción de la letra.
     *   Cálculo de la letra a partir del número.
     *   Comparación de la letra extraída con la letra calculada.
     *   
     * 
     * @param nif que sera um String
     * @return Devuelve True si es un dni correcto y false en caso contrario.
     */    
    public static boolean validarNIF (String nif) {

            boolean valido= true;   // Suponemos el NIF válido mientras no se encuentre algún fallo
            char letra_calculada;
            char letra_leida;
            int  dni_leido;
            
            if (nif == null) {  // El parámetro debe ser un objeto no vacío
                valido= false;
            }

            else if (nif.length()<8 || nif.length()>9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres
                valido= false;
            }
            else {
                letra_leida= Dni.extraerLetraNIF (nif);    // Extraemos la letra de NIF (letra)
                dni_leido= Dni.extraerNumeroNIF (nif);  // Extraemos el número de DNI (int)
                letra_calculada= Dni.calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del número extraído
                if (letra_leida == letra_calculada) {   // Comparamos la letra extraída con la calculada
                    // Todas las comprobaciones han resultado válidas. El NIF es válido.
                    valido= true;
                }
                else { 
                        valido= false;
                }
            }
            return valido;                       
    }
    
    //Es el mismo metodo para validar pero este implementa excepciones que son lanzadas dependiendo
    //del error que encuentre durante la validacion.
    //Ej: Si la letra del dni recibido por parametro es incorrecta, o si son pocos o muchos caracteres el dni.
    public static boolean validarNIFExcepcion (String nif) throws Exception {

        boolean valido= true;   // Suponemos el NIF válido mientras no se encuentre algún fallo
        char letra_calculada;
        char letra_leida;
        int  dni_leido;
            
        if (nif == null) {  // El parámetro debe ser un objeto no vacío
                valido= false;
                throw new Exception ("DNI vacio: " + String.valueOf(nif));
        }

        else if (nif.length()<8 || nif.length()>9) {    // La cadena debe estar entre 8(7+1) y 9(8+1) caracteres
               valido= false;
               throw new Exception ("DNI con un tamaño incorrecto: " + String.valueOf(nif));
        }
        else {
                letra_leida= Dni.extraerLetraNIF (nif);    // Extraemos la letra de NIF (letra)
                dni_leido= Dni.extraerNumeroNIF (nif);  // Extraemos el número de DNI (int)
                letra_calculada= Dni.calcularLetraNIF(dni_leido);  // Calculamos la letra de NIF a partir del número extraído
                if (letra_leida == letra_calculada) {   // Comparamos la letra extraída con la calculada
                    // Todas las comprobaciones han resultado válidas. El NIF es válido.
                    valido= true;
                }
                else { 
                        valido= false;
                        throw new Exception ("DNI con una letra incorrecta: " + String.valueOf(nif));
                }
        }
        if(valido){
            return valido;
        }
        else{
            throw new Exception ("DNI inválido: " + String.valueOf(nif));
        }
                        
    }
    
    
    
    
    /**
     * Y por último tan solo quedarían por implementar los métodos públicos (la interfaz):
     * Los dos métodos obtener (get). Obtener el NIF (String) u obtener el DNI (int).
     * Los dos métodos establecer (set). A partir de un int y a partir de un String.
     * En el primer caso habrá que devolver información añadiéndole (si es necesario) información adicional calculada,
     * y en el segundo habrá que almacenar
     * el nuevo valor de DNI/NIF.
     * 
     * @return 
     */
    
    public String obtenerNIF () {
        // Variables locales
         String cadenaNIF;   // NIF con letra para devolver
         char letraNIF;      // Letra del número de NIF calculado

         // Cálculo de la letra del NIF        
         letraNIF= Dni.calcularLetraNIF (numDNI);

         // Construcción de la cadena del DNI: número + letra
         cadenaNIF= Integer.toString(numDNI) + String.valueOf(letraNIF);

         // Devolución del resultado
         return cadenaNIF;
    }

    public int obtenerDNI () {

        return numDNI;
    }
    

    
    public void setDni (String nif) {
            this.numDNI= Dni.extraerNumeroNIF(nif); 
    }

    public void setDni (int dni)  {
            this.numDNI= dni; 
    }
    

    
    
    
}
