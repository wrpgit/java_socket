/*
 * Created by JFormDesigner on Thu May 28 08:15:22 CST 2020
 */

package com.wrpxcx.ui;

import java.awt.*;
import javax.swing.*;
//import com.jgoodies.forms.factories.*;
//import com.jgoodies.forms.factories.*;

/**
 * @author Brainrain
 */
public class tttt extends JFrame {
    public tttt() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        scrollPane3 = new JScrollPane();
        textArea1 = new JTextArea();
        label3 = new JLabel();
        button1 = new JButton();
        scrollPane4 = new JScrollPane();
        panel2 = new JPanel();
        panel3 = new JPanel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label5 = new JLabel();
        panel4 = new JPanel();
        label6 = new JLabel();
        textField2 = new JTextField();
        label7 = new JLabel();
        frame1 = new JFrame();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        comboBox1 = new JComboBox();
        label11 = new JLabel();
        button2 = new JButton();
        button3 = new JButton();
        textField3 = new JTextField();
        button4 = new JButton();
        label12 = new JLabel();
        frame2 = new JFrame();
        label13 = new JLabel();
        textField4 = new JTextField();
        label14 = new JLabel();
        label15 = new JLabel();
        label16 = new JLabel();
        textField5 = new JTextField();
        textField6 = new JTextField();
        textField7 = new JTextField();
        button5 = new JButton();
        label17 = new JLabel();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(60, 63, 255));
            panel1.setLayout(null);

            //---- label1 ----
            label1.setText("image");
            label1.setBackground(new Color(125, 0, 125));
            panel1.add(label1);
            label1.setBounds(120, 0, 40, 40);

            //---- label2 ----
            label2.setText("userName");
            panel1.add(label2);
            label2.setBounds(185, 5, 95, 30);

            //======== scrollPane3 ========
            {
                scrollPane3.setViewportView(textArea1);
            }
            panel1.add(scrollPane3);
            scrollPane3.setBounds(0, 310, 490, 60);

            //---- label3 ----
            label3.setText("cancel");
            panel1.add(label3);
            label3.setBounds(415, 5, 33, 35);

            //---- button1 ----
            button1.setText("\u53d1\u9001");
            panel1.add(button1);
            button1.setBounds(new Rectangle(new Point(410, 370), button1.getPreferredSize()));

            //======== scrollPane4 ========
            {

                //======== panel2 ========
                {
                    panel2.setLayout(null);

                    //======== panel3 ========
                    {
                        panel3.setForeground(new Color(10, 100, 10));
                        //panel3.setBorder(Borders.DLU21);
                        panel3.setBackground(new Color(10, 10, 10));
                        panel3.setLayout(null);

                        //---- label4 ----
                        label4.setText("head");
                        label4.setBackground(new Color(100, 100, 100));
                        panel3.add(label4);
                        label4.setBounds(10, 10, 40, 40);
                        panel3.add(textField1);
                        textField1.setBounds(65, 30, 290, 50);

                        //---- label5 ----
                        label5.setText("time");
                        panel3.add(label5);
                        label5.setBounds(70, 5, 100, 25);

                        { // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel3.getComponentCount(); i++) {
                                Rectangle bounds = panel3.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel3.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel3.setMinimumSize(preferredSize);
                            panel3.setPreferredSize(preferredSize);
                        }
                    }
                    panel2.add(panel3);
                    panel3.setBounds(0, 5, 490, 80);

                    //======== panel4 ========
                    {
                        panel4.setForeground(new Color(10, 100, 10));
                        //panel4.setBorder(Borders.DLU21);
                        panel4.setBackground(new Color(10, 10, 10));
                        panel4.setLayout(null);

                        //---- label6 ----
                        label6.setText("head");
                        label6.setBackground(new Color(100, 100, 100));
                        panel4.add(label6);
                        label6.setBounds(435, 35, 40, 40);
                        panel4.add(textField2);
                        textField2.setBounds(140, 30, 290, 50);

                        //---- label7 ----
                        label7.setText("time");
                        panel4.add(label7);
                        label7.setBounds(320, 5, 100, 25);

                        { // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < panel4.getComponentCount(); i++) {
                                Rectangle bounds = panel4.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = panel4.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            panel4.setMinimumSize(preferredSize);
                            panel4.setPreferredSize(preferredSize);
                        }
                    }
                    panel2.add(panel4);
                    panel4.setBounds(0, 85, 490, 80);

                    { // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel2.getComponentCount(); i++) {
                            Rectangle bounds = panel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel2.setMinimumSize(preferredSize);
                        panel2.setPreferredSize(preferredSize);
                    }
                }
                scrollPane4.setViewportView(panel2);
            }
            panel1.add(scrollPane4);
            scrollPane4.setBounds(0, 65, 490, 245);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < panel1.getComponentCount(); i++) {
                    Rectangle bounds = panel1.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = panel1.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                panel1.setMinimumSize(preferredSize);
                panel1.setPreferredSize(preferredSize);
            }
        }

        //======== frame1 ========
        {
            Container frame1ContentPane = frame1.getContentPane();
            frame1ContentPane.setLayout(null);

            //---- label8 ----
            label8.setText("image");
            frame1ContentPane.add(label8);
            label8.setBounds(30, 90, 55, 55);

            //---- label9 ----
            label9.setText("\u6635\u79f0\uff1a");
            frame1ContentPane.add(label9);
            label9.setBounds(110, 95, 245, 25);

            //---- label10 ----
            label10.setText("\u4e2a\u6027\u7b7e\u540d");
            frame1ContentPane.add(label10);
            label10.setBounds(110, 130, 225, 25);
            frame1ContentPane.add(comboBox1);
            comboBox1.setBounds(110, 190, 190, 40);

            //---- label11 ----
            label11.setText("\u9009\u62e9\u5206\u7ec4\uff1a");
            frame1ContentPane.add(label11);
            label11.setBounds(25, 195, 75, 25);

            //---- button2 ----
            button2.setText("\u786e\u8ba4\u6dfb\u52a0");
            frame1ContentPane.add(button2);
            button2.setBounds(70, 305, 80, 30);

            //---- button3 ----
            button3.setText("\u53d6\u6d88");
            frame1ContentPane.add(button3);
            button3.setBounds(210, 305, 88, 30);

            //---- textField3 ----
            textField3.setText(";");
            frame1ContentPane.add(textField3);
            textField3.setBounds(40, 25, 245, 30);

            //---- button4 ----
            button4.setText("search");
            frame1ContentPane.add(button4);
            button4.setBounds(new Rectangle(new Point(305, 25), button4.getPreferredSize()));

            //---- label12 ----
            label12.setText("text");
            frame1ContentPane.add(label12);
            label12.setBounds(45, 65, 195, 20);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame1ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frame1ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame1ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame1ContentPane.setMinimumSize(preferredSize);
                frame1ContentPane.setPreferredSize(preferredSize);
            }
            frame1.pack();
            frame1.setLocationRelativeTo(frame1.getOwner());
        }

        //======== frame2 ========
        {
            Container frame2ContentPane = frame2.getContentPane();
            frame2ContentPane.setLayout(null);

            //---- label13 ----
            label13.setText("\u6635\u79f0\uff1a");
            frame2ContentPane.add(label13);
            label13.setBounds(45, 30, 45, 20);
            frame2ContentPane.add(textField4);
            textField4.setBounds(80, 25, 170, 30);

            //---- label14 ----
            label14.setText("\u4e2a\u6027\u7b7e\u540d\uff1a");
            frame2ContentPane.add(label14);
            label14.setBounds(20, 160, 60, 15);

            //---- label15 ----
            label15.setText("\u5bc6\u7801\uff1a");
            frame2ContentPane.add(label15);
            label15.setBounds(new Rectangle(new Point(45, 65), label15.getPreferredSize()));

            //---- label16 ----
            label16.setText("\u91cd\u590d\u5bc6\u7801\uff1a");
            frame2ContentPane.add(label16);
            label16.setBounds(20, 100, 65, 20);
            frame2ContentPane.add(textField5);
            textField5.setBounds(80, 60, 170, 30);
            frame2ContentPane.add(textField6);
            textField6.setBounds(80, 95, 170, 30);
            frame2ContentPane.add(textField7);
            textField7.setBounds(80, 150, 170, 30);

            //---- button5 ----
            button5.setText("\u6ce8\u518c");
            frame2ContentPane.add(button5);
            button5.setBounds(105, 215, 80, 30);

            //---- label17 ----
            label17.setText("\u63d0\u793a\u4fe1\u606f");
            frame2ContentPane.add(label17);
            label17.setBounds(85, 125, 145, 25);

            { // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < frame2ContentPane.getComponentCount(); i++) {
                    Rectangle bounds = frame2ContentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = frame2ContentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                frame2ContentPane.setMinimumSize(preferredSize);
                frame2ContentPane.setPreferredSize(preferredSize);
            }
            frame2.pack();
            frame2.setLocationRelativeTo(frame2.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JScrollPane scrollPane3;
    private JTextArea textArea1;
    private JLabel label3;
    private JButton button1;
    private JScrollPane scrollPane4;
    private JPanel panel2;
    private JPanel panel3;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label5;
    private JPanel panel4;
    private JLabel label6;
    private JTextField textField2;
    private JLabel label7;
    private JFrame frame1;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JComboBox comboBox1;
    private JLabel label11;
    private JButton button2;
    private JButton button3;
    private JTextField textField3;
    private JButton button4;
    private JLabel label12;
    private JFrame frame2;
    private JLabel label13;
    private JTextField textField4;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button5;
    private JLabel label17;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
