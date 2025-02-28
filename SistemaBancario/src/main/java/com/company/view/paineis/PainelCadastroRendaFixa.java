package com.company.view.paineis;

import javax.swing.*;
import java.awt.*;

public class PainelCadastroRendaFixa extends JPanel {
    public PainelCadastroRendaFixa(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel labelOpcao = new JLabel("Opção escolhida:");
        labelOpcao.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoOpcao = new JTextField("CDB");
        campoOpcao.setEditable(false);
        
        JLabel labelValor = new JLabel("Valor a Investir:");
        labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoValor = new JTextField("Valor Investido");
        campoValor.setEditable(false);
        
        JLabel labelPrazo = new JLabel("Prazo(meses):");
        labelPrazo.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoPrazo = new JTextField("Prazo");
        campoPrazo.setEditable(false);
        
        JLabel labelTaxa = new JLabel("Taxa de Rentabilidade(%):");
        labelTaxa.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField campoTaxa = new JTextField("Taxa");
        campoTaxa.setEditable(false);
        
        Dimension campoSize = new Dimension(300, 30);
        campoOpcao.setPreferredSize(campoSize);
        campoOpcao.setMaximumSize(campoSize);
        campoValor.setPreferredSize(campoSize);
        campoValor.setMaximumSize(campoSize);
        campoPrazo.setPreferredSize(campoSize);
        campoPrazo.setMaximumSize(campoSize);
        campoTaxa.setPreferredSize(campoSize);
        campoTaxa.setMaximumSize(campoSize);
        
        add(Box.createVerticalStrut(130));
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
    }
}