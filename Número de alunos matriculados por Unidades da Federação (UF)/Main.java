
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static String removerAcentos(String str) {
        // Normaliza a string (decompõe caracteres acentuados)
        String normalized = Normalizer.normalize(str, Normalizer.Form.NFD);
        // Remove os caracteres não ASCII (como acentos)
        return normalized.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }

    // Função para ler um arquivo CSV e atualizar o mapa com os valores somados
    public static void lerCsvEAtualizarMapa(String filePath, Map<String, Integer> ufMatriculados) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String linha;
        br.readLine(); // Pula o cabeçalho

        // Leitura do arquivo CSV linha por linha
        while ((linha = br.readLine()) != null) {
            String[] campos = linha.split(";");
            String uf = campos[0].trim();
            int matriculados;

            try {
                matriculados = Integer.parseInt(campos[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter número: " + campos[1]);
                continue;
            }

            // Atualiza o mapa com a soma dos valores
            ufMatriculados.put(uf, ufMatriculados.getOrDefault(uf, 0) + matriculados);
        }
        br.close();
    }

    /*
     * --------------------------------------------------------------------------
     * Fase 1
     * Data : 03 de outubro de 2024
     * Objetivo : ETC - Número de Habitantes por Estado
     * Fase 1 : Tratamento do arquivo de entrada:
     * Tratamento do arquivo de saida:
     * --------------------------------------------------------------------------
     */
    public static void main(String[] args)
            throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        // Por ter dois arquivos para serem lidos Matriculados_1 por UF.csv e
        // Matriculados_2 por UF.csv o for serve para apenas mudar o numero e o
        // "main" ser percorrido duas vezes, assim fazendo o processo igual para os dois
        // arquivos
        for (int matriculadosParte = 1; matriculadosParte < 3; matriculadosParte++) {

            System.out.println("\nLimpeza de dados para o arquivo: Matriculados_" + String.valueOf(matriculadosParte)
                    + " por UF.csv");

            // *----------------- Definição dos paths ------------
            String path1 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_"
                    + String.valueOf(matriculadosParte) + " por UF.csv";
            String path2 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_"
                    + String.valueOf(matriculadosParte) + " por UF_2.csv";
            String path3 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_"
                    + String.valueOf(matriculadosParte) + " por UF_3.csv";
            String path4 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_"
                    + String.valueOf(matriculadosParte) + " por UF_4.csv";

            FileInputStream instream = new FileInputStream(path1);
            FileWriter fileWriter = new FileWriter(new File(path2));

            InputStreamReader reader = new InputStreamReader(instream);
            BufferedReader br = new BufferedReader(reader);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // *----------------- Definição da linha do arquivo de entrada
            // -------------------------------
            String linha;
            linha = br.readLine();

            // *----------------- Definição das variaveis de controle de lidos e gravados
            // -----------------
            int Lidos = 0;
            int Gravados = 0;

            // *----------------- Leitura de todos os registros (linhas) do arquivo de
            // entrada ------------
            while (linha != null) {
                Lidos = Lidos + 1;
                String[] campos = linha.split(",");
                int totalCampos = 0;

                // Verificar se a linha contém pelo menos um campo
                if (campos.length < 1) {
                    linha = br.readLine();
                    continue; // pula para a próxima iteração se a linha não tiver pelo menos dois campos
                }
                campos[0] = removerAcentos(campos[0]);

                if ((campos[0].equalsIgnoreCase("Acre"))
                        || (campos[0].equalsIgnoreCase("Alagoas"))
                        || (campos[0].equalsIgnoreCase("Amazonas"))
                        || (campos[0].equalsIgnoreCase("Amapa"))
                        || (campos[0].equalsIgnoreCase("Bahia"))
                        || (campos[0].equalsIgnoreCase("Distrito Federal"))
                        || (campos[0].equalsIgnoreCase("Ceara"))
                        || (campos[0].equalsIgnoreCase("Espirito Santo"))
                        || (campos[0].equalsIgnoreCase("Goias"))
                        || (campos[0].equalsIgnoreCase("Maranhao"))
                        || (campos[0].equalsIgnoreCase("Mato Grosso"))
                        || (campos[0].equalsIgnoreCase("Mato Grosso do Sul"))
                        || (campos[0].equalsIgnoreCase("Minas Gerais"))
                        || (campos[0].equalsIgnoreCase("Para"))
                        || (campos[0].equalsIgnoreCase("Paraiba"))
                        || (campos[0].equalsIgnoreCase("Parana"))
                        || (campos[0].equalsIgnoreCase("Pernambuco"))
                        || (campos[0].equalsIgnoreCase("Piaui"))
                        || (campos[0].equalsIgnoreCase("Rio de Janeiro"))
                        || (campos[0].equalsIgnoreCase("Rio Grande do Norte"))
                        || (campos[0].equalsIgnoreCase("Rio Grande do Sul"))
                        || (campos[0].equalsIgnoreCase("Rondonia"))
                        || (campos[0].equalsIgnoreCase("Roraima"))
                        || (campos[0].equalsIgnoreCase("Santa Catarina"))
                        || (campos[0].equalsIgnoreCase("Sao Paulo"))
                        || (campos[0].equalsIgnoreCase("Sergipe"))
                        || (campos[0].equalsIgnoreCase("Tocantins"))) {

                    String nomeEstadoAtual = campos[0];
                    String linhaTemporaria = "";
                    for (int i = 0; i < 5; i++) {
                        linhaTemporaria = br.readLine();
                    }
                    campos = linhaTemporaria.split(",");

                    for (int i = 1; i < campos.length; i++) {
                        try {
                            // Tente converter cada campo para inteiro e somar
                            totalCampos += Integer.parseInt(campos[i].trim());
                        } catch (NumberFormatException e) {
                            // Se não for um número válido, pule
                            System.out.println("Erro ao converter campo para número: " + campos[i]);
                        }
                    }

                    bufferedWriter.write(nomeEstadoAtual + ";" + totalCampos);
                    Gravados = Gravados + 1;
                    bufferedWriter.newLine();
                }
                linha = br.readLine();
            }
            // *----------------- Encerramento do tratamento do arquivo -----------
            bufferedWriter.close();
            fileWriter.close();

            // *------------------------ Exibe os controles ----------------------
            System.out.println("--------- Fase 1 ---------------");
            System.out.println("Registros lidos    = " + Lidos);
            System.out.println("Registros gravados = " + Gravados);

            /*
             * --------------------------------------------------------------------------
             * Fase 2
             * Data : 03 de outubro de 2024
             * Objetivo : ETC - Classificação alfabética pelo nome do Estado
             * Fase 1 : Tratamento do arquivo de entrada:
             * Tratamento do arquivo de saida:
             * --------------------------------------------------------------------------
             */
            int Gravados2 = 0;

            String str2;
            ArrayList<String> nomes2 = new ArrayList<String>();

            BufferedReader in2 = new BufferedReader(new FileReader(path2));

            while ((str2 = in2.readLine()) != null) {
                nomes2.add(str2);
            }
            in2.close();

            Collections.sort(nomes2);

            BufferedWriter out2 = new BufferedWriter(new FileWriter(path3));
            for (int i = 0; i < nomes2.size(); i++) {
                out2.write(nomes2.get(i));
                Gravados2 = Gravados2 + 1;
                out2.newLine();
            }
            out2.close();

            System.out.println("--------- Fase 2 ---------------");
            System.out.println("Registros gravados = " + Gravados2);

            /*
             * --------------------------------------------------------------------------
             * Fase 3
             * Data : 03 de outubro de 2024
             * Objetivo : ETC - Transformação de nome de estado para UF
             * Fase 1 : Tratamento do arquivo de entrada:
             * Tratamento do arquivo de saida:
             * --------------------------------------------------------------------------
             */
            FileInputStream instream3 = new FileInputStream(path3);
            FileWriter fileWriter3 = new FileWriter(new File(path4));

            InputStreamReader reader3 = new InputStreamReader(instream3);
            BufferedReader br3 = new BufferedReader(reader3);

            BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);

            String linha3;
            linha3 = br3.readLine();

            int Lidos3 = 0;
            int Gravados3 = 0;

            // Um array de 27 posições chamado UF_Estado é criado, onde cada posição guarda
            // uma string que contém a sigla do estado brasileiro seguida de seu nome
            // completo.
            // Cada posição contém uma entrada como “AC Acre”, onde as primeiras duas
            // letras são a sigla do estado e o restante é o nome completo.
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
                // System.out.println(linha3.toString());

                // O for percorre todas as posições do array UF_Estado (os 27 estados
                // brasileiros).
                for (int i = 0; i < 27; i++) {
                    // Para cada estado, ele verifica se o conteúdo do campo campos[0] (o primeiro
                    // campo da linha, que provavelmente contém o nome do estado) corresponde ao
                    // nome do estado armazenado em UF_Estado[i]. Isso é feito com o método
                    // substring(3, UF_Estado[i].length()), que “corta” as três primeiras letras (a
                    // sigla + espaço) da string, deixando apenas o nome do estado.
                    if (campos[0].equalsIgnoreCase(UF_Estado[i].substring(3, UF_Estado[i].length()))) {
                        campos[0] = UF_Estado[i].substring(0, 2);
                        bufferedWriter3.write(campos[0] + ";" + campos[1]);
                        Gravados3 = Gravados3 + 1;
                        bufferedWriter3.newLine();
                    }
                }
                linha3 = br3.readLine();
            }
            Gravados3 = Gravados3 + 1;
            bufferedWriter3.close();
            fileWriter3.close();

            System.out.println("--------- Fase 3 ---------------");
            System.out.println("Registros lidos = " + Lidos3);
            System.out.println("Registros gravados = " + Gravados3);
        }
        /*
         * --------------------------------------------------------------------------
         * Fase 4
         * Data : 03 de outubro de 2024
         * Objetivo : Junção dos arquivos Matriculados_1 e Matriculados_2
         * --------------------------------------------------------------------------
         */

        // Paths Matriculados_1 e Matriculados_2 e Matriculados_3
        String path_matriculados1 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_1 por UF_4.csv";
        String path_matriculados2 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_2 por UF_4.csv";
        String path_matriculados3 = "/Users/mitz/Documents/Development/AnaliseDeDados/Número de alunos matriculados por Unidades da Federação (UF)/Matriculados_3 por UF.csv";

        // Mapa para armazenar a soma de "matriculados" por UF
        Map<String, Integer> ufMatriculados = new HashMap<>();

        // Função para ler os arquivos e atualizar o mapa
        lerCsvEAtualizarMapa(path_matriculados1, ufMatriculados);
        lerCsvEAtualizarMapa(path_matriculados2, ufMatriculados);

        // Escreve o resultado combinado em um novo arquivo CSV
        FileWriter fileWriter = new FileWriter(path_matriculados3);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // Escreve cabeçalho
        bufferedWriter.write("UF;MATRICULADOS");
        bufferedWriter.newLine();

        // Escreve os dados acumulados de "matriculados" por UF
        for (Map.Entry<String, Integer> entry : ufMatriculados.entrySet()) {
            bufferedWriter.write(entry.getKey() + ";" + entry.getValue());
            bufferedWriter.newLine();
        }

        // Fecha o arquivo
        bufferedWriter.close();
        fileWriter.close();

        System.out.println("--------- Fase 4 ---------------");
        System.out.println("Arquivo combinado criado com sucesso: " + path_matriculados3);

    }

}
