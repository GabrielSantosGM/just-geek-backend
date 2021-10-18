package br.com.justgeek.mobile.enums.respostas.requisicoes;

public enum RespostasRequisicoesArtistaEnum {

    MENSAGEM_VALIDACAO("[Perfil] Erro na validação do cadastro do artista.");

    private final String resposta;

    RespostasRequisicoesArtistaEnum(String resposta) {
        this.resposta = resposta;
    }

    public String getResposta() {
        return resposta;
    }
}
