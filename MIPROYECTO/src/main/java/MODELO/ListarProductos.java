package MODELO;

import Conexion.ConexionMysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListarProductos {
    
    public void MostrarTable(JTable tabla) {
        // Se crea una instancia de la clase ConexionMysql
        ConexionMysql con = new ConexionMysql();
        // Se llama al método conectar() para obtener la conexión
        Connection cn = con.conectar();
        
        // Se crea un nuevo modelo de tabla
        DefaultTableModel modelo = new DefaultTableModel();
        // Se agregan las columnas al modelo
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("Total");
        
        // Se define la consulta SQL para obtener los productos
        String consultasql = "SELECT * FROM producto"; // Selecciona todas las columnas de la tabla
        
        try {
            // Se crea un Statement para ejecutar la consulta
            Statement st = cn.createStatement();
            // Se ejecuta la consulta y se obtiene el resultado
            ResultSet rs = st.executeQuery(consultasql);
            
            // Se itera sobre el resultado y se agregan las filas al modelo
            while (rs.next()) {
                // Se obtienen los valores de las columnas en la fila actual
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                int cantidad = rs.getInt(3);
                double precio = rs.getDouble(4);
                double total = rs.getDouble(5);
                
                // Se agrega una nueva fila al modelo con los valores obtenidos
                modelo.addRow(new Object[]{id, nombre, cantidad, precio, total});
            }
            
            // Se cierra el ResultSet y el Statement
            rs.close();
            st.close();
            
            // Se asigna el modelo a la tabla
            tabla.setModel(modelo);
        } catch (Exception e) {
            // Se maneja cualquier excepción que pueda ocurrir durante la consulta
            System.out.println("ERROR AL LISTAR LOS DATOS" + e);
        }
    }
}