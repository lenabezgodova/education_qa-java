package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGeneration {

    @Parameter(names = "-c", description = "User count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Data format")
    public String format;
    // -c 2 -f src/test/resources/users.xml -d xml
    // working directory C:\Users\lenab\IdeaProjects\education_qa-java\addressbook-web-tests

    public static void main(String[] args) throws IOException {
        UserDataGeneration generator = new UserDataGeneration();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        if (format.equals("csv")){
            saveAsCsv(users, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(users, new File(file));
        } else {
            System.out.println("Unrecognized format: " + format);
        }

    }

    private void saveAsXml(List<UserData> users, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(UserData.class);
        String xml = xstream.toXML(users);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsCsv (List<UserData> users, File file) throws IOException { //нужно этот список сохранить в файл
        Writer writer = new FileWriter(file);

        for(UserData user: users){
            writer.write(String.format("%s;%s;%s;%s;%s\n", user.getFirstName(), user.getLastName(), user.getMiddleName(), user.getWorkPhone()));
        }
        writer.close();
    }

    private List<UserData> generateUsers (int count){
        List<UserData> users = new ArrayList<UserData>();

        for (int i = 0; i < count; i++){
            users.add(new UserData().withFirstName(String.format("First Name %s", i))
                    .withLastName(String.format("Last name %s", i))
                    .withMiddleName(String.format("Middle Name %s", i))
                    .withWorkPhone(String.format("+7980(100)565%s", i)));
        }
        return users;
    }



}
