
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//@Disabled
@Autonomous(name = "DepotArmLaunch2.0Slow", group = "Linear")

public class RevisedDepot extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    GoldDetection detector = new GoldDetection();

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap, telemetry);
        detector.activateTF(hardwareMap);
        sleep(700);
        telemetry.addData("Camera is :", "Activated, Ready to Start Recognition");
        telemetry.update();



        waitForStart();

        while (opModeIsActive()) {
            int position = detector.detectObject2(telemetry);
            detector.shutdownTF();
            telemetry.addData("Gold Mineral Position is", position);
            telemetry.update();

            robot.leadScrewUp(robot.lsDistance, robot.lsPower, robot.lsTimeout, this);

            switch (position) {
                case 0:
                    telemetry.addData("Gold Mineral Position 0", "Left");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 155, 110, 10, this);
                    robot.tankRotate(125,this);//-125 angle rotate
                    robot.tankDrivecs(.4, 90,40,10,this);

                    sleep(2000);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(.5,90,60,10,this);
                    robot.BucketServo.setPosition(.75);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);

                    break;

                case 1:
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(.5, 180, 128, 10, this);
                    robot.tankRotate(-135,this);//-45 was 315
                    robot.tankDrivecs(.4, 0,5,10,this);
                    //robot.tankDrivecs(.4, 0,5,10,this);

                    //sleep(2000);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,270,76,20,this);
                    robot.BucketServo.setPosition(.75);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    break;


                case 2:
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(.5, 215, 88, 20, this);
                    robot.tankRotate(-35,this);
                    robot.tankDrivecs(.5, 90, 83, 10, this);
                    robot.tankRotate(-140,this);
                    robot.tankDrivecs(.4, 90,40,10,this);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,92,76,20,this);
                    robot.BucketServo.setPosition(.75);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    //robot.tankDrivecs(1, 272, 76, 20, this);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();

                    break;
                case -1: // center / unknown
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(.5, 180, 128, 10, this);
                    robot.tankRotate(45,this);//-45
                    robot.tankDrivecs(.4, 90,40,10,this);


                    sleep(2000);
                    robot.BucketMotor.setPower(-1);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,0,76,20,this);
                    robot.BucketServo.setPosition(.75);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    break;

            }
            break;


        }
    }

}



