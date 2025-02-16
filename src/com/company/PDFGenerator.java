package com.company;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;

public class PDFGenerator {

    public static void generateResume(String name, String address, String contact, String email,
                                      String skill1, String skill2, String skill3, String skill4,
                                      String college, String qualiA, String qualiB,
                                      String workExperience, String imagePath) throws Exception {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose where to save your resume");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String path = fileToSave.getAbsolutePath() + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(path));
            document.open();

            // Add Image
            if (!imagePath.isEmpty()) {
                Image img = Image.getInstance(imagePath);
                img.setAbsolutePosition(450f, 750f);
                img.scaleAbsolute(80f, 80f);
                document.add(img);
            }

            // Add Name
            document.add(new Paragraph(name, FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(" "));

            // Add Contact Info
            document.add(new Paragraph("Contact Information", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Email: " + email, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Phone: " + contact, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Address: " + address, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(" "));

            // Add Skills
            document.add(new Paragraph("Skills", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.DARK_GRAY)));
            PdfPTable skillTable = new PdfPTable(2);
            skillTable.addCell(skill1);
            skillTable.addCell(skill2);
            skillTable.addCell(skill3);
            skillTable.addCell(skill4);
            document.add(skillTable);
            document.add(new Paragraph(" "));

            // Add Qualifications
            document.add(new Paragraph("Qualifications", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(college, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(qualiA, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(qualiB, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(" "));

            // Add Work Experience
            document.add(new Paragraph("Work Experience", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(workExperience, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));
            document.add(new Paragraph(" "));

            // Add References
            document.add(new Paragraph("References", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.DARK_GRAY)));
            document.add(new Paragraph("Available upon request.", FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.DARK_GRAY)));

            document.close();
            JOptionPane.showMessageDialog(null, "Resume saved to " + path);
        }
    }
}
