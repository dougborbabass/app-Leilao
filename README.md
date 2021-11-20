# app-Leilao
App consumindo uma API (subida em local host) de leilões onde é possivel cadastrar itens a serem leiloados.

No app é possível cadastrar usuários e dar lances no itens de leilão.

Desenvolvido com foco em testes validando as regras de negócio onde:

- O lance não pode ser menor que o último
- O mesmo usuário não pode dar mais que 5 lances 
- O mesmo usuário não pode dar lances seguidos

Além disso é feito a validação das chamadas de API com sucesso e falha.

Testes em cada unidade, inicialmente desenvolvido com TDD.

Uso do mockito para dependencias do android e desacomplamento.

# API spring boot
- Para utilizar a API abrir o terminal na pasta server (localizada dentro do projeto) e executar
'''cmd
java -jar server.jar
