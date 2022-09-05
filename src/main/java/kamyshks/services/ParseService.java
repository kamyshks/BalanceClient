package kamyshks.services;

import kamyshks.dto.Params;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParseService {

    public Params setParamsFromTerminal(final String rCount, final String wCount, final String idListStr) {
        final List<Integer> idList = new ArrayList<>(parseAll(idListStr));
        Stream.of(idList).forEach(System.out::println);
        return new Params(Integer.parseInt(rCount), Integer.parseInt(wCount), idList);
    }

    public Params setParamsFromFile(String filePath) {
        try {
            FileReader fr = new FileReader(filePath);
            Scanner scan = new Scanner(fr);
            final Integer rCount = Integer.parseInt(scan.nextLine());
            final Integer wCount = Integer.parseInt(scan.nextLine());
            final List<Integer> idList = parseAll(scan.nextLine());
            Stream.of(idList).forEach(System.out::println);
            scan.close();
            return new Params(rCount, wCount, idList);
        } catch (FileNotFoundException e) {
            System.out.println("File read error");
            e.printStackTrace();
        }
        return null;
    }

    private List<Integer> parseOneItem(final String item) {
        Pattern pattern = Pattern.compile("^\\d+-\\d+$");
        if (pattern.matcher(item).find()) {
            final String[] range = item.split("-");
            return IntStream.rangeClosed(Integer.parseInt(range[0]), Integer.parseInt(range[1]))
                    .boxed()
                    .collect(Collectors.toList());
        }
        return Collections.singletonList(Integer.parseInt(item));
    }

    private List<Integer> parseAll(final String str) {
        return Arrays.stream(str.split(","))
                .map(this::parseOneItem)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
