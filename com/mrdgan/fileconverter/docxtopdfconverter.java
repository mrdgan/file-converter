/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 *
 * @author DGN
 */
public class docxtopdfconverter extends converter {

    public docxtopdfconverter(InputStream inStream, OutputStream outStream) {
        super(inStream, outStream);
    }

    @Override
    public void convert() {
        try {
            XWPFDocument document = new XWPFDocument(inStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, outStream, options);

        } catch (Exception e) {
        }
    }

}
