/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import java.io.InputStream;
import java.io.OutputStream;
import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
/**
 *
 * @author DGN
 */
public class odttopdfconverter extends converter {

    public odttopdfconverter(InputStream inStream, OutputStream outStream) {
        super(inStream, outStream);
    }

    @Override
    public void convert() {
        try {
            OdfTextDocument document = (OdfTextDocument) OdfTextDocument.loadDocument(inStream);
            PdfOptions options = PdfOptions.create();
            PdfConverter.getInstance().convert(document, outStream, options);
        } catch (Exception e) {
        }
    }

}
