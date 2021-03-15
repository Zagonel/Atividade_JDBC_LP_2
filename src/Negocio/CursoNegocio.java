package Negocio;

import Persistencia.ConexaoBD;
import Persistencia.CursoDAO;
import Persistencia.PersistenciaException;
import Vo.CursoVO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoNegocio {

    private CursoDAO cursoDAO;

    public CursoNegocio() throws NegocioExeption {
        try {
            this.cursoDAO = new CursoDAO(ConexaoBD.getInstancia());
        } catch (PersistenciaException e) {
            throw new NegocioExeption("erro ao iniciar a percistencia");
        }
    }

    public void inserir(CursoVO cursoVO) throws NegocioExeption {
        String mensagemErro = this.validarDados(cursoVO);
        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }
        try {
            if (cursoDAO.incluir(cursoVO) == 0) {
                throw new NegocioExeption("Inclusão não realizada");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao incluir o curso - " + e.getMessage());
        }
    }

    public void alterar(CursoVO cursoVO) throws NegocioExeption {
        String mensagemErro = this.validarDados(cursoVO);
        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }
        try {
            if (cursoDAO.alterar(cursoVO) == 0) {
                throw new NegocioExeption("Alteração não realizada!");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao alterar o curso " + e.getMessage());
        }
    }

    public void excluir(int codigo) throws NegocioExeption {
        try {
            if (cursoDAO.excluir(codigo) == 0) {
                throw new NegocioExeption("Alteração não realizada!");
            }
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao excluir o curso " + e.getMessage());
        }
    }

    public List<CursoVO> pesquisaParteNome(String parteNome) throws NegocioExeption {
        try {
            return cursoDAO.buscarPorNome(parteNome);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar o curso pelo nome" + e.getMessage());
        }
    }

    public CursoVO pesquisaCodigo(int codigo) throws NegocioExeption {

        try {
            return cursoDAO.BuscarPorCodigo(codigo);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar curso pelo codigo " + e.getMessage());
        }
    }

    public int verificaCurso() {
        int retorno;
        retorno = cursoDAO.verificaCurso();

        return retorno;
    }

    // Busca todos os cursos cadastrados no banco de dados em formato de lista
    public List<CursoVO> buscaTodosCursos() throws NegocioExeption {

        try {
            return cursoDAO.buscarListaCurso();
        } catch (PersistenciaException ex) {
            throw new NegocioExeption("Erro ao buscar cursos" + ex.getMessage());
        }
    }

    //Verifica se há cursos cadastrados no banco de dados
    public String validarDados(CursoVO cursoVO) {
        String mensagemErro = "";

        if (cursoVO.getNome() == null || cursoVO.getNome().length() == 0) {
            mensagemErro = "Nome do curso não pode ser vazio!";
        }
        return mensagemErro;
    }
}
