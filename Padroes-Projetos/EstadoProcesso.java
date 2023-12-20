import java.util.List;

public interface EstadoProcesso {
    public void proximoEstado(EstadoProcesso estado);

    //1º - Atribuir Processo a um Relator
    public void atribuirProcessoAoRelator(Professor relator) throws Exception;

    //2º - Adicionar Processo a pauta de uma Reuniao
    public void pautarProcesso() throws Exception;

    //3º - Relator adiciona sua Decisão

    //4º - Apregoar Para Julgamento
    public void colocarEmJulgamento() throws Exception;

    //5º - Julgar Processo
    public void julgarProcesso(List<Voto> listaDeVotos) throws Exception;

    public String toString();
}
