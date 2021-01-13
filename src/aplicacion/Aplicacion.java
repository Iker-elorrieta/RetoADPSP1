package aplicacion;

import java.sql.Date;
import java.sql.Time;

import dao.BaseDatos;
import modelo.Datos;
import modelo.DatosId;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Naturales;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.UbicacionesId;

public class Aplicacion {

	public static void main(String[] args) {
		

		BaseDatos bd = new BaseDatos();
		Datos datos = new Datos();
		
		long millis = System.currentTimeMillis();
		
		Date fecha = new Date(millis);		
		Time hora = new Time(millis);
		
		Provincias provincia = new Provincias(1,"Bizkaia");
		System.out.println("provincia"+bd.insertProvincias(provincia));
		
		Municipios municipio = new Municipios(0,provincia,"bil");
		System.out.println("muni"+bd.insertMunicipios(municipio));
		
		Naturales natural = new Naturales(0,"natural");
		System.out.println("nat"+bd.insertNaturales(natural));
		
		UbicacionesId uid = new UbicacionesId(0,0);
		Ubicaciones ubicacion = new Ubicaciones(uid,municipio,natural);	
		System.out.println("provincia"+bd.insertUbicaciones(ubicacion));
		
			
		Estaciones estacion = new Estaciones(0,municipio,"d","s",2.0,2.0,null);		
		bd.insertEstaciones(estacion);
		
		DatosId datosId = new DatosId(fecha,hora.toString(),0 );
		
		Datos dato2 = new Datos(datosId, estacion,3,3,3,3, "sd", 3, 3, 3, 3);
			
		
		boolean b = bd.insertDatos(dato2);
		System.out.println(b);
		
	}

}
