package com.example.group13backend.utils;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Service;

@Service
public class KeyUtil {

  private final PrivateKey privateKey;
  private final PublicKey publicKey;

  public KeyUtil() throws IOException {
    this.privateKey = readPrivateKey();
    this.publicKey = readPublicKey();
  }

  private PrivateKey readPrivateKey() throws IOException {
    String content = Files.readString(Path.of("src/main/resources/keys/private-key.pem"));
    PEMParser parser = new PEMParser(new StringReader(content));
    PEMKeyPair pemKeyPair = (PEMKeyPair) parser.readObject();
    return new JcaPEMKeyConverter().getPrivateKey(pemKeyPair.getPrivateKeyInfo());
  }

  private PublicKey readPublicKey() throws IOException {
    String content = Files.readString(Path.of("src/main/resources/keys/public-key.pem"));
    PEMParser parser = new PEMParser(new StringReader(content));
    SubjectPublicKeyInfo publicKeyInfo = (SubjectPublicKeyInfo) parser.readObject();
    return new JcaPEMKeyConverter().getPublicKey(publicKeyInfo);
  }

  public PrivateKey getPrivateKey() {
    return privateKey;
  }

  public PublicKey getPublicKey() {
    return publicKey;
  }
}
