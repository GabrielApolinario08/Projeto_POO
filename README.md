# Sistema de Gerenciamento de Usuários e Serviços

## Descrição

Desenvolvemos, em colaboração com [Caio Balieiro](https://github.com/CaioBalieir0), [Gabriel Apolinário](https://github.com/GabrielApolinario08) e [Carlos Eduardo](https://github.com/CaioBalieir0), um sistema de gerenciamento que permite o cadastro, login e gerenciamento de usuários e serviços. O sistema implementa funcionalidades de CRUD (Create, Read, Update, Delete) para gerenciar usuários com diferentes níveis de acesso (Administrador, Profissional e Cliente). Administradores podem adicionar ou remover serviços, cadastrar novos administradores e listar serviços disponíveis. Profissionais podem visualizar outros profissionais cadastrados. Os dados são armazenados em arquivos `.txt` para persistência.

## Funcionalidades

* **Cadastro de Usuário:** Permite o cadastro de novos usuários com informações como nome, senha, e-mail, e tipo (Profissional, Cliente ou Administrador).
* **Login de Usuário:** Sistema de autenticação que diferencia o acesso com base no tipo de usuário.
* **Gerenciamento de Serviços:**
  * **Administrador:** Pode adicionar ou remover serviços, cadastrar novos administradores e listar os serviços disponíveis.
  * **Profissional/Cliente:** Pode visualizar os profissionais cadastrados e serviços disponíveis.
* **Estrutura de Usuários:**
  * **Usuário:** Possui código, nome, senha, e-mail e tipo.
  * **Profissional:** Além dos atributos de usuário, tem uma profissão associada com nome e código.
  * **Profissão:** Gerenciada com nome e código.

## Tecnologias Utilizadas

* **Java:** Linguagem de programação principal.
* **JOptionPane:** Interface gráfica para interação com o usuário.
* **Arquivos de Texto (.txt):** Persistência de dados em `profissoes.txt` e `usuarios.txt`.

## Estrutura do Projeto

* **Pasta `src/main`:** Contém o arquivo `Main.java`, ponto de entrada do programa.
* **Pasta `src/entity`:** Contém as classes principais do sistema:
  * **Usuario.java:** Classe base para todos os usuários.
  * **Adm.java:** Classe para administradores, com permissões especiais.
  * **Cliente.java:** Classe para clientes, com funcionalidades específicas.
  * **Profissional.java:** Classe para profissionais, com associação a uma profissão.
  * **Profissao.java:** Classe que define uma profissão.
  * **Controle.java:** Classe para gerenciamento de usuários e autenticação.
  * **ControleServicos.java:** Classe para gerenciamento de serviços.

## Instalação e Uso

1. **Clone o Repositório:**

   ```
   git clone https://github.com/GabrielApolinario08/Projeto_POO
   ```
2. **Navegue até o Diretório do Projeto:**

   ```
   cd Projeto_POO
   ```
3. **Compile e Execute o arquivo Main.java:**


## Como Usar

* **Usuário Comum:** Pode se cadastrar e fazer login para visualizar profissionais.
* **Administrador:** Pode fazer login para gerenciar serviços e usuários.
* **Profissional:** Pode acessar o sistema para visualizar outros profissionais e seus serviços.
