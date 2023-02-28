package com.projeto.pokedex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Javadex {
    private JSONArray df;
    private JSONArray weakness;

    public Javadex() {
        try {
            File file = new File("/home/kadu/projetos/javadex-final/pokedex/src/main/java/com/projeto/pokedex/pokedex.json");
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            reader.close();
            this.df = new JSONArray(json);

            File file2 = new File("/home/kadu/projetos/javadex-final/pokedex/src/main/java/com/projeto/pokedex/fraqueza.json");
            BufferedReader reader2 = new BufferedReader(new FileReader(file2.getAbsolutePath()));
            json = "";
            line = reader2.readLine();
            while (line != null) {
                json += line;
                line = reader2.readLine();
            }
            reader2.close();
            this.weakness = new JSONArray(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // /**
    //  * @param pk1
    //  * @param pk2
    //  * @return
    //  */
    public int[] javadex(String pk1, String pk2) {
        ArrayList<String> type_pk1 = new ArrayList<>();
        ArrayList<String> type_pk2 = new ArrayList<>();
        ArrayList<ArrayList<String>> weak_pk1 = new ArrayList<>();
        ArrayList<ArrayList<String>> weak_pk2 = new ArrayList<>();

        int c = 0;
        int d = 0;

        for (int i = 0; i < this.df.length(); i++) {
            JSONObject pokemon = this.df.getJSONObject(i);
            if (pokemon.getJSONObject("name").getString("english").equals(pk1)) {
                JSONArray types = pokemon.getJSONArray("type");
                for (int j = 0; j < types.length(); j++) {
                    type_pk1.add(types.getString(j));
                }
            }
            if (pokemon.getJSONObject("name").getString("english").equals(pk2)) {
                JSONArray types = pokemon.getJSONArray("type");
                for (int j = 0; j < types.length(); j++) {
                    type_pk2.add(types.getString(j));
                }
            }
        }

        for (String type : type_pk1) {
            for (int i = 0; i < this.weakness.length(); i++) {
                JSONObject type_weakness = this.weakness.getJSONObject(i);
                if (type_weakness.getString("name").equals(type)) {
                    weak_pk1.add(new ArrayList<>());
                    JSONArray weaknesses = type_weakness.getJSONArray("weaknesses");
                    for (int j = 0; j < weaknesses.length(); j++) {
                        weak_pk1.get(weak_pk1.size() - 1).add(weaknesses.getString(j));
                    }
                }
            }
        }

        for (String type : type_pk2) {
            for (int i = 0; i < this.weakness.length(); i++) {
                JSONObject type_weakness = this.weakness.getJSONObject(i);
                if (type_weakness.getString("name").equals(type)) {
                    weak_pk2.add(new ArrayList<>());
                    JSONArray weaknesses = type_weakness.getJSONArray("weaknesses");
                    for (int j = 0; j < weaknesses.length(); j++) {
                        weak_pk2.get(weak_pk2.size() - 1).add(weaknesses.getString(j));
                    }
                }
            }
        }

        for (ArrayList<String> weaknesses : weak_pk1) {
            for (String weakness : weaknesses) {
                if (type_pk2.contains(weakness)) {
                    c++;
                }
            }
        }

        for (ArrayList<String> weaknesses : weak_pk2) {
            for (String weakness : weaknesses) {
                if (type_pk1.contains(weakness)) {
                    d++;
                }
            }
        }

        int[] result = { d, c };
        return result;

    }
}