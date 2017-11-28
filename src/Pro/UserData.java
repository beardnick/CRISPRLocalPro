package Pro;

import Util.CmdHelper;
import Util.MyJButton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
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
public class UserData {

    public UserData(JPanel panel , JFrame frame){
        this.mainPanel = panel;
        this.frame = frame;

    }

    private JFrame frame ;
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
    private MyJButton helpBtn = new MyJButton();

    private MyJButton submitBtn = new MyJButton();

    private JPanel titlePanel = new JPanel();
    private JPanel contentPanel = new JPanel();

    private GridBagConstraints con = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();


    private TextArea info = new TextArea("information" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog information = new JDialog(frame , "information" , false);
    private JFileChooser annFile = new JFileChooser();
    private JFileChooser dataFile = new JFileChooser();
    private JFileChooser outputFile = new JFileChooser();
    private String helpCmd = "!!!!";
    private File batFile;

    private TextArea warningText = new TextArea("warning" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog warning = new JDialog(frame , "warning" , true);

    private CmdHelper cmdHelper = new CmdHelper(info);
    private StringBuilder dataString = new StringBuilder("");


    public void initView(){
        title.setFont(R.titleFont);
        viceTitle.setFont(R.textFont);

        dataLable.setFont(R.textFont);
        outputLable.setFont(R.textFont);
        annoLable.setFont(R.textFont);
        annoLable.setForeground(Color.gray);
        threadsLable.setFont(R.textFont);

        information.add(info);
        information.setSize(1500 , 500);
        information.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);


        dataText.setFont(R.textFont);
        annoText.setFont(R.textFont);
        annoText.setOpaque(true);
        annoText.setBackground(Color.gray);
        outputText.setFont(R.textFont);
        threadsText.setFont(R.textFont);

        ImageIcon dirIcon = new ImageIcon("src/Resource/dir.png");
        ImageIcon submitIcon = new ImageIcon("src/Resource/submit.png");
        helpBtn.setIcon(new ImageIcon("src/Resource/help.png"));
        dataBtn.setIcon(dirIcon);
        annoBtn.setIcon(dirIcon);
        outputBtn.setIcon(dirIcon);

        submitBtn.setIcon(submitIcon);

        warningText.setFont(R.infoFont);
        warning.add(warningText);
        warning.setResizable(false);
        warning.setSize(1000 , 500);

//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         mainPanel.setLayout(new BorderLayout());
//        frame.setLayout(new BorderLayout());

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;

        contentPanel.setLayout(layout);
        contentPanel.setBackground(Color.white);
        titlePanel.setLayout(layout);
        titlePanel.setBackground(Color.white);


        addComp(con , 0 , 0  ,3 , 2 ,new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(title , con);
        titlePanel.add(title);

        con.anchor = GridBagConstraints.WEST;
        addComp(con , 4 , 3 , 1 , 5 , new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , con);
        titlePanel.add(viceTitle);


        //contentPanel

        addComp(con , 9 , 0 , 1 , 1 , new Insets(10 , 30, 10 , 10));
        layout.setConstraints(helpBtn , con);
        contentPanel.add(helpBtn);

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
        addComp(con , 8 , 1 , 1 , 1 , new Insets(10 , 30 , 10 , 10));
        layout.setConstraints(dataBtn , con);
        contentPanel.add(dataBtn);

        addComp(con , 8 , 2 , 1 , 1 , new Insets(10 , 30 , 10 , 10));
        layout.setConstraints(annoBtn , con);
        contentPanel.add(annoBtn);


        addComp(con , 8 , 3 , 1 , 1 , new Insets(10 , 30 , 10 , 10));
        layout.setConstraints(outputBtn , con);
        contentPanel.add(outputBtn);

        addComp(con , 9 , 5 , 1 , 1 , new Insets(50 , 10 , 10 , 10));
        layout.setConstraints(submitBtn , con);
        contentPanel.add(submitBtn);


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
        mainPanel.add(titlePanel , BorderLayout.NORTH);
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
                return ".gff3";
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
                        ".bam " +
                                ".sam " +
                                ".fa " +
                                ".fasta " +
                                ".fa.gz " +
                                ".fasta.gz " +
                                ".fq " +
                                ".fastq " +
                                ".fq.gz " +
                                ".fastq.gz ";
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
                            dataString.append(";");
                        }
                        dataString.append(dataFile.getSelectedFile().getPath());
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

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkData()){
                    try {
                        information.setVisible(true);
                        cmdHelper.execCmd(commandBuilder());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    warning.setTitle("warning");
                    warning.setVisible(true);
                }

            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader;
                    if(System.getProperty("os.name").toLowerCase().startsWith("lin")
                            || System.getProperty("os.name").toLowerCase().startsWith("ubu")){
                        reader = new BufferedReader(new InputStreamReader(
                                new FileInputStream("src/Resource/help.txt") , "GBK"
                        ));
                    }else {
                        reader = new BufferedReader(new InputStreamReader(
                                new FileInputStream("src/Resource/help.txt")
                        ));
                    }
                    String temp = "";
                    warningText.setText("");
                    while ((temp = reader.readLine()) != null ){
                        warningText.append(temp + "\n");
                    }
                    reader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                warning.setTitle("help text");
                warning.setVisible(true);
            }
        });



        information.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm =  JOptionPane.showConfirmDialog(frame ,
                        "do you really want to stop the process ?" , "warning" , JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION ){
                    try {
                        System.out.println(confirm);
                        cmdHelper.stopCmd();
                        information.dispose();
                        frame.dispose();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });


    }

    private String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl ./ProgramFile/User_sgRNA.pl");

        cmd.append(" -i " + dataText.getText());

        if(annoLable.getForeground()== Color.black){
            cmd.append(" -g " + annoText.getText());
        }

        cmd.append(" -o " + outputText.getText());

        cmd.append(" -p " + threadsText.getText());

        System.out.println(cmd.toString());
        return cmd.toString();
    }



    private boolean checkData(){
        warningText.setText("");
        boolean dataValid = true;
        textFieldEmpty(dataText , Color.pink, "please choose your data file\n" );
        if(annoLable.getForeground() == Color.black){
            textFieldEmpty(annoText , Color.pink , "please choose a gff3 file\n");
        }
        textFieldEmpty(outputText , Color.pink , "please choose a output directory\n");
        Pattern number = Pattern.compile("^[0-9]*$");
        if(textFieldEmpty(threadsText , Color.pink , "please enter the number of the thread\n" )){
            System.out.println("threadsNum :" + threadsText.getText());
        }else  if(! number.matcher(threadsText.getText()).matches()){
            warningText.append("please enter the number of the threads\n");
            threadsLable.setForeground(Color.pink);
        }else {
            threadsLable.setForeground(Color.black);
        }
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

//    private boolean batBuilder(String cmd) throws IOException {
//        boolean isTheSameCmd = false;
//        if(! (isTheSameCmd = cmd.equals(helpCmd)) ){
//            helpCmd = cmd;
//            batFile = new File("userData.bat");
//            int i = 1;
//            while (batFile.exists()){
//                batFile = new File("userData(" + String.valueOf(i) + ")" + ".bat");
//                i ++;
//            }
//            System.out.println(batFile.getName());
//            batFile.createNewFile();
//            FileWriter writer = new FileWriter(batFile);
//            writer.write(cmd);
//            writer.close();
//        }
//
//        return ! isTheSameCmd;
//    }




    private void addComp(GridBagConstraints constraints , int x , int y , int gridWidth , int gridHeight , Insets insets){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.insets = insets;
    }

}
