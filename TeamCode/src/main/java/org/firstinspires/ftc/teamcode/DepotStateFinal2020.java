
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//@Disabled
@Autonomous(name = "Depot", group = "Linear")

public class DepotStateFinal2020 extends LinearOpMode {

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
                    robot.tankDrivecs(1, 155, 48, 10, this);
                    robot.tankRotate(-124,this);//-125 angle rotate
                    robot.tankDrivecs(.4, 90,51,10,this);

                    robot.BucketMotor.setPower(-.6);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,276,65,10,this);
                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);

                    break;

                case 1:
                    telemetry.addData("Gold Mineral Position 1", "Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(1, 180,64,10, this);//128
                    robot.tankRotate(-135,this);//-45 was 315
                    robot.tankDrivecs(.4, 0,14,10,this);
                    robot.tankDrivecs(.4, 270,20,10,this);

                    //sleep(2000);
                    robot.BucketMotor.setPower(-.6);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,270,72,20,this);
                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    break;


                case 2:
                    robot.tankDrivecs(.5, 90, 9, 10, this);
                    robot.tankDrivecs(1, 215, 51, 20, this);
                    robot.tankRotate(-35,this);
                    robot.tankDrivecs(1, 90, 46, 10, this);
                    robot.tankRotate(-95,this);//140
                    //robot.tankDrivecs(.4,0,15,10,this);
                    //robot.tankDrivecs(.4,45,5,10,this);
                    //robot.tankDrivecs(.4, 90,40,10,this);
                    robot.BucketMotor.setPower(-.6);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    //robot.tankDrivecs(.4,270,10,10,this);
                    robot.tankRotate(-136,this);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,268,72,20,this);
                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    //robot.tankDrivecs(1, 272, 76, 20, this);
                    telemetry.addData("Gold Mineral Position 2", "Right");
                    telemetry.update();

                    break;
                case -1: // center / unknown
                    telemetry.addData("Gold Mineral Position 1", "Missing Center");
                    telemetry.update();
                    robot.tankDrivecs(.4, 90, 9,10 , this);
                    robot.tankDrivecs(1, 180,64,10, this);//128
                    robot.tankRotate(-135,this);//-45 was 315
                    robot.tankDrivecs(.4, 0,14,10,this);
                    robot.tankDrivecs(.4, 270,20,10,this);

                    //sleep(2000);
                    robot.BucketMotor.setPower(-.6);
                    sleep(2000);
                    robot.BucketMotor.setPower(0);
                    sleep(1000);
                    robot.ArmMotor.setPower(0.4);
                    robot.tankDrivecs(1,270,72,20,this);
                    robot.BucketServo.setPosition(.4);
                    robot.ArmMotor.setPower(0);
                    sleep(50000000);
                    break;



            }
            break;


        }
    }

}



