package Pro;

import Util.MyJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by asus on 2017/11/27.
 */
public class Pro{

    private JFrame frame = new JFrame();


    private MyJButton referenceBtn = new MyJButton();
    private MyJButton userDataBtn = new MyJButton();
    private MyJButton geneSearchBtn = new MyJButton();

    private JPanel topPanel = new JPanel();
    private JPanel referencePanel = new JPanel();
    private JPanel userDataPanel = new JPanel();
    private JPanel geneSearchPanel = new JPanel();

    private ReferenceGenome referenceGenome;
    private UserData userData;
    private GeneSearch geneSearch;

    private GridBagConstraints constraints = new GridBagConstraints();
    private GridBagLayout layout = new GridBagLayout();

    private JPanel mainPanel;

    public void initView(){
        referenceBtn.setIcon(new ImageIcon("src/Resource/R.png"));
        userDataBtn.setIcon(new ImageIcon("src/Resource/U.png"));
        geneSearchBtn.setIcon(new ImageIcon("src/Resource/D.png"));
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

        addComp(constraints , 0 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(referenceBtn , constraints);
        topPanel.add(referenceBtn);

        addComp(constraints , 1 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(userDataBtn , constraints);
        topPanel.add(userDataBtn);

        addComp(constraints , 2 , 0 , 1 , 1 , new Insets(10 , 10 , 10 , 10));
        layout.setConstraints(geneSearchBtn , constraints);
        topPanel.add(geneSearchBtn);

        topPanel.setBackground(Color.white);
        frame.add(topPanel , BorderLayout.NORTH);

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
