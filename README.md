# Sisyphus
## Projeto de Pweb2 Colegiado - Sisyphus
- Grupo: Jardielen de Sousa, Louise Fernandes, Luiz Gonzaga e Gabriel Macaúbas.

O Objetivo do Projeto é um Sistema de Colegiado.

## Padrões utilizados:

- State:
  - Foi utilizado para resolver o problema de quando o processo altera seus estados, eles podem variar entre: Criado, Distribuido, EmPauta, EmJulgamento, Julgado.
  - Foi utilizado também para resolver o problema dos status da reunião, que podem variar entre: Programada, Iniciada e Encerrada.

- Singleton:
  - Foi utilizado para montar os repositórios das diversas classes utilizadas no projeto, sendo elas: Processo, Aluno, Professor, Assunto, Curso e Coordenador.

