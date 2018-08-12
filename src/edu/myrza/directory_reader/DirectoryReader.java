package edu.myrza.directory_reader;

import java.io.File;
import java.util.function.Supplier;

public class DirectoryReader {
    public static void main(String[] args){

        final Integer recursionLevel = 4;
        final File    root;
        //arg0 path to directory
        //check if file exists
        //check if it's a directory File.isDirectory()
        //show directory content along with directory content of subclasses
        //checkCondition(() -> args.length == 0,"type file path you dumbass");//set filepath you dumbass
        root = new File("C:\\Users\\Myrzabek\\Desktop\\internship");
        checkCondition(() -> !root.exists(),"file doesn't exist");
        checkCondition(() -> !root.isDirectory(),"not a directory");

        displayContent(root,recursionLevel);

    }

    private static void checkCondition(Supplier<Boolean> condition,String messageIfFalse){
        if(condition.get()){
            System.out.println(messageIfFalse);
            System.exit(1);
        }
    }

    private static void displayContent(File root,Integer recursionLevel){
        displayContent(root,recursionLevel,"\t");
    }

    private static void displayContent(File root,Integer recursionLevel,String tabs){

        if(recursionLevel.equals(0)) return;

        File[] content = root.listFiles();

        for(File f : content){
            System.out.println(tabs + f.getName());
            if(f.isDirectory())
                displayContent(f, recursionLevel - 1,tabs + "\t");
        }

    }
}
