/*
Igor Rocha Matos - 202335041
João Paulo Macedo Fernandes - 202335009
Pedro Muniz Fagundes Netto Lau - 202376029
*/
package com.company.view.paineis;

import com.company.model.Transferencia;
import javax.swing.*;
import java.awt.*;

public class PainelApoio extends JPanel {
    
    private JList<Transferencia> jltransferencias;
    
    public PainelApoio() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            
            JPanel extrato1MI = new JPanel();
            extrato1MI.setBorder(BorderFactory.createTitledBorder("Movimentacoes acima de R$1.000.000,00"));
            extrato1MI.setPreferredSize(new Dimension(400 , 200));
            extrato1MI.setLayout(new BorderLayout());
            
            DefaultListModel<Transferencia> model = new DefaultListModel<>();
            
            jltransferencias = new JList<>(model);
            
            extrato1MI.add(new JScrollPane(jltransferencias), BorderLayout.CENTER);
            
            /* Exemplo de botão
        
            JLabel labelOperacao = new JLabel("Operacao:");
            labelOperacao.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField campoOperacao = new JTextField("Tranferencia");
            campoOperacao.setEditable(false);
        
            JLabel labelValor = new JLabel("Valor:");
            labelValor.setAlignmentX(Component.CENTER_ALIGNMENT);
            JTextField campoValor = new JTextField("R$" + valor);
            campoValor.setEditable(false);
        
            JLabel labelSenha = new JLabel("Senha:");
            labelSenha.setAlignmentX(Component.CENTER_ALIGNMENT);
            JPasswordField campoSenha = new JPasswordField("Valor");
            campoSenha.setEditable(false);
        
            JButton btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setAlignmentX(Component.CENTER_ALIGNMENT);
        

            btnConfirmar.addActionListener(e -> {
                // Função que será executada quando o botão for clicado
                JOptionPane.showMessageDialog(this, "Operacao Confirmada!");
            });

        
            Dimension campoSize = new Dimension(300, 30);
            campoOperacao.setPreferredSize(campoSize);
            campoOperacao.setMaximumSize(campoSize);
            campoValor.setPreferredSize(campoSize);
            campoValor.setMaximumSize(campoSize);
            campoSenha.setPreferredSize(campoSize);
            campoSenha.setMaximumSize(campoSize);
            btnConfirmar.setPreferredSize(new Dimension(150, 30));
            btnConfirmar.setMaximumSize(new Dimension(150, 30));
        
            
            add(Box.createVerticalStrut(60));
            add(labelOperacao);
            add(Box.createVerticalStrut(3));
            add(campoOperacao);
        
            add(Box.createVerticalStrut(15));
            add(labelValor);
            add(Box.createVerticalStrut(3));
            add(campoValor);
        
            add(Box.createVerticalStrut(15));
            add(labelSenha);
            add(Box.createVerticalStrut(3));
            add(campoSenha);
        
            add(Box.createVerticalStrut(15));
            add(btnConfirmar);

            */
       
        
    }
}
