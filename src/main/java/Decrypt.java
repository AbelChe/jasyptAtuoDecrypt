import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.*;

import java.util.ArrayList;
import java.util.Set;


public class Decrypt {
    static String Talgorithm;
    static String plaintext;
    static ArrayList<String> result = new ArrayList<>();

    static String ivClass = "org.jasypt.iv";
    static String saltClass = "org.jasypt.salt";
    static ArrayList<String> ivClassList = new ArrayList<>();;
    static ArrayList<String> saltClassList = new ArrayList<>();
    static String[] algorithmList = {
            "PBEWITHMD5ANDDES", // default
            "PBEWITHHMACSHA1ANDAES_128",
            "PBEWITHHMACSHA1ANDAES_256",
            "PBEWITHHMACSHA224ANDAES_128",
            "PBEWITHHMACSHA224ANDAES_256",
            "PBEWITHHMACSHA256ANDAES_128",
            "PBEWITHHMACSHA256ANDAES_256",
            "PBEWITHHMACSHA384ANDAES_128",
            "PBEWITHHMACSHA384ANDAES_256",
            "PBEWITHHMACSHA512ANDAES_128",
            "PBEWITHHMACSHA512ANDAES_256",
            "PBEWITHMD5ANDTRIPLEDES",
            "PBEWITHSHA1ANDDESEDE",
            "PBEWITHSHA1ANDRC2_128",
            "PBEWITHSHA1ANDRC2_40",
            "PBEWITHSHA1ANDRC4_128",
            "PBEWITHSHA1ANDRC4_40"
    };
    static String[] keyObtentionIterationsDefaultList = {
            "1000", // default
            "1001",
            "4000",
            "20",
            "10000",
            "1500",
            "13",
            "666",
            "2000000",
            "100",
            "253210",
    };
    static String[] StringOutputTypeList = {
            "base64", // default
            "hexadecimal"
    };


    public Decrypt() throws Exception {
        Set<Class<?>> ivclasses = utils.getClasses(ivClass);
        System.out.println("[=] Loading iv methods...");
        for(Class c:ivclasses){
            ivClassList.add(c.getName());
//            System.out.println("    - " + c.getName());
        }
        System.out.println("[+] Load iv success");

        Set<Class<?>> saltclasses = utils.getClasses(saltClass);
        System.out.println("[=] Loading salt methods...");
        for(Class c:saltclasses){
            saltClassList.add(c.getName());
//            System.out.println("    - " + c.getName());
        }
        System.out.println("[+] Load salt success");
    }

    public static boolean doDecrypt(String thisAlogrithm, String password, String encryptedMessage, String keyObtentionIterations, String providerName, String stringOutputType, String ivGeneratorClassName, String saltGeneratorClassName, String poolSize) throws EncryptionInitializationException, EncryptionOperationNotPossibleException{
        try{
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setAlgorithm(thisAlogrithm);
            config.setPassword(password);
            config.setKeyObtentionIterations(keyObtentionIterations); // 设置应用于获取加密密钥的哈希迭代次数 default 1000
            config.setProviderName(providerName); // 设置要请求加密算法的安全提供程序的名称 default SunJCE
            config.setSaltGeneratorClassName(saltGeneratorClassName); // default org.jasypt.salt.RandomSaltGenerator
            config.setStringOutputType(stringOutputType); // 设置字符串输出的编码形式，可用的编码类型有 base64、hexadecimal default base64
            config.setIvGeneratorClassName(ivGeneratorClassName); // default org.jasypt.iv.RandomIvGenerator
            config.setPoolSize(poolSize); // default 1
            encryptor.setConfig(config);
//            System.out.println("[-] " + thisAlogrithm + ", " + keyObtentionIterations + ", " + providerName + ", " + stringOutputType + ", " + saltGeneratorClassName);
            Decrypt.plaintext = encryptor.decrypt(encryptedMessage);
            Decrypt.Talgorithm = thisAlogrithm;
            return true;
        }
        catch (EncryptionInitializationException|EncryptionOperationNotPossibleException|AlreadyInitializedException e) {
//            System.out.println(e);
            return false;
        }
    }
}
