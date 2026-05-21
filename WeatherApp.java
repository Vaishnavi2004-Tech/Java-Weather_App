import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;

public class WeatherApp {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Weather App");

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JLabel title = new JLabel("Enter City");
        title.setFont(new Font("Arial", Font.BOLD, 34));

        JTextField cityField = new JTextField(20);
        cityField.setFont(new Font("Arial", Font.PLAIN, 22));

        JButton clearBtn = new JButton("X");
        clearBtn.setFont(new Font("Arial", Font.BOLD, 16));

        JButton searchBtn =
        new JButton("Search Weather");

        searchBtn.setFont(
        new Font("Arial", Font.BOLD, 18));

        JLabel result = new JLabel("");

        result.setFont(
        new Font("Arial", Font.BOLD, 22));

        JPanel inputPanel = new JPanel();

        inputPanel.add(cityField);
        inputPanel.add(clearBtn);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(15,15,15,15);

        panel.add(title, gbc);

        gbc.gridy++;
        panel.add(inputPanel, gbc);

        gbc.gridy++;
        panel.add(searchBtn, gbc);

        gbc.gridy++;
        panel.add(result, gbc);

        clearBtn.addActionListener(e -> {

            cityField.setText("");
            result.setText("");

        });

        searchBtn.addActionListener(e -> {

            String city = cityField.getText();

            String apiKey = "026e2baebec52c3d4d6be29ca85087b0";

            try {

                String url =
                "https://api.openweathermap.org/data/2.5/weather?q="
                + city
                + "&appid="
                + apiKey
                + "&units=metric";

                URL apiUrl = new URL(url);

                BufferedReader br =
                new BufferedReader(
                new InputStreamReader(
                apiUrl.openStream()));

                String data = "";
                String line;

                while((line = br.readLine()) != null){
                    data += line;
                }

                String weather =
                data.split("\"main\":\"")[1]
                .split("\"")[0];

                String temp =
                data.split("\"temp\":")[1]
                .split(",")[0];

                String humidity =
                data.split("\"humidity\":")[1]
                .split(",")[0];

                result.setText(
                "<html><center>"
                +"Weather : "+weather
                +"<br><br>"
                +"Temperature : "+temp+" °C"
                +"<br><br>"
                +"Humidity : "+humidity+"%"
                +"</center></html>");

            }

            catch(Exception ex){

                result.setText(
                "Check API Key / Internet");

            }

        });

        frame.add(panel);

        frame.setVisible(true);
    }
}