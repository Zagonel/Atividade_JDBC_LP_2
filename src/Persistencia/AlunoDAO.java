package Persistencia;

import Vo.AlunoVO;
import Vo.EnumSexo;
import Vo.EnumUF;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO extends DAO {

    private PreparedStatement comandoIncluir;
    private PreparedStatement comandoExcluir;
    private PreparedStatement comandoAlterar;
    private PreparedStatement comandoBuscarPorMatricula;
    private PreparedStatement comandoBuscarPorNome;
    private PreparedStatement comandoBuscarTudo;
    private CursoDAO cursoDAO;

    public AlunoDAO(ConexaoBD conexao) throws PersistenciaException {
        super(conexao);
        this.cursoDAO = new CursoDAO(conexao);
        try {
            this.comandoIncluir = conexao.IniciarConexao().prepareStatement("INSERT INTO aluno (nome , nomemae , nomepai , sexo, logradouro, numero, bairro, cidade, uf, curso) VALUES (?,?,?,?,?,?,?,?,?,?) ");
            this.comandoAlterar = conexao.IniciarConexao().prepareStatement("UPDATE aluno SET nome=?, nomemae=?, nomepai=?, sexo=?, logradouro=?, numero=?, bairro=?, cidade=?, uf=?, curso=?, WHERE matricula=? ");
            this.comandoExcluir = conexao.IniciarConexao().prepareStatement("DELETE FROM aluno WHERE matricula=? ");
            this.comandoBuscarPorMatricula = conexao.IniciarConexao().prepareStatement("SELECT * FROM aluno WHERE matricula=? ");
            this.comandoBuscarPorNome = conexao.IniciarConexao().prepareStatement("SELECT * FROM aluno WHERE UPPER (nome) LIKE ? ");
            this.comandoBuscarTudo = conexao.IniciarConexao().prepareStatement("SELECT * FROM aluno");
        } catch (SQLException e) {
            throw new PersistenciaException("ERRO ao iniciar camada de percistencia" + e.getMessage());
        }
    }

    public int incluir(AlunoVO alunoVO) throws PersistenciaException {
        int retorno = 0;
        try {
            comandoIncluir.setString(1, alunoVO.getNome());
            comandoIncluir.setString(2, alunoVO.getNomeMae());
            comandoIncluir.setString(3, alunoVO.getNomePai());
            comandoIncluir.setInt(4, alunoVO.getSexo().ordinal());
            comandoIncluir.setString(5, alunoVO.getEndereco().getLogradouro());
            comandoIncluir.setInt(6, alunoVO.getEndereco().getNumero());
            comandoIncluir.setString(7, alunoVO.getEndereco().getBairro());
            comandoIncluir.setString(8, alunoVO.getEndereco().getCidade());
            comandoIncluir.setString(9, alunoVO.getEndereco().getUf().name());
            comandoIncluir.setInt(10, alunoVO.getCurso().getCodigo());
            retorno = comandoIncluir.executeUpdate();
        } catch (SQLException ex) {
            throw new PersistenciaException("Erro ao incluir novo aluno − ERRO ALUNODAO " + ex.getMessage());
        }
        return retorno;
    }

    public int alterar(AlunoVO alunoVO) throws PersistenciaException {
        int retorno = 0;

        try {
            comandoAlterar.setString(1, alunoVO.getNome());
            comandoAlterar.setString(2, alunoVO.getNomeMae());
            comandoAlterar.setString(3, alunoVO.getNomePai());
            comandoAlterar.setInt(4, alunoVO.getSexo().ordinal());
            comandoAlterar.setString(5, alunoVO.getEndereco().getLogradouro());
            comandoAlterar.setInt(6, alunoVO.getEndereco().getNumero());
            comandoAlterar.setString(7, alunoVO.getEndereco().getBairro());
            comandoAlterar.setString(8, alunoVO.getEndereco().getCidade());
            comandoAlterar.setString(9, alunoVO.getEndereco().getUf().name());
            comandoAlterar.setInt(10, alunoVO.getCurso().getCodigo());
            comandoAlterar.setInt(11, alunoVO.getMatricula());
            retorno = comandoAlterar.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao alterar o aluno " + e.getMessage());
        }
        return retorno;
    }

    public int excluir(int matricula) throws PersistenciaException {
        int retorno = 0;

        try {
            comandoExcluir.setInt(1, matricula);
            retorno = comandoExcluir.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao excluir aluno");
        }
        return retorno;
    }

    public AlunoVO BuscaPorMatricula(int matricula) throws PersistenciaException {
        AlunoVO alunoVO = null;

        try {
            comandoBuscarPorMatricula.setInt(1, matricula);
            ResultSet rs = comandoBuscarPorMatricula.executeQuery();

            while (rs.next()) {
                alunoVO = new AlunoVO();

                alunoVO.setMatricula(rs.getInt("matricula"));
                alunoVO.setNome(rs.getString("nome"));
                alunoVO.setNomeMae(rs.getString("nomemae"));
                alunoVO.setNomePai(rs.getString("nomepai"));
                alunoVO.setSexo(EnumSexo.values()[rs.getInt("sexo")]);
                alunoVO.getEndereco().setBairro(rs.getString("bairro"));
                alunoVO.getEndereco().setCidade(rs.getString("cidade"));
                alunoVO.getEndereco().setNumero(rs.getInt("numero"));
                alunoVO.getEndereco().setLogradouro(rs.getString("logradouro"));
                alunoVO.getEndereco().setUf(EnumUF.valueOf(rs.getString("uf")));
                alunoVO.setCurso(this.cursoDAO.BuscarPorCodigo(rs.getInt("curso")));
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar por matricula" + e.getMessage());
        }
        return alunoVO;
    }

    public List<AlunoVO> buscarPorNome(String nome) throws PersistenciaException {
        List<AlunoVO> listaAlunos = new ArrayList();
        AlunoVO aluno;

        try {
            comandoBuscarPorNome.setString(1, "%" + nome.trim().toUpperCase() + "%");
            ResultSet rs = comandoBuscarPorNome.executeQuery();

            while (rs.next()) {
                aluno = new AlunoVO();

                aluno.setMatricula(rs.getInt("matricula"));
                aluno.setNome(rs.getString("nome"));
                aluno.setNomeMae(rs.getString("nomemae"));
                aluno.setNomePai(rs.getString("nomepai"));
                aluno.setSexo(EnumSexo.values()[rs.getInt("sexo")]);
                aluno.getEndereco().setBairro(rs.getString("bairro"));
                aluno.getEndereco().setLogradouro(rs.getString("logradouro"));
                aluno.getEndereco().setCidade(rs.getString("cidade"));
                aluno.getEndereco().setNumero(rs.getInt("numero"));
                aluno.getEndereco().setUf(EnumUF.valueOf(rs.getString("uf")));
                aluno.setCurso(this.cursoDAO.BuscarPorCodigo(rs.getInt("curso")));

                listaAlunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro ao buscar por nome " + e.getMessage());
        }
        return listaAlunos;
    }

    public List<AlunoVO> buscarListaAluno() throws PersistenciaException {
        List<AlunoVO> listaAluno = new ArrayList<>();
        AlunoVO alunoVO;

        try {
            ResultSet rs = comandoBuscarTudo.executeQuery();
            while (rs.next()) {
                alunoVO = new AlunoVO();
                alunoVO.setMatricula(rs.getInt("matricula"));
                alunoVO.setNome(rs.getString("nome"));
                alunoVO.setNomeMae(rs.getString("nomemae"));
                alunoVO.setNomePai(rs.getString("nomepai"));
                alunoVO.setSexo(EnumSexo.values()[rs.getInt("sexo")]);
                alunoVO.getEndereco().setBairro(rs.getString("bairro"));
                alunoVO.getEndereco().setLogradouro(rs.getString("logradouro"));
                alunoVO.getEndereco().setCidade(rs.getString("cidade"));
                alunoVO.getEndereco().setNumero(rs.getInt("numero"));
                alunoVO.getEndereco().setUf(EnumUF.valueOf(rs.getString("uf")));
                alunoVO.setCurso(this.cursoDAO.BuscarPorCodigo(rs.getInt("curso")));
                listaAluno.add(alunoVO);
            }
        } catch (SQLException e) {
            throw new PersistenciaException("Erro na seleção por nome − " + e.getMessage());
        }
        return listaAluno;
    }
}
