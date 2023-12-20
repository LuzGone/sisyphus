public class EmAndamento implements StatusReuniao{
    
    private Reuniao reuniao;

    public EmAndamento(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public void proximoStatus(StatusReuniao status){
        this.reuniao.setStatus(status);
    };

    //1º - Iniciar Reuniao
    public void inicarReuniao() throws Exception{
        if(this.reuniao.getStatus() instanceof Programada){
            this.proximoStatus(new EmAndamento(this.reuniao));
            System.out.println("Reunião iniciada");
        }else{
            throw new Exception("Reunião só pode ser iniciada enquanto estiver como Programada.");
        }
    };

    //2º - Encerrar Reuniao
    public void encerrarReuniao() throws Exception{
        if(this.reuniao.getStatus() instanceof EmAndamento){
            this.proximoStatus(new Encerrada(this.reuniao));
            System.out.println("Reunião encerrada");
        }else{
            throw new Exception("Reunião só pode ser encerrada enquanto estiver como EmAndamento.");
        }
    };

    @Override
    public String toString() {
        return "Em Andamento";
    }
}
