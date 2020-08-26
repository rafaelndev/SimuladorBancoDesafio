# Simulator de Aplicativo de Banco

Com funcionalidade basica de Deposito e Saque em conta.
Usando as tecnologias:
* Angular
* Spring Boot

## Arquitetura utilizada

A arquitetura usada foi a BFF Back-end for Front-end (Single Page Application), comunicando pela camada REST no Front usando o Spring Web.
Usando esse padrão arquitetural, temos uma melhor independência entre consumidor e provedor, podendo reusar a camada de API em outras aplicações caso necessário.

No Back-end, foi usado a arquitetura padrão do Spring Boot
* Camada de apresentação/api (Controllers)
* Camada de negócio (Services)
* Camada de Persistência (Repositórios JPA)

Foi usado também uma camada de Basic Authentication para registro de usuário.

## Problemas encontrados

## Solução / Padrão Arquitetural
Decidi por adicionar autenticação no projeto, para que a tomada de decisão seja feita mas pelo lado do servidor, tirando a dependência de dados enviados pelo cliente. 
Dessa forma a Conta Bancária que o usuário tem acesso, é ligada sempre ao Usuário logado, impedindo assim o acesso a outras contas.
