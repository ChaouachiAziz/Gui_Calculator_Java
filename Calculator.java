import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// the imported libraries are necessary for the code to work;
public class Calculator implements ActionListener{

    JFrame frame; //frame declaration
    JTextField text; //text field declaration
    JButton[] nbrButtons = new JButton[10]; // declaring an array of buttons which will contain the numbers
    JButton[] fctButtons = new JButton[8]; // declaring an array of buttons which will contain the function button (+,-,/,x).

    // we'll keep the numbers button anonymous as they will be declared later on , but we'll declare the functions button below:
    JButton plusButton,xButton,divButton,minusButton;
    JButton decButton,equButton,delButton,clcButton;
    JPanel panel; // Panel declaration;
    Font font = new Font("sans serif",Font.BOLD,30); // notice this line is optional , it's just if you'd like to customize the fonts.
    double first,second,result; // declaring the numbers which we will use during the operations.
    char operator;
    public Calculator(){ // constructor in which we will initialize our program.
        frame = new JFrame("Calulator"); //naming our frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to make the app exit when we're exiting the program;
        frame.setSize(550,550);// to set the size of our window;
        frame.setLayout(null);// we want to move our frame freely and not set it in an absolute position.
        text = new JTextField(); // initiate our text field
        text.setBounds(70,50,400,100);// set the bounds for the text field , the values are customizable to suit your need.
        text.setFont(font);// notice that we declared a Font object above now we'll use it to change the font of the text;
        text.setEditable(false);// notice that if we run the app without this command the user can write whatever he wants in the texfield and that's not our goal so we set the editable method to false to prevent any changes.
        frame.add(text);// adding the text to be displayed in the frame;
        frame.setVisible(true);// to make our frame visible when running
        // now the button initialization:
        plusButton = new JButton("+");
        xButton = new JButton("x");
        divButton = new JButton("/");
        minusButton = new JButton("-");
        decButton = new JButton(".");
        delButton = new JButton("Del");
        clcButton = new JButton("Clc");
        equButton = new JButton("=");

        fctButtons[0]=plusButton;
        fctButtons[1]=minusButton;
        fctButtons[2]=xButton;
        fctButtons[3]=divButton;
        fctButtons[4]=delButton;
        fctButtons[5]=decButton;
        fctButtons[6]=equButton;
        fctButtons[7]=clcButton;

        // we iterate through the function buttons list and add each one.
        for (int i=0;i<8;i++){
            fctButtons[i].addActionListener(this);
            fctButtons[i].setFont(font);
            fctButtons[i].setFocusable(false);
        }
        // we iterate through the number buttons list to add each number individually
        for (int i=0;i<10;i++){
            nbrButtons[i] = new JButton(String.valueOf(i)); // we add each number according to the value of i;
            nbrButtons[i].addActionListener(this);
            nbrButtons[i].setFont(font);
            nbrButtons[i].setFocusable(false);
        }
        delButton.setBounds(100,400,150,50);
        clcButton.setBounds(250,400,150,50);

        panel=new JPanel();
        panel.setBounds(100,100,300,300);
        GridLayout grd=new GridLayout(4,4,10,10);
        panel.setLayout(grd);
        panel.setBackground(Color.GRAY);

        panel.add(nbrButtons[1]);
        panel.add(nbrButtons[2]);
        panel.add(nbrButtons[3]);
        panel.add(plusButton);
        panel.add(nbrButtons[4]);
        panel.add(nbrButtons[5]);
        panel.add(nbrButtons[6]);
        panel.add(minusButton);
        panel.add(nbrButtons[7]);
        panel.add(nbrButtons[8]);
        panel.add(nbrButtons[9]);
        panel.add(xButton);
        panel.add(decButton);
        panel.add(nbrButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clcButton);
        frame.add(text);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Calculator cal = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<10; i++){
            if(e.getSource() == nbrButtons[i]) {
                text.setText(text.getText().concat(String.valueOf(i)));
            }
        }

        if(e.getSource() == decButton) {
            // Check if there is already a decimal point in the text
            if (!text.getText().contains(".")) {
                text.setText(text.getText().concat("."));
            }
        }

        if(e.getSource() == plusButton || e.getSource() == minusButton ||
                e.getSource() == xButton || e.getSource() == divButton) {
            try {
                first = Double.parseDouble(text.getText());
            } catch (NumberFormatException ex) {
                // Handle the exception, e.g., show an error message
                text.setText("Error");
                return;
            }
            operator = e.getActionCommand().charAt(0);
            text.setText("");
        }

        if(e.getSource() == equButton) {
            try {
                second = Double.parseDouble(text.getText());
            } catch (NumberFormatException ex) {
                // Handle the exception, e.g., show an error message
                text.setText("Error");
                return;
            }
            text.setText("");
            switch(operator) {
                case '+':
                    result = first + second;
                    break;
                case '-':
                    result = first - second;
                    break;
                case '/':
                    if (second != 0) {
                        result = first / second;
                    } else {
                        // Handle division by zero, e.g., show an error message
                        text.setText("Error");
                        return;
                    }
                    break;
                case 'x':
                    result = first * second;
                    break;
            }
            text.setText(String.valueOf(result));
            first = result;
        }
        if(e.getSource()==clcButton){
            text.setText("");
        }
        if(e.getSource()==delButton){
            String str = text.getText();
            text.setText("");
            for(int i=0;i<str.length()-1;i++){
                text.setText(text.getText()+str.charAt(i));
            }
        }
    }

}
