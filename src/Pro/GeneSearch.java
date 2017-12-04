package Pro;

import Util.CmdHelper;
import Util.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/11/27.
 */
public class GeneSearch {

    public GeneSearch(JPanel panel , JFrame frame){
        this.mainPanel = panel;
        this.frame = frame;
        information = new JDialog(frame , "information" , false);
        warning = new JDialog(frame , "warning" , true);
    }

    private JFrame frame ;
    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("Database search");

    private JLabel listLabel = new JLabel("Gene List :");
    private JLabel resultLabel = new JLabel("Reference database :");
    private JLabel userLabel = new JLabel("User's database :");
    private JLabel outputLabel = new JLabel("Output directory :");
    private JLabel numResultLabel = new JLabel("Number of result per gene :");
    private JLabel optionsLabel = new JLabel("Options :");

    private JTextField listText = new JTextField();
    private JTextField resultText = new JTextField();
    private JTextField userText = new JTextField();
    private JTextField outputText = new JTextField();
    private JTextField numResultText = new JTextField();

    private MyJButton listBtn = new MyJButton();
    private MyJButton resultBtn = new MyJButton();
    private MyJButton userBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();
    private MyJButton submitBtn = new MyJButton();
    private MyJButton helpBtn = new MyJButton();

    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private TextArea info = new TextArea("information" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog information;

    private GridBagConstraints con = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JFileChooser listFile= new JFileChooser();
    private JFileChooser resultFile= new JFileChooser();
    private JFileChooser outputFile= new JFileChooser();

    private CmdHelper cmdHelper = new CmdHelper(info);

    private TextArea warningText = new TextArea("warning" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog warning;

    public static String[] stopCmd = {"/bin/sh" , "-c" ,
            "ps -ef |grep -e 'Gene_search.pl'|cut -c 9-15 |xargs kill -s 9"};



    public void initView(){
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);
        listLabel.setFont(R.textFont);
        resultLabel.setFont(R.textFont);
        userLabel.setFont(R.textFont);
        outputLabel.setFont(R.textFont);
        numResultLabel.setFont(R.textFont);
        optionsLabel.setFont(R.textFont);

        listText.setFont(R.textFont);
        resultText.setFont(R.textFont);
        userText.setFont(R.textFont);
        outputText.setFont(R.textFont);
        numResultText.setFont(R.textFont);

        Icon dirIcon = new ImageIcon("src/Resource/dir.png");
        submitBtn.setIcon(new ImageIcon("src/Resource/submit.png"));
        helpBtn.setIcon(new ImageIcon("src/Resource/help.png"));
        listBtn.setIcon(dirIcon);
        resultBtn.setIcon(dirIcon);
        userBtn.setIcon(dirIcon);
        outputBtn.setIcon(dirIcon);

        warningText.setFont(R.infoFont);
        warning.add(warningText);
        warning.setResizable(false);
        warning.setSize(1000 , 500);
        information.add(info);
        info.setBackground(Color.white);
        information.setSize(1000 , 500);
        information.setResizable(false);
        information.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  frame.setLayout(new BorderLayout());

        mainPanel.setLayout(new BorderLayout());

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);

        con.anchor = GridBagConstraints.EAST;//all label align east
        con.fill = GridBagConstraints.NONE; //all label wont expand

        addComp(con , 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listLabel , con);
        contentPanel.add(listLabel);

        addComp(con , 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultLabel , con);
        contentPanel.add(resultLabel);

        addComp(con , 0 , 3 , 2 , 1 , new Insets(30 , 10 , 30 , 10));
        layout.setConstraints( optionsLabel, con);
      //  contentPanel.add(optionsLabel);

        addComp(con , 0 , 3 , 2 , 1 , new Insets(30 , 10 , 30 , 10));
        layout.setConstraints( userLabel, con);
        contentPanel.add(userLabel , con);

        addComp(con , 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLabel , con);
        contentPanel.add(outputLabel);

        addComp(con , 0 , 5 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(numResultLabel , con);
        contentPanel.add(numResultLabel);

        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 8;

        addComp(con , 2 , 0 , 1 , 1 , new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , con);
        contentPanel.add(viceTitle);

        addComp(con , 2 , 1 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listText , con);
        contentPanel.add(listText);

        addComp(con , 2 , 2 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultText , con);
        contentPanel.add(resultText);

        addComp(con , 2 , 3 , 6 , 1 , new Insets(10, 10 , 10 , 10));
        layout.setConstraints( userText, con);
        contentPanel.add(userText , con);

        addComp(con , 2 , 4  , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputText , con);
        contentPanel.add(outputText);

        addComp(con , 2 , 5 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(numResultText , con);
        contentPanel.add(numResultText);


        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        con.anchor = GridBagConstraints.WEST;

        addComp(con , 8 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listBtn , con);
        contentPanel.add(listBtn);

        addComp(con , 8 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultBtn , con);
        contentPanel.add(resultBtn);

        addComp(con , 8 , 3 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( userBtn, con);
        contentPanel.add(userBtn , con);

        addComp(con , 8 , 4 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputBtn , con);
        contentPanel.add(outputBtn);

        addComp(con , 9 , 6 , 1 , 1 , new Insets(10 , 10 , 100, 50));
        layout.setConstraints(submitBtn , con);
        contentPanel.add(submitBtn);

        mainPanel.add(contentPanel , BorderLayout.CENTER);
        mainPanel.setBackground(Color.white);
        mainPanel.setSize(1500 , 1000);

        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);
//        frame.setVisible(true);


    }

    public void initEvent(){
        listBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(listFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    listText.setText(listFile.getSelectedFile().getPath());
                    listText.setEditable(false);
                }
            }
        });

        resultBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(resultFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    resultText.setText(resultFile.getSelectedFile().getPath());
                    resultText.setEditable(false);
                }
            }
        });

        outputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(outputFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    outputText.setText(outputFile.getSelectedFile().getPath());
                    outputText.setEditable(false);
                }
            }
        });

//        submitBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(! information.isVisible() && checkData()){
//                    try {
//                        information.setVisible(true);
//                        cmdHelper.execCmd(commandBuilder());
//                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//                else {
//                    if(! checkData()){
//                        warning.setTitle("warning");
//                        warning.setVisible(true);
//                    }
//                }
//
//            }
//        });
//
//        information.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                int confirm =  JOptionPane.showConfirmDialog(frame ,
//                        "do you really want to stop the process ?" , "warning" , JOptionPane.YES_NO_OPTION);
//                if(confirm == JOptionPane.YES_OPTION ){
//                    try {
//                        System.out.println(confirm);
//                        cmdHelper.stopCmd(stopCmd);
//                        information.dispose();
//                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////                        frame.dispose();
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                    } catch (IOException e1) {
//                        e1.printStackTrace();
//                    }
//                }
//
//            }
//        });

    }

    public void initData(){
        numResultText.setText("1");

        listFile.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if(f.isDirectory())return true;
                return f.getName().endsWith(".txt");
            }

            @Override
            public String getDescription() {
                return "*.txt" ;
            }
        });
        outputText.setText(System.getProperty("user.dir"));

        listFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        resultFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    }



    private String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl Gene_search.pl");

        cmd.append("-l " + listText.getText());
        cmd.append("-i " + resultText.getText());
        if(userText.getText().length() > 0)
            cmd.append("-u " + userText.getText());
        if(outputText.getText().length() > 0)
            cmd.append("-o " + outputText.getText());
        if(numResultText.getText().length() > 0)
            cmd.append("-N " + numResultText.getText());


        System.out.println(cmd.toString());

        return cmd.toString();
    }




    private boolean checkData(){
        warningText.setText("");
        textFieldEmpty(listText , Color.pink , "please choose the gene list file\n");
        textFieldEmpty(resultText , Color.pink , "please choose the result file\n");
//        textFieldEmpty(outputText , Color.pink , "please choose the output directory\n");

       if(numResultText.getText().length() > 0){
           Pattern number = Pattern.compile("^[0-9]*$");
           if(! number.matcher(numResultText.getText()).matches()){
               warningText.append("please enter the number of the result\n");
               numResultLabel.setForeground(Color.pink);
           }else {
               numResultLabel.setForeground(Color.black);
           }
        }
        System.out.println(warningText.getText().equals(""));
        System.out.println(warningText.getText().toString());

        return warningText.getText().equals("");
    }

    private boolean textFieldEmpty(JTextField text , Color color , String notice  ){
        boolean isEmpty;
        if(text.getText().length() == 0){
            isEmpty =true;
            warningText.append(notice);
            text.setOpaque(true);
            text.setBackground(color);
        }else {
            isEmpty = false;
            text.setOpaque(false);
        }
        return isEmpty;
    }




    private void addComp(GridBagConstraints constraints , int x , int y , int gridWidth , int gridHeight , Insets insets){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.insets = insets;
    }



}
