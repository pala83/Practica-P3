package Utils;

import java.nio.file.Path;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que se encarga de seleccionar un archivo .txt de un directorio dado.
 */
public class FileSelector {

    /**
     * Lista los archivos .txt de la carpeta Data, pide al usuario que elija uno
     * y devuelve la ruta del archivo elegido.
     *
     * @param dataDirPath la ruta a la carpeta Data (puede ser relativa o absoluta)
     * @return la Path del archivo seleccionado
     * @throws Exception si ocurre un error de E/S o selección inválida
     */
    public Path chooseDataFile(String dataDirPath) throws Exception{
        Path dataDir = Paths.get(dataDirPath);
        if(!Files.isDirectory(dataDir))
            throw new IllegalArgumentException("La ruta no es un directorio valido"+dataDirPath);

        // Lista unicamente los archivos .txt
        File dir = dataDir.toFile();
        File[] files = dir.listFiles();
        if (files == null) {
            throw new IllegalStateException("No se pudo acceder al directorio: " + dataDirPath);
        }
        List<Path> txtFiles = new ArrayList<>();
        for (File file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
                txtFiles.add(file.toPath());
            }
        }
        if (txtFiles.isEmpty()) {
            throw new IllegalStateException("No se encontraron archivos .txt en " + dataDirPath);
        }

        // Si solo hay un archivo, se selecciona automáticamente
        if (txtFiles.size() == 1) {
            Path single = txtFiles.get(0);
            System.out.println("Solo se encontró un archivo: " + single.getFileName() + ". Seleccionado automáticamente.");
            return single;
        }
        // Si hay varios archivos, se pide al usuario que elija uno
        System.out.println("Archivos disponibles en:'"+dataDirPath+"':");
        for(int i = 0; i < txtFiles.size(); i++)
            System.out.println(i+1+". "+txtFiles.get(i).getFileName());
        // Lee la seleccion del usuario
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while(choice < 1 || choice > txtFiles.size()){
            System.out.print("Seleccione un archivo (1-"+txtFiles.size()+"): ");
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
            }else{
                scanner.next();
            }
        }
        scanner.close();
        Path selectedFile = txtFiles.get(choice-1);
        System.out.println("Archivo seleccionado: "+selectedFile.getFileName());
        return selectedFile;
    }

    public void main(String[] args) {
        try {
            Path fileToRead = chooseDataFile("./src/Data");
            TXTReader reader = new TXTReader(',');
            reader.readEngines(fileToRead.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
