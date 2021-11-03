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
            if(cupomEnum.name().contains(name)){
                return cupomEnum;
            }
        }
        throw new IllegalStateException("NAO EXISTE CUPOM ASSOCIADO A [" + name + "].");
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }
}
