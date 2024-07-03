package main;

import entity.*;

import javax.swing.*;
import java.io.*;
public class Main {
    static String tipoUser;
    static Usuario user;

    static {
        try {
            user = new Usuario();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static Controle controle = new Controle();

    public static void main(String[] args) throws Exception {
        Controle controle = new Controle();
        int opc;
        boolean continueOuterLoop = true;
        do {
            System.out.println(controle.isLogado());
            if (!controle.isLogado()) {
                try {
                    Object[] optionsMenu = {"Entrar", "Cadastrar", "Sair"};
                    opc = JOptionPane.showOptionDialog(null, "Selecione uma das opções:", "Menu", 0, 3, null, optionsMenu, optionsMenu[0]);

                    switch (opc) {
                        case 0:
                            logar();
                            controle.setLogado(true);
                            break;
                        case 1:
                            try {
                                cadastrar();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            break;
                        default:
                            JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Opção inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } //para quem já tá logado (ADM)
            else {
                // USER = ADM

                if (user.getTipo().equals("ADM")) {
                    Adm adm = new Adm();
                    ControleServicos controleServicos = new ControleServicos();
                    Object[] optionsCadastroProfissao = {"Cadastrar Serviço", "Cadastrar ADM", "Mostrar Serviços", "Deletar Serviço", "Sair"};
                    while (continueOuterLoop) {
                        opc = JOptionPane.showOptionDialog(null, "Selecione uma das opções:", "Menu - ADM", 0, 3, null, optionsCadastroProfissao, optionsCadastroProfissao[0]);
                        switch (opc) {
                            case 0 -> postServico(controleServicos);
                            case 1 -> postAdm();
                            case 2 -> getServices(controleServicos);
                            case 3 -> deletService(controleServicos);
                            case 4 -> {
                                controleServicos.arquivar();
                                JOptionPane.showMessageDialog(null, "Deslogando!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                controle.setLogado(false);
                                continueOuterLoop = false;
                            }
                            default -> {
                                JOptionPane.showInternalMessageDialog(null, "Operação cancelada, fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                            }
                        }
                    }
                    // USER = PROFISSIONAL
                } else if (tipoUser.equals("Profissional")) {
                    System.out.println("PROFISSAAAAAAAAAAAAA");
                    break;
                    // USER = CLIENTE
                } else {
                    Object[] optionsCliente = {"Visualizar profissionais", "Sair"};

                    while (true) {
                        opc = JOptionPane.showOptionDialog(null,"Selecione uma das opções:", "Menu - Cliente", 0, 2, null, optionsCliente, optionsCliente[0]);
                        switch (opc) {
                            case 0:
                                String[] profissionais = controle.carregarProfissionais("usuarios.txt");
                                JComboBox<String> profissional = new JComboBox<>(profissionais);
                                profissional.setSelectedItem("Selecione uma opção");

                                JOptionPane.showMessageDialog(null, profissional, "Profissionais disponíveis", JOptionPane.ERROR_MESSAGE);

                                break;
                            case 1:
                                JOptionPane.showMessageDialog(null, "Deslogando!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                controle.setLogado(false);
                                continueOuterLoop = true;

                                break;

                            default:
                                JOptionPane.showInternalMessageDialog(null,"Operação cancelada, fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                                System.exit(0);
                        }
                        if (continueOuterLoop) {
                            break;  // Sai do loop interno
                        }
                    }

                }
            }
        } while (continueOuterLoop);
    }

    public static void restart() throws Exception {
        main(null);
    }

    //CARLIN METODO INUTILL (VOLTAR JÀ COM O SETLOGADO TRUE PARA ELE JÀ VOLTAR COMO LOGADO)
    public static void restartLogado() throws Exception {

        Controle controle = new Controle();
        controle.setLogado(true);
        controle.setTipoUser(tipoUser);
        main(null);
    }

    public static void logar() {

        boolean certo = true;
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        Controle controle = new Controle();
        while (certo) {
            try {
                Object[] dados = {
                        "Email:", email,
                        "Senha:", senha
                };
                Object[] opcoes = {"OK", "Voltar", "Fechar"};

                int opt = JOptionPane.showOptionDialog(null, dados, "Entrar", 0, 3, null, opcoes, opcoes[0]);
                if (opt == 2) {
                    JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else if (opt == 0) {
                    user.setEmail(email.getText());
                    user.setSenha(senha.getText());
                    if (controle.logarControle(user)) {
                        System.out.println("CORRETO!");
                        tipoUser = user.getTipo();
                        JOptionPane.showMessageDialog(null, "Login feito com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        certo = false;
                    } else {
                        System.out.println("ERRO");
                    }
                } else {
                    Main.restart();
                }


            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public static void cadastrar() throws Exception {
        Object[] optionsMenu = {"Cliente", "Profissional", "Voltar"};
        int opc = JOptionPane.showOptionDialog(null, "Cadastrar como:", "Menu - cadastrar", 0, 3, null, optionsMenu, optionsMenu[0]);
        Object[] optionsCad;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        String[] profissoes = controle.carregarProfissoesDeArquivo("profissoes.txt");
        JComboBox<String> profissao = new JComboBox<>(profissoes);
        profissao.insertItemAt("Selecione uma opção", 0);
        profissao.setSelectedItem("Selecione uma opção");

        switch (opc) {
            case -1 -> {
                JOptionPane.showMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
            case 0 -> {
                optionsCad = new Object[]{
                        "Nome:", nome,
                        "Email:", email,
                        "Senha:", senha
                };
                while (true) {
                    try {
                        int opt = JOptionPane.showConfirmDialog(null, optionsCad, "Cadastrar cliente", JOptionPane.OK_CANCEL_OPTION);
                        if (opt == -1) {
                            JOptionPane.showMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        } if (opt == 2) {
                            Main.restart();
                        }
                        user = new Cliente(controle.codigoAleatorio(), nome.getText(), email.getText(), senha.getText(), "Cliente");
                        if (controle.emailExistente(user.getEmail())) throw new IOException("Email Existente!");
                        controle.cadastrarControle(user);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
            case 1 -> {
                optionsCad = new Object[]{
                        "Nome:", nome,
                        "Email:", email,
                        "Senha:", senha,
                        "Profissão:", profissao
                };

                while (true) {
                    try {
                        int opt = JOptionPane.showConfirmDialog(null, optionsCad, "Cadastrar Profissional", JOptionPane.OK_CANCEL_OPTION);
                        if (opt == -1) {
                            JOptionPane.showMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        } if (opt == 2) {
                            Main.restart();
                        }
                        user = new Profissional(controle.codigoAleatorio(), nome.getText(), email.getText(), senha.getText(), "Profissional", (String)profissao.getSelectedItem());
                        controle.cadastrarControle(user);
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    public static void postServico(ControleServicos controleServicos) throws Exception {
        String service;
        Profissao profissao = new Profissao();
        JTextField profissaoField = new JTextField();
        Object[] opcoes = {"OK", "Voltar"};
        boolean continuarLoop = true;
        while (continuarLoop) {
            int opc = JOptionPane.showOptionDialog(null, profissaoField, "Cadastro - Profissão", 0, 2, null, opcoes, opcoes[0]);
            switch (opc) {
                case 0 -> {
                    service = profissaoField.getText();
                    profissao.setName(service);
                    if (controleServicos.cadastrarProfissaoAdm(profissao)) {
                        JOptionPane.showMessageDialog(null, "Serviço cadastrado com sucesso!!", "alerta", JOptionPane.INFORMATION_MESSAGE);
                        continuarLoop = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Serviço já cadastrado", "alerta", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case 1 -> Main.restart();
                default ->
                        JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
            // CARLIN AJEITAR PARA QUANDO ELE SAIR JÀ VOLTAR COMO ADM LOGADO

        }
    }

    public static void postAdm() throws Exception {
        Adm adm;
        JTextField nome = new JTextField();
        JTextField email = new JTextField();
        JTextField senha = new JTextField();
        Object[] message = new Object[]{
                "Nome:", nome,
                "Email:", email,
                "Senha:", senha
        };
        while (true) {
            try {
                int opt = JOptionPane.showConfirmDialog(null, message, "Cadastrar ADM", JOptionPane.OK_CANCEL_OPTION);
                adm = new Adm(controle.codigoAleatorio(), nome.getText(), email.getText(), senha.getText(), "ADM");
                if (controle.emailExistente(adm.getEmail())) throw new IOException("Email Existente!");
                controle.cadastrarAdmControle(adm);
                JOptionPane.showMessageDialog(null, "ADM cadastrado com sucesso!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                if (opt != JOptionPane.OK_OPTION) {
                    JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                break;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    public static void getServices(ControleServicos controleAdm) throws IOException {
        /*List<String> allServices = new ArrayList<>(Arrays.asList(controleAdm.mostrarProfissao()));
        String[] services = new String[allServices.size()];
        for (int i = 0 ; i < allServices.size() ; i++) {
            String[] campos = allServices.get(i).split(";");
            services[i] = campos[0] + ") " + campos[1];
        }*/

        JComboBox<String> profissional = new JComboBox<>(controleAdm.mostrarProfissao());
        JOptionPane.showMessageDialog(null, profissional, "Profissionais disponíveis", JOptionPane.OK_OPTION);
    }

    public static void deletService(ControleServicos controleServicos) throws Exception {
        JTextField profissaoField = new JTextField();
        Object[] opcoes = {"OK", "Voltar"};
        boolean continuarLoop = true;
        while (continuarLoop) {
            int opc = JOptionPane.showOptionDialog(null, profissaoField, "Deletar - Profissão", 0, 2, null, opcoes, opcoes[0]);

            switch (opc) {
                case 0 -> {
                    String[] allServices = controleServicos.mostrarProfissao();
                    JComboBox<String> profissional = new JComboBox<>(allServices);
                    JOptionPane.showMessageDialog(null, profissional, "Profissionais disponíveis", JOptionPane.OK_OPTION);
                    System.out.println((String) profissional.getSelectedItem());
                    String itemExcluido = (String) profissional.getSelectedItem();
                    controleServicos.deletServicoControle(itemExcluido);
                    JOptionPane.showMessageDialog(null, "Serviço deletado com sucesso!!", "alerta", JOptionPane.INFORMATION_MESSAGE);
                    continuarLoop = false;
                }
                case 1 -> Main.restart();
                default ->
                        JOptionPane.showInternalMessageDialog(null, "Fim do programa!", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            }
            // CARLIN AJEITAR PARA QUANDO ELE SAIR JÀ VOLTAR COMO ADM LOGADO

        }
    }

}

