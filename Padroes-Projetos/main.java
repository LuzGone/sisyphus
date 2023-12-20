import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) {
        System.out.println("=====================");
        System.out.println("Bem Vindo ao Sisyphus");
        System.out.println("=====================");
        
        //Criando Assuntos
        System.out.println("\n=====================");
        System.out.println("Assuntos Disponíveis:");
        AssuntoRepositorio assuntoRepositorio = AssuntoRepositorio.getInstancia();
        System.out.println(assuntoRepositorio);
        System.out.println("=====================");
        
        //Criando Cursos
        System.out.println("\n=====================");
        System.out.println("Criando Cursos");
        CursoRepositorio cursoRepositorio = CursoRepositorio.getInstancia();
        System.out.println(cursoRepositorio);
        System.out.println("=====================");
        
        //Criando Alunos
        System.out.println("\n=====================");
        System.out.println("Criando Alunos");
        AlunoRepositorio alunoRepositorio = AlunoRepositorio.getInstancia();
        System.out.println(alunoRepositorio);
        System.out.println("=====================");
        
        //Criando Professores
        System.out.println("\n=====================");
        System.out.println("Criando Professores");
        ProfessorRepositorio professorRepositorio = ProfessorRepositorio.getInstancia();
        System.out.println(professorRepositorio);
        System.out.println("=====================");
        
        //Criando Coordenadores
        System.out.println("\n=====================");
        System.out.println("Criando Coordenadores");
        CoordenadorRepositorio coordenadorRepositorio = CoordenadorRepositorio.getInstancia();
        System.out.println(coordenadorRepositorio);
        System.out.println("=====================");
        
        //Criando Colegiados
        System.out.println("\n=====================");
        System.out.println("Criando Colegiados");
        ColegiadoRepositorio colegiadoRepositorio = ColegiadoRepositorio.getInstancia();
        System.out.println(colegiadoRepositorio);
        System.out.println("=====================");
        
        //Criar Repositorio de Processos
        System.out.println("\n=====================");
        System.out.println("Criando Repositorio de Processos");
        ProcessoRepositorio processoRepositorio = ProcessoRepositorio.getInstancia();
        System.out.println("Repositorio de Processos Criado");
        System.out.println("=====================");
        
        //1º Aluno Cria um processo
        System.out.println("\n=====================");
        System.out.println("Criando Processo Como Aluno");
        Aluno aluno1 = alunoRepositorio.buscarAluno("20211370040");
        Processo processo = new Processo(
            aluno1, 
            assuntoRepositorio.getRepositorio().get(0), 
            "Quero Renovar Minha Matricula", 
            colegiadoRepositorio.buscarColegiadoPorCurso(aluno1.getCurso()));
        processoRepositorio.adicionarProcesso(processo);
        aluno1.adicionarProcesso(processo);
        System.out.println("Processo Criado");
        System.out.println(processo);
        System.out.println("=====================");

        //2° Coordenador Delega Processo à um Professor
        System.out.println("\n=====================");
        System.out.println("Coordenador Delegando Processo à um Professor");
        Coordenador coordenador = coordenadorRepositorio.getRepositorio().get(0);
        Professor professor1 = professorRepositorio.buscarProfessor("123457");
        coordenador.delegarProcesso(processo, professor1);
        System.out.println(processo);
        System.out.println("Processo Delegado\n");
        System.out.println("=====================");

        //3° Coordenador Cria uma Reunião do Colegiado
        System.out.println("\n=====================");
        System.out.println("Coordenador Criando Reunião do Colegiado");
        List<Processo> processosParaJulgar = new ArrayList<Processo>();
        processosParaJulgar.add(processo);
        Reuniao reuniao = new Reuniao(new Date(), processosParaJulgar, colegiadoRepositorio.buscarColegiadoPorCurso(aluno1.getCurso()));
        System.out.println(reuniao);
        reuniao.pautarProcessosParaJulgamento();
        System.out.println(processo);
        System.out.println("=====================");
        
        //4° Professor Relator Adiciona sua Decisão
        System.out.println("\n=====================");
        System.out.println("Professor Relator Adicionando sua Decisão");
        try{
            processo.adicionarDecisao("Não.", TipoDecisao.INDEFERIDO);
            System.out.println(processo);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("=====================");

        //5° Coordenador Inicia uma Reuniao
        System.out.println("\n=====================");
        System.out.println("Coordenador Iniciando uma Reuniao");
        reuniao.inicarReuniao();
        System.out.println(reuniao);
        System.out.println(processo);
        System.out.println("=====================");

        //6º Membros do Colegiado Votam no Processo
        System.out.println("\n=====================");
        System.out.println("Membros do Colegiado Votando no Processo");
        List<Voto> listaDeVotos = new ArrayList<Voto>();
        Voto voto1 = new Voto(professorRepositorio.buscarProfessor("123458"), processo, TipoVoto.DIVERGENTE);
        Voto voto2 = new Voto(professorRepositorio.buscarProfessor("123459"), processo, TipoVoto.DIVERGENTE);
        Voto voto3 = new Voto(professorRepositorio.buscarProfessor("123460"), processo, TipoVoto.DIVERGENTE);
        Voto voto4 = new Voto(coordenador.getProfessor(), processo, TipoVoto.DIVERGENTE);
        listaDeVotos.add(voto1);
        listaDeVotos.add(voto2);
        listaDeVotos.add(voto3);
        listaDeVotos.add(voto4);
        try{
            processo.julgarProcesso(listaDeVotos);
            System.out.println(processo);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("=====================");

        //7º Coordenador Encerra a Reuniao
        System.out.println("\n=====================");
        System.out.println("Coordenador Encerrando a Reuniao");
        reuniao.encerrarReuniao();
        System.out.println("=====================");
    }
}
