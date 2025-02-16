package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CV {
    //SWING COMPONENTS
    private JPanel cvPanel;  // Make sure this is initialized
    private JTextField name;
    private JTextField address;
    private JTextField contact;
    private JTextField email;
    private JTextField skills1;
    private JTextField skills2;
    private JTextField skills3;
    private JTextField skills4;
    private JComboBox<String> work;
    private JTextField college;
    private JTextField qualiA;
    private JTextField qualiB;
    private JTextField location;
    private JButton SELECTIMAGEButton;
    private JLabel img;
    private JButton GENERATERESUMEButton;

    JFrame frame = new JFrame();

    public CV() {
        // Initialize cvPanel
        cvPanel = new JPanel();
        cvPanel.setLayout(new GridLayout(0, 2));  // Set a layout

        // Initialize components
        name = new JTextField();
        address = new JTextField();
        contact = new JTextField();
        email = new JTextField();
        skills1 = new JTextField();
        skills2 = new JTextField();
        skills3 = new JTextField();
        skills4 = new JTextField();
        college = new JTextField();
        qualiA = new JTextField();
        qualiB = new JTextField();
        location = new JTextField();
        img = new JLabel(); // Initialize the JLabel
        SELECTIMAGEButton = new JButton("Select Image");
        GENERATERESUMEButton = new JButton("Generate Resume");


        // Add components to the cvPanel
        cvPanel.add(new JLabel("Name:")); cvPanel.add(name);
        cvPanel.add(new JLabel("Address:")); cvPanel.add(address);
        cvPanel.add(new JLabel("Contact:")); cvPanel.add(contact);
        cvPanel.add(new JLabel("Email:")); cvPanel.add(email);
        cvPanel.add(new JLabel("Skills 1:")); cvPanel.add(skills1);
        cvPanel.add(new JLabel("Skills 2:")); cvPanel.add(skills2);
        cvPanel.add(new JLabel("Skills 3:")); cvPanel.add(skills3);
        cvPanel.add(new JLabel("Skills 4:")); cvPanel.add(skills4);
        cvPanel.add(new JLabel("College:")); cvPanel.add(college);
        cvPanel.add(new JLabel("Qualification A:")); cvPanel.add(qualiA);
        cvPanel.add(new JLabel("Qualification B:")); cvPanel.add(qualiB);
        cvPanel.add(new JLabel("Work Experience:"));
        work = new JComboBox<>(new String[]{"Experience 1", "Experience 2"}); // Example options
        cvPanel.add(work);
        cvPanel.add(new JLabel("Image Location:")); cvPanel.add(location);
        cvPanel.add(SELECTIMAGEButton);
        cvPanel.add(GENERATERESUMEButton);
        cvPanel.add(img);
//***********************************************R
        // Setup the JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(cvPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Action Listener for image selection
        SELECTIMAGEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "png");
                fileChooser.addChoosableFileFilter(filter);
                int rs = fileChooser.showOpenDialog(null);
                if (rs == JFileChooser.APPROVE_OPTION) {
                    File selectedImage = fileChooser.getSelectedFile();
                    location.setText(selectedImage.getAbsolutePath());
                    img.setIcon(resizeImage(location.getText()));
                }
            }
        });

        //********************************************************

        // Action Listener for resume generation
        GENERATERESUMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isInputValid()) {
                    try {
                        PDFGenerator.generateResume(
                                name.getText(),
                                address.getText(),
                                contact.getText(),
                                email.getText(),
                                skills1.getText(), skills2.getText(), skills3.getText(), skills4.getText(),
                                college.getText(),
                                qualiA.getText(), qualiB.getText(),
                                work.getSelectedItem().toString(),
                                location.getText()
                        );
                        JOptionPane.showMessageDialog(null, "CV successfully generated!");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter all required details!");
                }
            }
        });
    }



    private boolean isInputValid() {
        return !name.getText().isEmpty() && !address.getText().isEmpty() && !contact.getText().isEmpty() &&
                !email.getText().isEmpty() && !skills1.getText().isEmpty() && !skills2.getText().isEmpty() &&
                !skills3.getText().isEmpty() && !skills4.getText().isEmpty() && !college.getText().isEmpty() &&
                !qualiA.getText().isEmpty() && !qualiB.getText().isEmpty();
    }

    private ImageIcon resizeImage(String path) {
        ImageIcon myImg = new ImageIcon(path);
        Image img = myImg.getImage();
        Image newImage = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
