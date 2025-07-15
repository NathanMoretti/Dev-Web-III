# Controle de Locadora VHS

Este projeto é uma aplicação web feita para ajudar a gerenciar uma locadora de fitas VHS de um jeito prático e moderno.

---

### 1. 🛠️ Tecnologias Utilizadas

Construí este sistema usando as seguintes ferramentas e linguagens:

* **Java 17**: A linguagem de programação que faz tudo funcionar por trás dos panos.
* **Spring Boot**: A base do projeto, que facilita a vida do desenvolvedor ao montar a estrutura e configurar a aplicação.
    * **Spring Web**: Usado para criar as páginas da internet e garantir que o site se comunique bem.
    * **Spring Data JPA**: Facilita o trabalho com o banco de dados, tornando simples guardar e buscar informações.
    * **Spring Security**: Para proteger seu login e seus dados, garantindo que só pessoas autorizadas acessem o sistema.
* **Thymeleaf**: O motor de templates que me ajudou a criar as telas do site de forma dinâmica e interativa.
* **MySQL**: O banco de dados onde todas as informações são guardadas de forma segura.
* **Lombok**: Uma ferramenta que me ajuda a escrever menos código, deixando o projeto mais limpo e organizado.
* **Maven**: O "gerente de projeto" que organiza tudo, baixa o que é preciso e prepara o sistema para rodar.

---

### 2. ✨ O Que Ele Faz? (Funcionalidades)

Este sistema foi feito para controlar tudo em uma locadora de VHS de forma eficiente:

* **Entrada e Saída (Login e Cadastro)**:
    * Você pode **criar uma conta** se ainda não tiver uma.
    * Depois, é só **entrar** com seu e-mail e senha para acessar o sistema.
    * Ao entrar, você vai para a **página principal** (`Home`) com as opções de gerenciamento.
    * E pode **sair** da sua sessão a qualquer momento.

* **Gerenciar Fitas VHS**:
    * **Ver a lista** de todas as fitas cadastradas, incluindo capa (se houver), diretor, categoria, data de cadastro e o status atual (disponível, emprestada, etc.).
    * **Adicionar fitas novas** ao seu acervo com todos os detalhes necessários.
    * **Mudar os detalhes** de uma fita já existente, como título ou diretor.
    * **Remover fitas** que você não tem mais no estoque.
    * **Atualizar o status** da fita (por exemplo, de "DISPONÍVEL" para "EMPRESTADA" ou "INDISPONÍVEL").

* **Gerenciar Categorias (Ação, Comédia, Terror, etc.)**:
    * **Ver a lista** de todas as categorias de filmes cadastradas.
    * **Criar novas categorias** para organizar seus filmes.
    * **Mudar o nome** de uma categoria existente.
    * **Apagar categorias** que não são mais usadas. Se alguma fita estava nessa categoria, ela automaticamente fica "sem categoria" para não perder a informação.

---

### 3. 🚀 Como Fazer o Projeto Rodar no Seu Computador

Para testar este sistema e ver ele funcionando, você vai precisar de algumas coisas instaladas e seguir alguns passos simples:

#### O Que Você Precisa Ter (Pré-requisitos)

* **Java (versão 17 ou mais nova)**: É a linguagem principal, então é essencial ter o ambiente de desenvolvimento Java (JDK) instalado.
* **Apache Maven**: Ele é o responsável por organizar o projeto, baixar todas as peças (dependências) e "montar" o sistema para você.
* **MySQL Server**: Este é o programa do banco de dados onde todas as informações do seu sistema (usuários, fitas, categorias) serão guardadas.
