package Vo;

public class DisciplinaVO {

    private String nome;
    private int cargahoraria;
    private int semestre;
    private int codigo;
    private CursoVO curso;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCargahoraria() {
        return cargahoraria;
    }

    public void setCargahoraria(int cargahoraria) {
        this.cargahoraria = cargahoraria;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "DisciplinaVO{" + "nome=" + nome + ", cargahoraria=" + cargahoraria + ", semestre=" + semestre + ", codigo=" + codigo + '}';
    }

}
