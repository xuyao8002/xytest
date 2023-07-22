package com.xuyao.test.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSplitter {


    public static void splitBySize(String inputFile, String outputDir, int chunkSize) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFile);
        
             BufferedInputStream bufferedInput = new BufferedInputStream(inputStream, chunkSize)) {
            byte[] buffer = new byte[chunkSize];
            int bytesRead = 0;
            int index = 0;
            while ((bytesRead = bufferedInput.read(buffer)) > 0) {
                String fileName = outputDir + File.separator + "part-" + index;
                try (FileOutputStream outputStream = new FileOutputStream(fileName);
                     BufferedOutputStream bufferedOutput = new BufferedOutputStream(outputStream, chunkSize)) {
                    bufferedOutput.write(buffer, 0, bytesRead);
                }
                index++;
            }
        }
    }

    public static void splitByLine(String inputFile, String outputDir, int chunkSize) throws IOException {
        try (FileReader reader = new FileReader(inputFile);
        
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            String line;
            int lineCount = 0;
            int index = 0;
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDir + File.separator + "part-" + index));
            while((line = bufferedReader.readLine()) != null){
                writer.write(line);
                writer.newLine();
                lineCount++;
                if(lineCount >= chunkSize){
                    writer.close();
                    index++;
                    lineCount = 0;
                    writer = new BufferedWriter(new FileWriter(outputDir + File.separator + "part-" + index));
                }
            }
            writer.close();
        }
    }


    public static void main(String[] args) throws IOException {
        String inputFile = "";
        String outputDir = "";
        int chunkSize = 1 * 1024 * 1024; // 每个小文件的大小，单位为MB
        // splitBySize(inputFile, outputDir, chunkSize);
        int lineCount = 1 * 100 * 1000;
        splitByLine(inputFile, outputDir, lineCount);
    }
}
