/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaqueteAutos;


import PaqueteAutos.Vehiculo;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author oscar
 */




/**
 * Clase para almacenar objetos del tipo Vehiculo, los objetos se almacenas en un array
 * @author oscar
 */

public class Concesionario {
    
    //Lista de vehiculos del tipo LinkedList
    private LinkedList<Vehiculo> vehiculosLista= new LinkedList<Vehiculo>();
    /**
     * Explicacion de porque uso una Lista del tipo LinkedList.
     * Utilizo una lista porque nos permite el acceso directo al elemento por posicion y busqueda, los conjuntos no.
     * El tipo de lista es LinkedList porque cuando se realizan eliminaciones esta recomendado que sea del tipo Linked y no del ArrayList
     * Los elementos de la lista los ordeno desde el metodo sort que esta implementado en la interfaz.
     * No uso un diccionario porque no es una coleccion y no tiene la interfaz para poder ordenar sus elementos
     */
    //Tamaño maximo de de la lista
    private int tamanoListaVehiculos;//Se guarda la cantidad maxima de vehiculos(instanciados) que se pueden alamcenar en la lista
    
    /**
     * Constructor que no tiene parametros , define por defecto el tamñaño de los vehciulos que se puden guardar en la lista
     */

    public Concesionario(){
        this.tamanoListaVehiculos=50; //Inicializo la cantidad de vehiculos que tiene guardados el concesionario al crearse el objeto concesionario       
         //Para instanciar un vehiculo en cada posicion de la lista, tendremos que  crearlo con el metodo add de añadir elementos a la lista y new Vehiculo() dentro del add
    }
    
    
    
    /**
     * Constructor con parametros, define el tamñaño maximo  que puede tener la lista especificado en el parametro
     * @param tamanoArray Parametro int, que especifica el tamaño de cuantos elementos tendra la lista de vehiculos
     */

    public Concesionario(int tamanoLista){
        this.tamanoListaVehiculos=tamanoLista;       
        //No estan instanciados los vehiculos, la lista esta vacia
        //Para instanciar un vehiculo en cada posicion de la lista, tendremos que usar el metodo insertarVehiculo
    }
    
    
    /**
     * Metodo get del atributo vehiculoLista.
     * No se usa, pero si desde fuera de la clase se necesitara saber cuantos vehiculos instanciados tiene la lista   
     * @return 
     */
    public int getCantidadVehiculosAlmacenados(){
        return this.vehiculosLista.size();
    }
    

    
    
    
    /**
     * Metodo para insertar un nuevo vehiculo en la lista
     * El metodo comprueba que no este llena la lista y
     * Que la matricula no exista en algun otro vehiculo ya guardado 
     * Los parametros tienen que venir ya validados, en la clase Principal.
     * 
     * @param marca String con la marca del Vehiculo
     * @param matricula String con la matricula del Vehiculo
     * @param descripcion String con la descripcion del Vehiculo
     * @param nombrePropietario String con el nombre del propietario del Vehiculo
     * @param numKilometros int con los Km del vehiculo
     * @param precio int con el precio del vehiculo
     * @param dniPropietario String con el dni del propietario del vehiculo
     * @param fechaMatriculacion Atributo tipo LocalDate donde se guarda la fechadeMatriculacion
     * @return Devuelve un entero que sera como un codigo, 0 indica que se guardo, -1 array lleno, -2 Matricula ya existe
     */    
    public int  insertarVehiculo(String marca, String matricula, String descripcion, String nombrePropietario, int numKilometros, int precio, String dniPropietario, LocalDate fechaMatriculacion){
        int respuesta=-1;
        //Vehiculo vehiculoTemporal;
        //vehiculoTemporal = new Vehiculo(marca,  matricula, descripcion, nombrePropietario, numKilometros, precio, dniPropietario,  fechaMatriculacion);
        if(this.vehiculosLista.size()> this.tamanoListaVehiculos){
            respuesta =-1;  //Concesionario lleno, maximo 50 vehciulos o el tamaño definido en el contructor    
            }
            //Si el vehiculo no esta en la lista, el valor null significa que no esta
            else if( buscaVehiculo(matricula) == null ) {
                vehiculosLista.add(new Vehiculo(marca,  matricula, descripcion, nombrePropietario, numKilometros, precio, dniPropietario,  fechaMatriculacion));
                respuesta=0;
                //Hemos agregado un nuevo vehiculo y ordenadomos la lista, por matriculas, pues asi esta implementada la interface Comparable con el metodo CompareTo
                Collections.sort(vehiculosLista);
                
            }
            //El vehiculo esta en la lista porque la busqueda dedevolvio el valor diferente a null
            else{
                respuesta=-2;
            } //El vehiculo ya existe y no se guarda
        
        return respuesta;
    }
    
    
    
    
    
    
    /**
     * Metod que busca un vehiculo en la lista, lo busca por matricula
     * Si existe develve los datos del vehiculo en un String y no encuentra la matricula devuelve null
     * @param matricula Parametro String que contiene la matricula a buscar
     * @return Cadena String con los datos del vehiculo encontrado, a un Strin null en caso de no encontrarlo
     */
    
    public String buscaVehiculo(String matricula){
        String matriculaVehiculoBuscado=null;
        //Iteramos la lista de Vechiulos 
        for (Vehiculo vehiculoIterado: vehiculosLista){
            //Si la matricula coincide con la buscada
            if(vehiculoIterado.getMatricula().equals(matricula)){
                //Creamos el String con los datos del vehiculo encontrado  
                matriculaVehiculoBuscado= String.format("Marca: ' %s '. Matricula: ' %s '. Precio: ' %s '.", vehiculoIterado.getMarca(), vehiculoIterado.getMatricula(),vehiculoIterado.getPrecio());
                break; //Encontramos la matricula y forzamos la salida del for para no recorrerlo entero.
            }
        }
        return matriculaVehiculoBuscado;           
    }
    
    
    /**
     * Metodo que lista todos los vehiculos de la lista de vehiculos,
     * 
     */

    public void listaVehiculos(){
        int i=0;
        System.out.println("\n ------Listado de vehiculos-------");
        for (Vehiculo vehiculoIterado: vehiculosLista){            
            System.out.printf("\nVehiculo (%s/%s)", i+1, this.vehiculosLista.size());
            System.out.printf("\n Marca:'%s'. Matricula:'%s'. Precio:'%s'. Kilometros:'%s'. Descripcion:'%s'.",vehiculoIterado.getMarca(), vehiculoIterado.getMatricula(), vehiculoIterado.getPrecio(), vehiculoIterado.getNumKilometros(),vehiculoIterado.getDescripcion() );
            i++;
        }
    }
    
    
    /**
     * Metodo para actualizar los Km de un vehiculo
     * Busca un vehiculo, y si lo encuentra le suma los km.
     * @param matricula Paramtro String que es la matricula del vehiculo que tiene que actualizar los Km
     * @param kms Parametro int que contiene los km a sumar
     * @return Devuelve true sin consigue actualizar los km y false si no lo actualiza porque no encuentra el vehiculo
     */

    public boolean actualizaKms(String matricula, int kms){        
        boolean exito=false;
        int posVehiculoBuscado=-1;
        for (Vehiculo vehiculoIterado: vehiculosLista){ //Recorremos la lista hasta que lo encontremos o entera
            if(vehiculoIterado.getMatricula().equals(matricula)){
                posVehiculoBuscado=vehiculosLista.indexOf(vehiculoIterado);
                exito=true;
                break; //Matricula encontrada y salimos del for
            }
        }
        if(exito) vehiculosLista.get(posVehiculoBuscado).setNumKilometros(kms); //Si encontramos la matricula, accedemos a el desde la lista el con get y modificamos los km
            //No lo modifico dentro de la iteracion por no modifcar el iterador
        return exito;        
    }
        
    /**
     * Metodo para eliminar un vehiculo de la lista, el vehiculo a eliminar sera el que nos llega por parametro
     * @param matricula  String que es la matricula del vehiculo a borrar
     * @return True si eleimina el vehiculo y False si no lo elimina porque no existe
     */
    public boolean eliminaVehiculo(String matricula){    
        boolean exito=false;
        int posVehiculoBuscado=-1; //Guardamos la posicion en la lista del vehiculo si es existe
        for (Vehiculo vehiculoIterado: vehiculosLista){ //Recorremos la lista hasta que lo encontremos o entera
            if(vehiculoIterado.getMatricula().equals(matricula)){
                posVehiculoBuscado=vehiculosLista.indexOf(vehiculoIterado); //Esta es la posicion del vehiculo en la lista
                exito=true;
                break;
            }
        }
        if(exito) vehiculosLista.remove(posVehiculoBuscado); //Si encontramos la matricula, accedemos a el desde la lista y lo eliminamos con remove
            //No lo elimino dentro de la iteracion por no modifcar el iterador
        return exito;
    }
    
}
