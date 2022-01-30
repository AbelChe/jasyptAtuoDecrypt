public class main {

    public static void main(String[] args) throws Exception {

        CLI cli = new CLI(args);
        if (cli.parseCLI()) {
            Decrypt decrypt = new Decrypt();
            for (String algorithm : decrypt.algorithmList) {
                System.out.println("[+] Now Trying " + algorithm);
                for (String iv : decrypt.ivClassList)
                    for (String salt : decrypt.saltClassList)
                        for (String keyobs : decrypt.keyObtentionIterationsDefaultList)
                            for (String outtype : decrypt.StringOutputTypeList) {
                                if (decrypt.doDecrypt(
                                        algorithm,
                                        CLI.password,
                                        CLI.encryptedMessage,
                                        keyobs,
                                        "SunJCE",
                                        outtype,
                                        iv,
                                        salt,
                                        "1"
                                )) {
                                    if (GetEncode.isMessyCode(decrypt.plaintext)) {
                                        System.out.println("\033[32m[*] " + decrypt.plaintext + "\033[m");
                                        decrypt.result.add("[?] " + decrypt.plaintext + " : " + decrypt.Talgorithm + ", " + CLI.encryptedMessage + ", " + CLI.password);
                                    } else {
                                        System.out.println("\033[32m[*] " + Decrypt.plaintext + "\033[m");
                                        decrypt.result.add("[+] " + decrypt.plaintext + " : " + decrypt.Talgorithm + ", " + CLI.encryptedMessage + ", " + CLI.password);
                                    }
                                }
                            }
            }
            if (decrypt.result.size() != 0) {
                System.out.println("================================SUCCESS================================");
                for (String s : decrypt.result)
                    System.out.println(s);
                System.out.println("=======================================================================");
            } else {
                System.out.println("[-] Failed...");
            }
        }

    }
}
