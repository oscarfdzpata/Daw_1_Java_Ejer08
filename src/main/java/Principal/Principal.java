/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

//Importamos el paquete util creado para poder hacer uso de las clases y sus metodos
import Util.Dni;
import Util.ValidarDatosVehiculo;
        

//Importamos el paquete time para usar las clses de fecha
import java.time.LocalDate;
import java.time.Month;

//Importamos la clase Scanner para aceptar datos por teclado
import java.util.Scanner;

//Importamos el PaqueteAutos que contiene la clase Concesionario
import PaqueteAutos.Concesionario;
import PaqueteAutos.Vehiculo;

/**
 *
 * @author oscar
 */
public class Principal {
    
    /**
     * Metodo para visualizar el menu de opciones del programa.
     */
    
    private static void mostrarMenu(){
        System.out.println("\n\n ---------PROGRAMA08   CONCESIONARIO---------");
        System.out.println("\n1. Nuevo Vehiculo.");
        System.out.println("2. Listar Vehiculos.");
        System.out.println("3. Buscar Vehiculos.");
        System.out.println("4. Modfificar kms Kilometros.");
        System.out.println("5. Eliminar vehiculo.");    
        System.out.println("6. Salir.");
    }
    
    /**
     * Metodo para introducir datos por teclado, este metodo lo uso para  datos introducidos de tipo String
     * @param mensaje El mesaje a mostrar que sera una descripcion del dato
     * @param teclado Parametro de la clase Scanner, para introducir dato por teclado
     * @return Devuelve el String del dato
     */    
    private static String introducirDatosPorTeclado(String mensaje, Scanner teclado){
        String dato;
        System.out.println(mensaje);                    
        dato=teclado.nextLine();         
        return dato;
    }
    
    /**
     * Metodo para introducir datos por teclado, este metodo lo uso para  datos introducidos de tipo Int
     * @param mensaje El mesaje a mostrar que sera una descripcion del dato
     * @param teclado Parametro de la clase Scanner, para introducir dato por teclado
     * @return Devuelve el Int del dato 
     */    
    private static int introducirDatosPorTecladoInt(String mensaje, Scanner teclado){
        int dato;
        System.out.println(mensaje);                    
        dato=teclado.nextInt();
        teclado.nextLine();          
        return dato;
    }
    
    /**
     * Metodo Main donde se implemeta toda la logica.
     * @param args 
     */    
    public static void main(String[] args) {
        
        int opcionMenu=0; //Guardo la opcion del menu introducida por teclado
        Scanner teclado= new Scanner( System.in ); //Objeto de la clase Scanner para usar el teclado para introducir datos
        String marca,matricula,descripcion,nombrePropietario,dniString; //Guardo los dattos que corresponden a los atributos de la clase Vehiculo
        int numeroKilometros,precio,dia,mes,anio; //Guardo los dattos que corresponden a los atributos de la clase Vehiculo
        LocalDate fechaActual=LocalDate.now(); //Objeto del tipo LocalDate para guardar la fecha del dia de hoy
        LocalDate fechaMatriculacion; //Para guarda la fecha que corresponde con un atributo de la clse Vehiculo       
        boolean dniConfirmado=false;  //Para saber si el dni es correcto.       
        int sumarKm; // Guardo los km que se quieren añadir al vehiculo
        int respuesta; // Guarda la respuesta que devuelve el metodo de insertarVehiculo        
        String dni; //Guardo el dni, el dni es String, en este ejercicio no se usa la clase Dni
        //Declaro un objeto tipo Concesionario con el contructor que permite establecer la cantidad de vehiculos
        Concesionario concesionarioOscar= new Concesionario(50);
        
        do{ //Entrara minimo una vez hasta que pulse 5
            
            //Mostramos el menu, con el metodo mostrarMenu() de la clase Principal, no hace falta llamarlo con Principal.mostrarMenu(), bastaria con mostrarMenu().
            mostrarMenu();
            //Para todos los datos que se introducen por teclado uso el metodo, introducirDatosPorTeclado(), lo llamo con Principal. para que se vea mas claro de donde es el metodo,pero no hace falta.
            opcionMenu=Principal.introducirDatosPorTecladoInt("\n Introduce una opcion del menu: ...... ", teclado);
            
            switch (opcionMenu){ //Entrara en la opcion elegida por teclado.
                
                case 1: //Nuevo vehiculo, introducir datos y validarlos
                    System.out.println("\n           ---[Opcion 1] Configuracion del vehiculo.--- ");   
                    
                    
                    marca=Principal.introducirDatosPorTeclado("\n Introduce la marca del vehiculo: ...... ", teclado);       
                    //Se comprueba que el dato introducido no este vacio, si esta vacio se vuelve a pedir
                    while  ( ! ValidarDatosVehiculo.validarDatoTexto(marca)) { marca=Principal.introducirDatosPorTeclado("\n !!!Error. No se puede dejar vacio el campo. Introduce de nuevo la marca del vehiculo: ...... ", teclado); }
                            
                    matricula=Principal.introducirDatosPorTeclado("\n Introduce la matricula del vehiculo,, con este formato '1234ABC': ...... ", teclado);
                    //Se comprueba que la matricula introducida siga el formato NNNNLLL, si esta mal se vuelve a pedir
                    while( ! ValidarDatosVehiculo.validarMatricula(matricula)){ matricula=Principal.introducirDatosPorTeclado("\n !!!Error, la matricula tiene que tener el formato 'NNNNLLL', vuelve a introducirla", teclado); }
                    
                    numeroKilometros=  Principal.introducirDatosPorTecladoInt("\n Introduce los KM del vehiculo: ...... ", teclado);                    
                    //Compruebo que el dato de numeroKm es correcto, si no lo es se vuelve a pedir.
                    // El metodo estatico de la clase ValidarDatosVehiculo del paquete util, no hace falta instanciarlo
                    while( ! ValidarDatosVehiculo.validarKm(numeroKilometros)){ numeroKilometros= Principal.introducirDatosPorTecladoInt("!!!Error. Los KM del vehiculo no pueden ser 0, introduce una cantidad mayor: ...... ", teclado) ; }

                    System.out.println("\n---Fecha de matriculacion:---");
                    dia=  Principal.introducirDatosPorTecladoInt("Introduce el dia: ...... ", teclado) ;
                    mes=  Principal.introducirDatosPorTecladoInt("Introduce el mes: ...... ", teclado);
                    anio= Principal.introducirDatosPorTecladoInt("Introduce el anio: ...... ", teclado) ;
                    fechaMatriculacion=LocalDate.of(anio, mes, dia); //Paso los datos para crear uan fecha del tipo LocalDate                    
                    //Mientras la fecha introducida no sea inferior a la actual la vuelvo a pedir.
                    while ( ! ValidarDatosVehiculo.validaFechaMatriculacion(fechaActual, fechaMatriculacion) ){
                        System.out.println("\n !!La fecha introducida es erronea, tiene que ser inferior a la actual. Prueba otra vez.");
                        dia= Principal.introducirDatosPorTecladoInt("Introduce el dia: ...... ", teclado) ;
                        mes= Principal.introducirDatosPorTecladoInt("Introduce el mes: ...... ", teclado) ;
                        anio=Principal.introducirDatosPorTecladoInt("Introduce el anio: ...... ", teclado) ;
                        fechaMatriculacion=LocalDate.of(anio, mes, dia);
                    }
                    
                    descripcion=Principal.introducirDatosPorTeclado("\nIntroduce una descripcion del vehiculo:.....", teclado);                    
                                        
                    nombrePropietario=Principal.introducirDatosPorTeclado("\nIntroduce el nombre del propietario vehiculo con el formato 'Nombre Apellido1 Apellido2':.....", teclado);
                    //Se valida que el  nombre tenga, 'nombre apellido1 apellido2', y no exceda de 40 caracteres
                    while(! ValidarDatosVehiculo.validarNombreY2Apellidos(nombrePropietario, 40)){ nombrePropietario=Principal.introducirDatosPorTeclado("\n!!!Error, Tienes que introducir el nombre y dos apellidos, y que no supere los 40 caracteres en total,  vuelve a probar", teclado); }
                                        
                    precio=Principal.introducirDatosPorTecladoInt("\nIntroduce el precio del vehiculo", teclado);
                    while(! ValidarDatosVehiculo.validarPrecio(precio)) { precio=Principal.introducirDatosPorTecladoInt("\n !!!Error, no puede tener un precio negativo, vuelve a introducir el precio", teclado);}                   
                    
                    dni=Principal.introducirDatosPorTeclado("\nIntroduce el dni del propietario del vehiculo, con el formato 'NNNNNNNNL' ej: '12345678X'", teclado);
                    //Se valida que el dni cumpla el formato 'NNNNNNNNL'
                    while( ! ValidarDatosVehiculo.validarDniExpRegular(dni)) { dni=Principal.introducirDatosPorTeclado("\n!!!Error, dni incorretc, vuelve a introducirlo, con el formato 'NNNNNNNNL' ej: '12345678X'", teclado);  }
                    
                    
                    //En este punto ya estan todos los datos validados y se procede a intentar guardar el vehiculo                    
                    respuesta = concesionarioOscar.insertarVehiculo(marca, matricula, descripcion, nombrePropietario, numeroKilometros, precio, nombrePropietario, fechaMatriculacion);
                    
                    //Se comprueba el codigo devuelto por el metodo, -1 esta lleno el concesionario, 0 se ha guardado, y -2 la matricula esta duplicada
                    if(respuesta == -1) System.out.println("\n !!ERROR!!! El concecionario esta lleno, no se ha guardado el vehiculo");
                    else if( respuesta == 0) { System.out.println("\n !!!OPERACION CORRECTA!!! Se ha guardado un vehiculo en el concesionario"); }
                    else if ( respuesta==-2) { System.out.println("\n !!!ERROR!!! La matricula del vehiculo ya existe, no se ha guardado el vehiculo en el concesionario"); }
                    
                    break;
                    
                case 2: //Opcion listar vehiculos
                    concesionarioOscar.listaVehiculos();
                    break;
                
                case 3: //Opcion buscar vehiculo
                    System.out.println("\n ----Buscar vehiculo----");
                    matricula=Principal.introducirDatosPorTeclado("\n Introduce la matricula del vehiculo, a buscar , con este formato '1234ABC': ...... ", teclado);
                    if(concesionarioOscar.buscaVehiculo(matricula) == null) System.out.println("\n No existe vehiculo con la matricula introducida");
                    else System.out.println("\n \n Datos del vehiculo: " + concesionarioOscar.buscaVehiculo(matricula));
                    break;
                    
                case 4: //Opcion actualizar km
                    System.out.println("\n ----Actualizar Kms----");
                    numeroKilometros=  Principal.introducirDatosPorTecladoInt("\n Introduce los KM del vehiculo: ...... ", teclado);                    
                    //Compruebo que el dato de numeroKm es correcto,positivo, si no lo es se vuelve a pedir.
                    while( ! ValidarDatosVehiculo.validarKm(numeroKilometros)){ numeroKilometros= Principal.introducirDatosPorTecladoInt("!!!Error. Los KM del vehiculo no pueden ser 0, introduce una cantidad mayor: ...... ", teclado) ; }
                    matricula=Principal.introducirDatosPorTeclado("\n Introduce la matricula del vehiculo, con este formato '1234ABC': ...... ", teclado);
                    if( ! concesionarioOscar.actualizaKms(matricula, numeroKilometros)) System.out.println("\n No se ha encontrado el vehiculo, no se ha atualizado ningun vehiculo");
                    
                    break;
                
                case 5: //Opcion eliminar vehiculo
                    System.out.println("\n ----Eliminar vehiculo----");
                    matricula=Principal.introducirDatosPorTeclado("\n Introduce la matricula del vehiculo, con este formato '1234ABC': ...... ", teclado);
                    if( ! concesionarioOscar.eliminaVehiculo(matricula )) System.out.println("\n No se ha encontrado el vehiculo, no se ha eliminado ningun vehiculo");
                    
                    break;
            }
            
        } while ( opcionMenu != 6);    

        
    }
    
}
