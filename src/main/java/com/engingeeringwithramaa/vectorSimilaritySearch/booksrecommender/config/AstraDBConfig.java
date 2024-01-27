package com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "book-recommender.astra")
public class AstraDBConfig {

    private String secureBundle;
    private String dbUser;
    private String secretToken;
    private String vectorKeyspace;

    // Getters and setters

    public String getSecureBundle() {
        return secureBundle;
    }

    public void setSecureBundle(String secureBundle) {
        this.secureBundle = secureBundle;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public void setSecretToken(String secretToken) {
        this.secretToken = secretToken;
    }

    public String getVectorKeyspace() {
        return vectorKeyspace;
    }

    public void setVectorKeyspace(String vectorKeyspace) {
        this.vectorKeyspace = vectorKeyspace;
    }
}
