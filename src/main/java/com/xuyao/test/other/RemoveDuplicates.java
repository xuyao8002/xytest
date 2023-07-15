package com.xuyao.test.other;

import java.io.*;
import java.util.*;

public class RemoveDuplicates {
    private static final int MAX_CHUNK_SIZE = 10; // Adjust this based on available memory
    //每行一条记录
    private static final String INPUT_FILE = "E:\\accounts.txt";
    private static final String OUTPUT_FILE = "E:\\accounts_deduplicated.txt";

    public static void main(String[] args) throws IOException {
        List<File> sortedChunks = sortChunks(INPUT_FILE);
        mergeAndRemoveDuplicates(sortedChunks, OUTPUT_FILE);
    }

    private static List<File> sortChunks(String inputFile) throws IOException {
        List<File> sortedChunks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<Long> chunk = new ArrayList<>(MAX_CHUNK_SIZE);

        String line;
        while ((line = reader.readLine()) != null) {
            chunk.add(Long.parseLong(line));

            if (chunk.size() == MAX_CHUNK_SIZE) {
                sortedChunks.add(sortAndSaveChunk(chunk));
                chunk.clear();
            }
        }

        if (!chunk.isEmpty()) {
            sortedChunks.add(sortAndSaveChunk(chunk));
        }

        reader.close();
        return sortedChunks;
    }

    private static File sortAndSaveChunk(List<Long> chunk) throws IOException {
        Collections.sort(chunk);
        File sortedChunkFile = File.createTempFile("sorted_chunk_", ".txt");
        sortedChunkFile.deleteOnExit();

        BufferedWriter writer = new BufferedWriter(new FileWriter(sortedChunkFile));
        for (Long qqAccount : chunk) {
            writer.write(qqAccount.toString());
            writer.newLine();
        }

        writer.close();
        return sortedChunkFile;
    }

    private static void mergeAndRemoveDuplicates(List<File> sortedChunks, String outputFile) throws IOException {
        PriorityQueue<ChunkReader> queue = new PriorityQueue<>(Comparator.comparingLong(ChunkReader::currentValue));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        List<BufferedReader> readers = new ArrayList<>();

        for (File chunkFile : sortedChunks) {
            BufferedReader reader = new BufferedReader(new FileReader(chunkFile));
            readers.add(reader);
            ChunkReader chunkReader = new ChunkReader(reader);
            if (chunkReader.next()) {
                queue.add(chunkReader);
            }
        }

        Long current = null;
        while (!queue.isEmpty()) {
            ChunkReader chunkReader = queue.poll();

            if (current == null || !current.equals(chunkReader.currentValue())) {
                current = chunkReader.currentValue();
                writer.write(current.toString());
                writer.newLine();
            }

            if (chunkReader.next()) {
                queue.add(chunkReader);
            }
        }

        writer.close();
        for (BufferedReader reader : readers) {
            reader.close();
        }
    }

    private static class ChunkReader {
        private final BufferedReader reader;
        private Long currentValue;

        public ChunkReader(BufferedReader reader) {
            this.reader = reader;
        }

        public boolean next() throws IOException {
            String line = reader.readLine();
            if (line != null) {
                currentValue = Long.parseLong(line);
                return true;
            }
            return false;
        }

        public Long currentValue() {
            return currentValue;
        }
    }
}