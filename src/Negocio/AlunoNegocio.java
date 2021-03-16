package Negocio;

import Persistencia.AlunoDAO;
import Persistencia.ConexaoBD;
import Persistencia.PersistenciaException;
import Vo.AlunoVO;
import Vo.CursoVO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoNegocio {

    private AlunoDAO alunoDAO;

    public AlunoNegocio() throws NegocioExeption {
        try {
            this.alunoDAO = new AlunoDAO(ConexaoBD.getInstancia());
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao iniciar percistencia - " + e.getMessage());
        }
    }

    public void inserir(AlunoVO alunoVO) throws NegocioExeption {
        String mensagemErro = validarDados(alunoVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }

        try {
            if (alunoDAO.incluir(alunoVO) == 0) {
                throw new NegocioExeption("inclusão não realizada");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao incluir o aluno - " + e.getMessage());
        }
    }

    public void alterar(AlunoVO alunoVO) throws NegocioExeption {
        String mensagemErro = validarDados(alunoVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }

        try {
            if (alunoDAO.alterar(alunoVO) == 0) {
                throw new NegocioExeption("alteração não realizada");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao alterar o aluno - " + e.getMessage());
        }
    }

    public void excluir(int codigo) throws NegocioExeption {

        try {
            if (alunoDAO.excluir(codigo) == 0) {
                throw new NegocioExeption("exclusão não realizada");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao excluir aluno");
        }
    }

    public List<AlunoVO> pesquisaParteNome(String parteNome) throws NegocioExeption {

        try {
            return alunoDAO.buscarPorNome(parteNome);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar pelo nome - " + e.getMessage());
        }
    }

    public AlunoVO pesquisarPorMatricula(int codigo) throws NegocioExeption {

        try {
            return alunoDAO.BuscaPorMatricula(codigo);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar aluno por matricula" + e.getMessage());
        }
    }

    public List<AlunoVO> buscaTodosAlunos() throws NegocioExeption {
        try {
            return alunoDAO.buscarListaAluno();
        } catch (PersistenciaException ex) {
            throw new NegocioExeption("Erro ao buscar Disciplinas" + ex.getMessage());
        }
    }

    public List<AlunoVO> buscaTodosAlunosDeCurso(CursoVO curso) throws NegocioExeption {
        try {
            List<AlunoVO> lista = buscaTodosAlunos();
            List<AlunoVO> listaResultado = new ArrayList();

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCurso().getCodigo() == curso.getCodigo()) {
                    listaResultado.add(lista.get(i));
                }
            }
            return listaResultado;

        } catch (NegocioExeption ex) {
            throw new NegocioExeption("Erro ao buscar os alunos relacionados ao curso" + ex.getMessage());
        }
    }

    private String validarDados(AlunoVO alunoVO) {
        String mensagemErro = "";

        if (alunoVO.getNome() == null || alunoVO.getNome().length() == 0) {
            mensagemErro += "\nNome do aluno não pode ser vazio";
        }

        if (alunoVO.getNomeMae() == null || alunoVO.getNomeMae().length() == 0) {
            mensagemErro += "\nNome da mãe do aluno não pode ser vazio";
        }

        if (alunoVO.getNomePai() == null || alunoVO.getNomePai().length() == 0) {
            mensagemErro += "\nNome do pai do aluno não pode ser vazio";
        }

        if (alunoVO.getSexo() == null) {
            mensagemErro += "\nSexo do aluno não pode ser nulo";
        }

        if (alunoVO.getEndereco().getLogradouro() == null || alunoVO.getEndereco().getLogradouro().length() == 0) {
            mensagemErro += "\nLogradouro não pode ser vazio";
        }

        if (alunoVO.getEndereco().getNumero() <= 0) {
            mensagemErro += "\nNumero não pode ser menor ou igual a zero";
        }

        if (alunoVO.getEndereco().getBairro() == null || alunoVO.getEndereco().getBairro().length() == 0) {
            mensagemErro += "\nO bairro não pode ser vazio";
        }

        if (alunoVO.getEndereco().getCidade() == null || alunoVO.getEndereco().getCidade().length() == 0) {
            mensagemErro += "\nA cidade não pode ser vazia";
        }

        if (alunoVO.getEndereco().getUf() == null) {
            mensagemErro += "\nUF não pode ser vazia";
        }

        if (alunoVO.getCurso() == null) {
            mensagemErro += "\n O curso não pode ser vazio";
        }
        return mensagemErro;
    }
}
