package org.saojudas;
import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class Main {

    public static String removerAcentos(String str) {
        // Normaliza a string (decompõe caracteres acentuados)
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        // Remove os caracteres não ASCII (como acentos)
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    /*
    --------------------------------------------------------------------------
    Fase 1
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - Número de Habitantes por Estado
    Fase 1      :   Tratamento do arquivo de entrada:
                    Tratamento do arquivo de saida:
    --------------------------------------------------------------------------
    */

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        FileInputStream instream = new FileInputStream("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao.csv");
        FileWriter fileWriter = new FileWriter(new File("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao_02.csv"));

        InputStreamReader reader = new InputStreamReader(instream);
        BufferedReader br = new BufferedReader(reader);

        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        //*----------------- Definição da linha do arquivo de entrada  -------------------------------
        String linha;
        linha = br.readLine();

        //*----------------- Definição das variaveis de controle de lidos e gravados -----------------
        int Lidos = 0;
        int Gravados = 0;

        //*----------------- Leitura de todos os registros (linhas) do arquivo de entrada ------------
        while (linha != null) {
            Lidos = Lidos + 1;
            String[] campos = linha.split(",");

            // Verificar se a linha contém pelo menos dois campos
            if (campos.length < 2) {
                linha = br.readLine();
                continue; // pula para a próxima iteração se a linha não tiver pelo menos dois campos
            }
            campos[0] = removerAcentos(campos[0]);

            if     ((campos[0].equals("Acre"))
                    ||  (campos[0].equals("Alagoas"))
                    ||  (campos[0].equals("Amazonas"))
                    ||  (campos[0].equals("Amapa"))
                    ||  (campos[0].equals("Bahia"))
                    ||  (campos[0].equals("Distrito Federal"))
                    ||  (campos[0].equals("Ceara"))
                    ||  (campos[0].equals("Espirito Santo"))
                    ||  (campos[0].equals("Goias"))
                    ||  (campos[0].equals("Maranhao"))
                    ||  (campos[0].equals("Mato Grosso"))
                    ||  (campos[0].equals("Mato Grosso do Sul"))
                    ||  (campos[0].equals("Minas Gerais"))
                    ||  (campos[0].equals("Para"))
                    ||  (campos[0].equals("Paraiba"))
                    ||  (campos[0].equals("Parana"))
                    ||  (campos[0].equals("Pernambuco"))
                    ||  (campos[0].equals("Piaui"))
                    ||  (campos[0].equals("Rio de Janeiro"))
                    ||  (campos[0].equals("Rio Grande do Norte"))
                    ||  (campos[0].equals("Rio Grande do Sul"))
                    ||  (campos[0].equals("Rondonia"))
                    ||  (campos[0].equals("Roraima"))
                    ||  (campos[0].equals("Santa Catarina"))
                    ||  (campos[0].equals("Sao Paulo"))
                    ||  (campos[0].equals("Sergipe"))
                    ||  (campos[0].equals("Tocantins"))) {

                bufferedWriter.write(campos[0] + ";" + campos[1]);
                Gravados = Gravados + 1;
                bufferedWriter.newLine();

            }
            linha = br.readLine();
        }
        //*----------------- Encerramento do tratamento do arquivo -----------
        bufferedWriter.close();
        fileWriter.close();

        //*------------------------ Exibe os controles  ----------------------
        System.out.println("--------- Fase 1 ---------------");
        System.out.println("Registros lidos    = " + Lidos);
        System.out.println("Registros gravados = " + Gravados);

    /*
    --------------------------------------------------------------------------
    Fase 2
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - Classificação alfabética pelo nome do Estado
    Fase 1      :   Tratamento do arquivo de entrada:
                    Tratamento do arquivo de saida:
    --------------------------------------------------------------------------
    */
        int Gravados2 = 0;

        String str2;
        ArrayList<String> nomes2 = new ArrayList<String>();

        BufferedReader in2 = new BufferedReader(new FileReader("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao_02.csv"));

        while ((str2 = in2.readLine()) != null) {
            nomes2.add(str2);
        }
        in2.close();

        Collections.sort(nomes2);

        BufferedWriter out2 = new BufferedWriter(new FileWriter("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao_03.csv"));
        for (int i = 0; i < nomes2.size(); i++) {
            out2.write(nomes2.get(i));
            Gravados2 = Gravados2 + 1;
            out2.newLine();
        }
        out2.close();

        System.out.println("--------- Fase 2 ---------------");
        System.out.println("Registros gravados = " + Gravados2);

    /*
    --------------------------------------------------------------------------
    Fase 3
    Data        :   03 de outubro de 2024
    Objetivo    :   ETC - Transformação de nome de estado para UF
    Fase 1      :   Tratamento do arquivo de entrada:
                    Tratamento do arquivo de saida:
    --------------------------------------------------------------------------
    */
        FileInputStream instream3 = new FileInputStream("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao_03.csv");
        FileWriter fileWriter3 = new FileWriter(new File("/Users/mitz/Downloads/Aulas Material Oct 6/EstimativaPopulacao_04.csv"));

        InputStreamReader reader3 = new InputStreamReader(instream3);
        BufferedReader br3 = new BufferedReader(reader3);

        BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);

        String linha3;
        linha3 = br3.readLine();

        int Lidos3 = 0;
        int Gravados3 = 0;

        String[] UF_Estado = new String[27];

        UF_Estado[0] = "AC Acre";
        UF_Estado[1] = "AL Alagoas";
        UF_Estado[2] = "AM Amazonas";
        UF_Estado[3] = "AP Amapa";
        UF_Estado[4] = "BA Bahia";
        UF_Estado[5] = "DF Distrito Federal";
        UF_Estado[6] = "CE Ceara";
        UF_Estado[7] = "ES Espirito Santo";
        UF_Estado[8] = "GO Goias";
        UF_Estado[9] = "MA Maranhao";
        UF_Estado[10] = "MT Mato Grosso";
        UF_Estado[11] = "MS Mato Grosso do Sul";
        UF_Estado[12] = "MG Minas Gerais";
        UF_Estado[13] = "PA Para";
        UF_Estado[14] = "PB Paraiba";
        UF_Estado[15] = "PR Parana";
        UF_Estado[16] = "PE Pernambuco";
        UF_Estado[17] = "PI Piaui";
        UF_Estado[18] = "RJ Rio de Janeiro";
        UF_Estado[19] = "RN Rio Grande do Norte";
        UF_Estado[20] = "RS Rio Grande do Sul";
        UF_Estado[21] = "RO Rondonia";
        UF_Estado[22] = "RR Roraima";
        UF_Estado[23] = "SC Santa Catarina";
        UF_Estado[24] = "SP Sao Paulo";
        UF_Estado[25] = "SE Sergipe";
        UF_Estado[26] = "TO Tocantins";

        while (linha3 != null) {
            Lidos3 = Lidos3 + 1;
            String[] campos = linha3.split(";");
            for (int i = 0; i < 27; i++) {
                if (campos[0].equals(UF_Estado[i].substring(3, UF_Estado[i].length()))) {
                    campos[0] = UF_Estado[i].substring(0, 2);
                    bufferedWriter3.write(campos[0] + ";" + campos[1]);
                    Gravados3 = Gravados3 + 1;
                    bufferedWriter3.newLine();
                }
            }
            linha3 = br3.readLine();
        }
        Gravados3 = Gravados3 +1;
        bufferedWriter3.close();
        fileWriter3.close();

        System.out.println("--------- Fase 3 ---------------");
        System.out.println("Registros lidos    = " + Lidos3);
        System.out.println("Registros gravados = " + Gravados3);

    }
}