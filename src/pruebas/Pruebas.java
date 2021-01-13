package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import dao.baseDatos;

 
public class Pruebas {



	/*@Test	
	public void testInsercion() {
		
		baseDatos bd = new baseDatos();
		
		boolean b = bd.insert();
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
