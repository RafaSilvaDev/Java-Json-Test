import com.google.gson.Gson;
import ifsp.br.to.ViaCep;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class TestCep {

    private Gson gson = new Gson();

    final String CEP = "13183-250";

    @Test
    public void coletaJSON() throws IOException {
        URL viacep = new URL("https://viacep.com.br/ws/"+ CEP +"/json/");

        String temp = "";
        HttpURLConnection conn = (HttpURLConnection)
                    viacep.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        if(conn.getResponseCode() == 200){
            Scanner scanner = new Scanner(viacep.openStream());
            while(scanner.hasNext()){
                temp = temp + scanner.nextLine();
            }
        }
        System.out.println(temp);

        //Conversão do JSON para classe em seus atributos QUE DEVEM POSSUIR O MESMO NOME DOS CAMPOS NO JSON
        ViaCep end = gson.fromJson(temp, ViaCep.class);
        System.out.println(end.getBairro() + "!!!");

        boolean isFound = temp.contains(CEP);

        assert(isFound);
    }
}
