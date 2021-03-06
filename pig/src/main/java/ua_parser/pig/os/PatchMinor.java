package ua_parser.pig.os;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import ua_parser.OS;
import ua_parser.pig.PigParser;

public class PatchMinor extends EvalFunc<String> {

    PigParser parser;

    public PatchMinor() throws IOException {
        parser = PigParser.getParser();
    }

    public String exec(Tuple input) throws IOException {
        if (input == null || input.size() == 0)
            return null;
        try {
            String agentString = (String) input.get(0);
            OS os = parser.parseOS(agentString);
            if (os == null) {
                return null;
            }
            return os.patchMinor;
        } catch (Exception e) {
            throw new IOException("Caught exception processing input row ", e);
        }
    }

}
