package org.deeplearning4j.examples.cv.mnist;

import org.deeplearning4j.datasets.iterator.impl.MnistDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.examples.cv.TestModels.LeNet;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeNetMnist {
    private static final Logger log = LoggerFactory.getLogger(LeNetMnist.class);

    protected static final int height = 28;
    protected static final int width = 28;
    protected static final int channels = 3;

    protected static int numLabels = 10;
    protected static int batchSize = 500;
    protected static int seed = 42;
    protected static int listenerFreq = 10;
    protected static int iterations = 1;
    protected static int epochs = 50;

    public static void main(String[] args) throws Exception {

        log.info("Load data....");
        DataSetIterator mnistTrain = new MnistDataSetIterator(batchSize,true,12345);
        DataSetIterator mnistTest = new MnistDataSetIterator(batchSize,false,12345);

        log.info("Build model....");
        MultiLayerNetwork network = new LeNet(height, width, channels, numLabels, seed, iterations).init();

        log.info("Train model....");
        network.fit(mnistTrain);

        log.info("Evaluate model....");
        Evaluation eval = network.evaluate(mnistTest);
        log.info(eval.stats());

        log.info("****************Example finished********************");
    }
}