package ru.croc.finalProject;

public class InsertWordTest extends Test {

    public final String description;
    public final String sentence;
    public final String[] variantsToInsert;
    // private int rightVariant;
    // пусть первый вариант всё время будет правильным, а при опросе будем перемешивать варианты

    public InsertWordTest(String description, String sentence, String[] variantsToInsert) {
        this.description = description;
        this.sentence = sentence;
        this.variantsToInsert = variantsToInsert;
    }
    public static InsertWordTest importFromXML(String pathToFile) {

        String description = "Insert a right variant of verb \"be\"";
        String sentence = "My favorite politician and actor %s Donald Trump";
        String[] variantsToInsert = new String[] {"is", "are", "were", "was"};

        return new InsertWordTest(description, sentence, variantsToInsert);
    }

    @Override
    public boolean exportToJSON(String pathToFile) {
        return true; // true if export success
    }


}
