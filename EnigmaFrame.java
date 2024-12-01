import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnigmaFrame extends JFrame {
    private JComboBox<Integer> innerRotor;
    private JComboBox<Integer> middleRotor;
    private JComboBox<Integer> outerRotor;
    private JTextField startPos;
    private JTextArea input;
    private JTextArea output; 
    private JButton encrypt;
    private JButton decrypt;
    private Enigma enigmaMachine;

    public EnigmaFrame() {
        super("Enigma GUI");
        setSize(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        enigmaMachine = new Enigma(1, 1, 1, "AAA");
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel textPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        
        controlPanel.add(new JLabel("Inner"));
        innerRotor = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        controlPanel.add(innerRotor);
        
        controlPanel.add(new JLabel("Middle"));
        middleRotor = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        controlPanel.add(middleRotor);
        
        controlPanel.add(new JLabel("Out"));
        outerRotor = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        controlPanel.add(outerRotor);
        
        controlPanel.add(new JLabel("Initial Positions"));
        startPos = new JTextField(5);
        startPos.setText("AAA");
        controlPanel.add(startPos);
        
        encrypt = new JButton("Encrypt");
        decrypt = new JButton("Decrypt");
        controlPanel.add(encrypt);
        controlPanel.add(decrypt);
        
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Input:"), BorderLayout.NORTH);
        input = new JTextArea(3, 40);
        inputPanel.add(new JScrollPane(input), BorderLayout.CENTER);
        
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JLabel("Output:"), BorderLayout.NORTH);
        output = new JTextArea(3, 40);
        output.setEditable(false);
        outputPanel.add(new JScrollPane(output), BorderLayout.CENTER);
        
        textPanel.add(inputPanel);
        textPanel.add(outputPanel);
        
        mainPanel.add(controlPanel, BorderLayout.NORTH);
        mainPanel.add(textPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        
        encrypt.addActionListener(e -> doEncryption());
        decrypt.addActionListener(e -> doDecryption());
    }
    
    private void doEncryption() {
        try {
            int inner = (Integer) innerRotor.getSelectedItem();
            int middle = (Integer) middleRotor.getSelectedItem();
            int outer = (Integer) outerRotor.getSelectedItem();
            String pos = startPos.getText().toUpperCase().trim();
            
            if(pos.length() != 3) {
                JOptionPane.showMessageDialog(null, "the initial position requires 3 characters");
                return;
            }
            
            if (!pos.matches("[A-Z]{3}")) {
                JOptionPane.showMessageDialog(null, "the initial position must be 3 letters");
                return;
            }
            
            enigmaMachine = new Enigma(inner, middle, outer, pos);
            String result = enigmaMachine.encrypt(input.getText().toUpperCase());
            
            output.setText(result);
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "idk what happened man. this is what the error says: " + e.getMessage());
        }
    }
    
    private void doDecryption() {
        try {
            int inner = (Integer) innerRotor.getSelectedItem();
            int middle = (Integer) middleRotor.getSelectedItem();
            int outer = (Integer) outerRotor.getSelectedItem();
            String pos = startPos.getText().toUpperCase().trim();
            
            if(pos.length() != 3) {
                JOptionPane.showMessageDialog(null, "the initial position requires 3 characters");
                return;
            }
            
            if (!pos.matches("[A-Z]{3}")) {
                JOptionPane.showMessageDialog(null, "the initial position must be 3 letters");
                return;
            }
            
            enigmaMachine = new Enigma(inner, middle, outer, pos);
            String result = enigmaMachine.decrypt(input.getText().toUpperCase());
            
            output.setText(result);
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "idk what happened man. this is what the error says: " + e.getMessage());
        }
    }
}
