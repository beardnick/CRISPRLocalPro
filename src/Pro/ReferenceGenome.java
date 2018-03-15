package Pro;

import Util.MyJButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/11/27.
 */
public class ReferenceGenome extends Window implements WindowCallBack{

    public static final String CMD = "CMD >>";

    public ReferenceGenome(JPanel panel , JFrame frame ){
        super(frame);
       this.mainPanel = panel;
        setCallBack(this);
    }
    private String[] modes = {"Cas9" , "Cpf1" , "Custom"};

    private JPanel mainPanel;

    private JLabel designModeLabel = new JLabel("Design mode :");
    private JLabel referLabel = new JLabel("Reference genome :");
    private JLabel annoLabel = new JLabel("Genome annotation :");
    private JLabel option = new JLabel("Options :");
    private JLabel outputLabel = new JLabel("Output dirctory :");
    private JLabel threadsLabel = new JLabel("Threads :");
    private JLabel labelLabel = new JLabel("Lable :");
    private JLabel end5Label = new JLabel("Expanding 5'-end :");
    private JLabel end3Label = new JLabel("Expanding 3'-end :");
    private JLabel nt = new JLabel("nt");
    private JLabel newNt = new JLabel("nt");
    private JLabel guideSequenceLengthLabel = new JLabel("Guide Sequence length :");
    private JLabel pamLabel = new JLabel("PAM :");


    private JComboBox designModeBox = new JComboBox();
    private JTextField referDir = new JTextField();
    private JTextField annoDir = new JTextField();
    private JTextField outDir = new JTextField();
    private JTextField threadsNum = new JTextField();
    private JTextField labelText = new JTextField();
    private JTextField end5Text = new JTextField();
    private JTextField end3Text = new JTextField();
    private JTextField guideSequenceLengthText = new JTextField();
    private JTextField pamText = new JTextField();


    private MyJButton referBtn = new MyJButton();
    private MyJButton annoBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();


    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("Reference genome sgRNA design");

    private JFileChooser referFile = new JFileChooser();
    private JFileChooser annFile = new JFileChooser();
    private JFileChooser outputFile = new JFileChooser();

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints constraints = new GridBagConstraints();


    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

//    public  static String[] stopCmd = {"cmd.exe" , "/c" , "taskkill /f /im perl.exe"};
    public static String[] stopCmd = {"/bin/sh" , "-c" ,
        "ps -ef |grep -e 'RD-build.pl' -e 'rs2_score_calculator.py' -e 'seqmap-1.0.12-linux-64' -e 'sgRNA_CFD.pl' -e 'cfd-score-calculator.py'|cut -c 9-15 |xargs kill -s 9"};





    public void initView(){
        mainPanel.setLayout(new BorderLayout());
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);
        referLabel.setFont(R.textFont);
        annoLabel.setFont(R.textFont);
        referDir.setSize(R.text_width , R.height);
        referDir.setFont(R.textFont);
        annoDir.setSize(R.text_width , R.height);
        annoDir.setFont(R.textFont);
        option.setFont(new Font("Dialog" , 1 , 30));

        designModeBox.setFont(R.textFont);
        outputLabel.setFont(R.textFont);
        outDir.setFont(R.textFont);
        threadsLabel.setFont(R.textFont);
        threadsNum.setFont(R.textFont);
        labelLabel.setFont(R.textFont);
        labelText.setFont(R.textFont);
        labelText.setText("CRISPR");
        end5Label.setFont(R.textFont);
        end5Text.setFont(R.textFont);
        end3Label.setFont(R.textFont);
        end3Text.setFont(R.textFont);
        nt.setFont(R.textFont);
        newNt.setFont(R.textFont);
        designModeLabel.setFont(R.textFont);
        pamLabel.setFont(R.textFont);
        guideSequenceLengthLabel.setFont(R.textFont);
        guideSequenceLengthText.setFont(R.textFont);
        guideSequenceLengthLabel.setForeground(Color.gray);
        pamLabel.setForeground(Color.gray);
        pamText.setFont(R.textFont);
        guideSequenceLengthText.setBackground(Color.gray);
        pamText.setBackground(Color.gray);
        guideSequenceLengthText.setEditable(false);
        pamText.setEditable(false);

        Icon dirIcon = new ImageIcon("src/Resource/dir.png");
        referBtn.setIcon(dirIcon);
        referBtn.setBackground(Color.white);
        annoBtn.setIcon(dirIcon);
        annoBtn.setBackground(Color.white);
        outputBtn.setIcon(dirIcon);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);
        titlePanel.setLayout(layout);
        titlePanel.setBackground(Color.white);


        addComp(constraints, 0 , 0  ,3 , 2 ,new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(title , constraints);


        constraints.anchor = GridBagConstraints.WEST;
        addComp(constraints, 3 , 0 , 1 , 1, new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , constraints);
//        contentPanel.add(viceTitle);

//        mainPanel.add(titlePanel , BorderLayout.NORTH);




        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 3;

        addComp(constraints ,  3, 0 ,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( designModeBox, constraints);
        contentPanel.add(designModeBox);

        addComp(constraints, 3 , 1 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(referDir , constraints);
        contentPanel.add(referDir);



        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        addComp(constraints, 10 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referBtn , constraints);
        contentPanel.add(referBtn);


        addComp(constraints, 3 , 2 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 3;
        layout.setConstraints(annoDir , constraints);
        contentPanel.add(annoDir);

        addComp(constraints, 10 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(annoBtn , constraints);
        contentPanel.add(annoBtn);

        addComp(constraints, 3 , 4 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 3;
        layout.setConstraints(outDir , constraints);
        contentPanel.add(outDir);

        addComp(constraints, 10 , 4 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(outputBtn , constraints);
        contentPanel.add(outputBtn);

        addComp(constraints, 3 , 5 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(threadsNum , constraints);
        contentPanel.add(threadsNum);

        addComp(constraints, 3 , 6 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(labelText , constraints);
        contentPanel.add(labelText);

        addComp(constraints, 3 , 7 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.WEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(end5Text , constraints);
        contentPanel.add(end5Text);

        addComp(constraints, 10 , 8 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(newNt , constraints);
        contentPanel.add(newNt);

        addComp(constraints, 3 , 8 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        constraints.anchor = GridBagConstraints.EAST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        layout.setConstraints(end3Text , constraints);
        contentPanel.add(end3Text);

        addComp(constraints , 3 ,  9,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( guideSequenceLengthText, constraints);
        contentPanel.add(guideSequenceLengthText);

        addComp(constraints ,  3,  10,  1, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( pamText, constraints);
        contentPanel.add(pamText);

        addComp(constraints, 10 , 7 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(nt , constraints);
        contentPanel.add(nt);

        constraints.fill = GridBagConstraints.NONE;
        constraints.weightx = 1;//all label wont expand
        //contentPanel

        addComp(constraints , 0 , 0 ,  2, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( designModeLabel, constraints);
        contentPanel.add(designModeLabel);

        constraints.anchor = GridBagConstraints.EAST;
        addComp(constraints, 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referLabel, constraints);
        contentPanel.add(referLabel);


        addComp(constraints, 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoLabel, constraints);
        contentPanel.add(annoLabel);


        addComp(constraints, 0 , 3 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(option , constraints);

        constraints.anchor = GridBagConstraints.EAST;
        addComp(constraints, 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLabel, constraints);
        contentPanel.add(outputLabel);


        addComp(constraints, 0 , 5 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsLabel, constraints);
        contentPanel.add(threadsLabel);


        addComp(constraints, 0 , 6 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(labelLabel, constraints);
        contentPanel.add(labelLabel);


        addComp(constraints, 0 , 7 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(end5Label, constraints);
        contentPanel.add(end5Label);


        addComp(constraints, 0 , 8 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(end3Label, constraints);
        contentPanel.add(end3Label);

        addComp(constraints ,0  , 9 ,  2, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(guideSequenceLengthLabel , constraints);
        contentPanel.add(guideSequenceLengthLabel);

        addComp(constraints , 0 , 10 ,  2, 1, new Insets(10 , 10 , 10 , 10));
        layout.setConstraints( pamLabel, constraints);
        contentPanel.add(pamLabel);

        addComp(constraints, 11 , 9 , 2 , 1 , new Insets(10 , 10 , 10 , 50));
        //  constraints.fill = GridBagConstraints.HORIZONTAL;
//        layout.setConstraints(submitBtn , constraints);
//        contentPanel.add(submitBtn);

        addComp(constraints, 0 , 3 , 3 , 10 , new Insets(10  , 10 ,10 , 10));
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.anchor = GridBagConstraints.WEST;
        layout.setConstraints(contentPanel, constraints);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

    }


    public void initData(){
        for(String x : modes){
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
                        guideSequenceLengthText.setForeground(Color.black);
                        pamText.setBackground(Color.white);
                        guideSequenceLengthText.setEditable(true);
                        pamText.setEditable(true);
                        end3Label.setForeground(Color.gray);
                        end5Label.setForeground(Color.gray);
                        end3Text.setBackground(Color.gray);
                        end5Text.setBackground(Color.gray);
                        end3Text.setEditable(false);
                        end5Text.setEditable(false);
                    }else{
                        guideSequenceLengthLabel.setForeground(Color.gray);
                        pamLabel.setForeground(Color.gray);
                        guideSequenceLengthText.setBackground(Color.gray);
                        pamText.setBackground(Color.gray);
                        guideSequenceLengthText.setForeground(Color.gray);
                        guideSequenceLengthText.setEditable(false);
                        pamText.setEditable(false);
                        end3Label.setForeground(Color.black);
                        end5Label.setForeground(Color.black);
                        end3Text.setBackground(Color.white);
                        end5Text.setBackground(Color.white);
                        end3Text.setEditable(true);
                        end5Text.setEditable(true);
                    }
                }
            }
        });
        threadsNum.setText("1");
        guideSequenceLengthText.setText("24");
        guideSequenceLengthText.setForeground(Color.GRAY);
        referFile.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(java.io.File f) {
                if (f.isDirectory())return true;
                return f.getName().endsWith(".fa") || f.getName().endsWith(".fasta");
            }
            @Override
            public String getDescription(){
                return "*.fa ; *.fasta";
            }
        });
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
        outDir.setText(System.getProperty("user.dir"));
        end3Text.setText("0");
        end5Text.setText("0");
    }



    public void initEvent(){
        referBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(referFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    referDir.setText(referFile.getSelectedFile().getPath());
                    referDir.setEditable(false);
                }
            }
        });

        annoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(annFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    annoDir.setText(annFile.getSelectedFile().getPath());
                    annoDir.setEditable(false);
                }
            }
        });

        outputBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(outputFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                    outDir.setText(outputFile.getSelectedFile().getPath());
                    outDir.setEditable(false);
                }
            }
        });

    }

    public String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl $0 ");

        //-m mode

        cmd.append(" -m " + modes[designModeBox.getSelectedIndex()]);

        //-i Genome

        cmd.append(" -i " + referDir.getText());

        //-g Gff3

        cmd.append(" -g " + annoDir.getText());

        //-o paht

        cmd.append(" -o " + outDir.getText());

        //-l label

        cmd.append(" -l " + labelText.getText());

        //-p process

        cmd.append(" -p " + threadsNum.getText());


        if(modes[designModeBox.getSelectedIndex()].equals("Cas9")){
            //-U opt_up

            cmd.append(" -U " + end5Text.getText());

            //-D opt_down

            cmd.append(" -D " + end3Text.getText());
        }else {
            //-t Pamtype

            cmd.append(" -t " + pamText.getText());

            //-x length of protospacer

            cmd.append(" -x " + guideSequenceLengthText.getText());
        }

        System.out.println(CMD + cmd.toString());

        return cmd.toString();
    }

    public String[] stopCmdBuilder(){
        //        ps -ef |grep -e '$label'|cut -c 9-15 |xargs kill -s 9
        StringBuilder builder = new StringBuilder(" ps -ef |grep -e ");
        builder.append("'" + labelText.getText() + "'");
        builder.append("|cut -c 9-15 |xargs kill -s 9");
//        System.out.println("STOP CMD : " + builder.toString());
        String[] stop = {"/bin/sh" , "-c" , builder.toString()};
        return stop;
    }

    public boolean checkData(){
        warningText.setText("");
        textFieldEmpty(referDir , Color.pink, "please choose a fasta file\n" );
        textFieldEmpty(annoDir , Color.pink , "please choose a gff3 file\n");
        Pattern number = Pattern.compile("^[0-9]*$");
        if(textFieldEmpty(threadsNum , Color.pink , "please enter the number of the thread\n" )){
//            System.out.println("threadsNum :" + threadsNum.getText());
        }else  if(! number.matcher(threadsNum.getText()).matches()){
            warningText.append("please enter the number of the threads\n");
            threadsLabel.setForeground(Color.pink);
        }else {
            threadsLabel.setForeground(Color.black);
        }

        if(modes[designModeBox.getSelectedIndex()].equals("Cas9")){
            if(textFieldEmpty(end3Text , Color.pink , "please enter a number between 0 and 15 in Expanding 3 ' -end\n")){
//            System.out.println("end3Text :" + end3Text.getText());
            }else if(!  number.matcher(end3Text.getText()).matches()){
                warningText.append("please enter number to end3 text\n");
                end3Label.setForeground(Color.pink);
            }else if(Integer.valueOf(end3Text.getText()) > 15 || Integer.valueOf(end3Text.getText()) < 0){
                warningText.append("the number must between 0 and 15\n");
                end3Label.setForeground(Color.pink);

            }else{
                end3Label.setForeground(Color.black);
            }

            if(textFieldEmpty(end5Text , Color.pink , "please enter a number between 0 and 15 in Expanding 5 ' -end\n" )){
//            System.out.println("end5Text :" + end5Text.getText());
            }else if(! number.matcher(end5Text.getText()).matches()){
                warningText.append("please enter number to end5 text\n");
                end5Label.setForeground(Color.pink);
            } else  if(Integer.valueOf(end5Text.getText()) > 15 || Integer.valueOf(end5Text.getText()) < 0){
                warningText.append("the number must between 0 and 15\n");
                end5Label.setForeground(Color.pink);
            }else{
                end5Label.setForeground(Color.black);
            }
        }else {
            if(textFieldEmpty(guideSequenceLengthText , Color.pink , "please enter the length of protospacer\n")){
//            System.out.println("end3Text :" + end3Text.getText());
            }else if(!  number.matcher(guideSequenceLengthText.getText()).matches()){
                warningText.append("please enter a number\n");
                guideSequenceLengthLabel.setForeground(Color.pink);
            }
            textFieldEmpty(pamText , Color.PINK , "please enter the type of PAM\n");
        }

//        System.out.println(warningText.getText().equals(""));
//        System.out.println(warningText.getText().toString());

        return warningText.getText().length() == 0;
    }

}

