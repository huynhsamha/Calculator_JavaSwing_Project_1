package project_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class mainClass extends JFrame {

    public mainClass() {
        this.initComponent();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initComponent() {
        labelCalculator = new JLabel();
        ScreenUserInterface = new JPanel();
        screenInput = new JTextField();
        screenOutput = new JTextField();
        ButtonUserInterface = new JPanel();
        copyright = new JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(450, 550));
        setResizable(false);
        getContentPane().setLayout(new java.awt.FlowLayout());
        setTitle("Calculator - Java Swing - Stupid Dog");
        
        labelCalculator.setBackground(new Color(0, 0, 0));
        labelCalculator.setFont(new Font("Verdana", 1, 30)); 
        labelCalculator.setForeground(new Color(0, 255, 51));
        labelCalculator.setHorizontalAlignment(SwingConstants.CENTER);
        labelCalculator.setText("CALCULATOR");
        labelCalculator.setOpaque(true);
        labelCalculator.setPreferredSize(new Dimension(400, 50));
        getContentPane().add(labelCalculator);

        ScreenUserInterface.setBackground(new java.awt.Color(204, 204, 255));
        ScreenUserInterface.setPreferredSize(new java.awt.Dimension(400, 160));
        ScreenUserInterface.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        screenInput.setFont(new java.awt.Font("Verdana", 0, 30)); 
        screenInput.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        screenInput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        screenInput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        screenInput.setPreferredSize(new java.awt.Dimension(370, 60));
        screenInput.setSelectedTextColor(Color.WHITE);
        screenInput.setSelectionColor(Color.DARK_GRAY);

        addScreenInputEvent();
        
        screenOutput.setEditable(false);
        screenOutput.setFont(new java.awt.Font("Verdana", 0, 30)); 
        screenOutput.setForeground(new java.awt.Color(0, 51, 204));
        screenOutput.setOpaque(true);
        screenOutput.setBackground(Color.WHITE);
        screenOutput.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        screenOutput.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 3, true));
        screenOutput.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        screenOutput.setPreferredSize(new java.awt.Dimension(370, 60));
        screenOutput.setSelectedTextColor(Color.WHITE);
        screenOutput.setSelectionColor(Color.DARK_GRAY);
        
        ScreenUserInterface.add(screenInput);
        ScreenUserInterface.add(screenOutput);
        getContentPane().add(ScreenUserInterface);
        
        ButtonUserInterface.setBackground(new java.awt.Color(204, 204, 255));
        ButtonUserInterface.setAlignmentX(10.0F);
        ButtonUserInterface.setAlignmentY(10.0F);
        ButtonUserInterface.setPreferredSize(new java.awt.Dimension(400, 250));
        ButtonUserInterface.setLayout(new java.awt.GridLayout(4, 4, 10, 10));
        
        for (int i = 0; i < 20; i++) {
            b[i] = new JButton();
            b[i].setText(strB[i]);
            b[i].setFont(new java.awt.Font("Verdana", 1, 25)); 
            b[i].setForeground(new java.awt.Color(0, 0, 0));
            b[i].setMargin(new java.awt.Insets(0, 0, 0, 0));
            b[i].setPreferredSize(new java.awt.Dimension(70, 50));
        }
        for(int i=10;i<=12;i++) b[i].setForeground(new java.awt.Color(0, 0, 204));
        for(int i=13;i<=16;i++) b[i].setForeground(new java.awt.Color(204, 0, 0));
        b[17].setForeground(new java.awt.Color(255, 0, 0));
        b[18].setForeground(new java.awt.Color(200, 0, 0));
        b[19].setForeground(new java.awt.Color(0, 0, 204));
        b[10].setFont(new java.awt.Font("Verdana", 1, 35));
        b[13].setFont(new java.awt.Font("Verdana", 1, 30));
        b[14].setFont(new java.awt.Font("Verdana", 1, 40));
        b[15].setFont(new java.awt.Font("Verdana", 1, 30));
        b[17].setFont(new java.awt.Font("Verdana", 1, 23));
        b[18].setFont(new java.awt.Font("Verdana", 1, 23));
        b[19].setFont(new java.awt.Font("Verdana", 1, 30));
        
        addButtonEvent();
        
        ButtonUserInterface.add(b[7]);
        ButtonUserInterface.add(b[8]);
        ButtonUserInterface.add(b[9]);
        ButtonUserInterface.add(b[18]);
        ButtonUserInterface.add(b[17]);
        ButtonUserInterface.add(b[4]);
        ButtonUserInterface.add(b[5]);
        ButtonUserInterface.add(b[6]);
        ButtonUserInterface.add(b[15]);
        ButtonUserInterface.add(b[16]);
        ButtonUserInterface.add(b[1]);
        ButtonUserInterface.add(b[2]);
        ButtonUserInterface.add(b[3]);
        ButtonUserInterface.add(b[13]);
        ButtonUserInterface.add(b[14]);
        ButtonUserInterface.add(b[0]);
        ButtonUserInterface.add(b[10]);
        ButtonUserInterface.add(b[19]);
        ButtonUserInterface.add(b[11]);
        ButtonUserInterface.add(b[12]);
        getContentPane().add(ButtonUserInterface);
        
        copyright.setBackground(new Color(0, 0, 0));
        copyright.setFont(new Font("Verdana", 1, 18)); 
        copyright.setForeground(new Color(0, 255, 0));
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setText("COPYRIGHT STUPID-DOG");
        copyright.setOpaque(true);
        copyright.setPreferredSize(new Dimension(400, 35));
        getContentPane().add(copyright);
    }
    
    private void updateScreenInput(String add) {
        StringBuilder cur = new StringBuilder(screenInput.getText());
        int pos = screenInput.getCaretPosition();
        cur.insert(pos, add);
        screenInput.setText(new String(cur));
        screenInput.setCaretPosition(pos+1);
    }
    
    private void addScreenInputEvent() {
        screenInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
                char c = e.getKeyChar(); 
                String sc = Character.toString(c);
                for (int i = 0; i < 17; i++) {
                    if (sc.equals(strB[i])) {
                        updateScreenInput(sc); break;
                    }
                }
                if (c == '*') updateScreenInput("x");
                if (c == KeyEvent.VK_ENTER) {
                    String exp = screenInput.getText();
                    screenOutput.setText(new solveExpression(exp).Answer());
                }
            }
        });
    }
    
    private void addButtonEvent() {
        for (int i = 0; i <= 16; i++) {
            String g = strB[i];
            b[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateScreenInput(g);
                }
            });
        }
        
        b[17].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screenInput.setText("");
                screenOutput.setText("");
            }
        });
        
        b[18].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = screenInput.getText();
                if (s.length() == 0) return ;
                int pos = screenInput.getCaretPosition();
                if (pos == 0) return ;
                String t = s.substring(0, pos-1);
                screenInput.setText(t.concat(s.substring(pos, s.length())));
                screenInput.setCaretPosition(pos-1);
            }
        });
        
        b[19].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String exp = screenInput.getText();
                screenOutput.setText(new solveExpression(exp).Answer());
            }
        });
    }
    
    /* Variables declaration*/
    private javax.swing.JPanel ButtonUserInterface;
    private javax.swing.JPanel ScreenUserInterface;
    private javax.swing.JLabel labelCalculator;
    private javax.swing.JLabel copyright;
    private javax.swing.JTextField screenInput;
    private javax.swing.JTextField screenOutput;
    private JButton b[] = new JButton[20];
    public String strB[] = {"0","1","2","3","4","5","6","7","8","9",".","(",")","+","-","x","/","AC","DEL","="};
    /**
     * 10 : DOT
     * 11 : OPEN
     * 12 : CLOSE
     * 13 : PLUS
     * 14 : SUB
     * 15 : MUL
     * 16 : DIV
     * 17 : AC
     * 18 : DEL
     * 19 : EQUAL
     */
}
