/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.model.InvestimentoRendaFixa;
import com.company.persistence.InvestimentoRendaFixaPersistence;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PainelCadastroRendaFixa extends JPanel {
    
    private JComboBox<String> campoOpcao;
    private JTextField campoValor;
    private JTextField campoPrazo;
    private JTextField campoTaxa;
    
    public PainelCadastroRendaFixa(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelOpcao = new JLabel("Tipo de Investimento:");
        labelOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoOpcao = new JComboBox<>();
        campoOpcao.addItem("CDB");
        campoOpcao.addItem("LCI");
        campoOpcao.addItem("LCA");
        campoOpcao.addItem("Tesouro Direto");

        
        JLabel labelValor = new JLabel("Valor Mínimo a Investir:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField("");
        //campoValor.setEditable(false);
        
        JLabel labelPrazo = new JLabel("Prazo Mínimo (meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField("");
        
        JLabel labelTaxa = new JLabel("Taxa Mínima de Rentabilidade(%):");
        labelTaxa.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoTaxa = new JTextField("");
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCadastrar.addActionListener(new cadastroListener());
        
        
        Dimension campoSize = new Dimension(300, 30);
        campoOpcao.setPreferredSize(campoSize);
        campoOpcao.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoTaxa.setPreferredSize(campoSize);
        campoTaxa.setMaximumSize(campoSize);
        btnCadastrar.setPreferredSize(new Dimension(150, 30));
        btnCadastrar.setMaximumSize(new Dimension(150, 30));
        
        
        add(Box.createVerticalStrut(35));
        add(labelOpcao);
        add(Box.createVerticalStrut(3));
        add(campoOpcao);
        
        add(Box.createVerticalStrut(15));
        add(labelValor);
        add(Box.createVerticalStrut(3));
        add(campoValor);
        
        add(Box.createVerticalStrut(15));
        add(labelPrazo);
        add(Box.createVerticalStrut(3));
        add(campoPrazo);
        
        add(Box.createVerticalStrut(15));
        add(labelTaxa);
        add(Box.createVerticalStrut(3));
        add(campoTaxa);
        
        add(Box.createVerticalStrut(15));
        add(btnCadastrar);
    }
    
    private class cadastroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                int prazo = Integer.parseInt(campoPrazo.getText());
                double taxa = Double.parseDouble(campoTaxa.getText());
                String opcaoSelecionada = (String) campoOpcao.getSelectedItem();
               

                InvestimentoRendaFixa investimento = new InvestimentoRendaFixa(opcaoSelecionada,valor,taxa,prazo);
                InvestimentoRendaFixaPersistence ip = new InvestimentoRendaFixaPersistence();
                ip.adicionarSolicitacao(investimento);

                JOptionPane.showMessageDialog(null, "Cadastro de investimento mínimo realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Digite apenas números nos campos de valor, taxa e prazo.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
