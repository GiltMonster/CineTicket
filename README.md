# CineTicket

## O que é o projeto e qual seu objetivo?

O projeto em si é um e-commerce de venda de ingressos de cinema cujo objetivo é facilitar o ato de ir ao cinema. E se ter uma reserva antecipada de seu filme muito antes aguardado.

### Arquivos:
O projeto foi dividido em pastas para organização, do front-end e do back-end, e o armazenamento do script de banco de dados, ao baixar o repositório vira as pastas:

* **`Front-end`**
* **`Back-end`**
* **`Script-SQL`**

## Sobre o `Front-end`: 
O Front-end é feito em [React](https://react.dev/), e por isso é um projeto com variadas dependências, para evitar a falta delas tem que se rodar algum package-manager, não importa se for o [Yarn](https://yarnpkg.com/) ou o próprio [npm](https://www.npmjs.com/) pesa-os para buscar o pacote (node_modules) e após isso algumas coisas que se tem que fazer antes de tentar rodar o front-end:
>Nota: O `Front-end` acaba tendo um grupo de dependencias sua arvore de dependências atuais se encontra atualmente assim : 

```shell
front@0.1.0 /Users/lucas.scampos/Desktop/PI-4-Semestre/front-end
|── @testing-library/jest-dom@5.16.5
|── @testing-library/react@13.4.0
|── @testing-library/user-event@13.5.0
|── axios@1.4.0
|── date-fns@2.30.0
|── react-dom@18.2.0
|── react-icons@4.8.0
|── react-router-dom@6.11.2
|── react-scripts@5.0.1
|── react@18.2.0
|── web-vitals@2.1.4
```
Além das suas dependências, você vai precisar das chaves da API de filmes que utilizamos. Que é a do [TMDB](https://www.themoviedb.org/) para preencher com informações dos filmes, crie uma conta na plataforma e peça as chaves de acesso:
- Documentação da api:
	> [Docs TMDB_API](https://developer.themoviedb.org/docs)

- Após conseguir as chaves, crie um arquivo no caminho `front-end/src/apiKeys.js` para colocar as chaves, dentro do arquivo você deve exportar as duas constantes:
```js
export const keyV3  =  "sua_chaveV3";
export const keyV4  =  "sua_chaveV4";
```

## Sobre o `Back-end`:
O Back-end é feito em Java, utilizando o framework SpringBoot, fazendo com que a comunicação do front-end com o banco de dados seja possível, para utilizar, não se esquecer de mudar o `application.properties` localizada em:
> **back-end/src/main/resources/application.properties**

## Desenvolvedores:


|          |               **GitHub**             |
|----------------|-----------------------------|
| [![@GiltMonster](https://avatars.githubusercontent.com/u/71074779?s=64&v=4)](https://github.com/GiltMonster)   |      [**GiltMonster**](https://github.com/GiltMonster) - Lucas Santos 🚁
|[![@RudrigoPereira](https://avatars.githubusercontent.com/u/45149332?s=64&v=4)](https://github.com/RudrigoPereira)|[**RudrigoPereira**](https://github.com/RudrigoPereira) -  Rodrigo Ferreira Pereira 🎱
|[![@yurimcf](https://avatars.githubusercontent.com/u/86640514?s=64&v=4)](https://github.com/yurimcf)|[**yurimcf** ](https://github.com/yurimcf) - Yuri Mathaus 🧙‍♂️
[![@RimuruTempest267](https://avatars.githubusercontent.com/u/100353637?s=64&v=4)](https://github.com/RimuruTempest267)| [**RimuruTempest267**](https://github.com/RimuruTempest267) - Sérgio Lima Borges 💧

## Agradecimentos:
Agradeço aos meus colegas que participaram do projeto, só vocês sabem quantas noites de sono foram jogadas no lixo e as dificuldades que encontramos no decorrer do desenvolvimento deste projeto. Que com grande esforço entregamos um resultado excelente, obrigado a todos

----

# CineTicket

### What is the project and what is its objective?

The project itself is an e-commerce for the sale of cinema tickets whose objective is to facilitate the act of going to the cinema. What if you have an advance booking of your long awaited movie.

## About `Front-end`:
The Front-end is made in [React](https://react.dev/), and that's why it's a project with several dependencies, to avoid missing them you have to run some package-manager, it doesn't matter if it's the [Yarn](https://yarnpkg.com/) or [npm](https://www.npmjs.com/) weighs them to fetch the package (node_modules) and after that some things to do do before trying to run the front-end:
>Note: The `Front-end` ends up having a group of dependencies its current dependency tree currently looks like this:
```shell
front@0.1.0 /Users/lucas.scampos/Desktop/PI-4-Semestre/front-end
|── @testing-library/jest-dom@5.16.5
|── @testing-library/react@13.4.0
|── @testing-library/user-event@13.5.0
|── axios@1.4.0
|── date-fns@2.30.0
|── react-dom@18.2.0
|── react-icons@4.8.0
|── react-router-dom@6.11.2
|── react-scripts@5.0.1
|── react@18.2.0
|── web-vitals@2.1.4
```
In addition to your dependencies, you'll need the API keys for the movies we use. Which is the [TMDB](https://www.themoviedb.org/) to fill in with movie information, create an account on the platform and ask for the access keys:
- API documentation:
> [TMDB_API Docs](https://developer.themoviedb.org/docs)

- After getting the keys, create a file in the path `front-end/src/apiKeys.js` to place the keys, inside the file you must export the two constants:

```js
export const keyV3  =  "sua_chaveV3";
export const keyV4  =  "sua_chaveV4";
```

## About the `Backend`:
The Back-end is made in Java, using the SpringBoot framework, making the front-end communication with the database possible, to use it, don't forget to change the `application.properties` located at:
> **backend/src/main/resources/application.properties**

## Thanks:
I thank my colleagues who participated in the project, only you know how many nights of sleep were thrown away and the difficulties we encountered during the development of this project. That with great effort we delivered an excellent result, thank you all
