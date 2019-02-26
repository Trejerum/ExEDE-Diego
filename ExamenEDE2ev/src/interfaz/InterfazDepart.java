package interfaz;

import org.neodatis.odb.ODB;

import logica.Excepciones.NumDepartDuplicado;

public interface InterfazDepart {

	void insertarDep(ODB odb) throws NumDepartDuplicado;

	void borrarDep(ODB odb);

	void consultarDep(ODB odb);

	void modificarDep(ODB odb);

}