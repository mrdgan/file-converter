/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.usermodel.SlideShow;
/**
 *
 * @author DGN
 */
public class ppttopdfconverter extends converter {

    public ppttopdfconverter(InputStream inStream, OutputStream outStream) {
        super(inStream, outStream);
    }

    @Override
    public void convert() {
        //load any ppt file
        try {

            SlideShow ppt = new SlideShow(inStream);
            Dimension pgsize = ppt.getPageSize();
            Document document = new Document();
            try {

                Slide[] slide = ppt.getSlides();
                System.out.println("test");
                PdfWriter pdfWriter = PdfWriter.getInstance(document, outStream);
                PdfContentByte cb = new PdfContentByte(pdfWriter);
                document.open();
                pdfWriter.clearTextWrap();
                //  cb.beginText();
                for (int i = 0; i < slide.length; i++) {
                    try {
                        PdfGraphics2D graphics4 = (PdfGraphics2D) pdfWriter.getDirectContent().createGraphics((float) pgsize.getWidth(), (float) pgsize.getHeight());
                        slide[i].draw(graphics4);
                        graphics4.dispose();
                        document.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                        document.newPage();
                    } catch (Exception e) {
                    }
                }

                // cb.endText();
                document.close();
                pdfWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
