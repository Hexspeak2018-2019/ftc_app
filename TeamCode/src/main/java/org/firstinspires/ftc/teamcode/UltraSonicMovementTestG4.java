package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cRangeSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "TestUltraB", group = "MRI")

public class UltraSonicMovementTestG4 extends LinearOpMode {
    ModernRoboticsI2cRangeSensor rangeSensor;
    final static double ANDYMARK_TICKS_PER_REV = 1120;
    final static double WHEEL_DIAMETER = 3.97401575;
    final static double COUNTS_PER_INCH = ANDYMARK_TICKS_PER_REV / (WHEEL_DIAMETER * Math.PI);
    ElapsedTime runtime = new ElapsedTime();

    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;

    @Override
    public void runOpMode() {
        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_FrontMotor");
        leftRearMotor = hardwareMap.get(DcMotor.class, "left_RearMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_FrontMotor");
        rightRearMotor = hardwareMap.get(DcMotor.class, "right_RearMotor");

        // get a reference to our compass
        rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "sensor_range");

        // wait for the start button to be pressed
        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("raw ultrasonic", rangeSensor.rawUltrasonic());
            telemetry.addData("raw optical", rangeSensor.rawOptical());
            telemetry.addData("cm optical", "%.2f cm", rangeSensor.cmOptical());
            telemetry.addData("cm", "%.2f cm", rangeSensor.getDistance(DistanceUnit.CM));
            telemetry.update();

            if (rangeSensor.getDistance(DistanceUnit.CM) > 30 ) {
                telemetry.addData("hi","Hi");
                tankDrivecs(.4, 240, 1, 1, this);
                sleep(100);

            }
            else if (rangeSensor.getDistance(DistanceUnit.CM) < 30) {
                tankDrivecs(0.4, 290, 1, 1, this);
                sleep(100);
            }
            else if( rangeSensor.getDistance(DistanceUnit.CM) == 30)
            {
                tankDrivecs(0.4, 265, 1, 1, this);
                sleep(100);
            } else {}
        }

    }

    public void tankDrivecs (double drivePower, double robotAngle, int inches, double timeout, LinearOpMode aStop)
    {
        double angleInRad = (robotAngle + 180)*(Math.PI/180);
        int counts = (int) Math.round(COUNTS_PER_INCH * inches);


        double wheelSpeeds[] = new double[4];

//must set direction first
        setMotorDirections();
        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4));
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4));
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4));
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4));


        int wheelCounts[]= new int[4];

        wheelCounts[0]  =  (int)(counts* wheelSpeeds[0]);
        wheelCounts[1]  =  (int)(counts*  wheelSpeeds[1]);
        wheelCounts[2]  =  (int)(counts* wheelSpeeds[2]);
        wheelCounts[3]  =  (int)(counts*  wheelSpeeds[3]);

//then set position

        leftFrontMotor.setTargetPosition(wheelCounts[0]);
        rightFrontMotor.setTargetPosition(wheelCounts[1]);
        leftRearMotor.setTargetPosition(wheelCounts[2]);
        rightRearMotor.setTargetPosition(wheelCounts[3]);


//then set the mode
        leftFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        while (leftFrontMotor.isBusy() || rightFrontMotor.isBusy() || leftRearMotor.isBusy() || rightRearMotor.isBusy())  {

            if (runtime.seconds() > timeout || !aStop.opModeIsActive()) {
                break;
            }

            // Display it for the driver.
            telemetry.addData("Left F , Right F",  "Running to %7d :%7d", wheelCounts[0],  wheelCounts[1]);
            telemetry.addData("Left R , Right R",  "Running to %7d :%7d", wheelCounts[2],  wheelCounts[3]);
            telemetry.addData("Left F , Right F",  "Running at %7d :%7d", leftFrontMotor.getCurrentPosition(), rightFrontMotor.getCurrentPosition());
            telemetry.addData("Left R , Right R",  "Running at %7d :%7d", leftRearMotor.getCurrentPosition(), rightRearMotor.getCurrentPosition());
            telemetry.addData("hi", leftFrontMotor.getPower());
            telemetry.update();
        }

        resetMotors();
    }
    public void normalize ( double[] wheelSpeeds)

    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++) {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }
    public void resetMotors() {
        leftRearMotor.setPower(0);
        leftFrontMotor.setPower(0);
        rightRearMotor.setPower(0);
        rightFrontMotor.setPower(0);
    }
    public void setMotorDirections() {

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);


    }
}

