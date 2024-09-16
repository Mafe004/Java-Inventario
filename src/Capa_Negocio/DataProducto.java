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
public class DataProducto {

    //Variables
    private String product_cod;
    private String product_nom;
    private int product_can;
    private int product_pre;

    public String EliminarProducto() {//Metodo
        Conexion objmod = new Conexion();//Crea un instacion 
        String cad = "DELETE FROM producto WHERE Product_CODIGO = '" + this.getProduct_cod() + "'"; //Genera una sentencia para eliminar
        return objmod.Ejecutar(cad);
    }

    public String LlamarProducto() {

        Conexion objmod = new Conexion();
        String cad = "select * from producto WHERE product_CODIGO = '" + this.getProduct_cod() + "'";
        return objmod.Ejecutar(cad);

    }

    public String GrabarProducto() {
        Conexion objmod = new Conexion();
        String cad = "INSERT INTO product VALUES ('" + this.getProduct_cod() + "','" + this.getProduct_nom() + "','" + this.getProduct_can() + "','" + this.getProduct_pre() + "')";
        return objmod.Ejecutar(cad);
    }

    public String EditarProducto() {
        Conexion objmod = new Conexion();
        String cad = "update Inventario set inv_nombre='" + this.getProduct_nom()
                + "',inv_cantidad='" + this.getProduct_can() + "', inv_precio='" + this.getProduct_pre()
                + "' where inv_codigo='" + this.getProduct_cod() + "'";
        return objmod.Ejecutar(cad);
    }

    public ArrayList< DataProducto> ListaProducto() {
        ArrayList lista2 = new ArrayList();
        try {
            Conexion objmod = new Conexion();
            ResultSet tabla = objmod.Listar("select * from Producto");
            DataProducto objart;
            while (tabla.next()) {
                objart = new DataProducto();
                objart.setProduct_cod(tabla.getString("product_codigo"));
                objart.setProduct_nom(tabla.getString("product_nombre"));
                objart.setProduct_can(tabla.getInt("product_cantidad"));
                objart.setProduct_pre(tabla.getInt("product_precio"));

                lista2.add(objart);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return lista2;
        
    }

    public String getProduct_cod() {
        return product_cod;
    }

    public void setProduct_cod(String producto_cod) {
        this.product_cod = producto_cod;
    }

    public String getProduct_nom() {
        return product_nom;
    }

    public void setProduct_nom(String product_nom) {
        this.product_nom = product_nom;
    }

    public int getProduct_can() {
        return product_can;
    }

    public void setProduct_can(int product_can) {
        this.product_can = product_can;
    }

    public int getProduct_pre() {
        return product_pre;
    }

    public void setProduct_pre(int product_pre) {
        this.product_pre = product_pre;
    }

}
