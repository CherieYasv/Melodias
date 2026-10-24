package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.example.Modelo.CatalogoMelodias;
import com.example.Modelo.Melodia;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        CatalogoMelodias catalogo = new CatalogoMelodias();
        
        //Descomentar esta linea si se quiere probar con canciones puestas previamente:
        cargarDatosDePrueba(catalogo); 

        int opcion;
        do{
            mostrarMenu();
            opcion = leerEntero("Elige una opción: ", 0, 14);
            System.out.println();

            switch(opcion){
                case 1:
                    agregarMelodia(catalogo);
                    break;
                case 2:
                    eliminarMelodia(catalogo);
                    break;
                case 3:
                    conocerMelodia(catalogo);
                    break;
                case 4:
                    corregirMelodia(catalogo);
                    break;
                case 5:
                    valorColeccion(catalogo);
                    break;
                case 6:
                    cantidadCantantes(catalogo);
                    break;
                case 7:
                    listaCantantes(catalogo);
                    break;
                case 8:
                    melodiasPorGenero(catalogo);
                    break;
                case 9:
                    listaGeneros(catalogo);
                    break;
                case 10:
                    formatos(catalogo);
                    break;
                case 11:
                    melodiasDeCompositor(catalogo);
                    break;
                case 12:
                    melodiasDeCantante(catalogo);
                    break;
                case 13:
                    melodiasDeAno(catalogo);
                    break;
                case 14:
                    consultaGeneral(catalogo);
                    break;
                default:
                    break;
            }

            if(opcion != 0){
                System.out.println("\n1. Continuar");
                System.out.println("2. Salir del programa");
                int respuesta = leerEntero("Elige una opción: ", 1, 2);

                if(respuesta == 2){
                    opcion = 0;
                }
            }
        }while(opcion != 0);

        System.out.println("¡Hasta luego!");
    }

    //=================== MENU

    private static void mostrarMenu(){
        System.out.println("\n==============================================");
        System.out.println("                 MELODÍAS                       ");
        System.out.println("==============================================");
        System.out.println(" 1. Agregar una melodía");
        System.out.println(" 2. Eliminar una melodía por nombre");
        System.out.println(" 3. Conocer los datos de una melodía por nombre");
        System.out.println(" 4. Corregir los datos de una melodía");
        System.out.println(" 5. Valor de una colección");
        System.out.println(" 6. Cantantes del menu de melodías");
        System.out.println(" 7. Cantantes de la colección");
        System.out.println(" 8. Melodías de cada género");
        System.out.println(" 9. Géneros musicales de la colección");
        System.out.println("10. Formatos y cuántas melodías hay de cada uno");
        System.out.println("11. Melodías de un compositor");
        System.out.println("12. Melodías de un cantante");
        System.out.println("13. Melodías de un año");
        System.out.println("14. Consulta general");
        System.out.println(" 0. Salir");
        System.out.println("==============================================");
    }

    // =========== OPCIONES DEL MENU

    //Metodo para agregar una melodía al catalogo.
    private static void agregarMelodia(CatalogoMelodias catalogo){
        System.out.println("--- Agregar una melodía ---");
        String nombre = leerTexto("Nombre: ");
        String compositor = leerTexto("Compositor: ");
        int ano = leerAno();
        String genero = leerGenero();
        String cantante = leerTexto("Cantante: ");
        double precio = leerPrecio();
        String formato = leerFormato();

        Melodia nueva = new Melodia(nombre, compositor, ano, genero, cantante, precio, formato);

        // registrarMelodia regresa true si ya existia una igual
        if(catalogo.registrarMelodia(nueva)){
            System.out.println("\nEsa melodía ya está en el catálogo. No se agregó.");
        }else{
            System.out.println("\nMelodía agregada.");
        }
    }

    //Método para eliminar una melodia.
    private static void eliminarMelodia(CatalogoMelodias catalogo){
        System.out.println("--- Eliminar una melodía ---");
        Melodia elegida = elegirMelodiaPorNombre(catalogo);
        if(elegida == null){
            return;
        }

        System.out.println("\nVas a eliminar:");
        mostrarDatos(elegida);

        if(confirmar("¿Seguro que quieres eliminarla?")){
            catalogo.removerMelodia(elegida.getId());
            System.out.println("Melodía eliminada.");
        }else{
            System.out.println("No se eliminó nada.");
        }
    }

    //Metodo que imprime en pantalla los datos de la melodia en cuestión.
    private static void conocerMelodia(CatalogoMelodias catalogo){
        System.out.println("--- Datos de una melodía ---");
        String nombre = leerTexto("Nombre de la melodía: ");
        ArrayList<Melodia> encontradas = catalogo.buscar(nombre);

        if(encontradas.isEmpty()){
            System.out.println("\nNo tienes ninguna melodía con ese nombre.");
            return;
        }

        System.out.println("\nYa la tienes (" + encontradas.size() + " versión/es):");
        for(Melodia melodia:encontradas){
            System.out.println();
            mostrarDatos(melodia);
        }
    }

    //Método para correr un dato en especifico de una melodia en especifico. Regresando la melodia al catalogo.
    private static void corregirMelodia(CatalogoMelodias catalogo){
        System.out.println("--- Corregir los datos de una melodía ---");
        Melodia melodia = elegirMelodiaPorNombre(catalogo);
        if(melodia == null){
            return;
        }

        // Trabajamos sobre una copia y al final guardamos todo junto
        Melodia copia = new Melodia(melodia.getNombre(), melodia.getCompositor(), melodia.getAno(),
            melodia.getGenero(), melodia.getCantante(), melodia.getPrecio(), melodia.getFormato());

        int opcion;
        do{
            System.out.println("\nDatos actuales:");
            mostrarDatos(copia);
            System.out.println("\n¿Qué dato quieres corregir?");
            System.out.println("1. Nombre");
            System.out.println("2. Compositor");
            System.out.println("3. Año");
            System.out.println("4. Género");
            System.out.println("5. Cantante");
            System.out.println("6. Precio");
            System.out.println("7. Formato");
            System.out.println("0. Guardar cambios y volver");
            opcion = leerEntero("Elige una opción: ", 0, 7);

            switch(opcion){
                case 1:
                    copia.setNombre(leerTexto("Nuevo nombre: "));
                    break;
                case 2:
                    copia.setCompositor(leerTexto("Nuevo compositor: "));
                    break;
                case 3:
                    copia.setAno(leerAno());
                    break;
                case 4:
                    copia.setGenero(leerGenero());
                    break;
                case 5:
                    copia.setCantante(leerTexto("Nuevo cantante: "));
                    break;
                case 6:
                    copia.setPrecio(leerPrecio());
                    break;
                case 7:
                    copia.setFormato(leerFormato());
                    break;
                default:
                    break;
            }
        }while(opcion != 0);

        boolean guardada = catalogo.actualizarMelodia(melodia.getId(), copia.getNombre(), copia.getCompositor(),
            copia.getAno(), copia.getGenero(), copia.getCantante(), copia.getPrecio(), copia.getFormato());

        if(guardada){
            System.out.println("\nDatos guardados.");
        }else{
            System.out.println("\nNo se guardó, con esos datos quedaría igual a otra melodía ya existente.");
        }
    }

    //Método para citar al método de precio de la colección, imprimiendo en pantalla el precio total de la colección.
    private static void valorColeccion(CatalogoMelodias catalogo){
        System.out.println("Valor de la colección: $" + String.format("%.2f", catalogo.obtenerPrecioColeccion()));
    }

    //Método que obtiene el método de la cantidad de cantantes en la colección.
    private static void cantidadCantantes(CatalogoMelodias catalogo){
        System.out.println("Cantantes con melodías: " + catalogo.obtenerCantidadCantantes());
    }

    //Método que imprime en pantalla el resultado del método el cual ordena las melodias por cantante.
    private static void listaCantantes(CatalogoMelodias catalogo){
        ArrayList<String> cantantes = catalogo.ordenarMelodiasPorCantante();
        if(cantantes.isEmpty()){
            System.out.println("El catálogo está vacío.");
            return;
        }

        System.out.println("Cantantes con melodías:");
        cantantes.forEach(cantante -> System.out.println("- " + cantante));
    }

    //Método que llama al método que ordena las melodias por método.
    private static void melodiasPorGenero(CatalogoMelodias catalogo){
        Map<String, List<Melodia>> porGenero = catalogo.melodiasPorGenero();
        if(porGenero.isEmpty()){
            System.out.println("El catálogo está vacío.");
            return;
        }

        porGenero.forEach((genero, lista) -> {
            System.out.println("\n" + genero + ":");
            lista.forEach(melodia -> System.out.println("  - " + melodia));
        });
    }

    //Método que llama al método que contiene todos los generos del catalogo.
    private static void listaGeneros(CatalogoMelodias catalogo){
        ArrayList<String> generos = catalogo.obtenerTodosLosGeneros();
        if(generos.isEmpty()){
            System.out.println("El catálogo está vacío.");
            return;
        }

        System.out.println("Géneros de la colección:");
        for(String genero:generos){
            System.out.println("- " + genero);
        }
    }

    //Método que imprime en pantalla los formatos en la colección.
    private static void formatos(CatalogoMelodias catalogo){
        TreeMap<String, Integer> formatos = catalogo.contarMelodiasPorFormato();
        if(formatos.isEmpty()){
            System.out.println("El catálogo está vacío.");
            return;
        }

        System.out.println("Formatos en la colección:");
        formatos.forEach((formato, cantidad) -> System.out.println("- " + formato + ": " + cantidad + " melodía(s)"));
    }

    //Método que usa el método para mostrar las melodias por compositor.
    private static void melodiasDeCompositor(CatalogoMelodias catalogo){
        String compositor = leerTexto("Nombre del compositor: ");
        System.out.println();
        mostrarLista(catalogo.melodiasPorCompositor(compositor), "No hay melodías de ese compositor.");
    }

    //Método que llama el método que imprime las melodias por cantante.
    private static void melodiasDeCantante(CatalogoMelodias catalogo){
        String cantante = leerTexto("Nombre del cantante: ");
        System.out.println();
        mostrarLista(catalogo.melodiasPorCantante(cantante), "No hay melodías de ese cantante.");
    }

    //Método que llama al método que muestra las melodias por año.
    private static void melodiasDeAno(CatalogoMelodias catalogo){
        int ano = leerAno();
        System.out.println();
        mostrarLista(catalogo.melodiasPorAno(ano), "No hay melodías de ese año.");
    }

    //Método estatico para consultar la informacion de una melodia.
    private static void consultaGeneral(CatalogoMelodias catalogo){
        System.out.println("--- Consulta general ---");
        System.out.println("¿Cómo la quieres ordenada?");
        System.out.println("1. Por nombre de la melodía");
        System.out.println("2. Por cantante");
        System.out.println("3. Por año");
        System.out.println("4. Por precio");
        System.out.println("0. Cancelar");
        int opcion = leerEntero("Elige una opción: ", 0, 4);

        ArrayList<Melodia> lista = new ArrayList<>();
        switch(opcion){
            case 1:
                lista = catalogo.ordenarMelodiasPorNombre();
                break;
            case 2:
                lista = catalogo.ordenarMelodiasPorNombreCantante();
                break;
            case 3:
                lista = catalogo.ordenarMelodiasPorAno();
                break;
            case 4:
                lista = catalogo.ordenarMelodiasPorPrecio();
                break;
            default:
                return;
        }

        System.out.println();
        mostrarLista(lista, "El catálogo está vacío.");
    }

    //Método para mostrar la lista de las canciones, con sus datos correspondientes.
    private static void mostrarLista(ArrayList<Melodia> lista, String mensajeVacio){
        if(lista.isEmpty()){
            System.out.println(mensajeVacio);
            return;
        }

        for(Melodia melodia:lista){
            System.out.println("- " + melodia);
        }
    }

    private static void mostrarDatos(Melodia melodia){
        System.out.println("  Nombre:     " + melodia.getNombre());
        System.out.println("  Compositor: " + melodia.getCompositor());
        System.out.println("  Cantante:   " + melodia.getCantante());
        System.out.println("  Año:        " + melodia.getAno());
        System.out.println("  Género:     " + melodia.getGenero());
        System.out.println("  Precio:     $" + String.format("%.2f", melodia.getPrecio()));
        System.out.println("  Formato:    " + melodia.getFormato());
    }

    // Pide un nombre y regresa la melodia elegida (o null si no hay).
    // Si hay varias versiones con el mismo nombre, deja escoger cual.
    private static Melodia elegirMelodiaPorNombre(CatalogoMelodias catalogo){
        String nombre = leerTexto("Nombre de la melodía: ");
        ArrayList<Melodia> encontradas = catalogo.buscar(nombre);

        if(encontradas.isEmpty()){
            System.out.println("\nNo hay ninguna melodía con ese nombre.");
            return null;
        }

        if(encontradas.size() == 1){
            return encontradas.get(0);
        }

        System.out.println("\nHay varias versiones con ese nombre:");
        for(int i = 0; i < encontradas.size(); i++){
            System.out.println((i + 1) + ". " + encontradas.get(i));
        }

        int numero = leerEntero("¿Cuál quieres? (1 a " + encontradas.size() + "): ", 1, encontradas.size());
        return encontradas.get(numero - 1);
    }

    //Método para validar todos los datos de todos los métodos
    private static String leerLinea(String mensaje){
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    // Método que recibe datos enteros entre min y max. Rechazando vacio, letras, espacios, simbolos, negativos y fuera de rango
    private static int leerEntero(String mensaje, int min, int max){
        while(true){
            String texto = leerLinea(mensaje);

            if(texto.isEmpty()){
                System.out.println("  Error: no escribiste nada.");
            }else if(texto.startsWith("-")){
                System.out.println("  Error: no se permiten números negativos ni el símbolo \"-\".");
            }else if(!soloDigitos(texto)){
                System.out.println("  Error: escribe solo números, sin letras, espacios ni símbolos.");
            }else if(texto.length() > 9){
                System.out.println("  Error: ese número es demasiado grande.");
            }else{
                int numero = Integer.parseInt(texto);

                if(numero >= min && numero <= max){
                    return numero;
                }

                System.out.println("  Error: el número debe estar entre " + min + " y " + max + ".");
            }
        }
    }

    //Método simple para leer el año y actualizar dicha melodia.
    private static int leerAno(){
        return leerEntero("Año de grabación: ", 1800, 2100);
    }

    // Precio mayor a 0. Puede llevar un punto decimal
    private static double leerPrecio(){
        while(true){
            String texto = leerLinea("Precio pagado: $");

            if(texto.isEmpty()){
                System.out.println("  Error: no escribiste nada.");
            }else if(texto.startsWith("-")){
                System.out.println("  Error: no se permiten negativos ni el símbolo \"-\".");
            }else if(!esNumeroDecimal(texto)){
                System.out.println("  Error: escribe solo números, con un punto para los decimales, sin letras, espacios ni otros símbolos.");
            }else if(texto.length() > 9){
                System.out.println("  Error: ese precio es demasiado grande.");
            }else{
                double precio = Double.parseDouble(texto);

                if(precio > 0){
                    return precio;
                }

                System.out.println("  Error: el precio debe ser mayor que 0.");
            }
        }
    }

    // Texto general (nombre, compositor, cantante)
    // y con al menos una letra o numero (no solo simbolos)
    private static String leerTexto(String mensaje){
        while(true){
            String texto = leerLinea(mensaje);

            if(texto.isEmpty()){
                System.out.println("  Error: no escribiste nada.");
            }else if(texto.length() > 100){
                System.out.println("  Error: es demasiado largo.");
            }else if(!tieneLetraONumero(texto)){
                System.out.println("  Error: tiene que tener al menos una letra o un número.");
            }else{
                return texto;
            }
        }
    }

    // Método para guardar un genero.
    private static String leerGenero(){
        while(true){
            String texto = leerLinea("Género musical: ");

            if(texto.isEmpty()){
                System.out.println("  Error: no escribiste nada.");
            }else if(texto.length() > 50){
                System.out.println("  Error: es demasiado largo.");
            }else if(!soloLetrasYEspacios(texto)){
                System.out.println("  Error: usa solo letras y espacios, sin números ni símbolos.");
            }else{
                return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
            }
        }
    }

    //Método para leer el formato de la canción en cuestión
    private static String leerFormato(){
        while(true){
            String texto = leerLinea("Formato: ");

            // Si lo escribio con punto, se lo quitamos para revisar lo demas
            if(texto.startsWith(".")){
                texto = texto.substring(1);
            }

            if(texto.length() < 2 || texto.length() > 10){
                System.out.println("  Error: el formato debe tener de 2 a 10 caracteres.");
            }else if(!soloLetrasYNumeros(texto)){
                System.out.println("  Error: el formato solo puede tener letras y números.");
            }else{
                return "." + texto.toLowerCase();
            }
        }
    }

    // Método para preguntas de si o no.
    private static boolean confirmar(String mensaje){
        while(true){
            String respuesta = leerLinea(mensaje + " (s/n): ").toLowerCase();

            if(respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")){
                return true;
            }

            if(respuesta.equals("n") || respuesta.equals("no")){
                return false;
            }

            System.out.println("  Error: responde solo con \"s\" o \"n\".");
        }
    }

    //Método para revisar un solo digito en las preguntas que se requieran.
    private static boolean soloDigitos(String texto){
        for(int i = 0; i < texto.length(); i++){
            if(!Character.isDigit(texto.charAt(i))){
                return false;
            }
        }
        return true;
    }

    // Digitos y maximo un punto, que no puede ir al inicio ni al final
    private static boolean esNumeroDecimal(String texto){
        int puntos = 0;

        for(int i = 0; i < texto.length(); i++){
            char letra = texto.charAt(i);

            if(letra == '.'){
                puntos++;
            }else if(!Character.isDigit(letra)){
                return false;
            }
        }

        return puntos <= 1 && !texto.startsWith(".") && !texto.endsWith(".");
    }

    //Método para validar letras o espacios.
    private static boolean soloLetrasYEspacios(String texto){
        for(int i = 0; i < texto.length(); i++){
            char letra = texto.charAt(i);

            if(!Character.isLetter(letra) && letra != ' '){
                return false;
            }
        }
        return true;
    }

    //Método para validar letras y numeros.
    private static boolean soloLetrasYNumeros(String texto){
        for(int i = 0; i < texto.length(); i++){
            if(!Character.isLetterOrDigit(texto.charAt(i))){
                return false;
            }
        }
        return true;
    }

    //Método para validar letras o numeros.
    private static boolean tieneLetraONumero(String texto){
        for(int i = 0; i < texto.length(); i++){
            if(Character.isLetterOrDigit(texto.charAt(i))){
                return true;
            }
        }
        return false;
    }

    //============== DATOS DE PRUEBA

    private static void cargarDatosDePrueba(CatalogoMelodias catalogo){
        // Cuarteto de Nos YEEEEEEEEEEEH
        catalogo.registrarMelodia(new Melodia("Raro", "Roberto Musso", 2006, "Rock", "Cuarteto de Nos", 67, ".flac"));
        catalogo.registrarMelodia(new Melodia("Cuando sea grande", "Roberto Musso", 2006, "Rock", "Cuarteto de Nos", 55, ".mp3"));
        catalogo.registrarMelodia(new Melodia("Yendo a la casa de Damian", "Roberto Musso", 2006, "Rock", "Cuarteto de Nos", 60, ".mp3"));

        // The Beatles YAAAAAAAAAAAAAAAH
        catalogo.registrarMelodia(new Melodia("Hey Jude", "John Lennon", 1968, "Rock", "The Beatles", 70, ".flac"));
        catalogo.registrarMelodia(new Melodia("Yesterday", "John Lennon", 1965, "Pop", "The Beatles", 65, ".mp3"));
        catalogo.registrarMelodia(new Melodia("Let It Be", "John Lennon", 1970, "Pop", "The Beatles", 68, ".flac"));
    }
}