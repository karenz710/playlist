package com.hw2app;

import java.util.HashMap;

public class SongFileMapping {
    private static HashMap<String, String> songFileMap;

    static{
        songFileMap = new HashMap<>();
        songFileMap.put("1812 Overture", "overture_1812");
        songFileMap.put("Danse Macabre", "danse_macabre");
        songFileMap.put("Entry of the Gladiators", "entry_of_the_gladiators");
        songFileMap.put("Auld Lang Syne", "auld_lang_syne");
        songFileMap.put("Fanfare For The Common Man", "fanfare_for_the_common_man");
        songFileMap.put("Marriage of Figaro", "marriage_of_figaro");
        songFileMap.put("Ode to Joy", "ode_to_joy");
        songFileMap.put("Sprach Zarathustra", "sprach_zarathustra");
    }

    public static String getFileName(String name){
        return songFileMap.get(name);
    }
}
