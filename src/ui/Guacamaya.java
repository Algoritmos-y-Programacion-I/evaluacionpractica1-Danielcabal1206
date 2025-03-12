package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;
    public static int [] vendidos;
    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: l Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    System.out.println("\nOpcion invalida, intenta nuevamente.");
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];
        vendidos= new int[referencias];
    }
    /**
     * En este metodo se establece la solicitud inicial de los datos
     * como lo son:
     * pre: El precio no puede tener un valor que sea de 0 o inferior
     * pre: Las unidades de un producto no pueden ser 0 
     * pos: Los arreglos de precio y unidad ahora almacenan esos datos
     */

    public static void solicitarDatos(){
        System.out.println("Es momento de que ingreses el precio de los productos");
        double precio;
        for(int i=0 ;i<precios.length;i++){
            System.out.println("Ingrese el precio del producto numero"+" "+(i+1));
            while (true) {
                precio=reader.nextDouble();
                if(precio<0){
                    System.out.println("El precio no puede se de 0");
                }else{
                    precios[i]=precio;
                    break;
                }
            }
            System.out.println("Ahora ingrese la cantidad existente de este producto");
            int unidadesProducto;
            while(true){
                unidadesProducto=reader.nextInt();
                if(unidadesProducto<1){
                    System.out.println("No se puede ingresar una cantidad menor a 1");
                }else{
                    unidades[i]=unidadesProducto;
                    break;
                }
            }
            

            

        }
       

     
    }
    /**
     * Este metodo se encarga de dar el total de unidades vendidas TOTALES de todas las referencias de producto
     * Utiliza el arreglo uUNIDADES para conocer la extension de todos los productos para los cuales debe acumular
     * @return venta
     */
    public static int calcularTotalUnidadesVendidas(){
        solicitarDatos();
        int venta;
        venta=0;
        for(int i=0;i<unidades.length;i++){
            System.out.println("Cuantos productos se vendieron del producto" + (i+1));
            while(true){
                venta+=reader.nextInt();
                if(venta<1){
                    System.out.println("Si no has vendido no es necesario modificar");
                }else{
                    vendidos[i]=venta;
                    break;
                }
            }
        }
        return venta;

    }
    /**
     * Este metodo permite calcular el costo aproximado de media de los articulos de esta tienda
     * @return promedio El promedio del precio de los productos vendidos en ese dia 
     */
    public static double calcularPrecioPromedio(){
        solicitarDatos();
        double sumaPro=0.0;
        double promedio;
        for(int i=0;i<precios.length;i++){
            sumaPro+=precios[i];
        }
        promedio=sumaPro/(precios.length);
        
        return promedio;

    }

    /**
     * pre:Tener los precios definidos 
     * pre:El arreglo de vendidos debe estar lleno
     * @return total Es el total de ventas en efectivo de todo lo vendido en el dia
     */
    public static double calcularVentasTotales(){
        solicitarDatos();
        double total=0;
        for(int i=0;i<vendidos.length;i++){
            total+=vendidos[i]*precios[i];
        }
        return total;

    }
    /**
     * pre:se debe tener los datos basicos de los productos
     * ejemplo: su precio y su cantidad
     *
     * @param limite numero que ingresa el usuario definiendo el minimo de dinero que debe haber generado un producto
     * @return sobreLimite un numero que indica cuantos productos superaron ese minimo de ventas
     */

    public static int consultarReferenciasSobreLimite(double limite){
        solicitarDatos();
        calcularTotalUnidadesVendidas();
        int sobreLimite=0;
        for(int i=0;i<precios.length;i++){
            if((vendidos[i]*precios[i])>limite){
                sobreLimite+=1;
            }
        }
        return sobreLimite;

    }

}
