import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ejer02 {
    public static File file = new File("info.dat");
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }
    public static void menu(){
        ArrayList<String> list = null;
        boolean continuar = true;

        while (continuar) {
            System.out.println("***MENU***" +
                    "\n*****Programa 1****" +
                    "\n 1) Escribir Say My Name en info.dat y Guardar en Lista" +
                    "\n 2) Imprimir Lista" +
                    "\n*****Programa 2****" +
                    "\n 3) Llenar productos.dat" +
                    "\n 4) Imprimir productos.dat" +
                    "\n*****Programa 3****" +
                    "\n 5) Guarda Cabecera y datos de producto.csv" +
                    "\n 6) Imprimir producto.csv");
            String cadena = sc.nextLine();
            switch (cadena) {
                case "1":
                    crearInfoDat();
                    list = leerInfoDat();
                    break;
                    case "2":
                        imprimirListaDat(list);
                        break;
                        case "3":

                            break;
                            case "4":
                                productosDatLeer(productosDatGuardar());
                                break;
                                case "5":
                                    productosCsvCabecera();
                                    productosCsvGuardar();
                                    break;
                                    case "6":
                                        leerPrintf(productosCsvLeer());
                                        break;
                                        default:
                                            continuar = false;
            }
        }

    }
    public static void crearInfoDat(){

        if(!file.exists()){
            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
               dos.writeUTF("Say");
               dos.writeUTF("My");
               dos.writeUTF("Name");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static ArrayList<String> leerInfoDat(){
        ArrayList<String> listaInfo = new ArrayList<>();

        if(file.exists()){
            try(DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                String cadena = dis.readUTF();

                splitLista(listaInfo, cadena);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else System.out.println("No se encontro el archivo"+file.getName());

        return listaInfo;
    }

    public static void imprimirListaDat(ArrayList<String> listaInfo){
        try{
            for(String info : listaInfo){
                System.out.println(info+" ");
            }

        } catch (NullPointerException e) {
            System.out.println("Lista vacia");
        }

    }
    public static void splitLista(ArrayList<String> listaInfo,String cadena){
        String[] d = cadena.split(" ");
        for(int i = 0; i < d.length; i++){
            listaInfo.add(d[i]);
        }

    }
    public static int productosDatGuardar(){
            double[] precios = {1350,400,890,6200,8730};
            int[] unidades = {5,7,12,8,30};
            String[] descripcion = {"paquete de papel","lápices","bolígrados","carteras","mesas"};

        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("productos.dat"))) {
            // Guardar la longitud del arreglo (para saber cuántos productos hay)


            for(int i = 0; i < precios.length; i++){
                dos.writeDouble(precios[i]);
                dos.writeInt(unidades[i]);
                dos.writeUTF(descripcion[i]);
            }
            System.out.println("Datos guardados");

        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }
        return precios.length;
    }
    public static void productosDatLeer(int tamArray){
        try(DataInputStream dis = new DataInputStream(new FileInputStream("productos.dat"))) {
            // Leer la cantidad de productos();
            //int cantidad =

            System.out.printf("%-10s %-20s %-10s %n",
                    "PRECIOS","UNIDADES","DESCRIPCION");

            for(int i = 0; i < tamArray; i++){
                double precios = dis.readDouble();
                int unidades = dis.readInt();
                String descripcion = dis.readUTF();

                System.out.printf("%-10s %-20s %-10s %n",
                        precios,unidades,descripcion);


            }
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }
    }
    public static void productosCsvCabecera(){
        File file = new File("productos.csv");
        if(!file.exists()){
            try(FileWriter fw = new FileWriter(file)){
                fw.write("DESCRIPCION,PRECIOS,UNIDADES\n");

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
    public static void productosCsvGuardar(){
        File file = new File("productos.csv");
        int[] idProducto = {1,2,3,4,5};
        String[] descripcion =
                {
                "paquetes de papel",
                "lápìces",
                "bolígrafos",
                "carteras",
                "mesas"
                };
        double[] precios = {1350,400,890,6200,8730};
        int[] unidades = {5,7,12,8,30};

        try(FileWriter fw = new FileWriter(file,true)) {

            for(int i = 0; i < idProducto.length; i++){
                fw.write(idProducto[i]+",");
                fw.write(descripcion[i]+",");
                fw.write(precios[i]+",");
                fw.write(unidades[i]+"");
                fw.write("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> productosCsvLeer(){
        ArrayList<String> lista = new ArrayList<>();

        File file = new File("productos.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                lista.add(line);
            }
        }catch (FileNotFoundException e){
            System.out.println(file.getName()+" Esta Vacio");
        }catch (IOException e){
            e.printStackTrace();
        }
        return lista;
    }
    public static void leerPrintf(ArrayList<String> lista){

        System.out.println("***********************************************");
        for (int i = 0; i < lista.size(); i++){
            System.out.printf("%-10s %-25s %-10s %n",
                    lista.get(i).split(",")[0].toUpperCase() ,
                    lista.get(i).split(",")[1].toUpperCase() ,
                    lista.get(i).split(",")[2].toUpperCase() );
        }
        System.out.println("***********************************************");
    }


}
