package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.xml.internal.bind.v2.model.core.ID;
import controllers.IdController;
import controllers.MessageController;
import models.Id;
import models.Message;

// Simple Shell is a Console view for views.YouAreEll.
public class SimpleShell {



    public static void prettyPrint(String output) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(output, Object.class);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        output = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        System.out.println(output);
        //System.out.println(IdTextView.getINSTANCE().printIds());
        //System.out.println(output);


//        System.out.println("XXXXX");
//        System.out.println(MessageController.getINSTANCE().getMessageList());

//        IdTextView textView = new IdTextView();
//        System.out.println(textView.printIds(IdController.getINSTANCE().getIdList()));
    }


    public static void main(String[] args) throws java.io.IOException {

        ObjectMapper mapper = new ObjectMapper();

        YouAreEll webber = new YouAreEll(MessageController.getINSTANCE(), IdController.getINSTANCE());
        
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder pb = new ProcessBuilder();
        List<String> history = new ArrayList<String>();
        int index = 0;
        //we break out with <ctrl c>
        while (true) {
            //read what the user enters
            System.out.println("cmd? ");
            commandLine = console.readLine();

            //input parsed into array of strings(command and arguments)
            String[] commands;
            if (commandLine.contains("\'")) {
                String[] split = commandLine.split("'");
                String message = split[1];
                String[] beforeSplit = split[0].split(" ");
                String[] afterSplit;

                if (split.length >= 3) {
                    afterSplit = split[2].split(" ");
                } else {
                    afterSplit = new String[1];
                }
                commands = new String[beforeSplit.length + afterSplit.length];
                System.arraycopy(beforeSplit, 0, commands, 0, beforeSplit.length);
                System.arraycopy(afterSplit, 0, commands, beforeSplit.length, afterSplit.length);
                commands[beforeSplit.length] = message;
            } else {
                commands = commandLine.split(" ");
            }




            List<String> list = new ArrayList<String>();

            //if the user entered a return, just loop again
            if (commandLine.equals(""))
                continue;
            if (commandLine.equals("exit")) {
                System.out.println("bye!");
                break;
            }

            //loop through to see if parsing worked
            for (int i = 0; i < commands.length; i++) {
                //System.out.println(commands[i]); //***check to see if parsing/split worked***
                list.add(commands[i]);

            }
            System.out.print(list); //***check to see if list was added correctly***
            history.addAll(list);
            try {
                //display history of shell with index
                if (list.get(list.size() - 1).equals("history")) {
                    for (String s : history)
                        System.out.println((index++) + " " + s);
                    continue;
                }

                // Specific Commands.

                // ids
                if (list.contains("ids") && list.size() == 1) {
                    String results = webber.get_ids();
                    SimpleShell.prettyPrint(results);
                    continue;
                }


                if (list.contains("ids") && list.size() == 3){
                    IdController.getINSTANCE().updateIds();
                    Id id = IdController.getINSTANCE().listContains(list.get(2));
                    if (id == null){
                        Id newId = new Id(list.get(1), list.get(2));
                        String jsonID = mapper.writeValueAsString(newId);
                        String results = webber.post_id(jsonID);
                        SimpleShell.prettyPrint(results);
                        continue;
                    } else {
                        id.setName(list.get(1));
                        String jsonID = mapper.writeValueAsString(id);
                        String results = webber.put_id(jsonID);
                        SimpleShell.prettyPrint(results);
                        continue;
                    }
                }



                // messages
                if (list.contains("messages") && list.size() == 1) {
                    String results = webber.get_messages();
                    SimpleShell.prettyPrint(results);
                    continue;
                }
                // you need to add a bunch more.

                if (list.contains("messages") && list.size() == 2) {
                    String mainUrl = "/ids/" + list.get(1) + "/messages";
                    String results = webber.get_your_messages(mainUrl);
                    SimpleShell.prettyPrint(results);
                    continue;
                }


                if (list.contains("send") && list.size() == 3) {
                    String message = list.get(2);
                    String name = list.get(1);
                    Message newMessage = new Message(message, name, "");
                    String jsonMessage = mapper.writeValueAsString(newMessage);
                    String mainUrl = "/ids/" + name + "/messages";
                    String results = webber.post_message(mainUrl, jsonMessage);
                    SimpleShell.prettyPrint(results);
                    continue;
                }


                if (list.contains("send") && list.size() > 3) {
                    String message = list.get(2);
                    String name = list.get(1);
                    String toid = list.get(4);
                    Message newMessage = new Message(message, name, toid);
                    String jsonMessage = mapper.writeValueAsString(newMessage);
                    String mainUrl = "/ids/" + name + "/messages";
                    String results = webber.post_message(mainUrl, jsonMessage);
                    SimpleShell.prettyPrint(results);
                    continue;
                }










                //!! command returns the last command in history
                if (list.get(list.size() - 1).equals("!!")) {
                    pb.command(history.get(history.size() - 2));

                }//!<integer value i> command
                else if (list.get(list.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(list.get(list.size() - 1).charAt(1));
                    if (b <= history.size())//check if integer entered isn't bigger than history size
                        pb.command(history.get(b));
                } else {
                    pb.command(list);
                }

                // wait, wait, what curiousness is this?
                Process process = pb.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = br.readLine()) != null)
                    System.out.println(line);
                br.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}