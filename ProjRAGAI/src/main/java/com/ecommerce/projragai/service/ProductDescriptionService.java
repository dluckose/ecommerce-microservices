package com.ecommerce.projragai.service;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDescriptionService {

    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;
    private final ChatLanguageModel geminiModel; // Injected via Gemini Spring Boot Starter

    public ProductDescriptionService(EmbeddingStore<TextSegment> embeddingStore,
                                     EmbeddingModel embeddingModel,
                                     ChatLanguageModel geminiModel) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
        this.geminiModel = geminiModel;
    }

    public String generateDescription(String customerQuery) {
        try {
            // 1. Find the top 3 relevant products
            //List<EmbeddingMatch<TextSegment>> matches = embeddingStore.search(embeddingModel.embed(customerQuery).content(), 3, 0.7);
       List<EmbeddingMatch<TextSegment>> matches = embeddingStore.findRelevant(embeddingModel.embed(customerQuery).content(),3,0.7);

        if (matches.isEmpty()) {
            return "No matching products found.";
        }

            // 2. Build context string
        StringBuilder context = new StringBuilder();
        for (EmbeddingMatch<TextSegment> match : matches) {
            context.append(match.embedded().text()).append("\n");
        }

            // 3. Send to Gemini Flash
        String prompt = "You are a sales assistant. Use these product details to answer: " + customerQuery +
                "\n\nContext:\n" + context;
            System.out.println("context "+context);
            return geminiModel.generate(prompt);
        }catch (Exception e) {
            e.printStackTrace(); // This will print the actual error to your IntelliJ console
            return "Error calling Gemini: " + e.getMessage();
        }
    }

}