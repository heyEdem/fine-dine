package com.finedine.authservice.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.finedine.authservice.CustomMessages.KEYS_ROTATED_SUCCESSFULLY;

/**
 * KeyManager is responsible for managing RSA keys used for JWT signing.
 * It can load configured keys or generate new ones, and it implements automatic key rotation.
 */

@Component
public class KeyManager {
    private static final Logger logger = LoggerFactory.getLogger(KeyManager.class);
    private static final String THREAD_NAME = "key-rotation-sc";
    private final AtomicReference<RSAKey> activeKey = new AtomicReference<>();
    private final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1, r -> {
        Thread t = new Thread(r, THREAD_NAME);
        t.setDaemon(false);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    });

    @Value("${rsa.public-key:#{null}}")
    private RSAPublicKey configuredPublicKey;

    @Value("${rsa.private-key:#{null}}")
    private RSAPrivateKey configuredPrivateKey;

    @Value("${rsa.key-rotation-interval:3600}")
    private long keyRotationInterval;

    @PostConstruct
    public void init() {
        logger.info("Initializing InMemoryKeyManager...");
        loadOrGenerateKeys();
        scheduleRotation();
    }

    public JWKSet getJWKSet() {
        return new JWKSet (activeKey.get());
    }

    public String rotateKeys() {
        generateAndSetKeys();
        return KEYS_ROTATED_SUCCESSFULLY;
    }

    public RSAPublicKey getPublicKey() {
        try {
            return activeKey.get().toRSAPublicKey();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public RSAPrivateKey getPrivateKey() {
        try {
            return activeKey.get().toRSAPrivateKey();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    private void scheduleRotation() {
        logger.info("Scheduling key rotation every {} seconds", keyRotationInterval);
        scheduler.scheduleWithFixedDelay(this::generateAndSetKeys, keyRotationInterval,
                keyRotationInterval, TimeUnit.SECONDS);
    }

    private void loadOrGenerateKeys() {
        if (configuredPublicKey != null && configuredPrivateKey != null) {
            logger.info("Using configured RSA key pair");
            RSAKey rsaKey = new RSAKey.Builder(configuredPublicKey)
                    .privateKey(configuredPrivateKey)
                    .keyID(UUID.randomUUID().toString())
                    .build();
            activeKey.set(rsaKey);
            logger.info("Successfully loaded configured RSA key pair");
        } else {
            logger.warn("RSA keys not configured, generating new keys for development");
            generateAndSetKeys();
        }
    }

    private void generateAndSetKeys() {
        try {
            logger.info("Generating new RSA key pair");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                    .privateKey((RSAPrivateKey) keyPair.getPrivate())
                    .keyID(UUID.randomUUID().toString())
                    .build();

            activeKey.set(rsaKey);
            logger.info("Successfully generated and set new RSA key pair with Key ID: {}", rsaKey.getKeyID());
        } catch (Exception e) {
            logger.error("Failed to generate RSA key pair", e);
            throw new RuntimeException("Key generation failed", e);
        }
    }
}