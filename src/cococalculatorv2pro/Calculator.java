package cococalculatorv2pro;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Maneja una calculadora con numeros complejos
 * @author ivan_
 */

public class Calculator extends JFrame
{
    final JButton[][] buttons = new JButton[4][5];
    protected JTextField display;
    protected String text;
    private int height;
    private int width;
    private String error;
    
    /**
     * Inicializa variables y 
     * la vista
     */
    Calculator()
    {
        width=375;
        height=600;
        initWindow();
        initButtons();
        this.setLayout(null);            
        text = "";
        display.setText(text);   
    }
    
    /**
     * Inicializa la ventana
     */
    private void initWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setTitle("Calculadora imaginaria");
        Color color = new Color(102,153,153,255);
        this.getContentPane().setBackground(color);
        
        display = new JTextField();
        display.setBounds(50, 50, 275, 50);
        Font font = new Font("Arial",Font.PLAIN,35); 
        display.setText("Bienvenido");
        display.setFont(font);
        display.setEditable(false);
        this.add(display);
    }
    
    /**
     * Inicializa la matriz de botones
     */
    private void initButtons()
    {
        String buttonText[][] = {
            {"C", "(", ")","+"},
            {"7","8","9","-"},
            {"4","5","6","*"},
            {"1","2","3","/"},
            {"i","0","c","="}
        };
        int x0 = 50;
        int y0 = 150;
        Font font = new Font("Arial",Font.PLAIN,15); 
        for (int i = 0; i < 4; i++) 
        {
            for (int j = 0; j <5; j++) 
            {
                buttons[i][j] = new JButton(buttonText[j][i]);
                buttons[i][j].setBounds(x0+i*75, y0 + j*75, 50, 50);
                final String x = buttonText[j][i];
                if(!x.equals("C") && !x.equals("c") && !x.equals("="))
                {
                   buttons[i][j].addActionListener(e->
                    {
                        writeText(x);
                    });
                }
                buttons[i][j].setFont(font);
                this.add(buttons[i][j]);
            }
        }
        buttons[0][0].addActionListener(e->
        {
            text = "";
            display.setText(text);
        });
        
        buttons[3][4].addActionListener(e->
        {
            makeOperation();
        });
        
        buttons[2][4].addActionListener(e->
        {
            if(text.length()>0)
            {
            text = text.substring(0, text.length()-1);
            display.setText(text);
            }
        });
    }
    
    /**
     * Realiza la verificación y 
     * operación del string
     */
    private void makeOperation()
    {
        JOptionPane pop = new JOptionPane();
        String regex = "\\(\\-?[0-9]+[+|-]{1}[0-9]+i\\)[\\+|\\-|\\*|\\/]\\(\\-?[0-9]+[+|-]{1}[0-9]+i\\)";
        if(!text.matches(regex))
        {
            pop.showMessageDialog(null, "Error de sintaxis"); 
            return;
        }
        
        String complex1, complex2;
        regex = "\\).?\\(";
        String[] splitted = text.split(regex);
        if(splitted.length!=2)
        {
            error = "Por el momento solo hay soporte de dos parámetros";
            error();
            return;
        }
        
        complex1 = splitted[0];
        complex2 = splitted[1];
        String operation = text.substring(splitted[0].length()+1, splitted[0].length()+2);
        complex1 = complex1.substring(1,complex1.length());
        complex2 = complex2.substring(0,complex2.length()-1);
        
        Complex num1 = getNumber(complex1);
        Complex num2 = getNumber(complex2);
        
        switch(operation)
        {
            case "+":
                display.setText(num1.add(num1, num2).toString());
                break;
            case "-":
                display.setText(num1.subtract(num1,num2).toString());
                break;
            case "*":
                display.setText(num1.multiply(num1, num2).toString());
            case "/":
                display.setText(num1.divide(num1, num2));
                break;
        }
    }

    /**
     * Convierte un string en un numero
     * @param operation string
     * @return Numero complejo
     */
    Complex getNumber(String operation)
    {
        Double sign1 = 1.0;
        Double sign2 = 1.0;
        if(operation.substring(0,1).equals("-"))
        {
            sign1 = -1.0;
            operation = operation.substring(1,operation.length());
        }
        String regex = "[+|-]";
        String[] splitted = operation.split(regex);
        if(splitted.length!=2)
        {
            error = "Ingreso erróneo del número complejo";
            error();
            return new Complex();
        }
        if(operation.substring(splitted[0].length(), splitted[0].length()+1).equals("-"))
        {
            sign2 = -1.0;
        }
        return new Complex(sign1*Integer.parseInt(splitted[0]),sign2*Integer.parseInt(splitted[1].substring(0,splitted[1].length()-1)));   
    }
    
    /**
     * Imprime el error
     */
    private void error()
    {
        JOptionPane pop = new JOptionPane();
        pop.showMessageDialog(null, "Error de sintaxis: "+error);
        display.setText("ERROR");
    }

    /**
     * Listener por defecto 
     * @param button que realizó la acción
     */
    private final void writeText(String button)
    {
        text += button;
        display.setText(text); 
    }
}