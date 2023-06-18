package util;
import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonExtractor {

    public static void main(String[] args) {
    	String jsonString ="{\"result\":{\"tags\":[{\"confidence\":97.4589920043945,\"tag\":{\"en\":\"executive\"}},{\"confidence\":77.1211853027344,\"tag\":{\"en\":\"businessman\"}},{\"confidence\":66.6231842041016,\"tag\":{\"en\":\"suit\"}},{\"confidence\":57.3535652160645,\"tag\":{\"en\":\"man\"}},{\"confidence\":56.9759483337402,\"tag\":{\"en\":\"male\"}},{\"confidence\":56.1042785644531,\"tag\":{\"en\":\"business\"}},{\"confidence\":55.2075042724609,\"tag\":{\"en\":\"corporate\"}},{\"confidence\":51.0461463928223,\"tag\":{\"en\":\"professional\"}},{\"confidence\":46.4192085266113,\"tag\":{\"en\":\"person\"}},{\"confidence\":45.6538696289062,\"tag\":{\"en\":\"handsome\"}},{\"confidence\":45.4867324829102,\"tag\":{\"en\":\"portrait\"}},{\"confidence\":42.9373512268066,\"tag\":{\"en\":\"confident\"}},{\"confidence\":40.2017059326172,\"tag\":{\"en\":\"manager\"}},{\"confidence\":38.5844688415527,\"tag\":{\"en\":\"successful\"}},{\"confidence\":37.4798393249512,\"tag\":{\"en\":\"baron\"}},{\"confidence\":37.1554489135742,\"tag\":{\"en\":\"tie\"}},{\"confidence\":36.4013328552246,\"tag\":{\"en\":\"people\"}},{\"confidence\":34.7347793579102,\"tag\":{\"en\":\"success\"}},{\"confidence\":34.676082611084,\"tag\":{\"en\":\"office\"}},{\"confidence\":34.421272277832,\"tag\":{\"en\":\"adult\"}},{\"confidence\":31.9059524536133,\"tag\":{\"en\":\"men\"}},{\"confidence\":29.7183380126953,\"tag\":{\"en\":\"looking\"}},{\"confidence\":28.9455089569092,\"tag\":{\"en\":\"mature\"}},{\"confidence\":28.9375247955322,\"tag\":{\"en\":\"happy\"}},{\"confidence\":28.8051433563232,\"tag\":{\"en\":\"boss\"}},{\"confidence\":26.8717174530029,\"tag\":{\"en\":\"smiling\"}},{\"confidence\":26.7900810241699,\"tag\":{\"en\":\"work\"}},{\"confidence\":26.6779842376709,\"tag\":{\"en\":\"businesspeople\"}},{\"confidence\":25.7553615570068,\"tag\":{\"en\":\"smile\"}},{\"confidence\":23.092472076416,\"tag\":{\"en\":\"job\"}},{\"confidence\":22.5195159912109,\"tag\":{\"en\":\"modern\"}},{\"confidence\":21.6442337036133,\"tag\":{\"en\":\"senior\"}},{\"confidence\":20.7707843780518,\"tag\":{\"en\":\"secretary\"}},{\"confidence\":20.6838722229004,\"tag\":{\"en\":\"face\"}},{\"confidence\":20.2439270019531,\"tag\":{\"en\":\"one\"}},{\"confidence\":20.0781002044678,\"tag\":{\"en\":\"standing\"}},{\"confidence\":19.2815399169922,\"tag\":{\"en\":\"entrepreneur\"}},{\"confidence\":19.2260818481445,\"tag\":{\"en\":\"confidence\"}},{\"confidence\":19.1777935028076,\"tag\":{\"en\":\"formal\"}},{\"confidence\":17.9854469299316,\"tag\":{\"en\":\"team\"}},{\"confidence\":17.9680194854736,\"tag\":{\"en\":\"smart\"}},{\"confidence\":17.4831199645996,\"tag\":{\"en\":\"occupation\"}},{\"confidence\":17.0201835632324,\"tag\":{\"en\":\"meeting\"}},{\"confidence\":16.7493553161621,\"tag\":{\"en\":\"teamwork\"}},{\"confidence\":16.5295848846436,\"tag\":{\"en\":\"friendly\"}},{\"confidence\":16.466760635376,\"tag\":{\"en\":\"corporation\"}},{\"confidence\":16.4236660003662,\"tag\":{\"en\":\"businesswoman\"}},{\"confidence\":16.3659934997559,\"tag\":{\"en\":\"collar\"}},{\"confidence\":16.0669021606445,\"tag\":{\"en\":\"worker\"}},{\"confidence\":15.3973093032837,\"tag\":{\"en\":\"judge\"}},{\"confidence\":15.3905229568481,\"tag\":{\"en\":\"old\"}},{\"confidence\":14.5683650970459,\"tag\":{\"en\":\"expression\"}},{\"confidence\":14.3075342178345,\"tag\":{\"en\":\"guy\"}},{\"confidence\":13.8838672637939,\"tag\":{\"en\":\"black\"}},{\"confidence\":13.7089433670044,\"tag\":{\"en\":\"businessmen\"}},{\"confidence\":13.6911497116089,\"tag\":{\"en\":\"jacket\"}},{\"confidence\":13.6481523513794,\"tag\":{\"en\":\"older\"}},{\"confidence\":13.4450359344482,\"tag\":{\"en\":\"employee\"}},{\"confidence\":13.3071117401123,\"tag\":{\"en\":\"working\"}},{\"confidence\":13.1298418045044,\"tag\":{\"en\":\"shirt\"}},{\"confidence\":12.9732570648193,\"tag\":{\"en\":\"studio\"}},{\"confidence\":12.9342107772827,\"tag\":{\"en\":\"sitting\"}},{\"confidence\":12.577465057373,\"tag\":{\"en\":\"leader\"}},{\"confidence\":12.4485092163086,\"tag\":{\"en\":\"serious\"}},{\"confidence\":12.3574552536011,\"tag\":{\"en\":\"career\"}},{\"confidence\":12.2410793304443,\"tag\":{\"en\":\"cheerful\"}},{\"confidence\":12.1346244812012,\"tag\":{\"en\":\"group\"}},{\"confidence\":12.080394744873,\"tag\":{\"en\":\"glasses\"}},{\"confidence\":12.0462589263916,\"tag\":{\"en\":\"human\"}},{\"confidence\":12.0263223648071,\"tag\":{\"en\":\"positive\"}},{\"confidence\":11.7025909423828,\"tag\":{\"en\":\"colleagues\"}},{\"confidence\":11.5692481994629,\"tag\":{\"en\":\"leadership\"}},{\"confidence\":11.5357704162598,\"tag\":{\"en\":\"elderly\"}},{\"confidence\":11.2053260803223,\"tag\":{\"en\":\"company\"}},{\"confidence\":11.2018718719482,\"tag\":{\"en\":\"colleague\"}},{\"confidence\":10.9574956893921,\"tag\":{\"en\":\"communication\"}},{\"confidence\":10.7783365249634,\"tag\":{\"en\":\"hand\"}},{\"confidence\":10.5526790618896,\"tag\":{\"en\":\"together\"}},{\"confidence\":10.5347661972046,\"tag\":{\"en\":\"attractive\"}},{\"confidence\":10.2278337478638,\"tag\":{\"en\":\"happiness\"}},{\"confidence\":10.1560621261597,\"tag\":{\"en\":\"lawyer\"}},{\"confidence\":9.64082336425781,\"tag\":{\"en\":\"partnership\"}},{\"confidence\":9.56561088562012,\"tag\":{\"en\":\"workplace\"}},{\"confidence\":9.55669975280762,\"tag\":{\"en\":\"building\"}},{\"confidence\":9.54053783416748,\"tag\":{\"en\":\"thinking\"}},{\"confidence\":9.44058322906494,\"tag\":{\"en\":\"contemporary\"}},{\"confidence\":9.43446636199951,\"tag\":{\"en\":\"lifestyle\"}},{\"confidence\":9.39461803436279,\"tag\":{\"en\":\"planner\"}},{\"confidence\":9.37031078338623,\"tag\":{\"en\":\"model\"}},{\"confidence\":9.35767841339111,\"tag\":{\"en\":\"two\"}},{\"confidence\":9.27418994903564,\"tag\":{\"en\":\"head\"}},{\"confidence\":9.18811416625977,\"tag\":{\"en\":\"fresh\"}},{\"confidence\":8.8715124130249,\"tag\":{\"en\":\"coworkers\"}},{\"confidence\":8.79525852203369,\"tag\":{\"en\":\"middle aged\"}},{\"confidence\":8.73208618164062,\"tag\":{\"en\":\"natural\"}},{\"confidence\":8.72689342498779,\"tag\":{\"en\":\"partner\"}},{\"confidence\":8.64098739624023,\"tag\":{\"en\":\"staff\"}},{\"confidence\":8.33059978485107,\"tag\":{\"en\":\"phone\"}},{\"confidence\":7.94001817703247,\"tag\":{\"en\":\"indoors\"}},{\"confidence\":7.82912969589233,\"tag\":{\"en\":\"businessperson\"}},{\"confidence\":7.82056856155396,\"tag\":{\"en\":\"40s\"}},{\"confidence\":7.80580902099609,\"tag\":{\"en\":\"partners\"}},{\"confidence\":7.73833131790161,\"tag\":{\"en\":\"elegant\"}},{\"confidence\":7.73153209686279,\"tag\":{\"en\":\"outside\"}},{\"confidence\":7.65735149383545,\"tag\":{\"en\":\"casual\"}},{\"confidence\":7.61328554153442,\"tag\":{\"en\":\"fashionable\"}},{\"confidence\":7.46369886398315,\"tag\":{\"en\":\"necktie\"}},{\"confidence\":7.42287540435791,\"tag\":{\"en\":\"camera\"}},{\"confidence\":7.16148567199707,\"tag\":{\"en\":\"hair\"}},{\"confidence\":7.13747119903564,\"tag\":{\"en\":\"posing\"}},{\"confidence\":7.10805225372314,\"tag\":{\"en\":\"ruler\"}}]},\"status\":{\"text\":\"\",\"type\":\"success\"}}";
    	  ObjectMapper objectMapper = new ObjectMapper();
    	try {
            // Parsing del JSON
            JsonNode jsonNode = objectMapper.readTree(jsonString);

            // Ottenere l'array "tags"
            JsonNode tagsNode = jsonNode.path("result").path("tags");

            if (tagsNode.isArray()) {
                // Iterare attraverso i valori dell'array "tags"
                for (JsonNode tagNode : tagsNode) {
                    // Ottenere il valore "confidence"
                    double confidence = tagNode.path("confidence").asDouble();
                    System.out.println("Confidence: " + confidence);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}