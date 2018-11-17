package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous(name = "TheMiniGodAutonomous2", group = "Linear")

public class TankBotAutonomous extends LinearOpMode {
    public DcMotor topLeft;
    public DcMotor topRight;
    public DcMotor bottomLeft;
    public DcMotor bottomRight;

    @Override
    public void runOpMode() throws InterruptedException {
        topLeft = hardwareMap.dcMotor.get("topLeft");
        topRight = hardwareMap.dcMotor.get("topRight");
        bottomLeft = hardwareMap.dcMotor.get("bottomLeft");
        bottomRight = hardwareMap.dcMotor.get("bottomRight");

        tankDrive(1, 45, 2);
        sleep(1000);
        tankDrive(1,180,2);
        sleep(1000);


    }
    public void tankDrive(int power, double angle, long duration)
    {
       double topLeftSpeed = -power* Math.sin(( Math.toRadians( angle ))+Math.PI/4);
       double topRightSpeed =power*  Math.cos(( Math.toRadians( angle ))+Math.PI/4);
       double bottomLeftSpeed =-power* Math.cos(( Math.toRadians( angle ))+Math.PI/4);
       double bottomRightSpeed =power*  Math.sin(( Math.toRadians( angle ))+Math.PI/4);
        topLeft.setPower(topLeftSpeed);
        topRight.setPower(topRightSpeed);
        bottomLeft.setPower(bottomLeftSpeed);
        bottomRight.setPower(bottomRightSpeed);
       long SleepTime = (duration*1000);
       sleep(SleepTime);
       topLeft.setPower(0);
        topRight.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);



    }
}
