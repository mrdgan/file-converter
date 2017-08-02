/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrdgan.fileconverter;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author DGN
 */
public abstract class converter {

    protected InputStream inStream;
    protected OutputStream outStream;

    public converter(InputStream inStream, OutputStream outStream) {
        this.inStream = inStream;
        this.outStream = outStream;

    }

    public abstract void convert();

}
