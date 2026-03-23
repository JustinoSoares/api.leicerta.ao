# ⚖️ Lei Certa AO — API Jurídica Inteligente para Angola

**Lei Certa AO** é uma API moderna que fornece acesso inteligente à legislação angolana, permitindo que aplicações e sistemas consultem, interpretem e utilizem informações legais de forma simples, rápida e contextualizada.

Este projeto foi criado com o objetivo de aproximar o cidadão comum da lei, transformando documentos jurídicos complexos — como constituições, decretos e diários da república — em informações acessíveis, compreensíveis e acionáveis.

---

## 🚀 O que esta API oferece?

* 📚 **Consulta de legislação angolana**
  Acesso estruturado a leis, decretos, constituição e outros documentos oficiais.

* 🔍 **Busca inteligente (RAG)**
  Pesquisa semântica que retorna os trechos mais relevantes com base no contexto da pergunta.

* 🤖 **Interpretação jurídica com IA**
  Responde perguntas em linguagem natural com referências legais directas e orientação prática.

* 🧾 **Referência directa à lei**
  Respostas sempre acompanhadas de artigos e fontes legais específicas.

* 📥 **Acesso a documentos oficiais**
  Obtenha URLs directas para leis e documentos em formato PDF.

* 🏛️ **Orientação prática ao cidadão**
  Sugestões sobre como agir e onde se dirigir com base no problema apresentado.

---

## 🏗️ Arquitectura

```
src/
├── main/
│   ├── java/ao/leicerta/api/
│   │   ├── ApiLeicertaAoApplication.java  ← Ponto de entrada
│   │   ├── config/                        ← Swagger/OpenAPI, CORS
│   │   ├── controller/                    ← REST endpoints
│   │   ├── exception/                     ← Tratamento global de erros
│   │   ├── model/                         ← Entidades JPA e DTOs
│   │   ├── repository/                    ← Spring Data JPA
│   │   └── service/                       ← Lógica de negócio
│   └── resources/
│       ├── application.yml                ← Configurações
│       └── data.sql                       ← Dados de exemplo
└── test/                                  ← Testes unitários
```

**Stack tecnológica:**
* **Spring Boot 3.2** — Core da aplicação
* **Spring Data JPA + H2** — Persistência (facilmente substituível por PostgreSQL)
* **Springdoc OpenAPI** — Documentação interactiva (Swagger UI)
* **Lombok** — Redução de boilerplate

---

## 📡 Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/v1/legislacao` | Lista toda a legislação (com filtro opcional por tipo) |
| `GET` | `/api/v1/legislacao/{id}` | Obtém uma legislação pelo ID |
| `GET` | `/api/v1/legislacao/{id}/artigos` | Lista os artigos de uma legislação |
| `GET` | `/api/v1/legislacao/{id}/artigos/{artigoId}` | Obtém um artigo específico |
| `POST` | `/api/v1/legislacao` | Adiciona nova legislação |
| `DELETE` | `/api/v1/legislacao/{id}` | Remove uma legislação |
| `POST` | `/api/v1/busca` | Busca inteligente por termos legais |
| `GET` | `/api/v1/busca?q=termo` | Busca rápida via URL |
| `POST` | `/api/v1/ia/perguntar` | Pergunta jurídica em linguagem natural |
| `GET` | `/api/v1/documentos/{id}/url` | Obtém URL do documento oficial |

### Tipos de documento suportados
`CONSTITUICAO`, `LEI`, `DECRETO_LEI`, `DECRETO`, `DECRETO_PRESIDENCIAL`, `RESOLUCAO`, `DESPACHO`, `DIARIO_DA_REPUBLICA`

---

## 🚀 Como executar

### Pré-requisitos
- Java 17+
- Maven 3.8+

### Executar localmente

```bash
# Clonar o repositório
git clone https://github.com/JustinoSoares/api.leicerta.ao.git
cd api.leicerta.ao

# Executar
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Documentação interactiva (Swagger UI)
Acesse `http://localhost:8080/swagger-ui.html` para explorar e testar todos os endpoints.

---

## 💡 Exemplos de uso

### Busca inteligente

```bash
curl -X POST http://localhost:8080/api/v1/busca \
  -H "Content-Type: application/json" \
  -d '{"consulta": "direitos do trabalhador", "tipo": "LEI"}'
```

### Pergunta jurídica em linguagem natural

```bash
curl -X POST http://localhost:8080/api/v1/ia/perguntar \
  -H "Content-Type: application/json" \
  -d '{"pergunta": "Tenho direito a férias remuneradas? Quantos dias?"}'
```

### Listar legislação por tipo

```bash
curl "http://localhost:8080/api/v1/legislacao?tipo=CONSTITUICAO"
```

---

## 🧪 Testes

```bash
mvn test
```

---

## ⚠️ Aviso Legal

As informações fornecidas por esta API têm caráter **informativo e educativo**, não substituindo a consulta a um advogado ou especialista jurídico.

---

## 📌 Status do Projecto

🚧 Em desenvolvimento — contribuições e feedback são bem-vindos.

---

## 🤝 Contribuição

Se deseja contribuir com o projecto, seja melhorando a base de dados, sugerindo funcionalidades ou optimizando a arquitectura, sinta-se à vontade para abrir uma issue ou pull request.

---

## 🌍 Visão

Tornar-se uma referência em soluções de **Legal Tech em Angola**, ajudando cidadãos, estudantes e profissionais a compreender e aplicar a lei com mais facilidade.
