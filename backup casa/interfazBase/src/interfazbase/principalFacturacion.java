package interfazbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *   Ventana principal de facturacion
 *   
 *   @version 0.1  21/09/15
 *   @author SENA
 */

public class principalFacturacion extends javax.swing.JFrame {
    
  
    public principalFacturacion() {
        initComponents();
     
    }
    
   /* hacer los metodos para agregar ls datos del cliente a tbl datos_fact y 
    * del producto a tbl fact_prod desde los jtextfield 
    */
    
    
    void agreagarAfactura(){
         DefaultTableModel modeloFactura = new DefaultTableModel();
         
                modeloFactura.addColumn("Cod Producto");
                modeloFactura.addColumn("Nombre Producto");
                modeloFactura.addColumn("Marca Producto");
                modeloFactura.addColumn("Precio Producto");
                modeloFactura.addColumn("Descripcion Producto");
                modeloFactura.addColumn("Cantidad Producto");

                table_factura.setModel(modeloFactura); 

                String sql="select * from tblfactura";

                Object [] datos = new Object[6];
               
                try {
                     Statement st=cn.createStatement();
                     ResultSet re = st.executeQuery(sql);

                     while(re.next()){
                         datos[0]  = re.getInt(7);
                         datos[1]  = re.getString(8);
                         datos[2]  = re.getString(9);
                         datos[3]  = re.getInt(10);
                         datos[4]  = re.getString(11);
                         datos[5]  = re.getInt(12);

                         modeloFactura.addRow(datos);
                     }
                     
                         table_factura.setModel(modeloFactura);
                         
                     } catch (SQLException ex) {
                        Logger.getLogger(PrincipalClientes.class.getName()).log
                                (Level.SEVERE, null, ex);
                     }
            }
     
    void  generarFactura(){
          
          try{
            
                PreparedStatement pst = cn.prepareStatement("INSERT INTO "
                        + "tblfactura (numero_factura, ced_cli_fact, "
                        + "nom_cli_fact, ape_cli_fact, tl_cli_fact,"
                        + " dir_cli_fact, cod_prod_fact, nom_prod_fact,"
                        + " mar_prod_fact, precio_prod_fact, desc_prod_fact,"
                        + " cant_prod_fact, fecha_fact, total_fact)"
                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

                int num_fact = Integer.parseInt(txt_num_fact.getText());
                int ced_cli= Integer.parseInt(txt_ced_cli.getText());
                String nom_cli= txt_nom_cli.getText();
                String ape_cli= txt_ape_cli.getText();
                int tel_cli= Integer.parseInt(txt_tel_cli.getText());
                String dir_cli= txt_dir_cli.getText();
                int cod_prod= Integer.parseInt(txt_cod_prod.getText());
                String nom_prod= txt_nom_prod.getText();
                String marca_prod= txt_marca_prod.getText();
                int prec_prod= Integer.parseInt(txt_prec_prod.getText());
                String desc_prod= txt_desc_prod.getText();
                int cantidad= Integer.parseInt(txt_cant.getText());
                String fecha= jDateChooser1.getDateFormatString();
                int total = Integer.parseInt(txt_total.getText());

                pst.setInt(1, num_fact);
                pst.setInt(2, ced_cli);
                pst.setString(3,nom_cli);
                pst.setString(4,ape_cli);      //cliente
                pst.setInt(5,tel_cli);
                pst.setString(6,dir_cli);
                pst.setString(7,dir_cli);
                pst.setInt(7, cod_prod);
                pst.setString(8,nom_prod);
                pst.setString(9,marca_prod);       //producto
                pst.setInt(10,prec_prod);
                pst.setString(11,desc_prod);
                pst.setInt(12, cantidad);
                pst.setString(13, fecha);
                pst.setInt(14, total);
                pst.executeUpdate();

                   
        }
        catch(SQLException |NumberFormatException e){
            System.out.print(e.getMessage());
                       
        }
       
     }
 
     void calcularTotal(){
  
        int total;
    
        String sql = "select precio_prod_fact from tblfactura";
     
    
        Object valores [] = new Object [6];

          try {
                   Statement st=cn.createStatement();
                   ResultSet re = st.executeQuery(sql);

                    while(re.next()){

                        valores[0]  = re.getInt(1);
                        valores[1]  = re.getInt(2);
                        valores[2]  = re.getInt(3);
                        valores[3]  = re.getInt(4);
                        valores[4]  = re.getInt(5);

                        txt_ced_cli.setText(String.valueOf(valores[0]));  
                        txt_nom_cli.setText(String.valueOf(valores[1]));
                        txt_ape_cli.setText(String.valueOf(valores[2]));
                        txt_tel_cli.setText(String.valueOf(valores[3]));
                        txt_dir_cli.setText(String.valueOf(valores[4]));

                     }

                } 
          catch (SQLException ex) {
                    Logger.getLogger(PrincipalClientes.class.getName()).
                            log(Level.SEVERE, null, ex);

                    System.out.print(ex.getMessage());
                }
            }

    
    
      void llamarCliente(){

                int  valor = Integer.parseInt(txt_ced_cli_buscar.getText());
                String sql="";
                  sql="select * from tblclientes where cedula_cliente="+valor+"";
                //vector tipo object to handle int also 
                Object [] datos = new Object[5];
                         //query
                try {
                   Statement st=cn.createStatement();
                    ResultSet re = st.executeQuery(sql);

                    while(re.next()){
                        datos[0]  = re.getInt(1);
                        datos[1]  = re.getString(2);
                        datos[2]  = re.getString(3);
                        datos[3]  = re.getInt(4);
                        datos[4]  = re.getString(5);

                      txt_ced_cli.setText(String.valueOf(datos[0]));  
                      txt_nom_cli.setText(String.valueOf(datos[1]));
                      txt_ape_cli.setText(String.valueOf(datos[2]));
                      txt_tel_cli.setText(String.valueOf(datos[3]));
                      txt_dir_cli.setText(String.valueOf(datos[4]));
                      }

                } catch (SQLException ex) {
                    Logger.getLogger(PrincipalClientes.class.getName()).
                            log(Level.SEVERE, null, ex);

                    System.out.print(ex.getMessage());

        }
        }
     
     void llamarProducto(){
         
         int  valor = Integer.parseInt(txt_cod_prod_buscar.getText());
         
         String sql="";
        
         sql="select * from tblproductos where id_producto="+valor+"";
        
         Object [] datos = new Object[5];
                 
         try {
             
            Statement st=cn.createStatement();
            ResultSet re = st.executeQuery(sql);
            
            while(re.next()){
                datos[0]  = re.getInt(1);
                datos[1]  = re.getString(2);
                datos[2]  = re.getString(3);
                datos[3]  = re.getInt(4);
                datos[4]  = re.getString(6);

                txt_cod_prod.setText(String.valueOf(datos[0]));  
                txt_nom_prod.setText(String.valueOf(datos[1]));
                txt_marca_prod.setText(String.valueOf(datos[2]));
                txt_prec_prod.setText(String.valueOf(datos[3]));
                txt_desc_prod.setText(String.valueOf(datos[4]));

      
            }
                    
            } catch (SQLException ex) {
                Logger.getLogger(PrincipalClientes.class.getName()).
                        log(Level.SEVERE, null, ex);
                System.out.print(ex.getMessage());
        }
     };
     
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_ced_cli_buscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_dir_cli = new javax.swing.JTextField();
        txt_nom_cli = new javax.swing.JTextField();
        txt_ape_cli = new javax.swing.JTextField();
        txt_tel_cli = new javax.swing.JTextField();
        txt_ced_cli = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        txt_cod_prod_buscar = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_marca_prod = new javax.swing.JTextField();
        txt_prec_prod = new javax.swing.JTextField();
        txt_desc_prod = new javax.swing.JTextField();
        txt_nom_prod = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_cod_prod = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txt_cant = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt_total = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_factura = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        txt_num_fact = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(txt_ced_cli_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 200, 30));

        jLabel1.setText("Facturaciuon");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -30, -1, 26));

        jLabel2.setText("Cantidad: ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 340, 90, 30));

        jButton1.setText("Comprobar cliente");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 300, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setText("Direccion cliente: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 30));

        jLabel5.setText("Nombre cliente: ");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 30));

        jLabel6.setText("Apellido cliente:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 30));

        jLabel7.setText("Telefono cliente:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, 30));

        txt_dir_cli.setFocusable(false);
        txt_dir_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dir_cliActionPerformed(evt);
            }
        });
        jPanel1.add(txt_dir_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 190, 30));

        txt_nom_cli.setFocusable(false);
        jPanel1.add(txt_nom_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 190, 30));

        txt_ape_cli.setFocusable(false);
        jPanel1.add(txt_ape_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 190, 30));

        txt_tel_cli.setFocusable(false);
        jPanel1.add(txt_tel_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 190, 30));

        txt_ced_cli.setFocusable(false);
        jPanel1.add(txt_ced_cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 190, 30));

        jLabel4.setText("Cedula cliente: ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 95, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, 210));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, -10, 40, 390));

        jLabel8.setText("Cod Producto");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 95, 30));
        getContentPane().add(txt_cod_prod_buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, 260, 30));

        jButton2.setText("Comprobar producto");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 350, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setText("Marca Producto: ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 30));

        jLabel11.setText("Valor  unitario:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 140, 30));

        jLabel12.setText("Descripcion Producto:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 140, 30));

        txt_marca_prod.setFocusable(false);
        jPanel2.add(txt_marca_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 210, 30));

        txt_prec_prod.setFocusable(false);
        jPanel2.add(txt_prec_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 210, 30));

        txt_desc_prod.setFocusable(false);
        jPanel2.add(txt_desc_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 210, 30));

        txt_nom_prod.setFocusable(false);
        jPanel2.add(txt_nom_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 210, 30));

        jLabel13.setText("Nombre Producto: ");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 95, 30));

        jLabel14.setText("Cod Producto: ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 95, 30));

        txt_cod_prod.setFocusable(false);
        jPanel2.add(txt_cod_prod, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 210, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 350, 210));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 40, 390));

        jLabel9.setText("Cedula Cliente.");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 95, 30));
        getContentPane().add(txt_cant, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 210, 30));

        jLabel15.setText("Fecha: ");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 50, 30));

        jButton3.setText("Agregar a la factura");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 240, 40));
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 130, 260, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Valor total");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 90, 90, 40));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, -1, -1));
        getContentPane().add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 140, -1, -1));
        getContentPane().add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 80, 10));
        getContentPane().add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 80, 10));

        jButton4.setText("Calcular total");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 170, 260, 50));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 1010, 30));

        jButton5.setText("Generar factura");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 250, 130));

        table_factura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(table_factura);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 990, 300));

        jButton6.setText("Regresar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 770, 160, 30));
        getContentPane().add(txt_num_fact, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 240, 230, -1));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 50, 130, -1));

        jButton7.setText("Agregar a la factura");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 350, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        llamarCliente();                
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_dir_cliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dir_cliActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dir_cliActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        llamarProducto();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         
       MenuPrincipal  ventHome = new MenuPrincipal();
       ventHome.setVisible(true);
       dispose();
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        generarFactura();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        agreagarAfactura();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        calcularTotal();
  
    }//GEN-LAST:event_jButton4ActionPerformed

 
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
            java.util.logging.Logger.getLogger(principalFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(principalFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(principalFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(principalFacturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principalFacturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable table_factura;
    private javax.swing.JTextField txt_ape_cli;
    private javax.swing.JTextField txt_cant;
    private javax.swing.JTextField txt_ced_cli;
    private javax.swing.JTextField txt_ced_cli_buscar;
    private javax.swing.JTextField txt_cod_prod;
    private javax.swing.JTextField txt_cod_prod_buscar;
    private javax.swing.JTextField txt_desc_prod;
    private javax.swing.JTextField txt_dir_cli;
    private javax.swing.JTextField txt_marca_prod;
    private javax.swing.JTextField txt_nom_cli;
    private javax.swing.JTextField txt_nom_prod;
    private javax.swing.JTextField txt_num_fact;
    private javax.swing.JTextField txt_prec_prod;
    private javax.swing.JTextField txt_tel_cli;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables


        conexion cc= new conexion();
        Connection cn= cc.conexion();

}
