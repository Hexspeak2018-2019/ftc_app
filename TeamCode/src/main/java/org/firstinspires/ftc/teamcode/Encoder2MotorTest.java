package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "Encoder", group = "Tutorial")
public class Encoder2MotorTest extends LinearOpMode {
    private DcMotor ArmMotor;
    private DcMotor LinkMotor;
    private DcMotor RotationMotor;
    final static double ANDYMARK_TICKS_PER_REV = 1440;
    final static double WormGearRatio = 9;
    final static double TickPerDeg = (ANDYMARK_TICKS_PER_REV * WormGearRatio)/360;

    final static double ArmFinalPosition = 200*TickPerDeg;
    final static double ArmLiftPosition = 120*TickPerDeg;
    final static double ArmHomePosition = 0;
    final static double LinkFinalPosition = -100*TickPerDeg;
    final static double LinkHomePosition = 0;
    final static double RotationHomePosition = 0*TickPerDeg;
    final static double RotationFinalPosition = 200*TickPerDeg;



    @Override
    public void runOpMode() throws InterruptedException {
        ArmMotor = hardwareMap.dcMotor.get("ArmMotor");
        LinkMotor = hardwareMap.dcMotor.get("LinkMotor");
        RotationMotor = hardwareMap.dcMotor.get("RotatingMotor");
        ArmMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LinkMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        RotationMotor.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        ArmMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        LinkMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        RotationMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Arm Position", "Starting at %7d",
                ArmMotor.getCurrentPosition());
        telemetry.addData("Link Position", "Starting at %7d",
                LinkMotor.getCurrentPosition());
        telemetry.update();
        ArmMotor.setDirection(DcMotor.Direction.REVERSE);
        LinkMotor.setDirection(DcMotor.Direction.FORWARD);
        RotationMotor.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();

        while (opModeIsActive()) {
            double differenceRange = ArmMotor.getCurrentPosition()-LinkMotor.getCurrentPosition();




            if(gamepad1.dpad_up) {
                if ((ArmMotor.getCurrentPosition() < ArmLiftPosition) && (LinkMotor.getCurrentPosition() < 10))

                {

                    ArmMotor.setPower(.8);
                    LinkMotor.setPower(0);

                }
                else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() > LinkFinalPosition))

                {

                    ArmMotor.setPower(.7);
                    LinkMotor.setPower(-.9);

                } else if ((ArmMotor.getCurrentPosition() >= ArmFinalPosition) && (LinkMotor.getCurrentPosition() > LinkFinalPosition))

                {

                    ArmMotor.setPower(0);
                    LinkMotor.setPower(-.8);

                } else if ((ArmMotor.getCurrentPosition() < ArmFinalPosition) && (LinkMotor.getCurrentPosition() <= LinkFinalPosition))

                {

                    ArmMotor.setPower(.8);
                    LinkMotor.setPower(0);

                }
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                }

            }

            else if (gamepad1.dpad_down)
            {
                if (( ArmMotor.getCurrentPosition() > 100) && (LinkMotor.getCurrentPosition() < -50 ))
                {
                    ArmMotor.setPower(-.8);
                    LinkMotor.setPower(.8);
                    telemetry.addData("I am not in less than 20", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }

                else if ( ArmMotor.getCurrentPosition() >= 0 && LinkMotor.getCurrentPosition() >=0)//LinkMotor.getCurrentPosition() <=20 && LinkMotor.getCurrentPosition() <0)
                {

                    ArmMotor.setPower(-.8);
                    LinkMotor.setPower(0);
                    telemetry.addData("I am in less than 20", (LinkMotor.getCurrentPosition() / TickPerDeg));

                }


                else if(ArmMotor.getCurrentPosition() >= 2)
                {

                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                }
                else {
                    ArmMotor.setPower(0);
                    LinkMotor.setPower(0);
                    telemetry.addData("Link  stopped at ", (LinkMotor.getCurrentPosition()/TickPerDeg));
                }

            }
            else {
                ArmMotor.setPower(0);
                LinkMotor.setPower(0);
            }
            if (gamepad1.dpad_right)
            {
                if (RotationMotor.getCurrentPosition() <= RotationFinalPosition)
                {
                    RotationMotor.setPower(.3);
                }
                else {
                    RotationMotor.setPower(0);
                }
            }
            else if (gamepad1.dpad_left)
            {
                if (RotationMotor.getCurrentPosition() >= RotationHomePosition)
                {
                    RotationMotor.setPower(-.3);
                }
                else if (RotationMotor.getCurrentPosition() <= 0)
                {
                    RotationMotor.setPower(0);
                }
                else{
                    RotationMotor.setPower(0);
                }
            }
            else
            {
                RotationMotor.setPower(0);
            }

            telemetry.addData("Arm Position", (ArmMotor.getCurrentPosition()/TickPerDeg)); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getCurrentPosition()/TickPerDeg));
            telemetry.addData("Arm Position", (ArmMotor.getPower())); // updates telemetry to display current postion of motor
            telemetry.addData("Link Position", (LinkMotor.getPower()));
            telemetry.update();
        }

        idle();
    }

}

