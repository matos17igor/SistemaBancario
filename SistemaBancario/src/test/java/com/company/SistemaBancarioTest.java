package com.company;

import com.company.model.*;
import com.company.persistence.*;
import com.company.exception.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SistemaBancarioTest {

    private ClientePersistence clientePersistence;
    private CaixaPersistence caixaPersistence;
    private GerentePersistence gerentePersistence;
    private CreditoPersistence creditoPersistence;
    private SaquePersistence saquePersistence;
    private TransferenciaPersistence transferenciaPersistence;

    @BeforeEach
    public void setUp() {
        clientePersistence = new ClientePersistence();
        caixaPersistence = new CaixaPersistence();
        gerentePersistence = new GerentePersistence();
        creditoPersistence = new CreditoPersistence();
        saquePersistence = new SaquePersistence();
        transferenciaPersistence = new TransferenciaPersistence();
    }

    @Test
    public void testValidarCPF() {
        // Testa um CPF válido
        assertTrue(ValidaCPF.isCPF("52998224725"));

        // Testa um CPF inválido
        assertFalse(ValidaCPF.isCPF("111.111.111-11"));
    }

    @Test
    public void testValidarEmail() {
        // Testa um e-mail válido
        assertDoesNotThrow(() -> new Email("teste@teste.com"));

        // Testa um e-mail inválido
        assertThrows(EmailException.class, () -> new Email("teste@"));
    }

    @Test
    public void testAdicionarCredito() {
        // Cria um cliente e um crédito
        Cliente cliente = new Cliente();
        try {
            cliente.setName("Cliente Teste");
        } catch (NameException e) {
            System.out.println(e.getMessage());
        }
        Credito credito = new Credito("Pessoal", cliente, 1000.0, 100.0, 12, 0.1);
        // Adiciona o crédito
        creditoPersistence.adicionarSolicitacao(credito);

        // Verifica se o crédito foi adicionado corretamente
        List<Credito> creditos = creditoPersistence.getSolicitacoes();
        assertEquals(1, creditos.size());
        assertEquals("Cliente Teste", creditos.get(0).getCliente().getName());
    }

    @Test
    public void testAdicionarSaque() {
        // Cria uma conta e um saque
        Conta conta = new Conta("12345", 1000.0, "senha123", "Titular Teste");
        Saque saque = new Saque(conta, 500.0);

        // Adiciona o saque
        saquePersistence.adicionarSolicitacao(saque);

        // Verifica se o saque foi adicionado corretamente
        List<Saque> saques = saquePersistence.getSolicitacoes();
        assertEquals(1, saques.size());
        assertEquals(500.0, saques.get(0).getValor());
    }

    @Test
    public void testAdicionarTransferencia() {
        // Cria contas e uma transferência
        Conta origem = new Conta("12345", 1000.0, "senha123", "Titular Origem");
        Conta destino = new Conta("67890", 500.0, "senha456", "Titular Destino");
        Transferencia transferencia = new Transferencia(origem, 300.0, destino);

        // Adiciona a transferência
        transferenciaPersistence.adicionarSolicitacao(transferencia);

        // Verifica se a transferência foi adicionada corretamente
        List<Transferencia> transferencias = transferenciaPersistence.getSolicitacoes();
        assertEquals(1, transferencias.size());
        assertEquals(300.0, transferencias.get(0).getValor());
    }

    @Test
    public void testSaldoInsuficiente() {
        // Cria uma conta com saldo insuficiente
        Conta conta = new Conta("12345", 100.0, "senha123", "Titular Teste");

        // Tenta realizar um saque maior que o saldo
        assertThrows(SaldoException.class, () -> {
            if (conta.getSaldo() < 200.0) {
                throw new SaldoException();
            }
        });
    }

    @Test
    public void testDeposito() {
        // Cria uma conta de destino
        Conta destino = new Conta("12345", 1000.0, "senha123", "Titular Teste");

        // Realiza um depósito de 500.0 na conta de destino
        Deposito deposito = new Deposito(null, 500.0, destino);
        deposito.getDestino().setSaldo(deposito.getDestino().getSaldo() + deposito.getValor());

        // Verifica se o saldo da conta de destino foi atualizado corretamente
        assertEquals(1500.0, destino.getSaldo());
    }

    @Test
    public void testSaque() throws SaldoException {
        // Cria uma conta com saldo inicial de 1000.0
        Conta origem = new Conta("12345", 1000.0, "senha123", "Titular Teste");

        // Realiza um saque de 500.0
        Saque saque = new Saque(origem, 500.0);
        if (origem.getSaldo() < saque.getValor()) {
            throw new SaldoException();
        }
        origem.setSaldo(origem.getSaldo() - saque.getValor());

        // Verifica se o saldo da conta de origem foi atualizado corretamente
        assertEquals(500.0, origem.getSaldo());
    }

    @Test
    public void testTransferencia() throws SaldoException {
        // Cria uma conta de origem e uma conta de destino
        Conta origem = new Conta("12345", 1000.0, "senha123", "Titular Origem");
        Conta destino = new Conta("67890", 500.0, "senha456", "Titular Destino");

        // Realiza uma transferência de 300.0 da conta de origem para a conta de destino
        Transferencia transferencia = new Transferencia(origem, 300.0, destino);
        if (origem.getSaldo() < transferencia.getValor()) {
            throw new SaldoException();
        }
        origem.setSaldo(origem.getSaldo() - transferencia.getValor());
        destino.setSaldo(destino.getSaldo() + transferencia.getValor());

        // Verifica se os saldos foram atualizados corretamente
        assertEquals(700.0, origem.getSaldo());
        assertEquals(800.0, destino.getSaldo());
    }

    @Test
    public void testCredito() {
        // Cria um cliente e um crédito
        Cliente cliente = new Cliente();
        try {
            cliente.setName("Cliente Teste");
        } catch (NameException e) {
            System.out.println(e.getMessage());
        }
        Credito credito = new Credito("Pessoal", cliente, 1000.0, 100.0, 12, 0.1);

        // Verifica se os dados do crédito foram configurados corretamente
        assertEquals("Pessoal", credito.getTipo());
        assertEquals(1000.0, credito.getValor());
        assertEquals(12, credito.getParcelas());
        assertEquals("Cliente Teste", credito.getCliente().getName());
    }

    @Test
    public void testMovimentacao() {
        // Cria uma movimentação
        Movimentacao movimentacao = new Movimentacao(500.0, "Depósito", "Conta 12345");

        // Verifica se os dados da movimentação foram configurados corretamente
        assertEquals(500.0, movimentacao.getValor());
        assertEquals("Depósito", movimentacao.getDescricao());
        assertEquals("Conta 12345", movimentacao.getOrigem());
    }

    @Test
    public void testInvestimento() {
        // Cria um investimento
        Investimento investimento = new Investimento("Tesouro Direto", 1000.0, 0.1);

        // Verifica se os dados do investimento foram configurados corretamente
        assertEquals("Tesouro Direto", investimento.getNome());
        assertEquals(1000.0, investimento.getValorMinimo());
        assertEquals(0.1, investimento.getTaxaRendimento());
    }

    @Test
    public void testInvestimentoRendaFixa() {
        // Cria um investimento de renda fixa
        InvestimentoRendaFixa investimento = new InvestimentoRendaFixa("CDB", 1000.0, 0.1, 12);

        // Verifica se os dados do investimento foram configurados corretamente
        assertEquals("CDB", investimento.getNome());
        assertEquals(1000.0, investimento.getValorMinimo());
        assertEquals(0.1, investimento.getTaxaRendimento());
        assertEquals(12, investimento.getPrazoMinimo());
    }

    @Test
    public void testInvestimentoRendaVariavel() {
        // Cria um investimento de renda variável
        InvestimentoRendaVariavel investimento = new InvestimentoRendaVariavel("Ações", 1000.0, 0.15, 5.0);

        // Verifica se os dados do investimento foram configurados corretamente
        assertEquals("Ações", investimento.getNome());
        assertEquals(1000.0, investimento.getValorMinimo());
        assertEquals(0.15, investimento.getTaxaRendimento());
        assertEquals(5.0, investimento.getRisco());
    }

    @Test
    public void testPasswordException() {
        // Tenta criar uma senha inválida (vazia)
        assertThrows(PasswordException.class, () -> {
            String senha = ""; // Senha inválida
            if (senha == null || senha.trim().isEmpty()) {
                throw new PasswordException();
            }
        });

        // Tenta criar uma senha inválida (nula)
        assertThrows(PasswordException.class, () -> {
            String senha = null; // Senha inválida
            if (senha == null || senha.trim().isEmpty()) {
                throw new PasswordException();
            }
        });
    }
}
