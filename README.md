# table-api

Criada por: Willian Araujo Pilar
Contatos:
    Email: willianpilar@hotmail.com
    Celular: 11 949096639

Java version: 11
Spring version: 2.7.1

Para executar a API, rodar no diretório do projeto o comando: mvn spring-boot:run
Para abrir o Swagger, no seu navegador de preferência abra a seguinte URL: mvn spring-boot:run

Objetivo: Receber uma lista de números, validar se é possível criar uma Matriz com linhas e colunas iguais a partir dos números contidos nessa list - Caso seja possível, criar a Matriz e rotacionar os números em sentido horário

Entrada do programa: A requisição deve ser um JSON com uma String identificada como "numbers", na qual o conteúdo deve ser números separados por vírgula.

Exemplo de entradas Válidas:
    {
        "numbers" : "1, 2, 3, 4"
    }

    {
        "numbers" : "-1, 2, -3, 4"
    }

    {
        "numbers" : "1, 2, 3, 4, 5, -6, 7, 8, 9, 10, -11, 12, 13, 14, 15, 16"
    }

    {
        "numbers" : "14, 32, 23, 4, 5, -6, 7, 85, 9, 10, -11, 132, 13, 14, 15, 216"
    }


Resposta do programa:
    Em caso de sucesso - Programa retornara Código HTTP 200 (OK) e a Matriz rotacionada
    Em caso de erro por requisição - Programa retornara Código HTTP 400 (Bad Request) e uma Matriz vázia
    Em caso de erro interno - Programa retornara Código HTTP 500 (Internal Server Error) e uma Matriz vázia 

Foram realizados testes unitários da aplicação e testes manuais.
Cenários avaliados:
    1 - O programa é capaz de reconhecer a entrada e transformar em lista
    2 - O programa é capaz de reconhecer números negativos
    3 - O programa rotaciona essa lista no sentido horário
    4 - O programa cria uma primeira Matriz com as bordas rotacionadas
    5 - Caso tenha mais números para serem rotacionados além da borda, o programa é capaz de rotacionálos
    6 - O programa não rotaciona o número do centro em caso de quantidade total ser ímpar
    7 - O programa é capaz de tratar requisições inválidas
    8 - O programa é capaz de tratar erros

Nos testes foram utilizados diversas entrada diferentes - algumas de exmplo:
    Entradas com sucesso:
        {
            "numbers" : "1, 2, 3, 4"
        }

        {
            "numbers" : "-1, 2, -3, 4"
        }

        {
            "numbers" : "1, 2, 3, 4, 5, -6, 7, 8, 9, 10, -11, 12, 13, 14, 15, 16"
        }

        {
            "numbers" : "14, 32, 23, 4, 5, -6, 7, 85, 9, 10, -11, 132, 13, 14, 15, 216"
        }

        {
            "numbers" : "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36"
        }

        {
            "numbers" : "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49"
        }

        {
            "numbers" : "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64"
        }
    
    Entradas com erro:
        {
            "numbers" : "1"
        }

        {
            "numbers" : "-1, 2, -3, 4, 7"
        }

        {
            "numbers" : "1, 2, 3, 4, 5, -6, 7, 8, 9, 10, -11, 12, 13, 14, 15, 16, 12, 73"
        }

        {
            "numbers" : "4, 5, -6, 7, 85, 9, 10, -11, 132, A, 14, 15, 216"
        }


Qualquer dúvida entrar em contato por email ou celular - Contatos disponibilizados no inicío da documentação.
Obrigado!
