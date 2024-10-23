import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        leer(baseCsv());
        System.out.println(camposCabecera(leer(baseCsv())));

    }
    public static File baseCsv(){
        File dir = new File("db");

        if(!dir.exists()){
            dir.mkdir();
        }

        File file = new File(dir,"\\Piero.csv");

        if(!file.exists()){
            try(FileWriter fw = new FileWriter(file)){
                fw.write("ID,NOMBRE,APELLIDO,FECHA_NACIMIENTO");
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

}