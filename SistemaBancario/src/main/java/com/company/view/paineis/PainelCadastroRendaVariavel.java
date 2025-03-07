/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.model.InvestimentoRendaVariavel;
import com.company.persistence.InvestimentoRendaVariavelPersistence;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.*;

public class PainelCadastroRendaVariavel extends JPanel{
    
    private JComboBox<String> campoOpcao;
    private JTextField campoValor;
    private JTextField campoPrazo;
    private JTextField campoRisco;
    
    public PainelCadastroRendaVariavel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelOpcao = new JLabel("Opção escolhida:");
        labelOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoOpcao = new JComboBox<>();
        campoOpcao.addItem("Ações");
        campoOpcao.addItem("Fundos de investimento");
        campoOpcao.addItem("Imóveis");
        
        JLabel labelValor = new JLabel("Valor minímo:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoValor = new JTextField("");
        
        JLabel labelPrazo = new JLabel("Taxa de rendimento:");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoPrazo = new JTextField("");
        
        JLabel labelRisco = new JLabel("Risco Estimado:");
        labelRisco.setAlignmentX(Component.CENTER_ALIGNMENT);
        campoRisco = new JTextField("");
        
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
        campoRisco.setPreferredSize(campoSize);
        campoRisco.setMaximumSize(campoSize);
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
        add(labelRisco);
        add(Box.createVerticalStrut(3));
        add(campoRisco);
        
        add(Box.createVerticalStrut(15));
        add(btnCadastrar);
    }
    
    private class cadastroListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double valor = Double.parseDouble(campoValor.getText());
                int taxa = Integer.parseInt(campoPrazo.getText());
                double risco = Double.parseDouble(campoRisco.getText());
                String opcaoSelecionada = (String) campoOpcao.getSelectedItem();
                
                if (valor <= 0 || taxa <= 0 || risco <= 0) {
                throw new IllegalArgumentException("Os valores devem ser inseridos corretamente.");
            }

                InvestimentoRendaVariavel investimento = new InvestimentoRendaVariavel(opcaoSelecionada,valor,taxa,risco);
                InvestimentoRendaVariavelPersistence ip = new InvestimentoRendaVariavelPersistence();
                ip.adicionarSolicitacao(investimento);

                JOptionPane.showMessageDialog(null, "Cadastro de investimento realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                
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