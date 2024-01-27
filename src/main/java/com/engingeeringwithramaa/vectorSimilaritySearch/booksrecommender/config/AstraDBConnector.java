package com.engingeeringwithramaa.vectorSimilaritySearch.booksrecommender.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;

@Component
public class AstraDBConnector {
    private static final Logger LOGGER = LoggerFactory.getLogger(AstraDBConnector.class);
    private CqlSession cqlSession;

    public AstraDBConnector(AstraDBConfig astraDBConfig) {
        try {
            cqlSession = CqlSession.builder()
                    .withCloudSecureConnectBundle(Paths.get(astraDBConfig.getSecureBundle()))
                    .withAuthCredentials(astraDBConfig.getDbUser(), astraDBConfig.getSecretToken())
                    .withKeyspace(astraDBConfig.getVectorKeyspace())
                    .build();

            LOGGER.info("[OK] Welcome to Astra DB! Connected to Keyspace {}", cqlSession.getKeyspace().get());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public CqlSession getCqlSession() {
        return cqlSession;
    }

}
