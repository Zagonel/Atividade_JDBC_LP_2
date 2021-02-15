package Negocio;

public class NegocioExeption extends Exception {

    public NegocioExeption() {
        super("Erro ocorrido na camada de negocio");
    }

    public NegocioExeption(String msg) {
        super(msg);
    }
}
