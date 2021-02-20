
package Vo;

public class CursoVO {
    
    private int codigo;
    private String nome;

    public CursoVO() {
    }

    public CursoVO(int codigo, String nome) {
        this();
        this.codigo = codigo;
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "codigo = " + codigo + "|  nome = " + nome;
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
        final CursoVO other = (CursoVO) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }              
}
