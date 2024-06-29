package entity;

import java.util.Scanner;

public class Controle {
    Usuario usuario = new Usuario();
    
    Scanner sc = new Scanner(System.in);
    Scanner scStr = new Scanner(System.in);
    String senha, confSenha;
    public boolean cadastrar(int opc) {
        if (opc == 1) {
            System.out.println("Cadastrar usuário:");
            System.out.print("Nome: ");
            usuario.setNome(scStr.nextLine());
            System.out.println("Email: ");
            usuario.setEmail(scStr.nextLine());
            do{
            	//while
            	System.out.println("Digite a senha: ");
            	senha = sc.nextLine();
            	System.out.println("Confirme a senha a senha: ");
            	confSenha = sc.nextLine();
            	
            	if(!senha.equals(confSenha)) {
            		System.out.println();
            		System.out.println("------------------------------------");
            		System.out.println("Senhas diferentes, repita a senha!");
            		System.out.println();
            	}
            	
            }while(senha.equals(confSenha));
            usuario.setSenha(sc.nextLine());
            
        }
        return true;
    }

}
