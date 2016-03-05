package br.com.smsc.Service;

import au.com.bytecode.opencsv.CSVReader;
import br.com.smsc.Entity.Doenca;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by danielamorais on 3/5/16.
 */
//FIXME: Refactor e logs
public class DataReader {
    public void getCSVContent() {
        Doenca dados = new Doenca();
        HashMap<String, Integer> faixaEtaria = new HashMap<>();
        ArrayList<String> bairros = new ArrayList<>();
        int k = 0;
        String[] nextLine;

        try {
            CSVReader reader = new CSVReader(new FileReader(getClass().getResource("/csv/coqueluche.csv").getFile()));
            while ((nextLine = reader.readNext()) != null) {
                String content = nextLine[0];
                if (content.contains("Bairro Resid\";")) {
                    String[] fields = content.split(";");
                    for (int j = 0; j < fields.length; j++) {
                        if (j + 1 >= fields.length - 1) break;
                        if (fields[j + 1].contains("Total")) break;
                        faixaEtaria.put(fields[j + 1], 0);
                    }
                }
                if (content.contains("AREA")) {
                    String[] areaDados = content.split("\"");
                    String[] temp = areaDados[0].split(".*AREA ABRANG CS\\s");
                    bairros.add(k, temp[1]);
                    k++;
                }
            }
            bairros = formatData(bairros);
            System.out.println("end");
        } catch (IOException e) {
            System.err.println("Erro ao encontrar arquivo");
        }

    }

    private ArrayList<String> formatData(ArrayList<String> nomesBairros) {
        for (int i = 0; i < nomesBairros.size(); i++) {
            String name = nomesBairros.get(i).toLowerCase();
            name = firstCharToUpper(name);

            if (name.contains(" ")) {
                String temp = "";
                String[] partialsNames = name.split(" ");
                for (int j = 1; j < partialsNames.length; j++) {
                    temp += firstCharToUpper(partialsNames[j]);
                }
                name = temp;
            }
            nomesBairros.set(i, name);
        }
        return nomesBairros;
    }

    private String firstCharToUpper(String name) {
        char[] nameChar = name.toCharArray();
        nameChar[0] = Character.toUpperCase(nameChar[0]);
        name = new String(nameChar);
        return name;
    }
}
