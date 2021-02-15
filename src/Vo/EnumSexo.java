package Vo;

public enum EnumSexo {

    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String descricao;

    private EnumSexo(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return this.descricao;
    }

}
