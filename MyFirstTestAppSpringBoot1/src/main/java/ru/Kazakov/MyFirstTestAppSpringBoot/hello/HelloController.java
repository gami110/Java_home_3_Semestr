package ru.Kazakov.MyFirstTestAppSpringBoot.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name",
            defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
    private ArrayList<String> stringList;

    @GetMapping("/update-array")
    public ArrayList<String> updateArrayList(@RequestParam("s") String s) {
        if (stringList == null) {
            stringList = new ArrayList<>();
        }
        stringList.add(s);
        return stringList;
    }

    @GetMapping("/show-array")
    public ArrayList<String> showArrayList() {
        return stringList;
    }

    private HashMap<Integer, String> hashMap = new HashMap<>();

    @GetMapping("/update-map")
    public void updateHashMap(@RequestParam String s) {
        if (hashMap.isEmpty()) {
            hashMap.put(1, s);
        } else {
            int nextKey = hashMap.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
            hashMap.put(nextKey, s);
        }

    }

    @GetMapping("/show-map")
    public HashMap<Integer, String> showHashMap() {
        return hashMap;
    }

    //    нужен, потому что stringList и hashMap могут не существовать
    private void initCollections() {
        stringList = new ArrayList<>();
        hashMap = new HashMap<>();
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        if (stringList == null || hashMap == null) {
            initCollections();
        }

        int listSize = stringList.size();
        int mapSize = hashMap.size();

        return "Size of ArrayList: " + listSize + ", Size of HashMap: " + mapSize;
    }
}
