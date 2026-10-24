Clase
  Melodia ->
  
    La forma base de la melodía en cuestión. Con ID, nombre, compositor, año, genero, cantante, precio y formato. Con getters, setters, toString y equals.
    
Clase
  CatalogoMelodias ->
  
    boolean registrarMelodia(Melodia melodia) -> Registrar una melodía, insertando todos sus datos.
    
    removerMelodia(int id) -> Remover una melodia del catalogo, en este caso pudiendo hacerlo por nombre e ID.
    
    ArrayList<Melodia>buscar(String nombreMelodia) -> Buscar la información de una canción por su nombre.
    
    Melodia buscar(int id) -> Buscar una canción por su ID.
    
    actualizarMelodia(int id, String nombreMelodia y todos los demas) -> Actualizar los datos de una melodía
    
    obtenerPrecioColeccion() -> Obtener el precio de la colección entera.
    
    ArrayList<String> obtenerTodosLosGeneros() -> Obtener en una lista todos los generos de tu catalogo.
    
    ArrayList<Melodia> melodiasPorCompositor(String compositor) -> Obtener una lista de los compositores de todo tu catalogo.
    
    ArrayList<Melodia> melodiasPorAnio(int anio) -> Obtener los años de todas las melodias de tu catalogo.
    
    ordenarMelodiasPorPrecion() -> Mostrar en una lista las melodias ordenadas por precio.
    
    ordenarMelodiasPorAnio() -> Mostrar en una lista las melodias ordenadas por año. 
    
    ordenarMelodiasPorCantante() -> Mostrar en una lista las melodias ordenadas por cantante.

  Clase
    Main ->

      El main en este programa actua como intermedario para validar todas las opciones. El main cuenta con clases estaticas de cada método anteriormente presentado para pasarlo por los métodos de validaciones.

**Instrucciones de compilacion:**

Contar previamente con java openjdk 27 o superior.
Descargar "cadenas.java", "numeros.java" y "Main.java" y colocarlos en una misma carpeta.
Ejecutarlo con su IDE de confianza. O respectivamente: Para ejecutar en cmd o Powershell de Windows, colocar estos 3 comandos por separado:

```
    cd C:\ruta\a\tu\carpeta
    javac -encoding UTF-8 Main.java
    chcp 65001
    java -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 Main
```
Para ejecutar en la terminal de Linux, colocar estos 3 comandos por separado:
```
cd /ruta/a/tu/carpeta
javac -encoding UTF-8 Main.java
java Main
```
