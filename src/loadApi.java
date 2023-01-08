import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class loadApi {
    public static void catNinja(){
        // https://catfact.ninja/fact
        try {
            URL url = new URL("https://catfact.ninja/fact");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }

                scanner.close();

                //System.out.println(informationString + "\n");

                JSONParser parse = new JSONParser();
                Object dataObject = parse.parse(String.valueOf(informationString));
                JSONObject data = (JSONObject) dataObject;

                System.out.println(data.get("fact"));
                System.out.println("Characters: " + data.get("length"));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
