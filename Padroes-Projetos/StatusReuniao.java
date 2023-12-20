public interface StatusReuniao {
    public void proximoStatus(StatusReuniao status);

    //1º - Iniciar Reuniao
    public void inicarReuniao() throws Exception;

    //2º - Encerrar Reuniao
    public void encerrarReuniao() throws Exception;
    
}
