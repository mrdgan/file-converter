/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author DGN
 */
public class maintest {

    static InputStream inStream;
    static OutputStream outStream;

    public static void main(String[] args) {
        maintest main = new maintest("C:/Users/DGN/Desktop/pdf/tez.docx", "C:/Users/DGN/Desktop/pdf/tez.pdf");
    }

    public maintest(String input, String output) {
        converter converter;

        try {
            converter = process(input, output);
        } catch (Exception e) {
            System.out.println("\n\nInput\\Output file not specified properly.");
            return;
        }

        if (converter == null) {
            System.out.println("Unable to determine type of input file.");
        } else {
            try {
                converter.convert();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public converter process(String input, String output) {
        converter convert = null;
        try {
            String inPath = input;
            String outPath = output;
            String lowerCaseInPath = inPath.toLowerCase();
            InputStream inStream = getInFileStream(inPath);
            OutputStream outStream = getOutFileStream(outPath);
            if (input != null) {
                if (lowerCaseInPath.endsWith("doc")) {
                    convert = new doctopdfconverter(inStream, outStream);
                } else if (lowerCaseInPath.endsWith("docx")) {
                    convert = new docxtopdfconverter(inStream, outStream);
                } else if (lowerCaseInPath.endsWith("txt")) {
                    convert = new txttopdfconverter(inStream, outStream);
                } else if (lowerCaseInPath.endsWith("ppt")) {
                     convert = new ppttopdfconverter(inStream, outStream);
                } else if (lowerCaseInPath.endsWith("odt")) {
                    convert = new odttopdfconverter(inStream, outStream);
                } else {
                    convert = null;
                }

            }

            return convert;
        } catch (Exception e) {
            return null;
        }

    }

    protected static InputStream getInFileStream(String inputFilePath) throws FileNotFoundException {
        File inFile = new File(inputFilePath);
        FileInputStream iStream = new FileInputStream(inFile);
        return iStream;
    }

    protected static OutputStream getOutFileStream(String outputFilePath) throws IOException {
        File outFile = new File(outputFilePath);

        try {
            outFile.getParentFile().mkdirs();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        outFile.createNewFile();
        FileOutputStream oStream = new FileOutputStream(outFile);
        return oStream;
    }
}
