package com.xuyao.test.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LineNumberReader {

    public static void main(String[] args) throws IOException {
        File file = new File("");
        getLines(file);
    }

    private static long getLines(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        java.io.LineNumberReader lineNumberReader = new java.io.LineNumberReader(fileReader);
        lineNumberReader.skip(Long.MAX_VALUE);
        long lines = lineNumberReader.getLineNumber();
        fileReader.close();
        lineNumberReader.close();
        return lines;
    }

}
