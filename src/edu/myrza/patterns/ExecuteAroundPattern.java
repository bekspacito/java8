package edu.myrza.patterns;

import java.io.*;

public class ExecuteAroundPattern {

    public static void main(String[] args){

        ExecuteAroundPattern pattern = new ExecuteAroundPattern();

        String resultOne = pattern.processFile(br -> {

            StringBuilder builder = new StringBuilder();
            try {

                String line = null;
                while ((line = br.readLine()) != null) {
                    builder.append(line);
                    builder.append('\n');
                }

            }catch (IOException ex){
                ex.printStackTrace();
                return null;
            }
            return builder.toString();

        },"C:\\Users\\Myrzabek\\Desktop\\tutorials\\coursesAndBooks.txt");

        System.out.println(resultOne);


    }

    private String processFile(BufferedReaderProcessor processor,String filePath){
        try(BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                        new FileInputStream(filePath)
                                    )))
        {
            return processor.process(br);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    private interface BufferedReaderProcessor{
        String process(BufferedReader br);
    }

}
