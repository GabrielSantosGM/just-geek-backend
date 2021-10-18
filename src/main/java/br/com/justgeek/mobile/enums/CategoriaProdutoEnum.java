package br.com.justgeek.mobile.enums;

public enum CategoriaProdutoEnum {

    ANIME("ANIME"),
    CARTOON("CARTOON"),
    FILME("FILME");

    private final String categoria;

    CategoriaProdutoEnum(String categoria) {
        this.categoria = categoria;
    }

    public static CategoriaProdutoEnum valueFrom(String name){
        for(CategoriaProdutoEnum categoriaEnum : CategoriaProdutoEnum.values()){
            if(categoriaEnum.categoria.equalsIgnoreCase(name)
                    || categoriaEnum.name().equalsIgnoreCase(name)){
                return categoriaEnum;
            }
        }
        throw new IllegalStateException("Não existe opção associada a categoria [" + name + "].");
    }

    public String getCategoria() {
        return categoria;
    }
}
