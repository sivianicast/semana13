/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import entidades.Categoria;
import entidades.Libro;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jherom Chacon
 */
public class BaseDatos<Entidad> {//entidas
    private static String message;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String PATH = "jdbc:mysql://localhost:3306/";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "";
    private static final String DATA_BASE_NAME = "biblioteca";
    private Entidad dato;
    private String query="";
    public void setDato(Entidad dato){
        this.dato = dato;
    }
    public Entidad getDato(){
        return this.dato;
    }
    public void salvar() throws Exception{
        try{
            if(this.dato instanceof Categoria){
                this.salvar((Categoria)this.dato);
            }else if(this.dato instanceof Libro){
                this.salvar((Libro)this.dato);
            }
        }catch(Exception ex){
            throw ex;
        }
    }
    public void eliminar() throws Exception{
        try{
            if(this.dato instanceof Categoria){
                this.eliminar((Categoria)this.dato);
            }else if(this.dato instanceof Libro){
                this.eliminar((Libro)this.dato);
            }
        }catch(Exception ex){
            throw ex;
        }
    }
    public void consultar(int id) throws Exception{
        try{
        }catch(Exception ex){
            throw ex;
        }
    }
     private void salvar(Libro libro) throws Exception{
        this.query = "Insert into biblioteca.libro(id,categoria,nombre,fecha_llegada,prestado)"
                + "values("+libro.getId()+","
                + "values("+libro.getCategoria()+","
                + "'"+libro.getNombre()+"',"
                + libro.getFechaCreacion()+","
                + libro.getEstado()+")";
        try(Connection con = this.getConnection()){//try para ce
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
            this.message = "Entidad salvada";
            
        }catch(SQLException sqlEx){throw sqlEx;
        }catch(Exception ex){throw ex;}
    }
    private void salvar(Categoria categoria) throws Exception{
        this.query = "Insert into biblioteca.categoria(id,nombre,fecha_creacion,activo)"
                + "values("+categoria.getId()+","
                + "'"+categoria.getNombre()+"',"
                + categoria.getFechaCreacion()+","
                + categoria.getEstado()+")";
        try(Connection con = this.getConnection()){//try para ce
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }catch(SQLException sqlEx){throw sqlEx;
        }catch(Exception ex){throw ex;}
    }
   
    private void eliminar(Categoria categoria) throws Exception{}
    private void eliminar(Libro libro) throws Exception{}
    private Connection getConnection() throws Exception{
        java.sql.Connection connection = null;
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(
                    PATH + DATA_BASE_NAME, USER_NAME, PASSWORD);
        }catch(Exception ex){
            throw ex;
        }finally{
            return connection;
        }
    }
}
