package Persistencia;

import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DAO {

    protected ConexaoBD conexao;

    public DAO(ConexaoBD conexao) throws PersistenciaException {
        this.conexao = conexao;
        try {
            this.conexao.IniciarConexao().setAutoCommit(false);
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao configurar conexão");
        }
    }

    public void confirmarTransacao() throws PersistenciaException {
        try {
            this.conexao.IniciarConexao().commit();
            JOptionPane.showMessageDialog(null, "Transação confirmada");
        } catch (SQLException e) {
            throw new PersistenciaException("Transação nao confirmada");
        }
    }

    public void cancelarTransacao() throws PersistenciaException {
        try {
            this.conexao.IniciarConexao().rollback();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao cancelar a transação");
        }
    }

}
