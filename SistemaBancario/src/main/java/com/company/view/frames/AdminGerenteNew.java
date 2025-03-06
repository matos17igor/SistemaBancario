/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.company.view.frames;

import javax.swing.JButton;
/**
 *
 * @author muniz
 */
public class AdminGerenteNew extends javax.swing.JFrame {

    /**
     * Creates new form AdminGerenteNew
     */
    public AdminGerenteNew() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnApoio = new javax.swing.JButton();
        btnCadastroRendaFixa = new javax.swing.JButton();
        btnCadastroRendaVariavel = new javax.swing.JButton();
        btnAvaliacao = new javax.swing.JButton();
        label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnApoio.setText("Apoio em Movimentações Financeiras");
        btnApoio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApoioActionPerformed(evt);
            }
        });

        btnCadastroRendaFixa.setText("Cadastro de Investimentos em Renda Fixa");
        btnCadastroRendaFixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroRendaFixaActionPerformed(evt);
            }
        });

        btnCadastroRendaVariavel.setText("Cadastro de Investimentos em Renda Variável");
        btnCadastroRendaVariavel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastroRendaVariavelActionPerformed(evt);
            }
        });

        btnAvaliacao.setText("Avaliação de Crédito");
        btnAvaliacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvaliacaoActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label.setText("Bem-vindo,");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnApoio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastroRendaFixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCadastroRendaVariavel, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                    .addComponent(btnAvaliacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(label)
                .addGap(49, 49, 49)
                .addComponent(btnApoio, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCadastroRendaFixa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnCadastroRendaVariavel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnAvaliacao, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnApoioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApoioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnApoioActionPerformed

    private void btnCadastroRendaFixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroRendaFixaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastroRendaFixaActionPerformed

    private void btnCadastroRendaVariavelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastroRendaVariavelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastroRendaVariavelActionPerformed

    private void btnAvaliacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvaliacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAvaliacaoActionPerformed

    public JButton getBtnApoio() {
        return btnApoio;
    }

    public JButton getBtnAvaliacao() {
        return btnAvaliacao;
    }

    public JButton getBtnCadastroRendaFixa() {
        return btnCadastroRendaFixa;
    }
    
    public JButton getBtnCadastroRendaVariavel() {
        return btnCadastroRendaVariavel;
    }

    public javax.swing.JLabel getLabel() {
        return label;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApoio;
    private javax.swing.JButton btnAvaliacao;
    private javax.swing.JButton btnCadastroRendaFixa;
    private javax.swing.JButton btnCadastroRendaVariavel;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
