package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Logger {

    private static final String FILE_NAME = "log_simulacao.txt";

    public static void log(String info) {
        try {
            FileWriter x = new FileWriter(FILE_NAME,true);
            StringBuilder sb = new StringBuilder();
            sb.append("\n [").append(DateHourUtils.formattedTime()).append("] ").append(info);
            x.write(sb.toString());
            x.close();
        }
        catch(Exception e) {
            new Exception("Erro na gravação do arquivo!", e).printStackTrace();
        }
    }
    
    public static String getLog() {
        String result = "";
        try {
            File logFile = new File(FILE_NAME);
            BufferedReader br = new BufferedReader(new FileReader(logFile));
            StringBuilder sb = new StringBuilder();
            String linha;
            while( (linha = br.readLine()) != null ) {
                sb.append(linha);
            }
            
            result = sb.toString();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
