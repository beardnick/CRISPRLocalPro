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
public class ReferenceGenome{


    public ReferenceGenome(JPanel panel , JFrame frame){
        this.mainPanel = panel;
        this.frame = frame;

    }

    private JFrame frame ;
    private JPanel mainPanel;

    private JLabel referLable = new JLabel("Reference genome :");
    private JLabel annoLable = new JLabel("Genome annotation :");
    private JLabel option = new JLabel("Options :");
    private JLabel outputLable = new JLabel("Output dirctory :");
    private JLabel threadsLable = new JLabel("Threads :");
    private JLabel labelLable = new JLabel("Lable :");
    private JLabel end5Lable = new JLabel("Expanding 5'-end :");
    private JLabel end3Lable = new JLabel("Expanding 3'-end :");
    private JLabel nt = new JLabel("nt");
    private JLabel newNt = new JLabel("nt");


    private JTextField referDir = new JTextField();
    private JTextField annoDir = new JTextField();
    private JTextField outDir = new JTextField();
    private JTextField threadsNum = new JTextField();
    private JTextField labelText = new JTextField();
    private JTextField end5Text = new JTextField();
    private JTextField end3Text = new JTextField();


    private MyJButton referBtn = new MyJButton();
    private MyJButton annoBtn = new MyJButton();
    private MyJButton outputBtn = new MyJButton();
    private MyJButton submitBtn = new MyJButton();
    private MyJButton helpBtn = new MyJButton();


    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("Reference genome sgRNA design");

    private JFileChooser referFile = new JFileChooser();
    private JFileChooser annFile = new JFileChooser();
    private JFileChooser outputFile = new JFileChooser();

    private GridBagLayout layout = new GridBagLayout();
    private GridBagConstraints con = new GridBagConstraints();


    private JPanel titlePanel = new JPanel();
    private JPanel labelPanel = new JPanel();

    private TextArea info = new TextArea("information" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private TextArea warningText = new TextArea("warning" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);

    private JDialog information = new JDialog(frame , "information" , false);
    private JDialog warning = new JDialog(frame , "warning" , true);
    private File batFile;

    private String helpCmd = "!!!!!";//to make sure the same bat file will only be created once



    //   private CmdHelper cmdHelper = new CmdHelper(info);



    public void initView(){
  //      frame.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());
        title.setFont(R.titleFont);
        viceTitle.setFont(R.viceTitleFont);
        referLable.setFont(R.textFont);
        annoLable.setFont(R.textFont);
        referDir.setSize(R.text_width , R.height);
        referDir.setFont(R.textFont);
        annoDir.setSize(R.text_width , R.height);
        annoDir.setFont(R.textFont);
        option.setFont(new Font("Dialog" , 1 , 30));
        outputLable.setFont(R.textFont);
        outDir.setFont(R.textFont);
        threadsLable.setFont(R.textFont);
        threadsNum.setFont(R.textFont);
        labelLable.setFont(R.textFont);
        labelText.setFont(R.textFont);
        labelText.setText("CRISPR");
        end5Lable.setFont(R.textFont);
        end5Text.setFont(R.textFont);
        end3Lable.setFont(R.textFont);
        end3Text.setFont(R.textFont);
        nt.setFont(R.textFont);
        newNt.setFont(R.textFont);
        info.setEditable(false);
        info.setFont(R.infoFont);
        info.setColumns(25);
        information.add(info);
        info.setBackground(Color.white);
        information.setSize(1000 , 500);
        information.setResizable(false);
        information.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        warningText.setFont(R.infoFont);
        warning.add(warningText);
        warning.setResizable(false);
        warning.setSize(1000 , 500);
        Icon dirIcon = new ImageIcon("src/Resource/dir.png");
        submitBtn.setIcon(new ImageIcon("src/Resource/submit.png"));
        helpBtn.setIcon(new ImageIcon("src/Resource/help.png"));
        referBtn.setIcon(dirIcon);
        referBtn.setBackground(Color.white);
        annoBtn.setIcon(dirIcon);
        annoBtn.setBackground(Color.white);
        outputBtn.setIcon(dirIcon);

        con.fill = GridBagConstraints.BOTH;
        con.weightx = 1;

        labelPanel.setLayout(layout);
        labelPanel.setBackground(Color.white);
        titlePanel.setLayout(layout);
        titlePanel.setBackground(Color.white);


        addComp(con , 0 , 0  ,3 , 2 ,new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(title , con);
    //    titlePanel.add(title);

        addComp(con ,4  , 0 , 1 , 1 , new Insets(30 , 10, 10 , 10));
        layout.setConstraints(helpBtn , con);
     //   titlePanel.add(helpBtn);

        con.anchor = GridBagConstraints.WEST;
        addComp(con , 3 , 0 , 1 , 1, new Insets(10 , 10 , 10 ,10));
        layout.setConstraints(viceTitle , con);
        labelPanel.add(viceTitle);

        mainPanel.add(titlePanel , BorderLayout.NORTH);
//        frame.add(titlePanel , BorderLayout.NORTH);



        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 3;
        addComp(con , 3 , 1 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.WEST;
        layout.setConstraints(referDir , con);
        labelPanel.add(referDir);



        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        addComp(con , 10 , 1 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referBtn , con);
        labelPanel.add(referBtn);


        addComp(con , 3 , 2 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.WEST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 3;
        layout.setConstraints(annoDir , con);
        labelPanel.add(annoDir);

        addComp(con , 10 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(annoBtn , con);
        labelPanel.add(annoBtn);

        addComp(con , 3 , 4 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.WEST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 3;
        layout.setConstraints(outDir , con);
        labelPanel.add(outDir);

        addComp(con , 10 , 4 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.EAST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(outputBtn , con);
        labelPanel.add(outputBtn);

        addComp(con , 3 , 5 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.WEST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(threadsNum , con);
        labelPanel.add(threadsNum);

        addComp(con , 3 , 6 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.EAST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(labelText , con);
        labelPanel.add(labelText);

        addComp(con , 3 , 7 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.WEST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(end5Text , con);
        labelPanel.add(end5Text);

        addComp(con , 10 , 8 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(newNt , con);
        labelPanel.add(newNt);

        addComp(con , 3 , 8 , 7 , 1 , new Insets(10 , 10 , 10 , 10));
        con.anchor = GridBagConstraints.EAST;
        con.fill = GridBagConstraints.HORIZONTAL;
        con.weightx = 1;
        layout.setConstraints(end3Text , con);
        labelPanel.add(end3Text);


        addComp(con , 10 , 7 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(nt , con);
        labelPanel.add(nt);

        con.fill = GridBagConstraints.NONE;
        con.weightx = 1;//all label wont expand
        //labelPanel
        con.anchor = GridBagConstraints.EAST;
        addComp(con , 0 , 1 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referLable , con);
        labelPanel.add(referLable);


        addComp(con , 0 , 2 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(annoLable , con);
        labelPanel.add(annoLable);


        addComp(con , 0 , 3 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(option , con);
        labelPanel.add(option);

        con.anchor = GridBagConstraints.EAST;
        addComp(con , 0 , 4 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(outputLable , con);
        labelPanel.add(outputLable);


        addComp(con , 0 , 5 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(threadsLable , con);
        labelPanel.add(threadsLable);


        addComp(con , 0 , 6 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(labelLable , con);
        labelPanel.add(labelLable);


        addComp(con , 0 , 7 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(end5Lable , con);
        labelPanel.add(end5Lable);


        addComp(con , 0 , 8 , 2 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(end3Lable , con);
        labelPanel.add(end3Lable);

        addComp(con , 11 , 9 , 2 , 1 , new Insets(10 , 10 , 100 , 50));
        //  con.fill = GridBagConstraints.HORIZONTAL;
        layout.setConstraints(submitBtn , con);
        labelPanel.add(submitBtn);




        addComp(con , 0 , 3 , 3 , 10 , new Insets(10  , 10 ,10 , 10));
        con.fill = GridBagConstraints.VERTICAL;
        con.anchor = GridBagConstraints.WEST;
        layout.setConstraints(labelPanel , con);
//        frame.add(labelPanel , BorderLayout.CENTER);
        mainPanel.add(labelPanel , BorderLayout.CENTER);
      //  frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.white);
        frame.setSize(R.frame_width , R.frame_height);
//        frame.setVisible(true);
    }


    public void initData(){
        threadsNum.setText("1");

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

    private CmdHelper cmdHelper = new CmdHelper(info);

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

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkData()){
                    try {
                        information.setVisible(true);
                        cmdHelper.execCmd(commandBuilder());
                        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

//        helpBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    BufferedReader reader;
//                    if(System.getProperty("os.name").toLowerCase().startsWith("lin")
//                            || System.getProperty("os.name").toLowerCase().startsWith("ubu")){
//                        reader = new BufferedReader(new InputStreamReader(
//                                new FileInputStream("src/Resource/help.txt") , "GBK"
//                        ));
//                    }else {
//                        reader = new BufferedReader(new InputStreamReader(
//                                new FileInputStream("src/Resource/help.txt")
//                        ));
//                    }
//                    String temp = "";
//                    warningText.setText("");
//                    while ((temp = reader.readLine()) != null ){
//                        warningText.append(temp + "\n");
//                    }
//                    reader.close();
//                } catch (FileNotFoundException e1) {
//                    e1.printStackTrace();
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                warning.setTitle("help text");
//                warning.setVisible(true);
//            }
//        });

    }

    public String commandBuilder(){
        StringBuilder cmd = new StringBuilder("perl CRISPR_Local.pl");

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

        //-U opt_up

        cmd.append(" -U " + end5Text.getText());

        //-D opt_down

        cmd.append(" -D " + end3Text.getText());


        System.out.println(cmd.toString());

        return cmd.toString();
    }

//    private boolean batBuilder(String cmd) throws IOException {
//        boolean isTheSameCmd = false;
//        if(! (isTheSameCmd = cmd.equals(helpCmd)) ){
//            helpCmd = cmd;
//            batFile = new File(labelText.getText() + ".bat");
//            int i = 1;
//            while (batFile.exists()){
//                batFile = new File(labelText.getText() + "(" + String.valueOf(i) + ")" + ".bat");
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




    private boolean checkData(){
        warningText.setText("");
        textFieldEmpty(referDir , Color.pink, "please choose a fasta file\n" );
        textFieldEmpty(annoDir , Color.pink , "please choose a gff3 file\n");
        Pattern number = Pattern.compile("^[0-9]*$");
        if(textFieldEmpty(threadsNum , Color.pink , "please enter the number of the thread\n" )){
            System.out.println("threadsNum :" + threadsNum.getText());
        }else  if(! number.matcher(threadsNum.getText()).matches()){
            warningText.append("please enter the number of the threads\n");
            threadsLable.setForeground(Color.pink);
        }else {
            threadsLable.setForeground(Color.black);
        }

        if(textFieldEmpty(end3Text , Color.pink , "please enter a number between 0 and 15 in Expanding 3 ' -end\n")){
            System.out.println("end3Text :" + end3Text.getText());
        }else if(!  number.matcher(end3Text.getText()).matches()){
            warningText.append("please enter number to end3 text\n");
            end3Lable.setForeground(Color.pink);
        }else if(Integer.valueOf(end3Text.getText()) > 15 || Integer.valueOf(end3Text.getText()) < 0){
            warningText.append("the number must between 0 and 15\n");
            end3Lable.setForeground(Color.pink);

        }else{
            end3Lable.setForeground(Color.black);
        }

        if(textFieldEmpty(end5Text , Color.pink , "please enter a number between 0 and 15 in Expanding 5 ' -end\n" )){
            System.out.println("end5Text :" + end5Text.getText());
        }else if(! number.matcher(end5Text.getText()).matches()){
            warningText.append("please enter number to end5 text\n");
            end5Lable.setForeground(Color.pink);
        } else  if(Integer.valueOf(end5Text.getText()) > 15 || Integer.valueOf(end5Text.getText()) < 0){
            warningText.append("the number must between 0 and 15\n");
            end5Lable.setForeground(Color.pink);
        }else{
            end5Lable.setForeground(Color.black);
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

