package model.logic;

import model.data_structures.IQueue;

import model.data_structures.Queue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	private IQueue<Feature> queue;
	
	private Comparable<Feature>[] featuresArray;

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * 
	 * @param tamano
	 */
	public Modelo() {
		queue = new Queue<Feature>();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int getFeaturesSize() {
		return queue.size();
	}

	public Feature getFirstFeature() {
		return queue.getFront();
	}
	
	public Feature getLastFeature(){
		return queue.getBack();
	}

	public Comparable<Feature>[] copyFeatures(){
		
		if( !queue.isEmpty() ){
			featuresArray = new Comparable[ queue.size() ];
			
			for( int i = 0; i < featuresArray.length; i++ ){
				featuresArray[i] = queue.dequeue();
			}
		}
		
		Comparable<Feature>[] featuresLoaded = new Comparable[ featuresArray.length ];
		
		featuresLoaded = featuresArray;
		
		return featuresLoaded;
	}
	
	public void shellSort( Comparable<Feature>[] data ){
		
		int n = data.length;
		int h = 1;
		
		while( h < n/3 )
			h = 3*h + 1;
		
		while(h >= 1){
			
			for(int i = h; i < n; i++){
				for(int j = i; j>=h && less( data[j], data[j-h] ); j-=h ){
					Comparable<Feature> swap = data[j-h];
					data[j-h] = data[j];
					data[j] = swap;
				}
			}
			
			h = h/3;
		}
		
	}
	
	public void mergeSort( Comparable[] data, Comparable[] dataAux, int lowIdx, int hiIdx ){
		
		if( hiIdx <= lowIdx ) return;
		
		int mid = lowIdx + (hiIdx - lowIdx) / 2;
		
		mergeSort(data, dataAux, lowIdx, mid);
		mergeSort(data, dataAux, mid+1, hiIdx);
		merge(data, dataAux, lowIdx, mid, hiIdx);
	}
	
	private void merge( Comparable[] data, Comparable[] dataAux, int lowIdx, int mid, int hiIdx ){
		
		for( int i = lowIdx; i <= hiIdx; i++)
			dataAux[i] = data[i];
		
		int i = lowIdx;
		int j = mid+1;
		
		for( int k = lowIdx; k <= hiIdx; k++ ){
			
			if( i > mid )
				data[k] = dataAux[j++];
			else if ( j > hiIdx )
				data[k] = dataAux[i++];
			else if ( less(dataAux[j], dataAux[i]) )
				data[k] = dataAux[j++];
			else
				data[k] = dataAux[i++];
			
		}
	}
	
	public void quickSort (Comparable[] data, int lowIdx, int highIdx)
	{
		if(highIdx <= lowIdx) return;
		
		int p = partition(data, lowIdx, highIdx);
		
		quickSort(data, lowIdx, p-1);
		quickSort(data, p+1, highIdx);
	}
	
	private int partition (Comparable[] data, int lowIdx, int highIdx)
	{
		int j = highIdx;
		int i = lowIdx+1;
		
		while (i<=j)
		{
			if(data[i].compareTo(data[lowIdx])<=0)
			{
				i++;
			}
			else if(data[j].compareTo(data[lowIdx])>0)
			{
				j--;
			}
			else {
				swap(data, i, j);
			}
		}
		swap(data, lowIdx, j);
		return j;
	}
	
	private void swap (Object [] obj, int i, int j)
	{
		Object temporary = obj[i];
		obj[i]= obj[j];
		obj[j]= temporary;
	}
	
	
	
	private boolean less(Comparable obj1, Comparable obj2){
		return obj1.compareTo(obj2) < 0;
	}
	
	public boolean loadDataList(String path) {
		if( loadGson(path) )
			return true;
		else	
			return false;
	}

	private boolean loadGson(String path) {

		try {
			System.out.println(path);
			JsonReader reader = new JsonReader(new FileReader(path));
			JsonElement featuresElement = JsonParser.parseReader(reader).getAsJsonObject().get("features");
			JsonArray jsonFeaturesArray = featuresElement.getAsJsonArray();

			for (JsonElement element : jsonFeaturesArray) {

				String elemType = element.getAsJsonObject().get("type").getAsString();

				JsonElement elemProperties = element.getAsJsonObject().get("properties");

				int elemId = elemProperties.getAsJsonObject().get("OBJECTID").getAsInt();
				String elemDate = elemProperties.getAsJsonObject().get("FECHA_HORA").getAsString();
				String elemDetectionMethod = elemProperties.getAsJsonObject().get("MEDIO_DETE").getAsString();
				String elemVehicleClass = elemProperties.getAsJsonObject().get("CLASE_VEHI").getAsString();
				String elemServiceType = elemProperties.getAsJsonObject().get("TIPO_SERVI").getAsString();
				String elemInfraction = elemProperties.getAsJsonObject().get("INFRACCION").getAsString();
				String elemInfractionReason = elemProperties.getAsJsonObject().get("DES_INFRAC").getAsString();
				String elemLocality = elemProperties.getAsJsonObject().get("LOCALIDAD").getAsString();

				JsonElement elemGeometry = element.getAsJsonObject().get("geometry");

				String elemGeomType = elemGeometry.getAsJsonObject().get("type").getAsString();
				JsonArray elemGeomCoordinates = elemGeometry.getAsJsonObject().get("coordinates").getAsJsonArray();
				ArrayList<Integer> elemCoordinates = new ArrayList<Integer>();

				for (JsonElement elemCoord : elemGeomCoordinates) {
					int actualCoord = elemCoord.getAsInt();
					elemCoordinates.add(actualCoord);
				}

				Feature feature = new Feature(elemType, elemId, elemDate, elemDetectionMethod, elemVehicleClass,
						elemServiceType, elemInfraction, elemInfractionReason, elemLocality, elemGeomType,
						elemCoordinates);

				loadDataNode(feature);

			}

		} catch (FileNotFoundException e) {
			System.out.println("ERROR! File not found\n\n");
			return false;
		}
		
		return true;

	}

	private void loadDataNode(Feature feature) {
		queue.enqueue(feature);
	}

}