package edu.myrza.exampletwo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        String fileAbsolutePath = "C:\\Users\\Myrzabek\\Desktop\\GOT_mottos.txt";

        //display content
        String res = processFile(
        (BufferedReader br) -> {

            String result = "";
            String line;
            while ((line = br.readLine()) != null){
                result += (line + "\n");
            }
            return result;

        },
        fileAbsolutePath);

        System.out.println(res);

    }

    public static String processFile(BufferedReaderProcessor brp,String fileAbsolutePath){

        try(BufferedReader br =
                    new BufferedReader(new FileReader(fileAbsolutePath)))
        {
           String result =  brp.process(br);
           return result;
        }catch (IOException ex){
            ex.printStackTrace();
        }

        return null;
    }
}
