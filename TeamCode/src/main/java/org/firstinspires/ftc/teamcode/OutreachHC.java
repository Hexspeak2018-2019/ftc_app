package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class OutreachHC {

    /* Public OpMode members. */

    public DcMotor leftFrontMotor = null;
    public DcMotor rightFrontMotor = null;
    public DcMotor leftRearMotor = null;
    public DcMotor rightRearMotor = null;
    Telemetry localtelemetry;
    HardwareMap hwMap = null;

    //public DcMotor leadScrewMotor = null;
    ElapsedTime runtime = new ElapsedTime(); //what are we doing here
    public void init(HardwareMap ahwMap, Telemetry telemetry) {
        localtelemetry = telemetry;

        // Save reference to Hardware map
        hwMap = ahwMap;
        leftFrontMotor = ahwMap.get(DcMotor.class, "left_FrontMotor");
        leftRearMotor =ahwMap.get(DcMotor.class, "left_RearMotor");
        rightFrontMotor = ahwMap.get(DcMotor.class, "right_FrontMotor");
        rightRearMotor = ahwMap.get(DcMotor.class, "right_RearMotor");

    }
    public void setMotorDirections() {

        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
        rightRearMotor.setDirection(DcMotor.Direction.REVERSE);


    }
    public void Movement(double drivePower, double robotAngle, double rotPwr)
    {

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = (drivePower* Math.sin(robotAngle + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =  - (drivePower*  Math.cos(robotAngle + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =  (drivePower* Math.cos(robotAngle + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =  - (drivePower*  Math.sin(robotAngle + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

    }





    public void resetMotors() {
        leftRearMotor.setPower(0);
        leftFrontMotor.setPower(0);
        rightRearMotor.setPower(0);
        rightFrontMotor.setPower(0);

    }
    public void normalize(double[] wheelSpeeds)

    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }


}