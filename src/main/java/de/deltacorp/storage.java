package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.example.Invoice;
import org.example.Service;
import org.example.Session;
import org.example.Subscriber;
import org.example.Subscription;
import org.example.Terminal;
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
    public void storeSubscriber(Subscriber subscriber) {
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

    public void storeSession(Session session) {
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

    public void storeInvoice(Invoice invoice) {
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
    public ArrayList<Subscriber> getSubscribers(){
        ArrayList<Subscriber> array = new ArrayList<Subscriber>();
        JSONArray jsonArray = this.readSubscribers();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonSubscriber = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            Subscriber subscriber = new Subscriber(jsonSubscriber.getDouble("remainingDataVolumeMb"),
                                                   jsonSubscriber.getDouble("remainingFreeMinutes"),
                                                   jsonSubscriber.getDouble("chargingTotalEur"),
                                                   jsonSubscriber.getString("forename"),
                                                   jsonSubscriber.getString("surname"),
                                                   jsonSubscriber.getString("MCC"),
                                                   jsonSubscriber.getString("MNC"), 
                                                   jsonSubscriber.getString("MSIN"),
                                                   (Terminal)jsonSubscriber.get("terminal"),
                                                   (Subscription)jsonSubscriber.get("subscription"));
            array.add(subscriber);
        }
        return array;
    }

    /**
     * Returns all Subscribers stored in subscriber.json
     * @return ArrayList of Subscribers
     */
    public ArrayList<Session> getSessions(){
        ArrayList<Session> array = new ArrayList<Session>();
        JSONArray jsonArray = this.readSessions();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonSession = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            Session session = new Session(jsonSession.getString("username"),
                                          jsonSession.getDouble("durationSeconds"),
                                          (Service)jsonSession.get("service"));
            array.add(session);
        }
        return array;
    }

    public ArrayList<Invoice> getInvoices(){
        ArrayList<Invoice> array = new ArrayList<Invoice>();
        JSONArray jsonArray = this.readInvoices();
        for (int i = 0; i < jsonArray.length(); i++){
            JSONObject jsonInvoices = jsonArray.getJSONObject(i);
            // Create Java Object from JSON Object
            Invoice invoice = new Invoice(jsonInvoices.getString("subscriberImsi"),
                                          jsonInvoices.getString("subscriberFullName"),
                                          jsonInvoices.getDouble("usedDataMb"),
                                          jsonInvoices.getDouble("usedMinutes"),
                                          jsonInvoices.getDouble("appliedChargesEur"));
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
