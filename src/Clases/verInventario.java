/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Conexiones.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class verInventario extends javax.swing.JInternalFrame {

    DefaultTableModel model = new DefaultTableModel(); //crea  un modelo para la tabla
    Statement st = null;
    ResultSet rs = null;
    Connection con2 = null;//varianles para la coneccions
    public verInventario() {
        initComponents();
        vaciarTabla();
        verDatos();
    }
    
    public void vaciarTabla(){//crea un nuevo modelo y lo pone los titilos del string a la tabla
     String titulos[] = {"ID", "ID_CATEGORIA", "NOMBRE", "COSTO", "PRECIO", "CANTIDAD"};
        model = new DefaultTableModel(null,titulos);
        TabalaInventario.setModel(model);
    }
    
    public void verDatos(){
        //va a hacer una consulta a la base de datos y regresara todos los elementos que se encuentren en la tabla de inventario
        try {
            Connection con1 = null;
            DefaultTableModel miModelo = (DefaultTableModel) TabalaInventario.getModel();
            Conexion conect1 = new Conexion();
            con1 = conect1.getConnection();
            String dts[] = new String[6];//al ser 6 campos se necesita un vector de 6 posiciones para guardar la infirmacion
            //realiza la consulta
            String sql = "select * from inventario";
            Statement st = con1.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){//recore cada fila guardadndo en el ventor los elementos que se encuentra
                  dts[0] = rs.getString("id_inventario");
                  dts[1] = rs.getString("id_categoria");
                  dts[2] = rs.getString("nombre");
                  dts[3] = rs.getString("costo");
                  dts[4] = rs.getString("precio_venta");
                  dts[5] = rs.getString("cantidad");
                  miModelo.addRow(dts);//ya con todos los elementos guardados agrega la fila a la tabla y realiza otra vez el ciclo
                  
            }
            TabalaInventario.setModel(miModelo);//le pone el modelo con toda la informacion a la tabla del form(para que esto funcione la tabla tiene que estar en public static)
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "NO SE PUEDEN VISUALIZAR LOS DATOS DE LA TABLA", "Error", JOptionPane.ERROR_MESSAGE);
        }
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        panel = new javax.swing.JScrollPane();
        TabalaInventario = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 12)); // NOI18N
        jLabel1.setText("BUSCAR UN PRODUCTO POR NOMBRE:");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
        });

        TabalaInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "ID_CATEGORIA", "NOMBRE", "COSTO", "PRECIO", "CANTIDAD"
            }
        ));
        panel.setViewportView(TabalaInventario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
      //cada vez que el usuario presione un tecla en el textfile la tabla se va a modificar buscando los nombre que se parescan en la base de datos
        String[] titulos = {"ID", "ID_CATEGORIA", "NOMBRE", "COSTO", "PRECIO", "CANTIDAD"};
        String[] registros = new String[100];//este vector solo permite mostrar 100 elementos en la pantalla
       //realiza la consulta de buscar en inventario donde el nombre se paresca a lo que se ingreso
        String sql = "SELECT * FROM inventario WHERE nombre LIKE '%" + txtbuscar.getText() + "%'";
        model = new DefaultTableModel(null, titulos);
        Conexion conect1 = new Conexion();
        con2 = conect1.getConnection();
        try {
            //va a agregar a la tabla los elementos que se parescan si no encuentra ninguno saltra un mensaje de error
            st = (Statement) con2.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                 registros[0] = rs.getString("id_inventario");
                 registros[1]= rs.getString("id_categoria");
                 registros[2] = rs.getString("nombre");
                 registros[3]= rs.getString("costo");
                 registros[4] = rs.getString("precio_venta");
                 registros[5] = rs.getString("cantidad");
               model.addRow(registros);
            
            }
            TabalaInventario.setModel(model);

        } catch (SQLException ex) {
            System.out.println("ERROR AL BUSCAR LOS DATOS : " + ex.getMessage());
        }
        
    }//GEN-LAST:event_txtbuscarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable TabalaInventario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane panel;
    private javax.swing.JTextField txtbuscar;
    // End of variables declaration//GEN-END:variables
}