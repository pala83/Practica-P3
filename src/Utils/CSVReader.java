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

public class CSVReader {
	private LinkedList<Maquina> maquinas;
	private int piezasTotales;

	public CSVReader() {
		this.maquinas=new LinkedList<Maquina>();
		this.piezasTotales=0;
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
				lines.add(line.split(";"));
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

    public List<Maquina> getMaquinas(){
        return List.copyOf(this.maquinas);
    }

    public int getPiezasTotales(){
        return this.piezasTotales;
    }

    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        reader.readEngines("./src/Data/Maqinas1.csv");
        System.out.println(reader.getMaquinas());
        System.out.println(reader.getPiezasTotales());
    }
}