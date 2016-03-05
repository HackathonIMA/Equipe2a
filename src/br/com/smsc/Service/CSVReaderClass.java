package br.com.smsc.Service;

import au.com.bytecode.opencsv.CSVReader;
import br.com.smsc.Entity.Doenca;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by danielamorais on 3/5/16.
 */
public class CSVReaderClass {

    public void getCSVContent() {
        Doenca dados = new Doenca();
        ArrayList<String> faixaEtaria = new ArrayList<>();

        try {
            CSVReader reader = new CSVReader(new FileReader(getClass().getResource("/csv/coqueluche.csv").getFile()));
            String[] nextLine;
            int i = 0;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    String content = nextLine[i];
                    if (content.contains("Bairro Resid\";")) {
                        String[] fields = content.split(";");
                        for (int j = 0; j < fields.length - 1; j++) {
                            if (fields[j + 1].contains("Total")) break;
                            faixaEtaria.add(j, fields[j + 1]);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    i = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : faixaEtaria) {
            System.out.println(s);
        }


    }
}
