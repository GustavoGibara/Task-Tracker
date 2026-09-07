# Task Tracker CLI

CLI em Java para gerenciar tarefas (adicionar, atualizar, remover, marcar status e listar), com persistência em um arquivo JSON local — sem uso de bibliotecas externas.

Projeto baseado no desafio [Task Tracker do roadmap.sh](https://roadmap.sh/projects/task-tracker).

## Funcionalidades

- Adicionar, atualizar e remover tarefas
- Marcar tarefa como em progresso (`in-progress`) ou concluída (`done`)
- Listar todas as tarefas
- Listar tarefas filtrando por status (`todo`, `in-progress`, `done`)
- Persistência automática em `tasks.json` (criado no diretório atual caso não exista)

## Estrutura do projeto

```
src/
├── cli/          # Ponto de entrada (task_cli.java) e leitura dos argumentos de linha de comando
├── entities/     # Classe Task (modelo de domínio)
├── enums/        # Status da tarefa (TODO, IN_PROGRESS, DONE)
├── repository/   # Interface TaskRepository e implementação em JSON (TaskRepositoryJson)
├── service/      # Interface TaskService e implementação (TaskServiceJson) — validações e regras de negócio
├── util/         # JsonInteraction — leitura/escrita manual do arquivo JSON
└── test/         # Classes com main() para testes manuais de repository e service
```

A arquitetura segue uma separação em camadas: `cli` (apresentação) → `service` (regras de negócio e validação) → `repository` (persistência), com interfaces desacoplando contrato de implementação.

## Requisitos

- JDK 21 ou superior

## Como compilar

```bash
javac -d bin $(find src -name "*.java")
```

## Como executar

```bash
java -cp bin cli.task_cli <comando> [argumentos]
```

## Comandos disponíveis

```bash
# Adicionar uma nova tarefa
java -cp bin cli.task_cli add "Comprar mantimentos"

# Atualizar a descrição de uma tarefa
java -cp bin cli.task_cli update 1 "Comprar mantimentos e cozinhar"

# Remover uma tarefa
java -cp bin cli.task_cli remove 1

# Marcar tarefa como em progresso
java -cp bin cli.task_cli mark-in-progress 1

# Marcar tarefa como concluída
java -cp bin cli.task_cli mark-done 1

# Listar todas as tarefas
java -cp bin cli.task_cli list

# Listar tarefas por status
java -cp bin cli.task_cli list done
java -cp bin cli.task_cli list todo
java -cp bin cli.task_cli list in-progress

# Ajuda
java -cp bin cli.task_cli --help
```

## Estrutura de uma tarefa

Cada tarefa é armazenada em `tasks.json` com os seguintes campos:

| Campo         | Descrição                                      |
|---------------|-------------------------------------------------|
| `id`          | Identificador único da tarefa                   |
| `description` | Descrição da tarefa                             |
| `status`      | `TODO`, `IN_PROGRESS` ou `DONE`                 |
| `createdAt`   | Data/hora de criação                            |
| `updatedAt`   | Data/hora da última atualização                 |

## Limitações conhecidas

- O serializador/parser de JSON é feito manualmente (sem bibliotecas externas, por restrição do desafio) e ainda não escapa caracteres especiais (aspas, vírgulas, quebras de linha) na descrição da tarefa.
