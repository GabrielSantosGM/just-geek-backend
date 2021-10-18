package br.com.justgeek.mobile.enums;

public enum RoupaEnum {

    CAMISETA("CAMISETA"),
    MOLETOM("MOLETOM"),
    CALCAS("CALÇAS");

    private final String modeloRoupa;

    RoupaEnum(String modeloRoupa) {
        this.modeloRoupa = modeloRoupa;
    }

    public static RoupaEnum valueFrom(String name){
        for(RoupaEnum roupaEnum : RoupaEnum.values()){
            if(roupaEnum.modeloRoupa.equalsIgnoreCase(name)
                    || roupaEnum.name().equalsIgnoreCase(name)){
                return roupaEnum;
            }
        }
        throw new IllegalStateException("Não existe opção associada a roupa [" + name + "].");
    }

    public String getModeloRoupa() {
        return modeloRoupa;
    }
}
