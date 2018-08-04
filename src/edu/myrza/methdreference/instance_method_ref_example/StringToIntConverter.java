package edu.myrza.methdreference.instance_method_ref_example;

public class StringToIntConverter {

    private String name;

    public StringToIntConverter(String name) {
        this.name = name;
    }

    public Integer conv(String string){
        return Integer.valueOf(string);
    }

    @Override
    public String toString() {
        return "StringToIntConverter{" +
                "name='" + name + '\'' +
                '}';
    }
}
