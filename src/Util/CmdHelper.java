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
    private volatile boolean stop = false;
    private String cmdString = "";

    public CmdHelper(TextArea info) {
        this.info = info;
    }
    private volatile BufferedReader infoReader;
    private volatile BufferedReader errorReader;

    private Thread errorThread;
    private Thread infoThread;
    private Thread waitThread;


    public void stopCmd(String[] cmd) throws InterruptedException, IOException {
        if(! stop){
            if(result == null)System.out.println("the result == null");
            else {
                stop = true;
                result.destroy();
                runtime.exec(cmd);
                System.out.println(stop);
                System.out.println("ERROR : " + errorThread.isAlive());
                System.out.println("INFO : " + infoThread.isAlive());
                System.out.println("WAIT : " + waitThread.isAlive());
                info.append("INFO>    the process will stop soon \n INFO>    please wait\n");
                infoReader.close();
                errorReader.close();
                System.out.println("ERROR : " + errorThread.isAlive());
                System.out.println("INFO : " + infoThread.isAlive());
                System.out.println("WAIT : " + waitThread.isAlive());
            }
        }
    }

    public void execCmd(String cmd) throws IOException {
        stop = false;
        info.setText("the process is running ...\n" +
                "please wait\n");
        infoThread = new Thread(new InfoThread());
        errorThread = new Thread(new ErrorThread());
        waitThread = new Thread(new WaitThread());
        System.out.println("ERROR : " + errorThread.isAlive());
        System.out.println("INFO : " + infoThread.isAlive());
        System.out.println("WAIT : " + waitThread.isAlive());
        cmdString = cmd;
        waitThread.start();
    }

    class WaitThread implements Runnable{

        @Override
        public void run() {

                try {
                    //TODO: 2017/12/4 change the cmd to compatibilable to Linux
                    String[] cmd = {"cmd.exe" , "/c" , cmdString};
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
                    System.out.println("PROCESS EXIT VALUE : " + result.exitValue());
                    if(result.exitValue() == 0){
                        info.append("INFO>    the process is over\n");
                        info.append("INFO>    the window will close in 3 seconds later\n");
                        Thread.sleep(3000);
                    }
                    System.out.println("wait for has been executed");
                } catch (IOException e) {
                    e.printStackTrace();
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
                            System.out.println("INFO>    " + temp);
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
                            System.out.println("ERROR>    " + temp);
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

