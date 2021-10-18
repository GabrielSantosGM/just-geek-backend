package br.com.justgeek.mobile.enums;

public enum CupomDescontoEnum {

    PRIMEIRA_COMPRA(30),
    CUPOM_PRESENTE(20);

    private final Integer porcentagemDesconto;

    CupomDescontoEnum(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public static CupomDescontoEnum valueFrom(String name){
        for(CupomDescontoEnum cupomEnum : CupomDescontoEnum.values()){
            if(cupomEnum.name().equalsIgnoreCase(name)){
                return cupomEnum;
            }
        }
        throw new IllegalStateException("Não existe cupom associado a [" + name + "].");
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }
}
