package addressbook.generetor;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunction;
import model.GroupData;
import model2.AddContact;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
     String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    String count;

    public static void main (String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
    generator.run();

    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate () {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        }
         else {
        throw new IllegalArgumentException("Неизвестный тип данных" + type);
    }
    }

    private Object generateContacts() {
        var result = new ArrayList<AddContact>();
        for (int i = 0; i < 5; i++) {
            result.add(new AddContact().withFirstame(CommonFunction.randomString(i * 2))
                    .withLastame(CommonFunction.randomString(i * 2))
                    .withAddress(CommonFunction.randomString(i * 2))
                    .withMobile(CommonFunction.randomString(i * 2))
                    .withEmail(CommonFunction.randomString(i * 2))
                    .withPhoto(CommonFunction.randomFile("src/test/resources/images")));
        }
        return result;
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < 5; i++) {
            result.add(new GroupData()
                    .withName(CommonFunction.randomString(i * 2))
                    .withHeader(CommonFunction.randomString(i * 2))
                    .withFooter(CommonFunction.randomString(i * 2)));
        }
        return result;
    }

    private void save (Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }
}
