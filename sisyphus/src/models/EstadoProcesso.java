package sisyphus.src.models;

public interface EstadoProcesso {
    public void atualizarProcesso(String justificativaRelator, TipoDecisao tipoDecisao);

    public void atribuirProcesso(Professor relator, Colegiado colegiado);

    public void colocarEmJulgamento();

    public void votar(Voto voto);

    public void julgarProcesso();

    public String toString();
}
