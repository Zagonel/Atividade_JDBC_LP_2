package Persistencia;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Vo.CursoVO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO extends DAO {

    private PreparedStatement comandoIncluir;
    private PreparedStatement comandoExcluir;
    private PreparedStatement comandoAlterar;
    private PreparedStatement comandoBuscarPorCodigo;
    private PreparedStatement comandoBuscarPorNome;

    public CursoDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);
        try {
            this.comandoIncluir = conexao.IniciarConexao().prepareStatement("INSERT INTO curso (nome) VALUES (?)");
            this.comandoAlterar = conexao.IniciarConexao().prepareStatement("UPDATE curso SET nome=? WHERE codigo = ?");
            this.comandoExcluir = conexao.IniciarConexao().prepareStatement("SELECT * FROM curso WHERE codigo = ?");
            this.comandoBuscarPorNome = conexao.IniciarConexao().prepareStatement("SELECT * FROM curso WHERE UPPER(nome) LIKE ? ORDER BY NOME LIMIT 10*");
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao iniciar a camada de percistencia -" + e.getMessage());
        }
    }

    public int incluir(CursoVO cursoVo) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoIncluir.setString(1, cursoVo.getNome());
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao incluir novo curso − " + e.getMessage());
        }
        return retorno;
    }

    public int excluir(int codigo) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoExcluir.setInt(1, codigo);
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao excluir curso − " + e.getMessage());
        }
        return retorno;
    }

    public int alterar(CursoVO cursoVO) throws PersistenciaException {
        int retorno = 0;

        try {
            comandoAlterar.setString(1, cursoVO.getNome());
            comandoAlterar.setInt(2, cursoVO.getCodigo());

            retorno = comandoAlterar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao alterar o curso" + e.getMessage());
        }
        return retorno;
    }

    public CursoVO BuscarPorCodigo(int codigo) throws PersistenciaException {
        CursoVO cursoVO = null;
        try {
            comandoBuscarPorCodigo.setInt(1, codigo);
            ResultSet rs = comandoBuscarPorCodigo.executeQuery();
            if (rs.next()) {
                cursoVO = new CursoVO();
                cursoVO.setCodigo(rs.getInt("codigo"));
                cursoVO.setNome(rs.getString("nome").trim());
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro na seleção por codigo − " + e.getMessage());
        }
        return cursoVO;
    }

    public List<CursoVO> buscarPorNome(String nome) throws PersistenciaException {
        List<CursoVO> listaCurso = new ArrayList<>();
        CursoVO cursoVO;

        try {
            comandoBuscarPorNome.setString(1, "%" + nome.trim().toUpperCase() + "%");
            ResultSet rs = comandoBuscarPorNome.executeQuery();
            while (rs.next()) {
                cursoVO = new CursoVO();
                cursoVO.setCodigo(rs.getInt("codigo"));
                cursoVO.setNome(rs.getString("nome"));
                listaCurso.add(cursoVO);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro na seleção por nome − " + e.getMessage());
        }
        return listaCurso;
    }

}
