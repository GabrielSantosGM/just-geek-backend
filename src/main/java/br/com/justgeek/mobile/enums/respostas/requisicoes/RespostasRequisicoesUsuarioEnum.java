package br.com.justgeek.mobile.enums.respostas.requisicoes;

public enum RespostasRequisicoesUsuarioEnum {

    MENSAGEM_VALIDACAO("[PERFIL] ERRO NA VALIDACAO DO CADASTRO DO USUARIO DE ID {}."),
    MENSAGEM_AUTENTICACAO("[PERFIL] ERRO NA AUTENTICACAO DO USUARIO DE ID {}."),
    MENSAGEM_UNAUTHORIZED("[PERFIL] USUARIO DE ID {} NAO AUTORIZADO"),
    MENSAGEM_NOT_FOUND("[PERFIL] USUARIO NÃO ENCONTRADO");

    private final String resposta;

    RespostasRequisicoesUsuarioEnum(String resposta) {
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }
}
