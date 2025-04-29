import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import algorithms.*;

import java.util.ArrayList;
import java.util.List;

public class Simulator extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Simulador: Substituição de Páginas");

        // Inputs
        TextField capacityField = new TextField();
        capacityField.setPromptText("Capacidade da memória");

        TextField refSizeField = new TextField();
        refSizeField.setPromptText("Tamanho da string de referência");

        CheckBox aleatorioCheck = new CheckBox("Inserção aleatória");

        TextArea refInputArea = new TextArea();
        refInputArea.setPromptText("Digite a string de referência (números separados por espaço)");

        Button gerarButton = new Button("Gerar Gráfico");

        // Gráfico
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Algoritmo");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Page Faults");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Page Faults por Algoritmo");

        VBox pageFaultLabelsBox = new VBox(5);
        pageFaultLabelsBox.setPadding(new Insets(10));

        gerarButton.setOnAction(e -> {
            try {
                int capacity = Integer.parseInt(capacityField.getText());
                int refSize = Integer.parseInt(refSizeField.getText());

                List<Integer> ref = new ArrayList<>();

                if (aleatorioCheck.isSelected()) {
                    for (int i = 0; i < refSize; i++) {
                        ref.add((int) (Math.random() * 10));
                    }
                    refInputArea.setText(ref.toString().replaceAll("[\\[\\],]", ""));
                } else {
                    String[] parts = refInputArea.getText().trim().split("\\s+");
                    for (String part : parts) {
                        ref.add(Integer.parseInt(part));
                    }
                }

                FIFO fifo = new FIFO(ref, capacity);
                LRU lru = new LRU(ref, capacity);
                NFU nfu = new NFU(ref, capacity);
                Aging aging = new Aging(ref, capacity);

                barChart.getData().clear();
                XYChart.Series<String, Number> series = new XYChart.Series<>();
                series.setName("Page Faults");

                series.getData().add(new XYChart.Data<>("FIFO", fifo.getPageFaultCounter()));
                series.getData().add(new XYChart.Data<>("LRU", lru.getPageFaultCounter()));
                series.getData().add(new XYChart.Data<>("NFU", nfu.getPageFaultCounter()));
                series.getData().add(new XYChart.Data<>("Aging", aging.getPageFaultCounter()));

                barChart.getData().add(series);

                pageFaultLabelsBox.getChildren().clear();
                pageFaultLabelsBox.getChildren().addAll(
                        new Label("FIFO: " + fifo.getPageFaultCounter() + " page faults"),
                        new Label("LRU: " + lru.getPageFaultCounter() + " page faults"),
                        new Label("NFU: " + nfu.getPageFaultCounter() + " page faults"),
                        new Label("Aging: " + aging.getPageFaultCounter() + " page faults")
                );

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor, insira números válidos.");
                alert.show();
            }
        });

        VBox inputBox = new VBox(10,
                new Label("Capacidade da Memória:"), capacityField,
                new Label("Tamanho da String de Referência:"), refSizeField,
                aleatorioCheck,
                new Label("String de Referência:"), refInputArea,
                gerarButton
        );
        inputBox.setPadding(new Insets(10));

        VBox root = new VBox(15, inputBox, barChart, pageFaultLabelsBox);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
