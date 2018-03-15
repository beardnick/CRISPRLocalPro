package Pro;

import Util.MyJButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/11/27.
 */
public class GeneSearch extends Window implements WindowCallBack {

    public static final String CMD = "CMD>>";
    public GeneSearch(JPanel panel , JFrame frame){
        super(frame);
        this.mainPanel = panel;
        setCallBack(this);
    }


    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("Database search");

    private JLabel listLabel = new JLabel("Gene List :");
    private JLabel resultLabel = new JLabel("Reference database :");
    private JLabel userLabel = new JLabel("User's database :");
    private JLabel outputLabel = new JLabel("Output directory :");
    private JLabel numResultLabel = new JLabel("Number of result per gene :");
    private JLabel optionsLabel = new JLabel("Options :");
    private JLabel labelLabel = new JLabel("Label :");

    private JTextField listText = new JTextField();
    private JTextField resultText = new JTextField();
    private JTextField userText = new JTextField();
    private JTextField outputText = new JTextField();
    private JTextField numResultText = new JTextField();
    private JTextField labelText = new JTextField();

    private MyJButton listBtn = new MyJButton();
    private MyJButton resultBtn = new MyJButton();
    private MyJButton userBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();

    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JFileChooser listFile= new JFileChooser();
    private JFileChooser resultFile= new JFileChooser();
    private JFileChooser userFile = new JFileChooser();
    private JFileChooser outputFile= new JFileChooser();


    public static String[] stopCmd = {"/bin/sh" , "-c" ,
            "ps -ef |grep -e 'DB-search.pl'|cut -c 9-15 |xargs kill -s 9"};



    public void initView(){
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);
        listLabel.setFont(R.textFont);
        resultLabel.setFont(R.textFont);
        userLabel.setFont(R.textFont);
        outputLabel.setFont(R.textFont);
        numResultLabel.setFont(R.textFont);
        optionsLabel.setFont(R.textFont);
        labelLabel.setFont(R.textFont);


        listText.setFont(R.textFont);
        resultText.setFont(R.textFont);
        userText.setFont(R.textFont);
        outputText.setFont(R.textFont);
        numResultText.setFont(R.textFont);
        labelText.setFont(R.textFont);

        Icon dirIcon = new ImageIcon("src/Resource/dir.png");
        listBtn.setIcon(dirIcon);
        resultBtn.setIcon(dirIcon);
        userBtn.setIcon(dirIcon);
        outputBtn.setIcon(dirIcon);


        mainPanel.setLayout(new BorderLayout());

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);

        constraints.anchor = GridBagConstraints.EAST;//all label align east
        constraints.fill = GridBagConstraints.NONE; //all label wont expand

        addComp(constraints, 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listLabel , constraints);
        contentPanel.add(listLabel);

        addComp(constraints, 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultLabel , constraints);
        contentPanel.add(resultLabel);

        addComp(constraints, 0 , 3 , 2 , 1 , new Insets(30 , 10 , 30 , 10));
        layout.setConstraints( optionsLabel, constraints);
      //  contentPanel.add(optionsLabel);

        addComp(constraints, 0 , 3 , 2 , 1 , new Insets(30 , 10 , 30 , 10));
        layout.setConstraints( userLabel, constraints);
        contentPanel.add(userLabel , constraints);

        addComp(constraints, 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLabel , constraints);
        contentPanel.add(outputLabel);

//        addComp(constraints, 0 , 5 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
//        layout.setConstraints(numResultLabel , constraints);
//        contentPanel.add(numResultLabel);

        addComp(constraints, 0 , 5 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(labelLabel , constraints);
        contentPanel.add(labelLabel);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 8;

        addComp(constraints, 2 , 0 , 1 , 1 , new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , constraints);
//        contentPanel.add(viceTitle);

        addComp(constraints, 2 , 1 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listText , constraints);
        contentPanel.add(listText);

        addComp(constraints, 2 , 2 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultText , constraints);
        contentPanel.add(resultText);

        addComp(constraints, 2 , 3 , 6 , 1 , new Insets(10, 10 , 10 , 10));
        layout.setConstraints( userText, constraints);
        contentPanel.add(userText , constraints);

        addComp(constraints, 2 , 4  , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputText , constraints);
        contentPanel.add(outputText);

//        addComp(constraints, 2 , 5 , 6, 1 , new Insets(10 , 10 , 10 , 10));
//        layout.setConstraints(numResultText , constraints);
//        contentPanel.add(numResultText);

        addComp(constraints, 2 , 5 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(labelText , constraints);
        contentPanel.add(labelText);


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.WEST;

        addComp(constraints, 8 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(listBtn , constraints);
        contentPanel.add(listBtn);

        addComp(constraints, 8 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(resultBtn , constraints);
        contentPanel.add(resultBtn);

        addComp(constraints, 8 , 3 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( userBtn, constraints);
        contentPanel.add(userBtn , constraints);

        addComp(constraints, 8 , 4 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputBtn , constraints);
        contentPanel.add(outputBtn);

        addComp(constraints, 9 , 6 , 1 , 1 , new Insets(10 , 10 , 10, 50));
//        layout.setConstraints(submitBtn , constraints);
//        contentPanel.add(submitBtn);

        mainPanel.add(contentPanel , BorderLayout.CENTER);
        mainPanel.setBackground(Color.white);
        mainPanel.setSize(1500 , 1000);

        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);

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

        userBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    userText.setText(userFile.getSelectedFile().getPath());
                    userText.setEditable(false);
                }
            }
        });

    }

    public void initData(){
        numResultText.setText("1");
        labelText.setText("DB_search");
        listFile.setFileFilter(new FileFilter() {
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
        userFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
        outputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    }

    private String scriptHead = "perl DB-search.pl";

    public void setScriptHead(String scriptHead) {
        this.scriptHead = scriptHead;
    }

    public String commandBuilder(){
        StringBuilder cmd = new StringBuilder(scriptHead);

        cmd.append(" -g " + listText.getText());
        cmd.append(" -i " + resultText.getText());
        if(userText.getText().length() > 0)
            cmd.append(" -u " + userText.getText());
        if(outputText.getText().length() > 0)
            cmd.append(" -o " + outputText.getText());
        if(labelText.getText().length() > 0){
            cmd.append(" -l " + labelText.getText());
        }


        System.out.println(CMD + cmd.toString());

        return cmd.toString();
    }




       public boolean checkData(){
        warningText.setText("");
        textFieldEmpty(listText , Color.pink , "please choose the gene list file\n");
        textFieldEmpty(resultText , Color.pink , "please choose the result file\n");
//        textFieldEmpty(outputText , Color.pink , "please choose the output directory\n");

//       if(numResultText.getText().length() > 0){
//           Pattern number = Pattern.compile("^[0-9]*$");
//           if(! number.matcher(numResultText.getText()).matches()){
//               warningText.append("please enter the number of the result\n");
//               numResultLabel.setForeground(Color.pink);
//           }else {
//               numResultLabel.setForeground(Color.black);
//           }
//        }
//        System.out.println(warningText.getText().equals(""));
//        System.out.println(warningText.getText().toString());

        return warningText.getText().length() == 0;
    }

    public String[] stopCmdBuilder(){
//        System.out.println("STOP CMD : " + stopCmd);
        return stopCmd;
    }

    public JTextField getLabelText() {
        return labelText;
    }
}
