/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.docx4j.Docx4J;
import org.docx4j.convert.in.Doc;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 *
 * @author DGN
 */
public class doctopdfconverter extends converter {

    public doctopdfconverter(InputStream inStream, OutputStream outStream) {
        super(inStream, outStream);
    }

    @Override
    public void convert() {

        InputStream iStream = inStream;

        WordprocessingMLPackage wordMLPackage = null;
        try {
            wordMLPackage = getMLPackage(iStream);
        } catch (Exception ex) {
            Logger.getLogger(doctopdfconverter.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Docx4J.toPDF(wordMLPackage, outStream);
        } catch (Docx4JException ex) {
            Logger.getLogger(doctopdfconverter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected WordprocessingMLPackage getMLPackage(InputStream iStream) throws Exception {
        PrintStream originalStdout = System.out;

        //Disable stdout temporarily as Doc convert produces alot of output
        System.setOut(new PrintStream(new OutputStream() {
            public void write(int b) {
                //DO NOTHING
            }
        }));

        WordprocessingMLPackage mlPackage = Doc.convert(iStream);

        System.setOut(originalStdout);
        return mlPackage;
    }
}
