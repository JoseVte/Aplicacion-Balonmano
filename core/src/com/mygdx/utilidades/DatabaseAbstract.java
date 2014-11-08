package com.mygdx.utilidades;

//Clase general para implementar las app en android y escritorio
public abstract class DatabaseAbstract {

  protected static String database_name="tacticBoard";
  protected static DatabaseAbstract instance = null;
  protected static int version=1;

  //ejecutar query sin que devuelva resultado
  public abstract void execute(String sql);

  //ejecutar query devolviendo el numero de filas afectadas
  public abstract int executeUpdate(String sql);

  //ejecutar query y devolver el objeto
  public abstract Result query(String sql);

  public void onCreate(){
      execute("CREATE TABLE jugadas ("
      		+ "numero INTEGER PRIMARY KEY NOT NULL ,"
      		+ "nombre VARCHAR NOT NULL);");
      
      execute("CREATE TABLE posicion ( numeroPos INTEGER NOT NULL , jugada INTEGER NOT NULL, PRIMARY KEY(numeroPos,jugada), FOREIGN KEY(jugada) REFERENCES jugadas(numero);");
  }

  public void onUpgrade(){
      execute("DROP TABLE IF EXISTS jugadas;");
      execute("DROP TABLE IF EXISTS posicion;");
      onCreate();
      System.out.println("Actualizacion en la version de la BD");
  }

  //Interface to be implemented on both Android and Desktop Applications
  public interface Result{
      public boolean isEmpty();
      public boolean moveToNext();
      public int getColumnIndex(String name);
      public float getFloat(int columnIndex);
	public int getInt(int i);
  }
}
