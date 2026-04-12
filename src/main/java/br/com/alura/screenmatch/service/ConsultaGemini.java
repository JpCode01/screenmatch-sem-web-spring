package br.com.alura.screenmatch.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

// Não usar pelo amor de Deus
public class ConsultaGemini {
    public static String obterTraducao(String texto) {
        String apiKey = System.getenv("GEMINI_APIKEY");

        String prompt = "Traduza o seguinte texto e não faça mais nada, retorne apenas a tradução: " + texto;
        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.0-flash",
                        prompt, null);

        return response.text();
    }
}
