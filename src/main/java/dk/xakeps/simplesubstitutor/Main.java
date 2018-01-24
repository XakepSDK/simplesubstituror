package dk.xakeps.simplesubstitutor;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.text.StrLookup;
import org.apache.commons.text.StrSubstitutor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        String singleLookupStr = "ID=${id}, Name=${name}, Level=${level}";
        String mapLookupStr = "Sasha's level = ${sasha.level}, Dima's level = ${dima.level}, Sergey's level = ${sergey.level}, ";

        Player sasha = new Player(UUID.randomUUID(), "Sasha", 1);
        Player dima = new Player(UUID.randomUUID(), "Dima", 2);
        Player sergey = new Player(UUID.randomUUID(), "Sergey", 3);


        StrSubstitutor singleLookup = new StrSubstitutor(new BeanResolver(sasha));
        String replaced = singleLookup.replace(singleLookupStr);
        System.out.println(replaced);

        Map<String, Player> players = new HashMap<>();
        players.put("sasha", sasha);
        players.put("dima", dima);
        players.put("sergey", sergey);
        StrSubstitutor mapLookup = new StrSubstitutor(new BeanResolver(players));
        String replacedMap = mapLookup.replace(mapLookupStr);
        System.out.println(replacedMap);
    }

    private static final class BeanResolver extends StrLookup<Object> {
        private final Object holder;

        BeanResolver(Object holder) {
            this.holder = holder;
        }

        @Override
        public String lookup(String key) {
            try {
                return ConvertUtils.convert(PropertyUtils.getProperty(holder, key));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                return key;
            }
        }
    }
}
