/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Negocio;

import Capa_Datos.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author maria
 */
public class DataFactura {

    //Variables
    private int fact_id_Factura;
    private int FKemp_cedula;
    private int fact_fecha;
    private String fact_cod;
    private int fact_can;

    public String EliminarFactura() {//Metodo
        Conexion objmod = new Conexion();//Crea un instacion 
        String cad = "DELETE FROM detalle WHERE fact_id_Factura= '" + this.getFact_id_Factura() + "'"; //Genera una sentencia para eliminar
        return objmod.Ejecutar(cad);
    }

    public String Actualizar() {
        Conexion objmod = new Conexion();
        String cad = "UPDATE factura SET fact_cantidad = '" + this.getFact_can() + "' WHERE fact_id_Factura = " + this.getFact_id_Factura();
        return objmod.Ejecutar(cad);
    }

    public String LlamarFactura() {

        Conexion objmod = new Conexion();
        String cad = "select * from detalle WHERE fact_id_Factura = '" + this.getFact_id_Factura() + "'";
        return objmod.Ejecutar(cad);

    }

    public String GrabarFactura() {
        Conexion objmod = new Conexion();
        String cad = "INSERT INTO factura (fact_id_Factura, FKemp_cedula, fact_fecha, fact_codigo, fact_cantidad) "
                + "VALUES ('" + this.getFact_id_Factura() + "','" + this.getFKemp_cedula() + "','" + this.getFact_fecha() + "','" + this.getFact_cod() + "','" + this.getFact_can() + "')";
        return objmod.Ejecutar(cad);
    }

    public String EliminarTodosFactura() {
        Conexion objmod = new Conexion();
        String cad = "DELETE FROM factura"; // Elimina todos los registros de la tabla "factura"
        return objmod.Ejecutar(cad);
    }

    public ArrayList< DataVentas> ListaFActura() {
        ArrayList lista = new ArrayList();
        try {
            Conexion objmod = new Conexion();
            ResultSet tabla1 = objmod.Listar("select * from factura");
            DataFactura objart;
            while (tabla1.next()) {
                objart = new DataFactura();
                objart.setFact_id_Factura(tabla1.getInt("fact_numfac"));
                objart.setFKemp_cedula(tabla1.getInt("FKemp_cedula"));
                objart.setFact_fecha(tabla1.getInt("fact_fecha"));
                objart.setFact_cod(tabla1.getString("fact_codigo"));
                objart.setFact_can(tabla1.getInt("fact_cantidad"));

                lista.add(objart);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista;
    }

    public int getFact_fecha() {
        return fact_fecha;
    }

    public void setFact_fecha(int fact_fecha) {
        this.fact_fecha = fact_fecha;
    }

    public int getFKemp_cedula() {
        return FKemp_cedula;
    }

    public void setFKemp_cedula(int FKemp_cedula) {
        this.FKemp_cedula = FKemp_cedula;
    }

    public int getFact_id_Factura() {
        return fact_id_Factura;
    }

    public void setFact_id_Factura(int fact_id_Factura) {
        this.fact_id_Factura = fact_id_Factura;
    }

    public String getFact_cod() {
        return fact_cod;
    }

    public void setFact_cod(String fact_cod) {
        this.fact_cod = fact_cod;
    }

    public int getFact_can() {
        return fact_can;
    }

    public void setFact_can(int fact_can) {
        this.fact_can = fact_can;
    }

}
