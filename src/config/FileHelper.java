/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 *
 * @author A
 */
public class FileHelper {
    public InputStream byteToInputStream(byte[] bytes) {
        InputStream input = new ByteArrayInputStream(bytes);
        return input;
    }
}
