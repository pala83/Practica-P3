package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import DTO.Maquina;

public class TXTReader {
	private LinkedList<Maquina> maquinas;
	private int piezasTotales;
	private char regex;

    /**
     * Lee un archivo .txt retorna la siguiente informacion:
	 * <ul>
	 * <li>La primera linea es el <strong>total de piezas a producir</strong>.</li>
	 * <li>Las siguientes lineas son las <strong>maquinas</strong>, con el nombre y la produccion.</li>
	 * </ul>
     * Se debe respetar el siguiente formato:
	 * <pre>
	 * PiezasTotales
	 * Máquina1,Piezas
	 * ...
	 * MáquinaN,Piezas
	 * </pre>
     * @param regex el delimitador utilizado para separar los campos en el archivo
     */
	public TXTReader(char regex) {
		this.maquinas=new LinkedList<Maquina>();
		this.piezasTotales=0;
		this.regex = regex;
	}
	
	public void readEngines(String enginesPath) {
		ArrayList<String[]> lines = this.readContent(enginesPath);
        Iterator<String[]> it = lines.iterator();
        this.piezasTotales = Integer.parseInt(it.next()[0].trim());
        while(it.hasNext()){
            String[] line = it.next();
			String nombreMaquina = line[0].trim();
		    int piezas = Integer.parseInt(line[1].trim());
			this.maquinas.add(new Maquina(nombreMaquina,piezas));
		}
		
	}
	
	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(String.valueOf(this.regex)));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		return lines;
	}

	public void setRegex(char regex) {
		this.regex = regex;
	}

    public List<Maquina> getMaquinas(){
        return List.copyOf(this.maquinas);
    }

    public int getPiezasTotales(){
        return this.piezasTotales;
    }

    public static void main(String[] args) {
        TXTReader reader = new TXTReader(',');
        reader.readEngines("./src/Data/Maqinas1.txt");
        System.out.println(reader.getMaquinas());
        System.out.println(reader.getPiezasTotales());
    }
}