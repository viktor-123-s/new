package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot2  extends TelegramLongPollingBot {




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
            String chatId = message.getChatId().toString();
            String messageText = message.getText();

            // Відповідь на повідомлення
            SendMessage response = new SendMessage();
            response.setChatId(chatId);
            response.setText("Ви написали: " + messageText);

            // Створення кнопок
            ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
            List<KeyboardRow> keyboard = new ArrayList<>();

            KeyboardRow row = new KeyboardRow();
            row.add(new KeyboardButton("Кнопка 1"));
            row.add(new KeyboardButton("Кнопка 2"));

            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);

            response.setReplyMarkup(keyboardMarkup);

            try {
                execute(response);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}





