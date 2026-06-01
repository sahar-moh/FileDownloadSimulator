package filedownloadsimulator;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
public class DownloadTask implements Runnable {
    private final ProgressBar progressBar;
    private final Label statusLabel;
    public DownloadTask(ProgressBar progressBar, Label statusLabel) {
        this.progressBar = progressBar;
        this.statusLabel = statusLabel;
    }
    @Override
    public void run() {
        updateUI(0, "Downloading...");
        for (int progress = 0; progress <= 100; progress += 10) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                return;
            }
            final double value = progress / 100.0;
            updateUI(value, progress + "%");
        }
        updateUI(1.0, "Done!");
    }
    private void updateUI(double progress, String status) {
        Platform.runLater(() -> {
            progressBar.setProgress(progress);
            statusLabel.setText(status);
        });
    }
}