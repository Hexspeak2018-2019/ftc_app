package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name = "DcMotor", group = "Linear")
public class DcMotorTest extends LinearOpMode {

    private DcMotor Lefty;
    private DcMotor Upy;
    @Override
    public void runOpMode () throws InterruptedException
    {

        Lefty = hardwareMap.dcMotor.get("bottomLeft");
        Upy = hardwareMap.dcMotor.get("bottomRight");



        waitForStart();

        while (opModeIsActive()) {

            if (gamepad1.dpad_left) {
                Lefty.setPower(.5);
            }
            if (gamepad1.dpad_right) {
                Lefty.setPower(-.5);
            }
            if (gamepad1.dpad_up) {
                Upy.setPower(.5);
            }
            if (gamepad1.dpad_right) {
                Upy.setPower(-.5);




            }

            }






















    }






























}