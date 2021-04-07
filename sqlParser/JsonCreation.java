package com.planlytx.sqlParser;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonCreation {
    public void createJson(){
        JsonObject jsonObject = new JsonObject();
        String fileName;
        File dir = new  File("F:\\planlytx\\wave1\\sql\\scripts");
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.getName().endsWith(".sql")){
                fileName = file.getName();
                String fileNameNoSuffix = fileName.substring(0,fileName.length() - 4);
                //System.out.println(noSuffix.toUpperCase());
                jsonObject.addProperty(fileNameNoSuffix.toUpperCase(),fileName);
            }
        }
        try{
            FileWriter fileWriter = new FileWriter("F:/planlytx/wave1/sql/mapping/ds_mapping_sql.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

