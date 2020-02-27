package view;

import model.logic.Feature;
import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("\n1. Requerimiento 1 (copiar comparendos)");
			System.out.println("2. Requerimiento 2 (Ordenar ascendentemente con Shell sort)");
			System.out.println("3. Requerimiento 3 (Ordenar ascendentemente con Merge sort)");
			System.out.println("4. Requerimiento 4 (Ordenar ascendentemente con Quick sort)");
			System.out.println("5. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}
		
		public void printFeature(Feature feature){
			
			if(feature == null){
				System.out.println("No hay info de este comparendo o no existe.");
			}
			else{
				System.out.println("\nCOMPARENDO:");
				System.out.println("\n\tOBJECTID: " + feature.getObjectId());
				System.out.println("\n\tFECHA_HORA: " + feature.getDate());
				System.out.println("\n\tINFRACCION: " + feature.getInfraction());
				System.out.println("\n\tCLASE_VEHI: " + feature.getVehicleClass());
				System.out.println("\n\tTIPO_SERVI: " + feature.getServiceType());
				System.out.println("\n\tLOCALIDAD: " + feature.getLocality());
				System.out.println("\n");
			}
			
		}
		
		public void printGeneralFeaturesInfo( Feature firstFeature, Feature lastFeature, int featuresNumber ){
			
			System.out.println("\nDATOS GENERALES:");
			System.out.println("\n-----------------------------------\n\n");
			
			System.out.println("\nPRIMER COMPARENDO:");
			printFeature(firstFeature);
			
			System.out.println("\nULTIMO COMPARENDO:");
			printFeature(lastFeature);
			
			System.out.println("\nNUMERO TOTAL DE COMPARENDOS: " + featuresNumber);
			System.out.println("\n");
			
		}
		
		public void printTenFirstAndLast( Comparable<Feature>[] data )
		{
			printMessage("\nRESULTADO::::\n");
			if( data.length > 20 ){
				
				for( int i = 0; i < 10; i++ ){
					printMessage("\n\t" + (i+1) + ":\n");
					Feature f = (Feature)data[i];
					printFeature( f );
				}
				
				int firstOfTenLast = data.length-10;
				
				for( int i = firstOfTenLast-1; i < data.length; i++ ){
					printMessage("\n\t" + (i+1) + ":\n");
					Feature f = (Feature)data[i];
					printFeature( f );
				}
				
			}
			else{
				for(int i = 0; i < data.length; i++){
					printMessage("\n\t" + (i+1) + ":\n");
					Feature f = (Feature)data[i];
					printFeature( f );
				}
			}
		}
}
