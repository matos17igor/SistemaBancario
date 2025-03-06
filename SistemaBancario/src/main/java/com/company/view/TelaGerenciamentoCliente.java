package com.company.view;

import com.company.controller.AdicionarCliente;
import com.company.controller.EditarCliente;
import com.company.controller.GerenciarClientes;
import com.company.controller.RemoverCliente;
import com.company.controller.SelecionarCliente;
import com.company.exception.CPFException;
import com.company.exception.EmailException;
import com.company.exception.NameException;
import com.company.model.Cliente;
import com.company.model.Conta;
import com.company.model.Email;
import com.company.model.Endereco;
import com.company.persistence.ClientePersistence;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TelaGerenciamentoCliente {

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
    private JList<Cliente> jlClientes;

    public void desenha() {

        tela = new JFrame("Gerenciamento de clientes"); //modelo
        tela.addWindowListener(new GerenciarClientes(this));    //Carrega os clientes salvos quando a janela for aberta e salva os clientes atualizados quando a janela for fechada
        tela.setSize(WIDTH, HEIGHT); //modelo
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //modelo
        tela.setVisible(true); //modelo
        tela.setLayout(new BorderLayout()); //modelo
        tela.setResizable(false); //modelo
        tela.setLocationRelativeTo(null); //modelo

        desenhaLista();
        desenhaFormulario();

        tela.pack();
    }

    private void desenhaLista() {

        JPanel painel = new JPanel();
        painel.setBorder(BorderFactory.createTitledBorder("Clientes"));
        painel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        painel.setLayout(new BorderLayout());

        DefaultListModel<Cliente> model = new DefaultListModel<>();

        jlClientes = new JList<>(model);
        jlClientes.addListSelectionListener(new SelecionarCliente(this));

        painel.add(new JScrollPane(jlClientes), BorderLayout.CENTER);

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
        btnAdicionar.addActionListener(new AdicionarCliente(this));

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new RemoverCliente(this));

        JButton btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new EditarCliente(this));

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

    public void carregaClientes(List<Cliente> clientes) {

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();

        for (Cliente c : clientes) {
            model.addElement(c);
        }

    }

    public List<Cliente> listaClientes() {
        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        List<Cliente> clientes = new ArrayList<>();

        for (int i = 0; i < model.size(); i++) {
            clientes.add(model.get(i));
        }

        return clientes;
    }

    public void addCliente() {

        DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
        try {
            model.addElement(new Cliente(getNextId(), tfName.getText(), new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()), tfNasc.getText(), tfCPF.getText(), new Email(tfEmail.getText()), new String(tfSenha.getPassword()), tfPhone.getText(), new Conta(UUID.randomUUID().toString(), 0, new String(tfSenha.getPassword()), tfName.getText())));
        } catch (EmailException | NameException | CPFException e) {
            JOptionPane.showMessageDialog(tela, "Erro: " + e.getMessage());
        }

    }

    public int getNextId() {
        ClientePersistence clienteP = new ClientePersistence();
        List<Cliente> clientes = clienteP.findAll(); // Carrega os clientes salvos

        if (clientes.isEmpty()) {
            return 1; // Se não houver clientes, começa do 1
        }

        return clientes.stream()
                .mapToInt(Cliente::getId)
                .max()
                .orElse(0) + 1; // Pega o maior ID e soma 1
    }

    public void removerCliente() {

        int selectedIndex = jlClientes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Cliente> model
                    = (DefaultListModel<Cliente>) jlClientes.getModel();
            model.remove(selectedIndex);
        }
    }

    public void atualizarFormulario() {
        int selectedIndex = jlClientes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();
            Cliente cliente = model.get(selectedIndex);
            tfName.setText(cliente.getName());
            tfCPF.setText(cliente.getCpf());
            tfEmail.setText(cliente.getEmail());
            tfPhone.setText(cliente.getTelefone());
            tfSenha.setText(cliente.getSenha());
            tfNasc.setText(cliente.getNascimento());
            tfRua.setText(cliente.getLogradouro().getRua());
            tfBairro.setText(cliente.getLogradouro().getBairro());
            tfNum.setText(cliente.getLogradouro().getNumero());
            tfCEP.setText(cliente.getLogradouro().getCep());
        }

    }

    public void editarCliente() {

        int selectedIndex = jlClientes.getSelectedIndex();

        if (selectedIndex != -1) {

            DefaultListModel<Cliente> model = (DefaultListModel<Cliente>) jlClientes.getModel();

            Cliente cliente = model.get(selectedIndex);

            model.remove(selectedIndex);

            try {
                cliente.setEmail(tfEmail.getText());
                cliente.setName(tfName.getText());
                cliente.setTelefone(tfPhone.getText());
                cliente.setSenha(new String(tfSenha.getPassword()));
                cliente.setLogradouro(new Endereco(tfRua.getText(), tfBairro.getText(), tfNum.getText(), tfCEP.getText()));

                model.add(selectedIndex, cliente);
            } catch (EmailException | NameException ex) {
                JOptionPane.showMessageDialog(tela, "O email " + tfEmail.getText() + " é invalido!");
            }
        }

        tela.pack();

    }

}
