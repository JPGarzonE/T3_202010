package controller;

import java.util.Scanner;

import model.logic.Feature;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;
	
	/* Instancia de la Vista*/
	private View view;
	
	static final String DATA_PATH = "./data/comparendos_dei_2018_small.geojson";
	
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}
		
	public void run() 
	{
		loadFeatures();
		
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		Comparable<Feature>[] features = null;

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
				case 1:
					view.printMessage("--------- \nCopiando comparendos...");
					features = modelo.copyFeatures();
					view.printMessage("El nuevo arreglo tiene " + features.length + " comparendo(s)");
					break;
				
				case 2:
					view.printMessage("--------- \nOrdenando por Shell sort...");
					if( features != null ){
						modelo.shellSort(features);
						view.printTenFirstAndLast( features );
					}else
						view.printMessage("\n¡¡¡Primero copia los comparendos antes de ordenarlos!!!\n");
					break;
					
				case 3: 
					view.printMessage("--------- \n Hasta pronto !! \n---------"); 
					lector.close();
					fin = true;
					break;	

				default: 
					view.printMessage("--------- \n Opcion Invalida !! \n---------");
					break;
			}
		}
		
	}	
	
	private void loadFeatures(){
		view.printMessage("--------- \nCargando datos de comparendos...");
	    modelo = new Modelo();
	    if( modelo.loadDataList(DATA_PATH) ){
		    Feature firstFeature = modelo.getFirstFeature();
		    Feature lastFeature = modelo.getLastFeature();
		    int featuresNumber = modelo.getFeaturesSize();
		    view.printGeneralFeaturesInfo(firstFeature, lastFeature, featuresNumber);
	    }
	}
}
