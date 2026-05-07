package br.upe.atividaderegex;
import java.util.regex.*;

public class Leitura {
    public String ip;
    public String dataCompleta;
    public String metodo;
    public String url;
    public int codigo;
    public int tamanho;
    public String userAgent;
    public boolean validade = false;


    public Leitura(String linha) {

        try {
            Pattern pattern = Pattern.compile("(\\S+) - - \\[(.*?)\\] \"(\\w+) (.*?) HTTP/\\S+\" (\\d{3}) (\\d+|-) .*?\\\".*?\\\" \\\"(.*?)\\\"");
            Matcher matcher = pattern.matcher(linha);

            if (matcher.find()) {
                this.ip = matcher.group(1);
                this.dataCompleta = matcher.group(2);
                this.metodo = matcher.group(3);
                this.url = matcher.group(4);
                this.codigo = Integer.parseInt(matcher.group(5));
                this.tamanho = matcher.group(6).equals("-") ? 0 : Integer.parseInt(matcher.group(6));
                // nossa eu tinha esquecido que ? podia ser usado pra condições mais simples. Gostei de terem usado aqui
                this.userAgent = matcher.group(7);
                this.validade = true;
            }
        } catch (Exception ignored) {
        	// aqui tá vazio mesmo
          }
        }

    public boolean foiSucesso() {
        return codigo >= 200 && codigo <= 299;
    }

    public boolean foiErroCliente() {
        return codigo >= 400 && codigo <= 499;
    }

    public boolean ehDeNovembro2021() {
        return validade && dataCompleta.contains("/Nov/2021:");
    }

    public boolean ehDoAno2021() {
        return validade && dataCompleta.contains("/2021:");
    }
}
