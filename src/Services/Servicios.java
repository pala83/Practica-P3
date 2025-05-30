//package Services;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//
//import utils.Back;
//import utils.CSVReader;
//import utils.Greedy;
//import utils.Procesador;
//import utils.Tarea;
//
///**
// * NO modificar la interfaz de esta clase ni sus métodos públicos.
// * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
// * de implementación.
// */
//public class Servicios {
//	private LinkedList<Procesador> procesadoresList;//
//	private HashMap<String, Tarea> tareasHash;//se usa para servicio 1
//	private LinkedList<Tarea> listaTareas;// se usa para el servicio 3 
//	private LinkedList<Tarea> tareasCriticasListtrue;//se usa para servicio 2
//	private LinkedList<Tarea> tareasCriticasListFalse;//se usa para servicio 2
//	private Back back;
//	private Greedy greedy;
//
//	
//	/*
//     * Expresar la complejidad temporal del constructor.
//	 * la complejidad del constructos sera O(n)
//     */
//	public Servicios(String pathProcesadores, String pathTareas)
//	{
//		CSVReader reader = new CSVReader();
//		reader.readProcessors(pathProcesadores);
//		reader.readTasks(pathTareas);
//		this.tareasHash = new HashMap<>();
//		this.procesadoresList = new LinkedList<>();
//		this.tareasCriticasListtrue = new LinkedList<>();
//		this.tareasCriticasListFalse = new LinkedList<>();
//		this.listaTareas= new LinkedList<>();
//		this.cargaraListaTarea(reader.getTareas());
//		this.cargarProcesadoresList(reader.getProcesadores());
//		this.cargarTareasHash(this.listaTareas);
//		this.cargartareasLinkedtrueAndFalse(this.listaTareas);
//	}
//	private void cargaraListaTarea(List<Tarea> aux){
//		for (Tarea tarea : aux) {
//			this.listaTareas.add(tarea);
//		}
//	}
//
//	private void cargarProcesadoresList(List<Procesador> auxList){
//		this.procesadoresList.addAll(auxList);
//	}
//	private void cargarTareasHash(List<Tarea> auxList){
//		for (Tarea tarea : auxList) {
//			this.tareasHash.put(tarea.getId(), tarea);
//		}
//	}
//	private void cargartareasLinkedtrueAndFalse(List<Tarea> auxList){
//		for (Tarea tarea : auxList) {
//			if(tarea.isEs_critica()){
//				this.tareasCriticasListtrue.add(tarea);
//			}else{
//				this.tareasCriticasListFalse.add(tarea);
//			}
//		}
//	}
//
//	/*
//     * Expresar la complejidad temporal del servicio 1.
//	 * al ser la estructura que usamos una hashmap la  complejidad del metodo es O(1)
//     */
//	public Tarea servicio1(String ID) {	
//		return this.tareasHash.get(ID);
//	}
//
//	public List<Procesador> getProcesadores() {
//		return new LinkedList<Procesador>(this.procesadoresList);
//	}
//    
//    /*
//     * Expresar la complejidad temporal del servicio 2.
//	 * la complejidad en la del metodo es O(1) ya que al tener ya los elementos precargados en tablas
//	 * se retornan directamenten sin hacer ningun tipo de busqueda
//     */
//	public List<Tarea> servicio2(boolean esCritica) {
//		if(esCritica){
//			return new LinkedList<Tarea>(this.tareasCriticasListtrue);
//		}
//		return new ArrayList<Tarea>(this.tareasCriticasListFalse);
//	}
//
//    /*
//     * Expresar la complejidad temporal del servicio 3.
//	 * la complejidad es O(n) n siendo la cantidad de elemtos que tiene las lista 
//	 * ya que se debe buscar en la totalidad de los elementos para sacar una respuesta concreta
//	 * y solida
//     */
//	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
//		LinkedList<Tarea> aux = new LinkedList<Tarea>();
//		for (Tarea tarea : listaTareas) {
//			if (tarea.getPrioridad() > prioridadInferior && tarea.getPrioridad() < prioridadSuperior) {
//				aux.add(tarea);
//			}
//		}
//		return aux;
//	}
//
//	/* 
//		ESTRATEGIA Backtraking
//	 *
//	 *  Se utiliza la lista de tareas y de procesadores dadas por los archivos "Procesadores.csv" y "Tareas.csv". Se inicializa tomando
//	 * la tarea con index 0 dentro de la lista de tareas y se consulta a cada procesador si se puede asignar esa tarea, 
//	 * en caso de que pueda se la asigna a ese procesador y vuelve a llamar a backtracking sobre la tarea siguiente. 
//	 * De esta forma intenta asignar todas las tareas a cada procesador. Cuando se llega a la última tarea termina y se obtiene 
//	 * el tiempo máximo que tardan los procesadores en ejecutar todas las tareas. Luego se crea una copia de la mejor 
//	 * solución obtenida (para que no se altere la lista original de procesadores) y se guarda en un atributo. 
//	 * Luego se imprime el estado de la solución y se muestra el tiempo máximo de ejecución y los candidatos conciderados.
//	 */
//
//	public void getSolucionBacktracking(int x) {
//		this.back = new Back(x);
//		this.back.backtracking(listaTareas, procesadoresList);
//		this.back.imprimirSolucion();
//	}
//
//
//	/* 
//	 * ESTRATEGIA Greedy 
//	 * por cada tarea se accede a un metodo que busca entre todos los procesadores si se puede agregar la tarea a ese procesador 
//	 * y si es la mejor opcion que se encuentra en el momento, y retorna la pocicion del procesador, si no encuentra un procesador 
//	 * retorna un valor no apto y retorna datos que destacan que no encontro solucion 
//	 */
//	public void getSolucionGreedy(int x){
//		this.greedy = new Greedy(this.listaTareas, this.procesadoresList);
//		this.greedy.getSolucion(x);
//		this.greedy.imprimirSolucion();
//	}
//}