import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        //Fazer conexão HTTP e buscar os tops 250 filmes   
        String url = "https://api.mocki.io/v2/549a5d8b";
        URI endereco = URI.create(url);
       
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
       
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        
        //Extrair apenas os dados incialemnte relevantes (título, poster, classificação)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println("A lista tem: "+listaDeFilmes.size()+" filmes");

        //Exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: "+filme.get("title"));
            System.out.println("Poster: "+filme.get("image"));
            System.out.println("Classificão: "+filme.get("imDbRating"));
            System.out.println("");
        }
    }
}
