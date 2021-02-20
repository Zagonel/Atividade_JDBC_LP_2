package Negocio;

import Persistencia.ConexaoBD;
import Persistencia.DisciplinaDAO;
import Persistencia.PersistenciaException;
import Vo.DisciplinaVO;
import java.util.ArrayList;
import java.util.List;

public class DisciplinaNegocio {

    private DisciplinaDAO disciplinaDAO;

    public DisciplinaNegocio() throws NegocioExeption {
        try {
            this.disciplinaDAO = new DisciplinaDAO(ConexaoBD.getInstancia());
        } catch (PersistenciaException ex) {
            throw new NegocioExeption("Erro ao iniciar percistencia" + ex.getMessage());
        }
    }

    public void inserir(DisciplinaVO disciplinaVO) throws NegocioExeption {
        String mensagemErro = validarDados(disciplinaVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }

        try {
            if (disciplinaDAO.incluir(disciplinaVO) == 0) {
                throw new NegocioExeption("inclusão não realizada");
            } 
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao incluir o aluno - " + e.getMessage());
        }
    }

    public void alterar(DisciplinaVO disciplinaVO) throws NegocioExeption {
        String mensagemErro = validarDados(disciplinaVO);

        if (!mensagemErro.isEmpty()) {
            throw new NegocioExeption(mensagemErro);
        }

        try {
            if (disciplinaDAO.alterar(disciplinaVO) == 0) {
                throw new NegocioExeption("alteração não realizada");
            } 
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao alterar o aluno - " + e.getMessage());
        }
    }

    public void excluir(int codigo) throws NegocioExeption {
        try {
            if (disciplinaDAO.excluir(codigo) == 0) {
                throw new NegocioExeption("exclusão não realizada");
            } 
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao excluir o aluno - " + e.getMessage());
        }
    }

    public List<DisciplinaVO> buscaPorNome(String nome) throws NegocioExeption {
        try {
            return disciplinaDAO.buscarPorNome(nome);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar por nome " + e.getMessage());
        }
    }

    public DisciplinaVO buscaPorCodigo(int codigo) throws NegocioExeption {
        try {
            return disciplinaDAO.buscarPorCodigo(codigo);
        } catch (PersistenciaException e) {
            throw new NegocioExeption("Erro ao pesquisar por codigo" + e.getMessage());
        }
    }

    public String validarDados(DisciplinaVO disciplinaVO) {
        String mensagemErro = "";

        if (disciplinaVO.getNome() == null || disciplinaVO.getNome().length() == 0) {
            mensagemErro += "\n O nome da discplina não pode ser vazio";
        }
        if (disciplinaVO.getSemestre() <= 0) {
            mensagemErro += "\n O semestre não pode ser menor ou igual a zero";
        }
        if (disciplinaVO.getCargahoraria() <= 0 || disciplinaVO.getCargahoraria() > 180) {
            mensagemErro += "\n A carga horaria não pode ser negativa ou maior que 180hrs";
        }
        if (disciplinaVO.getCurso() == null) {
            mensagemErro += "\n O curso não pode ser vazio";
        }

        return mensagemErro;
    }
}
