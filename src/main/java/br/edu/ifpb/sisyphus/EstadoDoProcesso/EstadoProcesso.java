package src.main.java.br.edu.ifpb.sisyphus.EstadoDoProcesso;

public interface EstadoProcesso {
    //1º - Atribuir Processo a um Relator
    public void atribuirProcessoAoRelator(Professor relator, Colegiado colegiado);

    //2º - Adicionar Processo a pauta de uma Reuniao
    public void pautarProcesso();

    //3º - Relator adiciona sua Decisão
    public void adicionarDecisaoRelator(String justificativaRelator, TipoDecisao tipoDecisao);

    //4º - Apregoar Para Julgamento
    public void colocarEmJulgamento();

    //5º - Julgar Processo
    public void julgarProcesso(List<Voto> listaDeVotos);

    public String toString();
}
