import org.apache.commons.cli.*;

public class CLI {

    static String[] args;
    static String password;
    static String encryptedMessage;
    static Option optionKey = new Option("p", "password", true, "password of the encryptedMessage");
    static Option optionPassword = new Option("e", "encryptedMessage", true, "encryptedMessage");
    static Option optionHelp = new Option("h", "help", false, "display help text");

    public CLI(String[] args) {
        CLI.args = args;
    }

    private static boolean checkForHelp(String[] args) throws ParseException {
        boolean hashelp = false;
        Options options = new Options();
        try {
            options.addOption(CLI.optionHelp);
            new DefaultParser();
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(optionHelp.getOpt()) || args.length == 0) {
                hashelp = true;
            }
        } catch (ParseException e) {
            return hashelp;
        }

        return hashelp;
    }

    public static boolean parseCLI() throws ParseException {
        Options options = new Options();

        CLI.optionKey.setRequired(true);
        CLI.optionPassword.setRequired(true);

        options.addOption(optionKey);
        options.addOption(optionPassword);
        options.addOption(optionHelp);

        try {
            if (checkForHelp(CLI.args)) {
                HelpFormatter fmt = new HelpFormatter();
                fmt.printHelp(" ", options);
                return false;
            }
        } catch (ParseException e) {
            throw e;
        }

        try {
            CommandLine commandLine = new DefaultParser().parse(options, CLI.args);

            if (commandLine.hasOption("p") || commandLine.hasOption("password")) {
                //获取参数
                CLI.password = commandLine.getOptionValue('p');
                System.out.println("Get password: " + CLI.password);
            }

            if (commandLine.hasOption("e") || commandLine.hasOption("encryptedMessage")) {
                //获取参数
                CLI.encryptedMessage = commandLine.getOptionValue('e');
                System.out.println("Get encrypted message: " + CLI.encryptedMessage);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}
