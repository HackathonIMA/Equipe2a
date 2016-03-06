package br.com.smsc.Service;

import br.com.smsc.Entity.Informacoes;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by danielamorais on 3/5/16.
 */
public class DataReaderTest {

    @Test
    public void testGetCSVContent_successConceicaoCoqueluche() throws Exception {
        Map<String, String> actual = new DataReader().getCSVContent(2015, "coqueluche").getListaCampos().get(0);
        HashMap<String, String> campos = new LinkedHashMap<>();
        campos.put("bairro", "Conceicao");
        campos.put("<1 Ano", "1");
        campos.put("5-9", "0");
        campos.put("10-14", "1");
        campos.put("20-34", "0");
        campos.put("50-64", "0");
        campos.put("Total", "2");
        Assert.assertEquals(campos.get("bairro"), actual.get("bairro"));
        Assert.assertEquals(campos.get("<1 Ano"), actual.get("<1 Ano"));
        Assert.assertEquals(campos.get("5-9"), actual.get("5-9"));
        Assert.assertEquals(campos.get("10-14"), actual.get("10-14"));
        Assert.assertEquals(campos.get("20-34"), actual.get("20-34"));
        Assert.assertEquals(campos.get("50-64"), actual.get("50-64"));
        Assert.assertEquals(campos.get("Total"), actual.get("Total"));
    }

    @Test
    public void testGetCSVContent_errorConceicaoCoqueluche() throws Exception {
        Map<String, String> actual = new DataReader().getCSVContent(2015, "coqueluche").getListaCampos().get(0);
        HashMap<String, String> campos = new LinkedHashMap<>();
        campos.put("bairro", "conceicao");
        campos.put("<1 Ano", "0");
        campos.put("5-9", "5");
        campos.put("10-14", "7");
        campos.put("20-34", "8");
        campos.put("50-64", "9");
        Assert.assertNotEquals(campos.get("bairro"), actual.get("bairro"));
        Assert.assertNotEquals(campos.get("<1 Ano"), actual.get("<1 Ano"));
        Assert.assertNotEquals(campos.get("5-9"), actual.get("5-9"));
        Assert.assertNotEquals(campos.get("10-14"), actual.get("10-14"));
        Assert.assertNotEquals(campos.get("20-34"), actual.get("20-34"));
        Assert.assertNotEquals(campos.get("50-64"), actual.get("50-64"));
    }
}