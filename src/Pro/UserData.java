package Pro;

import Util.MyJButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/11/27.
 */
public class UserData extends Window implements WindowCallBack {

    public static final String CMD = "CMD>>";

    private String[] modes = {"Cas9" , "Cpf1" , "Custom"};

    public UserData(JPanel panel , JFrame frame){
        super(frame);
        this.mainPanel = panel;
        setCallBack(this);
    }

    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("User's data specific sgRNA design optional");
    private JLabel designModeLabel = new JLabel("Design mode :");
    private JLabel datalabel = new JLabel("User's sequencing data :");
    private JLabel outputLabel = new JLabel("Output directory :");
    private JLabel annoLabel = new JLabel("Genome annotation :");
    private JLabel threadsLabel = new JLabel("Threads :");
    private JLabel guideSequenceLengthLabel = new JLabel("Guide Sequence length :");
    private JLabel pamLabel = new JLabel("PAM :");

    private JComboBox designModeBox = new JComboBox();
    private JTextField dataText= new JTextField();
    private JTextField outputText= new JTextField();
    private JTextField annoText= new JTextField();
    private JTextField threadsText= new JTextField(5);
    private JTextField guideSequenceLengthText = new JTextField();
    private JTextField pamText = new JTextField();

    private MyJButton dataBtn = new MyJButton();
    private MyJButton annoBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();

    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JFileChooser annFile = new JFileChooser();
    private JFileChooser dataFile = new JFileChooser();
    private JFileChooser outputFile = new JFileChooser();

    private StringBuilder dataString = new StringBuilder("");

    private ArrayList<String> fileList = new ArrayList<String>();
    public static String[] stopCmd = {"/bin/sh" , "-c" ,
        "ps -ef |grep -e 'UD-build.pl' -e 'rs2_score_calculator.py' -e 'samtools' |cut -c 9-15 |xargs kill -s 9"};
    public void initView(){
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);

        designModeLabel.setFont(R.textFont);
        datalabel.setFont(R.textFont);
        outputLabel.setFont(R.textFont);
        annoLabel.setFont(R.textFont);
        annoLabel.setForeground(Color.gray);
        threadsLabel.setFont(R.textFont);
        guideSequenceLengthLabel.setFont(R.textFont);
        pamLabel.setFont(R.textFont);
        guideSequenceLengthLabel.setForeground(Color.gray);
        pamLabel.setForeground(Color.gray);

        designModeBox.setFont(R.textFont);
        dataText.setFont(R.textFont);
        annoText.setFont(R.textFont);
        annoText.setOpaque(true);
        annoText.setBackground(Color.gray);
        annoText.setEditable(false);
        outputText.setFont(R.textFont);
        threadsText.setFont(R.textFont);
        guideSequenceLengthText.setFont(R.textFont);
        pamText.setFont(R.textFont);
        guideSequenceLengthText.setBackground(Color.gray);
        pamText.setBackground(Color.gray);
        guideSequenceLengthText.setEditable(false);
        pamText.setEditable(false);

        ImageIcon dirIcon = new ImageIcon("src/Resource/dir.png");
        dataBtn.setIcon(dirIcon);
        annoBtn.setIcon(dirIcon);
        outputBtn.setIcon(dirIcon);

         mainPanel.setLayout(new BorderLayout());

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);
        titlePanel.setLayout(layout);
        titlePanel.setBackground(Color.white);


        addComp(constraints, 0 , 0  ,3 , 2 ,new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(title , constraints);

        constraints.anchor = GridBagConstraints.WEST;
        addComp(constraints, 2 , 0 , 1 , 1, new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , constraints);
//        contentPanel.add(viceTitle);


        //contentPanel

        constraints.anchor = GridBagConstraints.EAST;//all label align east
        constraints.fill = GridBagConstraints.NONE; //all label wont expand

        addComp(constraints , 0 , 0 ,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( designModeLabel, constraints);
        contentPanel.add(designModeLabel);

        addComp(constraints, 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(datalabel, constraints);
        contentPanel.add(datalabel);



        addComp(constraints, 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoLabel, constraints);
        contentPanel.add(annoLabel);

        addComp(constraints, 0 , 3 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLabel, constraints);
        contentPanel.add(outputLabel);

        addComp(constraints, 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsLabel, constraints);
        contentPanel.add(threadsLabel);

        addComp(constraints ,0  , 5 ,  2, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(guideSequenceLengthLabel , constraints);
        contentPanel.add(guideSequenceLengthLabel);

         addComp(constraints , 0 , 6 ,  1, 1, new Insets(10 , 10 , 10 , 10));
         layout.setConstraints( pamLabel, constraints);
         contentPanel.add(pamLabel);

        //middle panel , JTextField
//
//        addComp(constraints , 2 , 0 , 1 , 1 , new Insets(10 , 10 , 80 , 10));
//        constraints.anchor = GridBagConstraints.WEST;


        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;


        constraints.anchor = GridBagConstraints.WEST;
        addComp(constraints, 8 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dataBtn , constraints);
        contentPanel.add(dataBtn);

        addComp(constraints, 8 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoBtn , constraints);
        contentPanel.add(annoBtn);


        addComp(constraints, 8 , 3 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputBtn , constraints);
        contentPanel.add(outputBtn);

        addComp(constraints, 9 , 5 , 1 , 1 , new Insets(50 , 10 , 10 , 10));
//        layout.setConstraints(submitBtn , constraints);
//        contentPanel.add(submitBtn);

        addComp(constraints ,  2, 0 ,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( designModeBox, constraints);
        contentPanel.add(designModeBox);

        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 8;

        addComp(constraints, 2 , 1 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dataText , constraints);
        contentPanel.add(dataText);



        addComp(constraints, 2 , 2 , 6 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoText , constraints);
        contentPanel.add(annoText);



        addComp(constraints, 2 , 3 , 6 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputText , constraints);
        contentPanel.add(outputText);

        addComp(constraints, 2 , 4 , 3 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsText , constraints);
        contentPanel.add(threadsText);

        addComp(constraints , 2 ,  5,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( guideSequenceLengthText, constraints);
        contentPanel.add(guideSequenceLengthText);

        addComp(constraints ,  2,  6,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( pamText, constraints);
        contentPanel.add(pamText);



        constraints.fill = GridBagConstraints.HORIZONTAL;
        addComp(constraints, 0 , 0 , 3 , 3 , new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(titlePanel , constraints);
//        mainPanel.add(titlePanel , BorderLayout.NORTH);
//        frame.add(titlePanel , BorderLayout.NORTH);

        addComp(constraints, 0 , 3 , 3, 10 , new Insets(10  , 10 ,10 , 10));
        layout.setConstraints(contentPanel , constraints);
        mainPanel.add(contentPanel , BorderLayout.CENTER);
//        frame.add(contentPanel , BorderLayout.CENTER);
    //    frame.add(mainPanel);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);
//        frame.setVisible(true);


    }

    public void initData(){
        for (String x: modes
             ) {
            designModeBox.addItem(x);
        }
        designModeBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                if(designModeBox.getSelectedIndex() != 0){
                        guideSequenceLengthLabel.setForeground(Color.black);
                        pamLabel.setForeground(Color.black);
                        guideSequenceLengthText.setBackground(Color.white);
                        pamText.setBackground(Color.white);
                        guideSequenceLengthText.setEditable(true);
                        pamText.setEditable(true);
                    }else{
                        guideSequenceLengthLabel.setForeground(Color.gray);
                        pamLabel.setForeground(Color.gray);
                        guideSequenceLengthText.setBackground(Color.gray);
                        pamText.setBackground(Color.gray);
                        guideSequenceLengthText.setEditable(false);
                        pamText.setEditable(false);
                    }
                }
            }
        });
        threadsText.setText("1");
        annFile.setDialogTitle("choose a .gff3 file");
        annFile.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(java.io.File f) {
                if (f.isDirectory())return true;
                return f.getName().endsWith(".gff3");
            }
            @Override
            public String getDescription(){
                return "*.gff3";
            }
        });
        outputFile.setDialogTitle("choose a directory");
        outputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        outputText.setText(System.getProperty("user.dir"));
        dataFile.setDialogTitle("choose a file");
        dataFile.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())return true;
                return
                        f.getName().endsWith(".bam") ||
                                f.getName().endsWith(".sam") ||
                                f.getName().endsWith(".fa") ||
                                f.getName().endsWith(".fasta") ||
                                f.getName().endsWith(".fa.gz") ||
                                f.getName().endsWith(".fasta.gz") ||
                                f.getName().endsWith(".fq") ||
                                f.getName().endsWith(".fastq") ||
                                f.getName().endsWith(".fq.gz") ||
                                f.getName().endsWith(".fastq.gz");
            }
            @Override
            public String getDescription() {
                return
                        "*.bam ;" +
                                "*.sam ;" +
                                "*.fa ;" +
                                "*.fasta ;" +
                                "*.fa.gz ;" +
                                "*.fasta.gz ;" +
                                "*.fq ;" +
                                "*.fastq ;" +
                                "*.fq.gz ;" +
                                "*.fastq.gz ;";
            }
        });
        outputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        threadsText.setText("1");

    }

    public  void initEvent(){
        outputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(outputFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    outputText.setText(outputFile.getSelectedFile().getPath());
                    outputText.setEditable(false);
                }
            }
        });

        dataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dataFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    dataString.setLength(0);
                    dataString.append(dataText.getText());
                    if(! dataString.toString().contains(dataFile.getSelectedFile().getPath())){
                        if(dataText.getText().length() != 0){
                            dataString.append(",");
                        }
                        dataString.append(dataFile.getSelectedFile().getPath());
                        String fileName=dataFile.getSelectedFile().getName();
                        String name=fileName.substring(0 , fileName.indexOf("."));
                        fileList.add(name);
                    }
                    dataText.setText(dataString.toString());
                    outputText.setEditable(false);
                    if(dataString.toString().contains(".sam") || dataString.toString().contains(".bam")){
                        annoLabel.setForeground(Color.black);
                        annoText.setBackground(Color.white);
                        annoText.setEditable(true);
                    }else {
                        annoLabel.setForeground(Color.gray);
                        annoText.setBackground(Color.gray);
                        annoText.setEditable(false);
                    }
                }
            }
        });

        annoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(annoLabel.getForeground() == Color.black){
                    if(annFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                        annoText.setText(annFile.getSelectedFile().getPath());
                        annoText.setEditable(false);
                    }
                }
            }
        });

    }

    public String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl  UD-build.pl");

        // TODO: 2018/3/15 Genome什么时候有效，什么时候无效

        //-m mode

        cmd.append(" -m " + modes[designModeBox.getSelectedIndex()] );

        cmd.append(" -i " + dataText.getText());

        if(annoLabel.getForeground()== Color.black){
            cmd.append(" -g " + annoText.getText());
        }

        cmd.append(" -o " + outputText.getText());

        cmd.append(" -p " + threadsText.getText());

        if( ! modes[designModeBox.getSelectedIndex()].equals("Cas9")){
            //-t Pamtype

            cmd.append(" -t " + pamText.getText());

            //-x length of protospacer

            cmd.append(" -x " + guideSequenceLengthText.getText());
        }

        System.out.println(CMD + cmd.toString());
        return cmd.toString();
    }

    public boolean checkData(){
        warningText.setText("");
        boolean dataValid = true;
        textFieldEmpty(dataText , Color.pink, "please choose your data file\n" );
        if(annoLabel.getForeground() == Color.black){
            textFieldEmpty(annoText , Color.pink , "please choose a gff3 file\n");
        }
        textFieldEmpty(outputText , Color.pink , "please choose a output directory\n");
        Pattern number = Pattern.compile("^[0-9]*$");
        if(textFieldEmpty(threadsText , Color.pink , "please enter the number of the thread\n" )){
//            System.out.println("threadsNum :" + threadsText.getText());
        }else  if(! number.matcher(threadsText.getText()).matches()){
            warningText.append("please enter the number of the threads\n");
            threadsLabel.setForeground(Color.pink);
        }else {
            threadsLabel.setForeground(Color.black);
        }
        if(! modes[designModeBox.getSelectedIndex()].equals("Cas9")){
            if(textFieldEmpty(guideSequenceLengthText , Color.pink , "please enter the length of protospacer\n")){
//            System.out.println("end3Text :" + end3Text.getText());
            }else if(!  number.matcher(guideSequenceLengthText.getText()).matches()){
                warningText.append("please enter a number\n");
                guideSequenceLengthLabel.setForeground(Color.pink);
            }
            textFieldEmpty(pamText , Color.PINK , "please enter the type of PAM\n");
        }

        return warningText.getText().equals("");

    }

    public String[] stopCmdBuilder(){
//        ps -ef |grep '关键字' |cut -c 9-15 |xargs kill -s 9
        String[] stop = new String[fileList.size() + 2];
        stop[0] = "/bin/sh";
        stop[1] = "-c";
        int i = 2;
        for(String string : fileList){
            stop[i ++] = "ps -ef |grep " + "'" + string +  "'" + "|cut -c 9-15 |xargs kill -s 9";
//            System.out.println("STOP CMD :" + stop[i - 1]);
        }
        return stop;
    }


}
