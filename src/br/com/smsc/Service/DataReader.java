package br.com.smsc.Service;

import au.com.bytecode.opencsv.CSVReader;
import br.com.smsc.Entity.Informacoes;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by danielamorais on 3/5/16.
 */
public class DataReader {
    Map<String, String> info = new LinkedHashMap<>();
    List<Map<String, String>> listaTemp = new ArrayList<>();

    public Informacoes getCSVContent(int ano, String doenca) {
        String[] nextLine;
        try {
            CSVReader reader = new CSVReader(new FileReader(getClass().getResource("/csv/"+ doenca + ".csv").getFile()));
            while ((nextLine = reader.readNext()) != null) {
                String content = nextLine[0];
                //Obter o nome dos campos: Bairro e as faixas de idades
                if (content.contains("Bairro Resid\";")) setFields(content);
                //Obter valores dos campos
                if (content.contains("AREA")) {
                    Map<String, String> myValues = new LinkedHashMap<>(info);
                    String[] areaDados = content.split("\"");
                    String[] temp = areaDados[0].split(".*AREA ABRANG CS\\s");
                    myValues.put("bairro", formatData(temp[1]));
                    String[] numeros = areaDados[1].split(";");
                    int i = 1;
                    for (String key : info.keySet()) {
                        myValues.put(key, numeros[i]);
                        i++;
                    }
                    listaTemp.add(myValues);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao encontrar arquivo");
        }
        return new Informacoes().setListaCampos(listaTemp).setAno(ano).setNome(doenca);
    }

    private void setFields(String content) {
        Map<String, String> myMap = new LinkedHashMap<>();
        String[] fields = content.split(";");
        for (int j = 1; j < fields.length; j++) {
            myMap.put(fields[j].replaceAll("\"", ""), null);
        }
        info = myMap;
    }

    private String formatData(String name) {
        name = name.toLowerCase();
        name = firstCharToUpper(name);
        if (name.contains(" ")) {
            String temp = "";
            String[] partialsNames = name.split(" ");
            for (int j = 1; j < partialsNames.length; j++) {
                temp = partialsNames[0] + " " + firstCharToUpper(partialsNames[j]);
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

    public void exportToJson(Informacoes info) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(info.getNome() + ".json"), info);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
