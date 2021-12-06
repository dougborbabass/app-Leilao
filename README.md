# app-Leilao
App consumindo uma API (subida em local host) de leilões onde é possivel cadastrar itens a serem leiloados.

No app é possível cadastrar usuários e dar lances no itens de leilão.

Desenvolvido com foco em testes validando as regras de negócio onde:

- O lance não pode ser menor que o último
- O mesmo usuário não pode dar mais que 5 lances 
- O mesmo usuário não pode dar lances seguidos

Além disso é feito a validação das chamadas de API com sucesso e falha.

Testes de unidade, testes de integração e testes de UI

#### API spring boot com perfis separadas para depuração
- Para utilizar a API em "Produção" abrir o terminal na pasta server (localizada dentro do projeto) e executar:

```
java -jar server.jar
```

- Para utilizar a API em "Teste" abrir o terminal na pasta server (localizada dentro do projeto) e executar:

```
java -jar -Dspring.profiles.active=teste server.jar
```

## Rotas disponíveis na API REST
GET /leilao -> Devolve a lista com todos os leilões cadastrados e seus lances.

PUT /leilao/{id}/lance -> Permite a adição de novos lances com base no id do leilão existente, recebendo o lance via corpo da requisição. O objeto para o lance pode ser enviado com a seguinte estrutura.

```json
{
	"usuario" : {
		"id": 1,
		"nome" : "Douglas"
	},
	"valor" : 5000.0
}
```
