import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AthleticRaceInterface extends JFrame {

    JLabel      registerRunner      = new JLabel("New runner");
    JTextField  runnerNameField     = new JTextField();
    JButton     registerButton      = new JButton("Register");

    JLabel      registeredRunners   = new JLabel("Registered runners");
    JTextArea   runnersBox          = new JTextArea();

    JLabel      resultLabel         = new JLabel("Results");
    JTextArea   resultArea          = new JTextArea();
    JButton     startButton         = new JButton("Start");
    JButton     restartButton       = new JButton("Restart");
    JButton     finishButton        = new JButton("Close");

    JButton     ghostButton         = new JButton();

    ArrayList<Runner> runners       = new ArrayList<Runner>(5);

    public AthleticRaceInterface() {
        ListenButton listener = new ListenButton();
        FlatLightLaf.setup(new FlatDarculaLaf());

        setSize(440, 310);
        setTitle("Athletic race");
        setResizable(false);
        setLocationRelativeTo(null);

        registerRunner.setBounds(10, 10, 300, 10);
        runnerNameField.setBounds(10, 30, 300, 20);
        registerButton.setBounds(320, 30, 100, 20);
        registeredRunners.setBounds(10, 60, 410, 20);
        runnersBox.setBounds(10, 80, 410, 80);
        resultLabel.setBounds(10, 170, 300, 10);
        resultArea.setBounds(10, 180, 300, 80);
        startButton.setBounds(320, 180, 100, 20);
        restartButton.setBounds(320, 210, 100, 20);
        finishButton.setBounds(320, 240 , 100, 20);

        registerButton.addActionListener(listener);
        startButton.addActionListener(listener);
        restartButton.addActionListener(listener);
        finishButton.addActionListener(listener);

        ghostButton.setEnabled(false);

        getContentPane().add(registerRunner);
        getContentPane().add(runnerNameField);
        getContentPane().add(registerButton);
        getContentPane().add(registeredRunners);
        getContentPane().add(runnersBox);
        getContentPane().add(resultLabel);
        getContentPane().add(resultArea);
        getContentPane().add(startButton);
        getContentPane().add(restartButton);
        getContentPane().add(finishButton);
        getContentPane().add(ghostButton);

        FlatLightLaf.updateUI();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new AthleticRaceInterface();
    }

    class ListenButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == registerButton) {
                if(runnerNameField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Runner name empty");
                } else {
                    if (runners.size() <= 4) {
                        runners.add(
                                new Runner(runnerNameField.getText(), (long) (Math.random() * 30) + 1L)
                        );
                        runnerNameField.setText(null);
                        runnersList(runners);
                    } else {
                        JOptionPane.showMessageDialog(null, "Only 5 runners allowed");
                    }
                }
            } else if (e.getSource() == startButton) {
                ThreadRunner runner1 = new ThreadRunner(runners.get(0), resultArea);
                ThreadRunner runner2 = new ThreadRunner(runners.get(1), resultArea);
                ThreadRunner runner3 = new ThreadRunner(runners.get(2), resultArea);
                ThreadRunner runner4 = new ThreadRunner(runners.get(3), resultArea);
                ThreadRunner runner5 = new ThreadRunner(runners.get(4), resultArea);
                new Thread(runner1).start();
                new Thread(runner2).start();
                new Thread(runner3).start();
                new Thread(runner4).start();
                new Thread(runner5).start();
            } else if (e.getSource() == restartButton) {
                runners.clear();
                runnersBox.setText(null);
                resultArea.setText(null);
                runnerNameField.setText(null);
            } else if (e.getSource() == finishButton) {
                System.exit(0);
            }
        }
    }

    public void runnersList(ArrayList<Runner> list) {
        runnersBox.setText(null);
        list.forEach(runner -> {
            String previous = runnersBox.getText();
            runnersBox.setText("");
            runnersBox.setText(previous + runner.getName() + "\n");
            System.out.println(runner.getName() + " speed: " + runner.getSpeed());
        });
    }
}
