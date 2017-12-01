package Util;



import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by asus on 2017/11/10.
 */
public class CmdHelper {
    private java.lang.Runtime runtime = Runtime.getRuntime();
    private java.lang.Process result;
    public TextArea info;
    private boolean stop = false;
    private File file;
    private String cmdString = "";

    public CmdHelper(TextArea info) {
        this.info = info;
    }
    private BufferedReader infoReader;
    private BufferedReader errorReader;
    private OutputStream cmdWriter;

    private Thread errorThread = new Thread(new Runnable() {
        @Override
        public void run() {
            String temp = "";
            try {
                while (! stop){
                    if ((temp = errorReader.readLine()) != null) {
                        if (temp.length() != 0) {
                            System.out.println("ERROR>    " + temp);
                            info.append("ERROR>    " + temp + "\n");
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    });
    private Thread infoThread = new Thread(new Runnable() {
        @Override
        public void run() {

            String temp = "";
            try {

                while (! stop){
                    if ((temp = infoReader.readLine()) != null) {
                        if (temp.length() != 0) {
                            System.out.println("INFO>    " + temp);
                            info.append("INFO>    " + temp + "\n");
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    });

    private Thread waitThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                String[] cmd = {"/bin/sh" , "-c" , cmdString};
                result = runtime.exec(cmd);
                infoReader = new BufferedReader(new
                        InputStreamReader(result.getInputStream() , "GBK"
                ));
                errorReader = new BufferedReader(new
                        InputStreamReader(result.getErrorStream() , "GBK"
                 ));
                errorThread.start();
                cmdWriter = result.getOutputStream();
                infoThread.start();
                result.waitFor();
                infoThread.join();
                errorThread.join();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });


    public void stopCmd(String[] cmd) throws InterruptedException, IOException {
        if(! stop){
            if(result == null)System.out.println("the result == null");
            else {
//                cmdWriter = result.getOutputStream();
//                stop = true;
//                cmdWriter.write("\003\ny\n".getBytes());
//                cmdWriter.flush();
                result.destroy();
                infoThread.interrupt();
                errorThread.interrupt();
                waitThread.interrupt();
                runtime.exec(cmd);
            }
        }
    }

    public void execCmd(String cmd) throws IOException {
        info.setText("the process is runing ...\n" +
                "please wait\n");
        file = new File("run.bat");
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file);
        cmdString = cmd;
        writer.write(cmd);
        writer.close();
        waitThread.start();
    }
}

