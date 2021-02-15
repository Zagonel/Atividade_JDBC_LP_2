package Execucao;

import Negocio.AlunoNegocio;
import Negocio.CursoNegocio;
import Negocio.DisciplinaNegocio;
import Negocio.NegocioExeption;
import Vo.AlunoVO;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int opcao = 0;

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
                menuAluno();
                break;
            case 2:
                menuCurso();
                break;
            case 3:
                menuDisciplina();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuPrincipal();
                break;
        }

    }

    public static void menuAluno() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Aluno ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Matricula\n"
                    + "|  6 - Voltar Menu Principal\n"
                    + "|  7 - Sair\n"
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
                menuPrincipal();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuAluno();
                break;
        }
    }

    public static void incluirAluno() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void alterarAluno() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void excluirAluno() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarAlunoNome() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarAlunoMatricula() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void menuCurso() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Curso ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Codigo\n"
                    + "|  6 - Voltar Menu Principal\n"
                    + "|  7 - Sair\n"
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
                menuPrincipal();
                break;
            case 7:
                System.exit(0);
                break;

            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuCurso();
                break;
        }
    }

    public static void incluirCurso() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void alterarCurso() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void excluirCurso() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarCursoNome() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarCursoCodigo() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void menuDisciplina() {
        int opcao = 0;

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Disciplina ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - Pesquisar por Nome\n"
                    + "|  5 - Pesquisar por Codigo\n"
                    + "|  6 - Voltar Menu Principal\n"
                    + "|  7 - Sair\n"
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
                menuPrincipal();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuDisciplina();
                break;
        }
    }

    public static void incluirDisciplina() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void alterarDisciplina() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void excluirDisciplina() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarDisciplinaNome() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }

    public static void pesquisarDisciplinaCodigo() {

        try {
            AlunoNegocio novoAluno = new AlunoNegocio();
        } catch (NegocioExeption ex) {
            System.out.println("Erro ao iniciar a persistencia " + ex.getMessage());
        }
        AlunoVO novoAluno = new AlunoVO();
    }
}
