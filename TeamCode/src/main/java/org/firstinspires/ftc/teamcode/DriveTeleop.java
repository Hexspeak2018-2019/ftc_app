package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Drive Teleop", group = "Practice")
public class DriveTeleop extends OpMode {

    DcMotor leftFront;
    DcMotor rightFront;
    DcMotor leftBack;
    DcMotor rightBack;
    double drivePowerLeft = gamepad1.left_stick_y;
    double drivePowerRight = gamepad1.right_stick_y;

    @Override
    public void init() {
        leftFront =hardwareMap.dcMotor.get("leftFront");
        rightFront =hardwareMap.dcMotor.get("rightFront");
        leftBack =hardwareMap.dcMotor.get("leftBack");
        rightBack =hardwareMap.dcMotor.get("rightBack");

         leftFront.setDirection(DcMotor.Direction.FORWARD);
         rightFront.setDirection(DcMotor.Direction.FORWARD);
         leftBack.setDirection(DcMotor.Direction.FORWARD);
         rightBack.setDirection(DcMotor.Direction.FORWARD);
    }

    @Override
    public void loop(){
        leftFront.setPower(drivePowerLeft);
        leftBack.setPower(drivePowerLeft);
        rightBack.setPower(drivePowerRight);
        rightFront.setPower(drivePowerRight);
    }



}
