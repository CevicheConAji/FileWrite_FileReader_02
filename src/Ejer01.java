import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class Ejer01 {
        public static Random r = new Random();

        public static void main(String[] args) {
            arrayAleatorio();
            leerDat();
        }

        public static void crearDat(){
            File file = new File("data.dat");
            if(!file.exists()){
                try(FileWriter fw = new FileWriter(file)){
                    fw.write("Hola");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }

        public static void arrayAleatorio(){
            crearDat();
            int[] n;
            n = new int[5];
            for (int i = 0; i < n.length; i++) {
                n[i] = r.nextInt(999);
            }
            ingresarAleatorio(n);

        }
        public static void ingresarAleatorio(int[] n){
            try(FileWriter fw = new FileWriter("data.dat",false)){
                fw.write(n[0]+"\n");
                fw.write(n[1]+"\n");
                fw.write(n[2]+"\n");
                fw.write(n[3]+"\n");
                fw.write(n[4]+"\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public static void leerDat(){
            try(BufferedReader br = new BufferedReader(new FileReader("data.dat"))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
