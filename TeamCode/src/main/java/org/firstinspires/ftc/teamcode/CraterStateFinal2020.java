package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Crater", group = "Linear")

public class CraterStateFinal2020 extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        sleep(700);
        telemetry.addData("Camera is :", "Activated, Ready to Start Recognition");
        telemetry.update();
        /*int position = detector.detectObject2(telemetry);
        telemetry.addData("Gold Mineral Position is", position);
        telemetry.update();*/
        // detector.shutdownTF();*/
        waitForStart();
        int encoderValue = 2500;
        double arm_motor_power= 0.5;

        //robot.BucketServo.setPosition(0.48);


        while (opModeIsActive()) {

            int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();

           /* int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();*/

            //telemetry.addData("# Object Detected", detector.updatedRecognitions.size());

            robot.leadScrewUp(robot.lsDistance, robot.lsPower, robot.lsTimeout, this);



            switch (position) {//it works DONT CHANGE time: 18 sec left Not COMPLETE
                case 0: // if ( position == 0) /left
                    //if unknown, assume center and continue
                    //robot.tankDrive2(1, 180, 0, 3, this);
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    //robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 150, 32, 20, this);
                    robot.tankDrivecs(1, 350, 9, 10, this);
                    robot.tankDrivecs(1, 90, 35, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 90, 45, 10, this);
                    //robot.BucketServo.setPosition(0.52);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    //(2000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1, 264, 70, 10, this);

                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(500000000);

                    //robot.tankRotate(45,this);

                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 163, 60, 20, this);
                    robot.tankDrivecs(.5, 350, 9, 10, this);
                    robot.tankDrivecs(.5, 90, 100, 10, this);
                    robot.tankDrivecs(.5, 45, 90, 10, this);*/

                   /* robot.tankDrive(.5, 0, 0, .9, this);
                    robot.tankDrive(.5, 90, 0, 6.9, this);
                    robot.tankDrive(1, 45, 0, 1.2, this);
                    sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/
                    break;

                case 1: //center//it works DONT CHANGE time: 15 sec COMPLETE
                    telemetry.addData("Gold Mineral Position 1", "Missing Center");
                    telemetry.update();
                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 180, 47, 20, this);
                    robot.tankDrivecs(.5, 0, 8, 10, this);
                    robot.tankDrivecs(.5, 90, 110, 10, this);
                    robot.tankDrivecs(.5, 45,90, 10, this);*/
                    // robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 190, 25, 20, this);
                    robot.tankDrivecs(1, 0, 9, 10, this);
                    robot.tankDrivecs(1, 90, 60, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 88, 40, 10, this);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1, 264, 70, 10, this);//80

                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);




                    //robot.tankRotate(45,this);


                    /*sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/

                    break;
                case 2:
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();//right//it works DONT CHANGE time: 15 sec with out leadscrew COMPLETE
                    //robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 217, 30, 20, this);
                    robot.tankDrivecs(1, 340, 10, 10, this);
                    robot.tankDrivecs(1, 90, 82, 10, this);
                    robot.tankRotate(42,this);
                    robot.tankDrivecs(1, 90, 42, 10, this);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    //robot.ArmMotor.setPower(.2);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1, 264, 65, 10, this);
                    robot.BucketServo.setPosition(.75);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    //robot.ArmMotor.setPower(0);

                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 215, 74, 20, this);
                    robot.tankDrivecs(.5, 340, 15, 10, this);
                    robot.tankDrivecs(.5, 90, 125, 10, this);
                    robot.tankDrivecs(.5, 45, 108, 10, this);*/
                    /*robot.tankDrive(.5, 90, 0, 2.43, this);
                    robot.tankDrive(1, 45, 0, 1.3, this);*/
                    //sleep(500);
                    //robot.TeamMarker.setPosition(-100);
                    //sleep(500);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();
                    //robot.tankDrive2(1, 45, 0, 3, this);
                    break;
                case -1: // center / unknown//it works DONT CHANGE
                    telemetry.addData("Gold Mineral Position 1", "Missing Center");
                    telemetry.update();
                    /*robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 180, 47, 20, this);
                    robot.tankDrivecs(.5, 0, 8, 10, this);
                    robot.tankDrivecs(.5, 90, 110, 10, this);
                    robot.tankDrivecs(.5, 45,90, 10, this);*/
                    // robot.leadScrewUp(36,1,18,this);
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 190, 25, 20, this);
                    robot.tankDrivecs(1, 0, 9, 10, this);
                    robot.tankDrivecs(1, 90, 60, 10, this);
                    robot.tankRotate(38,this); //42
                    robot.tankDrivecs(1, 88, 40, 10, this);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1, 264, 65, 10, this);
                    //slow speed
                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);





                    //robot.tankRotate(45,this);


                    /*sleep(500);
                    robot.TeamMarker.setPosition(-100);
                    sleep(500);
                    robot.tankDrive(1, 225, 0, 2.5, this);*/

                    break;
            }
            break;


        }
    }
}