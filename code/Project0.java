/**
 * Author: Grant Hruzek
 * Course: CSCE-489 Cloud Computing
 */

package project0;

import java.util.Scanner;
import java.io.File;
import java.io.*;
import java.util.*;

public class Project0 {
    
    // aggiestack log file and filestream
    public static File logFile = new File("aggiestack-log.txt");
    public static FileOutputStream logFileStream = null;
    
    // Print streams for log file and console
    public static PrintStream PS_LOG = null;
    public static PrintStream PS_CONSOLE = null;
    
    // ANSI color codes
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    
    // holds number of entities in cloud
    public static int numMachines = 0;
    public static int numImages = 0;
    public static int numFlavors = 0;
    public static int numRacks = 0;
    
    // holds all entities in cloud
    public static ArrayList<machine> machineList = new ArrayList<machine>();
    public static ArrayList<machine> sickmachineList = new ArrayList<machine>();
    public static ArrayList<image> imageList = new ArrayList<image>();
    public static ArrayList<flavor> flavorList = new ArrayList<flavor>();
    public static ArrayList<rack> rackList = new ArrayList<rack>();
    public static ArrayList<rack> sickrackList = new ArrayList<rack>();
    public static ArrayList<instance> instanceList = new ArrayList<instance>();
    public static ArrayList<instance> sickinstanceList = new ArrayList<instance>();
    
    // class that holds our machines
    public static class machine {
        String name, rackName, ip;
        int memory, numDisk, numCPU;
        
        public machine(String name, String rackName, String ip, int memory, int numDisk, int numCPU) {
            this.name = name;
            this.rackName = rackName;
            this.ip = ip;
            this.memory = memory;
            this.numDisk = numDisk;
            this.numCPU = numCPU;
        }
        
        // setter functions
        public void setMem(int value) { this.memory = value; }
        public void setDisk(int value) { this.numDisk = value; }
        public void setCPU(int value) { this.numCPU = value; }
    }
    
    // class that holds our images
    public static class image {
        String name, directory;
        int size;
        
        public image(String name, int size, String directory) {
            this.name = name;
            this.size = size;
            this.directory = directory;
        }
    }
    
    // class that holds our flavors
    public static class flavor {
        String size;
        int memory, numDisk, numCPU;
        
        public flavor(String size, int memory, int numDisk, int numCPU) {
            this.size = size;
            this.memory = memory;
            this.numDisk = numDisk;
            this.numCPU = numCPU;
        }
    }
    
    // class that holds our racks
    public static class rack {
        String name;
        int capacity;
        
        public rack(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }
    }
    
    // class that holds our instances
    public static class instance {
        String instanceName, machineName, flavorName, imageName;
        
        public instance(String instanceName, String machineName, String flavorName, String imageName) {
            this.instanceName = instanceName;
            this.machineName = machineName;
            this.flavorName = flavorName;
            this.imageName = imageName;
        }
    }
    
    // print all hardware 
    public static void showHardware() {
        if (rackList.isEmpty()) {
            System.out.println(ANSI_RED + "Racks is Empty!" + ANSI_RESET);
        } 
        else {
            System.out.println(ANSI_GREEN + "Printing all racks:" + ANSI_RESET);
            System.out.println(rackList.size());
            for (int rackIndex = 0; rackIndex < rackList.size(); rackIndex++) {
                System.out.print(rackList.get(rackIndex).name + " ");
                System.out.print(rackList.get(rackIndex).capacity);
                System.out.println();
            }
            System.out.println();
        }
        
        if (machineList.isEmpty()) {
            System.out.println(ANSI_RED + "Machines is Empty!" + ANSI_RESET);
        } 
        else {
            System.out.println(ANSI_GREEN + "Printing all machines:" + ANSI_RESET);
            System.out.println(machineList.size());
            for (int machineIndex = 0; machineIndex < machineList.size(); machineIndex++) {
                System.out.print(machineList.get(machineIndex).name + " ");
                System.out.print(machineList.get(machineIndex).rackName + " ");
                System.out.print(machineList.get(machineIndex).ip + " ");
                System.out.print(machineList.get(machineIndex).memory + " ");
                System.out.print(machineList.get(machineIndex).numDisk + " ");
                System.out.print(machineList.get(machineIndex).numCPU);
                System.out.println();
            }
            System.out.println();
        }
    }
    
    // print all images
    public static void showImages() {
        if (imageList.isEmpty()) {
            System.out.println(ANSI_RED + "Images is Empty!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_GREEN + "Printing all Images:" + ANSI_RESET);
            System.out.println(imageList.size());
                for (int imageIndex = 0; imageIndex < imageList.size(); imageIndex++) {
                    System.out.print(imageList.get(imageIndex).name + " ");
                    System.out.print(imageList.get(imageIndex).size + " ");
                    System.out.print(imageList.get(imageIndex).directory);
                    System.out.println();
                }
            System.out.println();
        }
    }
    
    // print all flavors
    public static void showFlavors() {
        if (flavorList.isEmpty()) {
            System.out.println(ANSI_RED + "Flavors is Empty!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_GREEN + "Printing all Flavors:" + ANSI_RESET);
            System.out.println(flavorList.size());
                for (int flavorIndex = 0; flavorIndex < flavorList.size(); flavorIndex++) {
                    System.out.print(flavorList.get(flavorIndex).size + " ");
                    System.out.print(flavorList.get(flavorIndex).memory + " ");
                    System.out.print(flavorList.get(flavorIndex).numDisk + " ");
                    System.out.print(flavorList.get(flavorIndex).numCPU);
                    System.out.println();
                }
            System.out.println();
        }
    }
    
    // print all instances
    public static void showInstances() {
        if (instanceList.isEmpty()) {
            System.out.println(ANSI_RED + "There are no currently running instances!" + ANSI_RESET);
        }
        else {
            System.out.println(ANSI_GREEN + "Printing all Instances:" + ANSI_RESET);
            System.out.println("Number of Running Instances: " + instanceList.size());
                for (int instanceIndex = 0; instanceIndex < instanceList.size(); instanceIndex++) {
                    System.out.println("------------------------------------");
                    System.out.println(instanceList.get(instanceIndex).instanceName + " ");
                    System.out.println("Image: " + instanceList.get(instanceIndex).imageName + " ");
                    System.out.println("Flavor: " + instanceList.get(instanceIndex).flavorName);
                }
            System.out.println();
        }
    }
    
    // set up log file for current session
    public static void initLogFile() {
        try {
            Date date = new Date();
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            logFileStream = new FileOutputStream(logFile);
            writeToLogFile("AggieStack Log Session");
            writeToLogFile(date.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // appends a line to a log file
    public static void writeToLogFile(String line) {
        try {
            // set up printstream for Log/Console
            PS_LOG = new PrintStream(logFileStream);
            PS_CONSOLE = System.out;
            
            // print line to log
            System.setOut(PS_LOG);
            System.out.append(line + "\n");
            System.setOut(PS_CONSOLE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // closes current log session
    public static void closeLogFile() {
        try { 
            logFileStream.close(); 
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }
        finally { 
            PS_LOG.close(); 
        }
    }
    
    // returns rack object if it exists
    public static rack rackSearch(String rackName) {
        for (int rackIndex = 0; rackIndex < rackList.size(); ++rackIndex) {
            if (rackList.get(rackIndex).name.equals(rackName)) {
                return rackList.get(rackIndex);
            }
        }
        return null;
    }
    
    // returns machine object if it exists
    public static machine machineSearch(String machineName) {
        for (int machineIndex = 0; machineIndex < machineList.size(); ++machineIndex) {
            if (machineList.get(machineIndex).name.equals(machineName)) {
                return machineList.get(machineIndex);
            }
        }
        return null;
    }
    
    // returns image object if it exists
    public static image imageSearch(String imageName) {
        for (int imageIndex = 0; imageIndex < imageList.size(); ++imageIndex) {
            if (imageList.get(imageIndex).name.equals(imageName)) {
                return imageList.get(imageIndex);
            }
        }
        return null;
    }
    
    // returns flavor object if it exists
    public static flavor flavorSearch(String flavorName) {
        for (int flavorIndex = 0; flavorIndex < flavorList.size(); ++flavorIndex) {
            if (flavorList.get(flavorIndex).size.equals(flavorName)) {
                return flavorList.get(flavorIndex);
            }
        }
        return null;
    }
    
    // returns instance object if it exists
    public static instance instanceSearch(String instanceName) {
        for (int instanceIndex = 0; instanceIndex < instanceList.size(); ++instanceIndex) {
            if (instanceList.get(instanceIndex).instanceName.equals(instanceName)) {
                return instanceList.get(instanceIndex);
            }
        }
        return null;
    }
    
    // creates a server instance
    public static int instanceCreate(String instanceName, String imageName, String flavorName) {
        // init requested image and flavor objects
            image requestedImage = null;
            flavor requestedFlavor = null;
            machine requestedMachine = null;
            
            // return error if image DNE
            requestedImage = imageSearch(imageName);
            if (requestedImage == null) {
                return 2;
            }
            
            // return error if flavor DNE
            requestedFlavor = flavorSearch(flavorName);
            if (requestedFlavor == null) {
                return 3;
            }
            
            // SERVER PLACEMENT ALGORITHM: Here the algorithm is simple,
            // 1. Find the first server that has sufficient resources to host instance
            // 2. Select that machine, create the instance, add instance to instancelist
            // 3. Update resource usage configurations to the host machine
            // by simply comparing hardware configurations
            for (int machineIndex = 0; machineIndex < machineList.size(); machineIndex++) {
                if ((machineList.get(machineIndex).memory >= requestedFlavor.memory)
                        && (machineList.get(machineIndex).numDisk >= requestedFlavor.numDisk) 
                        && (machineList.get(machineIndex).numCPU >= requestedFlavor.numCPU)) {
                    requestedMachine = machineList.get(machineIndex);
                    break;
                } 
            }
            
            // Successfully create instance case, add to instance list, update resource usage
            if (requestedMachine != null) {
                instanceList.add(new instance(instanceName, requestedMachine.name, requestedFlavor.size, requestedImage.name));
                requestedMachine.setMem(requestedMachine.memory - requestedFlavor.memory);
                requestedMachine.setDisk(requestedMachine.numDisk - requestedFlavor.numDisk);
                requestedMachine.setCPU(requestedMachine.numCPU - requestedFlavor.numCPU);
                return -1;
            } 
            // No physical server available case
            else if (requestedMachine == null) {
               return 1;
            }
            // Not a valid command case
            else {
                return 0;
            }
    }
    
    // deletes a server instance
    public static int instanceDelete(String instanceName) {
        
        // init instance/machine objects
        instance requestedInstance = null;
        machine requestedMachine = null;
        flavor requestedFlavor = null;

        // get requested instance/machine
        requestedInstance = instanceSearch(instanceName);

        // requested instance DNE case
        if (requestedInstance == null) {
            return 5;
        }
        // free up phyiscal server resources and delete instance
        else {
            // find machine/flavor objects of the requested instance
            requestedMachine = machineSearch(requestedInstance.machineName);
            requestedFlavor = flavorSearch(requestedInstance.flavorName);

            // free up phyiscal server resources
            requestedMachine.setMem(requestedMachine.memory + requestedFlavor.memory);
            requestedMachine.setDisk(requestedMachine.numDisk + requestedFlavor.numDisk);
            requestedMachine.setCPU(requestedMachine.numCPU + requestedFlavor.numCPU);

            // delete instance
            instanceList.remove(requestedInstance);
            return -1;
        }
    }
    
    // removes a machine
    public static int machineRemove(String machineName) {
        // check if machine exists
        machine requestedMachine = machineSearch(machineName);
        if (requestedMachine == null) {
            return 16;
        }
        // remove machine and migrate instances
        else {

            // find all instances on machine to be removed
            ArrayList<instance> tempList = new ArrayList<instance>();
            for (int instanceIndex = 0; instanceIndex < instanceList.size(); instanceIndex++) {
                if (instanceList.get(instanceIndex).machineName.equals(requestedMachine.name)) {
                    tempList.add(instanceList.get(instanceIndex));
                    instanceList.remove(instanceList.get(instanceIndex));
                }
            }

            // remove machine
            machineList.remove(requestedMachine);

            // migrate instances
            for (int tempIndex = 0; tempIndex < tempList.size(); tempIndex++) {
                instanceCreate(tempList.get(tempIndex).instanceName, 
                    tempList.get(tempIndex).imageName, 
                    tempList.get(tempIndex).flavorName);
                tempList.remove(tempList.get(tempIndex));
            }
            return -2;
        }
    }
    
    // handles fail/success/error cases
    public static void handleCase(int value, String command) {
        switch (value) {
            case -2:
                writeToLogFile("SUCCESS: " + command);
                break;
            case -1:
                System.out.println(ANSI_GREEN + "Success!" + ANSI_RESET);
                writeToLogFile("SUCCESS: " + command);
                break;
            case 0:
                System.out.println(ANSI_RED + "Not a valid command!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 1:
                System.out.println(ANSI_RED + "Requested machine name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 2:
                System.out.println(ANSI_RED + "Requested image name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 3:
                System.out.println(ANSI_RED + "Requested flavor name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 4:
                System.out.println(ANSI_RED + "Requested rack name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 5:
                System.out.println(ANSI_RED + "Requested instance name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 6:
                System.out.println(ANSI_RED + "No more available resources!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 7:
                System.out.println(ANSI_RED + "No phyiscal servers currently available!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 8:
                System.out.println(ANSI_RED + "Phyiscal Sever Requirements Not Met!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 9:
                System.out.println(ANSI_RED + "Server Error!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 10:
                System.out.println(ANSI_RED + "Admin Error!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 11:
                System.out.println(ANSI_RED + "Error! File does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 12:
                System.out.println(ANSI_RED + "Error! Hardware is empty!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 13:
                System.out.println(ANSI_RED + "Error! Images is empty!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 14:
                System.out.println(ANSI_RED + "Error! Flavors is empty!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 15:
                System.out.println(ANSI_RED + "Error! Requested Machine name already exists!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 16:
                System.out.println(ANSI_RED + "Error! Requested Machine name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            case 17:
                System.out.println(ANSI_RED + "Error! Requested Rack name does not exist!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
            default:
                System.out.println(ANSI_RED + "Parser Error!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
                break;
        }
    }
    
    // processes config commands
    public static int processCommandConfig(String cmd, String[] cmdList) {
        
        // check that there are an appriopriate number of commands
        if (cmdList.length <= 3 || cmdList.length > 4) {
            return 0;
        }
        
        // file handler to input file
        File configFile = new File(cmdList[3]);
        
        // check if file exists
        if (!configFile.exists()) {
            return 11;
        }
        
        // read from hardware config file
        if (cmdList[2].equals("--hardware")) {
            try {
                // set up file readers and scanners
                FileReader reader = new FileReader(configFile);
                Scanner sc = new Scanner(reader);
                
                // holds specs of a machine/rack
                String[] specs;
                
                // holds number of racks in config file
                numRacks = Integer.parseInt(sc.nextLine());
                
                // get each line of rack specs
                while (!sc.hasNextInt()) {
                    // split each line up by space delimiter
                    specs = sc.nextLine().split("\\s+");
                    
                    // push each new machine into our list of machines
                    rackList.add(new rack(specs[0], Integer.parseInt(specs[1])));
                }
                
                // holds number of machines in config file
                numMachines = Integer.parseInt(sc.nextLine());

                // get each line of machine specs
                while (sc.hasNextLine()) {
                    // split each line up by space delimiter
                    specs = sc.nextLine().split("\\s+");
                    
                    // push each new machine into our list of machines
                    machineList.add(new machine(specs[0], specs[1], specs[2], Integer.parseInt(specs[3]), 
                        Integer.parseInt(specs[4]), Integer.parseInt(specs[5])));
                }
            }
            catch (Exception e) {
                System.out.println(ANSI_RED + "Exception Occurred: LoadHardWareConfigFile" + ANSI_RESET);
                e.printStackTrace();
            }
        }
        // read from images config file
        else if (cmdList[2].equals("--images")) {
            try {
                // set up file readers and scanners
                FileReader reader = new FileReader(configFile);
                Scanner sc = new Scanner(reader);
                
                // holds number of machines in config file
                numImages = Integer.parseInt(sc.nextLine());
                
                // holds specs of an image
                String[] specs;
                
                // get each line of image specs
                while (sc.hasNextLine()) {
                    // split each line up by space delimiter
                    specs = sc.nextLine().split("\\s+");
                    
                    // push each new machine into our list of machines
                    imageList.add(new image(specs[0], Integer.parseInt(specs[1]), specs[2]));
                }
            }
            catch (Exception e) {
                System.out.println(ANSI_RED + "Exception Occurred: LoadImageConfigFile" + ANSI_RESET);
                e.printStackTrace();
            }
        }
        // read from flavors config file
        else if (cmdList[2].equals("--flavors")) {
            try {
                // set up file readers and scanners
                FileReader reader = new FileReader(configFile);
                Scanner sc = new Scanner(reader);
                
                // holds number of machines in config file
                numFlavors = Integer.parseInt(sc.nextLine());
                
                // holds specs of an image
                String[] specs;
                
                // get each line of image specs
                while (sc.hasNextLine()) {
                    // split each line up by space delimiter
                    specs = sc.nextLine().split("\\s+");
                    
                    // push each new machine into our list of machines
                    flavorList.add(new flavor(specs[0], Integer.parseInt(specs[1]), 
                        Integer.parseInt(specs[2]), Integer.parseInt(specs[3])));
                }
            }
            catch (Exception e) {
                System.out.println(ANSI_RED + "Exception Occurred: LoadFlavorConfigFile" + ANSI_RESET);
                e.printStackTrace();
            }
        } 
        else {
            return 0;
        }
        return -2;
    }
    
    // processes show commands
    public static int processCommandShow(String cmd, String[] cmdList) {
        // read from hardware config file
        if (cmdList[2].equals("hardware")) {
            showHardware();
        }
        // read from images config file
        else if (cmdList[2].equals("images")) {
            showImages();
        }
        // read from flavors config file
        else if (cmdList[2].equals("flavors")) {
            showFlavors();
        }
        // show all 
        else if (cmdList[2].equals("all")) {
            showHardware();
            showImages();
            showFlavors();
        }
        // invalid command
        else {
            return 0;
        }
        return -2;
    }
    
    // process server commands
    public static int processCommandServer(String cmd, String[] cmdList) {
        
        // create virtual server if possible
        if (cmdList[2].equals("create")) {
            instanceCreate(cmdList[7], cmdList[4], cmdList[6]);
        }
        // find instance and delete if possible
        else if (cmdList[2].equals("delete")) {
            instanceDelete(cmdList[3]);
        }
        // print all instances
        else if (cmdList[2].equals("list")) {
            showInstances();
        }
        // invalid command case
        else {
            return 0;
        }
        return -2;
    }
    
    // process admin commands
    public static int processCommandAdmin(String cmd, String[] cmdList) {
        // print phyiscal server configurations
        if (cmdList[2].equals("show") && cmdList[3].equals("hardware")) {
            if (machineList.isEmpty()) {
                System.out.println(ANSI_RED + "No physical servers exist!" + ANSI_RESET);
                return -2;
            } 
            else {
                System.out.println(ANSI_GREEN + "Printing all Physical Servers and their available resources:" + ANSI_RESET);
                    for (int machineIndex = 0; machineIndex < machineList.size(); machineIndex++) {
                        System.out.println("------------------------------------");
                        System.out.println(machineList.get(machineIndex).name);
                        System.out.println("Free Memory Available (GB): " + machineList.get(machineIndex).memory + " ");
                        System.out.println("Free Disks Available: " + machineList.get(machineIndex).numDisk + " ");
                        System.out.println("Free CPUs Available: " + machineList.get(machineIndex).numCPU);
                    }
                System.out.println();
                return -2;
            }
        }
        // determine if machine can host a virtual server
        else if (cmdList[2].equals("can_host")) {

            // get requested name and flavor
            String inputName = cmdList[3];
            String inputFlavor = cmdList[4];
            machine requestedMachine = null;
            flavor requestedFlavor = null;
            
            // check if machine exists and extract object
            for (int machineIndex = 0; machineIndex < machineList.size(); machineIndex++) {
                if (machineList.get(machineIndex).name.equals(inputName)) {
                    requestedMachine = machineList.get(machineIndex);
                }
            }
            
            // check if flavor exists and extract object
            for (int flavorIndex = 0; flavorIndex < flavorList.size(); flavorIndex++) {
                if (flavorList.get(flavorIndex).size.equals(inputFlavor)) {
                    requestedFlavor = flavorList.get(flavorIndex);
                }
            }
            
            // machine doesn't exist case
            if (requestedMachine == null) {
                return 1;
            }
            
            // flavor doesn't exist case
            if (requestedFlavor == null) {
                return 3;
            }
            
            // YES case
            if (requestedMachine.memory >= requestedFlavor.memory && 
                    requestedMachine.numDisk >= requestedFlavor.numDisk && 
                    requestedMachine.numCPU >= requestedFlavor.numCPU) {
                return -1;
            } 
            // NO case
            else {
                return 8;
            }
        }
        // show instances and their host machines
        else if (cmdList[2].equals("show") && cmdList[3].equals("instances")) {
            if (instanceList.isEmpty()) {
                System.out.println(ANSI_RED + "No instances exist!" + ANSI_RESET);
                return -2;
            } 
            else {
                System.out.println(ANSI_GREEN + "Printing all Instances and their host machine:" + ANSI_RESET);
                    for (int instanceIndex = 0; instanceIndex < instanceList.size(); instanceIndex++) {
                        System.out.println("------------------------------------");
                        System.out.println("Instance Name: " + instanceList.get(instanceIndex).instanceName);
                        System.out.println("Host Machine: " + instanceList.get(instanceIndex).machineName);
                    }
                System.out.println();
                return -2;
            }
        }
        // add fixed machine back to cloud
        else if (cmdList[2].equals("add")) {
            
            // check if machine already exists
            if (machineSearch(cmdList[13]) != null) {
                return 15;
            } 
            // create new machine and add to machine list
            else {
                machineList.add(new machine(cmdList[13], cmdList[12], cmdList[10], 
                    Integer.parseInt(cmdList[4]), 
                    Integer.parseInt(cmdList[6]), 
                    Integer.parseInt(cmdList[8])));
                return -2;
            }
        }
        // remove a machine
        else if (cmdList[2].equals("remove")) {
            machineRemove(cmdList[3]);
            return -2;
        }
        // evacuate a rack
        else if (cmdList[2].equals("evacuate")) {
            
            // check if rack exists
            rack requestedRack = rackSearch(cmdList[3]);
            if (requestedRack == null) {
                return 17;
            }
            // remove rack and migrate all instances
            else {
                
                // remove rack
                String rackName = requestedRack.name;
                rackList.remove(requestedRack);
                
                // remove all machines in rack 
                ArrayList<machine> tempList = new ArrayList<machine>();
                for (int machineIndex = 0; machineIndex < machineList.size(); machineIndex++) {
                    if (machineList.get(machineIndex).rackName.equals(cmdList[3])) {
                        machineRemove(machineList.get(machineIndex).name);
                    }
                }
                return -2;
            }
        }
        else {
            writeToLogFile("FAILURE: " + cmd);
            System.out.println(ANSI_RED + "Please enter a valid command!" + ANSI_RESET);
            return 0;
        }
    }

    // main method
    public static void main(String[] args) {

        // Main System Loop
        boolean sys_run = true;
        
        // Scanner reads standard user-input line
        Scanner sc = new Scanner(System.in);
        
        // initialize log file for current session
        initLogFile();

        while (sys_run) {
            
            // Command line prompt
            System.out.print("@AggieStack> ");
            
            // Get entire user-input line as one string
            String command = sc.nextLine();
            
            // split command into parts by space delimiter
            String[] commandList = command.split("(\\ +)");
            int commandLength = commandList.length;
            
            // parse commands
            // exit command terminates program and ends log session
            if (commandList[0].equals("exit")) {
                writeToLogFile("SUCCESS: " + command);
                writeToLogFile("Session Closed Successfully");
                closeLogFile();
                System.exit(0);
            }
            // checks for a valid command
            else if (!commandList[0].equals("aggiestack") || commandLength < 3) {
                System.out.println(ANSI_RED + "Please enter a valid command!" + ANSI_RESET);
                writeToLogFile("FAILURE: " + command);
            }
            // handle commands
            else {
                // process config command
                if (commandList[1].equals("config")) {
                    int result = processCommandConfig(command, commandList);
                    handleCase(result, command);
                }
                // process show command
                else if (commandList[1].equals("show")) {
                    int result = processCommandShow(command, commandList);
                    handleCase(result, command);
                }
                // process server command
                else if (commandList[1].equals("server")) {
                   int result = processCommandServer(command, commandList);
                   handleCase(result, command);
                }
                // process admin command
                else if (commandList[1].equals("admin")) {
                    int result = processCommandAdmin(command, commandList);
                    handleCase(result, command);
                }
                // parser error
                else {
                    System.out.println(ANSI_RED + "Parser Error" + ANSI_RESET);
                }
            }
        }
    }
    
}
