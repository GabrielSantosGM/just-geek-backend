package br.com.justgeek.mobile.enums.respostas.requisicoes;

public enum RespostasRequisicoesUsuarioEnum {

    MENSAGEM_VALIDACAO("[Perfil] Erro na validação do cadastro do usuário de ID {}."),
    MENSAGEM_AUTENTICACAO("[Perfil] Erro na autenticacao do usuário de ID {}.");

    private final String resposta;

    RespostasRequisicoesUsuarioEnum(String resposta) {
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }
}
