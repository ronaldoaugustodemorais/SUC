package unidadecontrole;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author ronal
 */
public class UIHome extends javax.swing.JFrame
{
    String instructionsFileFolder, dataFileFolder; 
    UnidadeControle und = new UnidadeControle();
             
    public void setTxtOutput(String text)
    {
        txtOutput.setText(text);
    }
    public String getTxtOutput()
    {
        return txtOutput.getText();
    }
    
    public UIHome()
    {               
        initComponents();        
    }
        
        UnidadeControle _Unidade = new UnidadeControle();   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        btnOpenFile = new javax.swing.JButton();
        btnDataFileFolder = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador Unidade Controle");
        setName("UIHome"); // NOI18N
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("UNIDADE DE CONTROLE");

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtOutput.setLineWrap(true);
        txtOutput.setRows(5);
        jScrollPane2.setViewportView(txtOutput);

        btnOpenFile.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnOpenFile.setText("Abrir Codigo Fonte / Iniciar Programa");
        btnOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenFileActionPerformed(evt);
            }
        });

        btnDataFileFolder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnDataFileFolder.setText("Escolher Arquivo para os Dados");
        btnDataFileFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDataFileFolderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 349, Short.MAX_VALUE)
                                .addComponent(btnDataFileFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnOpenFile, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnDataFileFolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
             
    private void btnOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenFileActionPerformed
        // TODO add your handling code here:        
        JFileChooser abrir = new JFileChooser();
        int retorno = abrir.showOpenDialog(null);
            if (retorno==JFileChooser.APPROVE_OPTION)
            {
                instructionsFileFolder = abrir.getSelectedFile().getAbsolutePath();
                    if(instructionsFileFolder == null)
                    {
                        txtOutput.setText("Caminho especificado invalido! Escolha um arquivo com extensao .txt");
                        JOptionPane.showMessageDialog(null, "Caminho especificado invalido! Escolha um arquivo com extensao .txt");
                        instructionsFileFolder = null;
                        dataFileFolder = null;
                    }
                    else if(dataFileFolder == null)
                    {
                        txtOutput.setText("E necessario escolher um arquivo para salvar os dados, antes! Escolha um arquivo com extensao .txt");
                        JOptionPane.showMessageDialog(null, "E necessario escolher um arquivo para salvar os dados, antes! Escolha um arquivo com extensao .txt");
                        instructionsFileFolder = null;
                        dataFileFolder = null;
                    }
                    else
                    {                                                                                                                               
                        txtOutput.setText(txtOutput.getText()+"\n ");               
                        String newStr = instructionsFileFolder.replace("\\", "/");                    
                        UnidadeControle.instructionsFileFolder = newStr;                        
                        txtOutput.setText(getTxtOutput()+ "\nIniciando o programa com destino: "+newStr);
                        JOptionPane.showMessageDialog(null, "File Folder: "+ UnidadeControle.instructionsFileFolder);
                        
                        UnidadeControle.ok = true;
                    }                        
            }                
    }//GEN-LAST:event_btnOpenFileActionPerformed

    private void btnDataFileFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDataFileFolderActionPerformed
        // TODO add your handling code here:
        JFileChooser abrir = new JFileChooser();
        int retorno = abrir.showOpenDialog(null);
            if (retorno==JFileChooser.APPROVE_OPTION)
            {
                dataFileFolder = abrir.getSelectedFile().getAbsolutePath();
                    if(dataFileFolder == null)
                    {
                        JOptionPane.showMessageDialog(null, "Escolha um arquivo com extensao .txt , para armazenar os dados do seu programa.");
                    }
                    else
                    {                           
                        String newStr = dataFileFolder.replace("\\", "/");                    
                        UnidadeControle.dataFileFolderEXT = newStr;                        
                        txtOutput.setText("Arquivo de Dados carregado em: "+newStr);
                    }
                    if(instructionsFileFolder != null)
                    {
                        txtOutput.setText("Iniciando o programa com destino: "+instructionsFileFolder);                                                                            
                        txtOutput.setText(txtOutput.getText()+"\n ");               
                        String newStr = instructionsFileFolder.replace("\\", "/");                    
                        UnidadeControle.instructionsFileFolder = newStr;                        
                        JOptionPane.showMessageDialog(null, "File Folder: "+ UnidadeControle.instructionsFileFolder);
                        
                        UnidadeControle.ok = true;
                    }
            } 
    }//GEN-LAST:event_btnDataFileFolderActionPerformed
  
    public static void main(String args[]) throws IOException {
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
            java.util.logging.Logger.getLogger(UIHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIHome().setVisible(true);                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDataFileFolder;
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
