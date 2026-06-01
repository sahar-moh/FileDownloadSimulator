package filedownloadsimulator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
public class DownloadController {
    @FXML
    private ProgressBar progressBar1;
    @FXML
    private ProgressBar progressBar2;
    @FXML
    private ProgressBar progressBar3;
    @FXML
    private Label status1;
    @FXML
    private Label status2;
    @FXML
    private Label status3;
    @FXML
    private Button startButton;
    @FXML
    private void startDownloads() {
        resetAll();
        startButton.setDisable(true);
        Thread t1 = new Thread(
                new DownloadTask(progressBar1, status1));
        Thread t2 = new Thread(
                new DownloadTask(progressBar2, status2));
        Thread t3 = new Thread(
                new DownloadTask(progressBar3, status3));
        t1.start();
        t2.start();
        t3.start();
        new Thread(() -> {
            try {
                t1.join();
                t2.join();
                t3.join();
            } catch (InterruptedException e) {
            }
            javafx.application.Platform.runLater(() -> {
                startButton.setDisable(false);
                startButton.setText("Start Again");
            });
        }).start();
    }
    private void resetAll() {
        progressBar1.setProgress(0);
        progressBar2.setProgress(0);
        progressBar3.setProgress(0);
        status1.setText("Waiting");
        status2.setText("Waiting");
        status3.setText("Waiting");
    }
}