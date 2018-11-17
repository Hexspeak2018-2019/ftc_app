package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;


@Autonomous(name = "LeadScrewProgram", group = "Linear")

public class HexbotRoverMethods  extends LinearOpMode {

LeadClass lead = new LeadClass();


    public void runOpMode() throws InterruptedException {

        waitForStart();
        while (opModeIsActive()) {


            try {
                lead.leadScrewUp(16,1,10, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
            break;
        }
    }
       /* public void leadScrewUp ( double distance, double power, double timeout){
            HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
            robot.init(hardwareMap, telemetry);
            robot.resetMotorsAndEncoders();

                int tolerance = 50;
                int leadScrewPitch = 2;
                int counts = ((int) Math.round(distance / leadScrewPitch * robot.ANDYMARK_TICKS_PER_REV));
//must set direction first
                robot.leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
                robot.leadScrewMotor.setTargetPosition(counts);
//then set the mode
                robot.leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
                robot.runtime.reset();
                robot.leadScrewMotor.setPower(power);

                while (Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                        > tolerance || (opModeIsActive()))  {

                    if (robot.runtime.seconds() > timeout) {
                        break;
                    }


                    robot.localtelemetry.addData("Current LeadScrew Counts:", (robot.leadScrewMotor.getCurrentPosition()));
                    robot.localtelemetry.addData("LeadScrew Power:", robot.leadScrewMotor.getPower());
                    robot.localtelemetry.addData("LeadScrew Target Pos:", robot.leadScrewMotor.getTargetPosition());
                    robot.localtelemetry.update();
                }

            robot.resetMotorsAndEncoders();
        }


        public void leadScrewDown ( double distance, double power, double timeout){
            HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
            robot.init(hardwareMap, telemetry);
            robot.resetMotorsAndEncoders();

                int tolerance = 50;
                int leadScrewPitch = 2;
                int counts = (int) Math.round(distance / leadScrewPitch * robot.ANDYMARK_TICKS_PER_REV);
//must set direction first
                robot.leadScrewMotor.setDirection(DcMotor.Direction.REVERSE);
//then set position
                robot.leadScrewMotor.setTargetPosition(-counts);
//then set the mode
                robot.leadScrewMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//then set the desired power
                robot.runtime.reset();
                robot.leadScrewMotor.setPower(power);

                while (Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                        > tolerance || Math.abs(robot.leadScrewMotor.getTargetPosition() - robot.leadScrewMotor.getCurrentPosition())
                        > tolerance) {
                    if (robot.runtime.seconds() > timeout) {
                        break;
                    }

                    robot.localtelemetry.addData("Current LeadScrew Counts:", (robot.leadScrewMotor.getCurrentPosition()));
                    robot.localtelemetry.addData("LeadScrew Power:", robot.leadScrewMotor.getPower());
                    robot.localtelemetry.addData("LeadScrew Target Pos:", robot.leadScrewMotor.getTargetPosition());
                    robot.localtelemetry.update();
                }

            robot.resetMotorsAndEncoders();
        }
    public void tankDrive(double drivePower, double robotAngle, double rotPwr,double duration)
    {
        HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
        robot.init(hardwareMap, telemetry);
        robot.resetMotorsAndEncoders();
        double angleInRad = (robotAngle +180 )*(Math.PI/180);

        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  =   (drivePower* Math.sin(angleInRad + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =   -(drivePower*  Math.cos(angleInRad + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =   (drivePower* Math.cos(angleInRad + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =   -(drivePower*  Math.sin(angleInRad + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        robot.leftFrontMotor.setPower(wheelSpeeds[0]);
        robot.rightFrontMotor.setPower(wheelSpeeds[1]);
        robot.leftRearMotor.setPower(wheelSpeeds[2]);
        robot.rightRearMotor.setPower(wheelSpeeds[3]);

        long SleepTime = Math.round (duration*1000);


        robot.leftFrontMotor.setPower(0);
        robot.rightFrontMotor.setPower(0);
        robot.leftRearMotor.setPower(0);
        robot.rightRearMotor.setPower(0);

    }
    public void tankDrive(double drivePower, double robotAngle, double rotPwr)
    {
        HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
        robot.init(hardwareMap, telemetry);
        robot.resetMotorsAndEncoders();
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0]  = (drivePower* Math.sin(robotAngle + Math.PI/4) +rotPwr);
        wheelSpeeds[1]  =  - (drivePower*  Math.cos(robotAngle + Math.PI/4) - rotPwr);
        wheelSpeeds[2]  =  (drivePower* Math.cos(robotAngle + Math.PI/4) + rotPwr);
        wheelSpeeds[3]  =  - (drivePower*  Math.sin(robotAngle + Math.PI/4) - rotPwr);

        normalize(wheelSpeeds);

        //make sure all motors run forward when set to positive power

        robot.leftFrontMotor.setPower(wheelSpeeds[0]);
        robot.rightFrontMotor.setPower(wheelSpeeds[1]);
        robot.leftRearMotor.setPower(wheelSpeeds[2]);
        robot.rightRearMotor.setPower(wheelSpeeds[3]);
        robot.localtelemetry.addData("Left Front Power", (robot.leftFrontMotor.getCurrentPosition()));
        robot.localtelemetry.addData("Right Front Power", (robot.rightFrontMotor.getCurrentPosition()));
        robot.localtelemetry.addData("Left Rear Power", (robot.leftRearMotor.getCurrentPosition()));
        robot.localtelemetry.addData("Right Rear Power", (robot.rightRearMotor.getCurrentPosition()));

    }




    public void normalize(double[] wheelSpeeds)

    {
        HardwareHexbotRoverRuckus robot = new HardwareHexbotRoverRuckus();
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
        }*/
    }


