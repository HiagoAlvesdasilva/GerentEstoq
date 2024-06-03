package gerenciamentodeEstoque;

public class OpcaoInvalidaException extends Exception {
    public OpcaoInvalidaException(String mensagem) {
        super(mensagem);
    }
}