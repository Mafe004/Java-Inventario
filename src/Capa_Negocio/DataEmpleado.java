/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import java.util.ArrayList;
import java.sql.*;
import Capa_Datos.Conexion;

/**
 *
 * @author maria
 */
public class DataEmpleado {
    
     private String emp_ced;
    private String emp_nom;
    private Connection connection;

    public DataEmpleado(Connection connection) {
        this.connection = connection;
    }

    public String LlamarEmpleado() {
        String nombre = "Cédula no encontrada";

        try {
            String cad = "SELECT emp_nombre FROM empleado WHERE emp_cedula = ?";
            PreparedStatement stmt = connection.prepareStatement(cad);
            stmt.setString(1, this.getEmp_ced());

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                nombre = result.getString("emp_nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return nombre;
    }

    public String getEmp_ced() {
        return emp_ced;
    }

    public void setEmp_ced(String emp_ced) {
        this.emp_ced = emp_ced;
    }

    public String getEmp_nom() {
        return emp_nom;
    }

    public void setEmp_nom(String emp_nom) {
        this.emp_nom = emp_nom;
    }
}