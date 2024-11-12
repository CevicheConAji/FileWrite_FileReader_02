import model.Persona;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Ejer01 {
        public static Scanner sc = new Scanner(System.in);
        public static Random r = new Random();

        public static void main(String[] args) {
            menu();
        }

        public static void menu(){
            boolean continuar = true;
            while(continuar){
                System.out.println("***MENU***" +
                        "\n1) Generar Aleatorio en data.dat" +
                        "\n2) Introducir números en data.dat" +
                        "\n3) Leer data.dat" +
                        "\n4) Alta Persona en persona.dat" +
                        "\n5) Imprimir persona.dat" +
                        "\n6) Salir.");
                switch (sc.nextInt()){
                    case 1:
                        arrayAleatorio();
                        break;
                        case 2:
                            escribirDataOput();
                            break;
                            case 3:
                                leerDataInput();
                                break;
                                case 4:
                                    pedirDatos();
                                    break;
                                    case 5:
                                        imprimirDatos();
                                        break;
                                        default:
                                            continuar = false;
                }
            }
        }
        public static void escribirDataOput(){

            try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.dat",true))){

                while(true) {
                    System.out.println("escribir datos del archivo");
                    if (!sc.hasNextInt()) {
                        System.out.println("La entrada no es un número");
                        sc.next();
                        continue;
                    }
                    int n = sc.nextInt();

                    if(n == -1){
                        break;
                    }

                    dos.writeInt(n);
                }
            } catch (Exception e) {
                System.out.println("Error al escribir datos del archivo "+e.getMessage());
            }

        }
        public static void leerDataInput(){
            try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream("data.dat"))){
                System.out.println("leer datos del archivo");
                while (true){
                    try {
                        int n = dataInputStream.readInt();
                        System.out.println(n);
                    }catch (Exception e){
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("La entrada no es un archivo "+e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public static void crearDat(){
            File file = new File("data.dat");
            if(!file.exists()){
                try(DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))){

                } catch (Exception e) {
                    System.out.println(e.getMessage());
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
            try(DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("data.dat"))){
                dataOutputStream.writeInt(n[0]);
                dataOutputStream.writeInt(n[1]);
                dataOutputStream.writeInt(n[2]);
                dataOutputStream.writeInt(n[3]);
                dataOutputStream.writeInt(n[4]);

            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        public static void pedirDatos(){

            System.out.println("Ingrese su DNI");
            String dni = sc.nextLine();
            System.out.println("Ingrese su nombre");
            String nombre = sc.nextLine();
            System.out.println("Ingrese su edad");
            int edad = sc.nextInt();

            Persona persona = new Persona(dni, nombre, edad);
            ingresarPersona(persona);
        }

        public static void ingresarPersona(Persona persona){
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("persona.dat",true))){
                dos.writeUTF(persona.getDni());
                dos.writeUTF(persona.getNombre());
                dos.writeInt(persona.getEdad());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        public static void imprimirDatos(){
            try(DataInputStream dataInputStream = new DataInputStream(new FileInputStream("persona.dat"))) {
                while (true){
                    String dni = dataInputStream.readUTF();
                    String nombre = dataInputStream.readUTF();
                    int edad = dataInputStream.readInt();
                    System.out.println("DNI: " + dni+" Nombre: " + nombre+" Edad: " + edad);
                }
            }catch (EOFException e){
                System.out.println("Lectura del fichero completada");
            }
            catch (FileNotFoundException e){
                System.out.println(e.getMessage());
            }catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

}
