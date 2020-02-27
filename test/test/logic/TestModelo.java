package test.logic;

import static org.junit.Assert.*;

import model.logic.Feature;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private Feature dato;
	
	@Before
	public void setUp1() {
		modelo= new Modelo();
	}


	@Test
	public void testModelo() {
		assertTrue(modelo!=null);
		assertEquals(0, modelo.getFeaturesSize());  // Modelo con 0 elementos presentes.
	}

	@Test
	public void getFirstFeature(){
		setUp1();
		try
        {
            modelo.getFirstFeature();
            fail( "No debería encontrar un dato" );
        }
        catch( Exception e1 ){	
        }

        try
        {
            assertNull( "No deberia haber nada antes", modelo.getFirstFeature() );
        }
        catch( Exception e2 )
        {
            fail( "Hay un dato antes" );
        }
	}
	
	@Test
	public void getLastFeature(){
		setUp1();
		try
        {
            modelo.getLastFeature();
            fail( "No debería un dato" );
        }
        catch( Exception e1 ){
        }

        try
        {
            assertNull( "No deberia haber ningun dato despues",modelo.getLastFeature());
        }
        catch( Exception e2 )
        {
            fail( "Hay un dato despues" );
        }
	}

}
