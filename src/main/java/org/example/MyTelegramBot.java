package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyTelegramBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "Viktor_43343_Bot";
    }

    @Override
    public String getBotToken() {
        return "7445390731:AAGV_Es6AaBapBL58xjrfXolTj3tYKQ9ZD8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            long userId = message.getFrom().getId();
            String userName = message.getFrom().getUserName();
            String firstName = message.getFrom().getFirstName();
            String lastName = message.getFrom().getLastName();
            String text = message.getText();

            // Створення даних про користувача
            Map<String, Object> userData = new HashMap<>();
            userData.put("userId", userId);
            userData.put("userName", userName);
            userData.put("firstName", firstName);
            userData.put("lastName", lastName);
            userData.put("lastMessage", text);

            // Збереження даних в JSON-файл
            saveUserDataToJson(userData);

            // Відправка відповіді користувачу
            SendMessage response = new SendMessage();
            response.setChatId(message.getChatId().toString());
            response.setText("Ваші дані збережено!");

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для збереження даних у JSON
    private void saveUserDataToJson(Map<String, Object> userData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("user_data.json", true)) {
            gson.toJson(userData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
