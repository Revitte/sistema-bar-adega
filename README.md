# 🍺 Sistema Bar & Adega

Sistema de gestão de estoque para bares e adegas, com cadastro de produtos, categorias, preços e controle de entrada e saída de estoque.

## Sobre o projeto

Projeto pessoal criado para praticar back-end e front-end com Java, aplicando conceitos de arquitetura em camadas (Controller, Service, Repository) e regras de negócio reais, como validação de estoque insuficiente na hora de dar saída em um produto.

## Funcionalidades

- Listagem de produtos (nome, categoria, preço, estoque)
- Cadastro de novos produtos
- Saída de estoque por nome do produto, com validação para não deixar o estoque negativo

## Tecnologias

- Java 21
- Spring Boot
- Thymeleaf
- H2 Database (banco em memória)
- Gradle

## Como rodar localmente

```bash
git clone https://github.com/Revitte/sistema-bar-adega.git
cd sistema-bar-adega
./gradlew bootRun
```

Depois é só acessar `http://localhost:8080/produtos` no navegador.

## Preview

![Tela de Produtos](docs/preview.png)

## Roadmap

- [ ] Gerar valor de caixa a partir das saídas de estoque
- [ ] Migrar de H2 para um banco persistente
- [ ] Autenticação de usuário
- [ ] Relatórios de estoque e vendas

## Autor

Feito por [Revitte](https://github.com/Revitte)
