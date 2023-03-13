package uit.ac.ma.blockchaincybersecurity.ServiceData;

import uit.ac.ma.blockchaincybersecurity.Model.Wallet;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.*;

public class WalletData {

    private Wallet wallet;
    //singleton class
    private static WalletData instance;

    static {
        instance = new WalletData();
    }

    public static WalletData getInstance() {
        return instance;
    }

    //This will load your wallet from the database.
    public void loadWallet() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        Connection walletConnection = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\pc\\Desktop\\Project-Blockchain-cybersecurity\\Blockchain-cybersecurity-Copy2\\db\\wallet.db");
        Statement walletStatment = walletConnection.createStatement();
        ResultSet resultSet;
        resultSet = walletStatment.executeQuery(" SELECT * FROM WALLET ");
        KeyFactory keyFactory = KeyFactory.getInstance("DSA"); // it will help us create the PublicKey and PrivateKey
        PublicKey pub2 = null;
        PrivateKey prv2 = null;
        while (resultSet.next()) {
            pub2 = keyFactory.generatePublic(
                    new X509EncodedKeySpec(resultSet.getBytes("PUBLIC_KEY"))); //X509EncodedKeySpec use it to generate our public key
            prv2 = keyFactory.generatePrivate(
                    new PKCS8EncodedKeySpec(resultSet.getBytes("PRIVATE_KEY")));
        }
        this.wallet = new Wallet(pub2, prv2);
    }

    public Wallet getWallet() {
        return wallet;
    }
}
