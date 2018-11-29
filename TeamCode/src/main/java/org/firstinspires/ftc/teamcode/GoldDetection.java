package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;


/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */


public class GoldDetection {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
   // HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();


     /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY = " ASDLJMf/////AAABmbcG6YCph0gis3gtDkB9Wv0eUPEaiVl3/paQ9Erk9eMoIrafTN8bL7OCdL7/PM5oNpghea9UZWDgNxPt++xcfRivj66lU337bOxv/gLuIW38EwE9/HX6BxNQ9oUqoNWh9UOSJvL4u9yIv9GQX3X5hk9YGwa8YnNZ4ubYk9+S96fPW5CMBidcSIuV1WWw1vZHJQeHcsWr5ROhyyGdNhF3HZ06wNCr0eNJ/qy5A4UM47223Opf9faHPeB1Hj1QCVHAUAEtuZiAfDQbH84FDfluVN/oxjC2Ef6mt+8m/09O9rU2IM9z4CX2pGmt9osiS73sybyviiJKhT3RRt/kM75zBR9giyfRIJap5Iya1qMt3wl9 ";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.\
     *
     * |
     * |*/
    private TFObjectDetector tfod;

   /* @Override
    public void runOpMode() {
        robot.init(hardwareMap, telemetry);
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        *//** Wait for the game to begin *//*
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            *//** Activate Tensor Flow Object Detection. *//*
            activateTF();

            while (opModeIsActive()) {
                int objectPosition = detectObject();
            }
        }

        shutdownTF();
    }

*/

    public GoldDetection()
    {
    }

    public void activateTF(HardwareMap hardwareMap) {
        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initVuforia();
            initTfod(hardwareMap);
        } else {
//            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        if (tfod != null) {
            tfod.activate();
        }
    }

    public void shutdownTF() {
        if (tfod != null) {
            tfod.shutdown();
        }
    }

   public int detectObject(Telemetry telemetry) {
        int position = -1;
       if (tfod != null) {
           // getUpdatedRecognitions() will return null if no new information is available since
           // the last time that call was made.
           List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
           if (updatedRecognitions != null) {
               telemetry.addData("# Object Detected", updatedRecognitions.size());
               if (updatedRecognitions.size() == 3) {
                   int goldMineralX = -1;
                   int silverMineral1X = -1;
                   int silverMineral2X = -1;
                   for (Recognition recognition : updatedRecognitions) {
                       if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                           telemetry.addData("The gold position is ", recognition.getLeft());
                           goldMineralX = (int) recognition.getLeft();
                       } else if (silverMineral1X == -1) {
                           telemetry.addData("The gold position is ", recognition.getLeft());
                           silverMineral1X = (int) recognition.getLeft();
                       } else {
                           telemetry.addData("The gold position is ", recognition.getLeft());
                           silverMineral2X = (int) recognition.getLeft();
                       }
                   }
                   if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                       if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                           position = 0;

                           telemetry.addData("Gold Mineral Position", "Left");

                       } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                           position = 1;
                           telemetry.addData("Gold Mineral Position", "Right");

                       } else {
                           position = 2;
                           telemetry.addData("Gold Mineral Position", "Center");
                       }
                   }
               }
               telemetry.update();
           }
       }
   return position;
}



    public int detectObject2(Telemetry telemetry) {
        int position = -1;
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                    for (Recognition recognition : updatedRecognitions) {
                        if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            double length = recognition.getRight() - recognition.getLeft();
                            double width = recognition.getBottom() - recognition.getTop();

                            if (Math.abs(length - width) < 20) {
                                if (recognition.getTop() > 817.1898) {

                                    telemetry.addData("The gold position is ", "Right");
                                    position = 2;
                                } else if (recognition.getTop() <= 817.1898 && recognition.getTop() >= 411.6451) {

                                    telemetry.addData("The gold position is ", "Center");
                                    position = 1;
                                } else {

                                    telemetry.addData("The gold position is ", "Left");
                                    position = 0;
                                }
                            }
                        }
                    }
                }
                telemetry.update();
            }
        return position;
    }
    public int detectObjectTimeAct(Telemetry telemetry) {
        int position = -1;
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        double length = recognition.getRight() - recognition.getLeft();
                        double width = recognition.getBottom() - recognition.getTop();

                        if (Math.abs(length - width) < 20) {
                            if (recognition.getTop() > 817.1898) {

                                telemetry.addData("The gold position is ", "Right");
                                position = 2;
                            } else if (recognition.getTop() <= 817.1898 && recognition.getTop() >= 411.6451) {

                                telemetry.addData("The gold position is ", "Center");
                                position = 1;
                            } else {

                                telemetry.addData("The gold position is ", "Left");
                                position = 0;
                            }
                        }
                    }
                }
            }
            telemetry.update();
        }
        return position;
    }
    public int detectObjectWithTelemetry(Telemetry telemetry) {
        int position = -1;
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
                int i = 0;
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        telemetry.addData("The gold # ", i);
                         try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ie) {}
                     //  telemetry.addData("angle :", recognition.estimateAngleToObject(AngleUnit.DEGREES));
                     //   telemetry.addData("Confidence :", recognition.getConfidence());
                     //   telemetry.addData("The getRight is", recognition.getRight());
                     //   telemetry.addData("The getLeft is ", recognition.getLeft());
                        telemetry.addData("The getTop is", recognition.getTop());
                     //   telemetry.addData("The getBottom is ", recognition.getBottom());
                        double length = recognition.getRight()-  recognition.getLeft();
                        double width = recognition.getBottom() - recognition.getTop();

                        telemetry.update();
                        i++;

                        if (Math.abs(length-width) < 20) {

                            break;
                        }
                        telemetry.update();

                    }
                }
            }

        }
        return position;
    }





    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod(HardwareMap hardwareMap) {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}

