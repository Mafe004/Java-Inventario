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
public class DataVentas {

    //Variables
    private int det_numfac;
    private int FKfact_id_Factura;
    private int FKproduct_codigo;
    private int det_fecha;
    private String det_cod;
    private String det_nom;
    private int det_can;
    private int det_pre;

    public String EliminarVentas() {//Metodo
        Conexion objmod = new Conexion();//Crea un instacion 
        String cad = "DELETE FROM detalle WHERE det_numfac = '" + this.getDet_numfac() + "'"; //Genera una sentencia para eliminar
        return objmod.Ejecutar(cad);
    }

    public String LlamarVentas() {

        Conexion objmod = new Conexion();
        String cad = "select * from detalle WHERE det_numfac = '" + this.getDet_numfac() + "'";
        return objmod.Ejecutar(cad);

    }

    public String GrabarVentas() {
        Conexion objmod = new Conexion();
        String cad = "INSERT INTO detalle (det_numfac, FKfact_id_Factura, FKproduct_codigo, det_fecha, det_codigo, det_nombre, det_cantidad, det_precio) "
                + "VALUES ('" + this.getDet_numfac() + "','" + this.getFKfact_id_Factura() + "','" + this.getFKproduct_codigo() + "','" + this.getDet_fecha() + "','" + this.getDet_cod() + "','" + this.getDet_nom() + "','" + this.getDet_can() + "','" + this.getDet_pre() + "')";
        return objmod.Ejecutar(cad);
    }

    public String EliminarTodosVentas() {
        Conexion objmod = new Conexion();
        String cad = "DELETE FROM detalle"; // Elimina todos los registros de la tabla "detalle"
        return objmod.Ejecutar(cad);
    }

    public String EditarVentas() {
        Conexion objmod = new Conexion();
        String cad = "UPDATE detalle SET det_codigo = '" + this.getDet_cod() + "', det_cantidad = '" + this.getDet_can()+ "', det_nombre = '" + this.getDet_nom() + "' WHERE det_numfac = " + this.getDet_numfac();
        return objmod.Ejecutar(cad);
    }

    public ArrayList< DataVentas> ListaVentas() {
        ArrayList lista3 = new ArrayList();
        try {
            Conexion objmod = new Conexion();
            ResultSet tabla1 = objmod.Listar("select * from detalle");
            DataVentas objart;
            while (tabla1.next()) {
                objart = new DataVentas();
                objart.setDet_numfac(tabla1.getInt("det_numfac"));
                objart.setFKfact_id_Factura(tabla1.getInt("FKfact_id_Factura"));
                objart.setDet_fecha(tabla1.getInt("det_fecha"));
                objart.setDet_cod(tabla1.getString("det_codigo"));
                objart.setDet_nom(tabla1.getString("det_nombre"));
                objart.setDet_can(tabla1.getInt("det_cantidad"));
                objart.setDet_pre(tabla1.getInt("det_precio"));

                lista3.add(objart);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista3;
    }

    public int getDet_fecha() {
        return det_fecha;
    }

    public void setDet_fecha(int FKfact_id_Factura) {
        this.det_fecha = det_fecha;
    }

    public int getFKproduct_codigo() {
        return FKproduct_codigo;
    }

    public void setFKproduct_codigo(int FKproduct_codigo) {
        this.FKproduct_codigo = FKproduct_codigo;
    }

    public int getFKfact_id_Factura() {
        return FKfact_id_Factura;
    }

    public void setFKfact_id_Factura(int FKfact_id_Factura) {
        this.FKfact_id_Factura = FKfact_id_Factura;
    }

    public int getDet_numfac() {
        return det_numfac;
    }

    public void setDet_numfac(int det_numfac) {
        this.det_numfac = det_numfac;
    }

    public String getDet_cod() {
        return det_cod;
    }

    public void setDet_cod(String det_cod) {
        this.det_cod = det_cod;
    }

    public String getDet_nom() {
        return det_nom;
    }

    public void setDet_nom(String det_nom) {
        this.det_nom = det_nom;
    }

    public int getDet_can() {
        return det_can;
    }

    public void setDet_can(int det_can) {
        this.det_can = det_can;
    }

    public int getDet_pre() {
        return det_pre;
    }

    public void setDet_pre(int det_pre) {
        this.det_pre = det_pre;
    }

}
