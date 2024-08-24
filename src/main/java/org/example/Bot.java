package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

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
            String userFirstName = update.getMessage().getFrom().getFirstName();
            String chatId = update.getMessage().getChatId().toString();

            String responseMessage = "Hello, " + userFirstName + "!";

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(responseMessage);

            try {
                execute(message); // Sending the message to the user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}