package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "TeleopArmRESET", group = "Linear")

public class ArmResetTest extends LinearOpMode {

    HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();


    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap, telemetry);

        waitForStart();


        while (opModeIsActive()) {


            //Tank Drive with Joystick


            if(gamepad1.dpad_up)
            {

                robot.ArmMotor.setPower(-1);
            } else if(gamepad1.dpad_down){

                robot.ArmMotor.setPower(1);
            } else {

                robot.ArmMotor.setPower(0);
            }
            telemetry.addData("ARM  stopped at ", (robot.ArmMotor.getCurrentPosition()));
            telemetry.update();
            /*if (gamepad1.dpad_up){
                robot.ArmMotor.setTargetPosition(robot.ArmMotor.getCurrentPosition() + 200);

                robot.ArmMotor.setPower(1);
            }
            else if (gamepad1.dpad_down) {
                robot.ArmMotor.setTargetPosition(robot.ArmMotor.getCurrentPosition() - 200);
                robot.ArmMotor.setPower(-1);
            }

             else {
                robot.ArmMotor.setPower(0);

            }*/
            robot.LinkMotor.setPower(gamepad1.left_stick_y);



            // robot.BucketServo.setPosition(Range.clip(bucketPosition, robot.BucketHomePosition, bucketMaxPosition));


            //Arm drive*/




        }
    }

}






