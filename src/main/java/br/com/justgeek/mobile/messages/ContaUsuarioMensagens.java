package br.com.justgeek.mobile.messages;

public class ContaUsuarioMensagens {

    private ContaUsuarioMensagens() {
        throw new IllegalStateException("Utility class");
    }

    public static final String MENSAGEM_UNAUTHORIZED = "Usuário de ID {} não está logado.";
    public static final String MENSAGEM_VALIDACAO = "[Perfil] Erro na validação do cadastro do usuário.";
}
