/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author bournot
 */
public class main extends javax.swing.JFrame {

    Koneksi_db conn = new Koneksi_db();
    String tgl;
    Music music = new Music("./assets/ringing.mp3");
    public main() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        this.setSize(700, 550);
        setLocationRelativeTo(this);
        tampil();
        lbl_Opened.setText("Running at : "+getTglNow("dd-MMM-yyyy HH:mm"));
        realTime();        
    }
    
    private void realTime(){
        ActionListener taskPerformer = (ActionEvent e) -> {
            tampil();
        };
        new Timer(10000, taskPerformer).start();
    }
    
    private String getTglNow(String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        GregorianCalendar gcal = new GregorianCalendar();
        return sdf.format(gcal.getTime());
    }
    
    private  void tampil(){
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("#");
            model.addColumn("No Resep");            
            model.addColumn("Tgl Peresepan");
            model.addColumn("Jam ");
            model.addColumn("No. RM");
            model.addColumn("Nama Pasien");
            model.addColumn("Poli/Unit");            
            tbl_Data.setModel(model);
            
            String Query = "select resep_obat.no_resep,resep_obat.tgl_peresepan,resep_obat.jam_peresepan,"+
                    " resep_obat.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,resep_obat.kd_dokter,dokter.nm_dokter, "+
                    " if(resep_obat.jam_peresepan=resep_obat.jam,'Belum Terlayani','Sudah Terlayani') as status,poliklinik.nm_poli,  "+
                    " reg_periksa.kd_poli from resep_obat inner join reg_periksa inner join pasien inner join dokter inner join poliklinik "+
                    " on resep_obat.no_rawat=reg_periksa.no_rawat and reg_periksa.kd_poli=poliklinik.kd_poli "+
                    " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and resep_obat.kd_dokter=dokter.kd_dokter where resep_obat.status='ralan' and resep_obat.tgl_perawatan LIKE '%"+getTglNow("yyyy-MM")+"%'"+
                    " order by resep_obat.tgl_perawatan desc,resep_obat.jam desc";
            conn.res = conn.stat.executeQuery(Query);
            int no=0;
            while (conn.res.next()) {     
                if (conn.res.getString("status").equals("Belum Terlayani") && (conn.res.getString("nm_poli").equals("Poli Transit") || conn.res.getString("nm_poli").equals("-") || conn.res.getString("nm_poli").equals("UGD") )) {
                    model.addRow(new Object[]{
                        ++no,
                        conn.res.getString("no_resep"),
                        conn.res.getString("tgl_peresepan"),
                        conn.res.getString("jam_peresepan"),
                        conn.res.getString("no_rkm_medis"),
                        conn.res.getString("nm_pasien"),
                        conn.res.getString("nm_poli"),});
                    tbl_Data.setModel(model);
                    
                }                
            }
            if(no > 0){
                music.start();
            }else{
                music.stop();
            }
            tbl_Data.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_Data.getColumnModel().getColumn(3).setPreferredWidth(30);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Mohon tutup dan buka lagi aplikasi", "Error Tampil",JOptionPane.ERROR_MESSAGE);
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

        panelGlass1 = new usu.widget.glass.PanelGlass();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelGlass2 = new usu.widget.glass.PanelGlass();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        panelGlass3 = new usu.widget.glass.PanelGlass();
        panelGlass4 = new usu.widget.glass.PanelGlass();
        label6 = new usu.widget.Label();
        label7 = new usu.widget.Label();
        label8 = new usu.widget.Label();
        label9 = new usu.widget.Label();
        lbl_Opened = new usu.widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alarm Permintaan Obat");
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        panelGlass1.setAlpha(0.3F);
        panelGlass1.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/program/background.jpg"))); // NOI18N
        panelGlass1.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        panelGlass1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Alarm Permintaan Obat");
        panelGlass1.add(jLabel1);
        jLabel1.setBounds(220, 50, 280, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SUB-SISTEM SIMRS KHANZA");
        panelGlass1.add(jLabel2);
        jLabel2.setBounds(220, 20, 280, 30);

        tbl_Data.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_Data);

        javax.swing.GroupLayout panelGlass2Layout = new javax.swing.GroupLayout(panelGlass2);
        panelGlass2.setLayout(panelGlass2Layout);
        panelGlass2Layout.setHorizontalGroup(
            panelGlass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGlass2Layout.setVerticalGroup(
            panelGlass2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Data Permintaan", panelGlass2);

        panelGlass3.setLayout(null);

        panelGlass4.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/program/Logo IT Sadewa.png"))); // NOI18N
        panelGlass4.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);

        javax.swing.GroupLayout panelGlass4Layout = new javax.swing.GroupLayout(panelGlass4);
        panelGlass4.setLayout(panelGlass4Layout);
        panelGlass4Layout.setHorizontalGroup(
            panelGlass4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );
        panelGlass4Layout.setVerticalGroup(
            panelGlass4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 136, Short.MAX_VALUE)
        );

        panelGlass3.add(panelGlass4);
        panelGlass4.setBounds(310, 10, 136, 136);

        label6.setForeground(new java.awt.Color(255, 255, 255));
        label6.setText("Created By : TIM IT RSKIA SADEWA @2020 R.E.F.R");
        panelGlass3.add(label6);
        label6.setBounds(220, 160, 320, 16);

        label7.setForeground(new java.awt.Color(255, 255, 255));
        label7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label7.setText("alarm berhenti jika permintaan divalidasi lewat SIMRS");
        panelGlass3.add(label7);
        label7.setBounds(210, 230, 320, 16);

        label8.setForeground(new java.awt.Color(255, 255, 255));
        label8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label8.setText("Aplikasi untuk alarm jika ada permintaan obat poli transit-igd-ugd,");
        panelGlass3.add(label8);
        label8.setBounds(180, 200, 380, 30);

        label9.setForeground(new java.awt.Color(255, 255, 255));
        label9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label9.setText("Versi : APO.05.11.2020");
        panelGlass3.add(label9);
        label9.setBounds(210, 180, 320, 16);

        jTabbedPane1.addTab("Tentang App", panelGlass3);

        panelGlass1.add(jTabbedPane1);
        jTabbedPane1.setBounds(50, 90, 580, 320);

        lbl_Opened.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Opened.setText("Running at : 04-Nov-2020 15:00");
        panelGlass1.add(lbl_Opened);
        lbl_Opened.setBounds(420, 420, 210, 16);

        getContentPane().add(panelGlass1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private usu.widget.Label label6;
    private usu.widget.Label label7;
    private usu.widget.Label label8;
    private usu.widget.Label label9;
    private usu.widget.Label lbl_Opened;
    private usu.widget.glass.PanelGlass panelGlass1;
    private usu.widget.glass.PanelGlass panelGlass2;
    private usu.widget.glass.PanelGlass panelGlass3;
    private usu.widget.glass.PanelGlass panelGlass4;
    private javax.swing.JTable tbl_Data;
    // End of variables declaration//GEN-END:variables
}