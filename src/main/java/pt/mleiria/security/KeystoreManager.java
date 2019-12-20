package pt.mleiria.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class KeystoreManager {

    private static final String KEYSTORE_FILE = "/home/manuel/tools/Keys/truststore.jks";
    private static final char[] KEYSTORE_PWD = "123456".toCharArray();

    private KeyStore keyStore;

    public KeystoreManager() {
    }


    public static void main(String[] args) {
        try {
            KeystoreManager km = new KeystoreManager();
            km.loadKeyStore();
            PrivateKey pk = km.getPrivateKey("upi", "123456");
            System.out.println(pk.toString());
            Certificate[] cer = km.getCertChain("upi");
            Arrays.stream(cer).forEach(elem -> System.out.println(elem.getType() + " : " + elem.getPublicKey().getAlgorithm()));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @return
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     * @throws IOException
     * @throws KeyStoreException
     */
    private void loadKeyStore() throws CertificateException, NoSuchAlgorithmException, IOException, KeyStoreException {
        keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        try (FileInputStream fis = new FileInputStream(KEYSTORE_FILE);) {
            keyStore.load(fis, KEYSTORE_PWD);
        }
    }

    private void saveKeyStore() {

    }

    private void addTrustedCertEntries() {

    }

    /**
     *
     * @param alias
     * @param pwd
     * @return
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    private PrivateKey getPrivateKey(final String alias, final String pwd) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        return (PrivateKey) keyStore.getKey(alias, pwd.toCharArray());
    }

    /**
     *
     * @param alias
     * @return
     * @throws KeyStoreException
     */
    private Certificate[] getCertChain(final String alias) throws KeyStoreException {
        final Certificate[] cers = keyStore.getCertificateChain(alias);
        return cers;
    }

    private Certificate getCert() {
        return null;
    }
}
