package edu.myrza.directory_reader;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;

public class DirectoryReader {
    public static void main(String[] args){

        final Integer recursionLevel = 10;
        final File    root;
        //arg0 path to directory
        //todo add command argument parser
        checkCondition(() -> args.length == 0,"type file path you dumbass");
        root = new File(args[0]);
        checkCondition(() -> !root.exists(),"file doesn't exist");
        checkCondition(() -> !root.isDirectory(),"not a directory");

        displayContent(root,recursionLevel);

    }

    private static Comparator<File> getComparator(){

        Comparator<File> filesFirstComparator = (f1,f2) -> {
            if(!f1.isDirectory() && f2.isDirectory()) return -1;
            if(f1.isDirectory() && !f2.isDirectory()) return 1;
            return 0;
        };

        Comparator<File> directoryContentLengthComparator = (f1,f2) -> {
            if(f1.isDirectory() && f2.isDirectory())
                     if(f1.listFiles().length < f2.listFiles().length) return -1;
                else if(f1.listFiles().length > f2.listFiles().length) return  1;
            return 0;
        };

        return filesFirstComparator.thenComparing(directoryContentLengthComparator);

    }

    private static void checkCondition(Supplier<Boolean> condition,String messageIfFalse){
        if(condition.get()){
            System.out.println(messageIfFalse);
            System.exit(1);
        }
    }

    private static void displayContent(File directory,Integer recursionLevel){
        System.out.println(directory.getName());
        displayContent(directory,getComparator(),recursionLevel,"\t");
    }

    private static void displayContent(File directory, Comparator<File> fileComparator, Integer recursionLevel, String tabs){

        if(recursionLevel.equals(0)) return;

        List<File> content = Arrays.asList(directory.listFiles());
        content.sort(fileComparator);

        System.out.println(tabs + "|");
        for(File f : content){
            if(f.isDirectory()) {
                System.out.println(tabs + "[DIR]+--" + f.getName());
                displayContent(f, fileComparator, recursionLevel - 1, tabs + "\t\t");
                }
            else
                System.out.println(tabs + "[FILE]+--" + f.getName());
        }

    }
}
