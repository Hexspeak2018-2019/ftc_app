package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TeleOp Tutorial", group = "Tutorial")
public class EncoderTest extends LinearOpMode {
    private DcMotor encoderMotor;
    double motorPower = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {
        encoderMotor = hardwareMap.dcMotor.get("encoderMotor");

        encoderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


      encoderMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Encoder Position",  "Starting at %7d :%7d",
                encoderMotor.getCurrentPosition(),
                encoderMotor.getCurrentPosition());
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {


  driveForwardInches(500,0.5);

            idle();
        }
    }

    public void driveForwardInches(int counts, double power) {

        int tolerance = 20;

//must set direction first
        encoderMotor.setDirection(DcMotor.Direction.REVERSE);

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
                > tolerance)
        {


            }
            encoderMotor.setPower(0);

            telemetry.addData("Current LeftDriveFront Counts:", (encoderMotor.getCurrentPosition()));

            telemetry.addData("LeftDriveFront Power:", encoderMotor.getPower());

            telemetry.addData("LeftDriveFront Target Pos:", encoderMotor.getTargetPosition());

            telemetry.update();
        }

    }



