package Pro;

import Util.MyJButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by asus on 2017/11/27.
 */
public class UserData extends Window implements WindowCallBack {

    public UserData(JPanel panel , JFrame frame){
        super(frame);
        this.mainPanel = panel;
        setCallBack(this);
    }

    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("User's data specific sgRNA design optional");

    private JLabel dataLable= new JLabel("Your sequending data :");
    private JLabel outputLable= new JLabel("Output directory :");
    private JLabel annoLable= new JLabel("Genome annotation :");
    private JLabel threadsLable= new JLabel("Threads :");

    private JTextField dataText= new JTextField();
    private JTextField outputText= new JTextField();
    private JTextField annoText= new JTextField();
    private JTextField threadsText= new JTextField(5);

    private MyJButton dataBtn = new MyJButton();
    private MyJButton annoBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();

    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private GridBagConstraints con = new GridBagConstraints();
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

        dataLable.setFont(R.textFont);
        outputLable.setFont(R.textFont);
        annoLable.setFont(R.textFont);
        annoLable.setForeground(Color.gray);
        threadsLable.setFont(R.textFont);



        dataText.setFont(R.textFont);
        annoText.setFont(R.textFont);
        annoText.setOpaque(true);
        annoText.setBackground(Color.gray);
        outputText.setFont(R.textFont);
        threadsText.setFont(R.textFont);

        ImageIcon dirIcon = new ImageIcon("src/Resource/dir.png");
        dataBtn.setIcon(dirIcon);
        annoBtn.setIcon(dirIcon);
        outputBtn.setIcon(dirIcon);

         mainPanel.setLayout(new BorderLayout());

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);
        titlePanel.setLayout(layout);
        titlePanel.setBackground(Color.white);


        addComp(con , 0 , 0  ,3 , 2 ,new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(title , con);

        con.anchor = GridBagConstraints.WEST;
        addComp(con , 2 , 0 , 1 , 1, new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , con);
//        contentPanel.add(viceTitle);


        //contentPanel

        con.anchor = GridBagConstraints.EAST;//all label align east
        con.fill = GridBagConstraints.NONE; //all label wont expand

        addComp(con , 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dataLable , con);
        contentPanel.add(dataLable);



        addComp(con , 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoLable , con);
        contentPanel.add(annoLable);

        addComp(con , 0 , 3 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLable , con);
        contentPanel.add(outputLable);

        addComp(con , 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsLable , con);
        contentPanel.add(threadsLable);

        //middle panel , JTextField
//
//        addComp(con , 2 , 0 , 1 , 1 , new Insets(10 , 10 , 80 , 10));
//        con.anchor = GridBagConstraints.WEST;

        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;


        con.anchor = GridBagConstraints.WEST;
        addComp(con , 8 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dataBtn , con);
        contentPanel.add(dataBtn);

        addComp(con , 8 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoBtn , con);
        contentPanel.add(annoBtn);


        addComp(con , 8 , 3 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputBtn , con);
        contentPanel.add(outputBtn);

        addComp(con , 9 , 5 , 1 , 1 , new Insets(50 , 10 , 10 , 10));
//        layout.setConstraints(submitBtn , con);
//        contentPanel.add(submitBtn);


        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 8;

        addComp(con , 2 , 1 , 6, 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(dataText , con);
        contentPanel.add(dataText);



        addComp(con , 2 , 2 , 6 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoText , con);
        contentPanel.add(annoText);



        addComp(con , 2 , 3 , 6 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputText , con);
        contentPanel.add(outputText);

        addComp(con , 2 , 4 , 3 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsText , con);
        contentPanel.add(threadsText);





        con.fill = GridBagConstraints.HORIZONTAL;
        addComp(con , 0 , 0 , 3 , 3 , new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(titlePanel , con);
//        mainPanel.add(titlePanel , BorderLayout.NORTH);
//        frame.add(titlePanel , BorderLayout.NORTH);

        addComp(con , 0 , 3 , 3, 10 , new Insets(10  , 10 ,10 , 10));
        layout.setConstraints(contentPanel , con);
        mainPanel.add(contentPanel , BorderLayout.CENTER);
//        frame.add(contentPanel , BorderLayout.CENTER);
    //    frame.add(mainPanel);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);
//        frame.setVisible(true);


    }

    public void initData(){
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
                        annoLable.setForeground(Color.black);
                        annoText.setBackground(Color.white);
                    }else {
                        annoLable.setForeground(Color.gray);
                        annoText.setBackground(Color.gray);
                    }
                }
            }
        });

        annoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(annoLable.getForeground() == Color.black){
                    if(annFile.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION){
                        annoText.setText(annFile.getSelectedFile().getPath());
                        annoText.setEditable(false);
                    }
                }
            }
        });

    }

    public String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl UD-build.pl");

        cmd.append(" -i " + dataText.getText());

        if(annoLable.getForeground()== Color.black){
            cmd.append(" -g " + annoText.getText());
        }

        cmd.append(" -o " + outputText.getText());

        cmd.append(" -p " + threadsText.getText());

//        System.out.println(cmd.toString());
        return cmd.toString();
    }

    public boolean checkData(){
        warningText.setText("");
        boolean dataValid = true;
        textFieldEmpty(dataText , Color.pink, "please choose your data file\n" );
        if(annoLable.getForeground() == Color.black){
            textFieldEmpty(annoText , Color.pink , "please choose a gff3 file\n");
        }
        textFieldEmpty(outputText , Color.pink , "please choose a output directory\n");
        Pattern number = Pattern.compile("^[0-9]*$");
        if(textFieldEmpty(threadsText , Color.pink , "please enter the number of the thread\n" )){
//            System.out.println("threadsNum :" + threadsText.getText());
        }else  if(! number.matcher(threadsText.getText()).matches()){
            warningText.append("please enter the number of the threads\n");
            threadsLable.setForeground(Color.pink);
        }else {
            threadsLable.setForeground(Color.black);
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
