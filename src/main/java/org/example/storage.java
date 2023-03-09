package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// FIXME Don't use testclass
// swap with real classes
public class storage {
    
    private FileWriter writer;
    private File subscriberFile = null;
    private File sessionFile = null;
    private File invoiceFile = null;

    public storage() {
        subscriberFile = new File("subscriber.json");
        if(!subscriberFile.exists()) {
            try {
                subscriberFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sessionFile = new File("session.json");
        if(!sessionFile.exists()){
            try {
                sessionFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        invoiceFile = new File("invoice.json");
        if(!invoiceFile.exists()){
            try {
                invoiceFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Stores Subscriber as JSONObject in subscriber file
     * (Adds it to the exitsing JSONArray in the file)
     * @param subscriber subscriber to add
     */
    public void storeSubscriber(Object subscriber) {
        JSONArray jsonArray = readSubscribers();
        JSONObject jsonSubscriber = new JSONObject(subscriber);
        jsonArray.put(jsonSubscriber);
        try {
            writer = new FileWriter(subscriberFile.getAbsolutePath());
            writer.write(jsonArray.toString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void storeSession(Object session) {
        JSONArray jsonArray = readSessions();
        JSONObject jsonSessions = new JSONObject(session);
        jsonArray.put(jsonSessions);
        try {
            writer = new FileWriter(sessionFile.getAbsolutePath());
            writer.write(jsonArray.toString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void storeInvoice(Object invoice) {
        JSONArray jsonArray = readInvoices();
        JSONObject jsonInvoices = new JSONObject(invoice);
        jsonArray.put(jsonInvoices);
        try {
            writer = new FileWriter(invoiceFile.getAbsolutePath());
            writer.write(jsonArray.toString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Returns all Subscribers stored in subscriber.json
     * @return ArrayList of Subscribers
     */
    public ArrayList<testclass> getSubscribers(){
        ArrayList<testclass> array = new ArrayList<testclass>();
        JSONArray jsonArray = this.readSubscribers();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonSubscriber = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            testclass subscriber = new testclass(jsonSubscriber.getInt("numbera"), jsonSubscriber.getString("firstName"), jsonSubscriber.getString("secondName"));
            array.add(subscriber);
        }
        return array;
    }

    /**
     * Returns all Subscribers stored in subscriber.json
     * @return ArrayList of Subscribers
     */
    public ArrayList<testclass> getSessions(){
        ArrayList<testclass> array = new ArrayList<testclass>();
        JSONArray jsonArray = this.readSessions();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonSession = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            testclass session = new testclass(jsonSession.getInt("numbera"), jsonSession.getString("firstName"), jsonSession.getString("secondName"));
            array.add(session);
        }
        return array;
    }

    public ArrayList<testclass> getInvoices(){
        ArrayList<testclass> array = new ArrayList<testclass>();
        JSONArray jsonArray = this.readInvoices();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonInvoices = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            testclass invoice = new testclass(jsonInvoices.getInt("numbera"), jsonInvoices.getString("firstName"), jsonInvoices.getString("secondName"));
            array.add(invoice);
        }
        return array;
    }

    /** 
     * Reads Subscriber File and returns all subscriber
     * If file was created lately (file is empty) --> creates initial JSONArray
     * @return JSONArray of all subscribers
    */
    private JSONArray readSubscribers(){
        JSONArray ret = new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(subscriberFile.getAbsolutePath()))));
            if(contents.isEmpty()) {
                // File needs first JSONArray
                helperWriteFirstJSONArray(subscriberFile);
                contents = new String((Files.readAllBytes(Paths.get(subscriberFile.getAbsolutePath()))));
            }
            JSONArray jsonArray = new JSONArray(contents);
            ret = jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /** 
     * Reads Subscriber File and returns all subscriber
     * If file was created lately (file is empty) --> creates initial JSONArray
     * @return JSONArray of all subscribers
    */
    private JSONArray readSessions(){
        JSONArray ret = new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(sessionFile.getAbsolutePath()))));
            if(contents.isEmpty()) {
                // File needs first JSONArray
                helperWriteFirstJSONArray(sessionFile);
                contents = new String((Files.readAllBytes(Paths.get(sessionFile.getAbsolutePath()))));
            }
            JSONArray jsonArray = new JSONArray(contents);
            ret = jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    private JSONArray readInvoices(){
        JSONArray ret = new JSONArray();
        try {
            String contents = new String((Files.readAllBytes(Paths.get(invoiceFile.getAbsolutePath()))));
            if(contents.isEmpty()) {
                // File needs first JSONArray
                helperWriteFirstJSONArray(invoiceFile);
                contents = new String((Files.readAllBytes(Paths.get(invoiceFile.getAbsolutePath()))));
            }
            JSONArray jsonArray = new JSONArray(contents);
            ret = jsonArray;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Helper function to write initial JSONArray to file
     * @param file which file to use
     */
    private void helperWriteFirstJSONArray(File file){
        try {
            JSONArray arr = new JSONArray();
            writer = new FileWriter(file.getAbsolutePath());
            writer.write(arr.toString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
