package de.deltacorp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.mortbay.util.ajax.JSON;

public class storage {
    
    private FileWriter writer;
    private File directory = null;
    private File subscriberFile = null;
    private File sessionFile = null;
    private File invoiceFile = null;

    public storage() {
        File dir = new File("data");
        if (!dir.exists()){
            dir.mkdirs();
        }
        subscriberFile = new File("data/subscriber.json");
        if(!subscriberFile.exists()) {
            try {
                subscriberFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sessionFile = new File("data/session.json");
        if(!sessionFile.exists()){
            try {
                sessionFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        invoiceFile = new File("data/invoice.json");
        if(!invoiceFile.exists()){
            try {
                invoiceFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void storeSubscribers(ArrayList<Subscriber> subscribers) {
        JSONArray arr = new JSONArray();
        for (Subscriber subscriber : subscribers) {
            JSONObject jsonSubscriber = new JSONObject(subscriber);
            arr.put(jsonSubscriber);
        }
        helperWriteinFile(subscriberFile, arr);
    }

    public void storeSessions(ArrayList<Session> sessions) {
        JSONArray arr = new JSONArray();
        for (Session session : sessions) {
            JSONObject jsonSession = new JSONObject(session);
            arr.put(jsonSession);
        }
        helperWriteinFile(sessionFile, arr);
    }

    public void storeInvoices(ArrayList<Invoice> invoices) {
        JSONArray arr = new JSONArray();
        for (Invoice invoice : invoices) {
            JSONObject jsonInvoice = new JSONObject(invoice);
            arr.put(jsonInvoice);
        }
        helperWriteinFile(invoiceFile, arr);
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
                                                   jsonSubscriber.getEnum(Terminal.class,"terminal"),
                                                   jsonSubscriber.getEnum(Subscription.class, "subscription"));
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
                                          jsonSession.getEnum(Service.class, "service"));
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
        helperWriteinFile(file, new JSONArray());
    }

    private void helperWriteinFile(File file, JSONArray arr){
        try {
            writer = new FileWriter(file.getAbsolutePath());
            writer.write(arr.toString());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
