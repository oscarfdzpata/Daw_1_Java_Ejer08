//La clase esta dentro del paquete
package PaqueteAutos;

//Importamos los paquete para usar sus clases y metodos.
import Util.Dni;

//Clase para hacer uso de las fechas
import java.time.LocalDate;
import java.time.Period;

/**
 * Clase donde se guardan los datos que proporcioan informacion de un vehiculo.
 * En la clase vehículo no debe solicitar datos por teclado ni escribir datos en pantalla. Esas
 * operaciones se realizarán en la clase Principal.
 * En la clase Vehículo no se deben hacer validaciones de datos. Los datos se validan en la
 * clase Principal y si son correcto, se instancia el objeto Vehículo.
 * 
 * @author oscar Fernandez Pata
 */

public class Vehiculo implements Comparable<Vehiculo> {
    private String marca,matricula,descripcion, nombrePropietario;    
    private int numKilometros, precio;
    //private Dni dniPropietario;   
    String dniPropietario;
    private LocalDate fechaMatriculacion;
    
    //Atributo publico estatico que lo uso como contador de objetos instanciados de esta clase(Vehiculo).
    //Se puede acceder a este atributo fuera de esta clase sin intanciar la clase Vehiculo, Vehiculo.cantidadVehiculos
    //Guardara un entero que corresponde a la cantidad de objetos Vehiculo creados.
    public static int  cantidadVehiculos; 
    
    /**
     * Constructor que no recibe parametros, puede usarse para instanciar un objeto que tenga sus propiedades con un
     * valor por defecto que inicializa el constructor. Lo use para hacer las pruebas y no tener que introducir datos.
     */
    public Vehiculo(){
        /*
        this.marca= "FORD";
        this.matricula="2136BA";
        this.descripcion="Es un modelo hibrido, consume poco.";
        this.nombrePropietario="Oscar fernandez Pata";
        this.numKilometros= 15000;
        this.precio=6000;
        this.dniPropietario=new Dni("16598540S");
        this.fechaMatriculacion=LocalDate.of(2010,10,10);
        this.cantidadVehiculos++;  
        */
    }
    
    
    /**
     * Constructor que recibe todos los parametros que corresponden con los atributos de la clase.
     * Los parametros tienen que venir ya validados, en la clase Principal.
     * @param marca String con la marca del Vehiculo
     * @param matricula String con la matricula del Vehiculo
     * @param descripcion String con la descripcion del Vehiculo
     * @param nombrePropietario String con el nombre del propietario del Vehiculo
     * @param numKilometros int con los Km del vehiculo
     * @param precio int con el precio del vehiculo
     * @param dniPropietario String con el dni del propietario del vehiculo
     * @param fechaMatriculacion Atributo tipo LocalDate donde se guarda la fechadeMatriculacion
     */
    public Vehiculo(String marca, String matricula, String descripcion, String nombrePropietario, int numKilometros, int precio, String dniPropietario, LocalDate fechaMatriculacion){
        this.marca= marca;
        this.matricula=matricula;
        this.descripcion=descripcion;
        this.nombrePropietario=nombrePropietario;
        this.numKilometros= numKilometros;
        this.precio=precio;
        //this.dniPropietario=new Dni(dniPropietario); //Haciendo uso de la clase DNi, ya no se usa en este ejercicio
        this.dniPropietario=dniPropietario;                
        this.fechaMatriculacion=fechaMatriculacion;
        this.cantidadVehiculos++; //Incremento el valor en 1, nos indicara cuantos objetos de esta clase se han creado. Se usa como un contador de objetos Vehiculo instanciados.
    }
    
    
    
    //Metodos get y set
    /**
     * Estos son todos los metodos get y set de los atributos de la clase, que se utilizan 
     * para poder acceder y modificar su valor, son atributos privados por lo que solo se puede
     * acceder a ellos desde dentro de esta clase.
     * Los metodos get unicamente devuelven el valor del atributo, son metodos publicos para poder ser usados fuera de esta clase, una vez instanciada.
     * Los metodos set cambian el valor del atributo, los datos recibidos como parametro debe estar validado.
     * 
     */
    public String getMarca(){
        return this.marca;
    }
    
    public void setMarca(String marca){
        this.marca= marca;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public void setMatricula(String Matricula){
        this.matricula=matricula;
    }
    
    public int getNumKilometros(){
        return this.numKilometros;
    }
    
    public void setNumKilometros(int km){
         this.numKilometros+= km;
    }
    
    public String getPropietario(){
        return this.nombrePropietario;
    }
    
    public void setPropietario(String nombrePropietario){
        this.nombrePropietario=nombrePropietario;
    }
    
    public String getDni(){
        //return this.dniPropietario.obtenerNIF();  // Uso de la clase Dni, no se usa en este ejemplo
        return this.dniPropietario;
    }
    
    public void setDni(String dniPropietario){
        //this.dniPropietario.setDni(dniPropietario); //Uso de la clase Dni, no se usa en este ejemplo
        this.dniPropietario=dniPropietario;
    }
    
    public String getDescripcion(){
        return this.descripcion;
    }
    
    public void  setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
    
    public LocalDate getFechaMatriculacion(){
        return this.fechaMatriculacion;
    }
    
    public void setFechaMatriculacion(LocalDate fechaMatriculacion){
        this.fechaMatriculacion= fechaMatriculacion;
    }
    
    public int getPrecio(){
        return this.precio;
    }
    
    public void setPrecio(int precio){
        this.precio=precio;
    }
    //Fin metodos get y set
    
    
        
    /**
     * Implementacion del metodo compareTo de la interfaz Comparable, este metodo permite que sean ordenables
     * los objetos de esta clase, la implementacion de esta interfaz hace esto posible, es necesario cuando necesitamos
     * guardar los objetos del tipo de esta clase "Vehiculo" en una coleccion(Conjunto o lista) y sera invocado  desde el metodo
     * sort desde la coleccion a la que pertence
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Vehiculo o) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return matricula.compareTo(o.matricula);
    }
    
    
    /**
     * Metodo que no recibe parametros. Calcula cuantos años han pasado entre una fecha y la actual.
     * La fecha a comparar en un atributo de esta clase, fechamatriculacion.
     * @return Retorna un entero con el número de años del vehículo.
     */
    public int getAnios(){
        LocalDate fechaActual=LocalDate.now();
        int anios;
        anios = Period.between(this.fechaMatriculacion, fechaActual).getYears();
        return anios;        
    }

   
    
}
