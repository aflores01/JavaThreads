import javax.swing.*;

public class ThreadRunner extends Thread {

    private Runner runner;
    private JTextArea resultArea;

    public ThreadRunner(Runner runner, JTextArea resultArea) {
        this.runner = runner;
        this.resultArea = resultArea;
    }

    @Override
    public void run() {
        System.out.println(runner.getName().concat(": ").concat(runner.getSpeed().toString()));
        try {
            Thread.sleep(runner.speed * 1000);
            resultArea.setText(resultArea.getText().concat(runner.getName()).concat(" Finish in ").concat(runner.speed.toString()).concat("s \n"));
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("Runner: ".concat(runner.getName()).concat(" Finish"));
    }
}
