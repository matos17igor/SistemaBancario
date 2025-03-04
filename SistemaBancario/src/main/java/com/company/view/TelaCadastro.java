package com.company.view;

import com.company.controller.CadastrarCliente;
import com.company.exception.CPFException;
import com.company.exception.EmailException;
import com.company.exception.NameException;
import com.company.model.Cliente;
import com.company.model.Conta;
import com.company.model.Email;
import com.company.model.Endereco;
import com.company.persistence.ClientePersistence;
import com.company.persistence.ContaPersistence;
import com.company.view.frames.CadastroClienteFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.UUID;

public class TelaCadastro {

    private CadastroClienteFrame tela;

    public void desenha() {

        tela = new CadastroClienteFrame();
        tela.setVisible(true);

        JButton btn = tela.getBtnSubmit();
        btn.addActionListener(new CadastrarCliente(this));
    }

    public void cadastrarCliente() {

        String name = tela.getTfName().getText().trim();
        String email = tela.getTfEmail().getText().trim();
        String cpf = tela.getTfCPF().getText().trim();
        String nasc = tela.getTfNasc().getText().trim();
        String phone = tela.getTfPhone().getText().trim();
        String rua = tela.getTfRua().getText().trim();
        String bairro = tela.getTfBairro().getText().trim();
        String num = tela.getTfNum().getText().trim();
        String cep = tela.getTfCEP().getText().trim();
        String senha = new String(tela.getTfSenha().getPassword()).trim();

        // Validação de campos vazios
        if (name.isEmpty() || email.isEmpty() || cpf.isEmpty() || nasc.isEmpty() || phone.isEmpty()
                || rua.isEmpty() || bairro.isEmpty() || num.isEmpty() || cep.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Email em = new Email(email);
            Endereco e = new Endereco(rua, bairro, num, cep);
            String numConta = UUID.randomUUID().toString(); //Cria numero aleatorio da conta
            Conta conta = new Conta(numConta, 0, senha, name);
            Cliente c = new Cliente(getNextId(), name, e, nasc, cpf, em, senha, phone, conta);

            ClientePersistence clienteP = new ClientePersistence();
            ContaPersistence contaP = new ContaPersistence();

            clienteP.add(c);
            contaP.add(conta);
        } catch (EmailException | NameException | CPFException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Cadastro realizado com succeso.", "Parabéns!", JOptionPane.OK_OPTION);
        tela.setVisible(false);
    }

    public int getNextId() {
        ClientePersistence clienteP = new ClientePersistence();
        List<Cliente> clientes = clienteP.load(); // Carrega os clientes salvos

        if (clientes.isEmpty()) {
            return 1; // Se não houver clientes, começa do 1
        }

        return clientes.stream()
                .mapToInt(Cliente::getId)
                .max()
                .orElse(0) + 1; // Pega o maior ID e soma 1
    }

}
