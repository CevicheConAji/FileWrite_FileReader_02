import model.Persona;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Tarea01{
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        menu();

    }
    public static void menu(){
        boolean salir = false;
        while (!salir) {
            System.out.println("****MENU****" +
                    "\n 1) Dar alta Persona" +
                    "\n 2) IMPRIMIR CON FORMATO" +
                    "\n 3) Mis Datos" +
                    "\n *) Salir");
            String opcion = sc.nextLine();
            switch(opcion){
                case "1":
                    setDatos(csv());
                    break;
                    case "2":
                        leerPrintf(leer(csv()));
                        break;
                        case "3":
                            misDatos(csv());
                            break;
                                default:
                                    salir = true;
                                    break;
            }

        }
    }

    public static File csv(){
        File dir = new File("db");

        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File(dir,"Piero.csv");

        if(!file.exists()){
            try(FileWriter fw = new FileWriter(file)){
                fw.write("ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    public static ArrayList<String> leer(File file){
        ArrayList<String> lista = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linea;
            while((linea = br.readLine()) != null){

                lista.add(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;

    }
    public static boolean esFichero(File file){
        return file.isFile();
    }
    public static int camposCabecera(ArrayList<String> lista){
        String[] campo = lista.get(0).split(",");

        return campo.length;
    }
    public static int camposContenido(ArrayList<String> lista){
        return lista.size()-1;
    }

    public static void setDatos(File file){
        try (FileWriter fw = new FileWriter(file,true)){
            System.out.println("Ingrese el nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el apellido");
            String apellido = sc.nextLine();
            System.out.println("Ingrese el fecha de nacimiento");
            String fechaNacimiento = sc.nextLine();

            Persona pj = new Persona(nombre,apellido,fechaNacimiento);

            fw.write(pj.getId()+
                    ","+ pj.getNombre()+
                    ","+pj.getApellido()+
                    ","+pj.getFechaNacimiento()+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void misDatos(File file){
        try(FileWriter fw = new FileWriter(file,true);
            PrintWriter pw = new PrintWriter(fw)){

            Persona yo = new Persona("Piero","Zavala","24/11/2000");

            pw.println(yo.getId()+","+yo.getNombre()+","+yo.getApellido()+","+yo.getFechaNacimiento());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void leerPrintf(ArrayList<String> lista){

        System.out.println("***********************************************");
        for (int i = 0; i < lista.size(); i++){
            System.out.printf("%-10s %-10s %-10s %-10s %n",
                    lista.get(i).split(",")[0].toUpperCase() ,
                    lista.get(i).split(",")[1].toUpperCase() ,
                    lista.get(i).split(",")[2].toUpperCase() ,
                    lista.get(i).split(",")[3].toUpperCase() );
        }
        System.out.println("***********************************************");
    }
}