package com.epam.brest;

import java.io.*;
import java.util.List;

public class Serializator {

    public boolean serialization(List list, String fileName) {
        boolean flag = false;
        File f = new File(fileName);
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            if (fos != null) {
                ostream = new ObjectOutputStream(fos);
                ostream.writeObject(list); // сериализация
                flag = true;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File can't be created: " + e);
        } catch (NotSerializableException e) {
            System.err.println("Class no supported serialization: " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("thread close error");
            }
        }
        return flag;
    }

    public List deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);
// десериализация
            List st = (List) istream.readObject();
            return st;
        } catch (ClassNotFoundException ce) {
            System.err.println("Class no being: " + ce);
        } catch (FileNotFoundException e) {
            System.err.println("File not being to deserialization: " + e);
        } catch (InvalidClassException ioe) {
            System.err.println("Class version mismatch: " + ioe);
        } catch (IOException ioe) {
            System.err.println("Common I/O error: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("thread closing error ");
            }
        }
        throw new InvalidObjectException("object not restored.");
    }
}
