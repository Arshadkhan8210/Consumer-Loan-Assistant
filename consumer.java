import javax.swing.*;            //for using swing GUI
import java.awt.*;               // Contains all of the classes for creating user interfaces and for painting graphics and images.
import java.awt.event.ActionEvent;            //Provides interfaces and classes for dealing with different types of events fired by AWT components.
import java.awt.event.ActionListener;
import java.text.*;
import java.text.DecimalFormat;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class consumer extends JFrame {
    JLabel balanceLabel = new JLabel();
    JTextField balanceTextField = new JTextField();
    JLabel interestLabel = new JLabel();
    JTextField interestTextField = new JTextField();
    JLabel monthsLabel = new JLabel();
    JTextField monthsTextField = new JTextField();
    JLabel paymentLabel = new JLabel();
    JTextField paymentTextField = new JTextField();
    JButton computeButton = new JButton();
    JButton newLoanButton = new JButton();
    JButton monthsButton = new JButton();
    JButton paymentButton = new JButton();
    JLabel analysisLabel = new JLabel();
    JTextArea analysisTextArea = new JTextArea();
    JButton exitButton = new JButton();
    boolean computePayment;
    JFrame frame = new JFrame();
    public static void main(String[] args) {
        new consumer().show();

    }

    public consumer()                                            //Constructor
    {

        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocation(500, 200);                           //both setSize and Location can be done in one line
        frame.setSize(600, 400);                          //frame.setBounds(500,200,600,500);
        frame.setTitle("Consumer Loan Assistant ");
        frame.setResizable(false);                                    // for not resizing Jpanel
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);                //to terminate the window
        ImageIcon icon = new ImageIcon("C:\\Users\\asadk\\IdeaProjects\\ConsumerLoanApplication\\src\\kisspng-portable-network-graphics-image-loan-file-format-c-earn-png-transparent-image-png-mart-5b81a2a565b007.9238373215352224374165.png");
        frame.setIconImage(icon.getImage());

        Container c = frame.getContentPane();

        balanceLabel.setText("Loan Balance");
        c.add(balanceLabel);
        balanceLabel.setBounds(40, 30, 100, 20);
        c.add(balanceTextField);
        balanceTextField.setBounds(160, 30, 100, 20);
        balanceTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                balanceTextFieldActionPerformed(e);
            }
        });


        interestLabel.setText("Interest Rate");
        c.add(interestLabel);
        interestLabel.setBounds(40, 80, 100, 20);
        c.add(interestTextField);
        interestTextField.setBounds(160, 80, 100, 20);
        interestTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                interestTextFieldActionPerformed(e);
            }
        });

        monthsLabel.setText("Number Of Payments");
        c.add(monthsLabel);
        monthsLabel.setBounds(40, 130, 130, 20);
        c.add(monthsTextField);
        monthsTextField.setBounds(160, 130, 100, 20);
        monthsTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                monthsTextFieldActionPerformed(e);
            }
        });

        paymentLabel.setText("Monthly Payment");
        c.add(paymentLabel);
        paymentLabel.setBounds(40, 180, 130, 20);
        c.add(paymentTextField);
        paymentTextField.setBounds(160, 180, 100, 20);
        paymentTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paymentTextFieldActionPerformed(e);
            }
        });

        computeButton.setText("Compute Monthly Payment");
        c.add(computeButton);
        computeButton.setBounds(80, 240, 210, 30);
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeButtonActionPerformed(e);
            }
        });

        newLoanButton.setText("New Loan Analysis");
        c.add(newLoanButton);
        newLoanButton.setEnabled(false);
        newLoanButton.setBounds(90, 290, 180, 30);
        newLoanButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                     newLoanButtonActionPerformed(e);
           }
        });

        monthsButton.setText("x");
        c.add(monthsButton);
        monthsButton.setFocusable(false);
        monthsButton.setBounds(270, 130, 40, 30);
        monthsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                monthsButtonActionPerformed(e);
            }
        });
        paymentButton.setText("x");
        c.add(paymentButton);
        paymentButton.setBounds(270, 175, 40, 30);
        paymentButton.setFocusable(false);
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paymentButtonActionPerformed(e);
            }
        });
        analysisLabel.setText("Loan Analysis");
        c.add(analysisLabel);
        analysisLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        analysisLabel.setBounds(383, 26, 130, 25);
        c.add(analysisTextArea);
        analysisTextArea.setBounds(320, 50, 250, 200);
        analysisTextArea.setBorder(BorderFactory.createLineBorder(Color.black));
        analysisTextArea.setEditable(true);
        analysisTextArea.setBackground(Color.gray);
        analysisTextArea.setForeground(Color.black);
        analysisTextArea.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        analysisTextArea.setFocusable(false);

        exitButton.setText("Exit");
        c.add(exitButton);
        exitButton.setBounds(400, 270, 70, 25);
        exitButton.setFocusable(false);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                exitButtonActionPerformed(e);
            }

        });
    }
    private void balanceTextFieldActionPerformed(ActionEvent e) { balanceTextField.transferFocus(); }
    private void interestTextFieldActionPerformed(ActionEvent e) { interestTextField.transferFocus(); }
    private void monthsTextFieldActionPerformed(ActionEvent e) { monthsTextField.transferFocus(); }
    private void paymentTextFieldActionPerformed(ActionEvent e) { paymentTextField.transferFocus(); }



    private void computeButtonActionPerformed(ActionEvent e)
    {
        double balance, interest, payment;
        int months;
        double monthlyInterest= 0.0, multiplier=1.0;
        double loanBalance, finalPayment;
        if (validateDecimalNumber(balanceTextField))
        {
            balance =
                    Double.valueOf(balanceTextField.getText()).doubleValue();
        }
        else
        {
            JOptionPane.showConfirmDialog(null, "Invalid or empty Loan Balance entry.\nPlease correct.", "Balance Input Error", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (validateDecimalNumber(interestTextField))
        {
            interest =
                    Double.valueOf(interestTextField.getText()).doubleValue();
        }
        else
        {
            JOptionPane.showConfirmDialog(null, "Invalid or empty Interest Rate entry.\nPlease correct.", "Interest Input Error", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        monthlyInterest = interest / 1200;
        if (computePayment)
        {
// Compute loan payment
            if (validateDecimalNumber(monthsTextField))
            {
                months =
                        Integer.valueOf(monthsTextField.getText()).intValue();
            }
            else
            {
                JOptionPane.showConfirmDialog(null, "Invalid or empty Number of Payments entry.\nPlease correct.", "Number of Payments Input Error",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (interest == 0)
            {
                payment = balance / months;
            }
            else
            {
                multiplier = Math.pow(1 + monthlyInterest, months);
                payment = balance * monthlyInterest * multiplier / (multiplier - 1);
            }
            paymentTextField.setText(new DecimalFormat("0.00").format(payment));
        }
        else
        {
// Compute number of payments
            if (validateDecimalNumber(paymentTextField))
            {
                payment =
                        Double.valueOf(paymentTextField.getText()).doubleValue();
                if (payment <= (balance * monthlyInterest + 1.0))
                {
                    if (JOptionPane.showConfirmDialog(null, "Minimum payment must be $" +
                                    new DecimalFormat("0.00").format((int)(balance * monthlyInterest + 1.0)) + "\n" + "Do you want to use the minimum payment?", "Input Error", JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    {
                        paymentTextField.setText(new DecimalFormat("0.00").format((int)(balance *
                                monthlyInterest + 1.0)));
                        payment =
                                Double.valueOf(paymentTextField.getText()).doubleValue();
                    }
                    else
                    {
                        paymentTextField.requestFocus();
                        return;
                    }
                }
            }
            else
            {
                JOptionPane.showConfirmDialog(null, "Invalid or empty Monthly Payment entry.\nPlease correct.", "Payment Input Error", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (interest == 0)
            {
                months = (int)(balance / payment);
            }
            else
            {
                months = (int)((Math.log(payment) - Math.log(payment - balance * monthlyInterest)) /
                        Math.log(1 + monthlyInterest));
            }
            monthsTextField.setText(String.valueOf(months));
        }
// reset payment prior to analysis to fix at two decimals
        payment = Double.valueOf(paymentTextField.getText()).doubleValue();
// show analysis
        analysisTextArea.setText("Loan Balance: $" + new
                DecimalFormat("0.00").format(balance));
        analysisTextArea.append("\n" + "Interest Rate: " + new
                DecimalFormat("0.00").format(interest) + "%");
// process all but last payment
        loanBalance = balance;
        for (int paymentNumber = 1; paymentNumber <= months - 1; paymentNumber++)
        {
            loanBalance += loanBalance * monthlyInterest - payment;
        }
// find final payment
        finalPayment = loanBalance;
        if (finalPayment > payment)
        {
// apply one more payment
            loanBalance += loanBalance * monthlyInterest - payment;
            finalPayment = loanBalance;
            months++;
            monthsTextField.setText(String.valueOf(months));
        }
        analysisTextArea.append("\n\n" + (months - 1) + " Payments of $" + new
                DecimalFormat("0.00").format(payment));
        analysisTextArea.append("\n\n" + "Final Payment of: $" + new
                DecimalFormat("0.00").format(finalPayment));
        analysisTextArea.append("\n\n" + "Total Payments: $" + new
                DecimalFormat("0.00").format((months - 1) * payment + finalPayment));
        analysisTextArea.append("\n\n" + "Interest Paid $" + new

                DecimalFormat("0.00").format((months - 1) * payment + finalPayment - balance));
        computeButton.setEnabled(false);
        newLoanButton.setEnabled(true);
        newLoanButton.requestFocus();
    }

    private void monthsButtonActionPerformed(ActionEvent e)
    {
        computePayment = false;
        paymentButton.setVisible(true);
        monthsButton.setVisible(false);
        monthsTextField.setText("");
        monthsTextField.setEditable(false);
        monthsTextField.setBackground(Color.cyan);
        monthsTextField.setFocusable(false);
        paymentTextField.setEditable(true);
        paymentTextField.setBackground(Color.WHITE);
        paymentTextField.setFocusable(true);

        computeButton.setText("Compute Number of Payments");
        balanceTextField.requestFocus();
    }

    private void paymentButtonActionPerformed(ActionEvent e)
    {
        // will compute payment
        computePayment = true;
        paymentButton.setVisible(false);
        monthsButton.setVisible(true);
        monthsTextField.setEditable(true);
        monthsTextField.setBackground(Color.WHITE);
        monthsTextField.setFocusable(true);
        paymentTextField.setText("");
        paymentTextField.setEditable(false);
        paymentTextField.setBackground(Color.cyan);
        paymentTextField.setFocusable(false);
        computeButton.setText("Compute Monthly Payment");
        balanceTextField.requestFocus();
    }

    private void newLoanButtonActionPerformed(ActionEvent e)
    {
        int b = JOptionPane.showConfirmDialog(frame, "Are you sure? It will Clear all the Inputs");
        if (b == JOptionPane.YES_OPTION)
        {
            balanceTextField.setText(null);
            interestTextField.setText(null);
            monthsTextField.setText(null);
            paymentTextField.setText(null);
            analysisTextArea.setText(null);
            monthsTextField.setEditable(true);
            monthsTextField.setFocusable(true);
            paymentTextField.setFocusable(true);
            paymentTextField.setEditable(true);
            monthsTextField.setBackground(Color.white);
            paymentTextField.setBackground(Color.WHITE);
            monthsButton.setVisible(true);
            paymentButton.setVisible(true);
            computeButton.setText("Compute Payments");
            computeButton.setEnabled(true);

        }

    }

    private void exitButtonActionPerformed(ActionEvent e)
    {
        int a = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?");
                if (a == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
    }
    private boolean validateDecimalNumber(JTextField tf)
    {
// checks to see if text field contains
// valid decimal number with only digits and a single decimal point
        String s = tf.getText().trim();
        boolean hasDecimal = false;
        boolean valid = true;
        if (s.length() == 0)
        {
            valid = false;
        }
        else
        {
            for (int i = 0; i < s.length(); i++)
            {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9')
                {
                    continue;
                }
                else if (c == '.' && !hasDecimal)
                {
                    hasDecimal = true;
                }
                else
                {
// invalid character found

                    valid = false;
                }
            }
        }
        tf.setText(s);
        if (!valid)
        {
            tf.requestFocus();
        }
        return (valid);
    }
}


