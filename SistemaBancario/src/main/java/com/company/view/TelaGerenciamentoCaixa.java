package com.company.view;

import com.company.controller.AdicionarCaixa;
import com.company.controller.EditarCaixa;
import com.company.controller.GerenciarCaixas;
import com.company.controller.RemoverCaixa;
import com.company.controller.SelecionarCaixa;
import com.company.exception.CPFException;
import com.company.exception.EmailException;
import com.company.exception.NameException;
import com.company.model.Caixa;
import com.company.model.Email;
import com.company.model.Endereco;
import com.company.persistence.CaixaPersistence;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaGerenciamentoCaixa {

    private JFrame tela;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int V_GAP = 10;
    private final int H_GAP = 5;

    private javax.swing.JTextField tfBairro;
    private javax.swing.JTextField tfCEP;
    private javax.swing.JTextField tfCPF;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfName;
    private javax.swing.JTextField tfNasc;
    private javax.swing.JTextField tfNum;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfRua;
    private javax.swing.JPasswordField tfSenha;
    private JList<Caixa> jlCaixas;

    public void desenha() {

        tela = new JFrame("Gerenciamento de caixas");
        tela.addWindowListener(new GerenciarCaixas(this));
        tela.setSize(WIDTH, HEIGHT);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        tela.setLayout(new BorderLayout());
        tela.setResizable(false);
        tela.setLocationRelativeTo(null);

        desenhaLista();
        desenhaFormulario();

        tela.pack();
    }

    private void desenhaLista() {

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Caixas"));
        painel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Caixa> model = new DefaultListModel<>();

        jlCaixas = new JList<>(model);
        jlCaixas.addListSelectionListener(new SelecionarCaixa(this));

        painel.add(new JScrollPane(jlCaixas), BorderLayout.CENTER);

        tela.getContentPane().add(painel, BorderLayout.WEST);
    }

    private void desenhaFormulario() {

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Formulário"));

        JPanel formulario = new JPanel();
        JPanel painelLabel = new JPanel();
        painelLabel.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        painelLabel.add(new JLabel("Nome"));
        painelLabel.add(new JLabel("Telefone"));
        painelLabel.add(new JLabel("CPF"));
        painelLabel.add(new JLabel("Data de nascimento"));
        painelLabel.add(new JLabel("Rua"));
        painelLabel.add(new JLabel("Bairro"));
        painelLabel.add(new JLabel("Número"));
        painelLabel.add(new JLabel("CEP"));
        painelLabel.add(new JLabel("Email"));
        painelLabel.add(new JLabel("Senha"));

        JPanel painelField = new JPanel();
        painelField.setLayout(new GridLayout(0, 1, H_GAP, V_GAP));
        tfName = new JTextField(20);
        tfPhone = new JTextField(20);
        tfCPF = new JTextField(20);
        tfNasc = new JTextField(20);
        tfRua = new JTextField(20);
        tfBairro = new JTextField(20);
        tfNum = new JTextField(20);
        tfCEP = new JTextField(20);
        tfEmail = new JTextField(20);
        tfSenha = new JPasswordField(20);

        painelField.add(tfName);
        painelField.add(tfPhone);
        painelField.add(tfCPF);
        painelField.add(tfNasc);
        painelField.add(tfRua);
        painelField.add(tfBairro);
        painelField.add(tfNum);
        painelField.add(tfCEP);
        painelField.add(tfEmail);
        painelField.add(tfSenha);

        formulario.add(painelLabel);
        formulario.add(painelField);

        painel.setLayout(new BorderLayout());
        painel.add(formulario, BorderLayout.CENTER);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionarCaixa(this));

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new RemoverCaixa(this));

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new EditarCaixa(this));
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> tela.setVisible(false));

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnRemover);
        botoes.add(btnEditar);
        botoes.add(btnVoltar);

        painel.add(botoes, BorderLayout.SOUTH);

        tela.getContentPane().add(painel, BorderLayout.CENTER);

    }

    public void carregaCaixas(List<Caixa> caixas) {

        DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixas.getModel();

        for (Caixa c : caixas) {
            model.addElement(c);
        }

    }

    public List<Caixa> listaCaixas() {
        DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixas.getModel();
        List<Caixa> caixas = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            caixas.add(model.get(i));
        }

        return caixas;
    }

    public void addCaixa() {

        DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixas.getModel();
        try {
            model.addElement(new Caixa(getNextId(), tfName.getText(), new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()), tfNasc.getText(), tfCPF.getText(), new Email(tfEmail.getText()), new String(tfSenha.getPassword()), tfPhone.getText()));
        } catch (EmailException | NameException | CPFException e) {
            JOptionPane.showMessageDialog(tela, "Erro: " + e.getMessage());
        }

    }

    public int getNextId() {
        CaixaPersistence caixaP = new CaixaPersistence();
        List<Caixa> caixas = caixaP.findAll(); // Carrega os Caixas salvos

        if (caixas.isEmpty()) {
            return 1; // Se não houver Caixas, começa do 1
        }

        return caixas.stream()
                .mapToInt(Caixa::getId)
                .max()
                .orElse(0) + 1; // Pega o maior ID e soma 1
    }

    public void removerCaixa() {

        int selectedIndex = jlCaixas.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Caixa> model
                    = (DefaultListModel<Caixa>) jlCaixas.getModel();
            model.remove(selectedIndex);
        }
    }

    public void atualizarFormulario() {
        int selectedIndex = jlCaixas.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixas.getModel();
            Caixa caixa = model.get(selectedIndex);
            tfName.setText(caixa.getName());
            tfCPF.setText(caixa.getCpf());
            tfEmail.setText(caixa.getEmail());
            tfPhone.setText(caixa.getTelefone());
            tfSenha.setText(caixa.getSenha());
            tfNasc.setText(caixa.getNascimento());
            tfRua.setText(caixa.getLogradouro().getRua());
            tfBairro.setText(caixa.getLogradouro().getBairro());
            tfNum.setText(caixa.getLogradouro().getNumero());
            tfCEP.setText(caixa.getLogradouro().getCep());
        }

    }

    public void editarCaixa() {

        int selectedIndex = jlCaixas.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Caixa> model = (DefaultListModel<Caixa>) jlCaixas.getModel();

            Caixa caixa = model.get(selectedIndex);

            model.remove(selectedIndex);

            try {
                caixa.setEmail(tfEmail.getText());
                caixa.setName(tfName.getText());
                caixa.setTelefone(tfPhone.getText());
                caixa.setSenha(new String(tfSenha.getPassword()));
                caixa.setLogradouro(new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()));

                model.add(selectedIndex, caixa);
            } catch (EmailException | NameException ex) {
                JOptionPane.showMessageDialog(tela, "O email " + tfEmail.getText() + " é invalido!");
            }
        }

        tela.pack();

    }

}
