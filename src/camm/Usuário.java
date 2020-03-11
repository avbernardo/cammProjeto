
package camm;


public class Usuário {
    
    private String login;
    private String senha;
    private int Cod_Usuário;
    private String perguntaEspecial;
    private String respostaEspecial;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCod_Usuário() {
        return Cod_Usuário;
    }

    public void setCod_Usuário(int Cod_Usuário) {
        this.Cod_Usuário = Cod_Usuário;
    }

    public String getPerguntaEspecial() {
        return perguntaEspecial;
    }

    public void setPerguntaEspecial(String perguntaEspecial) {
        this.perguntaEspecial = perguntaEspecial;
    }

    public String getRespostaEspecial() {
        return respostaEspecial;
    }

    public void setRespostaEspecial(String respostaEspecial) {
        this.respostaEspecial = respostaEspecial;
    }
    
    
}
