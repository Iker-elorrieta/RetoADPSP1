package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import dao.BaseDatos;

 
public class Pruebas {



	/*@Test	
	public void testInsercionDatos() {
		
		BaseDatos bd = new BaseDatos();
		Datos datos = new Datos();
		
		long millis = System.currentTimeMillis();
		
		Date fecha = new Date(millis);		
		Time hora = new Time(millis);		
		
		Provincias provincia = new Provincias(0, "Gipuzkoa");		
		
		
		
		baseDatos bd = new baseDatos();
		
		boolean b = bd.insertDatos(datos);
		assertEquals(true,b );
		
	}*/
	
	@Test
	public void testServidorOK() {
		String[] args = null;
		PSP.Servidor.main(args);
		PSP.Servidor s = new PSP.Servidor();
		
		boolean  boo = s.iniciar();
		
		assertTrue(boo);
		
	}
	
	@Test
	public void testServidorMAL1() {
		PSP.Servidor s1 = new PSP.Servidor();

		boolean boo = s1.conectar("33","jdbc:mysql://localhost:3306/euskalmet");
		
		assertFalse(boo);
		
	}

	public void testServidorMAL2() {
		PSP.Servidor s2 = new PSP.Servidor();

		boolean boo = s2.conectar("com.mysql.jdbc.Driver","33");
		
		assertFalse(boo);
		
	}
	
	@Test
	public void testCliente() {
		
		PSP.Servidor sc = new PSP.Servidor();
		sc.iniciar();
		
		String[] args = null;
		PSP.Cliente.main(args);
		PSP.Cliente c = new PSP.Cliente();
		
		boolean boo = c.iniciar();
		
		assertTrue(boo);
	}
	
}
