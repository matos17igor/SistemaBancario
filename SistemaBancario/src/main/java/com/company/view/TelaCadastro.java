package com.company.view;

import com.company.controller.CadastrarCliente;
import com.company.exception.*;
import com.company.model.*;
import com.company.persistence.ClientePersistence;
import com.company.view.frames.CadastroClienteFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;

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

        // Validação da Data de Nascimento (Formato e maioridade)
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            Date dataNascimento = sdf.parse(nasc);

            // Verifica se o usuário tem pelo menos 18 anos
            Calendar hoje = Calendar.getInstance();
            Calendar nascimento = Calendar.getInstance();
            nascimento.setTime(dataNascimento);
            int idade = hoje.get(Calendar.YEAR) - nascimento.get(Calendar.YEAR);
            if (hoje.get(Calendar.DAY_OF_YEAR) < nascimento.get(Calendar.DAY_OF_YEAR)) {
                idade--;
            }

            if (idade < 18) {
                JOptionPane.showMessageDialog(null, "Você deve ter pelo menos 18 anos para se cadastrar.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data de nascimento inválida! Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do telefone (somente números e 10 ou 11 dígitos)
        if (!phone.matches("\\d{10,11}")) {
            JOptionPane.showMessageDialog(null, "Telefone inválido! Use o formato (XX) XXXXX-XXXX.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do número da residência (deve ser numérico)
        if (!num.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Número da residência inválido! Apenas números são permitidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação do CEP (8 dígitos numéricos)
        if (!cep.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "CEP inválido! Deve conter 8 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validação da senha (mínimo 6 caracteres, incluindo letras e números)
        if (senha.length() < 8) {
            JOptionPane.showMessageDialog(null, "Senha inválida! Deve conter pelo menos 8 caracteres.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Email em = new Email(email);
            Endereco e = new Endereco(rua, bairro, num, cep);
            Random random = new Random();
            int numero = 100000 + random.nextInt(900000); //Cria numero aleatorio da conta
            String numConta = String.valueOf(numero);
            Conta conta = new Conta(numConta, 0, senha, name);
            Cliente c = new Cliente(getNextId(), name, e, nasc, cpf, em, senha, phone, conta);

            ClientePersistence clienteP = new ClientePersistence();
            clienteP.add(c);
        } catch (EmailException | NameException | CPFException e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null, "Cadastro realizado com succeso.", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
        tela.setVisible(false);
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

}
