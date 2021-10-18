package br.com.justgeek.mobile.enums;

public enum TemaProdutoEnum {

    BOKU_NO_HERO("BOKU NO HERO"),
    JUJUTSU_KAISEN("JUJUTSU KAISEN"),
    NARUTO("NARUTO"),
    DRAGON_BALL("DRAGON BALL"),
    INVINCIBLE("INVINCIBLE"),
    PIRATAS_DO_CARIBE("PIRATAS DO CARIBE"),
    LEAGUE_OF_LEGENDS("LEAGUE OF LEGENDS");

    private final String tema;

    TemaProdutoEnum(String tema) {
        this.tema = tema;
    }

    public static TemaProdutoEnum valueFrom(String name){
        for(TemaProdutoEnum temaEnum : TemaProdutoEnum.values()){
            if (temaEnum.tema.equalsIgnoreCase(name)
                    || temaEnum.name().equalsIgnoreCase(name)){
                return temaEnum;
            }
        }
        throw new IllegalStateException("Não existe tema associado ao [" + name + "].");
    }

    public String getTema() {
        return tema;
    }
}
