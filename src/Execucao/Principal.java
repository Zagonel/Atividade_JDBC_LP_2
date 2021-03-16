package Execucao;

import Negocio.AlunoNegocio;
import Negocio.CursoNegocio;
import Negocio.DisciplinaNegocio;
import Negocio.NegocioExeption;
import Persistencia.CursoDAO;
import Persistencia.PersistenciaException;
import Vo.AlunoVO;
import Vo.CursoVO;
import Vo.DisciplinaVO;
import Vo.EnumSexo;
import Vo.EnumUF;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int opcao = 0;

        CursoNegocio curso = null;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Principal ---------------------------|\n"
                    + "|  1 - Alunos\n"
                    + "|  2 - Cursos\n"
                    + "|  3 - Disciplinas\n"
                    + "|  4 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));
        } catch (NumberFormatException e) {

            if (e.getMessage().equals("null")) {
                System.exit(0);
            }

            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuPrincipal();
        }

        switch (opcao) {

            case 1:

                try {
                    curso = new CursoNegocio();
                    if (curso.verificaCurso() == 0) {
                        JOptionPane.showMessageDialog(null, "Adicionar primeiro o curso antes do aluno");
                        menuPrincipal();
                    } else {
                        menuAluno();
                        break;
                    }
                } catch (NegocioExeption ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            case 2:
                menuCurso();
                break;

            case 3:
                try {
                    curso = new CursoNegocio();
                    if (curso.verificaCurso() == 0) {
                        JOptionPane.showMessageDialog(null, "Adicionar primeiro o curso antes da disciplina");
                        menuPrincipal();
                    } else {
                        menuDisciplina();
                        break;
                    }
                } catch (NegocioExeption ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            case 4:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuPrincipal();
                break;
        }

    }

    // Opções de Aluno
    public static void menuAluno() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Aluno ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Matricula\n"
                    + "|  6 - Buscar Todos os Alunos\n"
                    + "|  7 - Buscar Todos os Alunos De um curso\n"
                    + "|  8 - Voltar Menu Principal\n"
                    + "|  9 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));
        } catch (NumberFormatException e) {

            if (e.getMessage().equals("null")) {
                System.exit(0);
            }

            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuAluno();
        }

        switch (opcao) {

            case 1:
                incluirAluno();
                break;
            case 2:
                alterarAluno();
                break;
            case 3:
                excluirAluno();
                break;
            case 4:
                pesquisarAlunoNome();
                break;
            case 5:
                pesquisarAlunoMatricula();
                break;
            case 6:
                buscarTodosAlunos();
                menuAluno();
                break;
            case 7:
                buscarTodosAlunosDeCurso();
                menuAluno();
                break;
            case 8:
                menuPrincipal();
                break;
            case 9:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuAluno();
                break;
        }
    }

    public static void incluirAluno() {

        EnumUF uf;
        EnumSexo sexo;
        CursoVO curso = new CursoVO();

        AlunoNegocio alunoNegocio = null;

        AlunoVO novoAluno = new AlunoVO();

        try {
            CursoNegocio cursoAux = new CursoNegocio();

            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), novoAluno.getCurso());
            novoAluno.setCurso(curso);

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        novoAluno.setNome(JOptionPane.showInputDialog(null, "Digite o nome do novo aluno"));
        novoAluno.setNomeMae(JOptionPane.showInputDialog(null, "Digite o nome da mãe do novo aluno"));
        novoAluno.setNomePai(JOptionPane.showInputDialog(null, "Digite o nome do pai do novo aluno"));
        novoAluno.getEndereco().setBairro(JOptionPane.showInputDialog(null, "Digite o bairro do novo aluno"));
        novoAluno.getEndereco().setCidade(JOptionPane.showInputDialog(null, "Digite a cidade do novo aluno"));
        novoAluno.getEndereco().setLogradouro(JOptionPane.showInputDialog(null, "Digite o logradouro do novo aluno"));
        novoAluno.getEndereco().setNumero(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o numero da casa do novo aluno")));

        uf = (EnumUF) JOptionPane.showInputDialog(null, "Escolha a UF do aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, EnumUF.values(), novoAluno.getEndereco().getUf());
        novoAluno.getEndereco().setUf(uf);

        sexo = (EnumSexo) JOptionPane.showInputDialog(null, "Escolha o Sexo do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, EnumSexo.values(), novoAluno.getSexo());
        novoAluno.setSexo(sexo);

        System.out.println(novoAluno);

        try {
            alunoNegocio = new AlunoNegocio();
            alunoNegocio.inserir(novoAluno);
            menuAluno();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao incluir o aluno " + ex.getMessage());
        }

    }

    public static void alterarAluno() {

        EnumUF uf;
        EnumSexo sexo;
        CursoVO curso = new CursoVO();

        AlunoNegocio alunoNegocio = null;

        AlunoVO novoAluno = new AlunoVO();

        novoAluno.setMatricula(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o numero da matricula do aluno a ser alterado")));

        try {
            CursoNegocio cursoAux = new CursoNegocio();

            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), novoAluno.getCurso());
            novoAluno.setCurso(curso);

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        novoAluno.setNome(JOptionPane.showInputDialog(null, "Digite o nome do aluno"));
        novoAluno.setNomeMae(JOptionPane.showInputDialog(null, "Digite o nome da mãe do aluno"));
        novoAluno.setNomePai(JOptionPane.showInputDialog(null, "Digite o nome do pai do aluno"));
        novoAluno.getEndereco().setBairro(JOptionPane.showInputDialog(null, "Digite o bairro do aluno"));
        novoAluno.getEndereco().setCidade(JOptionPane.showInputDialog(null, "Digite a cidade do aluno"));
        novoAluno.getEndereco().setLogradouro(JOptionPane.showInputDialog(null, "Digite o logradouro do aluno"));
        novoAluno.getEndereco().setNumero(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o numero da casa do aluno")));

        uf = (EnumUF) JOptionPane.showInputDialog(null, "Escolha a UF do aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, EnumUF.values(), novoAluno.getEndereco().getUf());
        novoAluno.getEndereco().setUf(uf);

        sexo = (EnumSexo) JOptionPane.showInputDialog(null, "Escolha o Sexo do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, EnumSexo.values(), novoAluno.getSexo());
        novoAluno.setSexo(sexo);

        try {
            alunoNegocio = new AlunoNegocio();
            alunoNegocio.alterar(novoAluno);
            menuAluno();
        } catch (NegocioExeption ex) {
            JOptionPane.showMessageDialog(null, "Erro ao incluir o aluno " + ex.getMessage());
        }
    }

    public static void excluirAluno() {
        int codigo = 0;

        codigo = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite a matricula do aluno a ser deletada"));

        try {
            AlunoNegocio aluno = new AlunoNegocio();
            aluno.excluir(codigo);
            menuAluno();
        } catch (NegocioExeption ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o aluno" + ex.getMessage());
        }

    }

    public static void pesquisarAlunoNome() {
        try {
            List<AlunoVO> listaAlunos = new ArrayList();
            AlunoNegocio aluno = new AlunoNegocio();
            listaAlunos = aluno.pesquisaParteNome(JOptionPane.showInputDialog(null, "Digite o Parte do nome a ser buscado"));
            JOptionPane.showInputDialog(null, "Resultado de Busca", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, listaAlunos.toArray(), null);
            menuAluno();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void pesquisarAlunoMatricula() {

        try {

            AlunoNegocio aluno = new AlunoNegocio();
            AlunoVO alunoBuscado = new AlunoVO();
            alunoBuscado = aluno.pesquisarPorMatricula(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite a matricula do aluno a ser buscada")));
            JOptionPane.showMessageDialog(null, alunoBuscado);
            menuAluno();

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void buscarTodosAlunos() {
        try {
            AlunoNegocio aluno = new AlunoNegocio();

            List<AlunoVO> listaAlunos = aluno.buscaTodosAlunos();

            for (int i = 0; i < listaAlunos.size(); i++) {
                System.out.println("Nome: " + listaAlunos.get(i).getNome() + " Matricula: " + listaAlunos.get(i).getMatricula() + " Sexo: " + listaAlunos.get(i).getSexo() + "\n");
            }

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void buscarTodosAlunosDeCurso() {
        CursoVO curso = new CursoVO();
        try {

            CursoNegocio cursoAux = new CursoNegocio();
            AlunoNegocio aluno = new AlunoNegocio();
            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), curso.getCodigo());

            List<AlunoVO> listaResultado = new ArrayList();

            listaResultado = aluno.buscaTodosAlunosDeCurso(curso);

            for (int i = 0; i < listaResultado.size(); i++) {
                System.out.println(listaResultado.get(i));
            }

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //Fim opções de Aluno

    // Opções de curso
    public static void menuCurso() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Curso ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Codigo\n"
                    + "|  6 - Pesquisar todos os cursos\n"
                    + "|  7 - Voltar Menu Principal\n"
                    + "|  8 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));

        } catch (NumberFormatException e) {

            if (e.getMessage().equals("null")) {
                System.exit(0);
            }

            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuCurso();
        }

        switch (opcao) {

            case 1:
                incluirCurso();
                break;
            case 2:
                alterarCurso();
                break;
            case 3:
                excluirCurso();
                break;
            case 4:
                pesquisarCursoNome();
                break;
            case 5:
                pesquisarCursoCodigo();
                break;

            case 6:
                buscarTodosCursos();
                menuCurso();
                break;

            case 7:
                menuPrincipal();
                break;
            case 8:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuCurso();
                break;
        }
    }

    public static void incluirCurso() {

        CursoNegocio curso = null;
        CursoVO novoCurso = new CursoVO();

        novoCurso.setNome(JOptionPane.showInputDialog(null, "Digite o nome do curso a ser adicionado"));

        try {
            curso = new CursoNegocio();
            curso.inserir(novoCurso);
            menuCurso();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void alterarCurso() {

        CursoNegocio curso = null;
        CursoVO novoCurso = new CursoVO();

        novoCurso.setCodigo(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo do curso a alterado")));
        novoCurso.setNome(JOptionPane.showInputDialog(null, "Digite o novo nome do curso"));

        try {
            curso = new CursoNegocio();
            curso.inserir(novoCurso);
            menuCurso();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void excluirCurso() {

        CursoNegocio curso = null;

        int codigo = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo do curso a ser deletado"));

        try {
            curso = new CursoNegocio();
            curso.excluir(codigo);
            menuCurso();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pesquisarCursoNome() {

        try {
            List<CursoVO> listaCursos = new ArrayList();
            CursoNegocio curso = new CursoNegocio();
            listaCursos = curso.pesquisaParteNome(JOptionPane.showInputDialog(null, "Digite o Parte do nome a ser buscado"));
            JOptionPane.showInputDialog(null, "Resultado de Busca", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, listaCursos.toArray(), null);
            menuAluno();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pesquisarCursoCodigo() {

        try {
            CursoNegocio curso = new CursoNegocio();
            CursoVO cursoBuscado = new CursoVO();
            cursoBuscado = curso.pesquisaCodigo(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo do curso a ser buscado")));
            JOptionPane.showMessageDialog(null, cursoBuscado);
            menuAluno();

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void buscarTodosCursos() {
        try {
            CursoNegocio curso = new CursoNegocio();

            List<CursoVO> listaCursos = curso.buscaTodosCursos();

            for (int i = 0; i < listaCursos.size(); i++) {
                System.out.println("Nome: " + listaCursos.get(i).getNome() + " Codigo: " + listaCursos.get(i).getCodigo() + "\n");
            }

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Fim opções de curso

    // Opções de Disciplina
    public static void menuDisciplina() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Disciplina ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Codigo\n"
                    + "|  6 - Pesquisar todas Disciplinas\n"
                    + "|  7 - Pesquisar todas Disciplinas de um Curso\n"
                    + "|  8 - Voltar Menu Principal\n"
                    + "|  9 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));
        } catch (NumberFormatException e) {

            if (e.getMessage().equals("null")) {
                System.exit(0);
            }

            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuDisciplina();
        }

        switch (opcao) {

            case 1:
                incluirDisciplina();
                break;
            case 2:
                alterarDisciplina();
                break;
            case 3:
                excluirDisciplina();
                break;
            case 4:
                pesquisarDisciplinaNome();
                break;
            case 5:
                pesquisarDisciplinaCodigo();
                break;
            case 6:
                buscarTodasDisciplinas();
                menuDisciplina();
                break;
            case 7:
                buscarTodasDisciplinasDeCurso();
                menuDisciplina();
                break;
            case 8:
                menuPrincipal();
                break;
            case 9:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuDisciplina();
                break;
        }
    }

    public static void incluirDisciplina() {

        DisciplinaNegocio disciplina = null;
        DisciplinaVO novaDisciplina = new DisciplinaVO();
        CursoVO curso = new CursoVO();

        novaDisciplina.setNome(JOptionPane.showInputDialog(null, "Digite o nome da nova disciplina"));
        novaDisciplina.setSemestre(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o semestre que pertence essa disciplina")));
        novaDisciplina.setCargahoraria(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite a carga horaria que pertence a essa disciplina")));

        try {
            CursoNegocio cursoAux = new CursoNegocio();

            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso que a disciplina pertencerá ", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), novaDisciplina.getCurso());
            novaDisciplina.setCurso(curso);

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            disciplina = new DisciplinaNegocio();
            disciplina.inserir(novaDisciplina);
            menuDisciplina();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao incluir o aluno " + ex.getMessage());
        }
    }

    public static void alterarDisciplina() {

        DisciplinaNegocio disciplina = null;
        DisciplinaVO novaDisciplina = new DisciplinaVO();
        CursoVO curso = new CursoVO();

        novaDisciplina.setCodigo(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo da disciplina a ser alterada")));

        novaDisciplina.setNome(JOptionPane.showInputDialog(null, "Digite o nome da disciplina"));
        novaDisciplina.setSemestre(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o semestre que pertence essa disciplina")));
        novaDisciplina.setCargahoraria(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite a carga horaria que pertence a essa disciplina")));

        try {
            CursoNegocio cursoAux = new CursoNegocio();
            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso que a disciplina pertencerá ", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), novaDisciplina.getCurso());
            novaDisciplina.setCurso(curso);

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            disciplina = new DisciplinaNegocio();
            disciplina.alterar(novaDisciplina);
            menuDisciplina();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao incluir o aluno " + ex.getMessage());
        }
    }

    public static void excluirDisciplina() {

        DisciplinaNegocio disciplina = null;

        int codigo = Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo da disciplina a ser deletada"));

        try {
            disciplina = new DisciplinaNegocio();
            disciplina.excluir(codigo);
            menuDisciplina();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pesquisarDisciplinaNome() {

        try {
            List<DisciplinaVO> listaDisciplinas = new ArrayList();
            DisciplinaNegocio disciplina = new DisciplinaNegocio();
            listaDisciplinas = disciplina.buscaPorNome(JOptionPane.showInputDialog(null, "Digite o Parte do nome a ser buscado"));
            JOptionPane.showInputDialog(null, "Resultado de Busca", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, listaDisciplinas.toArray(), null);
            menuDisciplina();
        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void pesquisarDisciplinaCodigo() {

        try {
            DisciplinaNegocio disciplina = new DisciplinaNegocio();
            DisciplinaVO disciplinaBuscada = new DisciplinaVO();
            disciplinaBuscada = disciplina.buscaPorCodigo(Integer.valueOf(JOptionPane.showInputDialog(null, "Digite o codigo do curso a ser buscado")));
            JOptionPane.showMessageDialog(null, disciplinaBuscada);
            menuDisciplina();

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void buscarTodasDisciplinas() {
        try {
            DisciplinaNegocio disciplina = new DisciplinaNegocio();

            List<DisciplinaVO> listaDisciplinas = disciplina.buscaTodasDisciplinas();

            for (int i = 0; i < listaDisciplinas.size(); i++) {
                System.out.println("Nome: " + listaDisciplinas.get(i).getNome() + " Codigo: " + listaDisciplinas.get(i).getCodigo() + " Semestre: " + listaDisciplinas.get(i).getSemestre() + " Carga Horaria: " + listaDisciplinas.get(i).getCargahoraria() + "\n");
            }

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void buscarTodasDisciplinasDeCurso() {
        CursoVO curso = new CursoVO();
        try {

            CursoNegocio cursoAux = new CursoNegocio();
            DisciplinaNegocio disciplina = new DisciplinaNegocio();
            curso = (CursoVO) JOptionPane.showInputDialog(null, "Escolha o curso do Aluno", "Leitura de Dados", JOptionPane.QUESTION_MESSAGE, null, cursoAux.buscaTodosCursos().toArray(), curso.getCodigo());

            List<DisciplinaVO> listaResultado = new ArrayList();

            listaResultado = disciplina.buscaTodosDisciplinasDeCurso(curso);

            for (int i = 0; i < listaResultado.size(); i++) {
                System.out.println(listaResultado.get(i));
            }

        } catch (NegocioExeption ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Fim opções de Disciplina
}
