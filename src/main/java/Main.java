import org.apache.commons.cli.*;
import services.*;

public class Main {

    public static void main(String[] args) {

        final Options options = new Options();

        final Option optServerUrl = new Option("s", "serverUrl", true, "input server Url");
        optServerUrl.setRequired(true);
        options.addOption(optServerUrl);

        final Option optFilePath = new Option("f", "filePath", true, "input file path");
        optFilePath.setRequired(false);
        options.addOption(optFilePath);

        final Option optRCount = new Option("r", "rCount", true, "input rCount");
        optRCount.setRequired(false);
        optRCount.setType(Integer.class);
        options.addOption(optRCount);

        final Option optWCount = new Option("w", "wCount", true, "input wCount");
        optWCount.setRequired(false);
        optWCount.setType(Integer.class);
        options.addOption(optWCount);

        final Option optIdList = new Option("i", "idList", true, "input idList (for example: 13,6-8,4)");
        optIdList.setRequired(false);
        options.addOption(optIdList);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        try {
            final CommandLine cmd = parser.parse(options, args);

            final String serverUrl = cmd.getOptionValue("serverUrl");
            final String filePath = cmd.getOptionValue("filePath");
            final String rCount = cmd.getOptionValue("rCount");
            final String wCount = cmd.getOptionValue("wCount");
            final String idList = cmd.getOptionValue("idList");

            EndpointService endpointService = new EndpointService(serverUrl);
            ParseService parseService = new ParseService();
            if (filePath != null) {
                endpointService.call(parseService.setParamsFromFile(filePath));
            } else {
                endpointService.call(parseService.setParamsFromTerminal(rCount, wCount, idList));
            }
        } catch (ParseException| NullPointerException e) {
            System.out.println("Input error");
            formatter.printHelp("Balance client", options);
            System.exit(1);
        }
    }
}
