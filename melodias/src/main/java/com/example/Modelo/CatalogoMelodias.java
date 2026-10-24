package com.example.Modelo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CatalogoMelodias {
    private ArrayList<Melodia> canciones = new ArrayList<>();

    //bandera
    //true -> ya existia una igual (no se agrega)
    //false -> no habia una igual (se agrega)
    public boolean registrarMelodia(Melodia obj){
        boolean bandera = false;
        for(Melodia melodia:canciones){
            bandera = melodia.equals(obj);

            if(bandera){
                break;
            }
        }

        if(!bandera){
            canciones.add(obj);
        }

        return bandera;
    }

    // Elimina la melodia con ese id. Regresa true si la encontro y la borro
    public boolean removerMelodia(int id){
        return canciones.removeIf(melodia -> melodia.getId() == id);
    }

    // Busca todas las melodias que se llamen asi (puede haber varias versiones)
    public ArrayList<Melodia> buscar(String nombreMelodia){
        return new ArrayList<>(canciones
            .stream()
            .filter(melodia -> melodia.getNombre().equalsIgnoreCase(nombreMelodia))
            .collect(Collectors.toList()));
    }

    // Busca una melodia por su id. Regresa null si no existe
    public Melodia buscar(int id){
        for(Melodia melodia:canciones){
            if(melodia.getId() == id){
                return melodia;
            }
        }
        return null;
    }

    // Corrige los datos de la melodia con ese id.
    // Regresa false si no existe o si los datos nuevos la dejarian igual a otra que ya esta registrada.
    public boolean actualizarMelodia(int id, String nombre, String compositor, int ano, String genero, String cantante, double precio, String formato){
        Melodia melodia = buscar(id);
        if(melodia == null){
            return false;
        }

        Melodia nueva = new Melodia(nombre, compositor, ano, genero, cantante, precio, formato);
        for(Melodia otra:canciones){
            if(otra.getId() != id && otra.equals(nueva)){
                return false;
            }
        }

        melodia.setNombre(nombre);
        melodia.setCompositor(compositor);
        melodia.setAno(ano);
        melodia.setGenero(genero);
        melodia.setCantante(cantante);
        melodia.setPrecio(precio);
        melodia.setFormato(formato);
        return true;
    }

    public String mostrarTodasMelodias(){
        StringBuffer sb = new StringBuffer();
        for(Melodia melodia:canciones){
            sb.append(melodia);
            sb.append("\n");
        }
        return sb.toString();
    }

    // Suma lo que se pago por todas las melodias
    public double obtenerPrecioColeccion(){
        return canciones.stream().mapToDouble(Melodia::getPrecio).sum();
    }

    //Método para obtener todos los generos disponibles en una lista.
    public ArrayList<String> obtenerTodosLosGeneros(){
        ArrayList<String> generos = new ArrayList<>(canciones
            .stream()
            .map(Melodia::getGenero)
            .distinct()
            .collect(Collectors.toList()));

        return generos;
    }

    //Método para obtener todos los generos iterativos disponibles en una lista.
    public HashSet<String> obtenerTodosLosGenerosIterativos(){
        HashSet<String> generos = new HashSet<>();
        for(Melodia cancion:canciones){
            generos.add(cancion.getGenero());
        }

        return generos;
    }

    // Agrupa las melodias por genero (los generos salen en orden alfabetico)
    public Map<String, List<Melodia>> melodiasPorGenero(){
        return new TreeMap<>(canciones
            .stream()
            .collect(Collectors.groupingBy(Melodia::getGenero)));
    }

    // Cuenta cuantas melodias hay de cada formato
    public TreeMap<String, Integer> contarMelodiasPorFormato(){
        TreeMap<String, Integer> formatos = new TreeMap<>();
        for(Melodia melodia:canciones){
            formatos.put(melodia.getFormato(), formatos.getOrDefault(melodia.getFormato(), 0) + 1);
        }
        return formatos;
    }

    //Método para obtener la cantidad de cantantes total del catalogo.
    public int obtenerCantidadCantantes(){
        return (int) canciones.stream().map(Melodia::getCantante).distinct().count();
    }

    //Método para encontras las melodias por compositor.
    public ArrayList<Melodia> melodiasPorCompositor(String compositor){
        return new ArrayList<>(canciones
            .stream()
            .filter(melodia -> melodia.getCompositor().equalsIgnoreCase(compositor))
            .collect(Collectors.toList()));
    }

    //Método para listar las melodias por cantante.
    public ArrayList<Melodia> melodiasPorCantante(String cantante){
        return new ArrayList<>(canciones
            .stream()
            .filter(melodia -> melodia.getCantante().equalsIgnoreCase(cantante))
            .collect(Collectors.toList()));
    }

    //Método para listar las melodias por año.
    public ArrayList<Melodia> melodiasPorAno(int ano){
        ArrayList<Melodia> resultado = new ArrayList<>();
        for(Melodia melodia:canciones){
            if(melodia.getAno() == ano){
                resultado.add(melodia);
            }
        }
        return resultado;
    }

    // Regresa los nombres de los cantantes, sin repetir y en orden alfabetico
    public ArrayList<String> ordenarMelodiasPorCantante(){
        ArrayList<String> cantante = new ArrayList<>(canciones
            .stream()
            .map(Melodia::getCantante)
            .distinct()
            .sorted()
            .collect(Collectors.toList()));

        return cantante;
    }

    //Método que regresa las melodias completas ordenadas por cada nombre
    public ArrayList<Melodia> ordenarMelodiasPorNombre(){
        return new ArrayList<>(canciones
            .stream()
            .sorted(Comparator.comparing(Melodia::getNombre))
            .collect(Collectors.toList()));
    }
    //Método que regresa las melodias completas ordenadas por cada cantante
    public ArrayList<Melodia> ordenarMelodiasPorNombreCantante(){
        return new ArrayList<>(canciones
            .stream()
            .sorted(Comparator.comparing(Melodia::getCantante).thenComparing(Melodia::getNombre))
            .collect(Collectors.toList()));
    }

    //Método que regresa las melodias completas ordenadas por cada año
    public ArrayList<Melodia> ordenarMelodiasPorAno(){
        return new ArrayList<>(canciones
            .stream()
            .sorted(Comparator.comparing(Melodia::getAno))
            .collect(Collectors.toList()));
    }

    //Método que regresa las melodias completas ordenadas por cada precio
    public ArrayList<Melodia> ordenarMelodiasPorPrecio(){
        return new ArrayList<>(canciones
            .stream()
            .sorted(Comparator.comparing(Melodia::getPrecio))
            .collect(Collectors.toList()));
    }
}