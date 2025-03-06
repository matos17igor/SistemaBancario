package com.company.view;

import com.company.controller.AdicionarGerente;
import com.company.controller.EditarGerente;
import com.company.controller.GerenciarGerentes;
import com.company.controller.RemoverGerente;
import com.company.controller.SelecionarGerente;
import com.company.exception.CPFException;
import com.company.exception.EmailException;
import com.company.exception.NameException;
import com.company.model.Gerente;
import com.company.model.Email;
import com.company.model.Endereco;
import com.company.persistence.GerentePersistence;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaGerenciamentoGerente {

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
    private JList<Gerente> jlgerentes;

    public void desenha() {

        tela = new JFrame("Gerenciamento de gerentes");
        tela.addWindowListener(new GerenciarGerentes(this));
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
        painel.setBorder(BorderFactory.createTitledBorder("Gerentes"));
        painel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Gerente> model = new DefaultListModel<>();

        jlgerentes = new JList<>(model);
        jlgerentes.addListSelectionListener(new SelecionarGerente(this));

        painel.add(new JScrollPane(jlgerentes), BorderLayout.CENTER);

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
        btnAdicionar.addActionListener(new AdicionarGerente(this));

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new RemoverGerente(this));

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new EditarGerente(this));
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

    public void carregaGerentes(List<Gerente> gerentes) {

        DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlgerentes.getModel();

        for (Gerente c : gerentes) {
            model.addElement(c);
        }

    }

    public List<Gerente> listaGerentes() {
        DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlgerentes.getModel();
        List<Gerente> gerentes = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            gerentes.add(model.get(i));
        }

        return gerentes;
    }

    public void addGerente() {

        DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlgerentes.getModel();
        try {
            model.addElement(new Gerente(getNextId(), tfName.getText(), new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()), tfNasc.getText(), tfCPF.getText(), new Email(tfEmail.getText()), new String(tfSenha.getPassword()), tfPhone.getText()));
        } catch (EmailException | NameException | CPFException e) {
            JOptionPane.showMessageDialog(tela, "Erro: " + e.getMessage());
        }

    }

    public int getNextId() {
        GerentePersistence gerenteP = new GerentePersistence();
        List<Gerente> gerentes = gerenteP.findAll(); // Carrega os gerentes salvos

        if (gerentes.isEmpty()) {
            return 1; // Se não houver gerentes, começa do 1
        }

        return gerentes.stream()
                .mapToInt(Gerente::getId)
                .max()
                .orElse(0) + 1; // Pega o maior ID e soma 1
    }

    public void removerGerente() {

        int selectedIndex = jlgerentes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Gerente> model
                    = (DefaultListModel<Gerente>) jlgerentes.getModel();
            model.remove(selectedIndex);
        }
    }

    public void atualizarFormulario() {
        int selectedIndex = jlgerentes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlgerentes.getModel();
            Gerente gerente = model.get(selectedIndex);
            tfName.setText(gerente.getName());
            tfCPF.setText(gerente.getCpf());
            tfEmail.setText(gerente.getEmail());
            tfPhone.setText(gerente.getTelefone());
            tfSenha.setText(gerente.getSenha());
            tfNasc.setText(gerente.getNascimento());
            tfRua.setText(gerente.getLogradouro().getRua());
            tfBairro.setText(gerente.getLogradouro().getBairro());
            tfNum.setText(gerente.getLogradouro().getNumero());
            tfCEP.setText(gerente.getLogradouro().getCep());
        }

    }

    public void editarGerente() {

        int selectedIndex = jlgerentes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Gerente> model = (DefaultListModel<Gerente>) jlgerentes.getModel();

            Gerente gerente = model.get(selectedIndex);

            model.remove(selectedIndex);

            try {
                gerente.setEmail(tfEmail.getText());
                gerente.setName(tfName.getText());
                gerente.setTelefone(tfPhone.getText());
                gerente.setSenha(new String(tfSenha.getPassword()));
                gerente.setLogradouro(new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()));

                model.add(selectedIndex, gerente);
            } catch (EmailException | NameException ex) {
                JOptionPane.showMessageDialog(tela, "O email " + tfEmail.getText() + " é invalido!");
            }
        }

        tela.pack();

    }

}
