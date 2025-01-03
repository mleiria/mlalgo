package pt.mleiria.mlalgo.regressor.dl;

import junit.framework.TestCase;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.evaluation.regression.RegressionEvaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.util.ArrayList;
import java.util.List;

public class LinearRegressionTest extends TestCase {

    public void testLinearRegression() {


        // Generate synthetic training data
        double[][] input = new double[][]{
                {1.0}, {2.0}, {3.0}, {4.0}, {5.0},
                {6.0}, {7.0}, {8.0}, {9.0}, {10.0}
        };
        double[][] output = new double[][]{
                {2.0}, {4.0}, {6.0}, {8.0}, {10.0},
                {12.0}, {14.0}, {16.0}, {18.0}, {20.0}
        };

        // Create INDArray from input and output data
        INDArray inputNDArray = Nd4j.create(input);
        INDArray outputNDArray = Nd4j.create(output);

        // Create dataset and iterator
        DataSet dataSet = new DataSet(inputNDArray, outputNDArray);
        List<DataSet> listDs = dataSet.asList();
        DataSetIterator iterator = new ListDataSetIterator(listDs, listDs.size());

        // Build linear regression model
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(0.01))
                .list()
                .layer(0, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(1)
                        .nOut(1)
                        .build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        // Train the model
        for (int epoch = 0; epoch < 1000; epoch++) {
            iterator.reset();
            model.fit(iterator);
        }

        // Evaluate the model
        RegressionEvaluation eval = new RegressionEvaluation();
        INDArray predicted = model.output(inputNDArray);
        eval.eval(outputNDArray, predicted);

        System.out.println(eval.stats());
    }
}
