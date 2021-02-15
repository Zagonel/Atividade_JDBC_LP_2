package Persistencia;

public class PersistenciaException extends Exception {

    public PersistenciaException() {
        super("Erro na manipulação do banco de dados");
    }

    public PersistenciaException(String msg) {
        super(msg);
    }
}
