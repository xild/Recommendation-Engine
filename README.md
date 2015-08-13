# Recommendation Engine
Projeto criado com Neo4j e Spring-data para um sistema de recomendação. Para a iteração com o sistema de recomendação os serviços abaixos foram disponibilizados via RestFUL



#Descrição dos serviços

- Inserção de Pessoa
  - curl -X POST -F 'personId={personId}&email={email}&name={name}' https://hostName:port/v1/peoples
- Leitura de Pessoa única
  - curl -X GET https://hostName:port/v1/peoples/{personId}
- Lista de Pessoas com limite
  - curl -X GET https://hostName:port/v1/peoples?limit={limit}
- Delete de Pessoa
  - curl -X DELETE https://hostName:port/v1/peoples/{personId}
- Inserção de Produto
  - curl -X POST -F 'productId={productId}&price={price}&name={name}' https://hostName:port/v1/products
- Leitura de Produto único
  - curl -X GET https://hostName:port/v1/products/{productId}
- Lista de Produtos com limite
  - curl -X GET https://hostName:port/v1/products?limit={limit}
- Delete de produto
  - curl -X DELETE https://hostName:port/v1/products/{productId}
- Pessoa visualiza Produto
  - curl -X POST https://hostName:port/v1/peoples/{personId}/viewed/{productId}
- Pessoa adiciona ao carrinho um Produto
  - curl -X POST https://hostName:port/v1/peoples/{ personId}/add-to-cart/{productId}
 - Pessoa compra um Produto
  - curl -X POST https://hostName:port/v1/peoples/{personId}/bought/{productId}
- Últimas ações de uma pessoa
  - curl -X GET https://hostName:port/v1/peoples /{personId}/actions?limit={limit}
- Últimas 10 compras de uma Pessoa
  - curl -X GET https://hostName:port/v1/peoples/last-buys /{personId}

