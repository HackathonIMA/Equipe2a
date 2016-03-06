package br.com.smsc.Service;

import au.com.bytecode.opencsv.CSVReader;
import br.com.smsc.Entity.Informacoes;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by danielamorais on 3/5/16.
 */
public class DataReader {
    Informacoes info = new Informacoes();
    List<Map<String, String>> listaTemp = new ArrayList<>();

    public Informacoes getCSVContent() {
        String[] nextLine;
        try {
            CSVReader reader = new CSVReader(new FileReader(getClass().getResource("/csv/coqueluche.csv").getFile()));
            while ((nextLine = reader.readNext()) != null) {
                String content = nextLine[0];
                //Obter o nome dos campos: Bairro e as faixas de idades
                if (content.contains("Bairro Resid\";")) setFields(content);
                //Obter valores dos campos
                if (content.contains("AREA")) {
                    Map<String, String> myValues = new LinkedHashMap<>(info.getCampos());
                    String[] areaDados = content.split("\"");
                    String[] temp = areaDados[0].split(".*AREA ABRANG CS\\s");
                    myValues.put("bairro", formatData(temp[1]));
                    String[] numeros = areaDados[1].split(";");
                    int i = 1;
                    for (String key : info.getCampos().keySet()) {
                        myValues.put(key, numeros[i]);
                        i++;
                    }
                    listaTemp.add(myValues);
                }
            }
            info.setListaCampos(listaTemp);
        } catch (IOException e) {
            System.err.println("Erro ao encontrar arquivo");
        }
        return info;
    }

    private void setFields(String content) {
        Map<String, String> myMap = new LinkedHashMap<>();
        String[] fields = content.split(";");
        for (int j = 0; j < fields.length; j++) {
            if (j + 1 >= fields.length - 1) break;
            if (fields[j + 1].contains("Total")) break;
            myMap.put(fields[j + 1].replaceAll("\"", ""), null);
        }
        info.setCampos(myMap);
    }

    private String formatData(String name) {
        name = name.toLowerCase();
        name = firstCharToUpper(name);
        if (name.contains(" ")) {
            String temp = "";
            String[] partialsNames = name.split(" ");
            for (int j = 1; j < partialsNames.length; j++) {
                temp += firstCharToUpper(partialsNames[j]);
            }
            name = temp;
        }
        return name;
    }

    private String firstCharToUpper(String name) {
        char[] nameChar = name.toCharArray();
        nameChar[0] = Character.toUpperCase(nameChar[0]);
        name = new String(nameChar);
        return name;
    }

}
