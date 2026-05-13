package com.ecommerce.projragai.service;

import com.ecommerce.projragai.dto.ProductMessage;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ProductEventListener {

    private final EmbeddingStore<TextSegment> embeddingStore;
    private final EmbeddingModel embeddingModel;

    public ProductEventListener(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        this.embeddingStore = embeddingStore;
        this.embeddingModel = embeddingModel;
    }

    @RabbitListener(queues = "product_queue")
    public void onProductMessage(ProductMessage message) {
        // 1. Prepare text to embed
        String text = String.format("Name: %s, Category: %s, Price: %.2f",
                message.name(), message.category(), message.price());

        // 2. Add metadata (useful for retrieval or filtering)
        Metadata metadata = Metadata.from("productId", message.productId());
        TextSegment segment = TextSegment.from(text, metadata);

        // 3. Generate embedding and save to store
        embeddingStore.add(embeddingModel.embed(segment).content(), segment);
        System.out.println("Embedding Vector: " + Arrays.toString(embeddingModel.embed(segment).content().vector()));
        System.out.println("Product embedded successfully: " + message.name());
    }
}