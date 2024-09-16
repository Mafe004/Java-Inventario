/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Capa_Presentacion;

import Capa_Negocio.DataEmpleado;
import Capa_Negocio.DataFactura;
import Capa_Negocio.DataProducto;
import Capa_Negocio.DataVentas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jenni
 */
public class Formulario_CRUD_Contabilidad extends javax.swing.JFrame {

    /**
     * Creates new form Formulario_CRUD_Contabilidad
     */
    private double suma = 0.0;

    public Formulario_CRUD_Contabilidad() {
        initComponents();
        this.setSize(1100, 500);
        ListarInventario();
        double totalVenta = 0.0;

        Btn_Agregar.setEnabled(false);
        Btn_Eliminar.setEnabled(false);
        Btn_Modificar.setEnabled(false);

        //Agregar un DocumentListener al campo de cantidad en la tabla "detalle de venta"
        Jtf_Cantidad.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateInventory();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateInventory();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateInventory();
            }
        });

    }

// metodo para actualizar la cantidad en la tabla inventario
    private void updateInventory() {
        DefaultTableModel detallesModel = (DefaultTableModel) Codigo.getModel();
        DefaultTableModel inventarioModel = (DefaultTableModel) jTableInventario.getModel();

        int selectedRow = Codigo.getSelectedRow();

        if (selectedRow != -1) {
            String codigo = (String) detallesModel.getValueAt(selectedRow, 2);
            int cantidadCodigo = Integer.parseInt(Jtf_Cantidad.getText());

            for (int i = 0; i < inventarioModel.getRowCount(); i++) {
                if (codigo.equals(inventarioModel.getValueAt(i, 0))) {
                    int cantidadInventario = (int) inventarioModel.getValueAt(i, 2);

                    //Actualizar la cantida en la tabla inventario
                    inventarioModel.setValueAt(cantidadInventario - cantidadCodigo, i, 2);
                    inventarioModel.fireTableDataChanged();

                    break;

                }

            }

        }

    }

    public void ListarDetalles() {
        DefaultTableModel tabla = new DefaultTableModel();
        DataVentas objart = new DataVentas();
        ArrayList<DataVentas> lista2 = new ArrayList();
        lista2 = objart.ListaVentas();
        tabla.addColumn("Numero de factura");
        tabla.addColumn("Fecha venta");
        tabla.addColumn("codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio");

        tabla.setRowCount(lista2.size());
        int i = 0;

        for (DataVentas x : lista2) {
            tabla.setValueAt(x.getDet_numfac(), i, 0);
            tabla.setValueAt(x.getDet_fecha(), i, 1);
            tabla.setValueAt(x.getDet_cod(), i, 2);
            tabla.setValueAt(x.getDet_nom(), i, 3);
            tabla.setValueAt(x.getDet_can(), i, 4);
            tabla.setValueAt(x.getDet_pre(), i, 5);

            i++;

        }
        this.Codigo.setModel(tabla);
    }

    public void ListarInventario() {
        DefaultTableModel tabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer que todas las celdas no sean editables
            }
        };

        DataProducto objart = new DataProducto();
        ArrayList<DataProducto> lista2 = new ArrayList();
        lista2 = objart.ListaProducto();

        tabla.addColumn("Codigo");
        tabla.addColumn("Nombre");
        tabla.addColumn("Cantidad");
        tabla.addColumn("Precio");
        tabla.setRowCount(lista2.size());

        int i = 0;
        for (DataProducto x : lista2) {
            tabla.setValueAt(x.getProduct_cod(), i, 0);
            tabla.setValueAt(x.getProduct_nom(), i, 1);
            tabla.setValueAt(x.getProduct_can(), i, 2);
            tabla.setValueAt(x.getProduct_pre(), i, 3);
            i++;
        }

        this.jTableInventario.setModel(tabla);

        // Agregar un MouseListener para deshabilitar la edición al hacer clic en la tabla
        jTableInventario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTableInventario.editCellAt(-1, -1); // Deshabilita la edición
            }
        });
    }

    public void LimpiarCajasTexto() {
        this.Jtf_Num_Factura.setText("");
        this.Jtf_Fecha_Venta.setText("");
        this.Jtf_Codigo.setText("");
        this.Jtf_Cantidad.setText("");
    }

    private boolean verificarCodigoExistente(String numDetalle) {

        boolean numFactura = false;
        DefaultTableModel tabla = (DefaultTableModel) Codigo.getModel();// se toma el modelo de la tabla

        for (int i = 0; i < tabla.getRowCount(); i++) {// recorrer las filas
            String CodigoEnTabla = tabla.getValueAt(i, 0).toString();
            if (CodigoEnTabla.equals(numDetalle)) {// compara
                numFactura = true;// el codigo existe en la tabla
                break;
            }
        }
        return numFactura;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        label_CC = new javax.swing.JLabel();
        jtf_CC = new javax.swing.JTextField();
        label_Empleado = new javax.swing.JLabel();
        label_Datos_Factura = new javax.swing.JLabel();
        label_Num_Factura = new javax.swing.JLabel();
        label_Fecha_Venta = new javax.swing.JLabel();
        Jtf_Num_Factura = new javax.swing.JTextField();
        Jtf_Fecha_Venta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Codigo = new javax.swing.JTable();
        label_Codigo = new javax.swing.JLabel();
        label_Cantidad = new javax.swing.JLabel();
        Jtf_Codigo = new javax.swing.JTextField();
        Jtf_Cantidad = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableInventario = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Jtf_Total_Venta = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Btn_Enter = new javax.swing.JButton();
        label_Nombre = new javax.swing.JLabel();
        jtf_Nombre = new javax.swing.JTextField();
        Btn_Agregar = new javax.swing.JButton();
        Btn_Eliminar = new javax.swing.JButton();
        Btn_Modificar = new javax.swing.JButton();
        Btn_Limpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));

        label_CC.setText("CC");

        jtf_CC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtf_CCActionPerformed(evt);
            }
        });

        label_Empleado.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        label_Empleado.setText("Empleado");

        label_Datos_Factura.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        label_Datos_Factura.setText("Detalle Venta");

        label_Num_Factura.setText("N° Factura");

        label_Fecha_Venta.setText("Fecha de venta");

        Jtf_Num_Factura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtf_Num_FacturaActionPerformed(evt);
            }
        });

        Codigo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero de factura", "Fecha Venta", "Codigo", "Nombre", "Cantidad", "Precio", "Precio Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Codigo);

        label_Codigo.setText("Codigo");

        label_Cantidad.setText(" Cantidad");

        Jtf_Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jtf_CodigoActionPerformed(evt);
            }
        });

        jTableInventario = new javax.swing.JTable(){
            public boolean isceldasEditables (int rowIndex,int colIndex){
                return false;

            }
        };
        jTableInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableInventario);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 2, 12)); // NOI18N
        jLabel1.setText("Inventario Bodega (stock)");

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 12)); // NOI18N
        jLabel2.setText("Detalle Venta");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setText("Total Venta del día");

        jButton5.setText("Salir");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 102));
        jLabel4.setFont(new java.awt.Font("Swis721 Lt BT", 1, 24)); // NOI18N
        jLabel4.setText("     Factura Venta ------ASADERO POLLO--------día");

        Btn_Enter.setText("Enter");
        Btn_Enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EnterActionPerformed(evt);
            }
        });

        label_Nombre.setText("Nombre");

        Btn_Agregar.setText("Agregar");
        Btn_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_AgregarActionPerformed(evt);
            }
        });

        Btn_Eliminar.setText("Eliminar");
        Btn_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EliminarActionPerformed(evt);
            }
        });

        Btn_Modificar.setText("Modificar");
        Btn_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ModificarActionPerformed(evt);
            }
        });

        Btn_Limpiar.setText("Limpiar");
        Btn_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label_Cantidad)
                        .addGap(65, 65, 65)
                        .addComponent(Jtf_Cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(label_Num_Factura)
                                    .addGap(60, 60, 60))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(label_Fecha_Venta)
                                    .addGap(37, 37, 37)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label_Codigo)
                                .addGap(77, 77, 77)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Jtf_Fecha_Venta, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(Jtf_Num_Factura)
                            .addComponent(Jtf_Codigo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(label_Nombre)
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(label_CC)
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jtf_CC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Btn_Enter))
                            .addComponent(jtf_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(Btn_Agregar)
                        .addGap(79, 79, 79)
                        .addComponent(Btn_Eliminar)
                        .addGap(58, 58, 58)
                        .addComponent(Btn_Modificar)
                        .addGap(176, 176, 176)
                        .addComponent(Btn_Limpiar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(61, 61, 61)
                                .addComponent(Jtf_Total_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(176, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(label_Datos_Factura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_Empleado)
                .addGap(244, 244, 244))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Datos_Factura)
                    .addComponent(label_Empleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Num_Factura)
                            .addComponent(Jtf_Num_Factura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Fecha_Venta)
                            .addComponent(Jtf_Fecha_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_CC)
                            .addComponent(jtf_CC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Enter))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(label_Nombre)
                            .addComponent(jtf_Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Codigo)
                    .addComponent(Jtf_Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_Cantidad)
                    .addComponent(Jtf_Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Agregar)
                    .addComponent(Btn_Eliminar)
                    .addComponent(Btn_Modificar)
                    .addComponent(Btn_Limpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Jtf_Total_Venta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(72, 72, 72)
                .addComponent(jLabel5)
                .addGap(72, 72, 72))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Jtf_CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtf_CodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Jtf_CodigoActionPerformed

    private void jtf_CCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtf_CCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtf_CCActionPerformed

    private void Btn_EnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EnterActionPerformed
        String cedula = jtf_CC.getText();
        String nombre = "Cédula no encontrada"; // Valor predeterminado si no se encuentra la cédula

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/Contabilidad", "root", "");

            // Crear una instancia de la clase DataEmpleado pasando la conexión
            DataEmpleado dataEmpleado = new DataEmpleado(conn);
            dataEmpleado.setEmp_ced(cedula);

            // Llamar al método para buscar el empleado
            nombre = dataEmpleado.LlamarEmpleado();

            conn.close(); // Cerrar la conexión
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mostrar el nombre en jtf_Nombre
        jtf_Nombre.setText(nombre);
        Btn_Agregar.setEnabled(true);
        Btn_Eliminar.setEnabled(true);
        Btn_Modificar.setEnabled(true);

        // Verificar si el nombre es "Cédula no encontrada" y mostrar un mensaje de error
        if ("Cédula no encontrada".equals(nombre)) {
            javax.swing.JOptionPane.showMessageDialog(null, "La cédula no existe en la base de datos.");
            this.jtf_Nombre.setText("");
            Btn_Agregar.setEnabled(false);
            Btn_Eliminar.setEnabled(false);
            Btn_Modificar.setEnabled(false);
        }

    }//GEN-LAST:event_Btn_EnterActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int r = JOptionPane.showConfirmDialog(null, "Esta Seguro?");
        if (r == 0) {
            System.exit(0);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void Btn_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_AgregarActionPerformed

        // Obtener el número de factura, fecha, código y cantidad desde tus campos de entrada
        String numFactura = Jtf_Num_Factura.getText();
        String FKfact_id_Factura = Jtf_Num_Factura.getText();
        String FKproduct_codigo = Jtf_Codigo.getText();
        String fechaVenta = Jtf_Fecha_Venta.getText();
        String codigo = Jtf_Codigo.getText();
        String cantidad = Jtf_Cantidad.getText();
        String FKemp_cedula = jtf_CC.getText();

        // Buscar el nombre y precio en la tabla de inventario
        String nombre = "";
        double precio = 0.0;
        boolean codigoEncontrado = false;// verifica si el codigo existe en el inventario

        DefaultTableModel inventarioModel = (DefaultTableModel) jTableInventario.getModel();

        boolean codigoExistente = verificarCodigoExistente(numFactura);
        if (codigoExistente) {
            JOptionPane.showMessageDialog(null, "El numero de factura ya  Existe");
        } else {

            // busca el codigo en la tabla inventario
            for (int i = 0; i < inventarioModel.getRowCount(); i++) {
                String codigoInventario = (String) inventarioModel.getValueAt(i, 0);
                if (codigoInventario.equals(codigo)) {
                    nombre = (String) inventarioModel.getValueAt(i, 1);
                    precio = (int) inventarioModel.getValueAt(i, 3);
                    codigoEncontrado = true; // El código se encontró en el inventario
                    // obten la cantidad actual de inventario 
                    //break; // Encontramos el código, salimos del bucle
                    int cantidadActual = (int) inventarioModel.getValueAt(i, 2);

                    try {
                        // Intenta convertir la cantidad ingresada a un entero
                        int cantidadIngresada = Integer.parseInt(cantidad);

                        // Verifica si hay suficiente cantidad en inventario
                        if (cantidadActual >= cantidadIngresada) {
                            // Actualiza la cantidad en la tabla "jTableInventario"
                            int nuevaCantidad = cantidadActual - cantidadIngresada;
                            inventarioModel.setValueAt(nuevaCantidad, i, 2);

                            // Refresca la vista de la tabla "jTableInventario"
                            inventarioModel.fireTableDataChanged();

                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "No hay suficiente cantidad en inventario.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        javax.swing.JOptionPane.showMessageDialog(null, "Por favor, ingresa una cantidad válida.");
                    }

                    break;

                }
            }

            if (!codigoEncontrado) {
                // Si el código no se encontró en el inventario, muestra un mensaje de error
                javax.swing.JOptionPane.showMessageDialog(null, "El código no se encuentra en el inventario. Por favor, ingrese un código válido.");
            } else {
                // Calcular el precio total (precio unitario * cantidad)s
                double precioTotal = precio * Double.parseDouble(cantidad);

                // Agregar el nuevo registro a la tabla "Codigo"
                DefaultTableModel detallesModel = (DefaultTableModel) Codigo.getModel();
                detallesModel.addRow(new Object[]{numFactura, fechaVenta, codigo, nombre, cantidad, precio, precioTotal});
                suma += precioTotal;
            }
            String suma1 = String.valueOf(suma);
            Jtf_Total_Venta.setText(suma1);

            DataFactura objart = new DataFactura();
            objart.setFact_id_Factura(Integer.parseInt(numFactura));
            objart.setFKemp_cedula(Integer.parseInt(FKemp_cedula));
            objart.setFact_fecha(Integer.parseInt(fechaVenta));
            objart.setFact_cod(codigo);
            objart.setFact_can(Integer.parseInt(cantidad));

            DataVentas dataVentas = new DataVentas();
            dataVentas.setDet_numfac(Integer.parseInt(numFactura));
            dataVentas.setFKfact_id_Factura(Integer.parseInt(FKfact_id_Factura));
            dataVentas.setFKproduct_codigo(Integer.parseInt(FKproduct_codigo));
            dataVentas.setDet_fecha(Integer.parseInt(fechaVenta));
            dataVentas.setDet_cod(codigo);
            dataVentas.setDet_nom(nombre);
            dataVentas.setDet_can(Integer.parseInt(cantidad));
            dataVentas.setDet_pre((int) precio);

            String resultadoFactura = objart.GrabarFactura(); // Guardar la factura en la base de datos
            String resultadoVentas = dataVentas.GrabarVentas(); // Guardar los detalles de ventas en la base de datos

        }

    }//GEN-LAST:event_Btn_AgregarActionPerformed

    private void Btn_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EliminarActionPerformed

        int filaSeleccionada = Codigo.getSelectedRow();
        if (filaSeleccionada >= 0) {
            DefaultTableModel detallesModel = (DefaultTableModel) Codigo.getModel();
            detallesModel.removeRow(filaSeleccionada);
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "Seleccione un registro para eliminar.");

        }
    }//GEN-LAST:event_Btn_EliminarActionPerformed

    private void Btn_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ModificarActionPerformed
                                      
    // Obtener el número de factura, fecha, código y cantidad desde tus campos de entrada
    String numFactura = Jtf_Num_Factura.getText();
    String FKfact_id_Factura = Jtf_Num_Factura.getText();
    String FKproduct_codigo = Jtf_Codigo.getText();
    String fechaVenta = Jtf_Fecha_Venta.getText();
    String codigo = Jtf_Codigo.getText();
    String cantidad = Jtf_Cantidad.getText();
    String FKemp_cedula = jtf_CC.getText();

    // Buscar el nombre y precio en la tabla de inventario
    String nombre = "";
    double precio = 0.0;
    boolean codigoEncontrado = false; // verifica si el código existe en el inventario

    DefaultTableModel inventarioModel = (DefaultTableModel) jTableInventario.getModel();

    // Busca el código en la tabla inventario
    for (int i = 0; i < inventarioModel.getRowCount(); i++) {
        String codigoInventario = (String) inventarioModel.getValueAt(i, 0);
        if (codigoInventario.equals(codigo)) {
            nombre = (String) inventarioModel.getValueAt(i, 1);
            precio = (double) inventarioModel.getValueAt(i, 3);
            codigoEncontrado = true; // El código se encontró en el inventario

            // Obten la cantidad actual de inventario
            int cantidadActual = (int) inventarioModel.getValueAt(i, 2);

            try {
                // Intenta convertir la cantidad ingresada a un entero
                int cantidadIngresada = Integer.parseInt(cantidad);

                // Calcula la diferencia de cantidad para modificar el inventario
                int diferenciaCantidad = cantidadIngresada - Integer.parseInt(Jtf_Cantidad.getText());

                // Actualiza la cantidad en la tabla "jTableInventario"
                int nuevaCantidad = cantidadActual - diferenciaCantidad;
                inventarioModel.setValueAt(nuevaCantidad, i, 2);

                // Refresca la vista de la tabla "jTableInventario"
                inventarioModel.fireTableDataChanged();

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingresa una cantidad válida.");
            }

            break;
        }
    }

    if (!codigoEncontrado) {
        // Si el código no se encontró en el inventario, muestra un mensaje de error
        JOptionPane.showMessageDialog(null, "El código no se encuentra en el inventario. Por favor, ingrese un código válido.");
    } else {
        // Verificar si el número de factura existe en la tabla "Codigo"
        DefaultTableModel detallesModel = (DefaultTableModel) Codigo.getModel();
        boolean facturaExistente = false;

        for (int i = 0; i < detallesModel.getRowCount(); i++) {
            String numFacturaDetalles = detallesModel.getValueAt(i, 0).toString();
            if (numFacturaDetalles.equals(numFactura)) {
                facturaExistente = true;
                // Modificar la fila correspondiente al número de factura
                detallesModel.setValueAt(fechaVenta, i, 1);
                detallesModel.setValueAt(codigo, i, 2);
                detallesModel.setValueAt(nombre, i, 3);
                detallesModel.setValueAt(cantidad, i, 4);
                detallesModel.setValueAt(precio, i, 5);
                detallesModel.setValueAt(precio * Double.parseDouble(cantidad), i, 6);
                break;
            }
        }

        if (!facturaExistente) {
            // Si el número de factura no existe en la tabla "Codigo", mostrar un mensaje de error
            JOptionPane.showMessageDialog(null, "El número de factura no existe. Por favor, ingrese un número de factura válido.");
        } else {
            // Calcular el nuevo total de la venta
            double nuevoTotal = 0.0;
            for (int i = 0; i < detallesModel.getRowCount(); i++) {
                nuevoTotal += (double) detallesModel.getValueAt(i, 6);
            }
            Jtf_Total_Venta.setText(String.valueOf(nuevoTotal));

            // Actualiza la base de datos con la modificación
            // (puedes agregar tu lógica aquí)
        }
    }



    }//GEN-LAST:event_Btn_ModificarActionPerformed

    private void Jtf_Num_FacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jtf_Num_FacturaActionPerformed
    }//GEN-LAST:event_Jtf_Num_FacturaActionPerformed

    private void Btn_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LimpiarActionPerformed

        // Eliminar todos los registros de la tabla "detalle" (ventas)
        DataVentas dataVentas = new DataVentas();
        String resultadoVentas = dataVentas.EliminarTodosVentas(); // Agrega un método para eliminar todos los registros en DataVentas

        // Eliminar todos los registros de la tabla "factura"
        DataFactura dataFactura = new DataFactura();
        String resultadoFactura = dataFactura.EliminarTodosFactura(); // Agrega un método para eliminar todos los registros en DataFactura


    }//GEN-LAST:event_Btn_LimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario_CRUD_Contabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario_CRUD_Contabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario_CRUD_Contabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario_CRUD_Contabilidad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario_CRUD_Contabilidad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Agregar;
    private javax.swing.JButton Btn_Eliminar;
    private javax.swing.JButton Btn_Enter;
    private javax.swing.JButton Btn_Limpiar;
    private javax.swing.JButton Btn_Modificar;
    private javax.swing.JTable Codigo;
    private javax.swing.JTextField Jtf_Cantidad;
    private javax.swing.JTextField Jtf_Codigo;
    private javax.swing.JTextField Jtf_Fecha_Venta;
    private javax.swing.JTextField Jtf_Num_Factura;
    private javax.swing.JTextField Jtf_Total_Venta;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableInventario;
    private javax.swing.JTextField jtf_CC;
    private javax.swing.JTextField jtf_Nombre;
    private javax.swing.JLabel label_CC;
    private javax.swing.JLabel label_Cantidad;
    private javax.swing.JLabel label_Codigo;
    private javax.swing.JLabel label_Datos_Factura;
    private javax.swing.JLabel label_Empleado;
    private javax.swing.JLabel label_Fecha_Venta;
    private javax.swing.JLabel label_Nombre;
    private javax.swing.JLabel label_Num_Factura;
    // End of variables declaration//GEN-END:variables
}
