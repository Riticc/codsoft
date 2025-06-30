import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.nextLine().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.nextLine().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        try {
            String urlStr = "https://api.exchangerate.host/latest?base=" + base + "&symbols=" + target;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("Error: HTTP response code " + responseCode);
                scanner.close();
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();

            JSONObject json = new JSONObject(result.toString());

            if (!json.has("rates") || !json.getJSONObject("rates").has(target)) {
                System.out.println("Error: Exchange rate for " + target + " not found.");
                scanner.close();
                return;
            }

            double rate = json.getJSONObject("rates").getDouble(target);
            double converted = amount * rate;

            System.out.printf("Converted Amount: %.2f %s%n", converted, target);

        } catch (Exception e) {
            System.out.println("Error fetching exchange rates: " + e.getMessage());
        }

        scanner.close();
    }
}
