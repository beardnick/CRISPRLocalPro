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
    private TextArea info;
    private JDialog information;
    private volatile boolean stop = false;
    private String cmdString = "";
    private JFrame parent;

    public CmdHelper(TextArea info , JDialog information , JFrame parent) {
        this.info = info;
        this.information = information;
        this.parent = parent;
    }
    private volatile BufferedReader infoReader;
    private volatile BufferedReader errorReader;

    private Thread errorThread;
    private Thread infoThread;
    private Thread waitThread;


    public void stopCmd(String[] cmd) throws InterruptedException, IOException {
        if(! stop){
            if(result == null){
//                System.out.println("the result == null");
            }
            else {
                stop = true;
                result.destroy();
                if(cmd.length == 3){
                    runtime.exec(cmd);
                }
                else if(cmd.length > 3){
                    for(int i = 0 ; i < cmd.length - 2 ; i ++){
                        runtime.exec(new String[] {"/bin/sh" , "-c" , cmd[i]});
                    }
                }
//                System.out.println(stop);
//                System.out.println("ERROR : " + errorThread.isAlive());
//                System.out.println("INFO : " + infoThread.isAlive());
//                System.out.println("WAIT : " + waitThread.isAlive());
                info.append("INFO>    the process will stop soon \n INFO>    please wait\n");
                infoReader.close();
                errorReader.close();
//                System.out.println("ERROR : " + errorThread.isAlive());
//                System.out.println("INFO : " + infoThread.isAlive());
//                System.out.println("WAIT : " + waitThread.isAlive());
            }
        }
    }
    public void execCmd(String cmd) throws IOException {
        stop = false;
       info.setText("########## Welcome to CRISPR-Local ##########\n" +
               "CRISPR-Local is a Local and high-throughput sgRNA design tool for plant genome editing using CRISPR-Cas9, It supports the design of sgRNA for user’s sequence data.\n" +
               "The process is running, do not close this window...\n");
        infoThread = new Thread(new InfoThread());
        errorThread = new Thread(new ErrorThread());
        waitThread = new Thread(new WaitThread());
//        System.out.println("ERROR : " + errorThread.isAlive());
//        System.out.println("INFO : " + infoThread.isAlive());
//        System.out.println("WAIT : " + waitThread.isAlive());
        cmdString = cmd;
        waitThread.start();
    }

    class WaitThread implements Runnable{

        @Override
        public void run() {

                try {
//                    String[] cmd = {"cmd.exe" , "/c" , "tasklist"};
                   String[] cmd = {"/bin/sh" , "-c" , cmdString};
                    result = runtime.exec(cmd);
                    infoReader = new BufferedReader(new
                            InputStreamReader(result.getInputStream() , "GBK"
                    ));
                    errorReader = new BufferedReader(new
                            InputStreamReader(result.getErrorStream() , "GBK"
                    ));
                    errorThread.start();
                    infoThread.start();
                    result.waitFor();
//                    System.out.println("PROCESS EXIT VALUE : " + result.exitValue());
                    if(result.exitValue() == 0){
                        Thread.sleep(2000);
                        info.append("INFO>    the process is over\n");
                        info.append("INFO>    the window will close in 3 seconds later\n");
                        Thread.sleep(3000);
                        information.dispose();
                        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
//                    System.out.println("wait for has been executed");
                } catch (IOException e) {
                    e.printStackTrace();
                    info.append("ERROR>    " + e.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    class InfoThread implements Runnable{

        @Override
        public void run() {
            String temp = "";
            try {
                while (! stop){
                    if ((temp = infoReader.readLine()) != null) {
                        if (temp.length() != 0) {
//                            System.out.println("INFO>    " + temp);
                            info.append("INFO>    " + temp + "\n");
                        }

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    class ErrorThread implements Runnable{

        @Override
        public void run() {
            String temp = "";
            try {
                while (! stop){
                    if ((temp = errorReader.readLine()) != null) {
                        if (temp.length() != 0) {
//                            System.out.println("ERROR>    " + temp);
                            info.append("ERROR>    " + temp + "\n");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}

