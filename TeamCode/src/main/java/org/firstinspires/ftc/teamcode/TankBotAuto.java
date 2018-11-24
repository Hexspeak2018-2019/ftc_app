package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Disabled
@Autonomous(name = "ous", group = "Linear")

public class TankBotAuto extends LinearOpMode {
    public DcMotor  leftFrontMotor;
    public DcMotor rightFrontMotor;
    public DcMotor leftRearMotor;
    public DcMotor rightRearMotor;

    @Override
    public void runOpMode() throws InterruptedException {
         leftFrontMotor = hardwareMap.dcMotor.get("frontLeft");
        rightFrontMotor = hardwareMap.dcMotor.get("frontRight");
        leftRearMotor = hardwareMap.dcMotor.get("rearLeft");
        rightRearMotor = hardwareMap.dcMotor.get("rearRight");

        double drivePower  = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle  = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rotPwr      = gamepad1.right_stick_x;

        tankDrive(1, 45, 0,2);

        tankDrive(1,180,0,2);

        tankDrive(drivePower,robotAngle,rotPwr);

    }



    public void tankDrive(double drivePower, double robotAngle, double rotPwr,long duration)
    {
        double angleInRad = robotAngle*(Math.PI/180);

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = - (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   (drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  = - (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   (drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

        long SleepTime = (duration*1000);

        sleep(SleepTime);


        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
        leftRearMotor.setPower(0);
        rightRearMotor.setPower(0);

    }

    public void tankDrive(double drivePower, double robotAngle, double rotPwr)
    {
        double angleInRad = robotAngle*(Math.PI/180);

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = - (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   (drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  = - (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   (drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        leftFrontMotor.setPower(wheelSpeeds[0]);
        rightFrontMotor.setPower(wheelSpeeds[1]);
        leftRearMotor.setPower(wheelSpeeds[2]);
        rightRearMotor.setPower(wheelSpeeds[3]);

    }



    private void normalize(double[] wheelSpeeds)

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

