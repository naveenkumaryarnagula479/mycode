package com.planlytx.sqlParser;

import java.io.*;
import java.sql.*;
import java.util.*;
import com.planlytx.util.*;
import com.planlytx.dto.*;
import com.planlytx.util.PropertyReader;
import com.planlytx.gson.pojo.*;
import com.planlytx.gson.pojolist.*;
import com.planlytx.gson.exclusion.*;
import com.planlytx.appbuilder.*;

import org.json.*;
import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.csv.CSVRecord;

import org.apache.commons.cli.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;


public class ReadSqlData {
    private static final String MAPPING_JSON ="ds_mapping.json";
    private static final String SQL_FILE_KEY ="data_science_ui.sql";
    private String propertyFile;
    String sql;
    private LinkedHashMap<String, String> stmtMap = new LinkedHashMap<String, String>();
    public void setPropertyFile(String propertyFile) throws Exception {
        this.propertyFile = propertyFile;
    }
    public void readData() throws IOException {
//        JSONParser parser = new JSONParser();
//
//        Object obj = null;
//        try {
//            obj = parser.parse(new FileReader("F:\\ds_mapping_sql.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        JSONObject jsonObject = (JSONObject) obj;
//
//        Iterator<String> keys = jsonObject.keySet().iterator();
//        while( keys.hasNext() ){
//            String key = (String)keys.next();
//            First key in your json object
        /* FROM THE ABOVE JSON FILE I NEED TO GIVE THE KEY VALUES AS SQL_FILE_KEY
        BUT HERE I HAVE HARDCODED THE VALUES BY LET KNOW THE CODE IS WORKING OR NOT BUT HERE IAM
                GETTING NULL VALUE HAS OUTPUT
                */
        
        try {
            stmtMap = SQLMapUtil.getStatementMap(propertyFile, MAPPING_JSON, SQL_FILE_KEY);
            sql = stmtMap.get("global_business_calendar");
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        }

    public static void main(String[] args) throws Exception {
        Logger logger = LoggerFactory.getLogger(ReadSqlData.class);
        Options options = new Options();
        Option.Builder builder;
        builder = Option.builder("p").required(true).longOpt("proeprtyfile")
                .argName("propertyfile").hasArg().desc("Property File");
        options.addOption(builder.build());
        builder = Option.builder("t").required(true).longOpt("tenant_id")
                .type(Number.class)
                .argName("tenant_id").hasArg().desc("tenant id");
        options.addOption(builder.build());
        builder = Option.builder("o").required(true).longOpt("output_file")
                .argName("output_file").hasArg().desc("output_file");
        options.addOption(builder.build());
        builder = Option.builder("j1").required(true).longOpt("jsonfile")
                .argName("jsonfile").hasArgs().valueSeparator(',').desc("json file");
        options.addOption(builder.build());
        builder = Option.builder("ctrl").required(true).longOpt("controlfile")
                .argName("controlfile").hasArgs().valueSeparator(',').desc("control file");
        options.addOption(builder.build());
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("ReadSqlData", options);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonCreation jsonCreation = new JsonCreation();
        jsonCreation.createJson();
        ReadSqlData readSqlData = new ReadSqlData();
        readSqlData.readData();
        System.out.println("hi");
    }
}