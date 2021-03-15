package Persistencia;

import Vo.DisciplinaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaDAO extends DAO {

    private PreparedStatement comandoIncluir;
    private PreparedStatement comandoExcluir;
    private PreparedStatement comandoAlterar;
    private PreparedStatement comandoBuscarPorCodigo;
    private PreparedStatement comandoBuscarPorNome;
    private PreparedStatement comandoBuscarTudo;
    private CursoDAO cursoDAO;

    public DisciplinaDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);
        this.cursoDAO = new CursoDAO(conexao);

        try {
            this.comandoIncluir = conexao.IniciarConexao().prepareStatement("INSERT INTO disciplina (nome, semestre, cargahoraria, curso) VALUES (?,?,?,?)");
            this.comandoAlterar = conexao.IniciarConexao().prepareStatement("UPDATE disciplina SET nome=?, semestre=?, cargahoraria=?, curso=? WHERE codigo=?");
            this.comandoExcluir = conexao.IniciarConexao().prepareStatement("DELETE FROM disciplina WHERE codigo=?");
            this.comandoBuscarPorCodigo = conexao.IniciarConexao().prepareStatement("SELECT * FROM disciplina WHERE codigo=?");
            this.comandoBuscarPorNome = conexao.IniciarConexao().prepareStatement("SELECT * FROM disciplina WHERE UPPER(nome) LIKE ? ORDER BY NOME LIMIT 10*");
            this.comandoBuscarTudo = conexao.IniciarConexao().prepareStatement("SELECT * FROM disciplina");
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao iniciar a percistencia " + e.getMessage());
        }
    }

    public int incluir(DisciplinaVO disciplinaVO) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoIncluir.setString(1, disciplinaVO.getNome());
            comandoIncluir.setInt(2, disciplinaVO.getSemestre());
            comandoIncluir.setInt(3, disciplinaVO.getCargahoraria());
            comandoIncluir.setInt(4, disciplinaVO.getCurso().getCodigo());
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao incluir nova disciplina − " + ex.getMessage());
        }
        return retorno;
    }

    public int alterar(DisciplinaVO disciplinaVO) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoAlterar.setString(1, disciplinaVO.getNome());
            comandoAlterar.setInt(2, disciplinaVO.getSemestre());
            comandoAlterar.setInt(3, disciplinaVO.getCargahoraria());
            comandoAlterar.setInt(4, disciplinaVO.getCurso().getCodigo());
            comandoAlterar.setInt(4, disciplinaVO.getCodigo());
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao alterar a disciplina − " + ex.getMessage());
        }
        return retorno;
    }

    public int excluir(int codigo) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoExcluir.setInt(1, codigo);
            retorno = comandoExcluir.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao excluir a disciplina" + e.getMessage());
        }
        return retorno;
    }

    public DisciplinaVO buscarPorCodigo(int codigo) throws PersistenciaException {
        DisciplinaVO disciplinaVO = null;

        try {
            comandoBuscarPorCodigo.setInt(1, codigo);
            ResultSet rs = comandoBuscarPorCodigo.executeQuery();

            while (rs.next()) {
                disciplinaVO = new DisciplinaVO();

                disciplinaVO.setCodigo(rs.getInt("codigo"));
                disciplinaVO.setNome(rs.getString("nome"));
                disciplinaVO.setSemestre(rs.getInt("semestre"));
                disciplinaVO.setCargahoraria(rs.getInt("cargahoraria"));
                disciplinaVO.setCurso(this.cursoDAO.BuscarPorCodigo(codigo));
            }

        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar por codigo da disciplina " + e.getMessage());
        }
        return disciplinaVO;
    }

    public List<DisciplinaVO> buscarPorNome(String nome) throws PersistenciaException {
        List<DisciplinaVO> listaDisciplinas = new ArrayList();
        DisciplinaVO disciplinaVO;

        try {
            comandoBuscarPorNome.setString(1, "%" + nome.trim().toUpperCase() + "%");
            ResultSet rs = comandoBuscarPorNome.executeQuery();
            while (rs.next()) {
                disciplinaVO = new DisciplinaVO();

                disciplinaVO.setCodigo(rs.getInt("codigo"));
                disciplinaVO.setNome(rs.getString("nome"));
                disciplinaVO.setSemestre(rs.getInt("semestre"));
                disciplinaVO.setCargahoraria(rs.getInt("cargahoraria"));
                disciplinaVO.setCurso(this.cursoDAO.BuscarPorCodigo(rs.getInt("curso")));

                listaDisciplinas.add(disciplinaVO);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar por nome");
        }
        return listaDisciplinas;
    }

    public List<DisciplinaVO> buscarListaDisciplina() throws PersistenciaException {
        List<DisciplinaVO> listaDisciplina = new ArrayList<>();
        DisciplinaVO disciplinaVO;

        try {
            ResultSet rs = comandoBuscarTudo.executeQuery();
            while (rs.next()) {
                disciplinaVO = new DisciplinaVO();
                disciplinaVO.setCodigo(rs.getInt("codigo"));
                disciplinaVO.setNome(rs.getString("nome"));
                disciplinaVO.setCurso(this.cursoDAO.BuscarPorCodigo(rs.getInt("curso")));
                disciplinaVO.setCargahoraria(rs.getInt("cargahoraria"));
                disciplinaVO.setSemestre(rs.getInt("semestre"));
                listaDisciplina.add(disciplinaVO);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro na seleção por nome − " + e.getMessage());
        }
        return listaDisciplina;
    }

}
