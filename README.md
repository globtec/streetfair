# streetfair

O projeto `streetfair` é uma API RESTful para manipular os dados das feiras de São Paulo.

## Pré-requisitos

- [Docker](https://www.docker.com/)

## Tech Stack

- Docker
- Java 11
- Spring Boot
- Gradle
- MySQL

## Como executar o projeto em seu ambiente local?

### Definindo as variáveis de ambiente

O projeto utiliza um arquivo `.env` para armazenar as variáveis de ambiente, este processo evita o armazenamento de credenciais nos arquivos `*.properties` que são mantidos no repositório para executar o projeto em um determinado ambiente.

Para criar o arquivo `.env` execute o seguinte comando no diretório raiz do projeto, não esqueça de definir a senha para a variável de ambiente `DATABASE_PASSWORD`: 

```
cat << 'EOF' > .env
APP_ENV=development
DATABASE_HOST=database
DATABASE_PORT=3306
DATABASE_NAME=streetfair
DATABASE_USERNAME=streetfair
DATABASE_PASSWORD=
EOF
```

Em seguida, execute o seguinte comando para definir as variáveis de ambiente na sessão de seu terminal:

```
source .env
```

### Construindo a imagem Docker do projeto

Para construir a imagem do projeto execute o seguinte comando:
```
docker-compose build  
```

### Inicializando o projeto

Após a criação do arquivo `.env` você poderá iniciar os _containers_ do projeto usando o seguinte comando:
```
docker-compose up --detach
```

Neste ponto, você pode visualizar a saúde do projeto no endereço http://localhost:8080/actuator/health.

### Populando o banco de dados

O projeto utilizará os dados disponibilizados pela prefeitura de São Paulo.

http://www.prefeitura.sp.gov.br/cidade/secretarias/upload/chamadas/feiras_livres_1429113213.zip

Para realizar a importação dos dados CSV para o banco de dados será necessário executar o _script_ `csv-to-db.sh` que está disponível no _container_ do banco de dados utilizando o seguinte comando:

```
docker-compose exec database bash csv-to-db.sh
```

### Visualizando os logs

O projeto realiza o armazenamento de _logs_ estruturados em arquivos de texto no diretório `log`.

Listando os arquivos de _log_:
```
docker-compose exec app ls -l log
```

Você também pode visualizar os _logs_ pela saída do _container_:
```
docker-compose logs -f app
```

## Cobertura de testes do projeto

Execute a seguinte instrução para criar o relatório de cobertura de testes do projeto:
```
docker container run --rm -it \
    -v "${PWD}":/home/gradle \
    gradle:7.0.2-jdk11 gradle test jacocoTestReport
```

Neste ponto, será disponibilizado em seu ambiente local o relatório no seguinte caminho:

```
build/reports/jacoco/html/index.html 
```

## Maturidade da API

O projeto é uma API RESTful que contempla o nível mais alto de maturidade segundo o modelo desenvolvido por Leonard Richardson.

Saiba mais:

- https://martinfowler.com/articles/richardsonMaturityModel.html

## Consumindo a API

### Cadastrando uma feira

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[POST] /fairs`.

```
curl --location --request POST 'localhost:8080/fairs' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "2",
    "lat": "-46574716",
    "lon": "-23584852",
    "setcems": "355030893000035",
    "weightingArea": "3550308005042",
    "districtCode": "95",
    "district": "VILA PRUDENTE",
    "subprefectureCode": "29",
    "subprefecture": "VILA PRUDENTE",
    "region5": "Leste",
    "region8": "Leste 1",
    "name": "PRACA SANTA HELENA",
    "registry": "4045-2",
    "address": "RUA JOSE DOS REIS",
    "number": "909.000000",
    "neighborhood": "VL ZELINA",
    "landmark": "RUA OLIVEIRA GOUVEIA"
}'
```

Em caso de sucesso teremos o _HTTP status code_ `201 Created` e o seguinte `json` como resposta:

```json
{
    "id": "2",
    "lat": "-46574716",
    "lon": "-23584852",
    "setcems": "355030893000035",
    "weightingArea": "3550308005042",
    "districtCode": "95",
    "district": "VILA PRUDENTE",
    "subprefectureCode": "29",
    "subprefecture": "VILA PRUDENTE",
    "region5": "Leste",
    "region8": "Leste 1",
    "name": "PRACA SANTA HELENA",
    "registry": "4045-2",
    "address": "RUA JOSE DOS REIS",
    "number": "909.000000",
    "neighborhood": "VL ZELINA",
    "landmark": "RUA OLIVEIRA GOUVEIA",
    "_links": {
        "self": {
            "href": "http://localhost:8080/fairs/4045-2"
        }
    }
}
```

Em caso de falha podemos ter os seguintes _HTTP status code_:

| HTTP Status Code |
| ------ |
| 400 Bad Request| 
| 409 Conflict | 
| 500 Internal Server Error |

## Consultando uma feira

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[GET] /fairs/{registry}`.

```
curl --location --request GET 'localhost:8080/fairs/4045-2'
```

Em caso de sucesso teremos o _HTTP status code_ `200 OK` e o seguinte `json` como resposta:

```json
{
    "id": "2",
    "lat": "-46574716",
    "lon": "-23584852",
    "setcems": "355030893000035",
    "weightingArea": "3550308005042",
    "districtCode": "95",
    "district": "VILA PRUDENTE",
    "subprefectureCode": "29",
    "subprefecture": "VILA PRUDENTE",
    "region5": "Leste",
    "region8": "Leste 1",
    "name": "PRACA SANTA HELENA",
    "registry": "4045-2",
    "address": "RUA JOSE DOS REIS",
    "number": "909.000000",
    "neighborhood": "VL ZELINA",
    "landmark": "RUA OLIVEIRA GOUVEIA",
    "_links": {
        "self": {
            "href": "http://localhost:8080/fairs/4045-2"
        }
    }
}
```

Em caso de falha podemos ter os seguintes _HTTP status code_:

| HTTP Status Code |
| ------ |
| 404 Not Found | 
| 500 Internal Server Error |

## Alterando uma feira

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[PUT] /fairs/{registry}`.

```
curl --location --request PUT 'localhost:8080/fairs/4045-2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "2",
    "lat": "-46574716",
    "lon": "-23584852",
    "setcems": "355030893000035",
    "weightingArea": "3550308005042",
    "districtCode": "95",
    "district": "VILA PRUDENTE",
    "subprefectureCode": "29",
    "subprefecture": "VILA PRUDENTE",
    "region5": "Leste",
    "region8": "Leste 1",
    "name": "PRACA SANTA HELENA",
    "address": "RUA JOSE DOS REIS",
    "number": "909.000000",
    "neighborhood": "VILA ZELINA",
    "landmark": "RUA OLIVEIRA GOUVEIA"
}'
```

Em caso de sucesso teremos o _HTTP status code_ `200 OK` e o seguinte `json` como resposta:

```json
{
    "id": "2",
    "lat": "-46574716",
    "lon": "-23584852",
    "setcems": "355030893000035",
    "weightingArea": "3550308005042",
    "districtCode": "95",
    "district": "VILA PRUDENTE",
    "subprefectureCode": "29",
    "subprefecture": "VILA PRUDENTE",
    "region5": "Leste",
    "region8": "Leste 1",
    "name": "PRACA SANTA HELENA",
    "registry": "4045-2",
    "address": "RUA JOSE DOS REIS",
    "number": "909.000000",
    "neighborhood": "VILA ZELINA",
    "landmark": "RUA OLIVEIRA GOUVEIA",
    "_links": {
        "self": {
            "href": "http://localhost:8080/fairs/4045-2"
        }
    }
}
```

Em caso de falha podemos ter os seguintes _HTTP status code_:

| HTTP Status Code |
| ------ |
| 400 Bad Request| 
| 404 Not Found | 
| 500 Internal Server Error |

## Consultando todas feiras

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[GET] /fairs`.

```
curl --location --request GET 'localhost:8080/fairs'
```

Em caso de sucesso teremos o _HTTP status code_ `200 OK` e o seguinte `json` como resposta:

```json
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/fairs{?district,region5,name,neighborhood}",
            "templated": true
        }
    },
    "fairs": [
        {
            "id": "2",
            "lat": "-46574716",
            "lon": "-23584852",
            "setcems": "355030893000035",
            "weightingArea": "3550308005042",
            "districtCode": "95",
            "district": "VILA PRUDENTE",
            "subprefectureCode": "29",
            "subprefecture": "VILA PRUDENTE",
            "region5": "Leste",
            "region8": "Leste 1",
            "name": "PRACA SANTA HELENA",
            "registry": "4045-2",
            "address": "RUA JOSE DOS REIS",
            "number": "909.000000",
            "neighborhood": "VILA ZELINA",
            "landmark": "RUA OLIVEIRA GOUVEIA",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/fairs/4045-2"
                }
            }
        }
    ]
}
```

Em caso de falha temos os seguintes cenários:

| HTTP Status Code |
| ------ |
| 500 Internal Server Error |

## Consultando com filtros

Você pode filtrar pelos campos:

- registry
- region5
- name
- neighborhood

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[GET] /fairs{?district,region5,name,neighborhood}` filtrando por `region5`.

```
curl --location --request GET 'localhost:8080/fairs?region5=leste'
```

Em caso de sucesso teremos o _HTTP status code_ `200 OK` e o seguinte `json` como resposta:

```json
{
    "_links": {
        "self": {
            "href": "http://localhost:8080/fairs?region5=leste{&district,name,neighborhood}",
            "templated": true
        }
    },
    "fairs": [
        {
            "id": "2",
            "lat": "-46574716",
            "lon": "-23584852",
            "setcems": "355030893000035",
            "weightingArea": "3550308005042",
            "districtCode": "95",
            "district": "VILA PRUDENTE",
            "subprefectureCode": "29",
            "subprefecture": "VILA PRUDENTE",
            "region5": "Leste",
            "region8": "Leste 1",
            "name": "PRACA SANTA HELENA",
            "registry": "4045-2",
            "address": "RUA JOSE DOS REIS",
            "number": "909.000000",
            "neighborhood": "VILA ZELINA",
            "landmark": "RUA OLIVEIRA GOUVEIA",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/fairs/4045-2"
                }
            }
        }
    ]
}
```

Em caso de falha temos os seguintes cenários:

| HTTP Status Code |
| ------ |
| 500 Internal Server Error |

## Excluindo uma feira

Segue abaixo uma instrução `curl` para exemplificar uma solicitação ao _endpoint_ `[DELETE] /fairs`.

```
curl --location --request DELETE 'localhost:8080/fairs/4045-2'
```

Em caso de sucesso teremos o _HTTP status code_ `204 No Content`

Em caso de falha temos os seguintes cenários:

| HTTP Status Code |
| ------ |
| 500 Internal Server Error |