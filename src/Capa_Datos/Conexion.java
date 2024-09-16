/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Datos;
import java.sql.*;

/**
 *
 * @author maria
 */
public class Conexion {

    private final String url = "jdbc:mysql://localhost:3307/Contabilidad";
    private final String user = "root"; // 
    private final String pwd = "";

    public Conexion() {
    }

    public ResultSet Listar(String Cad) {//devuelve un objeto ResultSet y toma un parámetro de tipo String llamado Cad
        try {
            Connection cn = DriverManager.getConnection(url, user, pwd); //establece una conexión a una base de datos utilizando la clase DriverManag
            PreparedStatement da = cn.prepareStatement(Cad);//establece la conexión y se crea un objeto PreparedStatement llamado da
            //PreparedStatement se utiliza para ejecutar consultas SQL parametrizadas
            ResultSet tbl = da.executeQuery();//ejecuta la consulta SQL 
            return tbl;
        } catch (SQLException e) {//Si no se ejecuta se muestra mensaje de error
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String Ejecutar(String Cad) {
        try {
            Connection cn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement da = cn.prepareStatement(Cad);
            int r = da.executeUpdate();//Este método se utiliza generalmente para ejecutar sentencias que afectan a la base de datos
            return "Se afectaron " + r + " filas";
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
            return "Error " + e.getMessage();
        }

    }
}
