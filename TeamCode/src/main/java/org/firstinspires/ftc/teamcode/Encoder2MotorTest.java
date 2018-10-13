package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TeleOp 2 Tutorial", group = "Tutorial")
public class Encoder2MotorTest extends LinearOpMode {
    private DcMotor encoderMotor1;
    private DcMotor encoderMotor2;
    double motorPower = 0.5;
    //double a = 0;
    final static double ANDYMARK_TICKS_PER_REV = 1440;
    final static double STOP_POSITION = ANDYMARK_TICKS_PER_REV / 2;
    //double formula = (ANDYMARK_TICKS_PER_REV/360)*180;
    //double degrees = 0;



    @Override
    public void runOpMode() throws InterruptedException {
        encoderMotor1 = hardwareMap.dcMotor.get("encoderMotor");
        encoderMotor2 = hardwareMap.dcMotor.get("encoderMotor");

        encoderMotor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        encoderMotor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        encoderMotor1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        encoderMotor2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //encoderMotor.goto

        telemetry.addData("Encoder1 Position", "Starting at %7d",
                encoderMotor1.getCurrentPosition());
        telemetry.addData("Encoder2 Position", "Starting at %7d",
                encoderMotor2.getCurrentPosition());
        telemetry.update();


        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_up && (encoderMotor1.getCurrentPosition() < STOP_POSITION)) { //if both current position and the d-pad up is pressed, and less than 180 degrees

                encoderMotor1.setPower(.4);// then power is set to .3
                encoderMotor2.setPower(.4);// then power is set to .3

                telemetry.addData("Encoder1 Position", encoderMotor1.getCurrentPosition()); // updates telemetry to display current postion of motor
                telemetry.addData("Encoder2 Position", encoderMotor2.getCurrentPosition());
                telemetry.update();
            }
            encoderMotor1.setPower(0);// if criteria is not met, then power is set to 0!!!!!
            encoderMotor2.setPower(0);
            if (gamepad1.dpad_down && (encoderMotor1.getCurrentPosition() > 0)){
                encoderMotor1.setDirection(DcMotor.Direction.FORWARD);
                encoderMotor2.setDirection(DcMotor.Direction.FORWARD);
                encoderMotor1.setPower(-.4); // then power is set to .3
                encoderMotor2.setPower(-.4);

                telemetry.addData("Encoder1 Position", encoderMotor1.getCurrentPosition());
                telemetry.addData("Encoder2 Position", encoderMotor2.getCurrentPosition());// updates telemetry to display current postion of motor
                telemetry.update();
            }
            encoderMotor1.setPower(0);
            encoderMotor2.setPower(0);// if criteria is not met, then power is set to 0!!!!!

           /* if (gamepad1.dpad_up && (encoderMotor1.getCurrentPosition()==10))
            {

            }*/
            //if (gamepad1.a) {
            //motorPower = a;
            //}
            idle();
        }
        //  if gamepad is pressed && degrees < 180 then set power .5
        //


    }


   /* public void driveForwardInches(int counts, double power) {

        int tolerance = 20;

//must set direction first
        encoderMotor.setDirection(DcMotor.Direction.FORWARD);

//then set position
        encoderMotor.setTargetPosition(-counts);

//then set the mode
        encoderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//then set the desired power

        encoderMotor.setPower(power);

        telemetry.addData("Current LeftDriveFront Counts:", (encoderMotor.getCurrentPosition()));

        telemetry.addData("LeftDriveFront Power:", encoderMotor.getPower());

        telemetry.addData("LeftDriveFront Target Pos:", encoderMotor.getTargetPosition());

        telemetry.update();

        while (Math.abs(encoderMotor.getTargetPosition() - encoderMotor.getCurrentPosition())
                > tolerance) {


        }
        encoderMotor.setPower(0);

        telemetry.addData("Current LeftDriveFront Counts:", (encoderMotor.getCurrentPosition()));

        telemetry.addData("LeftDriveFront Power:", encoderMotor.getPower());

        telemetry.addData("LeftDriveFront Target Pos:", encoderMotor.getTargetPosition());

        telemetry.update();
    }
}


*/
}