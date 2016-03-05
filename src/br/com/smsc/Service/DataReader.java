package br.com.smsc.Service;

import au.com.bytecode.opencsv.CSVReader;
import br.com.smsc.Entity.IdadeInfo;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danielamorais on 3/5/16.
 */
//FIXME: Refactor e logs
public class DataReader {
    public void getCSVContent() {
        HashMap<String, List<IdadeInfo>> infoBairros = new HashMap<>();
        List<IdadeInfo> idadesInfoList = new ArrayList<>();
        String[] nextLine;
        IdadeInfo info = new IdadeInfo();
        try {
            CSVReader reader = new CSVReader(new FileReader(getClass().getResource("/csv/coqueluche.csv").getFile()));
            while ((nextLine = reader.readNext()) != null) {
                String content = nextLine[0];
                //FAIXAS DE IDADES
                if (content.contains("Bairro Resid\";")) {
                    String[] fields = content.split(";");
                    for (int j = 0; j < fields.length; j++) {
                        if (j + 1 >= fields.length - 1) break;
                        if (fields[j + 1].contains("Total")) break;
                        idadesInfoList.add(j, new IdadeInfo().setIdade(fields[j + 1]));
                    }
                }
                //VALORES POR CADA IDADE
                if (content.contains("AREA")) {
                    String[] areaDados = content.split("\"");
                    //Irá formatar dados desta linha
                    String[] numeros = areaDados[1].split(";");
                    for (int j = 1; j <= idadesInfoList.size(); j++) {
                        info = idadesInfoList.get(j - 1);
                        idadesInfoList.set(j - 1, info.setValor(Integer.parseInt(numeros[j])));
                    }
                    String[] temp = areaDados[0].split(".*AREA ABRANG CS\\s");
                    infoBairros.put(formatData(temp[1]), idadesInfoList);
                }
            }

            System.out.println("end");
        } catch (IOException e) {
            System.err.println("Erro ao encontrar arquivo");
        }

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
