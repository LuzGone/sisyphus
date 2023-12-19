package sisyphus.src.models;
public class Assunto {

    private String assunto;

    public Assunto(String assunto) {
        this.assunto = assunto;
    }

    @Override
    public String toString(){
        return this.assunto;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }
}
