package Vo;

public class AlunoVO {

    private int matricula;
    private String nome;
    private String nomeMae;
    private String nomePai;
    private EnumSexo sexo;
    private EnderecoVO endereco;
    private CursoVO curso;

    public AlunoVO() {
        this.endereco = new EnderecoVO();
    }

    public AlunoVO(int matricula, String nome, EnumSexo sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.sexo = sexo;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public EnumSexo getSexo() {
        return sexo;
    }

    public void setSexo(EnumSexo sexo) {
        this.sexo = sexo;
    }

    public EnderecoVO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoVO endereco) {
        this.endereco = endereco;
    }

    public CursoVO getCurso() {
        return curso;
    }

    public void setCurso(CursoVO curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "matricula = " + matricula + "  " + "|  nome = " + nome + "  " + "|  nomeMae = " + nomeMae + "  " + "|  nomePai = " + nomePai + "  " + "|  sexo = " + sexo + "  " + "|  endereco : " + endereco + "  " + "|  curso : " + curso;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlunoVO other = (AlunoVO) obj;
        if (this.matricula != other.matricula) {
            return false;
        }
        return true;
    }

}
