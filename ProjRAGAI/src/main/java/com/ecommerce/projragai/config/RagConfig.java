package com.ecommerce.projragai.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import dev.langchain4j.model.embedding.onnx.allminilml6v2q.AllMiniLmL6V2QuantizedEmbeddingModel;

@Configuration
public class RagConfig {

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // Correct implementation name for in-memory
        return new InMemoryEmbeddingStore<>();
    }

   // Inside your @Configuration class:
    @Bean
    public EmbeddingModel embeddingModel() {
        // This is the implementation provided by the dependency above
        return new AllMiniLmL6V2QuantizedEmbeddingModel();
    }
}