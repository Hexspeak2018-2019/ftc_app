package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "Outreach", group = "Linear")

public class OutreachProgramFinal extends LinearOpMode {

    //HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;

    @Override
    public void runOpMode() throws InterruptedException {


        //robot.init(hardwareMap, telemetry);

        leftFrontMotor = hardwareMap.get(DcMotor.class, "left_FrontMotor");
        leftRearMotor = hardwareMap.get(DcMotor.class, "left_RearMotor");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "right_FrontMotor");
        rightRearMotor = hardwareMap.get(DcMotor.class, "right_RearMotor");

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();


        while (opModeIsActive()) {


            double drivePower = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 2;
            double rotPwr = gamepad1.right_stick_x * 0.7;

            tankDrive(drivePower, robotAngle, rotPwr);


        }
    }

    public void tankDrive(double drivePower, double robotAngle, double rotPwr) {

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = (drivePower * Math.sin(robotAngle + Math.PI / 4) + rotPwr);
        wheelSpeeds[1] = -(drivePower * Math.cos(robotAngle + Math.PI / 4) - rotPwr);
        wheelSpeeds[2] = (drivePower * Math.cos(robotAngle + Math.PI / 4) + rotPwr);
        wheelSpeeds[3] = -(drivePower * Math.sin(robotAngle + Math.PI / 4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(scaleInput(wheelSpeeds[0]));
        rightFrontMotor.setPower(scaleInput(wheelSpeeds[1]));
        leftRearMotor.setPower(scaleInput(wheelSpeeds[2]));
        rightRearMotor.setPower(scaleInput(wheelSpeeds[3]));

    }

    double scaleInput(double dVal) {
        double[] scaleArray = {0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.40, 0.50, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00, 1.00};

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }

    public void normalize(double[] wheelSpeeds)

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
}






