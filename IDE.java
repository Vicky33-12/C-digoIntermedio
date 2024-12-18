package Intermedio;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class IDE extends JFrame {

    private JTextArea codigoTxt, tokensTxt, pilaTxt;
    private JButton btnCompilar;

    public IDE() {
        initComponents();
    }
                       
    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Compilador");
        setSize(900, 600);

        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        codigoTxt = new JTextArea();
        tokensTxt = new JTextArea();
        pilaTxt = new JTextArea();
        tokensTxt.setEditable(false);
        pilaTxt.setEditable(false);

        JScrollPane codigoScroll = new JScrollPane(codigoTxt);
        JScrollPane tokensScroll = new JScrollPane(tokensTxt);
        JScrollPane pilaScroll = new JScrollPane(pilaTxt);

        codigoScroll.setBorder(BorderFactory.createTitledBorder("Código"));
        tokensScroll.setBorder(BorderFactory.createTitledBorder("Tokens"));
        pilaScroll.setBorder(BorderFactory.createTitledBorder("Pila"));

        JSplitPane rightSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tokensScroll, pilaScroll);
        rightSplitPane.setResizeWeight(0.5);

        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, codigoScroll, rightSplitPane);
        mainSplitPane.setResizeWeight(0.6);

        mainPanel.add(mainSplitPane, BorderLayout.CENTER);

        btnCompilar = new JButton("Compilar");
        btnCompilar.setPreferredSize(new Dimension(100, 30));
        btnCompilar.addActionListener(e -> btnCompilarActionPerformed());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(btnCompilar);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }                        

    private void btnCompilarActionPerformed() {
        analisisLexico analizador = new analisisLexico();
        analisisSintactico analizadorS = new analisisSintactico();
        
        ArrayList<String> lista = new ArrayList<>(analizador.analizadorLexico(codigoTxt.getText() + "\r\n"));
        
        tokensTxt.setText(String.join("\n", lista));
        
        if (analizador.getError().length() > 0) {
            JOptionPane.showMessageDialog(this, analizador.getError(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            lista.add("$");
            String pila = analizadorS.evaluar(lista.toArray(new String[0]), 
                                              analizador.getCantidadTokensFila(), 
                                              analizador.getValores());
            if (analizadorS.getError().isEmpty()) {
                pilaTxt.setText(pila);
                JOptionPane.showMessageDialog(this, analizadorS.getCodigoIntermedio(), "Aceptada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, analizadorS.getError(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new IDE().setVisible(true));
    }
}

