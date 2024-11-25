package com.message.service;

import com.message.service.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MessageApplication implements CommandLineRunner  {

	private MessageService messageService;

	@Autowired
	public MessageApplication(MessageService messageService) {
		this.messageService = messageService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Scanner scanner = new Scanner(System.in);

			System.out.print("Digite seu nome: ");
			String username = scanner.nextLine();

			messageService.initializeChannel("chat-message");

			System.out.println("Bem-vindo ao chat, " + username + "!");
			System.out.println("Digite suas mensagens abaixo (digite 'sair' para sair):");

			while (true) {
				String message = scanner.nextLine();

				System.out.print("\033[1A");
				System.out.print("\033[2K");
				System.out.print("\r");

				if (message.equalsIgnoreCase("sair")) {
					System.out.println("Saindo do chat. Até logo!");
					break;
				}

				messageService.sendMessage(message, "chat-message", username);
			}

			scanner.close();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
