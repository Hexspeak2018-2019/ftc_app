package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TeleOp 2 Tutorial", group = "Tutorial")
public class Encoder2MotorTest extends LinearOpMode {
    private DcMotor ArmMotor;
    private DcMotor LinkMotor;
    //double motorPower = 0.5;
    //double a = 0;
    final static double ANDYMARK_TICKS_PER_REV = 1440;
    final static double WormGearRatio = 27;
    final static double TickPerDeg = (ANDYMARK_TICKS_PER_REV * WormGearRatio)/360;

    final static double ArmFinalPosition = 200*TickPerDeg;
    final static double ArmLiftPosition = 100*TickPerDeg;
    final static double ArmHomePosition = 0*TickPerDeg;
    final static double LinkFinalPosition = 100*TickPerDeg;
    final static double LinkHomePosition = 0*TickPerDeg;



    @Override
    public void runOpMode() throws InterruptedException {
        ArmMotor = hardwareMap.dcMotor.get("ArmMotor");
        LinkMotor = hardwareMap.dcMotor.get("LinkMotor");

        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinkMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinkMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //encoderMotor.goto

        telemetry.addData("Arm Position", "Starting at %7d",
                ArmMotor.getCurrentPosition());
        telemetry.addData("Link Position", "Starting at %7d",
                LinkMotor.getCurrentPosition());
        telemetry.update();
        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
        LinkMotor.setDirection(DcMotor.Direction.FORWARD);


        waitForStart();

        while (opModeIsActive()) {
            double differenceRange = ArmMotor.getCurrentPosition()-LinkMotor.getCurrentPosition();


            /*if (gamepad1.dpad_up && (ArmMotor.getCurrentPosition() < ArmLiftPosition) && (LinkMotor.getCurrentPosition() < 10))

            {
                *//*armMotorPower = 0.5;

                ArmMotor.setPower(armMotorPower);*//*

            }*/

            if(gamepad1.dpad_up) {
                if ((ArmMotor.getCurrentPosition() < ArmLiftPosition) && (LinkMotor.getCurrentPosition() < 10))

                {

                    ArmMotor.setPower(.5);
                    LinkMotor.setPower(0);

                }
                else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() < LinkFinalPosition))

                {

                    ArmMotor.setPower(.5);
                    LinkMotor.setPower(.4);

                } else if ((ArmMotor.getCurrentPosition() >= ArmFinalPosition) && (LinkMotor.getCurrentPosition() < LinkFinalPosition))

                {

                    ArmMotor.setPower(0);
                    LinkMotor.setPower(.5);

                } else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() >= LinkFinalPosition))

                {

                    ArmMotor.setPower(.5);
                    LinkMotor.setPower(0);

                }
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                }




            }

            else if (gamepad1.dpad_down)
            {
                if (( ArmMotor.getCurrentPosition() > 100) && (LinkMotor.getCurrentPosition() > 50) )
                {
                    ArmMotor.setPower(-.5);
                    LinkMotor.setPower(-.3);
                    telemetry.addData("I am not in less than 20", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }
                else if (LinkMotor.getCurrentPosition() <=20)
                {

                    ArmMotor.setPower(-.2);
                    LinkMotor.setPower(-.2);
                    telemetry.addData("I am in less than 20", (LinkMotor.getCurrentPosition()/TickPerDeg));

                }
                /*else if (LinkMotor.getCurrentPosition() < LinkHomePosition && ArmMotor.getCurrentPosition() < ArmHomePosition)
                {
                    LinkMotor.setPower(.1);
                    ArmMotor.setPower(.1);
                }*/
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                    telemetry.addData("I stopped", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }
            }
            else {
                ArmMotor.setPower(0);
                LinkMotor.setPower(0);
            }
             /*else if (gamepad1.dpad_down && ArmMotor.getCurrentPosition() >100 && LinkMotor.getCurrentPosition() > 50)
            {
                ArmMotor.setPower(-.3);

                LinkMotor.setPower(-.5);
            }
           // else if ()
            else{
                ArmMotor.setPower(0);
                LinkMotor.setPower(0);
            }

            if (gamepad1.dpad_up){
                // if leftmotor > 100
                //
                //
            } else if (gamepad1.dpad_down)
            {
                // if
            }*/
            /*if (gamepad1.dpad_down && (ArmMotor.getCurrentPosition() > 0) &&  (LinkMotor.getCurrentPosition() > 0) && differenceRange < 100.5)

            {

                ArmMotor.setPower(-.3);
                LinkMotor.setPower(-.5);

            }*/



            telemetry.addData("Arm Position", (ArmMotor.getCurrentPosition()/TickPerDeg)); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getCurrentPosition()/TickPerDeg));
            telemetry.addData("Arm Position", (ArmMotor.getPower())); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getPower()));
            telemetry.update();
        }


            // if criteria is not met, then power is set to 0!!!!!



            idle();
        }



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
