package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dao.BaseDatos;
import jdk.internal.misc.FileSystemOption;
import modelo.Datos;
import modelo.DatosId;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

 
public class Pruebas {



	@Test	
	public void testInsercionDatos() {
		
		BaseDatos bd = new BaseDatos();
		Datos datos = new Datos();
		
		long millis = System.currentTimeMillis();
		
		Date fecha = new Date(millis);		
		Time hora = new Time(millis);		
		
		Provincias provincia = new Provincias(0, "Gipuzkoa");		
		
		
		
		
		boolean b = bd.insertDatos(datos);
		assertEquals(true,b );
		
	}
	
}
