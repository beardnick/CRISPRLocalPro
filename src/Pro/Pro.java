package Pro;


import Util.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by asus on 2017/11/27.
 */
public class Pro{

    private JFrame frame = new JFrame();

    private JPanel topPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JPanel referencePanel = new JPanel();
    private JPanel userDataPanel = new JPanel();
    private JPanel geneSearchPanel = new JPanel();
    private JPanel plSearchPanel = new JPanel();

    private MyJButton referenceBtn = new MyJButton();
    private MyJButton userDataBtn = new MyJButton();
    private MyJButton geneSearchBtn = new MyJButton();
    private JLabel maizegoLabel = new JLabel();

    private ReferenceGenome referenceGenome;
    private UserData userData;
    private GeneSearch geneSearch;
    private PlSearch plSearch;

    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JPanel mainPanel;

    private JLabel title = new JLabel("CRISPR-Local");
    private JLabel viceTitle = new JLabel("A local tool for high-throughput CRISPR single-guide RNA (sgRNA) design in plants.");
    private MyJButton helpBtn = new MyJButton();
    private TextArea helpText = new TextArea("help text" , 10 , 25 , TextArea.SCROLLBARS_VERTICAL_ONLY);
    private JDialog help = new JDialog(frame , "help text" , true);


    public void initView(){
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        referenceBtn.setIcon(new ImageIcon("src/Resource/R.png"));
        userDataBtn.setIcon(new ImageIcon("src/Resource/U.png"));
        geneSearchBtn.setIcon(new ImageIcon("src/Resource/D.png"));
        helpBtn.setIcon(new ImageIcon("src/Resource/help.png"));
        maizegoLabel.setIcon(new ImageIcon("src/Resource/MaizeGo_logo.png"));


        title.setFont(R.titleFont);

        helpText.setFont(R.infoFont);
        help.add(helpText);
        help.setResizable(false);
        help.setSize(1000 , 500);

        referenceGenome = new ReferenceGenome(referencePanel, frame);
        userData = new UserData(userDataPanel , frame);
        geneSearch = new GeneSearch(geneSearchPanel , frame);
        referenceGenome.initView();
        userData.initView();
        geneSearch.initView();
        userDataPanel.setVisible(false);
        geneSearchPanel.setVisible(false);
        referencePanel.setVisible(true);
        topPanel.setLayout(layout);

//        constraints.anchor = GridBagConstraints.WEST;
        addComp(constraints , 0 , 0 , 1, 1 , new Insets(10 , 10 , 10 , 100) );
        layout.setConstraints(title  , constraints);
        topPanel.add(title);


        addComp(constraints , 2 , 0 , 1 , 1 , new Insets(10 , 100 , 10 , 10));
        layout.setConstraints(helpBtn , constraints);
        topPanel.add(helpBtn);

        addComp(constraints , 0 , 1 , 4 , 1, new Insets(10 , 10 , 10 , 10));
                layout.setConstraints( viceTitle, constraints);
                topPanel.add(viceTitle);

        topPanel.setBackground(Color.white);
        frame.add(topPanel , BorderLayout.NORTH);

//        addComp(constraints , 1, 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
//        layout.setConstraints(referenceBtn , constraints);
//        topPanel.add(referenceBtn);
//
//        addComp(constraints , 2 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
//        layout.setConstraints(userDataBtn , constraints);
//        topPanel.add(userDataBtn);
//
//        addComp(constraints , 3 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
//        layout.setConstraints(geneSearchBtn , constraints);
//        topPanel.add(geneSearchBtn);

        constraints.fill = GridBagConstraints.NONE;

        //setLayout后才会生效
        leftPanel.setLayout(layout);
//        leftPanel.setBackground(Color.white);

        addComp(constraints , 0 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referenceBtn , constraints);
        leftPanel.add(referenceBtn);

        addComp(constraints , 0 , 1 , 1 ,1 , new Insets(10 , 10 , 10 , 10));
                layout.setConstraints( userDataBtn, constraints);
                leftPanel.add(userDataBtn);

        addComp(constraints , 0 , 2 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(geneSearchBtn , constraints);
        leftPanel.add(geneSearchBtn);

        addComp(constraints ,  0,  3, 1 ,1 , new Insets(200 , 10 , 10 , 10));
                layout.setConstraints(maizegoLabel, constraints);
                leftPanel.add(maizegoLabel);


        frame.add(leftPanel , BorderLayout.WEST);

        mainPanel = referencePanel;

        frame.add(mainPanel , BorderLayout.CENTER);
        frame.setVisible(true);

    }

    public void initData(){
        referenceGenome.initData();
        geneSearch.initData();
        userData.initData();
    }

    public void initEvent(){
        maizegoLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.maizego.org/"));
                } catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
        referenceGenome.initEvent();
        geneSearch.initEvent();
        userData.initEvent();

        referenceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.add(topPanel , BorderLayout.NORTH);

                mainPanel = referencePanel;
                referencePanel.setVisible(true);
                geneSearchPanel.setVisible(false);
                userDataPanel.setVisible(false);

                frame.add(mainPanel , BorderLayout.CENTER);
//                mainPanel = referencePanel;
                referencePanel.setVisible(true);
            }
        });

        userDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.add(topPanel , BorderLayout.NORTH);

                mainPanel = userDataPanel;
                userDataPanel.setVisible(true);
                referencePanel.setVisible(false);
                geneSearchPanel.setVisible(false);

                frame.add(mainPanel , BorderLayout.CENTER);
//                mainPanel = userDataPanel;
                userDataPanel.setVisible(true);
            }
        });

        geneSearchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.add(topPanel , BorderLayout.NORTH);

                mainPanel = geneSearchPanel;
                geneSearchPanel.setVisible(true);
                referencePanel.setVisible(false);
                userDataPanel.setVisible(false);

                frame.add(mainPanel , BorderLayout.CENTER);
//                mainPanel = geneSearchPanel;

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
                    helpText.setText("");
                    while ((temp = reader.readLine()) != null ){
                        helpText.append(temp + "\n");
                    }
                    reader.close();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                help.setTitle("help text");
                help.setVisible(true);
            }
        });
    }

    private void addComp(GridBagConstraints constraints , int x , int y , int gridWidth , int gridHeight , Insets insets){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridheight = gridHeight;
        constraints.gridwidth = gridWidth;
        constraints.insets = insets;
    }


    public static void main(String[] args){
        Pro pro = new Pro();
        pro.initView();
        pro.initData();
        pro.initEvent();
    }

}
